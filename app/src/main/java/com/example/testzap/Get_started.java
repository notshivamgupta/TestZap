package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Get_started extends AppCompatActivity {
private ImageView i1,i2,i3;
private TextView t1,t2;
private Button b;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        i1=findViewById(R.id.img1);
        i2 = findViewById(R.id.img2);
        t1=findViewById(R.id.txt1);
        t2=findViewById(R.id.tex2);
        i3=findViewById(R.id.imageView);
        b=findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent =new Intent(Get_started.this,Register.class);
                startActivity(intent);
            }
        });
    }
}