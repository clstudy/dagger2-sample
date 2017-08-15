package com.example.jacky.mydagger2.di.data;

import javax.inject.Inject;

import dagger.Module;

/**
 * Created by jacky on 2017/8/15.
 * banker developer. <br/>
 * <br/>
 */

@Module
public class Main2Model {
    private String name;

    @Inject
    public Main2Model() {
    }

    // @Inject  报错了
    public Main2Model(String name) {
        this.name = name;
    }

    public String sayHI() {
        return (name == null ? "" : "cute " + this.name) + ",I am Main2Model say hi to you \n";
    }
}
