package com.wuochoang.binarybot.ui.binary.view;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.olddog.common.ToastUtils;
import com.wuochoang.binarybot.R;
import com.wuochoang.binarybot.base.BaseFragment;
import com.wuochoang.binarybot.base.BasePresenter;
import com.wuochoang.binarybot.common.Constant;
import com.wuochoang.binarybot.ui.binary.presenter.BinaryPresenter;


import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

/**
 * Created by HoangNQ on 23,July,2018
 */
public class BinaryFragment extends BaseFragment implements BinaryView {

    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.btnWebSocket)
    Button btnWebSocket;

    private OkHttpClient client;
    private Request request;
    private WebSocket ws;

    BinaryPresenter binaryPresenter = new BinaryPresenter();
    private EchoWebSocketListener listener;


    @OnClick(R.id.btnWebSocket)

    public void call() {
        EchoWebSocketListener listener = EchoWebSocketListener.getInstance(Constant.AUTHORIZATION_REQUEST, binaryPresenter);
        WebSocket ws = client.newWebSocket(request, listener);
    }

    @OnClick(R.id.btnBalance)
    public void getBalance() {
        getBalanceAccount();
//        authorizationObservable = Observable.create(e -> {
//            listener.setWsObservable(e);
//        });
//        binaryPresenter.setWebSocket(ws);
//        binaryPresenter.authenticateAccount();
//        listener.setWsObservable(binaryPresenter.getObservableEmitter());


//        authorizationObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableObserver<String>() {
//                    @Override
//                    public void onNext(String s) {
//                        ToastUtils.show(s);
//                        balanceObservable = Observable.create(e -> {
//                            listener.setWsObservable(e);
//                        });
//                        ws.send(Constant.BALANCE_REQUEST);
//                        balanceObservable.subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(success -> ToastUtils.show(success));
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
    public void injectDependence() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_binary;
    }

    @Override
    public BasePresenter getPresenter() {
        return binaryPresenter;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        listener = new EchoWebSocketListener();
        ws = client.newWebSocket(request, listener);
        client = new OkHttpClient();
        request = new Request.Builder().url("wss://ws.binaryws.com/websockets/v3?app_id=13114").build();
    }

    private void getBalanceAccount() {
        ws.send(Constant.AUTHORIZATION_REQUEST);
        Observable<String> auObservable = Observable.create(e -> {
            listener.setWsObservable(e);
        });
        auObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    ToastUtils.show(s);
                    Observable<String> balanceObservable = Observable.create(e -> {
                        listener.setWsObservable(e);
                    });
                    ws.send(Constant.BALANCE_REQUEST);
                    balanceObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(success -> ToastUtils.show(success));
                });
    }

    @Override
    public void getBalance(String balance) {
        ToastUtils.show(balance);
    }

    @Override
    public void onAuthenicate(String text) {
        ToastUtils.show(text);
        binaryPresenter.getBalance();
        listener.setWsObservable(binaryPresenter.getObservableEmitter());

    }
}
