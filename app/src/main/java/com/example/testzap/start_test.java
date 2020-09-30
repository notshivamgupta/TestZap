package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class start_test extends AppCompatActivity {
    private TextView txtsub,subname,txtset,setname;
    private Button starttest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);
        txtset=findViewById(R.id.sub_txt);
        subname=findViewById(R.id.sub_txttxt);
        txtset=findViewById(R.id.sub_set);
        setname=findViewById(R.id.sub_settxt);
        Intent intent=getIntent();
        setname.setText(intent.getStringExtra("Set"));
        starttest=findViewById(R.id.buttonstart);
        starttest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(start_test.this,ExamPage.class);
                startActivity(a);
            }
        });
    }
}