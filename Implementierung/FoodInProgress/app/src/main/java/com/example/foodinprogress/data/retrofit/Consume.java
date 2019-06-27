package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.SerializedName;

class Consume {

    @SerializedName("datum")
    private String date;

    @SerializedName("mhd")
    private Boolean mhd;

    public Consume(String date, Boolean mhd) {
        this.date = date;
        this.mhd = mhd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getMhd() {
        return mhd;
    }

    public void setMhd(Boolean mhd) {
        this.mhd = mhd;
    }
}
