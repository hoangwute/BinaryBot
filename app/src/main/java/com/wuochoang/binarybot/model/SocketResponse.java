package com.wuochoang.binarybot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangNQ on 24,July,2018
 */
public class SocketResponse {
    @SerializedName("balance")
    private Balance balance;

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}
