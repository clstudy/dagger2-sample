package com.example.jacky.mydagger2.framework;

/**
 * Created by jess on 17/03/2017 11:15
 * Contact with jess.yan.effort@gmail.com
 */

public interface IRepositoryManager {

    /**
     * 注入RetrofitService
     * @param services
     */
    void injectRetrofitService(Class<?>... services);


    /**
     * 注入CacheService
     * @param services
     */
    void injectCacheService(Class<?>... services);


    /**
     * 根据传入的Class获取对应的Retrift service
     *
     * @param service
     * @param <T>
     * @return
     */
    <T> T obtainRetrofitService(Class<T> service);

    /**
     * 根据传入的Class获取对应的RxCache service
     *
     * @param cache
     * @param <T>
     * @return
     */
    <T> T obtainCacheService(Class<T> cache);

}
