package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.SerializedName;

public class GenerallyPost {

    @SerializedName("status")
    private Integer status;

    @SerializedName("statusMessage")
    private String statusMessage;

    @SerializedName("results")
    private String results;

    public GenerallyPost(Integer status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}