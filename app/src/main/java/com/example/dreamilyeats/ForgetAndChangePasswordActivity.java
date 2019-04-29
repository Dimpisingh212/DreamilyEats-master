package com.example.dreamilyeats;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgetAndChangePasswordActivity extends AppCompatActivity {

    EditText email_id;
    Button button;
    private ProgressDialog PD;
    private FirebaseAuth auth;
    TextInputLayout label;
    TextView change_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_and_change_password);
        auth = FirebaseAuth.getInstance();

        email_id = findViewById(R.id.email_id);
        button = findViewById(R.id.button);
        label = findViewById(R.id.label);
        change_pass = findViewById(R.id.change_pass);

            PD = new ProgressDialog(this);
            PD.setMessage("Loading...");
            PD.setCancelable(true);
            PD.setCanceledOnTouchOutside(false);



            final int mode = getIntent().getIntExtra("Mode", 0);
            if (mode == 0) {
                change_pass.setText("Forget Password");
                email_id.setHint("Enter Registered Email");
                label.setHint("Enter Registered Email");
            } else if (mode == 1) {
                change_pass.setText("Change Password");
                email_id.setHint("Enter New Password");
                label.setHint("Enter New Password");
            } else if (mode == 2) {
                change_pass.setText("Change Email");
                email_id.setHint("Enter New Email");
                label.setHint("Enter New Email");
            } else {
                change_pass.setText("Delete User");
                email_id.setVisibility(View.GONE);
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callFunction(mode);
                }
            });

        }

        private void callFunction(int mode) {

            FirebaseUser user = auth.getCurrentUser();
            final String modeStr = email_id.getText().toString();
            if (mode == 0) {
                if (TextUtils.isEmpty(modeStr)) {
                    email_id.setError("Value Required");
                } else {
                    PD.show();
                    auth.sendPasswordResetEmail(modeStr).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgetAndChangePasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ForgetAndChangePasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                            }
                            PD.dismiss();

                        }
                    });
                }
            } else if (mode == 1) {
                if (TextUtils.isEmpty(modeStr)) {
                    email_id.setError("Value Required");
                } else {
                    PD.show();
                    user.updatePassword(modeStr)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ForgetAndChangePasswordActivity.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(ForgetAndChangePasswordActivity.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                                    }
                                    PD.dismiss();
                                }

                            });
                }
            } else if (mode == 2) {
                if (TextUtils.isEmpty(modeStr)) {
                    email_id.setError("Value Required");
                } else {
                    PD.show();
                    user.updateEmail(modeStr)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override                            public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ForgetAndChangePasswordActivity.this, "Email address is updated.", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(ForgetAndChangePasswordActivity.this, "Failed to update email!", Toast.LENGTH_LONG).show();
                                    }
                                    PD.dismiss();
                                }
                            });
                }
            } else {
                if (user != null) {
                    PD.show();
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ForgetAndChangePasswordActivity.this, "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(ForgetAndChangePasswordActivity.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                                    }
                                    PD.dismiss();
                                }
                            });
                }
            }


    }
}
