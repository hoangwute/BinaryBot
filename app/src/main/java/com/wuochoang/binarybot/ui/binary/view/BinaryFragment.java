package com.wuochoang.binarybot.ui.binary.view;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.olddog.common.ToastUtils;
import com.wuochoang.binarybot.NLServicee;
import com.wuochoang.binarybot.R;
import com.wuochoang.binarybot.base.BaseFragment;
import com.wuochoang.binarybot.base.BasePresenter;
import com.wuochoang.binarybot.common.Constant;
import com.wuochoang.binarybot.common.utils.Utils;
import com.wuochoang.binarybot.manager.StorageManager;
import com.wuochoang.binarybot.model.AuthorizeRequest;
import com.wuochoang.binarybot.model.Balance;
import com.wuochoang.binarybot.model.BalanceRequest;
import com.wuochoang.binarybot.model.Contract;
import com.wuochoang.binarybot.model.LogEntry;
import com.wuochoang.binarybot.model.ProfitTableRequest;
import com.wuochoang.binarybot.model.ProfitTableResponse;
import com.wuochoang.binarybot.model.ProposalRequest;
import com.wuochoang.binarybot.model.SocketResponse;
import com.wuochoang.binarybot.model.StatementResponse;
import com.wuochoang.binarybot.model.Transaction;
import com.wuochoang.binarybot.model.WebSocketResult;
import com.wuochoang.binarybot.ui.binary.adapter.ActivityLogAdapter;
import com.wuochoang.binarybot.ui.binary.dialog.AccountTypeDialog;
import com.wuochoang.binarybot.ui.binary.presenter.BinaryPresenter;
import com.wuochoang.binarybot.ui.devices.DevicesFragment;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by HoangNQ on 23,July,2018
 */
public class BinaryFragment extends BaseFragment {

    @Inject
    StorageManager storageManager;

    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.rvActivityLog)
    RecyclerView rvActivityLog;
    @BindView(R.id.txtPortNumber)
    EditText txtPortNumber;
    @BindView(R.id.txtDuration)
    EditText txtDuration;
    @BindView(R.id.txtPurchasePrice)
    EditText txtPurchasePrice;
    @BindView(R.id.loadingLl)
    LinearLayout loadingLl;
    @BindView(R.id.txtAccountType)
    TextView txtAccountType;

    private OkHttpClient client;
    private Request request;
    private WebSocket ws;

    private Socket socket;
    private Gson gson;
    private ActivityLogAdapter activityLogAdapter;
    private List<LogEntry> logEntries = new ArrayList<>();
    private Date trackingDate;
