package com.example.jacky.mydagger2.mvp.presenter;

import com.example.basement.net.core.subscriber.BaseHttpResultSubscriber;
import com.example.jacky.mydagger2.di.scope.ActivityScope;
import com.example.jacky.mydagger2.framework.mvp.base.BasePresenter;
import com.example.jacky.mydagger2.mvp.contract.FoodContract;
import com.example.jacky.mydagger2.mvp.di.model.bean.Food;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;


@ActivityScope
public class FoodPresenter extends BasePresenter<FoodContract.Model, FoodContract.View> {

    @Inject
    public FoodPresenter(FoodContract.Model model, FoodContract.View rootView) {
        super(model, rootView);
    }

    public void getFood() {
        mModel.getFood()
                .observeOn(AndroidSchedulers.mainThread())//在主线程回调
                .subscribeOn(Schedulers.io())//在子线程执行
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mRootView.showLoading();
                    }
                })
                .subscribe(new BaseHttpResultSubscriber<Food>() {
                    @Override
                    protected void _onSuccess(Food response) {
                        mRootView.onGetFood(response);
                    }

                    @Override
                    protected void _onError(String error) {
                        mRootView.showMessage(error);
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
