package com.example.dreamilyeats;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamilyeats.InterfaceForEmailChecking.OnEmailCheckListener;
import com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.gson.JsonObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private SignInButton sign_in_button;
    private LoginButton login_button;
    private GoogleSignInClient googleSignInClient;
    private String TAG = "LoginActivity";
    private FirebaseAuth firebaseAuth;
    private static int RC_SIGN_IN = 1;
    private CallbackManager callbackManager;
    private EditText e_id, pass;
    private Button button,fb;
    private TextView signup,fpass;
    private ProgressDialog PD;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();

        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);


       /* try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.dreamilyeats",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/

      //  LoginManager.getInstance().logOut();




        sign_in_button = findViewById(R.id.sign_in_button);
        login_button = findViewById(R.id.login_button);
        e_id = findViewById(R.id.e_id);
        pass = findViewById(R.id.pass);
        button = findViewById(R.id.button);
        signup = findViewById(R.id.signup);
        fpass = findViewById(R.id.fpass);
        fb = findViewById(R.id.fb);



        e_id.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                e_id.setFocusable(true);
                e_id.setFocusableInTouchMode(true);

                return false;
            }
        });

        pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pass.setFocusable(true);
                pass.setFocusableInTouchMode(true);

                return false;
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("323497170435-79bulh6chdsk4ov5hme1r0h47gk33i4g.apps.googleusercontent.com").requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = e_id.getText().toString();
                final String password = pass.getText().toString();

                try {

                    if((NetworkConnectionCheck.isOnline(LoginActivity.this))) {
                        Log.e(TAG, "Network Connection on");
                        if (password.length() > 0 && email.length() > 0) {
                            PD.show();
                            isCheckEmail(email, new OnEmailCheckListener() {
                                @Override
                                public void onSucess(boolean isRegistered) {

                                    if (isRegistered) {
                                        Log.e(TAG, "Successful");
                                        createAccount(email, password);
                                    } else {
                                        Log.e(TAG, "There haven't any account by using this email Plz register before");
                                        Toast.makeText(LoginActivity.this, "There haven't any account by using this email Plz register before!!!", Toast.LENGTH_LONG).show();
                                        PD.dismiss();
                                    }

                                }
                            });

                        } else {
                            Toast.makeText(LoginActivity.this, "Fill All Fields", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Plz Turn On your Data Connection.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetAndChangePasswordActivity.class);
                startActivity(intent);
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == fb) {
                    if(NetworkConnectionCheck.isOnline(LoginActivity.this)) {
                        login_button.performClick();
                    } else {
                        Toast.makeText(LoginActivity.this, "Plz Turn On your Data Connection.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


            login_button.setReadPermissions("email", "public_profile");
            login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.e(TAG, "OnSuccess :" + loginResult);
                    handleFacebookACcessToken(loginResult.getAccessToken());


                }


                @Override
                public void onCancel() {
                    Log.e(TAG, "OnCancel");
                }

                @Override
                public void onError(FacebookException error) {
                    Log.e(TAG, "OnError");
                }
            });



    }

    private void handleFacebookACcessToken(AccessToken accessToken) {
        Log.e(TAG, "handleFacebookAccessToken :" + accessToken);

        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.e(TAG, "Login Success");
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    Log.e(TAG, "User Id :" + firebaseUser.getEmail());
                    Log.e(TAG, "User name :" + firebaseUser.getDisplayName());
                    Log.e(TAG, "User phone number :" + firebaseUser.getPhoneNumber());
                    Log.e(TAG, "User Pic :" + firebaseUser.getPhotoUrl());


                    /*JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("name",firebaseUser.getDisplayName());
                    jsonObject.addProperty("email_id",firebaseUser.getEmail());
                    jsonObject.addProperty("phone_number",firebaseUser.getPhoneNumber());
                    jsonObject.addProperty("profile_pic", String.valueOf(firebaseUser.getPhotoUrl()));

                    SharedPreferences.Editor editor_record = getSharedPreferences("User_record" , MODE_PRIVATE).edit();
                    editor_record.putString("my_record" , String.valueOf(jsonObject));
                    editor_record.commit();*/


                    Toast.makeText(LoginActivity.this, "Successful", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(LoginActivity.this, HomePage.class);
                    startActivity(intent);


                    String username = firebaseUser.getDisplayName();
                    String email = firebaseUser.getEmail();
                    Log.e(TAG, "Facebook :" + username + "   " + email);

                    updateUI(firebaseUser);

                } else {
                    Log.e(TAG, "Creation failed");
                    updateUI(null);

                }
            }
        });


    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(LoginActivity.this, HomePage.class);
            startActivity(intent);
        }
    }

    private void updateUI(FirebaseUser currentUser) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                if(NetworkConnectionCheck.isOnline(LoginActivity.this)) {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account);
                } else {
                    Toast.makeText(LoginActivity.this, "Plz Turn On your Data Connection.", Toast.LENGTH_LONG).show();
                }
            } catch (ApiException e) {
                Log.e(TAG, "Google sign in failed" + e);
                updateUI(null);
            }
        }

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        String Id = account.getId();
        String user_email = account.getEmail();
        String user_name = account.getDisplayName();


        Log.e(TAG, "google_id:" + account.getId());
        Log.e(TAG, "google_email:" + account.getEmail());
        Log.e(TAG, "google_name:" + account.getDisplayName());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Log.e(TAG, "signInWithCredential:success");
                            updateUI(user);


                            Intent intent = new Intent(LoginActivity.this, HomePage.class);
                            startActivity(intent);


                        } else {
                            Log.e(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "aunthentication failure", Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
    }




    private void createAccount(String email, String password) {

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                            Log.e("error", task.getResult().toString());
                        } else {


                            String userName = String.valueOf(task.getResult().getUser().getDisplayName()

                            );
                            Log.e(TAG, "userName: "+userName );
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        PD.dismiss();
                    }
                });
    }


    public void isCheckEmail(final String email,final OnEmailCheckListener listener){
        firebaseAuth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>()
        {
            @Override
            public void onComplete(@NonNull Task<ProviderQueryResult> task)
            {
                boolean check = !task.getResult().getProviders().isEmpty();

                listener.onSucess(check);

            }
        });

    }


}

