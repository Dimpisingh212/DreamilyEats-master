package com.example.dreamilyeats.Retrofit;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    public static ApiService getAPIService() {

        return RetrofitIntance.getRetrofit(BASE_URL).create(ApiService.class);
    }
}
