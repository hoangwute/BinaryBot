package com.wuochoang.binarybot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangNQ on 05,September,2018
 */
public class ProfitTableResponse {

    @SerializedName("msg_type")
    @Expose
    private String msgType;

    @SerializedName("profit_table")
    private ProfitTable profitTable;

    public ProfitTableResponse() {

    }

    public ProfitTableResponse(String msgType, ProfitTable profitTable) {
        this.msgType = msgType;
        this.profitTable = profitTable;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public ProfitTable getProfitTable() {
        return profitTable;
    }

    public void setProfitTable(ProfitTable profitTable) {
        this.profitTable = profitTable;
    }
}
