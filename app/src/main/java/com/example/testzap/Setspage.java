package com.example.testzap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Setspage extends AppCompatActivity {
    private ImageView setsimg;
    private TextView setstxt;
    private ConstraintLayout setslyt;
    private ImageButton im1;
    RecyclerView recsetsview;
    SetsAdapter myadapter;
LottieAnimationView anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setspage);
        setsimg=findViewById(R.id.setsimg);
        setstxt=findViewById(R.id.setstxt);
        setslyt=findViewById(R.id.setslnr);
        im1=findViewById(R.id.im1);
        anim=findViewById(R.id.animationload);
        recsetsview= (RecyclerView) findViewById(R.id.setsRecycler);

        recsetsview.setLayoutManager(new GridLayoutManager(Setspage.this,1));


        Intent intent=getIntent();
        final int c=intent.getIntExtra("Subject Colour",0);
        final int img=intent.getIntExtra("Subject Image",0);
        final String name=intent.getStringExtra("Subject Name");
        setstxt.setText(name);
        setsimg.setImageResource(img);
        setslyt.setBackgroundResource(R.drawable.linearsets);

        GradientDrawable drawable = (GradientDrawable) setslyt.getBackground();
        drawable.setColor(c);
          im1.setBackgroundColor(c);
      Query query= FirebaseDatabase.getInstance().getReference().child("subject_name").child(name).child("sets");
        final FirebaseRecyclerOptions<Setsmodel> options =
                new FirebaseRecyclerOptions.Builder<Setsmodel>()
                        .setQuery(query, new SnapshotParser<Setsmodel>() {
                            @NonNull
                            @Override
                            public Setsmodel parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Setsmodel(snapshot.child("Name").getValue().toString());
                            }
                        })
                        .build();
        myadapter=new SetsAdapter(options,c,name,img);
        recsetsview.setAdapter(myadapter);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Setspage.this,HomeActivity.class));
            }
        });
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        anim.setVisibility(View.GONE);
                        recsetsview.setVisibility(View.VISIBLE);
                    }
                },2000);

    }

   @Override
    protected void onStart() {
        super.onStart();
        myadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myadapter.stopListening();
    }
}
