package com.example.dreamilyeats;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FilterPageAdapter extends FragmentStatePagerAdapter {

    int numofTabs;

    public FilterPageAdapter(FragmentManager fm, int numofTabs) {
        super(fm);
        this.numofTabs=numofTabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                SortFilter_Fragment sortFragment = new SortFilter_Fragment();
                return sortFragment;

            case 1:
                PriceFilter_fragment priceFragment = new PriceFilter_fragment();
                return priceFragment;

            case 2:
                DietaryFilter_Fragment dietaryFilterFragment = new DietaryFilter_Fragment();
                return dietaryFilterFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numofTabs;
    }
}
