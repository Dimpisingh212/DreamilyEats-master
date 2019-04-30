package com.example.dreamilyeats.Retrofit;

import com.example.dreamilyeats.Model.retrofitModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/posts")
    @FormUrlEncoded
    Call<retrofitModel> savePost(@Field("title") String title,
                                 @Field("body") String body,
                                 @Field("userId") long userId);

}
