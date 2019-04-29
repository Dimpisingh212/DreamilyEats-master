package com.example.dreamilyeats;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExploreNextAdapter extends RecyclerView.Adapter<ExploreNextAdapter.Viewholder> {

    Context context;
    ArrayList<MoreRestaurantsModel> arrayList;

    public ExploreNextAdapter(Context context, ArrayList<MoreRestaurantsModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ExploreNextAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.activity_more_restaurants_design, viewGroup, false);
        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreNextAdapter.Viewholder viewholder, int i) {

        viewholder.food_image.setImageResource(arrayList.get(i).getFood_image());
        viewholder.restaurant_name.setText(arrayList.get(i).getRestaurant_name());
        viewholder.dishes_type.setText(arrayList.get(i).getDishes_type());
        viewholder.grade.setText(arrayList.get(i).getGrade());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView food_image;
        TextView restaurant_name,dishes_type,grade;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            food_image = itemView.findViewById(R.id.food_image);
            restaurant_name = itemView.findViewById(R.id.restaurant_name);
            dishes_type = itemView.findViewById(R.id.dishes_type);
            grade = itemView.findViewById(R.id.grade);
        }
    }
}
