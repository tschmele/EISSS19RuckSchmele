package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("autor")
    private Author author;

    @SerializedName("kommentar")
    private String comment;

    @SerializedName("wertung")
    private double rating;
}
