package com.example.dreamilyeats;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dreamilyeats.Model.MyItemArray;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ReciptAdapter extends RecyclerView.Adapter<ReciptAdapter.Viewholder> {

    private Context context;
    ArrayList<MyItemArray> myarray;
    final GlobalArray globalArray = (GlobalArray) getApplicationContext();

    public ReciptAdapter(Context context, ArrayList<MyItemArray> myarray) {
        this.context = context;
        this.myarray = myarray;
    }

    @NonNull
    @Override
    public ReciptAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_orders_design, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReciptAdapter.Viewholder viewholder, int i) {

        myarray = globalArray.getMyItemArrays();
        viewholder.num_dishes.setText(String.valueOf(myarray.get(i).getNumber_of_items()));
        viewholder.dishes_list.setText(myarray.get(i).getItem_name());
        viewholder.dishes_price.setText(myarray.get(i).getItem_price().toString());

    }

    @Override
    public int getItemCount() {
        return myarray.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView num_dishes, dishes_list, dishes_price;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            num_dishes = itemView.findViewById(R.id.num_dishes);
            dishes_list = itemView.findViewById(R.id.dishes_list);
            dishes_price = itemView.findViewById(R.id.dishes_price);
        }
    }
}
