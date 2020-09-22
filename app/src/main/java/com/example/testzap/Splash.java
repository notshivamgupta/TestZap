package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    Animation a1,a2;
    ImageView s,b,i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        a1= AnimationUtils.loadAnimation(this,R.anim.small);
        a2= AnimationUtils.loadAnimation(this,R.anim.big);

        s=findViewById(R.id.s);
        b=findViewById(R.id.b);
        i=findViewById(R.id.splashimgtest);

        s.setAnimation(a1);
        b.setAnimation(a2);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Intent intent=new Intent(Splash.this,Get_started.class);
        startActivity(intent);
    }
}