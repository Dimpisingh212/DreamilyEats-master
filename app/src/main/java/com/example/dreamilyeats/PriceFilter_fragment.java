package com.example.dreamilyeats;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PriceFilter_fragment extends Fragment {

    public static PriceFilter_fragment createInstance() {
        return new PriceFilter_fragment();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_price_filter_fragment, container, false);
    }


}
