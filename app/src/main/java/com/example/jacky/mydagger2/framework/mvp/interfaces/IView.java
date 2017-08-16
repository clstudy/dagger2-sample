package com.example.jacky.mydagger2.framework.mvp.interfaces;

import android.content.Intent;

/**
 * Created by jacky on 2017/8/16.
 * banker developer. <br/>
 * <br/>
 */

public interface IView {

    void showLoading();

    void hideLoading();

    void showMessage(String message);

    void launchActivity(Intent intent);

    void killMyself();
}
