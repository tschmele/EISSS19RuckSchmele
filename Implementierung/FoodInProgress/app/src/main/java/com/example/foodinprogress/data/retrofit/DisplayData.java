package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DisplayData {

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


    public Boolean getRequest() {
        return request;
    }

    public void setRequest(Boolean request) {
        this.request = request;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getReserve() {
        return reserve;
    }

    public void setReserve(Boolean reserve) {
        this.reserve = reserve;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Consume getConsume() {
        return consume;
    }

    public void setConsume(Consume consume) {
        this.consume = consume;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
