package com.wuochoang.binarybot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangNQ on 05,September,2018
 */
public class ProfitTableRequest {

    @SerializedName("profit_table")
    @Expose
    private int statement;
    @SerializedName("description")
    @Expose
    private int description;
    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("sort")
    @Expose
    private String sort;

    public ProfitTableRequest(int statement, int description, int limit, String sort) {
        this.statement = statement;
        this.description = description;
        this.limit = limit;
        this.sort = sort;
    }

    public ProfitTableRequest(int statement, int description, String sort) {
        this.statement = statement;
        this.description = description;
        this.sort = sort;
    }

    public ProfitTableRequest() {

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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
