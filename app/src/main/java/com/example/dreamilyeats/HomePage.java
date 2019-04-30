package com.example.dreamilyeats;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    private BottomNavigationView bottom_navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        firebaseAuth = FirebaseAuth.getInstance();

        loadFragment(new Home_Fragment());

        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(this);


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
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
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
}
