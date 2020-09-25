package com.example.testzap;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;


public class HomeFragment extends Fragment {
    private RecyclerView rView;
    private GridLayoutManager manager;
    private List<DataModel> modelList;
    DataSource dataSource;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    CustomAdapter adapter;
    TextView tv,t1;

    String user;
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String s= "";
        Date today = new Date();

        SimpleDateFormat format = new SimpleDateFormat("HH");

        String time = format.format(today);

        int date= Integer.parseInt(time);
        if(date>=4&&date<12)
            s="Good Morning, "+user;
        else if(date>=12&&date<17)
            s="Good Afternoon, "+user;
        else if(date>=17&&date<23)
            s="Good Evening, "+user;
        else
            s="Good Morning, "+user;

        View view=inflater.inflate(R.layout.fragment_home, container, false);

        rView = view.findViewById(R.id.res1);
        tv=view.findViewById(R.id.texthome);
        t1= view.findViewById(R.id.namehome);
        t1.setText(s);
        manager=new GridLayoutManager(view.getContext(),2);
        rView.setLayoutManager(manager);
        dataSource=new DataSource();
        modelList=dataSource.list;
        adapter=new CustomAdapter(modelList,view.getContext());
        rView.setAdapter(adapter);
        return view;
    }
}