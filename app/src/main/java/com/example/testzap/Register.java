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
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
private ImageView ri1,ri2;
private TextInputEditText Rt1,Rt2,Rt3,Rt4;
private TextView Rtt1,Rtt2;
private Button Rb1,Rb2;
Intent intent,a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        ri1 =findViewById(R.id.rimg1);
        ri2 =findViewById(R.id.rimg2);
        Rt1=findViewById(R.id.rti1);
        Rt2=findViewById(R.id.rti2);
        Rt3=findViewById(R.id.rti3);
        Rt4=findViewById(R.id.rti4);
        Rtt1=findViewById(R.id.rt1);
        Rtt2=findViewById(R.id.rt2);
        Rb1=findViewById(R.id.rb1);
        Rb2=findViewById(R.id.rb2);
        if (mAuth.getCurrentUser()!=null) {

            startActivity(new Intent(getApplicationContext(), Register.class));
            finish();
        }
       Rb1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             String Email=Rt2.getText().toString().trim();
             String Pass=Rt3.getText().toString().trim();
             if(TextUtils.isEmpty(Email)) {

                 Rt2.setError("Email is Required");
                 return;
             }
             if(TextUtils.isEmpty(Pass))
             {
                 Rt3.setError("Password is Required");
                 return;
             }
             if (Pass.length()<=8)
             {
                 Rt3.setError("Password must contain atleast 8 characters");
                 return;
             }
             mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful())
                     {
                         Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                         intent=new Intent(Register.this,Sign_in.class);
                         startActivity(intent);
                     }
                     else
                     {
                         Toast.makeText(Register.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                     }

                 }
             });
           }
       });
      Rb2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               a=new Intent(Register.this,Sign_in.class);
               startActivity(a);
           }
       });
    }
}

