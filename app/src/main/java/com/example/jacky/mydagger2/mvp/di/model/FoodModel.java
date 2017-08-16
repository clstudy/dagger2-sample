package com.example.jacky.mydagger2.mvp.di.model;


import com.example.jacky.mydagger2.di.scope.ActivityScope;
import com.example.jacky.mydagger2.helper.NetHelper;
import com.example.jacky.mydagger2.mvp.contract.FoodContract;
import com.example.jacky.mydagger2.mvp.di.model.api.FoodServices;
import com.example.jacky.mydagger2.mvp.di.model.bean.Food;

import javax.inject.Inject;

import rx.Observable;


@ActivityScope
public class FoodModel implements FoodContract.Model {

    @Inject
    public FoodModel() {
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Observable<Food> getFood() {
        Observable<Food> food = NetHelper.provideService(FoodServices.class).getFood();
        return food;
    }
}
