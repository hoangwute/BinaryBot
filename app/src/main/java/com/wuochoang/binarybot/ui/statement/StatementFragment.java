package com.wuochoang.binarybot.ui.statement;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.olddog.common.ToastUtils;
import com.wuochoang.binarybot.R;
import com.wuochoang.binarybot.base.BaseFragment;
import com.wuochoang.binarybot.base.BasePresenter;
import com.wuochoang.binarybot.common.Constant;
import com.wuochoang.binarybot.common.utils.Utils;
import com.wuochoang.binarybot.manager.StorageManager;
import com.wuochoang.binarybot.model.AuthorizeRequest;
import com.wuochoang.binarybot.model.Balance;
import com.wuochoang.binarybot.model.ProposalRequest;
import com.wuochoang.binarybot.model.SocketResponse;
import com.wuochoang.binarybot.model.StatementRequest;
import com.wuochoang.binarybot.model.StatementResponse;
import com.wuochoang.binarybot.model.Transaction;
import com.wuochoang.binarybot.ui.binary.view.EchoWebSocketListener;
import com.wuochoang.binarybot.ui.statement.adapter.StatementAdapter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

/**
 * Created by HoangNQ on 30,August,2018
 */
public class StatementFragment extends BaseFragment {

    @Inject
    StorageManager storageManager;

    @BindView(R.id.txtDateFrom)
    TextView txtDateFrom;
    @BindView(R.id.txtDateTo)
    TextView txtDateTo;
    @BindView(R.id.rvTransaction)
    RecyclerView rvTransaction;
    @BindView(R.id.resultLl)
    ConstraintLayout resultLl;
    @BindView(R.id.txtTotalTrades)
    TextView txtTotalTrades;
    @BindView(R.id.txtTotalItm)
    TextView txtTotalItm;
    @BindView(R.id.txtWinRatio)
    TextView txtWinRatio;
    @BindView(R.id.txtAccountType)
    TextView txtAccountType;

    private OkHttpClient client;
    private Request request;
    private WebSocket ws;
    private Gson gson;
    private EchoWebSocketListener listener;
    private StatementAdapter statementAdapter;
    private List<Transaction> transactionList = new ArrayList<>();

    private Calendar dateFromCalendar, dateToCalendar;

