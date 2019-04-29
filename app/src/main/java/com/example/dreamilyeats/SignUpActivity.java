package com.example.dreamilyeats;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private Button submitButton;
    private TextView signin;
    private EditText password,email_id,confirm_password,username;
    private final String TAG="SignUpActivity";
    private FirebaseAuth auth;
    private ProgressDialog PD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sign_up);

        submitButton = findViewById(R.id.button);
        signin = findViewById(R.id.signin);
        password = findViewById(R.id.password);
        email_id = findViewById(R.id.email_id);
        confirm_password = findViewById(R.id.confirm_password);
        username = findViewById(R.id.username);

        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);


        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        }


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = email_id.getText().toString();
                final String password1 = password.getText().toString();

                Log.e(TAG , "username : " +username.getText().toString());

                try {
                    if (!(password1.length() > 6 && email.length() > 6)) {
                        email_id.setError("Invalid Length, should be minimum 6 characters.");
                        password.setError("Invalid Length, should be minimum 6 characters.");

                    } else if (!password1.equals(confirm_password.getText().toString())) {
                        Toast.makeText(SignUpActivity.this, "Password do not match", Toast.LENGTH_LONG).show();

                    } else {
                        PD.show();
                        auth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override

                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(SignUpActivity.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                                            Log.e("error", task.getResult().toString());
                                        } else {

                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                    .setDisplayName(username.getText().toString())
                                                    .build();

                                            user.updateProfile(profileUpdates)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful()) {
                                                                Log.e(TAG , "User Profile updated");
                                                            }
                                                        }
                                                    });

                                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        PD.dismiss();
                                    }
                        });



                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });






    }
}





