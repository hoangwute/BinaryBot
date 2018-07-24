package com.wuochoang.binarybot.ui.binary.presenter;

import com.wuochoang.binarybot.base.BasePresenter;
import com.wuochoang.binarybot.common.Constant;
import com.wuochoang.binarybot.ui.binary.view.BinaryView;
import com.wuochoang.binarybot.ui.binary.view.EchoWebSocketListener;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.WebSocket;


/**
 * Created by HoangNQ on 23,July,2018
 */
public class BinaryPresenter extends BasePresenter<BinaryView> {

    private ObservableEmitter<String> observableEmitter;
    private WebSocket webSocket;

    public ObservableEmitter<String> getObservableEmitter() {
        return observableEmitter;
    }

    public void setObservableEmitter(ObservableEmitter<String> observableEmitter) {
        this.observableEmitter = observableEmitter;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public void setWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    public void authenticateAccount() {
        webSocket.send(Constant.AUTHORIZATION_REQUEST);
        Observable<String> authenticateObservable = Observable.create(e -> observableEmitter = e);
        authenticateObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        if (getView() != null) {
                            getView().onAuthenicate(s);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getBalance() {
        webSocket.send(Constant.BALANCE_REQUEST);
        Observable<String> authenticateObservable = Observable.create(e -> observableEmitter = e);
        authenticateObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        if (getView() != null) {
                            getView().getBalance(s);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
