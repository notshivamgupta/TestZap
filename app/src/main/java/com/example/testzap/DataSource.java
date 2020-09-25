package com.example.testzap;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    List<DataModel> list = new ArrayList<>();
    DataModel model;

    public DataSource() {
        model = new DataModel(R.drawable.a1, "Physics");
        list.add(model);
        model = new DataModel(R.drawable.a2, "Maths");
        list.add(model);
        model = new DataModel(R.drawable.a3, "gk");
        list.add(model);
        model = new DataModel(R.drawable.a4, "entrance");
        list.add(model);
        model = new DataModel(R.drawable.a5, "exam");
        list.add(model);
        model = new DataModel(R.drawable.a6, "exam");
        list.add(model);
    }
}