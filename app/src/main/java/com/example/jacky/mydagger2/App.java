/*
 *  Copyright (C) 2016 BoBoMEe(wbwjx115@gmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.jacky.mydagger2;

import android.app.Application;

import com.example.basement.BasementLibData;
import com.example.jacky.mydagger2.mvp.di.component.AppComponent;

import javax.inject.Inject;

/**
 * Created by jacky on 2017/8/15.
 * banker developer. <br/>
 * <br/>
 */

public class App extends Application {

    private AppComponent appComponent;

    @Inject
    static App app;

    public static App get() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BasementLibData.init(this, null);
        buildComponent();
    }

    private void buildComponent() {
        appComponent = AppComponent.Initializer.init(this);
        appComponent.inject(this);
    }

    public AppComponent component() {
        return appComponent;
    }
}