    @Override
    public void injectDependence() {
        component.inject(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_statement;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void initView() {
        statementAdapter = new StatementAdapter(transactionList);
        rvTransaction.setAdapter(statementAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvTransaction.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initData() {
        if (storageManager.getBooleanValue(Constant.isVirtual, true))
            txtAccountType.setText("Virtual Account");
        else
            txtAccountType.setText("Real Account");

        gson = new Gson();
        dateFromCalendar = Calendar.getInstance();
        dateToCalendar = Calendar.getInstance();
        dateFromCalendar.setTime(new Date());
        dateToCalendar.setTime(new Date());
        String checkInDate = new SimpleDateFormat("MMMM dd, EEEE", Locale.US).format(dateFromCalendar.getTime());
        String checkOutDate = new SimpleDateFormat("MMMM dd, EEEE", Locale.US).format(dateToCalendar.getTime());
        txtDateFrom.setText(checkInDate);
        txtDateTo.setText(checkOutDate);
    }

    @SuppressLint("CheckResult")
    @OnClick(R.id.btnViewTransaction)
    public void viewTransaction() {
        listener = new EchoWebSocketListener();
        client = new OkHttpClient();
        request = new Request.Builder().url("wss://ws.binaryws.com/websockets/v3?app_id=13114").build();
        ws = client.newWebSocket(request, listener);
        ProgressDialog progressDialog = Utils.showLoadingDialog(getContext());
        progressDialog.show();

        AuthorizeRequest authorizationRequest = new AuthorizeRequest();
        if (storageManager.getBooleanValue(Constant.isVirtual, true))
            authorizationRequest.setAuthorize(Constant.API_TOKEN_VIRTUAL);
        else
            authorizationRequest.setAuthorize(Constant.API_TOKEN_REAL);

        ws.send(Utils.objectToJson(authorizationRequest));
        Observable<String> auObservable = Observable.create(e -> listener.setWsObservable(e));
        auObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    StatementRequest statementRequest = new StatementRequest(1, 1, 500,
                            Math.round(dateFromCalendar.getTime().getTime() / 1000), Math.round(dateToCalendar.getTime().getTime() / 1000), "sell");
                    ws.send(Utils.objectToJson(statementRequest));
                    Observable<String> statementObservable = Observable.create(e -> listener.setWsObservable(e));

                    statementObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(statementJson -> {
                                Log.d("Statement", statementJson);
                                transactionList = gson.fromJson(statementJson, StatementResponse.class).getStatement().getTransactionList();
                                statementAdapter.updateTransactionList(transactionList);
                                resultLl.setVisibility(View.VISIBLE);
                                txtTotalTrades.setText(String.valueOf(transactionList.size()));
                                int itm = 0;
                                for (Transaction transaction : transactionList) {
                                    if (transaction.getAmount() > 0) {
                                        itm++;
                                    }
                                }
                                txtTotalItm.setText(String.valueOf(itm));
                                txtTotalItm.setTextColor(getContext().getResources().getColor(R.color.green));
                                txtWinRatio.setText(String.format("%s%%", (double) (itm * 100) / transactionList.size()));
                                progressDialog.dismiss();
                            }, throwable -> {
                                ToastUtils.show(throwable.getMessage());
                                progressDialog.dismiss();
                            });
                }, throwable -> {

                });
    }

    @OnClick(R.id.txtDateFrom)
    public void choseDateFrom() {
        DatePickerDialog dateToDatePicker = new DatePickerDialog(getActivity(), (view, year, monthOfYear, dayOfMonth) -> {
            dateFromCalendar.set(Calendar.YEAR, year);
            dateFromCalendar.set(Calendar.MONTH, monthOfYear);
            dateFromCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dateFromCalendar.set(Calendar.HOUR_OF_DAY, dateFromCalendar.getMinimum(Calendar.HOUR_OF_DAY));
            dateFromCalendar.set(Calendar.MINUTE, dateFromCalendar.getMinimum(Calendar.MINUTE));
            dateFromCalendar.set(Calendar.SECOND, dateFromCalendar.getMinimum(Calendar.SECOND));
            dateFromCalendar.set(Calendar.MILLISECOND, dateFromCalendar.getMinimum(Calendar.MILLISECOND));
            txtDateFrom.setText(new SimpleDateFormat("MMMM dd, EEEE", Locale.US).format(dateFromCalendar.getTime()));
            Log.d("epoch", dateFromCalendar.getTime().getTime() + "");
        }, dateFromCalendar.get(Calendar.YEAR), dateFromCalendar.get(Calendar.MONTH), dateFromCalendar.get(Calendar.DAY_OF_MONTH));
        dateToDatePicker.getDatePicker().setMaxDate(new Date().getTime());
        dateToDatePicker.show();
    }

    @OnClick(R.id.txtDateTo)
    public void choseDateTo() {
        DatePickerDialog dateFromDatePicker = new DatePickerDialog(getActivity(), (view, year, monthOfYear, dayOfMonth) -> {
            dateToCalendar.set(Calendar.YEAR, year);
            dateToCalendar.set(Calendar.MONTH, monthOfYear);
            dateToCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dateToCalendar.set(Calendar.HOUR_OF_DAY, dateToCalendar.getMaximum(Calendar.HOUR_OF_DAY));
            dateToCalendar.set(Calendar.MINUTE, dateToCalendar.getMaximum(Calendar.MINUTE));
            dateToCalendar.set(Calendar.SECOND, dateToCalendar.getMaximum(Calendar.SECOND));
            dateToCalendar.set(Calendar.MILLISECOND, dateToCalendar.getMaximum(Calendar.MILLISECOND));
            txtDateTo.setText(new SimpleDateFormat("MMMM dd, EEEE", Locale.US).format(dateToCalendar.getTime()));
            Log.d("epoch", dateToCalendar.getTime().getTime() + "");
        }, dateToCalendar.get(Calendar.YEAR), dateToCalendar.get(Calendar.MONTH), dateToCalendar.get(Calendar.DAY_OF_MONTH));
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(dateFromCalendar.getTime());
        dateFromDatePicker.getDatePicker().setMinDate(tempCalendar.getTime().getTime());
        dateFromDatePicker.show();

    }

}
