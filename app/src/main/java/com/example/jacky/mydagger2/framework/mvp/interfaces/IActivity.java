package com.example.jacky.mydagger2.framework.mvp.interfaces;

import android.os.Bundle;

import com.example.jacky.mydagger2.mvp.di.component.AppComponent;

/**
 * Created by jacky on 2017/8/16.
 * banker developer. <br/>
 * <br/>
 */

public interface IActivity {

    void setupActivityComponent(AppComponent appComponent);

    boolean useEventBus();

    int initView(Bundle savedInstanceState);

    void initData(Bundle savedInstanceState);

    boolean useFragment();
}
