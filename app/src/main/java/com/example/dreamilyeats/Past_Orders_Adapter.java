package com.example.dreamilyeats;

import android.content.Context;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dreamilyeats.Model.PlaceOrderListModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Past_Orders_Adapter extends RecyclerView.Adapter<Past_Orders_Adapter.Viewholder> {

    Context context;
    ArrayList<PlaceOrderListModel> arrayList;
    final GlobalArray globalArray = (GlobalArray) getApplicationContext();
    private String TAG = "Past_Orders_Adapter =>";
    private  int actual_diff;

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa  dd:MMMM:yyyy");
    Date myTime = Calendar.getInstance().getTime();
    String currentTime = sdf.format(myTime);





    public Past_Orders_Adapter(Context context, final ArrayList<PlaceOrderListModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;


    }

    @NonNull
    @Override
    public Past_Orders_Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.past_orders_notification_design, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Past_Orders_Adapter.Viewholder viewholder, final int i) {
        arrayList = globalArray.getPlaceOrderListModels();
        Glide.with(context).load(arrayList.get(i).getHotel_image()).into(viewholder.food_image);
        viewholder.hotel_name.setText(arrayList.get(i).getHotel_name());
        viewholder.time_date.setText(arrayList.get(i).getTime_date());
        viewholder.total.setText(arrayList.get(i).getTotal_cost());

        Log.e(TAG , "total : " +arrayList.get(i).getTotal_cost());

        viewholder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setMessage("Remove item from Notification list");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        removeAt(i);

                        dialog.dismiss();


                    }
                });
                alertDialog.show();
            }
        });

        final String orderedTime = arrayList.get(i).getTime_date();
        Log.e(TAG , "OrderedTime :" +orderedTime);
        Log.e(TAG , "CurrentTime :" +currentTime);

        String o_time = orderedTime.substring(0 ,8);
        String c_time = currentTime.substring(0 ,8);
        Log.e(TAG , "substring of orderedTime :" +o_time);
        Log.e(TAG , "substring of CurrentTime :" +c_time);


        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            Date d1 = sdf.parse(o_time);
            Date d2 = sdf.parse(c_time);
            long difference = d2.getTime() - d1.getTime();


            int days = (int) (difference / (1000*60*60*24));
            int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
            int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
            int sec = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60*60);

            actual_diff = (int) (difference/1000);

            Log.e("======= Hours"," :: "+hours);
            Log.e("======= minutes"," :: "+min);
            Log.e("======= second"," :: "+sec);
            Log.e(TAG , " difference :: " +actual_diff );
        } catch (ParseException e) {
            e.printStackTrace();
        }




        if(actual_diff  > 50) {
            globalArray.newplaceOrderListModels.add(new PlaceOrderListModel(arrayList.get(i).getHotel_image(), arrayList.get(i).getHotel_name(), arrayList.get(i).getTime_date(), arrayList.get(i).getTotal_cost()));
            globalArray.placeOrderListModels.remove(i);
            //notifyItemRangeChanged(i, globalArray.placeOrderListModels.size());

            Log.e(TAG , "My New global array : : " +globalArray.newplaceOrderListModels);

        } else {

            Log.e(TAG, "Hello");
        }




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        CardView card_view;
        ImageView food_image;
        TextView hotel_name,time_date,total;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            card_view = itemView.findViewById(R.id.card_view);
            food_image = itemView.findViewById(R.id.food_image);
            hotel_name = itemView.findViewById(R.id.hotel_name);
            time_date = itemView.findViewById(R.id.time_date);
            total = itemView.findViewById(R.id.total);

        }
    }

    public void removeAt(int position) {
        arrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, arrayList.size());

    }




}
