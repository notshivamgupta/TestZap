package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class OpenProfileforChatUser extends AppCompatActivity {
    private TextView name,status,test_taken,time_taken;
    String UserId;
    Button SendMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_profilefor_chat_user);
        name=findViewById(R.id.nameforchatuser);
        SendMessage=findViewById(R.id.SendMessage);
        status=findViewById(R.id.status_textforchatuser);
        test_taken=findViewById(R.id.test_completedforchatuser);
        time_taken=findViewById(R.id.time_takenforchatuser);
        Intent intent=getIntent();
        final String User_Name=intent.getStringExtra("Name");
        name.setText(User_Name);
        final String User_Status=intent.getStringExtra("Status");
        status.setText(User_Status);
        UserId=intent.getStringExtra("UserId");
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
        SendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent1=new Intent(OpenProfileforChatUser.this,SendMessageActivity.class);
              intent1.putExtra("UserName",User_Name);
              intent1.putExtra("User_Id",UserId);
              startActivity(intent1);
            }
        });
    }
}