package com.example.dreamilyeats;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicMarkableReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class Edit_Profile_setting extends AppCompatActivity {

    EditText user_name;
    ImageView back,done;
    FloatingActionButton add;
    private CircleImageView profile_dp;
    SharedPreferences.Editor editor1;
    SharedPreferences.Editor editor;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage storage;
    private Bitmap my_image;
    private  FirebaseUser firebaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile_setting);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        storage = FirebaseStorage.getInstance();


       // user_name = findViewById(R.id.user_name);
        back = findViewById(R.id.back);
        add = findViewById(R.id.add);
        profile_dp = findViewById(R.id.profile_dp);
        done = findViewById(R.id.done);
      //  user_name.setText(firebaseUser.getDisplayName());







        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                upload();
                onBackPressed();
            }
        });


        SharedPreferences sharedPreferences1 = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        String mygallaryimage = sharedPreferences1.getString("MYPIC", null);

        if (sharedPreferences1.getString("MYPIC" , null)!=null)
        {
            Bitmap image = decodeBase64(mygallaryimage);
            Glide.with(getApplicationContext()).load(image).into(profile_dp);
            getImage();

        }else {
            Glide.with(getApplicationContext()).load(firebaseUser.getPhotoUrl()).into(profile_dp);

        }






        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(Edit_Profile_setting.this, new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                AlertDialog alertDialog = new AlertDialog.Builder(Edit_Profile_setting.this).create();
                alertDialog.setMessage("Choose the source for capturing Image");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pickingPicture();
                        dialog.dismiss();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        takingPicture();
                        dialog.dismiss();
                    }
                });
                alertDialog.show();

            }
        });

    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 0){

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void takingPicture() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, 0);
    }

    private void pickingPicture() {
        Intent pickPicture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPicture, 1);
    }


    protected void onActivityResult(int requestCode , int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 0:
                if(resultCode == RESULT_OK){
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    profile_dp.setImageBitmap(imageBitmap);
                    storage();

                    editor1 = getSharedPreferences("USER_INFO", MODE_PRIVATE).edit();
                    editor1.putString("MYPIC", encodeToBase64(imageBitmap));
                    editor1.commit();

                }
                break;
            case 1:
                if(resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    if (data != null) {
                        Uri selectedImage = data.getData();
                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        profile_dp.setImageBitmap(bitmap);

                        editor1 = getSharedPreferences("USER_INFO", MODE_PRIVATE).edit();
                        editor1.putString("MYPIC", encodeToBase64(bitmap));
                        editor1.commit();

                    }
                }
                break;
        }
    }

    private void storage() {
        OutputStream output;
        File filepath = Environment.getExternalStorageDirectory();
        File dir= new File(filepath.getAbsolutePath()+"/camera picture/");
        dir.mkdirs();
        BitmapDrawable drawable = (BitmapDrawable) profile_dp.getDrawable();
        Bitmap bitmap1 =drawable.getBitmap();
        Date date=new Date();
        String currentTime= String.valueOf(date.getTime());
        Log.d("Edit_profile_setting", "storage: "+currentTime);
        File file =new File(dir, currentTime+".jpg");


        Log.d("Edit_profile_setting", "storage_Path: "+file.getAbsolutePath());


        try{
            output=new FileOutputStream(file);
            bitmap1.compress(Bitmap.CompressFormat.JPEG,100,output);
            output.flush();
            output.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static String encodeToBase64(Bitmap image) {
        Bitmap image1 = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image1.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        return  imageEncoded;
    }


    public static Bitmap decodeBase64(String input) {
        byte[] decodeByte = Base64.decode(input, 0);
        return  BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
    }


    public void upload() {

        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();
        // Create a reference to "mountains.jpg"
        StorageReference childRef = storageRef.child("mountains.jpg");
        // Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");
        // While the file names are the same, the references point to different files
        childRef.getName().equals(mountainImagesRef.getName());    // true
        childRef.getPath().equals(mountainImagesRef.getPath());    // false


        // Get the data from an ImageView as bytes
        profile_dp.setDrawingCacheEnabled(true);
        profile_dp.buildDrawingCache();
        Bitmap bitmap = profile_dp.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = childRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.e("Profile upload exception", " " +exception);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.e("Profile upload ", " upload ");

            }
        });
    }



    public void getImage() {
        if (firebaseUser.getPhotoUrl() != null && !firebaseUser.getPhotoUrl().toString().equalsIgnoreCase("")) {
            // get image from firebase storage :
            StorageReference ref = storage.getReference().child("mountains.jpg");
            try {
                final File localFile = File.createTempFile("Images", "bmp");
                ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        my_image = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        Glide.with(getApplicationContext()).load(my_image).into(profile_dp);
                        Log.e("Profile upload ", " my_image ");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Edit_Profile_setting.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        //Glide.with(getApplicationContext()).load(firebaseUser.getPhotoUrl()).into(user_profile_pic);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                Glide.with(getApplicationContext()).load(firebaseUser.getPhotoUrl()).into(profile_dp);
                Toast.makeText(Edit_Profile_setting.this, "Profile not uploaded.", Toast.LENGTH_LONG).show();
            }

        } else {

            Glide.with(getApplicationContext()).load(R.drawable.profile_).into(profile_dp);
            Log.e("Profile upload ", " profile not upload in imageview ");
            StorageReference ref = storage.getReference().child("mountains.jpg");
            try {
                final File localFile = File.createTempFile("Images", "bmp");
                ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        my_image = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        Glide.with(getApplicationContext()).load(my_image).into(profile_dp);
                        Log.e("Profile upload ", " my_image ");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Edit_Profile_setting.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        Glide.with(getApplicationContext()).load(R.drawable.profile_).into(profile_dp);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                Glide.with(getApplicationContext()).load(R.drawable.profile_).into(profile_dp);
                Toast.makeText(Edit_Profile_setting.this, "Profile not uploaded.", Toast.LENGTH_LONG).show();
            }


        }

    }

}
