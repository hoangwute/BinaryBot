package com.wuochoang.binarybot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangNQ on 07,August,2018
 */
public class WebSocketResult {
    @SerializedName("proposal")
    private Proposal proposal;

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }
}
