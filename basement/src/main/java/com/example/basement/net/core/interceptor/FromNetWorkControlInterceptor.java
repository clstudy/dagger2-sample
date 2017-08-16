package com.example.basement.net.core.interceptor;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jacky on 2016/12/12.
 * banker develper
 */
public class FromNetWorkControlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build();

        Response response = chain.proceed(request);

        return response;
    }
}