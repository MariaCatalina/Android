package com.example.cata.tabs.imageviewpager;

/**
 * Created by cata on 14.03.2017.
 */

public class DataModel {
    private int imageId;
    private String title;

    public DataModel(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }
}
