
package com.example.foodinprogress.data.retrofit;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("anzeigen")
    @Expose
    private List<Anzeigen> anzeigen = null;

    public List<Anzeigen> getAnzeigen() {
        return anzeigen;
    }

    public void setAnzeigen(List<Anzeigen> anzeigen) {
        this.anzeigen = anzeigen;
    }

}
