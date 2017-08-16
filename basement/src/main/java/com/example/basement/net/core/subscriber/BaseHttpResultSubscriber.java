package com.example.basement.net.core.subscriber;


import com.example.basement.R;
import com.example.basement.utils.LogUtils;
import com.example.basement.utils.UIUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by jacky on 2016/11/30.
 * banker develper
 */
public abstract class BaseHttpResultSubscriber<T> extends Subscriber<T> {
    private static final String TAG = "BaseHttpResultSubscriber";

    @Override
    public void onCompleted() {
        LogUtils.d(TAG, "onCompleted: ");
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.d(TAG, "onError: " + e.getMessage());
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            if (code == 500 || code == 404) {
                _onError(UIUtils.getStringByResId(R.string.server_error));
            }
            _onHandlerHttpException((HttpException) e);
        } else if (e instanceof UnknownHostException) {
            _onError(UIUtils.getStringByResId(R.string.connect_faild));
        } else if (e instanceof ConnectException) {
            _onError(UIUtils.getStringByResId(R.string.connot_connect_server_please_checknet));
        } else if (e instanceof SocketTimeoutException) {
            _onError(UIUtils.getStringByResId(R.string.connect_timeout_please_checknet));
        } else {
            _onError(UIUtils.getStringByResId(R.string.unknow_error));
        }
    }

    @Override
    public void onNext(T response) {
        _onSuccess(response);
    }

    protected abstract void _onSuccess(T response);

    protected abstract void _onError(String error);

    /**
     * ready to handler HttpException
     *
     * @param e HttpException
     */
    protected void _onHandlerHttpException(HttpException e) {
    }

}