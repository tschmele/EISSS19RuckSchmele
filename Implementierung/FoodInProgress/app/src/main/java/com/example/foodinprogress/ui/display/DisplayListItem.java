package com.example.foodinprogress.ui.display;

public class DisplayListItem {

    private String title;
    private int imageURL;

    public DisplayListItem(String title, int imageURL) {
        this.title = title;
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageURL() {
        return imageURL;
    }

    public void setImageURL(int imageURL) {
        this.imageURL = imageURL;
    }
}
