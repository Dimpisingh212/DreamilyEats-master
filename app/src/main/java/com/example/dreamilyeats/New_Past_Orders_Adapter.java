package com.example.dreamilyeats;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class New_Past_Orders_Adapter extends RecyclerView.Adapter<New_Past_Orders_Adapter.Viewholder> {

    Context context;
    ArrayList<PlaceOrderListModel> arrayList;
   // final GlobalArray globalArray = (GlobalArray) getApplicationContext();

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa  dd:MMMM:yyyy");
    Date myTime = Calendar.getInstance().getTime();
    String currentTime = sdf.format(myTime);
    SharedPreferences preferences;



    public New_Past_Orders_Adapter(Context context, ArrayList<PlaceOrderListModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public New_Past_Orders_Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.past_orders_notification_design, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull New_Past_Orders_Adapter.Viewholder viewholder, int i) {
        //arrayList = globalArray.getNewplaceOrderListModels();

        /*preferences = getApplicationContext().getSharedPreferences("Update_List" , Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("List", null);
        Type type = new TypeToken<List<PlaceOrderListModel>>(){}.getType();
        arrayList = gson.fromJson(json, type);

        Log.e("New_Past_Orders" , "Array :" +arrayList.toString());
        Log.e("New_Past_Orders" , "json :" +json);
*/

        if(arrayList != null && arrayList.size() > 0) {

            Glide.with(context).load(arrayList.get(i).getHotel_image()).into(viewholder.food_image);
            viewholder.hotel_name.setText(arrayList.get(i).getHotel_name());
            viewholder.time_date.setText(arrayList.get(i).getTime_date());
            viewholder.total.setText(arrayList.get(i).getTotal_cost());

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
}
