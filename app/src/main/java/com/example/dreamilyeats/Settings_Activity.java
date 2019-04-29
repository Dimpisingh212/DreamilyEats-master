package com.example.dreamilyeats;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class Settings_Activity extends AppCompatActivity {

    private TextView sign_out,user_name,edit_account;
    private FirebaseAuth firebaseAuth;
    private ImageView back;
    private CircleImageView user_profile_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        setContentView(R.layout.activity_settings_);

        sign_out = findViewById(R.id.sign_out);
        back = findViewById(R.id.back);
        user_name = findViewById(R.id.user_name);
        user_profile_pic=findViewById(R.id.user_profile_pic);
        edit_account=findViewById(R.id.edit_account);

        SharedPreferences preferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        String n = preferences.getString("username", null);
        user_name.setText(n);


        user_name.setText(firebaseUser.getDisplayName());
        Glide.with(getApplicationContext()).load(firebaseUser.getPhotoUrl()).into(user_profile_pic);

        /*SharedPreferences sharedPreferences = getSharedPreferences("USER_PROFILE", MODE_PRIVATE);
        String image = sharedPreferences.getString("myprofile" , null);
        Bitmap profile = decodeBase64(image);

        Glide.with(getApplicationContext()).load(profile).into(user_profile_pic);*/


        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(Settings_Activity.this).create();
                alertDialog.setMessage("Are you sure for Sign out");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();
                        Intent intent = new Intent(Settings_Activity.this, LoginActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              onBackPressed();

            }
        });

        edit_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings_Activity.this, EditProfile.class);
                startActivity(intent);
            }
        });
    }



    public static Bitmap decodeBase64(String input) {
        byte[] decodeByte = Base64.decode(input, 0);
        return  BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
    }



}
