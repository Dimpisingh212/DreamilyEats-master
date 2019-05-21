package com.example.dreamilyeats.NetworkConnectivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import static com.example.dreamilyeats.HomePage.cancelDialogBox;
import static com.example.dreamilyeats.HomePage.showDialogBox;
import static com.example.dreamilyeats.About_profile_activity.cancelProfileDialogBox;
import static com.example.dreamilyeats.About_profile_activity.showProfileDialogBox;
import static com.example.dreamilyeats.Cash_Payment_setting.cancelPaymentDialogBox;
import static com.example.dreamilyeats.Cash_Payment_setting.showPaymentDialogBox;
import static com.example.dreamilyeats.Edit_Profile_setting.cancelEdit_ProfileDialogBox;
import static com.example.dreamilyeats.Edit_Profile_setting.showEdit_ProfileDialogBox;
import static com.example.dreamilyeats.EditProfile.cancelEditProfileDialogBox;
import static com.example.dreamilyeats.EditProfile.showEditProfileDialogBox;
import static com.example.dreamilyeats.ForgetAndChangePasswordActivity.cancelForgotPassDialogBox;
import static com.example.dreamilyeats.ForgetAndChangePasswordActivity.showForgotPassDialogBox;
import static com.example.dreamilyeats.Help_Setting.cancelHelpDialogBox;
import static com.example.dreamilyeats.Help_Setting.showHelpDialogBox;
import static com.example.dreamilyeats.Next_MoreRestaurant.cancelNext_restDialogBox;
import static com.example.dreamilyeats.Next_MoreRestaurant.showNext_restDialogBox;
import static com.example.dreamilyeats.Payments_Setting.cancelPayment_SettingDialogBox;
import static com.example.dreamilyeats.Payments_Setting.showPayment_SettingDialogBox;
import static com.example.dreamilyeats.Promotion_Setting.cancelPromotionDialogBox;
import static com.example.dreamilyeats.Promotion_Setting.showPromotionDialogBox;
import static com.example.dreamilyeats.Settings_Activity.cancelSettingDialogBox;
import static com.example.dreamilyeats.Settings_Activity.showSettingDialogBox;
import static com.example.dreamilyeats.SignUpActivity.cancelSignUpDialogBox;
import static com.example.dreamilyeats.SignUpActivity.showSignUPDialogBox;
import static com.example.dreamilyeats.Your_Favourites.cancelFavouriteDialogBox;
import static com.example.dreamilyeats.Your_Favourites.showFavouriteDialogBox;


public class NetworkConnectionCheck extends BroadcastReceiver {

    private String TAG = "NetworkConnectionCheck";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG , "Internet onreceive") ;
        try
        {
            if (isOnline(context)) {
                Log.e(TAG , "Internet connect") ;
                cancelDialogBox();
                cancelProfileDialogBox();
                cancelPaymentDialogBox();
                cancelEdit_ProfileDialogBox();
                //cancelEditProfileDialogBox();
                cancelForgotPassDialogBox();
                cancelHelpDialogBox();
                cancelNext_restDialogBox();
                cancelPayment_SettingDialogBox();
                cancelPromotionDialogBox();
                cancelSettingDialogBox();
                cancelSignUpDialogBox();
                cancelFavouriteDialogBox();


            } else {
                Log.e(TAG , "Internet disconnect") ;
                showDialogBox();
                showProfileDialogBox();
                showPaymentDialogBox();
                showEdit_ProfileDialogBox();
                //showEditProfileDialogBox();
                showForgotPassDialogBox();
                showHelpDialogBox();
                showNext_restDialogBox();
                showPayment_SettingDialogBox();
                showPromotionDialogBox();
                showSettingDialogBox();
                showSignUPDialogBox();
                showFavouriteDialogBox();

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
