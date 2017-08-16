package com.example.basement.net.core.download;


import com.example.basement.utils.CacheUtils;
import com.example.basement.utils.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jacky on 2017/3/6.
 * banker devel0per
 * <p></p>
 */
public class DownloadManager {
    private final String TAG = "DownloadManager";

    private static final AtomicReference<DownloadManager> INSTANCE = new AtomicReference<>();
    private HashMap<String, Call> mDownCalls;//用来存放各个下载的请求
    private HashMap<String, String> mSaveFilesNames;//用来存放各个下载的保存的本地全路径
    private OkHttpClient mClient;//OKHttpClient;

    //获得一个单例类
    public static DownloadManager getInstance() {
        for (; ; ) {
            DownloadManager current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new DownloadManager();
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }

    private DownloadManager() {
        mDownCalls = new HashMap<>();
        mSaveFilesNames = new HashMap<>();
        mClient = new OkHttpClient.Builder().build();
    }

    public String getDownloadPath(String url) {
        return mSaveFilesNames.get(url);
    }

    public void deleteDownloadFile(String url) {
        String downloadPath = getDownloadPath(url);
        if (downloadPath == null) return;
        File file = new File(downloadPath);
        if (!file.exists()) return;
        boolean delete = file.delete();
        LogUtils.d(TAG, "deleteDownloadFile=" + delete);
    }

    /**
     * 开始下载
     *
     * @param url              下载请求的网址
     * @param savePath         保存的path路径
     * @param downLoadObserver 用来回调的接口
     */
    public void download(final String url, final File savePath, Subscriber<DownloadInfo> downLoadObserver) {
        if (savePath == null || !savePath.exists()) return;
        Observable.just(url)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !mDownCalls.containsKey(s);
                    }
                })
                .flatMap(new Func1<String, Observable<DownloadInfo>>() {
                    @Override
                    public Observable<DownloadInfo> call(String s) {
                        return Observable.just(createDownInfo(s, savePath));
                    }
                })
                .map(new Func1<DownloadInfo, DownloadInfo>() {
                    @Override
                    public DownloadInfo call(DownloadInfo downloadInfo) {
                        mSaveFilesNames.put(url, savePath + downloadInfo.getFileName());
                        LogUtils.d(TAG, "downloadInfo.getFileName()=" + savePath + downloadInfo.getFileName());
                        return getRealFileName(downloadInfo);
                    }
                })
                .flatMap(new Func1<DownloadInfo, Observable<DownloadInfo>>() {
                    @Override
                    public Observable<DownloadInfo> call(DownloadInfo downloadInfo) {
                        return Observable.create(new DownloadSubscribe(downloadInfo));
                    }
                })
                .sample(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())//在主线程回调
                .subscribeOn(Schedulers.io())//在子线程执行
                .subscribe(downLoadObserver);//添加观察者
    }

    public void cancel(String url) {
        Call call = mDownCalls.get(url);
        if (call != null) {
            call.cancel();//取消
        }
        mDownCalls.remove(url);
    }

    /**
     * 创建DownInfo
     *
     * @param url 请求网址
     * @return DownInfo
     */
    private DownloadInfo createDownInfo(String url, File savePath) {
        DownloadInfo downloadInfo = new DownloadInfo(url, savePath);
        long contentLength = getContentLength(url);//获得文件大小
        downloadInfo.setTotal(contentLength);
        String fileName = url.substring(url.lastIndexOf("/"));
        downloadInfo.setFileName(fileName);
        return downloadInfo;
    }

    private DownloadInfo getRealFileName(DownloadInfo downloadInfo) {
        String fileName = downloadInfo.getFileName();
        long downloadLength = 0, contentLength = downloadInfo.getTotal();
        if (contentLength == -1) {
            throw new IllegalArgumentException("源链接不可用");
        }

        File file = new File(downloadInfo.getSavePath(), fileName);
        if (file.exists()) {
            //找到了文件,代表已经下载过,则获取其长度
            downloadLength = file.length();
        }
        //之前下载过,需要重新来一个文件
        int i = 1;
        while (downloadLength >= contentLength) {
            int dotIndex = fileName.lastIndexOf(".");
            String fileNameOther;
            if (dotIndex == -1) {
                fileNameOther = fileName + "(" + i + ")";
            } else {
                fileNameOther = fileName.substring(0, dotIndex)
                        + "(" + i + ")" + fileName.substring(dotIndex);
            }
            File newFile = new File(downloadInfo.getSavePath(), fileNameOther);
            file = newFile;
            downloadLength = newFile.length();
            i++;
        }
        //设置改变过的文件名/大小
        downloadInfo.setProgress(downloadLength);
        downloadInfo.setFileName(file.getName());
        return downloadInfo;
    }

    private class DownloadSubscribe implements Observable.OnSubscribe<DownloadInfo> {
        private DownloadInfo downloadInfo;

        public DownloadSubscribe(DownloadInfo downloadInfo) {
            this.downloadInfo = downloadInfo;
        }

        @Override
        public void call(Subscriber<? super DownloadInfo> subscriber) {
            String url = downloadInfo.getUrl();
            long downloadLength = downloadInfo.getProgress();//已经下载好的长度
            long contentLength = downloadInfo.getTotal();//文件的总长度
            //初始进度信息
            subscriber.onNext(downloadInfo);

            Request request = new Request.Builder()
                    //确定下载的范围,添加此头,则服务器就可以跳过已经下载好的部分
                    .addHeader("RANGE", "bytes=" + downloadLength + "-" + contentLength)
                    .url(url)
                    .build();
            Call call = mClient.newCall(request);
            mDownCalls.put(url, call);//把这个添加到call里,方便取消

            File file = new File(downloadInfo.getSavePath(), downloadInfo.getFileName());
            InputStream is = null;
            FileOutputStream fileOutputStream = null;
            try {
                Response response = call.execute();
                is = response.body().byteStream();
                fileOutputStream = new FileOutputStream(file, true);
                byte[] buffer = new byte[2048];//缓冲数组2kB
                int len;
                while ((len = is.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                    downloadLength += len;
                    downloadInfo.setProgress(downloadLength);
                    subscriber.onNext(downloadInfo);
                }
                fileOutputStream.flush();
                mDownCalls.remove(url);
            } catch (Exception e) {
                e.printStackTrace();
                subscriber.onError(e);
            } finally {
                //关闭IO流
                CacheUtils.closeQuietly(is);
                CacheUtils.closeQuietly(fileOutputStream);
            }
            subscriber.onCompleted();//完成
        }
    }

    /**
     * 获取下载长度
     *
     * @param downloadUrl
     * @return
     */

    private long getContentLength(String downloadUrl) {
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        try {
            Response response = mClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                long contentLength = response.body().contentLength();
                response.close();
                return contentLength == 0 ? DownloadInfo.TOTAL_ERROR : contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DownloadInfo.TOTAL_ERROR;
    }


}
