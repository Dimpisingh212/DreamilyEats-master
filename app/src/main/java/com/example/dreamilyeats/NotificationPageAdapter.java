package com.example.dreamilyeats;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

class NotificationPageAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public NotificationPageAdapter(FragmentManager childFragmentManager, int numOfTabs) {
        super(childFragmentManager);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                Past_Orders_Notification past_orders_notification = new Past_Orders_Notification();
                return past_orders_notification;

            case 1:
                UpComing_Notification upComing_notification = new UpComing_Notification();
                return upComing_notification;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
