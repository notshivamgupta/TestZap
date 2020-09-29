package com.example.testzap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ExamPage extends AppCompatActivity {
TextView question,ops1,ops2,ops3,ops4;
private FirebaseDatabase fDbase;
private DatabaseReference dref;
private Button nextques;
int total=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_page);
        question=findViewById(R.id.question);
        ops1=findViewById(R.id.option1);
        ops2=findViewById(R.id.option2);
        ops3=findViewById(R.id.option3);
        ops4=findViewById(R.id.option4);
        nextques=findViewById(R.id.buttonchangeques);
        fDbase = FirebaseDatabase.getInstance();

        if (total>5) {
        }
        else {

            dref = fDbase.getReference().child("Questions").child(Integer.toString(total));
            dref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    Questionmodel Question = snapshot.getValue(Questionmodel.class);
                    question.setText(Question.getQuestion());
                    ops1.setText(Question.getOption0());
                    ops2.setText(Question.getOption1());
                    ops3.setText(Question.getOption2());
                    ops4.setText(Question.getOption3());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
             total++;
            nextques.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dref = fDbase.getReference().child("Questions").child(Integer.toString(total));
                    dref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Questionmodel Question = snapshot.getValue(Questionmodel.class);
                            question.setText(Question.getQuestion());
                            ops1.setText(Question.getOption0());
                            ops2.setText(Question.getOption1());
                            ops3.setText(Question.getOption2());
                            ops4.setText(Question.getOption3());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    total++;

                }
            });
        }


    }
}