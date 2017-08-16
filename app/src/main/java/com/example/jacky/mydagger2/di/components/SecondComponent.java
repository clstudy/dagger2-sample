package com.example.jacky.mydagger2.di.components;

import com.example.jacky.mydagger2.di.modules.SecondModule;
import com.example.jacky.mydagger2.di.scope.ActivityScope;
import com.example.jacky.mydagger2.ui.didemo.SecondActivity;

import dagger.Component;

/**
 * Created by jacky on 2017/8/15.
 * banker developer. <br/>
 * <br/>
 */

@ActivityScope
@Component(modules = SecondModule.class)
public interface SecondComponent {

    void inject(SecondActivity secondActivity);

    final class Initializer {
        private Initializer() {
        }

        public static SecondComponent init() {
            return DaggerSecondComponent.builder().secondModule(new SecondModule()).build();
        }
    }
}

