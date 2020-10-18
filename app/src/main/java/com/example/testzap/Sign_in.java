package com.example.testzap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_in extends AppCompatActivity {
private TextInputEditText st1,st2;
private Button b;
private TextView t,forgotpassword;
CentralStorage storage;
    private FirebaseAuth mAuth;
private ImageView si1,si2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        storage= new CentralStorage(Sign_in.this);
        st1=findViewById(R.id.sti1);
        st2=findViewById(R.id.sti2);
        b=findViewById(R.id.sb1);
        forgotpassword=findViewById(R.id.forgotpassword);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Email=st1.getText().toString().trim();
                final String Pass=st2.getText().toString().trim();
                if(TextUtils.isEmpty(Email)) {

                    st1.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(Pass))
                {
                    st2.setError("Password is Required");
                    return;
                }
                if (Pass.length()<7)
                {
                    st2.setError("Password must contain atleast 8 characters");
                    return;
                }
                mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            FirebaseUser users=mAuth.getCurrentUser();
                            if (users.isEmailVerified()){
                                storage.setData("USER", Email);
                                storage.setData("PASS", Pass);
                                Toast.makeText(Sign_in.this, "Login Sucessful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Sign_in.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {

                                Toast.makeText(Sign_in.this, "Email not Verified", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Sign_in.this, "Login Failed!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText resetMail = new EditText(view.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setCancelable(true).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Sign_in.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Sign_in.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                passwordResetDialog.create().show();

            }
        });
    }
}