package com.example.jacky.mydagger2.di.modules;

import com.example.jacky.mydagger2.di.data.SecondModel;
import com.example.jacky.mydagger2.di.qualifier.SeondQualifier;
import com.example.jacky.mydagger2.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jacky on 2017/8/15.
 * banker developer. <br/>
 * <br/>
 */

@Module
public class SecondModule {

    @ActivityScope
    @Provides
    SecondModel provideSecondModel() {
        return new SecondModel("this is a SecondModel");
    }

    @ActivityScope
    @SeondQualifier("empty")
    @Provides
    SecondModel provideEmptySecondModel() {
        return new SecondModel();
    }

    @ActivityScope
    @SeondQualifier("good")
    @Provides
    SecondModel provideGoodSecondModel() {
        return new SecondModel("good SecondModel");
    }

    @ActivityScope
    @SeondQualifier("bad")
    @Provides
    SecondModel provideBadSecondModel() {
        return new SecondModel("bad SecondModel");
    }
}
