package com.example.dreamilyeats;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck.isOnline;

public class ForgetAndChangePasswordActivity extends AppCompatActivity {

    EditText email_id;
    Button button;
    private ProgressDialog PD;
    private FirebaseAuth auth;
    TextInputLayout label;
    TextView change_pass;
    private String emailId;

    public  AlertDialog.Builder builder;
    public static AlertDialog alertDialog;

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




            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  callFunction(mode);
                    emailId = email_id.getText().toString();
                    auth.sendPasswordResetEmail(emailId).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Intent intent = new Intent(ForgetAndChangePasswordActivity.this, LoginActivity.class);
                                Toast.makeText(ForgetAndChangePasswordActivity.this, "check your email id." , Toast.LENGTH_LONG).show();
                                startActivity(intent);
                            }
                        }
                    });
                }
            });


        builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Alert");
        builder.setMessage("Network Connection off!!").setCancelable(false);
        alertDialog = builder.create();

        if (!isOnline(this)){

            alertDialog.show();
        }

}



    public static void showForgotPassDialogBox() {
        alertDialog.show();
    }

    public static void cancelForgotPassDialogBox() {
        alertDialog.cancel();
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        ForgetAndChangePasswordActivity.this.registerReceiver(new NetworkConnectionCheck(), intentFilter);

    }

}
