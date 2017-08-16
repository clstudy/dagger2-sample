package com.example.basement.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jacky on 2017/7/3.
 * banker developer. <br/>
 * <br/>
 */

public class NetUtils {

    public static boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) UIUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        //should check null because in air plan mode it will be null
        return netInfo != null && netInfo.isConnected();
    }

}
