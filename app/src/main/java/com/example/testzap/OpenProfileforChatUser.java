package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OpenProfileforChatUser extends AppCompatActivity {
    private TextView name,status,test_taken,time_taken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_profilefor_chat_user);
        name=findViewById(R.id.nameforchatuser);
        status=findViewById(R.id.status_textforchatuser);
        test_taken=findViewById(R.id.test_completedforchatuser);
        time_taken=findViewById(R.id.time_takenforchatuser);
        Intent intent=getIntent();
        name.setText(intent.getStringExtra("Name"));
        status.setText(intent.getStringExtra("Status"));
     Long a=intent.getLongExtra("test",0);
     Long b=intent.getLongExtra("Time",0);
     test_taken.setText(a.toString());
String time;
        if(b<60)
            time=b+" Sec";
        else if( b<3600)
            time = b/60+"M "+b%60+"S";
        else
            time= (b/60)/60+"H "+(b/60)%60+"M";
        time_taken.setText(time);
    }
}