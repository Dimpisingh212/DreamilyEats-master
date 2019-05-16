package com.example.dreamilyeats.NetworkConnectivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;



import com.example.dreamilyeats.GlobalArray;

import static com.example.dreamilyeats.HomePage.cancelDialogBox;
import static com.example.dreamilyeats.HomePage.showDialogBox;

public class NetworkConnectionCheck extends BroadcastReceiver {

    //public static ConnectivityReceiverListener connectivityReceiverListener;

    private String TAG = "NetworkConnectionCheck";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG , "Internet onreceive") ;
        try
        {
            if (isOnline(context)) {
                Log.e(TAG , "Internet connect") ;
                cancelDialogBox();

            } else {
                Log.e(TAG , "Internet disconnect") ;
                showDialogBox();
            }

    } catch (NullPointerException e) {
            e.printStackTrace();
        }



       /* ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (connectivityReceiverListener != null) {
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }*/

    }

  /*  public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) GlobalArray.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }*/


    public static boolean isOnline(Context context1) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context1.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null.

          //  Log.e("isonline","true");
            return (networkInfo != null && networkInfo.isConnected());

            } catch (NullPointerException e) {
               e.printStackTrace();

            //Log.e("isonline","false");
               return false;
        }
    }




  /*  public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
*/
}
