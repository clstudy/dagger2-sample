package com.example.jacky.mydagger2.di.modules;

import com.example.jacky.mydagger2.di.data.ThirdModel;
import com.example.jacky.mydagger2.di.qualifier.ThirdQualifier;
import com.example.jacky.mydagger2.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jacky on 2017/8/15.
 * banker developer. <br/>
 * <br/>
 */

@Module
public class ThirdModule {

    @ActivityScope
    @ThirdQualifier("empty")
    @Provides
    ThirdModel provideEmptyThirdModel() {
        return new ThirdModel();
    }

    @ActivityScope
    @ThirdQualifier("good")
    @Provides
    ThirdModel provideGoodThirdModel() {
        return new ThirdModel("good ThirdModel");
    }

    @ActivityScope
    @ThirdQualifier("bad")
    @Provides
    ThirdModel provideBadThirdModel() {
        return new ThirdModel("bad ThirdModel");
    }

}
