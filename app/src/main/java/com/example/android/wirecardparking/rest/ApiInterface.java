package com.example.android.wirecardparking.rest;


import com.example.android.wirecardparking.rest.model.SetAvailableDaysRequest;
import com.example.android.wirecardparking.rest.model.Token;
import com.example.android.wirecardparking.rest.model.getallplaces.ParkingHouses;
import com.example.android.wirecardparking.rest.model.registeruser.RegisterErrorRequest;
import com.example.android.wirecardparking.rest.model.registeruser.RegisterRequest;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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
    @POST("token")
    Observable<Token> loginUser(@Header("Authorization") String credentials);


//    @Headers({
//            "Content-Type: application/json",
//            "Authorization: Bearer 9623e0a6-12d1-4251-b36b-5529e8546a60"    // TODO: CHANCGCE AKWAYS
//    })
//    @GET("parking-houses/free-parking-places")
//    Observable<FreePlaces> getFreePlaces(@Body FreePlaces freePlaces);


    @Headers({
            "Content-Type: application/json",
            "Accept: application/json",
    })
    @GET("parking-houses")
    Observable<ParkingHouses> getAllPlaces(@Header("Authorization") String token);

    @Headers({
            "Content-Type: application/json",
            "Accept: application/json",
    })
    @PUT("user/+421901111244/set-available-days")
    Completable setAvailableDays(@Header("Authorization") String token, @Body SetAvailableDaysRequest setAvailableDays);



    @GET("movie/{id}")
    Call<RegisterErrorRequest> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
