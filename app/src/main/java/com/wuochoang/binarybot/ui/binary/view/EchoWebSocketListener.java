package com.wuochoang.binarybot.ui.binary.view;

import android.annotation.SuppressLint;

import com.olddog.common.ToastUtils;
import com.wuochoang.binarybot.ui.binary.presenter.BinaryPresenter;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by HoangNQ on 23,July,2018
 */

public class EchoWebSocketListener extends WebSocketListener {
    private static final int NORMAL_CLOSURE_STATUS = 1000;
    private String message;
    private BinaryPresenter presenter;

    public static EchoWebSocketListener getInstance(String text, BinaryPresenter presenter) {
        EchoWebSocketListener echoWebSocketListener = new EchoWebSocketListener();
        echoWebSocketListener.message = text;
        echoWebSocketListener.presenter = presenter;
        return echoWebSocketListener;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        webSocket.send(message);
        webSocket.close(NORMAL_CLOSURE_STATUS, "Bye");
    }

    @SuppressLint("CheckResult")
    @Override
    public void onMessage(WebSocket webSocket, String text) {
        presenter.getBalance(text);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {

    }

}

