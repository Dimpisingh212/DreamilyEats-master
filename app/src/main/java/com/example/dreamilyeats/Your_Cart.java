package com.example.dreamilyeats;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamilyeats.Model.MyItemArray;
import com.example.dreamilyeats.Model.PlaceOrderListModel;
import com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck.isOnline;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Your_Cart extends AppCompatActivity {

    TextView restaurant_name,estimate_time,current_location,num_dishes,dishes_price,dishes_list,total_price,total_price1,other_location;
    private String TAG = "Your_cart";
    ArrayList<MyItemArray> myarray = new ArrayList<>();
    private ImageView map_image;
    private RecyclerView recycler_view;
    private  double total = 0.00;
    private double delivery_charge = 20.00;
    private TextView total_billing_charge ;
    private ConstraintLayout order;
    private ImageView back;
    private int image = R.drawable.slide2;



    public  AlertDialog.Builder builder;
    public static AlertDialog alertDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your__cart);

        restaurant_name = findViewById(R.id.restaurant_name);
        estimate_time = findViewById(R.id.estimate_time);
        current_location = findViewById(R.id.current_location);
        num_dishes = findViewById(R.id.num_dishes);
        dishes_price = findViewById(R.id.dishes_price);
        dishes_list = findViewById(R.id.dishes_list);
        recycler_view = findViewById(R.id.recycler_view);
        total_price = findViewById(R.id.total_price);
        map_image = findViewById(R.id.map_image);
        total_price1 = findViewById(R.id.total_price1);
        total_billing_charge = findViewById(R.id.total_price3);
        order = findViewById(R.id.order);
        back = findViewById(R.id.back);
        other_location = findViewById(R.id.other_location);

        restaurant_name.setText(GlobalArray.hotel_name);
        estimate_time.setText(GlobalArray.time);
        total_price1.setText(String.valueOf(delivery_charge));





        other_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Add_Map_fragment add_map_fragment = new Add_Map_fragment();
                transaction.replace(R.id.constraint_layout, add_map_fragment);
                transaction.addToBackStack(null);
                transaction.commit()

                ;
            }
        });

        //set current address :
        SharedPreferences sharedPreferences1 = getSharedPreferences("ADDRESS", MODE_PRIVATE);
        String current_address = sharedPreferences1.getString("address", null);
        Log.e("location :" , " " +current_address);
        current_location.setText(current_address);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(linearLayoutManager);
        recycler_view.setNestedScrollingEnabled(false);


        //use global array :
        final GlobalArray globalArray = (GlobalArray) getApplicationContext();
        myarray = globalArray.getMyItemArrays();



        CartOrdersAdapter cartOrdersAdapter = new CartOrdersAdapter(this,myarray);
        recycler_view.setAdapter(cartOrdersAdapter);

        //get the subtotal :
        SharedPreferences sharedPreferences = getSharedPreferences("SUBTOTAL", MODE_PRIVATE);
        Double subtotal = Double.valueOf(sharedPreferences.getString("total_cost", null));
        Log.e(TAG, "Total :" +subtotal);


        total_price.setText(new DecimalFormat("##.##").format(subtotal));


        double t = subtotal + delivery_charge;
        total_billing_charge.setText(String.valueOf(t));


        Date c = Calendar.getInstance().getTime();
        Log.e(TAG, "Current time  => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss aa  dd:MMMM:yyyy");
        final String formattedDate = df.format(c.getTime());


        //creating receipt :

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isOnline(Your_Cart.this)){
                    if((current_location.getText().toString().equalsIgnoreCase("Current location") || current_location.getText().toString().equals(null) || current_location.getText().toString().isEmpty())) {
                        Toast.makeText(Your_Cart.this, "Add current location" , Toast.LENGTH_LONG).show();

                    } else {
                        globalArray.placeOrderListModels.add(new PlaceOrderListModel(image, GlobalArray.hotel_name, formattedDate, total_billing_charge.getText().toString()));
                        Log.e(TAG , "Global array : " +globalArray.placeOrderListModels);
                        AlertDialog alertDialog = new AlertDialog.Builder(Your_Cart.this).create();
                        alertDialog.setTitle("DreamilyEats :");
                        alertDialog.setMessage("Are you sure for Order");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                openingReceiptDialogBox();
                                dialog.dismiss();
                            }
                        });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });//jjj
                        alertDialog.show();

                    }
                } else {
                    builder = new AlertDialog.Builder(Your_Cart.this);
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    builder.setTitle("Alert");
                    builder.setMessage("Network Connection off!!");
                    alertDialog = builder.create();

                    if (!isOnline(Your_Cart.this)){

                        alertDialog.show();
                    }
                }


            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }



    public void update_total(double item_cost) {

       total = Double.parseDouble(total_price.getText().toString()) - item_cost;
        Log.e(TAG , "total update :" +total);
        total_price.setText(String.valueOf(total));

        double t = total + delivery_charge;
        total_billing_charge.setText(new DecimalFormat("##.##").format(String.valueOf(t)));


    }



    private void openingReceiptDialogBox() {

        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView  = LayoutInflater.from(Your_Cart.this).inflate(R.layout.view_receipt_dialog_box, viewGroup , false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        TextView home_page = dialogView.findViewById(R.id.home_page);
        home_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Your_Cart.this, HomePage.class);
                startActivity(intent);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);

    }



    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        Your_Cart.this.registerReceiver(new NetworkConnectionCheck(), intentFilter);

    }



}
