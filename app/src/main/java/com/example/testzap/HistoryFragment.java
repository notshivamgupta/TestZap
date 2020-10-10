package com.example.testzap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HistoryFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private HistoryAdapter adapter;
    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_history, container, false);
        setUpRecyclerView(view);
        return view;
    }
    private void setUpRecyclerView(View view) {

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        CollectionReference cRef=db.collection("History").document(userId)
        .collection("collection_name");

        Query query = cRef.orderBy("time",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<HistoryModel> options = new FirestoreRecyclerOptions.Builder<HistoryModel>()
                .setQuery(query, HistoryModel.class)
                .build();
        adapter = new HistoryAdapter(options);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerhistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
