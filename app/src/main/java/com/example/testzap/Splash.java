package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.concurrent.RunnableFuture;

public class Splash extends AppCompatActivity {
    Animation a1,a2,a3;
    ImageView s,b,i;
    CentralStorage storage;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        storage= new CentralStorage(Splash.this);
        a1= AnimationUtils.loadAnimation(this,R.anim.small);
        a2= AnimationUtils.loadAnimation(this,R.anim.big);
a3=AnimationUtils.loadAnimation(this,R.anim.animword);
        s=findViewById(R.id.s);
        b=findViewById(R.id.b);
        i=findViewById(R.id.splashimgtest);

        s.setAnimation(a1);
        b.setAnimation(a2);
        i.setAnimation(a3);
        value=storage.getData("USER");

       new Handler().postDelayed(
               new Runnable() {
                   @Override
                    public void run() {
                        if (value.isEmpty())
                       {
                            startActivity(new Intent(Splash.this,Get_started.class));
                        }
                        else
                       {
                            startActivity(new Intent(Splash.this,HomeActivity.class));
                       }
                       Splash.this.finish(); }
                },
                2000
       );
    }
}