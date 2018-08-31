package com.wuochoang.binarybot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangNQ on 07,August,2018
 */
public class Proposal {

    @SerializedName("ask_price")
    @Expose
    private String askPrice;
    @SerializedName("date_start")
    @Expose
    private String dateStart;
    @SerializedName("display_value")
    @Expose
    private String displayValue;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("longcode")
    @Expose
    private String longcode;
    @SerializedName("payout")
    @Expose
    private String payout;
    @SerializedName("spot")
    @Expose
    private String spot;
    @SerializedName("spot_time")
    @Expose
    private String spotTime;

    public String getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(String askPrice) {
        this.askPrice = askPrice;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongcode() {
        return longcode;
    }

    public void setLongcode(String longcode) {
        this.longcode = longcode;
    }

    public String getPayout() {
        return payout;
    }

    public void setPayout(String payout) {
        this.payout = payout;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public String getSpotTime() {
        return spotTime;
    }

    public void setSpotTime(String spotTime) {
        this.spotTime = spotTime;
    }
}
