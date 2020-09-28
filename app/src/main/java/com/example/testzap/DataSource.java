package com.example.testzap;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    List<DataModel> list = new ArrayList<>();
    DataModel model;

    public DataSource() {
        model = new DataModel(R.drawable.science, "Science & Nature","#808AFF");
        list.add(model);
        model = new DataModel(R.drawable.gk, "General Knowledge","#FA6E5A");
        list.add(model);
        model = new DataModel(R.drawable.computerscience, "Computer Science","#FEB18F");
        list.add(model);
        model = new DataModel(R.drawable.history, "History","#FFCF86");
        list.add(model);
        model = new DataModel(R.drawable.geography, "Geography","#6CB28E");
        list.add(model);
        model = new DataModel(R.drawable.maths, "Mathematics","#3F414E");
        list.add(model);
        model = new DataModel(R.drawable.politics, "Politics","#7583CA");
        list.add(model);
        model = new DataModel(R.drawable.sports, "Sports","#D78CF1");
        list.add(model);
    }
}