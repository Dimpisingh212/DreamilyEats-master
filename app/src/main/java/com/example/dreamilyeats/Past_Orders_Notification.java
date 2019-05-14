package com.example.dreamilyeats;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamilyeats.Model.PlaceOrderListModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


public class Past_Orders_Notification extends Fragment {

    private RecyclerView recycler_view;
    private CardView card_view;
    private ImageView food_image;
    private TextView hotel_name;
    private ArrayList<PlaceOrderListModel> arrayList;
    SharedPreferences preferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_past__orders__notification, container, false);

        card_view = view.findViewById(R.id.card_view);
        food_image = view.findViewById(R.id.food_image);
        hotel_name = view.findViewById(R.id.hotel_name);
        recycler_view = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(linearLayoutManager);

        final GlobalArray globalArray = (GlobalArray) getActivity().getApplicationContext();
        //arrayList = globalArray.newplaceOrderListModels;
        preferences = getApplicationContext().getSharedPreferences("Update_List" , Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("List", null);
        Type type = new TypeToken<List<PlaceOrderListModel>>(){}.getType();
        arrayList = gson.fromJson(json, type);

        New_Past_Orders_Adapter new_past_orders_adapter = new New_Past_Orders_Adapter(getActivity(), arrayList);
        recycler_view.setAdapter(new_past_orders_adapter);




        return view;
    }

}
