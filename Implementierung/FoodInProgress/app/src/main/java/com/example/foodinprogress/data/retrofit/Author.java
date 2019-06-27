package com.example.foodinprogress.data.retrofit;

import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("id")
    private String mId;

    @SerializedName("name")
    private String mName;

    public Author(String id, String name){
        mId = id;
        mName = name;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
