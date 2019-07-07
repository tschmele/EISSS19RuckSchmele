package com.example.foodinprogress.ui.display;

public class DisplayListItem {

    private String title;
    private String tags;
    private int imageURL;

    public DisplayListItem(String title, String tags, int imageURL) {
        this.title = title;
        this.tags = tags;
        this.imageURL = imageURL;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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
