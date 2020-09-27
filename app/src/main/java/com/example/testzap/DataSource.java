package com.example.testzap;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    List<DataModel> list = new ArrayList<>();
    DataModel model;

    public DataSource() {
        model = new DataModel(R.drawable.science, "Physics","#808AFF");
        list.add(model);
        model = new DataModel(R.drawable.science, "Maths","#FA6E5A");
        list.add(model);
        model = new DataModel(R.drawable.science, "gk","#808AFF");
        list.add(model);
        model = new DataModel(R.drawable.science, "entrance","#FA6E5A");
        list.add(model);
        model = new DataModel(R.drawable.science, "exam","#808AFF");
        list.add(model);
        model = new DataModel(R.drawable.science, "exam","#FA6E5A");
        list.add(model);
        model = new DataModel(R.drawable.science, "exam","#808AFF");
        list.add(model);
        model = new DataModel(R.drawable.science, "exam","#FA6E5A");
        list.add(model);
    }
}