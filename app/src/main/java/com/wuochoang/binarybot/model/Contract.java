package com.wuochoang.binarybot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangNQ on 07,August,2018
 */
public class Contract {

    @SerializedName("buy")
    private String proposalId;
    @SerializedName("price")
    private int price;

    public Contract(String proposalId, int price) {
        this.proposalId = proposalId;
        this.price = price;
    }

    public Contract() {
    }

    public String getProposalId() {
        return proposalId;
    }

    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
