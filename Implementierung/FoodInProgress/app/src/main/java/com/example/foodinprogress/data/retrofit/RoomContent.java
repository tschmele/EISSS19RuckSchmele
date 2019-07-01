package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.SerializedName;

public class RoomContent {

    @SerializedName("lebensmittel")
    private String groocery;

    @SerializedName("gewicht")
    private double weight;

    @SerializedName("status")
    private String status;

    @SerializedName("tags")
    private String[] tags;

    @SerializedName("verbrauch")
    private Consume consume;


}
