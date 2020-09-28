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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    private CircleImageView imgprofile;
    private ImageButton ProfileButton;
    FirebaseAuth fAuth;
    String userId;
    StorageReference storageReference;
    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_profile, container, false);
        imgprofile =view.findViewById(R.id.profile_image);
        ProfileButton = view.findViewById(R.id.addImage);
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