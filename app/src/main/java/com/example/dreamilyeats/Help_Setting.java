package com.example.dreamilyeats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Help_Setting extends AppCompatActivity {

    ImageView  back;
    LinearLayout layout1,layout2,past_order_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__setting);


        back = findViewById(R.id.back);
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        past_order_layout = findViewById(R.id.past_order_layout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Help_Setting.this, Cash_Payment_setting.class);
                startActivity(intent);
            }
        });

        past_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Help_Setting.this, HomePage.class);

                GlobalArray.flag = true ;
                startActivity(intent);

                
            }
        });

    }
}
