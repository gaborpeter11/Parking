package com.example.android.wirecardparking.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MoviesResponse {

    @SerializedName("salutation")
    @Expose
    private String salutation;

    @SerializedName("user-name")
    @Expose
    private String user_name;

    @SerializedName("status")
    @Expose
    private String status;




    public String getUserName() {
        return user_name;
    }


    public String getStatus() {
        return status;
    }

    public String getSalutation() {
        return salutation;
    }

}
