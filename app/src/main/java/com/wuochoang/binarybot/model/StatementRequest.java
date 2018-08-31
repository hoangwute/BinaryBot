package com.wuochoang.binarybot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangNQ on 31,August,2018
 */
public class StatementRequest {

    @SerializedName("statement")
    @Expose
    private int statement;
    @SerializedName("description")
    @Expose
    private int description;
    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("offset")
    @Expose
    private int offset;
    @SerializedName("date_from")
    @Expose
    private long dateFrom;
    @SerializedName("date_to")
    @Expose
    private long dateTo;
    @SerializedName("action_type")
    @Expose
    private String actionType;

    public StatementRequest(int statement, int description, int limit, int offset, long date_from, long date_to, String actionType) {
        this.statement = statement;
        this.description = description;
        this.limit = limit;
        this.offset = offset;
        this.dateFrom = date_from;
        this.dateTo = date_to;
        this.actionType = actionType;
    }

    public StatementRequest(int statement, int description, int limit, long date_from, long date_to, String actionType) {
        this.statement = statement;
        this.description = description;
        this.limit = limit;
        this.dateFrom = date_from;
        this.dateTo = date_to;
        this.actionType = actionType;
    }

    public StatementRequest(int statement, int description, int limit, String actionType) {
        this.statement = statement;
        this.description = description;
        this.limit = limit;
        this.actionType = actionType;
    }

    public StatementRequest(int statement, int description, int limit) {
        this.statement = statement;
        this.description = description;
        this.limit = limit;
    }

    public StatementRequest() {

    }

    public int getStatement() {
        return statement;
    }

    public void setStatement(int statement) {
        this.statement = statement;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(long dateFrom) {
        this.dateFrom = dateFrom;
    }

    public long getDateTo() {
        return dateTo;
    }

    public void setDateTo(long dateTo) {
        this.dateTo = dateTo;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}
