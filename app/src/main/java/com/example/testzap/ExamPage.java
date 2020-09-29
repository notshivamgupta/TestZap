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
          fDbase=FirebaseDatabase.getInstance();
          for (int i=1;i<3;i++){
              String a="Question";
              a=a.concat(Integer.toString(i));
              dref = fDbase.getReference().child("Questions").child(a);
              dref.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot snapshot) {

                      Questionmodel Question = snapshot.getValue(Questionmodel.class);
                      question.setText(Question.getQuestion());
                      ops1.setText(Question.getOption1());
                      ops2.setText(Question.getOption2());
                      ops3.setText(Question.getOption3());
                      ops4.setText(Question.getOption4());
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError error) {

                  }
              });
          }
            /*  nextques.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      dref.addValueEventListener(new ValueEventListener() {
                          @Override
                          public void onDataChange(@NonNull DataSnapshot snapshot) {

                              Questionmodel Question=snapshot.getValue(Questionmodel.class);
                              question.setText(Question.getQuestion());
                              ops1.setText(Question.getOption1());
                              ops2.setText(Question.getOption2());
                              ops3.setText(Question.getOption3());
                              ops4.setText(Question.getOption4());
                          }

                          @Override
                          public void onCancelled(@NonNull DatabaseError error) {

                          }
                      });

                  }
              });*/


        /*FirebaseRecyclerOptions<Questionmodel> options =
                new FirebaseRecyclerOptions.Builder<Questionmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("subject_name").child("Scince & Nature").child("Sets").child("results").child("0"), new SnapshotParser<Questionmodel>() {
                            @NonNull
                            @Override
                            public Questionmodel parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Questionmodel(snapshot.child("question").getValue().toString(),
                                        snapshot.child("correct_answer").getValue().toString(),
                                        snapshot.child("incorrect_answers").child("0").getValue().toString(),
                                        snapshot.child("incorrect_answers").child("1").getValue().toString(),
                                        snapshot.child("incorrect_answers").child("2").getValue().toString());
                            }
                        })
                        .build();*/
    }
}