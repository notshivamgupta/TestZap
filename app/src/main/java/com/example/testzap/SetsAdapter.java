package com.example.testzap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SetsAdapter extends FirebaseRecyclerAdapter<Setsmodel,SetsAdapter.SetsViewHolder> {
    public SetsAdapter(@NonNull FirebaseRecyclerOptions<Setsmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final SetsViewHolder setsViewHolder, final int i, @NonNull final Setsmodel setsmodel) {
        setsViewHolder.setTxt(setsmodel.getSet());
        setsViewHolder.ButSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Starting the Test- "+setsmodel.Name, Toast.LENGTH_SHORT).show();
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
        public SetsViewHolder(@NonNull View itemView) {
            super(itemView);
            SetsTxt=itemView.findViewById(R.id.Settxt);
            ButSets=itemView.findViewById(R.id.BUTTONSETs);
        }
        public void setTxt(String string) {
            SetsTxt.setText(string);
        }
    }
}
