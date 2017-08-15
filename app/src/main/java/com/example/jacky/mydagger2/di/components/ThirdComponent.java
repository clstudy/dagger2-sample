package com.example.jacky.mydagger2.di.components;

import com.example.jacky.mydagger2.di.modules.ThirdModule;
import com.example.jacky.mydagger2.di.scope.ActivityScope;
import com.example.jacky.mydagger2.ui.ThirdActivity;

import dagger.Component;

/**
 * Created by jacky on 2017/8/15.
 * banker developer. <br/>
 * <br/>
 */

@ActivityScope
@Component(dependencies = ThirdUserComponent.class,
        modules = ThirdModule.class)
public interface ThirdComponent extends ThirdUserComponent {

    void inject(ThirdActivity thirdActivity);

    final class Initializer {
        private Initializer() {
        }

        public static ThirdComponent init(ThirdUserComponent userComponent) {
            return DaggerThirdComponent.builder().thirdModule(new ThirdModule()).thirdUserComponent(userComponent).build();
        }
    }
}
