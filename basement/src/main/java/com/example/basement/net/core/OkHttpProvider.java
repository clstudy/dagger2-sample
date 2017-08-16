package com.example.basement.net.core;

import com.example.basement.net.core.interceptor.CacheControlInterceptor;
import com.example.basement.utils.UIUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;


/**
 * Created by jacky on 2016/11/30.
 * banker develper
 */
public class OkHttpProvider {
    private static final String TAG = "OkHttpProvider";
    private final static long DEFAULT_TIMEOUT_CONN = 10;
    private final static long DEFAULT_TIMEOUT_IO = 10;

    public static OkHttpClient.Builder newOkHttpClientBuilder(Interceptor cacheControl, Interceptor customInterceptor) {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT_CONN, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT_IO, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT_IO, TimeUnit.SECONDS);
        //设置缓存
        File httpCacheDirectory = new File(UIUtils.getContext().getCacheDir(), "OkHttpCache");
        httpClientBuilder.cache(new Cache(httpCacheDirectory, 100 * 1024 * 1024));
        // 自定义拦截器
        if (customInterceptor != null) {
            httpClientBuilder.addInterceptor(customInterceptor);
        }
        //设置缓存拦截器
        if (cacheControl != null) {
            httpClientBuilder.addNetworkInterceptor(cacheControl);
        }
        return httpClientBuilder;
    }

    /**
     * @return 有缓存的OkHttpClient
     */
    public static OkHttpClient getDefaultOkHttpClient() {
        return newOkHttpClientBuilder(new CacheControlInterceptor(), null)
                .build();
    }


}
