package com.example.testzap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;
import org.w3c.dom.Text;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "Tag";
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
private ImageView ri1,ri2;
private TextInputEditText Rt1,Rt2,Rt3,Rt4;
private TextView Rtt1,Rtt2;
CentralStorage storage;
private Button Rb1,Rb2;
String userId;
Intent intent,a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
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
        storage= new CentralStorage(Register.this);
        if (mAuth.getCurrentUser()!=null) {

            startActivity(new Intent(getApplicationContext(), Register.class));
            finish();
        }
       Rb1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             final String Email=Rt2.getText().toString().trim();
             final String Pass=Rt3.getText().toString().trim();
             final String Name=Rt1.getText().toString();
             final String status="Available";
               final int test_completed=0;
               final int time_taken=0;
             if(TextUtils.isEmpty(Email)) {

                 Rt2.setError("Email is Required");
                 return;
             }
             if(TextUtils.isEmpty(Pass))
             {
                 Rt3.setError("Password is Required");
                 return;
             }
             if (Pass.length()<7)
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
                          userId= mAuth.getCurrentUser().getUid();
                         DocumentReference documentReference=db.collection("Users").document(userId);
                         Map<String,Object>user=new HashMap<>();
                         user.put("Full_Name",Name);
                         user.put("Email_Id",Email);
                         user.put("status",status);
                         user.put("test_completed",test_completed);
                         user.put("time_taken",time_taken);
                         documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void aVoid) {
                                 Log.d(TAG,"On Success: User Profile Created for"+userId);
                             }
                         });
                       storage.setData("USER",Email);
                       storage.setData("PASS",Pass);
                          intent=new Intent(Register.this,HomeActivity.class);
                         startActivity(intent);
                          finish();
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

