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

package com.example.jacky.mydagger2.di.components;

import dagger.Component;

/**
 * Created by jacky on 2017/8/15.
 * banker developer. <br/>
 * <br/>
 */

//@ActivityScope  如果被依赖了，使用该注解会报错
@Component
public interface ThirdUserComponent {

//    void inject(ThirdActivity thirdActivity);

    final class Initializer {
        private Initializer() {
        }

        public static ThirdUserComponent init() {
            return DaggerThirdUserComponent.create();
        }
    }
}
