package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.SerializedName;

class Consume {

    @SerializedName("_seconds")
    private long second;

    @SerializedName("_nanoseconds")
    private long nanosecond;

    @SerializedName("mhd")
    private Boolean mhd;

    public Consume(int day, int month, int year, Boolean mhd) {

        int dayInSeconds   = day * 86400;
        int monthInSeconds = month * 2629800;
        int diffYear = year - 1970;
        int yearInSeconds  = diffYear * 31557600;

        this.second = dayInSeconds + monthInSeconds + yearInSeconds;
        this.mhd = mhd;
    }

    public void setSecond(long second) {
        this.second = second;
    }

    public void setNanosecond(long nanosecond) {
        this.nanosecond = nanosecond;
    }

    public Boolean getMhd() {
        return mhd;
    }

    public void setMhd(Boolean mhd) {
        this.mhd = mhd;
    }
}
