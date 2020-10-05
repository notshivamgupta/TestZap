package com.example.testzap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.autofill.AutofillId;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ExamPage extends AppCompatActivity {
    TextView question, ops1, ops2, ops3, ops4;
    private FirebaseDatabase fDbase;
    private TextView questionno;
    private DatabaseReference dref;
    private Button nextques,endtest;
    int total = 0;
    String correct_ans, name, subset;
    public int counter;
    int correct = 0;
    int incorrect = 0;
    String userId;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_page);
        question = findViewById(R.id.question);
        ops1 = findViewById(R.id.option1);
        ops2 = findViewById(R.id.option2);
        ops3 = findViewById(R.id.option3);
        ops4 = findViewById(R.id.option4);
        endtest=findViewById(R.id.button);
        questionno=findViewById(R.id.textView3);
        nextques = findViewById(R.id.buttonchangeques);
        getquestion();
        checkans();
        endtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(ExamPage.this);
                builder.setMessage("Are you sure! you want to End the Exam");
                builder.setCancelable(true);
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        timer.cancel();
                        finish();
                    }
                });
                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });
        final TextView counttime = findViewById(R.id.counttime);
        timer = new CountDownTimer(600000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //counttime.setText(String.valueOf(counter));

                counttime.setText("  " + String.format("%d : %d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                if ((TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) == 0) &&
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) == 10) {
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500);
                }
                /* every second vibrate
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(500);*/
                counter++;
            }

            @Override
            public void onFinish() {
                counttime.setText("Finished");
                Toast.makeText(ExamPage.this, "Time Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ExamPage.this, result.class);
                intent.putExtra("Correct", correct);
                intent.putExtra("Incorrect", incorrect);
                storedata();
                updatedate();
                startActivity(intent);
                finish();
            }
        }.start();

    }


    public void getquestion() {
        questionno.setText("Question  "+(total+1));
        Intent intent = getIntent();
        name = intent.getStringExtra("Subject Name");
        subset = intent.getStringExtra("Set");
        fDbase = FirebaseDatabase.getInstance();
        final ArrayList<String> list = new ArrayList<String>();

        dref = fDbase.getReference().child("subject_name").child(name).child("sets").child(subset).child(Integer.toString(total));
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Questionmodel Question = snapshot.getValue(Questionmodel.class);
                question.setText(Question.getQuestion());
                correct_ans = Question.getCorrect_answer();

                list.add(correct_ans);
                list.add(Question.getOption_1());
                list.add(Question.getOption_2());
                list.add(Question.getOption_3());

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

    public void checkans() {
        final String[] a = new String[1];
        final String[] b = new String[1];
        final String[] c = new String[1];
        ;
        final String[] d = new String[1];
        ;
        ops1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ops1.setBackground(getDrawable(R.drawable.selected_a));
                ops2.setBackground(getDrawable(R.drawable.option_2));
                ops3.setBackground(getDrawable(R.drawable.option_3));
                ops4.setBackground(getDrawable(R.drawable.option_4));
                a[0] = "correct";
                b[0] = "";
                c[0] = "";
                d[0] = "";
            }
        });
        ops2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ops2.setBackground(getDrawable(R.drawable.selected_b));
                ops1.setBackground(getDrawable(R.drawable.opton_1));
                ops3.setBackground(getDrawable(R.drawable.option_3));
                ops4.setBackground(getDrawable(R.drawable.option_4));
                a[0] = "";
                b[0] = "correct";
                c[0] = "";
                d[0] = "";

            }
        });
        ops3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ops3.setBackground(getDrawable(R.drawable.selected_c));
                ops2.setBackground(getDrawable(R.drawable.option_2));
                ops1.setBackground(getDrawable(R.drawable.opton_1));
                ops4.setBackground(getDrawable(R.drawable.option_4));
                a[0] = "";
                b[0] = "";
                c[0] = "correct";
                d[0] = "";
            }
        });
        ops4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ops3.setBackground(getDrawable(R.drawable.option_3));
                ops2.setBackground(getDrawable(R.drawable.option_2));
                ops1.setBackground(getDrawable(R.drawable.opton_1));
                ops4.setBackground(getDrawable(R.drawable.selected_d));
                a[0] = "";
                b[0] = "";
                c[0] = "";
                d[0] = "correct";

            }
        });
        nextques.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (total < 10) {

                    if (a[0].equals("correct")) {
                        if (ops1.getText().toString().equals(correct_ans)) {
                            ops1.setBackground(getDrawable(R.drawable.right_a));
                            correct++;
                        } else {
                            ops1.setBackground(getDrawable(R.drawable.wrong_a));
                            incorrect++;
                            if (ops2.getText().toString().equals(correct_ans)) {
                                ops2.setBackground(getDrawable(R.drawable.right_b));
                            } else if (ops3.getText().toString().equals(correct_ans)) {
                                ops3.setBackground(getDrawable(R.drawable.right_c));
                            } else {
                                ops4.setBackground(getDrawable(R.drawable.right_d));
                            }
                        }
                    } else if (b[0].equals("correct")) {
                        if (ops2.getText().toString().equals(correct_ans)) {
                            ops2.setBackground(getDrawable(R.drawable.right_b));
                            correct++;
                        } else {
                            ops2.setBackground(getDrawable(R.drawable.wrong_b));
                            incorrect++;
                            if (ops1.getText().toString().equals(correct_ans)) {
                                ops1.setBackground(getDrawable(R.drawable.right_a));
                            } else if (ops3.getText().toString().equals(correct_ans)) {
                                ops3.setBackground(getDrawable(R.drawable.right_c));
                            } else {
                                ops4.setBackground(getDrawable(R.drawable.right_d));
                            }
                        }
                    } else if (c[0].equals("correct")) {

                        if (ops3.getText().toString().equals(correct_ans)) {
                            ops3.setBackground(getDrawable(R.drawable.right_c));
                            correct++;
                        } else {
                            ops3.setBackground(getDrawable(R.drawable.wrong_c));
                            incorrect++;
                            if (ops1.getText().toString().equals(correct_ans)) {
                                ops1.setBackground(getDrawable(R.drawable.right_a));
                            } else if (ops2.getText().toString().equals(correct_ans)) {
                                ops2.setBackground(getDrawable(R.drawable.right_b));
                            } else {
                                ops4.setBackground(getDrawable(R.drawable.right_d));
                            }
                        }
                    } else {

                        if (ops4.getText().toString().equals(correct_ans)) {
                            ops4.setBackground(getDrawable(R.drawable.right_d));
                            correct++;
                        } else {
                            ops4.setBackground(getDrawable(R.drawable.wrong_d));
                            incorrect++;
                            if (ops1.getText().toString().equals(correct_ans)) {
                                ops1.setBackground(getDrawable(R.drawable.right_a));
                            } else if (ops3.getText().toString().equals(correct_ans)) {
                                ops3.setBackground(getDrawable(R.drawable.right_c));
                            } else {
                                ops2.setBackground(getDrawable(R.drawable.right_b));
                            }
                        }
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void run() {


                            ops1.setBackground(getDrawable(R.drawable.opton_1));
                            ops2.setBackground(getDrawable(R.drawable.option_2));
                            ops3.setBackground(getDrawable(R.drawable.option_3));
                            ops4.setBackground(getDrawable(R.drawable.option_4));
                            getquestion();
                        }
                    }, 1500);
                } else if (total == 10) {
                    if (a[0].equals("correct")) {
                        if (ops1.getText().toString().equals(correct_ans)) {
                            ops1.setBackground(getDrawable(R.drawable.right_a));
                            correct++;
                        } else {
                            ops1.setBackground(getDrawable(R.drawable.wrong_a));
                            incorrect++;
                            if (ops2.getText().toString().equals(correct_ans)) {
                                ops2.setBackground(getDrawable(R.drawable.right_b));
                            } else if (ops3.getText().toString().equals(correct_ans)) {
                                ops3.setBackground(getDrawable(R.drawable.right_c));
                            } else {
                                ops4.setBackground(getDrawable(R.drawable.right_d));
                            }
                        }
                    } else if (b[0].equals("correct")) {
                        if (ops2.getText().toString().equals(correct_ans)) {
                            ops2.setBackground(getDrawable(R.drawable.right_b));
                            correct++;
                        } else {
                            ops2.setBackground(getDrawable(R.drawable.wrong_b));
                            incorrect++;
                            if (ops1.getText().toString().equals(correct_ans)) {
                                ops1.setBackground(getDrawable(R.drawable.right_a));
                            } else if (ops3.getText().toString().equals(correct_ans)) {
                                ops3.setBackground(getDrawable(R.drawable.right_c));
                            } else {
                                ops4.setBackground(getDrawable(R.drawable.right_d));
                            }
                        }
                    } else if (c[0].equals("correct")) {

                        if (ops3.getText().toString().equals(correct_ans)) {
                            ops3.setBackground(getDrawable(R.drawable.right_c));
                            correct++;
                        } else {
                            ops3.setBackground(getDrawable(R.drawable.wrong_c));
                            incorrect++;
                            if (ops1.getText().toString().equals(correct_ans)) {
                                ops1.setBackground(getDrawable(R.drawable.right_a));
                            } else if (ops2.getText().toString().equals(correct_ans)) {
                                ops2.setBackground(getDrawable(R.drawable.right_b));
                            } else {
                                ops4.setBackground(getDrawable(R.drawable.right_d));
                            }
                        }
                    } else {

                        if (ops4.getText().toString().equals(correct_ans)) {
                            ops4.setBackground(getDrawable(R.drawable.right_d));
                            correct++;
                        } else {
                            ops4.setBackground(getDrawable(R.drawable.wrong_d));
                            incorrect++;
                            if (ops1.getText().toString().equals(correct_ans)) {
                                ops1.setBackground(getDrawable(R.drawable.right_a));
                            } else if (ops3.getText().toString().equals(correct_ans)) {
                                ops3.setBackground(getDrawable(R.drawable.right_c));
                            } else {
                                ops2.setBackground(getDrawable(R.drawable.right_b));
                            }
                        }
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void run() {
                            timer.cancel();
                            Intent intent = new Intent(ExamPage.this, result.class);
                            intent.putExtra("Correct", correct);
                            intent.putExtra("Incorrect", incorrect);
                            intent.putExtra("time", counter);
                            storedata();
                            updatedate();
                            startActivity(intent);
                            finish();
                        }
                    }, 1500);
                }
            }
        });
    }

    public void storedata(){
            FirebaseAuth mAuth;
            FirebaseFirestore db;
            mAuth = FirebaseAuth.getInstance();
            db = FirebaseFirestore.getInstance();
            userId = mAuth.getCurrentUser().getUid();
                            Map<String, Object> user = new HashMap<>();
                            user.put("sub_name", name);
                            user.put("sub_set", subset);
                            user.put("time_taken", counter);
                            user.put("correct", correct);
                            user.put("incorrect", incorrect);

                            String id = db.collection("History")
                                    .document(userId).collection("collection_name").document().getId();
                            db.collection("History")
                                    .document(userId).collection("collection_name").document(id).set(user);

        }
        public void updatedate()
        {

       final FirebaseFirestore fstore= FirebaseFirestore.getInstance();

            DocumentReference documentReference= fstore.collection("Users").document(userId);

            documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                  long test_completed = value.getLong("test_completed");
                         long time_taken = value.getLong("time_taken");

                         long a=1+test_completed;
                         long b= time_taken+counter;
                    Map<String, Object> user = new HashMap<>();
                    user.put("test_completed",a );
                    user.put("time_taken",b);
                    fstore.collection("Users").document(userId).update(user);

                }
            });



        }
        @Override
    public void onBackPressed()
        {
               final AlertDialog.Builder builder=new AlertDialog.Builder(ExamPage.this);
               builder.setMessage("Are you sure! you want to End the Exam");
               builder.setCancelable(true);
               builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                       timer.cancel();
                       finish();
                   }
               });
               builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.cancel();
                   }
               });
               AlertDialog alertDialog=builder.create();
               alertDialog.show();
        }
}