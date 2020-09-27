package com.example.testzap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView t1;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.tx1);

        fAuth= FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser() .getUid();

        DocumentReference documentReference= fStore.collection("Users").document(userId);
       documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
           @Override
           public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
               t1.setText(value.getString("Full_Name"));
           }
       });
    }
}