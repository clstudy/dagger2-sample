package com.example.jacky.mydagger2.mvp.contract;


import com.example.jacky.mydagger2.framework.mvp.interfaces.IModel;
import com.example.jacky.mydagger2.framework.mvp.interfaces.IView;
import com.example.jacky.mydagger2.mvp.di.model.bean.Food;

import rx.Observable;


public interface FoodContract {

    interface View extends IView {

        void onGetFood(Food nodes);
    }

    interface Model extends IModel {
        Observable<Food> getFood();

    }
}
