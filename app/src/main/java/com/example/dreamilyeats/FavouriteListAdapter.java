package com.example.dreamilyeats;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamilyeats.Model.FavouriteListArray;
import com.example.dreamilyeats.Model.MyItemArray;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FavouriteListAdapter extends RecyclerView.Adapter<FavouriteListAdapter.Viewholder> {

    Context context;
    ArrayList<FavouriteListArray> favouriteListArrays ;
    final GlobalArray globalArray = (GlobalArray) getApplicationContext();

    public FavouriteListAdapter(Context context, ArrayList<FavouriteListArray> favouriteListArrays) {
        this.context = context;
        this.favouriteListArrays = favouriteListArrays;
    }

    @NonNull
    @Override
    public FavouriteListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.favourite_list_design, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavouriteListAdapter.Viewholder viewholder, final int i) {
        favouriteListArrays = globalArray.getFavouriteListArrays();
        viewholder.fav_images.setImageResource(favouriteListArrays.get(i).getHotel_image());
        viewholder.hotel_name.setText(favouriteListArrays.get(i).getHotel_name());

        viewholder.fav_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setMessage("Remove item from Favourite list");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        removeAt(i);

                        dialog.dismiss();

                    }
                });
                alertDialog.show();

            }
        });

}

    @Override
    public int getItemCount() {
        return favouriteListArrays.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView fav_images;
        TextView hotel_name;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            fav_images = itemView.findViewById(R.id.fav_images);
            hotel_name = itemView.findViewById(R.id.hotel_name);
        }
    }


    public void removeAt(int position) {
            favouriteListArrays.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, favouriteListArrays.size());

}
}
