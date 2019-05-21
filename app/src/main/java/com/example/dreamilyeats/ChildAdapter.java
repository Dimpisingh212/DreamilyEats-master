package com.example.dreamilyeats;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamilyeats.Model.ChildModel;
import com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck;

import java.util.ArrayList;

import static com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck.isOnline;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.Viewholder> {

    Context context;
    ArrayList<ChildModel> arrayList1;
    ArrayList<ChildModel> filterlist;
    public  AlertDialog.Builder builder;
    public static AlertDialog alertDialog;

    public ChildAdapter(Context context, ArrayList<ChildModel> arrayList1) {
        this.context=context;
        this.arrayList1 = arrayList1;
    }


    @NonNull
    @Override
    public ChildAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.picked_fragment_design, viewGroup ,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChildAdapter.Viewholder viewholder, final int i) {

        viewholder.dish_name.setText(arrayList1.get(i).getDish_name());
        viewholder.about.setText(arrayList1.get(i).getAbout());
        viewholder.price.setText(arrayList1.get(i).getPrice());
        viewholder.veg_image.setImageResource(arrayList1.get(i).getVeg_icon());
        viewholder.vegetarian.setText(arrayList1.get(i).getVegetarian());

        viewholder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkConnectionCheck.isOnline(context)) {
                    Intent intent = new Intent(context, After_select_menu.class);
                    intent.putExtra("dish_name", arrayList1.get(i).getDish_name());
                    intent.putExtra("price", arrayList1.get(i).getPrice());
                    context.startActivity(intent);


                    Log.e("child array :", " " + arrayList1.get(i).getDish_name());
                    Log.e("child array :", " " + arrayList1.get(i).getAbout());
                    Log.e("child array :", " " + arrayList1.get(i).getPrice());

                } else {
                    builder = new AlertDialog.Builder(context);
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    builder.setTitle("Alert");
                    builder.setMessage("Network Connection off!!");
                    alertDialog = builder.create();

                    if (!isOnline(context)){

                        alertDialog.show();
                    }

                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return (arrayList1 == null) ? 0 : arrayList1.size();

    }






  /*  @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();
                if (charString.isEmpty()) {

                    filterlist = arrayList1;

                } else {

                    ArrayList<ChildModel> filteredlist = new ArrayList<>();
                    for (ChildModel childModel : arrayList1) {

                        if (childModel.getDish_name().toLowerCase().contains(charString.toLowerCase())) {

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



                filterlist = (ArrayList<ChildModel>) results.values;

                Log.e("ChildAdapter", String.valueOf(filterlist));
                notifyDataSetChanged();

            }
        };
    }
*/





    public class Viewholder extends RecyclerView.ViewHolder {

        TextView dish_name,about,price,vegetarian;
        ImageView veg_image;
        ConstraintLayout layout;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            dish_name = itemView.findViewById(R.id.dish_name);
            about = itemView.findViewById(R.id.about);
            price = itemView.findViewById(R.id.price);
            vegetarian = itemView.findViewById(R.id.vegetarian);
            veg_image = itemView.findViewById(R.id.veg_image);
            layout = itemView.findViewById(R.id.layout);



        }
    }
}
