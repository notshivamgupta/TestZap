package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class start_test extends AppCompatActivity {
    private TextView txtsub,subname,txtset,setname;
    private Button starttest;
    ImageView im1,subimg;
    LinearLayout setslyt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);
        txtset=findViewById(R.id.sub_txt);
        subname=findViewById(R.id.sub_txttxt);
        txtset=findViewById(R.id.sub_set);
        setname=findViewById(R.id.sub_settxt);
        setslyt=findViewById(R.id.layout1);
        im1=findViewById(R.id.im1);
        starttest=findViewById(R.id.buttonstart);
        subimg=findViewById(R.id.sub_img);
        Intent intent=getIntent();

        int c= intent.getIntExtra("Colour",0);
        String name=intent.getStringExtra("Subject Name");
        int img=intent.getIntExtra("Subject Image",0);
        String set=intent.getStringExtra("Set");

        subname.setText(name);
        setname.setText(set);
        subimg.setImageResource(img);
        setslyt.setBackgroundResource(R.drawable.linearsets);

        GradientDrawable drawable = (GradientDrawable) setslyt.getBackground();
        drawable.setColor(c);
        im1.setBackgroundColor(c);

        starttest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(start_test.this,ExamPage.class);
                startActivity(a);
            }
        });
    }
}
