package com.example.dreamilyeats;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewAddress extends AppCompatActivity {

    private TextView current_location,change,add_address;
    private TextView save;
    EditText editText;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_address);

        current_location = findViewById(R.id.current_address);
        change = findViewById(R.id.change);
        save = findViewById(R.id.save);
        back = findViewById(R.id.back);
        editText = findViewById(R.id.editText);
        add_address = findViewById(R.id.add_address);


        SharedPreferences sharedPreferences1 = ViewAddress.this.getSharedPreferences("ADDRESS", MODE_PRIVATE);
        String current_address = sharedPreferences1.getString("address", null);
        current_location.setText(current_address);


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Add_Map_fragment add_map_fragment = new Add_Map_fragment();
                transaction.replace(R.id.constraint_layout1, add_map_fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        String current_address1 = sharedPreferences1.getString("address", null);
        current_location.setText(current_address1);




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_address.setText(editText.getText().toString());
                editText.getText().clear();

            }
        });


        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(ViewAddress.this).create();
                alertDialog.setMessage("Remove item from Favourite list");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        add_address.setText("Add Address");
                        add_address.setTextColor(Color.BLACK);

                        dialog.dismiss();

                    }
                });
                alertDialog.show();

            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
