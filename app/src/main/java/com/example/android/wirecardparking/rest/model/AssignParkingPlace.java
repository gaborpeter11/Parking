package com.example.android.wirecardparking.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by PepovPC on 4/20/2018.
 */

public class AssignParkingPlace {
    @SerializedName("parking-place-identifier")
    @Expose
    private String parkingPlaceIdentifier;

    @SerializedName("days")
    @Expose
    private List<String> days = null;

    @SerializedName("login-alias")
    @Expose
    private String loginAlias;

    @SerializedName("login-alias-type")
    @Expose
    private String loginAliasType;

    public String getParkingPlaceIdentifier() {
        return parkingPlaceIdentifier;
    }

    public void setParkingPlaceIdentifier(String parkingPlaceIdentifier) {
        this.parkingPlaceIdentifier = parkingPlaceIdentifier;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public String getLoginAlias() {
        return loginAlias;
    }

    public void setLoginAlias(String loginAlias) {
        this.loginAlias = loginAlias;
    }

    public String getLoginAliasType() {
        return loginAliasType;
    }

    public void setLoginAliasType(String loginAliasType) {
        this.loginAliasType = loginAliasType;
    }

}