package com.example.android.wirecardparking.rest;


import com.example.android.wirecardparking.rest.model.ErrorResponse;
import com.example.android.wirecardparking.rest.model.MoviesResponse;
import com.example.android.wirecardparking.rest.model.SkusPUT;

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
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

//    @FormUrlEncoded
//    @Headers({
//            "Content-Type: application/json",
//            "Accept: application/json"
//    })
//    @PUT("register/user/")
//    Observable<MoviesResponse> putNewUser(
//                                   @Field(value = "salutation", encoded = true) String salutation,
//                                   @Field("first-name") String first_name,
//                                   @Field("last-name") String last_name,
//                                   @Field("mobile-number") String mobile_number,
//                                   @Field("email") String email,
//                                   @Field("user-name") String user_name,
//                                   @Field("password") String password,
//                                   @Field("security-question") String security_question,
//                                   @Field("security-answer") String security_answer,
//                                   @Field("terms-of-use-accepted") String terms_of_use);

    //@FormUrlEncoded
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json"
    })
    @PUT("register/user/")
    Observable<MoviesResponse> putNewUser(@Body SkusPUT skus);


    @Headers({
            "Content-Type: application/json",
            "Accept: application/json"
    })
    @GET("user/+44875464469/profile")
    Observable<ErrorResponse> getErrorPokus(@Header("Authorization") String name);


    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
