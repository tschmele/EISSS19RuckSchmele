package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DisplayPost {

    @SerializedName("id")
    private String id;

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

    public void setId(String id) {
        this.id = id;
    }

    public void setRequest(Boolean request) {
        this.request = request;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReserve(Boolean reserve) {
        this.reserve = reserve;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setConsume(Consume consume) {
        this.consume = consume;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
