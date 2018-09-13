package com.wuochoang.binarybot.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HoangNQ on 05,September,2018
 */
public class ProfitTable {

    @SerializedName("count")
    private int count;
    @SerializedName("transactions")
    private List<Transaction> transactionList;

    public ProfitTable(int count, List<Transaction> transactionList) {
        this.count = count;
        this.transactionList = transactionList;
    }

    public ProfitTable() {

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
