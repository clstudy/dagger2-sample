package com.example.jacky.mydagger2.framework.mvp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jacky.mydagger2.App;
import com.example.jacky.mydagger2.mvp.di.component.AppComponent;
import com.example.jacky.mydagger2.framework.mvp.interfaces.IActivity;
import com.example.jacky.mydagger2.framework.mvp.interfaces.IPresenter;

import javax.inject.Inject;

/**
 * Created by jacky on 2017/8/16.
 * banker developer. <br/>
 * <br/>
 */

public abstract class BaseMvpActivity<P extends IPresenter> extends AppCompatActivity implements IActivity {

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(initView(savedInstanceState));
        setupActivityComponent(((App) getApplication()).component());
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public boolean useEventBus() {
        return false;
    }


    @Override
    public boolean useFragment() {
        return false;
    }
}
