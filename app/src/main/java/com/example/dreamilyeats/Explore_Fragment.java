package com.example.dreamilyeats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.dreamilyeats.Model.ExploreFragmentModel;

import java.util.ArrayList;

public class Explore_Fragment extends Fragment {

    RecyclerView recyclerView;
    EditText search_line;
    ArrayList<ExploreFragmentModel> arrayList;
    ExploreFragmentAdapter exploreFragmentAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore_, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        search_line = view.findViewById(R.id.search_line);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        arrayList = new ArrayList<>();
        arrayList.add(new ExploreFragmentModel(R.drawable.pizza, "Pizza"));
        arrayList.add(new ExploreFragmentModel(R.drawable.north_indian, "North Indian"));
        arrayList.add(new ExploreFragmentModel(R.drawable.indian, "Indian"));
        arrayList.add(new ExploreFragmentModel(R.drawable.fast_food, "Fast Food"));
        arrayList.add(new ExploreFragmentModel(R.drawable.desserts, "Desserts"));
        arrayList.add(new ExploreFragmentModel(R.drawable.bakery, "Bakery"));
        arrayList.add(new ExploreFragmentModel(R.drawable.burgers, "Burgers"));
        arrayList.add(new ExploreFragmentModel(R.drawable.chinese, "Chinese"));
        arrayList.add(new ExploreFragmentModel(R.drawable.cafe, "Cafe"));
        arrayList.add(new ExploreFragmentModel(R.drawable.veg, "Vegetarian"));
        arrayList.add(new ExploreFragmentModel(R.drawable.cake, "Cakes"));

        exploreFragmentAdapter = new ExploreFragmentAdapter(getContext(), arrayList);
        recyclerView.setAdapter(exploreFragmentAdapter);




        search_line.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.e("Explore_Fragment", "ontextchanged");
            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());
            }
        });


        return view;
    }



    private void filter(String text) {

        ArrayList<ExploreFragmentModel> filterNames = new ArrayList<>();

        for(ExploreFragmentModel exploreFragmentModel : arrayList) {

            if (exploreFragmentModel.getFood_type_name().toLowerCase().contains(text.toLowerCase())) {

                filterNames.add(exploreFragmentModel);
            }

            exploreFragmentAdapter.filterlist(filterNames);

        }
    }
}
