package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("Location")
    private Double[][] Location;

    public Location(Double[][] location) {
        Location = location;
    }

    public Double[][] getLocation() {
        return Location;
    }

    public void setLocation(Double[][] location) {
        Location = location;
    }
}
