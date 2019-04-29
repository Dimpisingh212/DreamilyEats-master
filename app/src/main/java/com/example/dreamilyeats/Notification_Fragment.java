package com.example.dreamilyeats;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Notification_Fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification_, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("PAST ORDERS"));
        tabLayout.addTab(tabLayout.newTab().setText("UPCOMING"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = view.findViewById(R.id.pager);
        final NotificationPageAdapter adapter = new NotificationPageAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.i("tabs","tab selected");

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                Log.i("tabs","tab unselected");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                Log.i("tabs","tab Reselected");
            }
        });



        return view;
    }

}
