package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("name")
    private String name;

    @SerializedName("tags")
    private String[] tags;

    @SerializedName("inhalt")
    private RoomContent[] roomContent;

}
