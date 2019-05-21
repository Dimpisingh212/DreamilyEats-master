package com.example.dreamilyeats;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamilyeats.Model.FavouriteListArray;
import com.example.dreamilyeats.Model.MyItemArray;
import com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck;

import java.util.List;

import static com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck.isOnline;

public class After_select_menu extends AppCompatActivity {

    private TextView dish_name,price,add_1,instruction,add1,totalPrice,error;
    private ConstraintLayout constraint_layout,bottom_bar;
    private ImageView add,remove;
    private double total_price = 0.00;
    private String TAG = "After_Select_menu";
    private LinearLayout special_instruction;

    public  AlertDialog.Builder builder;
    public static AlertDialog alertDialog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_select_menu);

        dish_name = findViewById(R.id.dish_name);
        price = findViewById(R.id.price);
        constraint_layout = findViewById(R.id.constraint_layout);
        special_instruction = findViewById(R.id.special_instruction);
        add = findViewById(R.id.add);
        add_1 = findViewById(R.id.add_1);
        instruction = findViewById(R.id.instruction);
        add1 = findViewById(R.id.add1);
        totalPrice = findViewById(R.id.totalPrice);
        bottom_bar = findViewById(R.id.bottom_bar);
        error = findViewById(R.id.error);

       /* //Dialog box for network connection :
        builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Alert");
        builder.setMessage("Network Connection off!!").setCancelable(false);
        alertDialog = builder.create();

        if (!isOnline(this)){

            alertDialog.show();
        }*/

        final String dishname = getIntent().getExtras().getString("dish_name");
        String dish_price = getIntent().getExtras().getString("price");

        dish_name.setText(dishname);
        price.setText(dish_price);

        SharedPreferences.Editor editor = getSharedPreferences("INFO" , MODE_PRIVATE).edit();
        editor.putString("dishname" , dishname);
        editor.putString("price" , dish_price);
        editor.commit();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               total_price = Double.parseDouble(price.getText().toString()) + total_price;
               int i = Integer.parseInt(add_1.getText().toString()) + 1 ;
               add_1.setText(String.valueOf(i));
                add1.setText(String.valueOf(i));
                totalPrice.setText(String.valueOf(total_price));


            }
        });

        remove = findViewById(R.id.remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(add_1.getText().toString()) > 0) {
                    total_price = total_price - Double.parseDouble(price.getText().toString());
                    int i = Integer.parseInt(add_1.getText().toString()) - 1;
                    add_1.setText(String.valueOf(i));
                    add1.setText(String.valueOf(i));
                    totalPrice.setText(String.valueOf(total_price));
                }
            }
        });




        special_instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewGroup viewGroup = findViewById(android.R.id.content);
                View view = LayoutInflater.from(After_select_menu.this).inflate(R.layout.special_instruction_dialog_box, viewGroup, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(After_select_menu.this);

                builder.setView(view);

                final EditText note = view.findViewById(R.id.note);
                final Button done = view.findViewById(R.id.done);

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        instruction.setText(note.getText().toString());
                        alertDialog.dismiss();
                    }
                });
            }
        });




        bottom_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(add1.getText().toString());
                if(isOnline(After_select_menu.this)) {
                    if (i == 0) {
                        error.setText("Please add item");
                        error.setError("Please add item!!");
                        error.setTextColor(Color.RED);
                        Toast.makeText(After_select_menu.this, "Please add the item", Toast.LENGTH_LONG).show();
                    } else {
                        final GlobalArray globalArray = (GlobalArray) getApplicationContext();
                        if (containsName(globalArray.myItemArrays, dish_name.getText().toString())) {
                            Toast.makeText(globalArray, "Item already added", Toast.LENGTH_SHORT).show();
                        } else {
                            globalArray.myItemArrays.add(new MyItemArray(Double.parseDouble(totalPrice.getText().toString()), Integer.parseInt(add1.getText().toString())
                                    , dish_name.getText().toString()));
                        }


                        Log.e(TAG, " price  :" + totalPrice.getText().toString());
                        Log.e(TAG, "number  : " + add1.getText().toString());


                        Log.e(TAG, " Number of Items  :" + add1.getText().toString());
                        Log.e(TAG, " Price of dish :" + totalPrice.getText().toString());
                        Log.e(TAG, " name of dish :" + dish_name.getText().toString());

                        Log.e(TAG, "Global Array :" + globalArray.myItemArrays);


                        finish();


                    }
                } else {
                    builder = new AlertDialog.Builder(After_select_menu.this);
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    builder.setTitle("Alert");
                    builder.setMessage("Network Connection off!!");
                    alertDialog = builder.create();

                    if (!isOnline(After_select_menu.this)){

                        alertDialog.show();
                    }
                }

            }
        });



    }

    private static boolean containsName(final List<MyItemArray> myItemArrays, final String itemname) {
        for (final MyItemArray myItemArray : myItemArrays) {
            if (myItemArray.getItem_name().equals(itemname)) {
                return true;
            }
        }

        return false;
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        After_select_menu.this.registerReceiver(new NetworkConnectionCheck(), intentFilter);

    }
*/
}
