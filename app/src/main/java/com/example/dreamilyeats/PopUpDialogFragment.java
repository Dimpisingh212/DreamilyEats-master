package com.example.dreamilyeats;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class PopUpDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View dialogview = LayoutInflater.from(getContext()).inflate(R.layout.activity_home__filter__custom__window,container,false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(dialogview);
        final AlertDialog alertDialog = builder.create();

        ImageView back = dialogview.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        TabLayout tabLayout = dialogview.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("CHATS"));
        tabLayout.addTab(tabLayout.newTab().setText("STATUS"));
        tabLayout.addTab(tabLayout.newTab().setText("CALLS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = dialogview.findViewById(R.id.pager);
        final FilterPageAdapter adapter = new FilterPageAdapter(getChildFragmentManager(), tabLayout.getTabCount());
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


        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Window dialogWindow = alertDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);

        lp.x = 100; // The new position of the X coordinates
        lp.y = 100; // The new position of the Y coordinates
        lp.width = 400; // Width
        lp.height = 300;

        dialogWindow.setAttributes(lp);

        return  dialogview;

    }
}
