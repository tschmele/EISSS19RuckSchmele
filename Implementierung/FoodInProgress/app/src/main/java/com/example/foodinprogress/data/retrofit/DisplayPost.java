package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DisplayPost {

    @SerializedName("anfrage")
    private Boolean request;

    @SerializedName("title")
    private String title;

    @SerializedName("beschreibung")
    private String decription;

    @SerializedName("gewicht")
    private Double weight;

    @SerializedName("status")
    private String status;

    @SerializedName("reserviert")
    private Boolean reserve;

    @SerializedName("tags")
    private List<String> tags = null;

    @SerializedName("standort")
    private Location location;

    @SerializedName("verbrauch")
    private Consume consume;

    @SerializedName("autor")
    private Author author;

    public DisplayPost(Boolean request, String title, String decription, Double weight, String status, Boolean reserve, List<String> tags, Location location, Consume consume, Author author) {
        this.request = request;
        this.title = title;
        this.decription = decription;
        this.weight = weight;
        this.status = status;
        this.reserve = reserve;
        this.tags = tags;
        this.location = location;
        this.consume = consume;
        this.author = author;
    }
}
