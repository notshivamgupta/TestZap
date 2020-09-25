package com.example.testzap;

public class DataModel {
    private int img;
    private String title;

    public DataModel() {
    }

    public DataModel(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }
}
