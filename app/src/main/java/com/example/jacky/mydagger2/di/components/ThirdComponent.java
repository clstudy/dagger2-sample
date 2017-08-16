package com.example.jacky.mydagger2.di.components;

import com.example.jacky.mydagger2.di.modules.ThirdModule;
import com.example.jacky.mydagger2.di.scope.ActivityScope;
import com.example.jacky.mydagger2.ui.didemo.ThirdActivity;

import dagger.Component;

/**
 * Created by jacky on 2017/8/15.
 * banker developer. <br/>
 * <br/>
 * <p>
 * public interface ThirdComponent{
 * public interface ThirdComponent extends ThirdUserComponent {
 * 这两个都可以
 * </p>
 */

@ActivityScope
@Component(dependencies = ThirdUserComponent.class,
        modules = ThirdModule.class)
public interface ThirdComponent {

    void inject(ThirdActivity thirdActivity);

    final class Initializer {
        private Initializer() {
        }

        public static ThirdComponent init(ThirdUserComponent userComponent) {
            return DaggerThirdComponent.builder().thirdModule(new ThirdModule()).thirdUserComponent(userComponent).build();
        }
    }
}
