package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Setspage extends AppCompatActivity {
    private ImageView setsimg;
    private TextView setstxt;
    private LinearLayout setslyt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setspage);
        setsimg=findViewById(R.id.setsimg);
        setstxt=findViewById(R.id.setstxt);
        setslyt=findViewById(R.id.setslnr);
        Intent intent=getIntent();
        setstxt.setText(intent.getStringExtra("Subject Name"));
        setsimg.setImageResource(intent.getIntExtra("Subject Image",0));
        setslyt.setBackgroundColor(intent.getIntExtra("Subject Colour",0));
    }
}