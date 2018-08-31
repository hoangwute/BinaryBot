package com.wuochoang.binarybot.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HoangNQ on 31,August,2018
 */
public class Statement {

    @SerializedName("count")
    private int count;
    @SerializedName("transactions")
    private List<Transaction> transactionList;

    public Statement(int count, List<Transaction> transactionList) {
        this.count = count;
        this.transactionList = transactionList;
    }

    public Statement() {

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

}
