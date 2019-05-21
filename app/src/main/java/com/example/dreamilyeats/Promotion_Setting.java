package com.example.dreamilyeats;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck;

import static com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck.isOnline;

public class Promotion_Setting extends AppCompatActivity {

    ImageView back;

    public  AlertDialog.Builder builder;
    public static AlertDialog alertDialog;
    private NetworkConnectionCheck receiver = new NetworkConnectionCheck();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion__setting);

        back = findViewById(R.id.back);

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

        // Registers BroadcastReceiver to track network connection changes.
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkConnectionCheck();
        Promotion_Setting.this.registerReceiver(receiver, intentFilter);

    }

    public static void showPromotionDialogBox() {
        alertDialog.show();
    }

    public static void cancelPromotionDialogBox() {
        alertDialog.cancel();
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkConnectionCheck();
        Promotion_Setting.this.registerReceiver(receiver, intentFilter);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregisters BroadcastReceiver when app is destroyed.
        if (receiver != null) {
            this.unregisterReceiver(receiver);
        }
    }

    }
