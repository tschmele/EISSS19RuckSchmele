
package com.example.foodinprogress.dispalydata;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("standort")
    @Expose
    private Standort standort;
    @SerializedName("anfrage")
    @Expose
    private Boolean anfrage;
    @SerializedName("titel")
    @Expose
    private String titel;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("reserviert")
    @Expose
    private Boolean reserviert;
    @SerializedName("autor")
    @Expose
    private Autor autor;
    @SerializedName("beschreibung")
    @Expose
    private String beschreibung;
    @SerializedName("verbrauch")
    @Expose
    private Verbrauch verbrauch;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("gewicht")
    @Expose
    private Double gewicht;

    public Standort getStandort() {
        return standort;
    }

    public void setStandort(Standort standort) {
        this.standort = standort;
    }

    public Boolean getAnfrage() {
        return anfrage;
    }

    public void setAnfrage(Boolean anfrage) {
        this.anfrage = anfrage;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getReserviert() {
        return reserviert;
    }

    public void setReserviert(Boolean reserviert) {
        this.reserviert = reserviert;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Verbrauch getVerbrauch() {
        return verbrauch;
    }

    public void setVerbrauch(Verbrauch verbrauch) {
        this.verbrauch = verbrauch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getGewicht() {
        return gewicht;
    }

    public void setGewicht(Double gewicht) {
        this.gewicht = gewicht;
    }

}
