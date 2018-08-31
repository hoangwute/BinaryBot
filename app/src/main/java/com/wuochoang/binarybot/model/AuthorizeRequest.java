package com.wuochoang.binarybot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangNQ on 30,August,2018
 */
public class AuthorizeRequest {

    @SerializedName("authorize")
    @Expose
    private String authorize;

    public AuthorizeRequest(String authorize) {
        this.authorize = authorize;
    }

    public AuthorizeRequest() {
    }

    public String getAuthorize() {
        return authorize;
    }

    public void setAuthorize(String authorize) {
        this.authorize = authorize;
    }
}
