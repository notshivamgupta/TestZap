package com.example.testzap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendMessageActivity extends AppCompatActivity {
private TextView name;
private EditText message;
private RecyclerView recyclerView;
private Button send;
GetMessageAdapter adapter;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        name=findViewById(R.id.title);
        message=findViewById(R.id.Typemessagehere);
        recyclerView=findViewById(R.id.Recyclerinchat);
        send=findViewById(R.id.presstosendmessage);
        Intent intent = getIntent();
        String Name = intent.getStringExtra("UserName");
        final String UserIDres=intent.getStringExtra("User_Id");
        name.setText(Name);
        final String user=FirebaseAuth.getInstance().getCurrentUser().getUid();
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);

              mAuth = FirebaseAuth.getInstance();
            final String userId=mAuth.getCurrentUser().getUid();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Chats").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Send Message", "Listen failed.", error);
                    return;
                }
                else
                {
                    List<GetMessageModel> messages = new ArrayList<>();

                    for (QueryDocumentSnapshot doc : value) {
                      if (doc.getString("Receiver").equals(UserIDres)|| doc.getString("Sender").equals(UserIDres))
                          messages.add(
                                  new GetMessageModel(
                                          doc.getString("Message"),
                                          doc.getString("Receiver"),
                                          doc.getString("Sender")
                                  )
                          );
                    }
                adapter = new GetMessageAdapter(messages,userId);
                    recyclerView.setAdapter(adapter);
            }
        }
    });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mes=message.getText().toString();
                if (!mes.equals(""))
                {
                    FirebaseAuth mAuth;
                    FirebaseFirestore db;
                    mAuth = FirebaseAuth.getInstance();
                    db = FirebaseFirestore.getInstance();
                    String userId = mAuth.getCurrentUser().getUid();
                    Map<String, Object> user = new HashMap<>();
                    user.put("Message", mes);
                    user.put("Sender",userId);
                    user.put("Receiver",UserIDres);
                    String id = db.collection("Chats").document().getId();
                    db.collection("Chats").document(id).set(user);
                }
                else
                {
                    Toast.makeText(SendMessageActivity.this, "Message is Empty", Toast.LENGTH_SHORT).show();
                }
                message.setText("");

            }
        });
    }

}