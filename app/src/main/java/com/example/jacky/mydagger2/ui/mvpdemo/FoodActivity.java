package com.example.jacky.mydagger2.ui.mvpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.basement.utils.UIUtils;
import com.example.jacky.mydagger2.R;
import com.example.jacky.mydagger2.framework.mvp.BaseMvpActivity;
import com.example.jacky.mydagger2.mvp.contract.FoodContract;
import com.example.jacky.mydagger2.mvp.di.component.AppComponent;
import com.example.jacky.mydagger2.mvp.di.component.DaggerFoodComponent;
import com.example.jacky.mydagger2.mvp.di.model.bean.Food;
import com.example.jacky.mydagger2.mvp.di.module.FoodModule;
import com.example.jacky.mydagger2.mvp.presenter.FoodPresenter;

/**
 * Created by jacky on 2017/8/16.
 * banker developer. <br/>
 * <br/>
 */

public class FoodActivity extends BaseMvpActivity<FoodPresenter> implements FoodContract.View {

    private TextView mFoodName;

    @Override
    public void showLoading() {
        UIUtils.showShortToast("showLoading");
    }

    @Override
    public void hideLoading() {
        UIUtils.showShortToast("hideLoading");
    }

    @Override
    public void showMessage(String message) {
        UIUtils.showShortToast(message);
    }

    @Override
    public void launchActivity(Intent intent) {
        UIUtils.showShortToast("launchActivity");
    }

    @Override
    public void killMyself() {
        UIUtils.showShortToast("killMyself");
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        super.setupActivityComponent(appComponent);
        DaggerFoodComponent.builder().appComponent(appComponent)
                .foodModule(new FoodModule(this)).build().inject(this);
    }

    @Override
    public void onGetFood(Food food) {
        mFoodName.setText(food.getFeeds().get(0).getCard_image());
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_food;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mFoodName = (TextView) findViewById(R.id.food_name);
        mPresenter.getFood();
    }

}
