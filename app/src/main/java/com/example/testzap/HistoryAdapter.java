package com.example.testzap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class HistoryAdapter extends FirestoreRecyclerAdapter<HistoryModel, HistoryAdapter.HistoryHolder> {


    public HistoryAdapter(@NonNull FirestoreRecyclerOptions<HistoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HistoryHolder holder, int position, @NonNull HistoryModel model) {
        holder.sub.setText(model.sub_name);
        holder.set.setText(model.sub_set);

        int i=model.time_taken;
        String time="";
        if (i<60)
            time=i+" sec";
        else
        {
            int m=i/60,s=i%60;
            time = i/60+"m "+i%60+"s";
        }
        holder.time.setText(time);
        holder.correct.setText(Integer.toString(model.correct));
    }
    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.historyrecycler,
                parent, false);
        return new HistoryHolder(v);
    }

    class HistoryHolder extends RecyclerView.ViewHolder {
        TextView sub,set,time,correct;

        public HistoryHolder(View itemView) {
            super(itemView);
            sub= itemView.findViewById(R.id.subjectfromfirestore);
            set= itemView.findViewById(R.id.setfromfirestore);
            time= itemView.findViewById(R.id.timetakenhistory);
            correct= itemView.findViewById(R.id.correctpart);

        }
    }
}