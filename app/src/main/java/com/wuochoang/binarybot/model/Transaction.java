package com.wuochoang.binarybot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangNQ on 31,August,2018
 */
public class Transaction {

    @SerializedName("action_type")
    @Expose
    private String actionType;
    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("app_id")
    @Expose
    private int appId;
    @SerializedName("balance_after")
    @Expose
    private double balanceAfter;
    @SerializedName("contract_id")
    @Expose
    private long contractId;
    @SerializedName("longCode")
    @Expose
    private String longCode;
    @SerializedName("payout")
    @Expose
    private double payout;
    @SerializedName("purchase_time")
    @Expose
    private int purchaseTime;
    @SerializedName("reference_id")
    @Expose
    private long referenceId;
    @SerializedName("shortcode")
    @Expose
    private String shortCode;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("transaction_time")
    @Expose
    private long transactionTime;
    @SerializedName("buy_price")
    @Expose
    private String buyPrice;
    @SerializedName("sell_price")
    @Expose
    private String sellPrice;

    public Transaction (String actionType, double amount, int appId, double balanceAfter, long contractId, String longCode, double payout, int purchaseTime, int referenceId, String shortCode, String transactionId, long transactionTime) {
        this.actionType = actionType;
        this.amount = amount;
        this.appId = appId;
        this.balanceAfter = balanceAfter;
        this.contractId = contractId;
        this.longCode = longCode;
        this.payout = payout;
        this.purchaseTime = purchaseTime;
        this.referenceId = referenceId;
        this.shortCode = shortCode;
        this.transactionId = transactionId;
        this.transactionTime = transactionTime;
    }

    public Transaction () {

    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public String getLongCode() {
        return longCode;
    }

    public void setLongCode(String longCode) {
        this.longCode = longCode;
    }

    public double getPayout() {
        return payout;
    }

    public void setPayout(double payout) {
        this.payout = payout;
    }

    public int getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(int purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(long referenceId) {
        this.referenceId = referenceId;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(long transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }
}
