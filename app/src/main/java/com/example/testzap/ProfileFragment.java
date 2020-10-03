package com.example.testzap;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    private CircleImageView imgprofile;
    private ImageButton ProfileButton, editstatus;
    FirebaseAuth fAuth;
    String userId,statu;
    StorageReference storageReference;
    FirebaseFirestore fStore;
    TextView name,status, comp, duration;
    EditText editText;
    Button done;
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    public ProfileFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_profile, container, false);
        imgprofile =view.findViewById(R.id.profile_image);
        ProfileButton = view.findViewById(R.id.addImage);
        name= view.findViewById(R.id.user_name);
        status=view.findViewById(R.id.status_text);
        comp=view.findViewById(R.id.test_completed);
        duration=view.findViewById(R.id.time_taken);
        editstatus= view.findViewById(R.id.addstatus);
        editText=view.findViewById(R.id.editPersonName);
        done=view.findViewById(R.id.done);

        fAuth=FirebaseAuth.getInstance();
        userId=fAuth.getCurrentUser().getUid();

        storageReference= FirebaseStorage.getInstance().getReference();
        StorageReference profoleRef=storageReference.child("UsersProfile").child(userId);
            profoleRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(imgprofile);
                }
            });
        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent OpenGalary= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(OpenGalary,1000);
            }
        });

        fAuth= FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser() .getUid();

        DocumentReference documentReference= fStore.collection("Users").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {


                String user=value.getString("Full_Name");
                statu=value.getString("status");
                long test_completed= value.getLong("test_completed");
                long time_taken= value.getLong("time_taken");


                name.setText(user);
                status.setText(statu);
                comp.setText(Long. toString(test_completed));
                duration.setText(Long. toString(time_taken));

            }
        });

        editstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.setVisibility(view.INVISIBLE);
                done.setVisibility(view.VISIBLE);
                editText.setText(status.getText(), TextView.BufferType.EDITABLE);
                editText.setVisibility(view.VISIBLE);

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=editText.getText().toString();

                // firestore update
                db = FirebaseFirestore.getInstance();

                db.collection("Users")
                        .document(userId)
                .update("status", a)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Status Updated", Toast.LENGTH_SHORT).show();
                        } else {
                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });

                editText.setVisibility(view.INVISIBLE);
                status.setVisibility(view.VISIBLE);
                done.setVisibility(view.INVISIBLE);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000)
        {
          if (resultCode== Activity.RESULT_OK)
          {
              Uri ImageUri=data.getData();
              imgprofile.setImageURI(ImageUri);
              final StorageReference fileref=storageReference.child("UsersProfile").child(userId);
              fileref.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                  @Override
                  public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                      fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                          @Override
                          public void onSuccess(Uri uri) {
                              Picasso.get().load(uri).into(imgprofile);
                          }
                      });
                  }
              }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                  }
              });
          }
        }
    }
}