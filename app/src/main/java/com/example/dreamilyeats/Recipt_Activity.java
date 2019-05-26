package com.example.dreamilyeats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamilyeats.Model.MyItemArray;

import java.util.ArrayList;

public class Recipt_Activity extends AppCompatActivity {

    private TextView restaurant_name,total_price;
    private RecyclerView recycler_view;
    private String TAG = "Recipt_Activity";
    private ImageView close;
    ArrayList<MyItemArray> myarray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipt_);

        restaurant_name = findViewById(R.id.restaurant_name);
        recycler_view = findViewById(R.id.recycler_view);
        total_price = findViewById(R.id.total_price);
        close = findViewById(R.id.close);

        restaurant_name.setText(GlobalArray.hotel_name);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(linearLayoutManager);
        recycler_view.setNestedScrollingEnabled(false);

        //use global array :
        final GlobalArray globalArray = (GlobalArray) getApplicationContext();
        myarray = globalArray.getMyItemArrays();
        Log.e(TAG, "List Item Array :" +myarray.toString());

        ReciptAdapter reciptAdapter = new ReciptAdapter(this,myarray);
        recycler_view.setAdapter(reciptAdapter);

        SharedPreferences charge = getSharedPreferences("Total_charge", MODE_PRIVATE);
        String bill = charge.getString("total_charge", null);

        total_price.setText(bill);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Recipt_Activity.this, HomePage.class);
                startActivity(intent);
            }
        });

    }
}
