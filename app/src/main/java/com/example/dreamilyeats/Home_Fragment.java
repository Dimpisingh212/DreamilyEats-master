package com.example.dreamilyeats;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dreamilyeats.Model.ChildModel;
import com.example.dreamilyeats.Model.ParentModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;

public class Home_Fragment extends Fragment {

    private ViewPager pager;
    private ImageView filter;
    private ArrayList<String> photosUrl = new ArrayList<>();
    private TextView address;
    private RecyclerView recycler_view,recycler_view1,recycler_view2;
    private View view;
    private ConstraintLayout linearLayout;
    private int currentPage = 0;
    private Timer timer;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_, container, false);

        pager = (ViewPager) view.findViewById(R.id.photos_viewpager);
        PagerAdapter adapter = new PhotosHomeAdapter(getChildFragmentManager(), photosUrl, getActivity());        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager, true);




        filter = view.findViewById(R.id.filter);
        address = view.findViewById(R.id.address);
        linearLayout = view.findViewById(R.id.linearLayout);
        /*filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new PopUpDialogFragment().show(getChildFragmentManager(), PopUpDialogFragment.class.getSimpleName());

            }
        });*/

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == 8-1) {
                    currentPage = 0;
                }
                pager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 2000);


        recycler_view = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(linearLayoutManager);

        ArrayList<PopularNear_Model> arrayList = new ArrayList<>();
        arrayList.add(new PopularNear_Model(R.drawable.offer_image1, "Agarwal Caterers-Vaishali Nagar", "Rs.Indian", "30-40 Min", "4.2"));
        arrayList.add(new PopularNear_Model(R.drawable.offers_image2, "Thaggu ke Samose", "Rs.Fast Food.North Indian", "30-40 Min", "4.3"));
        arrayList.add(new PopularNear_Model(R.drawable.offers_image_4, "Agarwal Caterers-Shastri Nagar", "Rs.Indian", "25-35 Min", "4.4"));

        PopularNearAdapter popularNearAdapter = new PopularNearAdapter(getContext(), arrayList);
        recycler_view.setAdapter(popularNearAdapter);



        recycler_view2 = view.findViewById(R.id.recycler_view2);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        recycler_view2.setLayoutManager(linearLayoutManager2);

        ArrayList<MoreRestaurantsModel> arrayList2 = new ArrayList<>();
        arrayList2.add(new MoreRestaurantsModel(R.drawable.slide2, "Topaz Restaurant", "Rs.North Indian.Chinese", "30-40 Min"));
        arrayList2.add(new MoreRestaurantsModel(R.drawable.slide1, "Kanji Sweets", "Rs.Chinese", "20-30 Min"));
        arrayList2.add(new MoreRestaurantsModel(R.drawable.slide5, "Cafe Kebabs", "Rs.Cafe.North Indian", "20-25 Min"));
        arrayList2.add(new MoreRestaurantsModel(R.drawable.slide6, "Cafe Coffee Day - Vidhyadhar nagar", "Rs.Cafe.Coffee and Tea", "30-40 Min"));
        arrayList2.add(new MoreRestaurantsModel(R.drawable.slider4, "Khandelwal Pavitra Bhojnalaya", "North Indian.Indian Curry", "20-30 Min"));

        MoreRestaurantsAdapter moreRestaurantsAdapter = new MoreRestaurantsAdapter(getContext(), arrayList2);
        recycler_view2.setAdapter(moreRestaurantsAdapter);


        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("ADDRESS", MODE_PRIVATE);
        String current_address = sharedPreferences1.getString("address", null);

        address.setText(current_address);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewAddress.class);
                getActivity().startActivity(intent);

            }
        });

        return view;


    }



}
