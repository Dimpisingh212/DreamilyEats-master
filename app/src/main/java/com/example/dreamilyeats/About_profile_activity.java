package com.example.dreamilyeats;

import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck;

import static com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck.isOnline;

public class About_profile_activity extends AppCompatActivity {

    ImageView back;
    public  AlertDialog.Builder builder;
    public static AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_profile_activity);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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

    public static void showProfileDialogBox() {
        alertDialog.show();
    }

    public static void cancelProfileDialogBox() {
        alertDialog.cancel();
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        About_profile_activity.this.registerReceiver(new NetworkConnectionCheck(), intentFilter);

    }

}
