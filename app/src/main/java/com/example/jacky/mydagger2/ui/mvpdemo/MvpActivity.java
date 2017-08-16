package com.example.jacky.mydagger2.ui.mvpdemo;

import android.os.Bundle;

import com.example.jacky.mydagger2.R;
import com.example.jacky.mydagger2.framework.mvp.BaseMvpActivity;

/**
 * Created by jacky on 2017/8/16.
 * banker developer. <br/>
 * <br/>
 */

public class MvpActivity extends BaseMvpActivity {

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_mvp1;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
