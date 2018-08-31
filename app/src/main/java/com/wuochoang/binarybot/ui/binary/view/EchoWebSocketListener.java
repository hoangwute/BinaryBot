package com.wuochoang.binarybot.ui.binary.view;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.Gson;
import com.olddog.common.ToastUtils;
import com.wuochoang.binarybot.common.Constant;
import com.wuochoang.binarybot.common.utils.Utils;
import com.wuochoang.binarybot.model.BalanceRequest;
import com.wuochoang.binarybot.ui.binary.presenter.BinaryPresenter;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by HoangNQ on 23,July,2018
 */

public class EchoWebSocketListener extends WebSocketListener {

    private static final int NORMAL_CLOSURE_STATUS = 1000;
    private ObservableEmitter<String> wsObservable;

    @Override
    public void onOpen(WebSocket webSocket, Response response) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void onMessage(WebSocket webSocket, String text) {
       wsObservable.onNext(text);

    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
    }

    public ObservableEmitter<String> getWsObservable() {
        return wsObservable;
    }

    public void setWsObservable(ObservableEmitter<String> wsObservable) {
        this.wsObservable = wsObservable;
    }

}

