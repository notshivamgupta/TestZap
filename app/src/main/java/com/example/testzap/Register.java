package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {
private ImageView ri1,ri2;
private TextInputEditText Rt1,Rt2,Rt3,Rt4;
private TextView Rtt1,Rtt2;
private Button Rb1,Rb2;
Intent intent,a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ri1 =findViewById(R.id.rimg1);
        ri2 =findViewById(R.id.rimg2);
        Rt1=findViewById(R.id.rti1);
        Rt2=findViewById(R.id.rti2);
        Rt3=findViewById(R.id.rti3);
        Rt4=findViewById(R.id.rti4);
        Rtt1=findViewById(R.id.rt1);
        Rtt2=findViewById(R.id.rt2);
        Rb1=findViewById(R.id.rb1);
        Rb2=findViewById(R.id.rb2);

       Rb1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               intent=new Intent(Register.this,MainActivity.class);
               startActivity(intent);
           }
     });

      Rb2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               a=new Intent(Register.this,Sign_in.class);
               startActivity(a);
           }
       });
    }
}