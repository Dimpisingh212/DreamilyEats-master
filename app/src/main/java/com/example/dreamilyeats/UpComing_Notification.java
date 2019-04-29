package com.example.dreamilyeats;

import android.content.Context;
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

import java.util.ArrayList;

public class UpComing_Notification extends Fragment {

    private RecyclerView recycler_view;
    private CardView card_view;
    private ImageView food_image;
    private TextView hotel_name;
    private ArrayList<PlaceOrderListModel> arrayList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming__notification, container, false);


        card_view = view.findViewById(R.id.card_view);
        food_image = view.findViewById(R.id.food_image);
        hotel_name = view.findViewById(R.id.hotel_name);
        recycler_view = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(linearLayoutManager);

        final GlobalArray globalArray = (GlobalArray) getActivity().getApplicationContext();
        arrayList = globalArray.getPlaceOrderListModels();

        Past_Orders_Adapter past_orders_adapter = new Past_Orders_Adapter(getActivity(), arrayList);
        recycler_view.setAdapter(past_orders_adapter);



        return view;
    }


}
