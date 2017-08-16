package com.example.basement.net.core;


import com.example.basement.BasementLibData;
import com.example.basement.utils.LogUtils;

import java.lang.reflect.Field;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by jacky on 2016/11/30.
 * banker develper
 */
public class ServiceProvider {
    private static final String TAG = "ServiceProvider";

    private OkHttpClient mOkHttpClient;

    private ServiceProvider() {
        mOkHttpClient = OkHttpProvider.getDefaultOkHttpClient();
    }

    public <S> S createService(Class<S> serviceClass, Converter.Factory factory) {
        String baseUrl = "";
        try {
            Field baseUrlField = serviceClass.getField("BASE_URL");
            baseUrl = (String) baseUrlField.get(serviceClass);
        } catch (Exception e) {
            LogUtils.d(TAG, "createService: BASE_URL not exist");
            baseUrl = BasementLibData.API_BASE_URL;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    public static final class Builder {
        ServiceProvider INSTANCE;

        public Builder() {
            INSTANCE = new ServiceProvider();
        }

        public Builder addHttpClient(OkHttpClient client) {
            if (client != null)
                INSTANCE.mOkHttpClient = client;
            return this;
        }

        public ServiceProvider build() {
            return INSTANCE;
        }

    }


}