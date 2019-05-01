package com.example.dreamilyeats.NetworkConnectivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkConnectionCheck extends BroadcastReceiver {
    AlertDialog.Builder builder;
    Context c;
    private String TAG = "NetworkConnectionCheck";

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            if(isOnline(context)) {
                Log.e(TAG, "Online Connect Internet");


            } else {
                Log.e(TAG, "COnnection Failure !!");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }



    public static boolean isOnline(Context context1) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context1.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null.

            return (networkInfo != null && networkInfo.isConnected());

            } catch (NullPointerException e) {
               e.printStackTrace();

               return false;
        }
    }

}
