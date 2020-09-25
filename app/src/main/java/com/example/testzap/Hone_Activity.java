package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Layout;
import android.widget.ImageView;

import java.util.List;

public class Hone_Activity extends AppCompatActivity {
private RecyclerView rView;
private GridLayoutManager manager;
private List<DataModel> modelList;
DataSource dataSource;
CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hone_);
        rView=findViewById(R.id.res1);
        manager=new GridLayoutManager(Hone_Activity.this,2);
        rView.setLayoutManager(manager);
        dataSource=new DataSource();
        modelList=dataSource.list;
        adapter=new CustomAdapter(modelList,Hone_Activity.this);
    rView.setAdapter(adapter);
    }
}