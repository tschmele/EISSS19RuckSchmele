package com.example.foodinprogress.dispalydata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Anzeigenliste {

    @SerializedName("anzeigen")
    @Expose
    private ArrayList<Example> examples = null;

    public ArrayList<Example> getExamples() {
        return examples;
    }

    public void setExamples(ArrayList<Example> examples) {
        this.examples = examples;
    }
}
