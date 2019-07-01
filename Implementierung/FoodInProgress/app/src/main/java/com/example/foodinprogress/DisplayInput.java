package com.example.foodinprogress;

public class DisplayInput {


    private String id;
    private Boolean request;
    private String title;
    private String decription;
    private Double weight;


    private int day;
    private int month;
    private int year;

    private long second;
    private String mobility;
    private String[] tags;
    private String amount;


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSecond(int day, int month, int year){

        int dayInSeconds   = day * 86400;
        int monthInSeconds = month * 2629800;
        int diffYear = year - 1970;
        int yearInSeconds  = diffYear * 31557600;

        this.second = dayInSeconds + monthInSeconds + yearInSeconds;
    }

    public void setMobility(String mobility) {
        this.mobility = mobility;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
