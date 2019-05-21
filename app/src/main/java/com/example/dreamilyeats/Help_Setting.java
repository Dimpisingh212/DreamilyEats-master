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

public class Help_Setting extends AppCompatActivity {

    ImageView  back;
    LinearLayout layout1,layout2,past_order_layout;

    public  AlertDialog.Builder builder;
    public static AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__setting);


        back = findViewById(R.id.back);
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        past_order_layout = findViewById(R.id.past_order_layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Help_Setting.this, Cash_Payment_setting.class);
                startActivity(intent);
            }
        });

        past_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Help_Setting.this, HomePage.class);

                GlobalArray.flag = true ;
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

    public static void showHelpDialogBox() {
        alertDialog.show();
    }

    public static void cancelHelpDialogBox() {
        alertDialog.cancel();
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        Help_Setting.this.registerReceiver(new NetworkConnectionCheck(), intentFilter);

    }

}
