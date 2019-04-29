package com.example.dreamilyeats;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile_Fragment extends Fragment {

    private LinearLayout settings,favourite,payments,help,promotion;
    private FirebaseAuth firebaseAuth;
    private TextView user_name,about;
    private ImageView back;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);

        settings = view.findViewById(R.id.Linear_layout_6);
        favourite = view.findViewById(R.id.favourite);
        payments = view.findViewById(R.id.payments);
        help = view.findViewById(R.id.help);
        promotion = view.findViewById(R.id.promotion);
        about = view.findViewById(R.id.about);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Settings_Activity.class);
                startActivity(intent);
            }
        });

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Your_Favourites.class);
                startActivity(intent);
            }
        });

        payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Payments_Setting.class);
                startActivity(intent);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Help_Setting.class);
                startActivity(intent);
            }
        });

        promotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Promotion_Setting.class);
                startActivity(intent);
            }
        });


        back=view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), About_profile_activity.class);
                startActivity(intent);
            }
        });

        user_name=view.findViewById(R.id.user_name);
        if(firebaseUser != null) {
            String name = firebaseUser.getDisplayName();
            Log.e("Profile Fragment" , "Updation :" +name);
            user_name.setText(name);
        }




        return view;
    }


}
