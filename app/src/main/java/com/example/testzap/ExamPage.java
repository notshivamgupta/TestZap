package com.example.testzap;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
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
import java.util.concurrent.TimeUnit;

public class ExamPage extends AppCompatActivity {
    TextView question,ops1,ops2,ops3,ops4;
    private FirebaseDatabase fDbase;
    private DatabaseReference dref;
    private Button nextques;
    int total=0;
    String correct_ans;
    public int counter;
    int correct=0;
    int incorrect=0;
    CountDownTimer timer;
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
        getquestion();
        checkans();
        final TextView counttime=findViewById(R.id.counttime);
        CountDownTimer timer;
        timer= new CountDownTimer(600000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //counttime.setText(String.valueOf(counter));

                counttime.setText(""+String.format("%d : %d",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                if((TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished)==0) &&
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))==10)
                {
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
                Intent intent=new Intent(ExamPage.this,result.class);
                intent.putExtra("Correct",correct);
                intent.putExtra("Incorrect",incorrect);
                startActivity(intent);
                finish();
            }
        }.start();

    }
    public void getquestion()
    {
        Intent intent=getIntent();
        String name=intent.getStringExtra("Subject Name");
        final String subset=intent.getStringExtra("Set");
        fDbase = FirebaseDatabase.getInstance();
         final ArrayList<String> list = new ArrayList<String>();

        dref = fDbase.getReference().child("subject_name").child(name).child("sets").child(subset).child(Integer.toString(total));
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
    }
    public void checkans()
    {
        final String[] a = new String[1];
        final String[] b = new String[1];
        final String[] c=new String[1];;
        final String[] d=new String[1];;
        ops1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ops1.setBackground(getDrawable(R.drawable.selected_a));
                ops2.setBackground(getDrawable(R.drawable.option_2));
                ops3.setBackground(getDrawable(R.drawable.option_3));
                ops4.setBackground(getDrawable(R.drawable.option_4));
                a[0] ="correct";
                b[0] ="";
                c[0] ="";
                d[0] ="";
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
                a[0] ="";
                b[0] ="correct";
                c[0] ="";
                d[0] ="";

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
                a[0] ="";
                b[0] ="";
                c[0] ="correct";
                d[0] ="";
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
                a[0] ="";
                b[0] ="";
                c[0] ="";
                d[0] ="correct";

            }
        });
        nextques.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if (total<10) {

                    if (a[0].equals("correct"))
                    {
                        if (ops1.getText().toString().equals(correct_ans)) {
                            ops1.setBackground(getDrawable(R.drawable.right_a));
                            correct++;
                        } else {
                            ops1.setBackground(getDrawable(R.drawable.wrong_a));
                            incorrect++;
                            if (ops2.getText().toString().equals(correct_ans))
                            {
                                ops2.setBackground(getDrawable(R.drawable.right_b));
                            }
                            else
                            if (ops3.getText().toString().equals(correct_ans))
                            {
                                ops3.setBackground(getDrawable(R.drawable.right_c));
                            }
                            else {
                                ops4.setBackground(getDrawable(R.drawable.right_d));
                            }
                        }
                    }
                    else
                    if (b[0].equals("correct"))
                    {
                        if (ops2.getText().toString().equals(correct_ans)) {
                            ops2.setBackground(getDrawable(R.drawable.right_b));
                            correct++;
                        } else {
                            ops2.setBackground(getDrawable(R.drawable.wrong_b));
                            incorrect++;
                            if (ops1.getText().toString().equals(correct_ans))
                            {
                                ops1.setBackground(getDrawable(R.drawable.right_a));
                            }
                            else
                            if (ops3.getText().toString().equals(correct_ans))
                            {
                                ops3.setBackground(getDrawable(R.drawable.right_c));
                            }
                            else {
                                ops4.setBackground(getDrawable(R.drawable.right_d));
                            }
                        }
                    }
                    else if (c[0].equals("correct"))
                    {

                        if (ops3.getText().toString().equals(correct_ans)) {
                            ops3.setBackground(getDrawable(R.drawable.right_c));
                            correct++;
                        } else {
                            ops3.setBackground(getDrawable(R.drawable.wrong_c));
                            incorrect++;
                            if (ops1.getText().toString().equals(correct_ans))
                            {
                                ops1.setBackground(getDrawable(R.drawable.right_a));
                            }
                            else
                            if (ops2.getText().toString().equals(correct_ans))
                            {
                                ops2.setBackground(getDrawable(R.drawable.right_b));
                            }
                            else {
                                ops4.setBackground(getDrawable(R.drawable.right_d));
                            }
                        }
                    }
                    else
                    {

                        if (ops4.getText().toString().equals(correct_ans)) {
                            ops4.setBackground(getDrawable(R.drawable.right_d));
                            correct++;
                        } else {
                            ops4.setBackground(getDrawable(R.drawable.wrong_d));
                            incorrect++;
                            if (ops1.getText().toString().equals(correct_ans))
                            {
                                ops1.setBackground(getDrawable(R.drawable.right_a));
                            }
                            else
                            if (ops3.getText().toString().equals(correct_ans))
                            {
                                ops3.setBackground(getDrawable(R.drawable.right_c));
                            }
                            else {
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
                }
                else if (total==10)
                {
                    if (a[0].equals("correct"))
                    {
                        if (ops1.getText().toString().equals(correct_ans)) {
                            ops1.setBackground(getDrawable(R.drawable.right_a));
                            correct++;
                        } else {
                            ops1.setBackground(getDrawable(R.drawable.wrong_a));
                            incorrect++;
                            if (ops2.getText().toString().equals(correct_ans))
                            {
                                ops2.setBackground(getDrawable(R.drawable.right_b));
                            }
                            else
                            if (ops3.getText().toString().equals(correct_ans))
                            {
                                ops3.setBackground(getDrawable(R.drawable.right_c));
                            }
                            else {
                                ops4.setBackground(getDrawable(R.drawable.right_d));
                            }
                        }
                    }
                    else
                    if (b[0].equals("correct"))
                    {
                        if (ops2.getText().toString().equals(correct_ans)) {
                            ops2.setBackground(getDrawable(R.drawable.right_b));
                            correct++;
                        } else {
                            ops2.setBackground(getDrawable(R.drawable.wrong_b));
                            incorrect++;
                            if (ops1.getText().toString().equals(correct_ans))
                            {
                                ops1.setBackground(getDrawable(R.drawable.right_a));
                            }
                            else
                            if (ops3.getText().toString().equals(correct_ans))
                            {
                                ops3.setBackground(getDrawable(R.drawable.right_c));
                            }
                            else {
                                ops4.setBackground(getDrawable(R.drawable.right_d));
                            }
                        }
                    }
                    else if (c[0].equals("correct"))
                    {

                        if (ops3.getText().toString().equals(correct_ans)) {
                            ops3.setBackground(getDrawable(R.drawable.right_c));
                            correct++;
                        } else {
                            ops3.setBackground(getDrawable(R.drawable.wrong_c));
                            incorrect++;
                            if (ops1.getText().toString().equals(correct_ans))
                            {
                                ops1.setBackground(getDrawable(R.drawable.right_a));
                            }
                            else
                            if (ops2.getText().toString().equals(correct_ans))
                            {
                                ops2.setBackground(getDrawable(R.drawable.right_b));
                            }
                            else {
                                ops4.setBackground(getDrawable(R.drawable.right_d));
                            }
                        }
                    }
                    else
                    {

                        if (ops4.getText().toString().equals(correct_ans)) {
                            ops4.setBackground(getDrawable(R.drawable.right_d));
                            correct++;
                        } else {
                            ops4.setBackground(getDrawable(R.drawable.wrong_d));
                            incorrect++;
                            if (ops1.getText().toString().equals(correct_ans))
                            {
                                ops1.setBackground(getDrawable(R.drawable.right_a));
                            }
                            else
                            if (ops3.getText().toString().equals(correct_ans))
                            {
                                ops3.setBackground(getDrawable(R.drawable.right_c));
                            }
                            else {
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
                            Intent intent=new Intent(ExamPage.this,result.class);
                            intent.putExtra("Correct",correct);
                            intent.putExtra("Incorrect",incorrect);
                            startActivity(intent);
                            finish();
                        }
                    }, 1500);
                }
            }
        });
    }

}