package com.example.dreamilyeats;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Explore_Next_Restaurants extends AppCompatActivity {

    private RecyclerView recycler_view;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore__next__restaurants);

        back = findViewById(R.id.back);
        recycler_view = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(linearLayoutManager);

        ArrayList<MoreRestaurantsModel> arrayList = new ArrayList<>();
        arrayList.add(new MoreRestaurantsModel(R.drawable.slide2, "Topaz Restaurant", "Rs.North Indian.Chinese", "30-40 Min"));
        arrayList.add(new MoreRestaurantsModel(R.drawable.slide1, "Kanji Sweets", "Rs.Chinese", "20-30 Min"));
        arrayList.add(new MoreRestaurantsModel(R.drawable.slide5, "Cafe Kebabs", "Rs.Cafe.North Indian", "20-25 Min"));
        arrayList.add(new MoreRestaurantsModel(R.drawable.slide6, "Cafe Coffee Day - Vidhyadhar nagar", "Rs.Cafe.Coffee and Tea", "30-40 Min"));
        arrayList.add(new MoreRestaurantsModel(R.drawable.slider4, "Khandelwal Pavitra Bhojnalaya", "North Indian.Indian Curry", "20-30 Min"));



        //ExploreNextAdapter exploreNextAdapter = new ExploreNextAdapter(this, arrayList);
        //recycler_view.setAdapter(exploreNextAdapter);

        MoreRestaurantsAdapter moreRestaurantsAdapter = new MoreRestaurantsAdapter(this, arrayList);
        recycler_view.setAdapter(moreRestaurantsAdapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
