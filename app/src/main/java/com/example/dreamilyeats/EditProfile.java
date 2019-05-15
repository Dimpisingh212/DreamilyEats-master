package com.example.dreamilyeats;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.Profile;
import com.facebook.internal.ImageRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.http.Url;

public class EditProfile extends AppCompatActivity {

    private static final String TAG = "EditProfile=> ";
    private TextView user_name, email_id,phone_number;
    private FirebaseAuth firebaseAuth;
    private CircleImageView profile_dp;
    private ImageView back,edit;
    private FirebaseStorage storage;
    private Bitmap my_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        storage = FirebaseStorage.getInstance();






        user_name = findViewById(R.id.user_name);
        email_id = findViewById(R.id.email_id);
        profile_dp = findViewById(R.id.profile_dp);
        back = findViewById(R.id.back);
        phone_number = findViewById(R.id.phone_number);
        edit = findViewById(R.id.edit);

        SharedPreferences preferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        String n = preferences.getString("username", null);
        user_name.setText(n);


        user_name.setText(firebaseUser.getDisplayName());
        email_id.setText(firebaseUser.getEmail());



        if (firebaseUser.getPhotoUrl()!=null && !firebaseUser.getPhotoUrl().toString().equalsIgnoreCase("")){
            // get image from firebase storage :
            StorageReference ref = storage.getReference().child(firebaseUser.getUid()+"mountains.jpg");
            try {
                final File localFile = File.createTempFile("Images", "bmp");
                ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener< FileDownloadTask.TaskSnapshot >() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        my_image = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        Glide.with(getApplicationContext()).load(my_image).into(profile_dp);
                        Log.e("Profile upload ", " my_image ");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        Glide.with(getApplicationContext()).load(firebaseUser.getPhotoUrl()).into(profile_dp);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                Glide.with(getApplicationContext()).load(firebaseUser.getPhotoUrl()).into(profile_dp);
                Toast.makeText(EditProfile.this, "Profile not uploaded.", Toast.LENGTH_LONG).show();
            }

        }else {

            Glide.with(getApplicationContext()).load(R.drawable.profile_).into(profile_dp);
            Log.e("Profile upload ", " profile not upload in imageview ");
            StorageReference ref = storage.getReference().child(firebaseUser.getUid()+"mountains.jpg");
            try {
                final File localFile = File.createTempFile("Images", "bmp");
                ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener< FileDownloadTask.TaskSnapshot >() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        my_image = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        Glide.with(getApplicationContext()).load(my_image).into(profile_dp);
                        Log.e("Profile upload ", " my_image ");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        Glide.with(getApplicationContext()).load(R.drawable.profile_).into(profile_dp);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                Glide.with(getApplicationContext()).load(R.drawable.profile_).into(profile_dp);
                Toast.makeText(EditProfile.this, "Profile not uploaded.", Toast.LENGTH_LONG).show();
            }
        }








        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, Edit_Profile_setting.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        phone_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewGroup viewGroup = findViewById(android.R.id.content);
                View view = LayoutInflater.from(EditProfile.this).inflate(R.layout.number_dialog_box, viewGroup, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);

                builder.setView(view);

                final EditText note = view.findViewById(R.id.note);
                final Button done = view.findViewById(R.id.done);

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (note.getText().toString().equals(null)) {

                            note.setError("Enter the valid number");
                        } else if (note.getText().toString().length() == 10){
                            phone_number.setText(note.getText().toString());
                            alertDialog.dismiss();
                        } else {
                            note.setError("Enter the valid number");
                        }
                    }
                });
            }
        });



    }



    public static Bitmap decodeBase64(String input) {
        byte[] decodeByte = Base64.decode(input, 0);
        return  BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
    }



}