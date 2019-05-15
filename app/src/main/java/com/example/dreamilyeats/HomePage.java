package com.example.dreamilyeats;

import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NetworkConnectionCheck.ConnectivityReceiverListener {

    private Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    private BottomNavigationView bottom_navigation;


    public  AlertDialog.Builder builder;
    public static AlertDialog alertDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        firebaseAuth = FirebaseAuth.getInstance();
        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(this);

        if (GlobalArray.flag){
            GlobalArray.flag = false;

            bottom_navigation.getMenu().getItem(2).setChecked(true);
            loadFragment(new Notification_Fragment());
        }

        else {

            loadFragment(new Home_Fragment());
        }


        builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Alert");
        builder.setMessage("Network Connection off!!").setCancelable(false);
        alertDialog = builder.create();


        // Manually checking internet connection
        checkConnection();

    }

    private void checkConnection() {
        boolean isConnected = NetworkConnectionCheck.isConnected();
        showToast(isConnected);
    }

    private void showToast(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Toast.makeText(HomePage.this, " "+message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        GlobalArray.getInstance().setConnectivityListener(this);
    }


    public static void showDialogBox() {
       alertDialog.show();

    }

    public static void cancelDialogBox() {
     alertDialog.cancel();
    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).commit();
            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.home:
                fragment = new Home_Fragment();
                break;

            case R.id.deshboard:
                fragment = new Explore_Fragment();
                break;

            case R.id.notification:
                fragment = new Notification_Fragment();
                break;

            case R.id.profile:
                fragment = new Profile_Fragment();
                break;
        }

        return loadFragment(fragment);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       HomePage.super.onBackPressed();
                        Intent start = new Intent(Intent.ACTION_MAIN);
                        start.addCategory(Intent.CATEGORY_HOME);
                        start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(start);
                    }
                }).setNegativeButton("No", null).show();

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showToast(isConnected);
    }
}
