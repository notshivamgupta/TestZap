package com.example.testzap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SetsAdapter extends FirebaseRecyclerAdapter<Setsmodel,SetsAdapter.SetsViewHolder> {
    public SetsAdapter(@NonNull FirebaseRecyclerOptions<Setsmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SetsViewHolder setsViewHolder, int i, @NonNull Setsmodel setsmodel) {
        setsViewHolder.setTxt(setsmodel.getSet());
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
        public SetsViewHolder(@NonNull View itemView) {
            super(itemView);
            SetsTxt=itemView.findViewById(R.id.Settxt);
        }
        public void setTxt(String string) {
            SetsTxt.setText(string);
        }
    }
}
