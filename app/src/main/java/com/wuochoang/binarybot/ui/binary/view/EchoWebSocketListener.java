package com.wuochoang.binarybot.ui.binary.view;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.Gson;
import com.olddog.common.ToastUtils;
import com.wuochoang.binarybot.common.Constant;
import com.wuochoang.binarybot.common.utils.Utils;
import com.wuochoang.binarybot.model.BalanceReceived;
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
    private String request;
    private BinaryPresenter presenter;

    private boolean isAuthorized = false;
    private ObservableEmitter<String> wsObservable;
    private Observable<String> observablee;

    public static EchoWebSocketListener getInstance(String request, BinaryPresenter presenter) {
        EchoWebSocketListener echoWebSocketListener = new EchoWebSocketListener();
        echoWebSocketListener.request = request;
        echoWebSocketListener.presenter = presenter;
        return echoWebSocketListener;
    }


    @Override
    public void onOpen(WebSocket webSocket, Response response) {
//        webSocket.send(request);
//        String balanceRequest = Utils.objectToJson(new BalanceRequest(1, 1));
//        webSocket.send(balanceRequest);
//        webSocket.close(NORMAL_CLOSURE_STATUS, "Bye");
    }

    @SuppressLint("CheckResult")
    @Override
    public void onMessage(WebSocket webSocket, String text) {
       wsObservable.onNext(text);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableObserver<String>() {
//                    @Override
//                    public void onNext(String s) {
//                        Log.d("WebSocket", s);
////                        wsObservable.onNext(s);
//                        observablee.just(s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {

    }

    public ObservableEmitter<String> getWsObservable() {
        return wsObservable;
    }

    public void setWsObservable(ObservableEmitter<String> wsObservable) {
        this.wsObservable = wsObservable;
    }

    public Observable<String> getObservablee() {
        return observablee;
    }

    public void setObservablee(Observable<String> observablee) {
        this.observablee = observablee;
    }
}

