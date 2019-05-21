package com.example.dreamilyeats;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck;

import static com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck.isOnline;

public class Payments_Setting extends AppCompatActivity {

    ImageView back;
    LinearLayout layout;

    public  AlertDialog.Builder builder;
    public static AlertDialog alertDialog;
    private  NetworkConnectionCheck receiver = new NetworkConnectionCheck();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments__setting);

        back = findViewById(R.id.back);
        layout = findViewById(R.id.layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payments_Setting.this, Cash_Payment_setting.class);
                startActivity(intent);
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

    public static void showPayment_SettingDialogBox() {
        alertDialog.show();
    }

    public static void cancelPayment_SettingDialogBox() {
        alertDialog.cancel();
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        receiver = new NetworkConnectionCheck();
        Payments_Setting.this.registerReceiver(receiver, intentFilter);

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
