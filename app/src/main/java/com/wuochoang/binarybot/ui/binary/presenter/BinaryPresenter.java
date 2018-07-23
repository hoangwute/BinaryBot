package com.wuochoang.binarybot.ui.binary.presenter;

import com.wuochoang.binarybot.base.BasePresenter;
import com.wuochoang.binarybot.ui.binary.view.BinaryView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by HoangNQ on 23,July,2018
 */
public class BinaryPresenter extends BasePresenter<BinaryView> {

    public void getBalance(String text) {

        Observable<String> balanceObservable = Observable.just(text);
        balanceObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        if (getView() != null) {
                            getView().getAccountBalance(s);
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
