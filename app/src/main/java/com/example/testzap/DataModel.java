package com.example.testzap;

import android.graphics.Color;

public class DataModel {
    private int img;
    private String title;
    private String colour;



    public DataModel() {
    }

    public DataModel(int img, String title, String colour) {
        this.img = img;
        this.title = title;
        this.colour= colour;
    }


    public String getColour() {
        return colour;
    }
    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }
}
