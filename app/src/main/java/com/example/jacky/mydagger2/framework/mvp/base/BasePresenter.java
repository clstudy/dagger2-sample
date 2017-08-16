package com.example.jacky.mydagger2.framework.mvp.base;

import com.example.jacky.mydagger2.framework.mvp.interfaces.IModel;
import com.example.jacky.mydagger2.framework.mvp.interfaces.IPresenter;
import com.example.jacky.mydagger2.framework.mvp.interfaces.IView;

/**
 * Created by jacky on 2017/8/16.
 * banker developer. <br/>
 * <br/>
 */

public class BasePresenter<M extends IModel, V extends IView> implements IPresenter {

    protected M mModel;
    protected V mRootView;

    public BasePresenter(M model, V rootView) {
        this.mModel = model;
        this.mRootView = rootView;
        onStart();
    }

    public BasePresenter(V rootView) {
        this.mRootView = rootView;
        onStart();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }
}
