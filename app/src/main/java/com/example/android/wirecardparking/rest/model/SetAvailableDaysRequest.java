package com.example.android.wirecardparking.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by PepovPC on 4/19/2018.
 */

public class SetAvailableDaysRequest {
    @SerializedName("parking-place-identifier")
    @Expose
    private String parkingPlaceIdentifier;
    @SerializedName("days")
    @Expose
    private List<String> days = null;

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

}