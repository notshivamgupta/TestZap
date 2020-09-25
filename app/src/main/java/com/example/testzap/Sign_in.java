package com.example.testzap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_in extends AppCompatActivity {
private TextInputEditText st1,st2;
private Button b;
private TextView t;
    private FirebaseAuth mAuth;
private ImageView si1,si2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        si1=findViewById(R.id.si1);
        mAuth = FirebaseAuth.getInstance();
        si2=findViewById(R.id.si2);
        st1=findViewById(R.id.sti1);
        st2=findViewById(R.id.sti2);
        t=findViewById(R.id.st1);
        b=findViewById(R.id.sb1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=st1.getText().toString().trim();
                String Pass=st2.getText().toString().trim();
                if(TextUtils.isEmpty(Email)) {

                    st1.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(Pass))
                {
                    st2.setError("Password is Required");
                    return;
                }
                if (Pass.length()<=8)
                {
                    st2.setError("Password must contain atleast 8 characters");
                    return;
                }
                mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Sign_in.this, "Login Sucessful!", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Sign_in.this,HomeActivity.class);
                            startActivity(intent);
                        }
                        Toast.makeText(Sign_in.this, "Login Failed!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}