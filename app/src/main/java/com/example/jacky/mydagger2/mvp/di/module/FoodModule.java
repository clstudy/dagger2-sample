package com.example.jacky.mydagger2.mvp.di.module;


import com.example.jacky.mydagger2.di.scope.ActivityScope;
import com.example.jacky.mydagger2.mvp.contract.FoodContract;
import com.example.jacky.mydagger2.mvp.di.model.FoodModel;

import dagger.Module;
import dagger.Provides;

@Module
public class FoodModule {

    private FoodContract.View mView;

    public FoodModule(FoodContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    FoodContract.View provideView() {
        return mView;
    }

    @ActivityScope
    @Provides
    FoodContract.Model provideModel(FoodModel model) {
        return model;
    }
}
