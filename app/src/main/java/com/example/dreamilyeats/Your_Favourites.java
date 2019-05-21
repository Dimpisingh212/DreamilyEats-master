package com.example.dreamilyeats;

import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamilyeats.Model.FavouriteListArray;
import com.example.dreamilyeats.Model.MyItemArray;
import com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck;

import java.util.ArrayList;

import static com.example.dreamilyeats.NetworkConnectivity.NetworkConnectionCheck.isOnline;

public class Your_Favourites extends AppCompatActivity {

    private RecyclerView recycler_view;
    ArrayList<FavouriteListArray> favouriteListArrays = new ArrayList<>();
    private ImageView back;

    public  AlertDialog.Builder builder;
    public static AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your__favourites);

        back = findViewById(R.id.back);
        recycler_view = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(linearLayoutManager);


        builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Alert");
        builder.setMessage("Network Connection off!!").setCancelable(false);
        alertDialog = builder.create();

        if (!isOnline(this)){

            alertDialog.show();
        }

        final GlobalArray globalArray = (GlobalArray) getApplicationContext();
        favouriteListArrays = globalArray.getFavouriteListArrays();
        Log.e("FavouriteAdapter" , "my array :: " +favouriteListArrays);


        FavouriteListAdapter favouriteListAdapter = new FavouriteListAdapter(this, favouriteListArrays);
        recycler_view.setAdapter(favouriteListAdapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }


    public static void showFavouriteDialogBox() {
        alertDialog.show();
    }

    public static void cancelFavouriteDialogBox() {
        alertDialog.cancel();
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        Your_Favourites.this.registerReceiver(new NetworkConnectionCheck(), intentFilter);

    }

}
