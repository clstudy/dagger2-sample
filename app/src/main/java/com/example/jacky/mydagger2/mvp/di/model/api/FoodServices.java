package com.example.jacky.mydagger2.mvp.di.model.api;


import com.example.jacky.mydagger2.mvp.di.model.bean.Food;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by jacky on 2017/8/3.
 * banker developer. <br/>
 * <br/>
 */

public interface FoodServices {
    //http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10

    String BASE_URL = "http://food.boohee.com/fb/v1/feeds/";

    @GET("category_feed?page=1&category=1&per=10")
    Observable<Food> getFood();
}
