package com.example.jacky.mydagger2.helper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Custom interceptor made to include all headers
 */
public class CustomInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        builder.removeHeader("User-Agent")
//                .addHeader("User-agent", NetworkUtils.getDefaultUserAgent())
                .addHeader("Content-Type", "text/html; charset=utf-8")
                .addHeader("Referer", "TechFinancials Android App");
//        builder.addHeader("Content-Encoding", "gzip");

//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(UIUtils.getContext());
//        if (sharedPreferences.contains(PreferencesKeys.HEADERS_JSESSIONID)) {
//            builder.addHeader("Cookie", "JSESSIONID=" + sharedPreferences.getString(PreferencesKeys.HEADERS_JSESSIONID, ""));
//        }

        originalRequest = builder.build();
        return chain.proceed(originalRequest);
    }

    private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
