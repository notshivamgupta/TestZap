package com.example.testzap;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<DataModel> list;
    private Context context;

    public CustomAdapter(List<DataModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView= LayoutInflater.from(context).inflate(R.layout.recyclerview,parent,false);

        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int a = Color.parseColor(list.get(position).getColour());
  holder.tView.setText(list.get(position).getTitle());
  holder.iView.setImageResource(list.get(position).getImg());
  holder.layout.setBackgroundColor(a);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView iView;
       private TextView tView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.reslr);
            iView=itemView.findViewById(R.id.recycle);
           tView=itemView.findViewById(R.id.recycletytle);
        }
    }
}
