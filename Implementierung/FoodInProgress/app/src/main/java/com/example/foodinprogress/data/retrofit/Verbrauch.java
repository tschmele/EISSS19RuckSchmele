
package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Verbrauch {

    @SerializedName("mhd")
    @Expose
    private Boolean mhd;
    @SerializedName("datum")
    @Expose
    private Datum datum;

    public Boolean getMhd() {
        return mhd;
    }

    public void setMhd(Boolean mhd) {
        this.mhd = mhd;
    }

    public Datum getDatum() {
        return datum;
    }

    public void setDatum(Datum datum) {
        this.datum = datum;
    }

}
