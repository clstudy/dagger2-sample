package com.example.jacky.mydagger2.framework.mvp.base;

import com.example.jacky.mydagger2.framework.IRepositoryManager;
import com.example.jacky.mydagger2.framework.mvp.interfaces.IModel;

/**
 * Created by jacky on 2017/8/16.
 * banker developer. <br/>
 * <br/>
 */

public class BaseModel implements IModel {
    protected IRepositoryManager mRepositoryManager;//用于管理网络请求层,以及数据缓存层

    public BaseModel(IRepositoryManager repositoryManager) {
        this.mRepositoryManager = repositoryManager;
    }

    @Override
    public void onDestroy() {
        mRepositoryManager = null;
    }
}
