package com.example.dreamilyeats.NetworkConnectivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.dreamilyeats.GlobalArray;

import static com.example.dreamilyeats.HomePage.showDialogBox;
import static com.example.dreamilyeats.HomePage.cancelDialogBox;

public class NetworkConnectionCheck extends BroadcastReceiver {

    public static ConnectivityReceiverListener connectivityReceiverListener;

    private String TAG = "NetworkConnectionCheck";

    @Override
    public void onReceive(Context context, Intent intent) {

       /* try {
            if(isOnline(context)) {
                Log.e(TAG, "Online Connect Internet");

                cancelDialogBox();

            } else {

                showDialogBox();
                Log.e(TAG, "Connection Failure !!");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
*/

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (connectivityReceiverListener != null) {
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }

    }

    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) GlobalArray.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
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


    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }

}
