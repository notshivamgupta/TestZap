package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {
    private TextView correct,incorrect,min,sec;
    private Button gotohome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
      correct=findViewById(R.id.correctans);
      incorrect=findViewById(R.id.incorrectans);
      gotohome=findViewById(R.id.buttonresult);
      min=findViewById(R.id.min);
      sec=findViewById(R.id.sec);


      int a,b,time;
        Intent intent=getIntent();
      a=intent.getIntExtra("Correct",0);
      b=intent.getIntExtra("Incorrect",0);
      time= intent.getIntExtra("time",0);

        min.setText(Integer.toString(time/60));
        sec.setText(Integer.toString(time%60));

      correct.setText(Integer.toString(a));
      incorrect.setText(Integer.toString(b));
      gotohome.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(result.this,HomeActivity.class));
              finish();
          }
      });
    }
}