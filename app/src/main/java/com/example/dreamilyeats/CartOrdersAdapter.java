package com.example.dreamilyeats;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamilyeats.Model.MyItemArray;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;


public class CartOrdersAdapter extends RecyclerView.Adapter<CartOrdersAdapter.Viewholder> {

    Your_Cart context;
    ArrayList<MyItemArray> myarray;
    final GlobalArray globalArray = (GlobalArray) getApplicationContext();

    public CartOrdersAdapter(Your_Cart context, ArrayList<MyItemArray> myarray) {
        this.context = context;
        this.myarray = myarray;
    }

    @NonNull
    @Override
    public CartOrdersAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_orders_design, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartOrdersAdapter.Viewholder viewholder, final int i) {

        myarray = globalArray.getMyItemArrays();
        viewholder.num_dishes.setText(String.valueOf(myarray.get(i).getNumber_of_items()));
        viewholder.dishes_list.setText(myarray.get(i).getItem_name());
        viewholder.dishes_price.setText(myarray.get(i).getItem_price().toString());

        viewholder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setMessage("Remove item from cart");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "remove", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            public void onClick(DialogInterface dialog, int which) {
                                removeAt(i);
                                double removable_price = Double.parseDouble(viewholder.dishes_price.getText().toString());
                                Log.e("remove_item_price  : ", " " +removable_price);
                                context.update_total(removable_price);

                                dialog.dismiss();

                            }
                        });
                alertDialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return myarray.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView num_dishes, dishes_list, dishes_price;
        ImageView remove;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            num_dishes = itemView.findViewById(R.id.num_dishes);
            dishes_list = itemView.findViewById(R.id.dishes_list);
            dishes_price = itemView.findViewById(R.id.dishes_price);
            remove = itemView.findViewById(R.id.remove);
        }
    }


    public void removeAt(int position) {
        myarray.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, myarray.size());



    }
}