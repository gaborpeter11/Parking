package com.example.android.wirecardparking.rest.model.getallplaces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PepovPC on 4/17/2018.
 */

public class User {


    @SerializedName("mobile-number")
    @Expose
    private String number;

    @SerializedName("first-name")
    @Expose
    private String first_name;

    @SerializedName("last-name")
    @Expose
    private String last_name;



    public String getUserNumber() {
        return number;
    }

    public void setUserNumber(String number) {
        this.number = number;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLasNname() {
        return last_name;
    }


}
