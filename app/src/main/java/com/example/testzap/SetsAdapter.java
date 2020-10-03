package com.example.testzap;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SetsAdapter extends FirebaseRecyclerAdapter<Setsmodel,SetsAdapter.SetsViewHolder> {
    public int colour, img;
String name;

    public SetsAdapter(@NonNull FirebaseRecyclerOptions<Setsmodel> options,int a,String na, int b) {
        super(options);
        colour=a;
        img=b;
        name=na;
    }

    @Override
    protected void onBindViewHolder(@NonNull final SetsViewHolder setsViewHolder, final int i, @NonNull final Setsmodel setsmodel) {
        setsViewHolder.setTxt(setsmodel.getSet());
        setsViewHolder.ButSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(view.getContext(),start_test.class);

                intent.putExtra("Set",setsmodel.Name);
                intent.putExtra("Colour",colour);
                intent.putExtra("Subject Name",name);
                intent.putExtra("Subject Image",img);
                view.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public SetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.setsrecycler,parent,false);
        return new SetsViewHolder(view);
    }

    class SetsViewHolder extends RecyclerView.ViewHolder
    {
        TextView SetsTxt;
        Button ButSets;
        ImageView but;
        public SetsViewHolder(@NonNull View itemView) {
            super(itemView);
            SetsTxt=itemView.findViewById(R.id.Settxt);
            ButSets=itemView.findViewById(R.id.BUTTONSETs);
            but=itemView.findViewById(R.id.imageback);

            but.setBackgroundResource(R.drawable.circle);
            GradientDrawable drawa = (GradientDrawable) but.getBackground();
            drawa.setColor(colour);

            ButSets.setBackgroundResource(R.drawable.buttonsets);
            GradientDrawable drawable = (GradientDrawable) ButSets.getBackground();
            drawable.setColor(colour);
        }
        public void setTxt(String string) {
            SetsTxt.setText(string);
        }
    }
}
