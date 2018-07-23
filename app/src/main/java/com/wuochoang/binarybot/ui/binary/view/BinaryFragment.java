package com.wuochoang.binarybot.ui.binary.view;

import android.widget.Button;
import android.widget.TextView;

import com.olddog.common.ToastUtils;
import com.wuochoang.binarybot.R;
import com.wuochoang.binarybot.base.BaseFragment;
import com.wuochoang.binarybot.base.BasePresenter;
import com.wuochoang.binarybot.ui.binary.presenter.BinaryPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

/**
 * Created by HoangNQ on 23,July,2018
 */
public class BinaryFragment extends BaseFragment implements BinaryView {

    @BindView(R.id.txtResponse)
    TextView txtResponse;
    @BindView(R.id.btnWebSocket)
    Button btnWebSocket;

    private OkHttpClient client;
    private Request request;

    BinaryPresenter binaryPresenter = new BinaryPresenter();


    @OnClick(R.id.btnWebSocket)
    public void call() {
        EchoWebSocketListener listener = EchoWebSocketListener.getInstance("{\n" +
                "  \"ping\": 1\n" +
                "}", binaryPresenter);
        WebSocket ws = client.newWebSocket(request, listener);
    }

    @OnClick(R.id.btnBalance)
    public void getBalance() {
        EchoWebSocketListener listener = EchoWebSocketListener.getInstance("5000USD", binaryPresenter);
        WebSocket ws = client.newWebSocket(request, listener);
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
        client = new OkHttpClient();
        request = new Request.Builder().url("wss://ws.binaryws.com/websockets/v3?app_id=0000").build();
    }

    @Override
    public void initData() {

    }

    @Override
    public void getAccountBalance(String balance) {
        ToastUtils.show("Main from balance is " + balance);

    }
}
