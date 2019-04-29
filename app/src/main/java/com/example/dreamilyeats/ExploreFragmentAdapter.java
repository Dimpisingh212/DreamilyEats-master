package com.example.dreamilyeats;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dreamilyeats.Model.ExploreFragmentModel;
import com.example.dreamilyeats.Model.ParentModel;

import java.util.ArrayList;

public class ExploreFragmentAdapter extends RecyclerView.Adapter<ExploreFragmentAdapter.Viewholder>  {

    Context context;
    ArrayList<ExploreFragmentModel> arrayList;

    public ExploreFragmentAdapter(Context context, ArrayList<ExploreFragmentModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ExploreFragmentAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.explore_dishes_categroies_design, viewGroup , false);
        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExploreFragmentAdapter.Viewholder viewholder, int i) {

        Glide.with(context).load(arrayList.get(i).getFood_image()).into(viewholder.food_image);
        viewholder.food_type_name.setText(arrayList.get(i).getFood_type_name());
        viewholder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewholder.getAdapterPosition();
                Log.e("position :" , " :::" +pos);

                Intent intent = new Intent(context, Explore_Next_Restaurants.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }




    public void filterlist(ArrayList<ExploreFragmentModel> filterNames) {

        this.arrayList = filterNames;
        notifyDataSetChanged();
    }





    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView food_image;
        TextView food_type_name;
        CardView card_view;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            food_image = itemView.findViewById(R.id.food_image);
            food_type_name = itemView.findViewById(R.id.food_type_name);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
