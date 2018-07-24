package com.wuochoang.binarybot.model;

/**
 * Created by HoangNQ on 24,July,2018
 */
public class BalanceRequest {

    private int balance;
    private int subscribe;

    public BalanceRequest(int balance, int subscribe) {
        this.balance = balance;
        this.subscribe = subscribe;
    }

    public BalanceRequest() {
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }
}
