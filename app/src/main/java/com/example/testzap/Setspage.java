package com.example.testzap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Setspage extends AppCompatActivity {
    private ImageView setsimg;
    private TextView setstxt;
    private LinearLayout setslyt;
    private ImageButton im1;
    RecyclerView recsetsview;
    SetsAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setspage);
        setsimg=findViewById(R.id.setsimg);
        setstxt=findViewById(R.id.setstxt);
        setslyt=findViewById(R.id.setslnr);
        im1=findViewById(R.id.im1);
        recsetsview= (RecyclerView) findViewById(R.id.setsRecycler);

        recsetsview.setLayoutManager(new GridLayoutManager(Setspage.this,1));


        /*Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("posts");*/


        Intent intent=getIntent();
        int c=intent.getIntExtra("Subject Colour",0);
        setstxt.setText(intent.getStringExtra("Subject Name"));
        setsimg.setImageResource(intent.getIntExtra("Subject Image",0));
        setslyt.setBackgroundResource(R.drawable.linearsets);

        GradientDrawable drawable = (GradientDrawable) setslyt.getBackground();
        drawable.setColor(c);
          im1.setBackgroundColor(c);

        FirebaseRecyclerOptions<Setsmodel> options =
                new FirebaseRecyclerOptions.Builder<Setsmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Sets"), new SnapshotParser<Setsmodel>() {
                            @NonNull
                            @Override
                            public Setsmodel parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Setsmodel(snapshot.child("Name").getValue().toString());
                            }
                        })
                        .build();

        myadapter=new SetsAdapter(options);
        recsetsview.setAdapter(myadapter);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Setspage.this,ExamPage.class));
            }
        });
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
