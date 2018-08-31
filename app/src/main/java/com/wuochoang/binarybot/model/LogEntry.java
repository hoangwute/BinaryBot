package com.wuochoang.binarybot.model;

/**
 * Created by HoangNQ on 29,August,2018
 */
public class LogEntry {

    private String text;
    private boolean success;

    public LogEntry(String text, boolean success) {
        this.text = text;
        this.success = success;
    }

    public LogEntry() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
