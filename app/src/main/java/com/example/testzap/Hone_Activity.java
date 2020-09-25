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
private ImageView i1,i2,i3,i4;
private RecyclerView rView;
private Layout l;
private GridLayoutManager manager;
private List<DataModel> modelList;
DataSource dataSource;
CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hone_);
        i1=findViewById(R.id.gri1);
        i2=findViewById(R.id.gri2);
        i3=findViewById(R.id.gri3);
        i4=findViewById(R.id.gri4);
        rView=findViewById(R.id.res1);
        manager=new GridLayoutManager(Hone_Activity.this,2);
        rView.setLayoutManager(manager);
        dataSource=new DataSource();
        modelList=dataSource.list;
        adapter=new CustomAdapter(modelList,Hone_Activity.this);
    rView.setAdapter(adapter);
    }
}