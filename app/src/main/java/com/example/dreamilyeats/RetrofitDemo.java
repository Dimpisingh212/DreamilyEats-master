package com.example.dreamilyeats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dreamilyeats.Model.retrofitModel;
import com.example.dreamilyeats.Retrofit.ApiService;
import com.example.dreamilyeats.Retrofit.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitDemo extends AppCompatActivity {

    private TextView mResponseTv;
    private ApiService mApiService;
    private String TAG = "RetrofitDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_demo);

        final EditText titleEt = (EditText) findViewById(R.id.et_title);
        final EditText bodyEt = (EditText) findViewById(R.id.et_body);
        Button submitBtn = (Button) findViewById(R.id.btn_submit);
        mResponseTv = (TextView) findViewById(R.id.tv_response);

        mApiService = ApiUtils.getAPIService();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEt.getText().toString().trim();
                String body = bodyEt.getText().toString().trim();
                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                    sendPost(title, body);
                    Log.e(TAG, "Helo");
                }
            }
        });

    }

    private void sendPost(String title, String body) {

        Log.e(TAG, "Helottt");
        mApiService.savePost(title, body, 1).enqueue(new Callback<retrofitModel>() {
            @Override
            public void onResponse(Call<retrofitModel> call, Response<retrofitModel> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.e(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<retrofitModel> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");

            }
        });
    }

    private void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }
}
