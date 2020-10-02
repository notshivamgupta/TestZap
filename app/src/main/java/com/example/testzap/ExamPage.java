package com.example.testzap;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    int correct=0;
    int incorrect=0;
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

        dref = fDbase.getReference().child("subject_name").child("General Knowledge").child("sets").child("1").child(Integer.toString(total));
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Questionmodel Question = snapshot.getValue(Questionmodel.class);
                question.setText(Question.getQuestion());
                correct_ans=Question.getCorrect_answer();

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
        ops1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (ops1.getText().toString().equals(correct_ans))
                {
                    correct++;
                    ops1.setBackground(getDrawable(R.drawable.right_a));
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void run() {

                            ops1.setBackground(getDrawable(R.drawable.opton_1));
                        }
                    },1500);
                }
                else
                {
                    incorrect++;
                    ops1.setBackground(getDrawable(R.drawable.wrong_a));
                    if (ops2.getText().toString().equals(correct_ans))
                    {
                        ops2.setBackground(getDrawable(R.drawable.right_b));
                    }
                    else
                    if (ops3.getText().toString().equals(correct_ans))
                    {
                        ops3.setBackground(getDrawable(R.drawable.right_c));
                    }
                    else
                    if (ops4.getText().toString().equals(correct_ans))
                    {
                        ops4.setBackground(getDrawable(R.drawable.right_d));
                    }

                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void run() {



                            ops1.setBackground(getDrawable(R.drawable.opton_1));
                            ops2.setBackground(getDrawable(R.drawable.option_2));
                            ops3.setBackground(getDrawable(R.drawable.option_3));
                            ops4.setBackground(getDrawable(R.drawable.option_4));
                        }
                    },1500);
                }

            }
        });

        ops2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (ops2.getText().toString().equals(correct_ans))
                {
                    correct++;
                    ops2.setBackground(getDrawable(R.drawable.right_b));
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void run() {
                            ops2.setBackground(getDrawable(R.drawable.option_2));

                        }
                    },1500);
                }
                else
                {
                    incorrect++;
                    ops2.setBackground(getDrawable(R.drawable.wrong_b));
                    if (ops1.getText().toString().equals(correct_ans))
                    {
                        ops1.setBackground(getDrawable(R.drawable.right_a));
                    }
                    else
                    if (ops3.getText().toString().equals(correct_ans))
                    {
                        ops3.setBackground(getDrawable(R.drawable.right_c));
                    }
                    else
                    if (ops4.getText().toString().equals(correct_ans))
                    {
                        ops4.setBackground(getDrawable(R.drawable.right_d));
                    }

                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void run() {
                            ops1.setBackground(getDrawable(R.drawable.opton_1));
                            ops2.setBackground(getDrawable(R.drawable.option_2));
                            ops3.setBackground(getDrawable(R.drawable.option_3));
                            ops4.setBackground(getDrawable(R.drawable.option_4));

                        }
                    },1500);
                }

            }
        });
        ops3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (ops3.getText().toString().equals(correct_ans))
                {
                    correct++;
                    ops3.setBackground(getDrawable(R.drawable.right_c));
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void run() {
                            ops3.setBackground(getDrawable(R.drawable.option_3));

                        }
                    },1500);
                }
                else
                {
                    incorrect++;
                    ops3.setBackground(getDrawable(R.drawable.wrong_c));
                    if (ops1.getText().toString().equals(correct_ans))
                    {
                        ops1.setBackground(getDrawable(R.drawable.right_a));
                    }
                    else
                    if (ops2.getText().toString().equals(correct_ans))
                    {
                        ops2.setBackground(getDrawable(R.drawable.right_b));
                    }
                    else
                    if (ops4.getText().toString().equals(correct_ans))
                    {
                        ops4.setBackground(getDrawable(R.drawable.right_d));
                    }

                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void run() {
                            ops1.setBackground(getDrawable(R.drawable.opton_1));
                            ops2.setBackground(getDrawable(R.drawable.option_2));
                            ops3.setBackground(getDrawable(R.drawable.option_3));
                            ops4.setBackground(getDrawable(R.drawable.option_4));

                        }
                    },1500);
                }

            }
        });


        ops4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (ops4.getText().toString().equals(correct_ans))
                {
                    correct++;
                    ops4.setBackground(getDrawable(R.drawable.right_d));
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void run() {
                            ops4.setBackground(getDrawable(R.drawable.option_4));

                        }
                    },1500);
                }
                else
                {
                    incorrect++;
                    ops4.setBackground(getDrawable(R.drawable.wrong_d));
                    if (ops1.getText().toString().equals(correct_ans))
                    {
                        ops1.setBackground(getDrawable(R.drawable.right_a));
                    }
                    else
                    if (ops3.getText().toString().equals(correct_ans))
                    {
                        ops3.setBackground(getDrawable(R.drawable.right_c));
                    }
                    else
                    if (ops2.getText().toString().equals(correct_ans))
                    {
                        ops2.setBackground(getDrawable(R.drawable.right_b));
                    }

                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void run() {
                            ops1.setBackground(getDrawable(R.drawable.opton_1));
                            ops2.setBackground(getDrawable(R.drawable.option_2));
                            ops3.setBackground(getDrawable(R.drawable.option_3));
                            ops4.setBackground(getDrawable(R.drawable.option_4));

                        }
                    },1500);
                }

            }
        });



        nextques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (total < 8) {
                    dref = fDbase.getReference().child("subject_name").child("General Knowledge").child("sets").child("1").child(Integer.toString(total));
                    dref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Questionmodel Question = snapshot.getValue(Questionmodel.class);
                            question.setText(Question.getQuestion());
                            correct_ans=Question.getCorrect_answer();

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

                    ops1.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View view) {
                            if (ops1.getText().toString().equals(correct_ans))
                            {
                                correct++;
                                ops1.setBackground(getDrawable(R.drawable.right_a));
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                    @Override
                                    public void run() {

                                        ops1.setBackground(getDrawable(R.drawable.opton_1));
                                    }
                                },1500);
                            }
                            else
                            {
                                incorrect++;
                                ops1.setBackground(getDrawable(R.drawable.wrong_a));
                                if (ops2.getText().toString().equals(correct_ans))
                                {
                                    ops2.setBackground(getDrawable(R.drawable.right_b));
                                }
                                else
                                if (ops3.getText().toString().equals(correct_ans))
                                {
                                    ops3.setBackground(getDrawable(R.drawable.right_c));
                                }
                                else
                                if (ops4.getText().toString().equals(correct_ans))
                                {
                                    ops4.setBackground(getDrawable(R.drawable.right_d));
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                    @Override
                                    public void run() {


                                        ops1.setBackground(getDrawable(R.drawable.opton_1));
                                        ops2.setBackground(getDrawable(R.drawable.option_2));
                                        ops3.setBackground(getDrawable(R.drawable.option_3));
                                        ops4.setBackground(getDrawable(R.drawable.option_4));

                                    }
                                },1500);
                            }

                        }
                    });

                    ops2.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View view) {
                            if (ops2.getText().toString().equals(correct_ans))
                            {
                                correct++;
                                ops2.setBackground(getDrawable(R.drawable.right_b));
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                    @Override
                                    public void run() {
                                        ops2.setBackground(getDrawable(R.drawable.option_2));

                                    }
                                },1500);
                            }
                            else
                            {
                                incorrect++;
                                ops2.setBackground(getDrawable(R.drawable.wrong_b));
                                if (ops1.getText().toString().equals(correct_ans))
                                {
                                    ops1.setBackground(getDrawable(R.drawable.right_a));
                                }
                                else
                                if (ops3.getText().toString().equals(correct_ans))
                                {
                                    ops3.setBackground(getDrawable(R.drawable.right_c));
                                }
                                else
                                if (ops4.getText().toString().equals(correct_ans))
                                {
                                    ops4.setBackground(getDrawable(R.drawable.right_d));
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                    @Override
                                    public void run() {
                                        ops1.setBackground(getDrawable(R.drawable.opton_1));
                                        ops2.setBackground(getDrawable(R.drawable.option_2));
                                        ops3.setBackground(getDrawable(R.drawable.option_3));
                                        ops4.setBackground(getDrawable(R.drawable.option_4));

                                    }
                                },1500);
                            }

                        }
                    });
                    ops3.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View view) {
                            if (ops3.getText().toString().equals(correct_ans))
                            {
                                correct++;
                                ops3.setBackground(getDrawable(R.drawable.right_c));
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                    @Override
                                    public void run() {
                                        ops3.setBackground(getDrawable(R.drawable.option_3));

                                    }
                                },1500);
                            }
                            else
                            {
                                incorrect++;
                                ops3.setBackground(getDrawable(R.drawable.wrong_c));
                                if (ops1.getText().toString().equals(correct_ans))
                                {
                                    ops1.setBackground(getDrawable(R.drawable.right_a));
                                }
                                else
                                if (ops2.getText().toString().equals(correct_ans))
                                {
                                    ops2.setBackground(getDrawable(R.drawable.right_b));
                                }
                                else
                                if (ops4.getText().toString().equals(correct_ans))
                                {
                                    ops4.setBackground(getDrawable(R.drawable.right_d));
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                    @Override
                                    public void run() {
                                        ops1.setBackground(getDrawable(R.drawable.opton_1));
                                        ops2.setBackground(getDrawable(R.drawable.option_2));
                                        ops3.setBackground(getDrawable(R.drawable.option_3));
                                        ops4.setBackground(getDrawable(R.drawable.option_4));

                                    }
                                },1500);
                            }

                        }
                    });


                    ops4.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View view) {
                            if (ops4.getText().toString().equals(correct_ans))
                            {
                                correct++;
                                ops4.setBackground(getDrawable(R.drawable.right_d));
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                    @Override
                                    public void run() {
                                        ops4.setBackground(getDrawable(R.drawable.option_4));

                                    }
                                },1500);
                            }
                            else
                            {
                                incorrect++;
                                ops4.setBackground(getDrawable(R.drawable.wrong_d));
                                if (ops1.getText().toString().equals(correct_ans))
                                {
                                    ops1.setBackground(getDrawable(R.drawable.right_a));
                                }
                                else
                                if (ops3.getText().toString().equals(correct_ans))
                                {
                                    ops3.setBackground(getDrawable(R.drawable.right_c));
                                }
                                else
                                if (ops2.getText().toString().equals(correct_ans))
                                {
                                    ops2.setBackground(getDrawable(R.drawable.right_b));
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                    @Override
                                    public void run() {
                                        ops1.setBackground(getDrawable(R.drawable.opton_1));
                                        ops2.setBackground(getDrawable(R.drawable.option_2));
                                        ops3.setBackground(getDrawable(R.drawable.option_3));
                                        ops4.setBackground(getDrawable(R.drawable.option_4));

                                    }
                                },1500);
                            }

                        }
                    });


                }else
                {

                    Intent intent=new Intent(ExamPage.this,result.class);
                    intent.putExtra("Correct",correct);
                    intent.putExtra("Incorrect",incorrect);
                    startActivity(intent);
                    finish();
                }
            }
        });
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
                Toast.makeText(ExamPage.this, "Time Out", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ExamPage.this,result.class);
                intent.putExtra("Correct",correct);
                intent.putExtra("Incorrect",incorrect);
                startActivity(intent);
                finish();
            }
        }.start();

    }
}