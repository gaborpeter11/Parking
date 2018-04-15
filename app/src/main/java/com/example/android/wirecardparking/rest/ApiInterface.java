package com.example.android.wirecardparking.rest;


import com.example.android.wirecardparking.rest.model.RegisterErrorRequest;
import com.example.android.wirecardparking.rest.model.RegisterRequest;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("movie/top_rated")
    Call<RegisterErrorRequest> getTopRatedMovies(@Query("api_key") String apiKey);


    //@FormUrlEncoded
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json"
    })
    @PUT("register/user/")
    Observable<RegisterRequest> putNewUser(@Body RegisterRequest register);


    @Headers({
            "Content-Type: application/json",
            "Accept: application/json"
    })
    @GET("user/+44875464469/profile")
    Observable<RegisterErrorRequest> getErrorPokus(@Header("Authorization") String name);


    @GET("movie/{id}")
    Call<RegisterErrorRequest> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
