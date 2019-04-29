package com.example.dreamilyeats;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamilyeats.Model.ChildModel;
import com.example.dreamilyeats.Model.MyItemArray;
import com.example.dreamilyeats.Model.ParentModel;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class Next_MoreRestaurant extends AppCompatActivity {

    private TabLayout tab_layout;
    private RecyclerView recycler_view_parent;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private String TAG = "MoreRestaurant.java";
    public ParentAdapter parentAdapter;
    private ConstraintLayout bottom_bar;
    private int total_item = 0;
    private Double total_cost = 0.00;
    private String restaurent_name;
    private String maximum_time;
    private TextView restaurantnames;
    ArrayList<ParentModel> arrayList;
    private ImageView back;

    ArrayList<ChildModel> arrayList1 ;
    ArrayList<ChildModel> arrayList2 ;
    ArrayList<ChildModel> arrayList3 ;
    ArrayList<ChildModel> arrayList4 ;
    ArrayList<ChildModel> arrayList5;
    ArrayList<ChildModel> arrayList6 ;
    ArrayList<ChildModel> arrayList7 ;
    ArrayList<ChildModel> arrayList8  ;
    private int position = 0;

    private TextView number_of_items,selected_item_Price;

    ArrayList<MyItemArray> myarray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next__more_restaurant);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        restaurantnames= findViewById(R.id.restaurantnames);
        back= findViewById(R.id.imageView4);

        tab_layout = findViewById(R.id.tab_layout);
        tab_layout.addTab(tab_layout.newTab().setText("Picked For You"));
        tab_layout.addTab(tab_layout.newTab().setText("Thali"));
        tab_layout.addTab(tab_layout.newTab().setText("South Indians"));
        tab_layout.addTab(tab_layout.newTab().setText("Sweets"));
        tab_layout.addTab(tab_layout.newTab().setText("Namkeens & Snacks"));
        tab_layout.addTab(tab_layout.newTab().setText("Chinese"));
        tab_layout.addTab(tab_layout.newTab().setText("Cakes & Pastries"));
        tab_layout.addTab(tab_layout.newTab().setText("Biscuits"));
        tab_layout.addTab(tab_layout.newTab().setText("Burgers"));
        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });





        final GlobalArray globalArray = (GlobalArray) getApplicationContext();
        myarray = globalArray.getMyItemArrays();



        Intent intent = getIntent();
        String restaurant_names = intent.getStringExtra("restaurant_name");

       if("Topaz Restaurant".equalsIgnoreCase(restaurant_names)) {
           addtoarray();
           myarray.clear();
           restaurantnames.setText(restaurant_names);

       } else if("Kanji Sweets".equalsIgnoreCase(restaurant_names)) {
           addtoarray1();
           myarray.clear();
           restaurantnames.setText(restaurant_names);

       } else if("Cafe Kebabs".equalsIgnoreCase(restaurant_names)) {
           addtoarray2();
           myarray.clear();
           restaurantnames.setText(restaurant_names);

       } else if("Cafe Coffee Day - Vidhyadhar nagar".equalsIgnoreCase(restaurant_names)) {
           addtoarray3();
           myarray.clear();
           restaurantnames.setText(restaurant_names);

       } else if("Khandelwal Pavitra Bhojnalaya".equalsIgnoreCase(restaurant_names)) {
           addtoarray1();
           myarray.clear();
           restaurantnames.setText(restaurant_names);
       }



       Intent intent1 = getIntent();
       String hotel_name = intent1.getStringExtra("hotel_names");

        if("Agarwal Caterers-Vaishali Nagar".equalsIgnoreCase(hotel_name)) {
            addtoarray();
            myarray.clear();
            restaurantnames.setText(hotel_name);

        } else if("Thaggu ke Samose".equalsIgnoreCase(hotel_name)) {
            addtoarray1();
            myarray.clear();
            restaurantnames.setText(hotel_name);

        } else if("Agarwal Caterers-Shastri Nagar".equalsIgnoreCase(hotel_name)) {
            addtoarray2();
            myarray.clear();
            restaurantnames.setText(hotel_name);

        }



        arrayList = new ArrayList<>();

        arrayList.add(new ParentModel("Thali" ,arrayList1));
        arrayList.add(new ParentModel("South Indians",arrayList2));
        arrayList.add(new ParentModel("Sweets",arrayList3));
        arrayList.add(new ParentModel("Namkeen & Snacks",arrayList4));
        arrayList.add(new ParentModel("Chinese",arrayList5));
        arrayList.add(new ParentModel("Cakes & Pastries",arrayList6));
        arrayList.add(new ParentModel("Biscuits",arrayList7));
        arrayList.add(new ParentModel("Burgers",arrayList8));


        recycler_view_parent = findViewById(R.id.recycler_view_parent);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view_parent.setLayoutManager(linearLayoutManager);

        parentAdapter = new ParentAdapter(this, arrayList);
        recycler_view_parent.setAdapter(parentAdapter);


        number_of_items = findViewById(R.id.number_of_items);
        selected_item_Price = findViewById(R.id.selected_item_Price);
        bottom_bar = findViewById(R.id.bottom_bar);



        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                int position = tab.getPosition();
                Log.e(TAG , "Tabs position :" +position);
                int current_position = position -1 ;

                recycler_view_parent.scrollToPosition(current_position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        bottom_bar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Next_MoreRestaurant.this , Your_Cart.class);
                    startActivity(intent);
                }
            });


        recycler_view_parent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                int visibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                Log.e(TAG, "onScrolled: "+visibleItemPosition );

                switch (visibleItemPosition) {
                    case 0 :
                        TabLayout.Tab tab = tab_layout.getTabAt(1);
                        tab.select();


                        break;

                    case 1 :
                        TabLayout.Tab tab1 = tab_layout.getTabAt(2);
                        tab1.select();

                        break;

                    case 2 :
                        TabLayout.Tab tab2 = tab_layout.getTabAt(3);
                        tab2.select();

                        break;

                    case 3 :
                        TabLayout.Tab tab3 = tab_layout.getTabAt(4);
                        tab3.select();

                        break;


                    case 4 :
                        TabLayout.Tab tab4 = tab_layout.getTabAt(5);
                        tab4.select();

                        break;


                    case 5 :
                        TabLayout.Tab tab5 = tab_layout.getTabAt(6);
                        tab5.select();

                        break;

                    case 6 :
                        TabLayout.Tab tab6 = tab_layout.getTabAt(7);
                        tab6.select();

                        break;

                    case 7 :
                        TabLayout.Tab tab7= tab_layout.getTabAt(8);
                        tab7.select();

                        break;

                }

            }
        });






    }



    @Override
    protected void onResume() {
        super.onResume();

        total_cost = 0.00;
        total_item = 0;

        final GlobalArray globalArray = (GlobalArray) getApplicationContext();
        myarray = globalArray.getMyItemArrays();
        Log.e(TAG, " array " + myarray);

        for(int i=0; i < myarray.size(); i++) {
            total_cost = total_cost + myarray.get(i).getItem_price() ;
            total_item = total_item + myarray.get(i).getNumber_of_items();

        }

        selected_item_Price.setText(String.valueOf(total_cost));

        number_of_items.setText(String.valueOf(total_item));



        SharedPreferences.Editor editor = getSharedPreferences("SUBTOTAL" , MODE_PRIVATE).edit();
        editor.putString("total_cost" , String.valueOf(total_cost));
        editor.commit();


        if (String.valueOf(total_item).equalsIgnoreCase("0")) {
            bottom_bar.setVisibility(View.GONE);
        } else {
            bottom_bar.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);


       if (menuItem != null) {

           searchView = (SearchView) menuItem.getActionView();
           searchView.setMaxWidth(Integer.MAX_VALUE);
       }

       if (searchView != null) {
           searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
           searchView.setMaxWidth(Integer.MAX_VALUE);


           queryTextListener = new SearchView.OnQueryTextListener() {
               @Override
               public boolean onQueryTextSubmit(String query) {
                   parentAdapter.getFilter().filter(query);
                   Log.e(TAG , query);
                   return false;

               }

               @Override
               public boolean onQueryTextChange(String newText) {
                   Log.e(TAG , newText);
                  parentAdapter.getFilter().filter(newText);

                   return false;


               }
           };
           searchView.setOnQueryTextListener(queryTextListener);
       }

       return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:

                return false;

            default:
                break;
        }

        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }






    public void addtoarray() {


        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        arrayList4 = new ArrayList<>();
        arrayList5 = new ArrayList<>();
        arrayList6 = new ArrayList<>();
        arrayList7 = new ArrayList<>();
        arrayList8 = new ArrayList<>();


        arrayList1.add(new ChildModel("Executive Thali","Dal makhani with seasonal veg. rice, laccha paratha,papad,pickle","175.00","vegetarian", R.drawable.vege));
        arrayList1.add(new ChildModel("Special Thali","Dal Makhani + Paneer Vegetable + Seasonal Vegetables+Rice _ 1 Naan.","200.00","vegetarian", R.drawable.vege));

        //South Indians
        arrayList2.add(new ChildModel("Mixed Veg Uttapam"," ","120.00","vegetarian", R.drawable.vege));
        arrayList2.add(new ChildModel("Mysore Masala Dosa","Masala patty","138.00","vegetarian", R.drawable.vege));
        arrayList2.add(new ChildModel("Onion Uttapam","onion uttapam","110.00","vegetarian", R.drawable.vege));
        arrayList2.add(new ChildModel("Pizza Dosa"," ","150.00","vegetarian", R.drawable.vege));
        arrayList2.add(new ChildModel("South Indian Paneer"," ","190.00","vegetarian", R.drawable.vege));

        //Sweets
        arrayList3.add(new ChildModel("Akhrot Barfi","500 grams packet.","190.00","vegetarian", R.drawable.vege));
        arrayList3.add(new ChildModel("Angoori Petha(Kesar)","Per Kg.","209.00","vegetarian", R.drawable.vege));
        arrayList3.add(new ChildModel("Atta Laddu","500 grams packet.","181.00","vegetarian", R.drawable.vege));
        arrayList3.add(new ChildModel("Badam Barfi"," ","419.00","vegetarian", R.drawable.vege));
        arrayList3.add(new ChildModel("Balooshai(Makhan Bada)","500 grams","171.00","vegetarian", R.drawable.vege));
        arrayList3.add(new ChildModel("Besan Barfi","500 grams packet","180.00","vegetarian", R.drawable.vege));

        //Namkeens & Snacks
        arrayList4.add(new ChildModel("Aloo Bhujia - 400 grams packts","400 grams packets.","60.00","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Aloo Chips Black Masala","200 grams.","44.00","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Aloo Chips Masala","200 grams","44.00","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Badam Fry Salted"," ","227.00","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Besan Papri","200 grams.","35.00","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Bhujia"," ","32.00","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Bikaneri Bhakarwadi(500 gram)","500 grams packet.","38.00","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Chatpat Bhel","Packet","32.00","vegetarian", R.drawable.vege));

        //Chinese
        arrayList5.add(new ChildModel("Veg Chowmein","chowmein","110.00","vegetarian", R.drawable.vege));
        arrayList5.add(new ChildModel("Veg Fried Rice"," ","100.00","vegetarian", R.drawable.vege));
        arrayList5.add(new ChildModel("Chinese Platter","Plate.","190.00","vegetarian", R.drawable.vege));
        arrayList5.add(new ChildModel("Veg Spring Rolls"," ","100.00","vegetarian", R.drawable.vege));
        arrayList5.add(new ChildModel("Veg Americon Chopsuey"," ","120.00","vegetarian", R.drawable.vege));
        arrayList5.add(new ChildModel("Chilly Paneer","paneer","140.00","vegetarian", R.drawable.vege));

        //Cakes & Pastries
        arrayList6.add(new ChildModel("Black Forest Cake","Black Forest","584.00"," ", 0));
        arrayList6.add(new ChildModel("Brownie"," ","25.42"," ", 0));
        arrayList6.add(new ChildModel("ButterScotch Pastry","Per Piece.","140.00"," ", 0));
        arrayList6.add(new ChildModel("Cake Butter Scotch","450 gms","305.00"," ", 0));
        arrayList6.add(new ChildModel("Choco Cupcake","Per piece.","44.00"," ", 0));

        //Biscuits
        arrayList7.add(new ChildModel("Ajwain Phool","300 grams packets","67.00","vegetarian",  R.drawable.vege));
        arrayList7.add(new ChildModel("Almond Butter Cookies","450 grams Box","288.00","vegetarian",  R.drawable.vege));
        arrayList7.add(new ChildModel("Assorted Cookies","500 grams Box","254.00","vegetarian",  R.drawable.vege));
        arrayList7.add(new ChildModel("Atta Biscuit","350 grams packets","67.00","vegetarian",  R.drawable.vege));
        arrayList7.add(new ChildModel("Butter Cake Rusk","400 grams packets","157.00","vegetarian",  R.drawable.vege));

        //Burgers
        arrayList8.add(new ChildModel("Crispy Masala Burger","Desi masala patty","65.00","vegetarian", R.drawable.vege));
        arrayList8.add(new ChildModel("Devils Delight Burger Meal","100% chicken patty","200.00"," ", 0));
        arrayList8.add(new ChildModel("Go Green Burger","Vegetable patty","89.00","vegetarian", R.drawable.vege));

    }


    public void addtoarray1() {


        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        arrayList4 = new ArrayList<>();
        arrayList5 = new ArrayList<>();
        arrayList6 = new ArrayList<>();
        arrayList7 = new ArrayList<>();
        arrayList8 = new ArrayList<>();


        arrayList1.add(new ChildModel("Paneer Butter masala","Dal makhani with seasonal veg. rice, laccha paratha,papad,pickle","175.00","vegetarian", R.drawable.vege));
        arrayList1.add(new ChildModel("Shahi paneer","Dal Makhani + Paneer Vegetable + Seasonal Vegetables+Rice _ 1 Naan.","300.00","vegetarian", R.drawable.vege));

        //South Indians
        arrayList2.add(new ChildModel("Vada sambhar"," ","120.00","vegetarian", R.drawable.vege));
        arrayList2.add(new ChildModel("Masala Dosa","Masala patty","138.00","vegetarian", R.drawable.vege));
        arrayList2.add(new ChildModel("Chinese Idli","onion uttapam","110.00","vegetarian", R.drawable.vege));

        //Sweets
        arrayList3.add(new ChildModel("Kaju Dimand cake","500 grams packet.","190.00","vegetarian", R.drawable.vege));
        arrayList3.add(new ChildModel("Angoori Petha(Kesar)","Per Kg.","209.00","vegetarian", R.drawable.vege));
        arrayList3.add(new ChildModel("Kaju Kasta","500 grams packet.","181.00","vegetarian", R.drawable.vege));


        //Namkeens & Snacks
        arrayList4.add(new ChildModel("Lal mirchi sev - 400 grams packts","400 grams packets.","60.00","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Chole ki Dal","200 grams.","44.00","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Aloo Chips Masala","200 grams","44.00","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Kachori"," ","227.68","vegetarian", R.drawable.vege));


        //Chinese
        arrayList5.add(new ChildModel("Veg schezwan noodels","chowmein","110.00","vegetarian", R.drawable.vege));
        arrayList5.add(new ChildModel("Veg Fried Rice"," ","100.00","vegetarian", R.drawable.vege));


        //Cakes & Pastries
        arrayList6.add(new ChildModel("strawberry Cake","Black Forest","584.00"," ", 0));
        arrayList6.add(new ChildModel("pineapple pudding"," ","25.42"," ", 0));
        arrayList6.add(new ChildModel("ButterScotch Pastry","Per Piece.","140.00"," ", 0));

        //Biscuits
        arrayList7.add(new ChildModel("Ajwain Phool","300 grams packets","67.00","vegetarian",  R.drawable.vege));
        arrayList7.add(new ChildModel("Almond Butter Cookies","450 grams Box","288.00","vegetarian",  R.drawable.vege));

        //Burgers
        arrayList8.add(new ChildModel("Crispy Masala Burger","Desi masala patty","65.00","vegetarian", R.drawable.vege));
        arrayList8.add(new ChildModel("Devils Delight Burger Meal","100% chicken patty","200.00"," ", 0));


    }

    public void addtoarray2() {


        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        arrayList4 = new ArrayList<>();
        arrayList5 = new ArrayList<>();
        arrayList6 = new ArrayList<>();
        arrayList7 = new ArrayList<>();
        arrayList8 = new ArrayList<>();


        arrayList1.add(new ChildModel("Executive Thali","Dal makhani with seasonal veg. rice, laccha paratha,papad,pickle","175.00","vegetarian", R.drawable.vege));
        arrayList1.add(new ChildModel("Special Thali","Dal Makhani + Paneer Vegetable + Seasonal Vegetables+Rice _ 1 Naan.","200.00","vegetarian", R.drawable.vege));

        //South Indians
        arrayList2.add(new ChildModel("Agarwal Special dosa"," ","120.00","vegetarian", R.drawable.vege));
        arrayList2.add(new ChildModel("Plain Dosa","Masala patty","138.00","vegetarian", R.drawable.vege));


        //Sweets
        arrayList3.add(new ChildModel("Kaju katli","500 grams packet.","190.00","vegetarian", R.drawable.vege));
        arrayList3.add(new ChildModel("Pista Lounge","Per Kg.","209.00","vegetarian", R.drawable.vege));
        arrayList3.add(new ChildModel("Besan Laddu","500 grams packet.","181.00","vegetarian", R.drawable.vege));

        //Namkeens & Snacks
        arrayList4.add(new ChildModel("Mix namkeen","400 grams packets.","60.14","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Aloo Chips Black Masala","200 grams.","44.00","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Aloo Chips Masala","200 grams","44.00","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Aloo vafers"," ","227.00","vegetarian", R.drawable.vege));


        //Chinese
        arrayList5.add(new ChildModel("Vpaneer sataty"," ","100.00","vegetarian", R.drawable.vege));
        arrayList5.add(new ChildModel("Chilli paneer","Plate.","190.00","vegetarian", R.drawable.vege));

        //Cakes & Pastries
        arrayList6.add(new ChildModel("chocolate pudding","Black Forest","584.00"," ", 0));
        arrayList6.add(new ChildModel("Brownie"," ","25.00"," ", 0));


        //Biscuits
        arrayList7.add(new ChildModel("Ajwain Phool","300 grams packets","67.00","vegetarian",  R.drawable.vege));
        arrayList7.add(new ChildModel("Almond Butter Cookies","450 grams Box","288.00","vegetarian",  R.drawable.vege));
        arrayList7.add(new ChildModel("butter Cookies","500 grams Box","254.00","vegetarian",  R.drawable.vege));

        //Burgers
        arrayList8.add(new ChildModel("Crispy Masala Burger","Desi masala patty","65.00","vegetarian", R.drawable.vege));
        arrayList8.add(new ChildModel("Devils Delight Burger Meal","100% chicken patty","200.00"," ", 0));
        arrayList8.add(new ChildModel("Go Green Burger","Vegetable patty","89.00","vegetarian", R.drawable.vege));

    }

    public void addtoarray3() {


        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        arrayList4 = new ArrayList<>();
        arrayList5 = new ArrayList<>();
        arrayList6 = new ArrayList<>();
        arrayList7 = new ArrayList<>();
        arrayList8 = new ArrayList<>();


        arrayList1.add(new ChildModel("Executive Thali","Dal makhani with seasonal veg. rice, laccha paratha,papad,pickle","175.00","vegetarian", R.drawable.vege));
        arrayList1.add(new ChildModel("Special Thali","Dal Makhani + Paneer Vegetable + Seasonal Vegetables+Rice _ 1 Naan.","200.00","vegetarian", R.drawable.vege));

        //South Indians
        arrayList2.add(new ChildModel("Paneer Uttapam"," ","120.00","vegetarian", R.drawable.vege));
        arrayList2.add(new ChildModel("Hakka Masala Dosa","Masala patty","138.00","vegetarian", R.drawable.vege));
        arrayList2.add(new ChildModel("Onion Uttapam","onion uttapam","110.00","vegetarian", R.drawable.vege));

        //Sweets
        arrayList3.add(new ChildModel("Gajak","500 grams packet.","190.48","vegetarian", R.drawable.vege));
        arrayList3.add(new ChildModel("Angoori Petha(Kesar)","Per Kg.","209.52","vegetarian", R.drawable.vege));
        arrayList3.add(new ChildModel("Kaju Bites","500 grams packet.","181.00","vegetarian", R.drawable.vege));

        //Namkeens & Snacks
        arrayList4.add(new ChildModel("Moong mogar - 400 grams packts","400 grams packets.","60.14","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("Aloo Chips Black Masala","200 grams.","44.92","vegetarian", R.drawable.vege));
        arrayList4.add(new ChildModel("mix namkeen","200 grams","44.00","vegetarian", R.drawable.vege));

        //Chinese
        arrayList5.add(new ChildModel("Veg manchourine","chowmein","110.00","vegetarian", R.drawable.vege));
        arrayList5.add(new ChildModel("Veg hakka noodels"," ","100.00","vegetarian", R.drawable.vege));


        //Cakes & Pastries
        arrayList6.add(new ChildModel("Black Forest Cake","Black Forest","584.75"," ", 0));
        arrayList6.add(new ChildModel("Brownie"," ","25.42"," ", 0));
        arrayList6.add(new ChildModel("ButterScotch Pastry","Per Piece.","140.68"," ", 0));
        arrayList6.add(new ChildModel("Cake Butter Scotch","450 gms","305.00"," ", 0));
        arrayList6.add(new ChildModel("Choco Cupcake","Per piece.","44.07"," ", 0));

        //Biscuits
        arrayList7.add(new ChildModel("Ajwain Phool","300 grams packets","67.42","vegetarian",  R.drawable.vege));
        arrayList7.add(new ChildModel("Almond Butter Cookies","450 grams Box","288.42","vegetarian",  R.drawable.vege));

        //Burgers
        arrayList8.add(new ChildModel("Crispy Masala Burger","Desi masala patty","65.00","vegetarian", R.drawable.vege));
        arrayList8.add(new ChildModel("Devils Delight Burger Meal","100% chicken patty","200.00"," ", 0));


    }


}








