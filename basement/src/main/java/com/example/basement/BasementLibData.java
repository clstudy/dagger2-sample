package com.example.basement;

import android.content.Context;

/**
 * Created by jacky on 2016/9/30.
 * sounbus app-developer
 */

public class BasementLibData {

    public static Context mContext;
    public static String API_BASE_URL;

    public static Context getContext() {
        return mContext;
    }

    public static void init(Context context, String api_base_url) {
        mContext = context;
        API_BASE_URL = api_base_url;
    }

}
