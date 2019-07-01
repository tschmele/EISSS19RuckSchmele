package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("beschreibung")
    private String description;

    @SerializedName("bewertung")
    private int rating;

    @SerializedName("standort")
    private Location location;

    @SerializedName("tags")
    private String[] tags;

    @SerializedName("reservierungen")
    private Reservation reservation;

    @SerializedName("lager")
    private Room[] rooms;


}
