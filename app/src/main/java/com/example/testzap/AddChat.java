package com.example.testzap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SearchView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AddChat extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private AddChatAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);
        searchView=findViewById(R.id.searchView);
        final RecyclerView recyclerView = findViewById(R.id.allusers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                Query query = db.collection("Users").orderBy("Full_Name").startAt(s).endAt(s+"\uf8ff");
                FirestoreRecyclerOptions<AddChatModel> options = new FirestoreRecyclerOptions.Builder<AddChatModel>()
                        .setQuery(query, AddChatModel.class)
                        .build();
                adapter = new AddChatAdapter(options);
                adapter.startListening();
                recyclerView.setAdapter(adapter);
                return true;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        try{
        adapter.stopListening();
        }
        catch (Exception e){
        }
    }

}