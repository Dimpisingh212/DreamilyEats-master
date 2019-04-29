package com.example.dreamilyeats;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dreamilyeats.Model.ChildModel;
import com.example.dreamilyeats.Model.FavouriteListArray;
import com.example.dreamilyeats.Model.ParentModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MoreRestaurantsAdapter extends RecyclerView.Adapter<MoreRestaurantsAdapter.Viewholder> {

    Context context;
    ArrayList<MoreRestaurantsModel> arrayList2;
    boolean isplay = false;
    ArrayList<FavouriteListArray> favouriteListArrays = new ArrayList<>();
    private String TAG = "MoreRestaurantsAdapter =>";
    final GlobalArray globalArray = (GlobalArray) getApplicationContext();



    public MoreRestaurantsAdapter(Context context, ArrayList<MoreRestaurantsModel> arrayList2) {
        this.context=context;
        this.arrayList2=arrayList2;
    }

    @NonNull
    @Override
    public MoreRestaurantsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.activity_more_restaurants_design, viewGroup, false);
        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoreRestaurantsAdapter.Viewholder viewholder, final int i) {

        Glide.with(context).load(arrayList2.get(i).getFood_image()).into(viewholder.food_image);
        viewholder.restaurant_name.setText(arrayList2.get(i).getRestaurant_name());
        viewholder.dishes_type.setText(arrayList2.get(i).getDishes_type());
        viewholder.grade.setText(arrayList2.get(i).getGrade());


        if (containsName(globalArray.favouriteListArrays,arrayList2.get(i).getRestaurant_name())){
            viewholder.like.setBackgroundResource(R.drawable.purple_like);
        }else {
            viewholder.like.setBackgroundResource(R.drawable.like);
        }



        viewholder.whole_layout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               GlobalArray.hotel_name=arrayList2.get(i).getRestaurant_name();
               GlobalArray.time= arrayList2.get(i).getGrade();

               Intent intent = new Intent(context , Next_MoreRestaurant.class);
               intent.putExtra("restaurant_name", arrayList2.get(i).getRestaurant_name());
               context.startActivity(intent);
           }
       });

        viewholder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isplay) {
                    v.setBackgroundResource(R.drawable.like);

                    if (containsName(globalArray.favouriteListArrays,arrayList2.get(i).getRestaurant_name())) {
                        globalArray.favouriteListArrays.remove(i);
                        notifyItemRangeChanged(i, globalArray.favouriteListArrays.size());


                        Toast.makeText(globalArray, "Restaurent removed succesfully", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onClick: "+globalArray.favouriteListArrays );

                    } else {
                        Toast.makeText(globalArray, "Restaurent is not added", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    v.setBackgroundResource(R.drawable.purple_like);

                    if (containsName(globalArray.favouriteListArrays,arrayList2.get(i).getRestaurant_name())){
                        Toast.makeText(globalArray, "Restaurent already added", Toast.LENGTH_SHORT).show();
                    }else {

                        Toast.makeText(globalArray, "Restaurent added succesfully", Toast.LENGTH_SHORT).show();
                        globalArray.favouriteListArrays.add(new FavouriteListArray(arrayList2.get(i).getFood_image(), arrayList2.get(i).getRestaurant_name()));
                        Log.e(TAG, "onClick: "+globalArray.favouriteListArrays );
                    }
                }

                isplay = !isplay;
            }
        });




    }

    @Override
    public int getItemCount() {
        return arrayList2.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView food_image,like;
        TextView restaurant_name,dishes_type,grade;
        ConstraintLayout whole_layout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            food_image = itemView.findViewById(R.id.food_image);
            restaurant_name = itemView.findViewById(R.id.restaurant_name);
            dishes_type = itemView.findViewById(R.id.dishes_type);
            grade = itemView.findViewById(R.id.grade);
            whole_layout = itemView.findViewById(R.id.whole_layout);
            like = itemView.findViewById(R.id.like);





        }
    }


    private static boolean containsName(final List<FavouriteListArray> favouriteListArrays, final String hotelName) {
        for (final FavouriteListArray favouriteListArray : favouriteListArrays) {
            if (favouriteListArray.getHotel_name().equals(hotelName)) {
                return true;
            }
        }

        return false;
    }



}


