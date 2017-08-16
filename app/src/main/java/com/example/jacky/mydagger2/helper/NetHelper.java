package com.example.jacky.mydagger2.helper;

import com.example.basement.net.core.OkHttpProvider;
import com.example.basement.net.core.ServiceProvider;
import com.example.basement.net.core.interceptor.FromNetWorkControlInterceptor;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by jacky on 2017/7/4.
 * banker developer. <br/>
 * <br/>
 * <p>对basement 的底层再的网络做一层封装</p>
 */

public class NetHelper {
    private static final String TAG = "NetHelper";

    private NetHelper() {
    }

    private static ServiceProvider newServiceProvider(OkHttpClient client) {
        return new ServiceProvider.Builder()
                .addHttpClient(client)
                .build();
    }

    private static ServiceProvider newServiceProvider() {
        return newServiceProvider(newHttpClient());
    }

    private static OkHttpClient newHttpClient() {
        OkHttpClient.Builder builder = OkHttpProvider.newOkHttpClientBuilder(new FromNetWorkControlInterceptor(), new CustomInterceptor());
//        // 设置cookies相关
//        builder.cookieJar(new CookieJar() {
//
//            @Override
//            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                LogUtils.d(TAG, "saveFromResponse: cookies size=" + cookies.size());
//                for (int i = 0; i < cookies.size(); i++) {
//                    if (TextUtils.equals(cookies.get(i).name(), "JSESSIONID")) {
//                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(UIUtils.getContext());
//                        sharedPreferences.edit().putString(PreferencesKeys.HEADERS_JSESSIONID, cookies.get(i).value()).apply();
//                        LogUtils.d(TAG, "saveFromResponse: JSESSIONID=" + cookies.get(i).value());
//                    }
//                }
//            }
//
//            @Override
//            public List<Cookie> loadForRequest(HttpUrl url) {
//                return new ArrayList<Cookie>();
//            }
//        });
        //设置日志拦截器
        builder.addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY));//可设置日志是否打印到屏幕
        return builder.build();
    }

    public static <S> S provideExposeFieldService(Class<S> serviceClass) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //不导出实体中没有用@Expose注解的属性.
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gsonBuilder.create());
        return newServiceProvider().createService(serviceClass, gsonConverterFactory);
    }

    public static <S> S provideService(Class<S> serviceClass) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gsonBuilder.create());
        return newServiceProvider().createService(serviceClass, gsonConverterFactory);
    }

    public static <S> S getServiceByCustom(Class<S> serviceClass, Converter.Factory factory) {
        return newServiceProvider().createService(serviceClass, factory);
    }

    public static <S> S getServiceByCustom(Class<S> serviceClass, OkHttpClient client, Converter.Factory factory) {
        return newServiceProvider(client).createService(serviceClass, factory);
    }

}