//    private ProgressDialog progressDialog;

    BinaryPresenter binaryPresenter = new BinaryPresenter();
    private EchoWebSocketListener listener;

    private NotificationReceiver nReceiver = new NotificationReceiver();



    @SuppressLint("CheckResult")
    @OnClick(R.id.btnStartTrading)
    public void requestServer() {
        if (!txtPortNumber.getText().toString().isEmpty() && !txtDuration.getText().toString().isEmpty()
                && !txtPurchasePrice.getText().toString().isEmpty()) {
            loadingLl.setVisibility(View.VISIBLE);
            Observable<String> getResponseFromServer = Observable.create(e -> {
                socket = new Socket("10.0.3.2", Integer.parseInt(txtPortNumber.getText().toString()));
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (socket.isConnected()) {
                    String s = input.readLine();
                    e.onNext(s);
                }
            });
            getResponseFromServer.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        ToastUtils.show(s);
                        Log.d("SocketResponse", s);
                        getProposal(txtPurchasePrice.getText().toString(), Utils.getContractType(s), txtDuration.getText().toString(), Constant.CONTRACT_DURATION_UNIT_MINUTE, Utils.getCurrencyPair(s));
                    }, throwable -> {
                        ToastUtils.show("Error: " + throwable.getMessage());
                    });
        }
        else {
            ToastUtils.show("Please fulfill all contract information before proceeding");
        }
    }


    @OnClick(R.id.btnViewBalance)
    public void getBalance() {

//        getProposal("1", "CALL", txtDuration.getText().toString(), Constant.CONTRACT_DURATION_UNIT_MINUTE, "frxUSDJPY");

//        progressDialog.show();
        getBalanceAccount();
//        long secondsInMilli = 1000;
//        long minutesInMilli = secondsInMilli * 60;
//        long hoursInMilli = minutesInMilli * 60;
//        long daysInMilli = hoursInMilli * 24;

//        Date date = new Date();
//        Log.d("Date", "Date difference in long:" + (date.getTime() - trackingDate.getTime()));

    }

    @Override
    public void injectDependence() {
        component.inject(this);
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
        activityLogAdapter = new ActivityLogAdapter(logEntries);
        rvActivityLog.setAdapter(activityLogAdapter);
        rvActivityLog.setLayoutManager(new LinearLayoutManager(getContext()));
//        progressDialog = Utils.showLoadingDialog(getContext());
    }

    @Override
    public void initData() {
        setUpAccountInfo(storageManager.getBooleanValue(Constant.isVirtual, true));
        gson = new Gson();
//        nReceiver = new NotificationReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(Constant.INTENT);
//        getContext().registerReceiver(nReceiver, filter);

    }

    @SuppressLint("CheckResult")
    private void getProposal(String amount, String contractType, String duration, String durationUnit, String symbol) {
        listener = new EchoWebSocketListener();
        client = new OkHttpClient();
        request = new Request.Builder().url("wss://ws.binaryws.com/websockets/v3?app_id=13114").build();
        ws = client.newWebSocket(request, listener);
        ProposalRequest proposal = new ProposalRequest(1, amount, Constant.BASIS_STAKE, contractType, "USD", duration, durationUnit, symbol);
        ws.send(Utils.objectToJson(proposal));
        Observable<String> proposalObservable = Observable.create(e -> listener.setWsObservable(e));
        proposalObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(proposalJson -> {
                    Log.d("Procedure", "Proposal Ready");
                    AuthorizeRequest request = new AuthorizeRequest();
                    if (storageManager.getBooleanValue(Constant.isVirtual, true))
                        request.setAuthorize(Constant.API_TOKEN_VIRTUAL);
                    else
                        request.setAuthorize(Constant.API_TOKEN_REAL);

                    ws.send(Utils.objectToJson(request));
                    Observable<String> auObservable = Observable.create(e -> listener.setWsObservable(e));
                    auObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(s -> {
                                WebSocketResult returnedPropsal = gson.fromJson(proposalJson, WebSocketResult.class);
                                Log.d("Procedure", "Authorized");
                                Contract contract = new Contract(returnedPropsal.getProposal().getId(), Integer.parseInt(amount));
                                ws.send(Utils.objectToJson(contract));
                                Observable<String> buyContractObservable = Observable.create(e -> listener.setWsObservable(e));
                                buyContractObservable.subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(contractJson -> {
                                            Log.d("Procedure", "Contract brought");
                                            activityLogAdapter.notifyDataSetChanged();
                                            ToastUtils.show("Contract has been successfully brought");
                                            new Handler().postDelayed(() -> { //update log after 5 minutes
                                                Log.d("Procedure", "5 mins after");
                                                getProfitTableAccount();
                                            }, 300000);

                                        });
                            }, throwable -> {
                                LogEntry failLogEntry = new LogEntry();
                                failLogEntry.setPair(throwable.getMessage());
                                failLogEntry.setSuccess(false);
                                logEntries.add(failLogEntry);
                                activityLogAdapter.notifyDataSetChanged();
                                ToastUtils.show(throwable.getMessage());
                            });
                });
    }

    @SuppressLint("CheckResult")
    public void getProfitTableAccount() {
        Log.d("Procedure", "Goes here");
        listener = new EchoWebSocketListener();
        client = new OkHttpClient();
        request = new Request.Builder().url("wss://ws.binaryws.com/websockets/v3?app_id=13114").build();
        ws = client.newWebSocket(request, listener);

        AuthorizeRequest request = new AuthorizeRequest();
        if (storageManager.getBooleanValue(Constant.isVirtual, true))
            request.setAuthorize(Constant.API_TOKEN_VIRTUAL);
        else
            request.setAuthorize(Constant.API_TOKEN_REAL);

        ws.send(Utils.objectToJson(request));
        Observable<String> auObservable = Observable.create(e -> listener.setWsObservable(e));
        auObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Observable<String> profitTableObservable = Observable.create(e -> listener.setWsObservable(e));
                    ws.send(Utils.objectToJson(new ProfitTableRequest(1, 1, "DESC")));
                    profitTableObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(success -> {
                                Log.d("Procedure", success);
                                Gson gson = new Gson();
                                List<Transaction> transactionList = gson.fromJson(success, ProfitTableResponse.class).getProfitTable().getTransactionList();
                                String pair = Utils.getCurrencyPair(transactionList.get(0).getShortCode());
                                Log.d("Procedure", pair);
                                if (Double.parseDouble(transactionList.get(0).getSellPrice()) > 0) {
                                    logEntries.add(new LogEntry(pair, transactionList.get(0).getActionType(), "ITM", true));
                                }
                                else
                                    logEntries.add(new LogEntry(pair, transactionList.get(0).getActionType(), "OTM", true));
                                activityLogAdapter.notifyDataSetChanged();
                                ws.close(1000, null);
//                                progressDialog.dismiss();
                            }, throwable -> {
                                Log.d("Procedure", throwable.getMessage());
                                ToastUtils.show(throwable.getMessage());
                                ws.close(1000, null);
//                                progressDialog.dismiss();
                            });
                });
    }

    @SuppressLint("CheckResult")
    private void getBalanceAccount() {
        listener = new EchoWebSocketListener();
        client = new OkHttpClient();
        request = new Request.Builder().url("wss://ws.binaryws.com/websockets/v3?app_id=13114").build();
        ws = client.newWebSocket(request, listener);

        AuthorizeRequest request = new AuthorizeRequest();
        if (storageManager.getBooleanValue(Constant.isVirtual, true))
            request.setAuthorize(Constant.API_TOKEN_VIRTUAL);
        else
            request.setAuthorize(Constant.API_TOKEN_REAL);

        ws.send(Utils.objectToJson(request));
        Observable<String> auObservable = Observable.create(e -> listener.setWsObservable(e));
        auObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Observable<String> balanceObservable = Observable.create(e -> listener.setWsObservable(e));
                    ws.send(Utils.objectToJson(new BalanceRequest(1, 1)));
                    balanceObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(success -> {
                                Gson gson = new Gson();
                                Balance balance = gson.fromJson(success, SocketResponse.class).getBalance();
                                txtPrice.setText(String.format("$ %s", balance.getBalance()));
                                ws.close(1000, null);
//                                progressDialog.dismiss();
                                storageManager.setDoubleValue(Constant.ACCOUNT_BALANCE, Double.parseDouble(balance.getBalance()));
                            }, throwable -> {
                                ToastUtils.show(throwable.getMessage());
                                ws.close(1000, null);
//                                progressDialog.dismiss();
                            });
                });
    }

    @OnClick({R.id.accountTypeLl, R.id.imgAccountType})
    public void switchAccountType() {
        AccountTypeDialog dialog = AccountTypeDialog.getInstance(storageManager.getBooleanValue(Constant.isVirtual, true), isVirtual -> {
            storageManager.setBooleanValue(Constant.isVirtual, isVirtual);
            setUpAccountInfo(storageManager.getBooleanValue(Constant.isVirtual, true));
        });
        dialog.show(getActivity().getSupportFragmentManager(), "AccountTypeDialog");
    }

    public void setUpAccountInfo(boolean isVirtual) {
        if (isVirtual)
            txtAccountType.setText("Virtual");
        else
            txtAccountType.setText("Real");
//        getBalanceAccount();
    }

    @Override
    public void onResume() {
        super.onResume();
        nReceiver = new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.INTENT);
        getContext().registerReceiver(nReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(nReceiver);
    }

    public class NotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            String notificationBody =
                    extras.getString(Notification.EXTRA_TEXT);
            Log.d("NotificationListener", notificationBody);
            ToastUtils.show(notificationBody);
            if (notificationBody.contains("Valid Signal"))
                getProposal(txtPurchasePrice.getText().toString(), Utils.getContractType(notificationBody), txtDuration.getText().toString(), Constant.CONTRACT_DURATION_UNIT_MINUTE, Utils.getCurrencyPair(notificationBody));

        }
    }

}
