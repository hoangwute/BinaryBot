package com.wuochoang.binarybot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangNQ on 24,July,2018
 */
public class BalanceReceived {

    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("loginid")
    @Expose
    private String loginid;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

}

