package com.example.dreamilyeats;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dreamilyeats.Model.FavouriteListArray;
import com.example.dreamilyeats.Model.MyItemArray;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class PopularNearAdapter extends RecyclerView.Adapter<PopularNearAdapter.Viewholder> {
    Context context;
    ArrayList<PopularNear_Model> arrayList;
    boolean isplay = false;
    private final String TAG = "PopularNearAdapter=> ";
    private GlobalArray globalArray;
    // ArrayList<FavouriteListArray> favouriteListArrays = new ArrayList<>();


    public PopularNearAdapter(Context context, ArrayList<PopularNear_Model> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public PopularNearAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.activity_popular_near, viewGroup, false);

        globalArray = (GlobalArray) getApplicationContext();
        return  new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PopularNearAdapter.Viewholder viewholder, final int i) {

        Glide.with(context).load(arrayList.get(i).getOffer_images()).into(viewholder.offer_images);
        viewholder.hotel_name.setText(arrayList.get(i).getHotel_name());
        viewholder.price_for.setText(arrayList.get(i).getPrice_for());
        viewholder.time.setText(arrayList.get(i).getTime());
        viewholder.grade.setText(arrayList.get(i).getGrade());


        if (containsName(globalArray.favouriteListArrays,arrayList.get(i).getHotel_name())){
            viewholder.like.setBackgroundResource(R.drawable.purple_like);
        }else {
            viewholder.like.setBackgroundResource(R.drawable.like);
        }



        viewholder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalArray.hotel_name = arrayList.get(i).getHotel_name();
                GlobalArray.time = arrayList.get(i).getTime();

                Intent intent = new Intent(context, Next_MoreRestaurant.class);
                intent.putExtra("hotel_names", arrayList.get(i).getHotel_name());
                context.startActivity(intent);


            }
        });

        viewholder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(isplay) {
                    v.setBackgroundResource(R.drawable.like);

                    if (containsName(globalArray.favouriteListArrays,arrayList.get(i).getHotel_name())) {
                        globalArray.favouriteListArrays.remove(i);
                        notifyItemRangeChanged(i, globalArray.favouriteListArrays.size());


                        Toast.makeText(globalArray, "Restaurent removed succesfully", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onClick: "+globalArray.favouriteListArrays );

                    } else {
                        Toast.makeText(globalArray, "Restaurent is not added", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    v.setBackgroundResource(R.drawable.purple_like);

                    if (containsName(globalArray.favouriteListArrays,arrayList.get(i).getHotel_name())){
                        Toast.makeText(globalArray, "Restaurent already added", Toast.LENGTH_SHORT).show();
                    }else {

                        Toast.makeText(globalArray, "Restaurent added succesfully", Toast.LENGTH_SHORT).show();
                        globalArray.favouriteListArrays.add(new FavouriteListArray(arrayList.get(i).getOffer_images(), arrayList.get(i).getHotel_name()));
                        Log.e(TAG, "onClick: "+globalArray.favouriteListArrays );
                    }
                }

                isplay = !isplay;
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView offer_images,like;
        TextView hotel_name,price_for,time,grade;
        CardView card_view;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            offer_images = itemView.findViewById(R.id.offer_images);
            hotel_name = itemView.findViewById(R.id.hotel_name);
            price_for = itemView.findViewById(R.id.price_for);
            time = itemView.findViewById(R.id.time);
            grade = itemView.findViewById(R.id.grade);
            card_view = itemView.findViewById(R.id.card_view);
            like = itemView.findViewById(R.id.likeimage);
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
