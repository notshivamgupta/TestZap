package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Setspage extends AppCompatActivity {
    private ImageView setsimg;
    private TextView setstxt;
    private LinearLayout setslyt;
    private ImageButton im1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setspage);
        setsimg=findViewById(R.id.setsimg);
        setstxt=findViewById(R.id.setstxt);
        setslyt=findViewById(R.id.setslnr);
        im1=findViewById(R.id.im1);

        Intent intent=getIntent();
        int c=intent.getIntExtra("Subject Colour",0);
        setstxt.setText(intent.getStringExtra("Subject Name"));
        setsimg.setImageResource(intent.getIntExtra("Subject Image",0));
        setslyt.setBackgroundResource(R.drawable.linearsets);

        GradientDrawable drawable = (GradientDrawable) setslyt.getBackground();
        drawable.setColor(c);

        im1.setBackgroundColor(c);

    }
}
