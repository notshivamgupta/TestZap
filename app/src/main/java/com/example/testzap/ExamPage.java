package com.example.testzap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ExamPage extends AppCompatActivity {
TextView question,ops1,ops2,ops3,ops4;
private FirebaseDatabase fDbase;
private DatabaseReference dref;
private Button nextques;
int total=1;
String correct_ans;
    public int counter;
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
        final ArrayList<String> list = new ArrayList<String>();

        if (total>5) {
        }
        else {

            dref = fDbase.getReference().child("Questions").child(Integer.toString(total));
            dref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Questionmodel Question = snapshot.getValue(Questionmodel.class);
                    question.setText(Question.getQuestion());
                    correct_ans=Question.getOption0();

                    list.add(Question.getOption0());
                    list.add(Question.getOption1());
                    list.add(Question.getOption2());
                    list.add(Question.getOption3());

                    Collections.shuffle(list);

                    ops1.setText(list.get(0));
                    ops2.setText(list.get(1));
                    ops3.setText(list.get(2));
                    ops4.setText(list.get(3));
                    list.clear();
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
                            correct_ans=Question.getOption0();
                            list.add(Question.getOption0());
                            list.add(Question.getOption1());
                            list.add(Question.getOption2());
                            list.add(Question.getOption3());

                            Collections.shuffle(list);

                            ops1.setText(list.get(0));
                            ops2.setText(list.get(1));
                            ops3.setText(list.get(2));
                            ops4.setText(list.get(3));
                            list.clear();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    total++;

                }
            });
        }
        final TextView counttime=findViewById(R.id.counttime);
        new CountDownTimer(50000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counttime.setText(String.valueOf(counter));
                counter++;
            }
            @Override
            public void onFinish() {
                counttime.setText("Finished");
            }
        }.start();

    }
}