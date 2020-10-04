package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class start_test extends AppCompatActivity {
    private TextView txtsub,subname,txtset,setname;
    private Button starttest;
    ImageView subimg;
    ImageButton im1;
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
        final Intent intent=getIntent();

        int c= intent.getIntExtra("Colour",0);
        final String name=intent.getStringExtra("Subject Name");
        int img=intent.getIntExtra("Subject Image",0);
        final String set=intent.getStringExtra("Set");

        subname.setText(name);
        setname.setText(set);
        subimg.setImageResource(img);
        setslyt.setBackgroundResource(R.drawable.linearsets);

        GradientDrawable drawable = (GradientDrawable) setslyt.getBackground();
        drawable.setColor(c);
        im1.setBackgroundColor(c);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(start_test.this,Setspage.class);
                startActivity(intent1);
            }
        });
        starttest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(start_test.this,ExamPage.class);
                a.putExtra("Subject Name",name);
                a.putExtra("Set",set);
                startActivity(a);
                finish();
            }
        });
    }
}
