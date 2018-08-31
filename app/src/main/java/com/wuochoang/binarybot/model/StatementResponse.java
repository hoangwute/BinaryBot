package com.wuochoang.binarybot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangNQ on 31,August,2018
 */
public class StatementResponse {

    @SerializedName("msg_type")
    @Expose
    private String msgType;

    @SerializedName("statement")
    private Statement statement;

    public StatementResponse() {

    }

    public StatementResponse(String msgType, Statement statement) {
        this.msgType = msgType;
        this.statement = statement;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
}
