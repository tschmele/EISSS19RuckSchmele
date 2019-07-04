package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.SerializedName;

public class DisplayPost {

    @SerializedName("id")
    private String id;

    @SerializedName("data")
    DisplayData data;


    public void setId(String id) {
        this.id = id;
    }

    public DisplayData getData() {
        return data;
    }

    public void setData(DisplayData data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }


}
