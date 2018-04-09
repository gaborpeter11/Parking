package com.example.android.wirecardparking.rest;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient{

    public static final String BASE_URL = "http://10.0.3.2:8090/parking/rest-api/v1/";
    private static Retrofit retrofit = null;



    private static OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
            .addInterceptor(
                    chain -> {
                        Request request = chain.request().newBuilder()
                                .header("Content-Type", "application/json")
                                .header("Accept", "application/json").build();
                        return chain.proceed(request);
                    }).build();



    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //.client(defaultHttpClient)
                    .build();
        }
        return retrofit;
    }


    public static ApiInterface getApiService() {
        return getClient().create(ApiInterface.class);
    }


}
