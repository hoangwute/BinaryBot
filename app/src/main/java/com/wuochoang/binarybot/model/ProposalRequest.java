package com.wuochoang.binarybot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangNQ on 07,August,2018
 */
public class ProposalRequest {

    @SerializedName("proposal")
    private Integer proposal;
    @SerializedName("amount")
    private String amount;
    @SerializedName("basis")
    private String basis;
    @SerializedName("contract_type")
    private String contractType;
    @SerializedName("currency")
    private String currency;
    @SerializedName("duration")
    private String duration;
    @SerializedName("duration_unit")
    private String durationUnit;
    @SerializedName("symbol")
    private String symbol;

    public ProposalRequest(Integer proposal, String amount, String basis, String contractType, String currency, String duration, String durationUnit, String symbol) {
        this.proposal = proposal;
        this.amount = amount;
        this.basis = basis;
        this.contractType = contractType;
        this.currency = currency;
        this.duration = duration;
        this.durationUnit = durationUnit;
        this.symbol = symbol;
    }

    public ProposalRequest() {
    }

    public Integer getProposal() {
        return proposal;
    }

    public void setProposal(Integer proposal) {
        this.proposal = proposal;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDurationUnit() {
        return durationUnit;
    }

    public void setDurationUnit(String durationUnit) {
        this.durationUnit = durationUnit;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
