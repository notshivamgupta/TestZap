package com.example.testzap;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    String user=null;
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_home, container, false);
        fAuth=FirebaseAuth.getInstance();
        t1= view.findViewById(R.id.namehome);
        /*fStore=FirebaseFirestore.getInstance();
        String userId=fAuth.getCurrentUser().getUid();
        DocumentReference documentReference= fStore.collection("Users").document(userId);
       documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
           @Override
           public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                      user=value.getString("Full_Name");
               Toast.makeText(getActivity(), ""+user, Toast.LENGTH_SHORT).show();
           }
       });*/

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

        rView = view.findViewById(R.id.res1);
        tv=view.findViewById(R.id.texthome);
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