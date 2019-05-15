package com.example.dreamilyeats;

import android.app.Application;

import com.example.dreamilyeats.Model.FavouriteListArray;
import com.example.dreamilyeats.Model.MyItemArray;
import com.example.dreamilyeats.Model.PlaceOrderListModel;
import com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck;

import java.util.ArrayList;



public class GlobalArray extends Application {

    public static String hotel_name;
    public static String time;

    //For Broadcast Receeiver Internet checking :
    private static GlobalArray mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }


    public static synchronized GlobalArray getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(NetworkConnectionCheck.ConnectivityReceiverListener listener) {
        NetworkConnectionCheck.connectivityReceiverListener = listener;
    }


    public ArrayList<MyItemArray> myItemArrays = new ArrayList<>();

    public ArrayList<FavouriteListArray> favouriteListArrays = new ArrayList<>();

    public ArrayList<PlaceOrderListModel> placeOrderListModels = new ArrayList<>();

    public ArrayList<PlaceOrderListModel> newplaceOrderListModels = new ArrayList<>();




    public ArrayList<MyItemArray> getMyItemArrays() {
        return myItemArrays;
    }

    public ArrayList<FavouriteListArray> getFavouriteListArrays() {
        return favouriteListArrays;
    }

    public ArrayList<PlaceOrderListModel> getPlaceOrderListModels() {
        return placeOrderListModels;
    }

    public ArrayList<PlaceOrderListModel> getNewplaceOrderListModels() {
        return newplaceOrderListModels;
    }


    public static boolean flag = false;
}



