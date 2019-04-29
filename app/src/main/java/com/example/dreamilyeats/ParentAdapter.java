package com.example.dreamilyeats;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.dreamilyeats.Model.ChildModel;
import com.example.dreamilyeats.Model.ParentModel;

import java.util.ArrayList;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.Viewholder> implements Filterable {

    Context context;
    ArrayList<ParentModel> arrayList;
    ArrayList<ParentModel> filterlist;
    public ChildAdapter childAdapter ;
    private String TAG = "ParentAdapter";

    public ParentAdapter(Context context, ArrayList<ParentModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        Log.e(TAG , " Size : " +arrayList.size());
    }

    @NonNull
    @Override
    public ParentAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.title_picked_fragment_design, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentAdapter.Viewholder viewholder, int i) {

        ParentModel parentModel = arrayList.get(i);
        ArrayList<ChildModel> arrayList1 = parentModel.getArrayList();
        Log.e(TAG ,  " arraylist : " +arrayList1);

        viewholder.dish_title.setText(arrayList.get(i).getTitle());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        viewholder.recycler_view_parent.setLayoutManager(linearLayoutManager);
        viewholder.recycler_view_parent.setHasFixedSize(true);


        viewholder.recycler_view_parent.setNestedScrollingEnabled(false);
        childAdapter = new ChildAdapter(context,arrayList1);
        viewholder.recycler_view_parent.setAdapter(childAdapter);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();
                if (charString.isEmpty()) {

                    filterlist = arrayList;

                } else {

                    ArrayList<ParentModel> filteredlist = new ArrayList<>();
                    for (ParentModel childModel : arrayList) {

                        if (childModel.getTitle().toLowerCase().contains(charString.toLowerCase())) {

                            filteredlist.add(childModel);
                        }

                    }

                    filterlist = filteredlist;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterlist;
                return filterResults;
            }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {



                filterlist = (ArrayList<ParentModel>) results.values;

                Log.e("ParentAdapter_list", String.valueOf(filterlist));

                arrayList = filterlist;
                notifyDataSetChanged();

            }
        };
    }



    public class Viewholder extends RecyclerView.ViewHolder {

        TextView dish_title;
        RecyclerView recycler_view_parent;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            dish_title = itemView.findViewById(R.id.dish_title);
            recycler_view_parent = itemView.findViewById(R.id.recycler_view_parent);



        }
    }
}
