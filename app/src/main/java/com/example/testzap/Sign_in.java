package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Sign_in extends AppCompatActivity {
private TextInputEditText st1,st2;
private Button b;
private TextView t;
private ImageView si1,si2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        si1=findViewById(R.id.si1);
        si2=findViewById(R.id.si2);
        st1=findViewById(R.id.sti1);
        st2=findViewById(R.id.sti2);
        t=findViewById(R.id.st1);
        b=findViewById(R.id.sb1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sign_in.this,profile.class);
                startActivity(intent);
            }
        });
    }
}