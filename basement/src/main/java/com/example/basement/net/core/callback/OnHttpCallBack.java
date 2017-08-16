package com.example.basement.net.core.callback;

/**
 * Created by jacky on 2016/11/29.
 * banker develper
 * <p>Common CallBack</p>
 */
public interface OnHttpCallBack<T> {

    /**
     * 请求网络得到model
     *
     * @param t 数据模型
     */
    void onSuccessful(T t);

    /**
     * 错误信息
     *
     * @param errorMsg 错误信息
     */
    void onErrorMsg(String errorMsg);

}
