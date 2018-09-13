package com.wuochoang.binarybot.model;

/**
 * Created by HoangNQ on 29,August,2018
 */
public class LogEntry {

    private String pair;
    private boolean success;
    private String action;
    private String result;


    public LogEntry() {

    }

    public LogEntry(String pair, String action, String result, boolean success) {
        this.pair = pair;
        this.action = action;
        this.result = result;
        this.success = success;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
