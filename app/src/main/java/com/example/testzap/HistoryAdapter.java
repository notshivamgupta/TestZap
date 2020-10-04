package com.example.testzap;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class HistoryAdapter extends FirestoreRecyclerAdapter<HistoryModel, HistoryAdapter.HistoryHolder> {

ArrayList pieEntries;
    PieDataSet pieDataSet;
    PieData pieData;
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
            time = i/60+"m "+i%60+"s";
        }
        holder.time.setText(time);
        holder.correct.setText(Integer.toString(model.correct));
        pieEntries=new ArrayList<>();
        pieEntries.add(new PieEntry(model.correct,""));
        pieEntries.add(new PieEntry(model.incorrect,""));
        pieDataSet=new PieDataSet(pieEntries,"");
        pieData=new PieData(pieDataSet);
        pieData.setDrawValues(false);
        holder.pieChart.setData(pieData);
        pieDataSet.setColors(new int[]{Color.parseColor("#07E092"),Color.parseColor("#E76F51")});

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
        PieChart pieChart;

        public HistoryHolder(View itemView) {
            super(itemView);
            sub= itemView.findViewById(R.id.subjectfromfirestore);
            set= itemView.findViewById(R.id.setfromfirestore);
            time= itemView.findViewById(R.id.timetakenhistory);
            correct= itemView.findViewById(R.id.correctpart);
            pieChart=itemView.findViewById(R.id.piechart);
            pieChart.getDescription().setEnabled(false);
            pieChart.getLegend().setEnabled(false);
            pieChart.setDrawSliceText(false);
            pieChart.setHoleRadius(75f);
        }
    }
}