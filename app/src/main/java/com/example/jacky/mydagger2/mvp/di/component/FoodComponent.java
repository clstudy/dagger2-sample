package com.example.jacky.mydagger2.mvp.di.component;


import com.example.jacky.mydagger2.di.scope.ActivityScope;
import com.example.jacky.mydagger2.mvp.di.module.FoodModule;
import com.example.jacky.mydagger2.ui.mvpdemo.FoodActivity;

import dagger.Component;

@ActivityScope
@Component(modules = FoodModule.class, dependencies = AppComponent.class)
public interface FoodComponent {
    void inject(FoodActivity activity);
}
