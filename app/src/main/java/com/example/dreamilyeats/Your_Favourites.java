package com.example.dreamilyeats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamilyeats.Model.FavouriteListArray;
import com.example.dreamilyeats.Model.MyItemArray;

import java.util.ArrayList;

public class Your_Favourites extends AppCompatActivity {

    private RecyclerView recycler_view;
    ArrayList<FavouriteListArray> favouriteListArrays = new ArrayList<>();
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your__favourites);

        back = findViewById(R.id.back);
        recycler_view = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(linearLayoutManager);

        final GlobalArray globalArray = (GlobalArray) getApplicationContext();
        favouriteListArrays = globalArray.getFavouriteListArrays();
        Log.e("FavouriteAdapter" , "my array :: " +favouriteListArrays);


        FavouriteListAdapter favouriteListAdapter = new FavouriteListAdapter(this, favouriteListArrays);
        recycler_view.setAdapter(favouriteListAdapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}
