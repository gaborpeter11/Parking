package com.example.android.wirecardparking.rest.model.getallplaces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by PepovPC on 4/17/2018.
 */

public class Place {


    @SerializedName("identifier")
    @Expose
    private String id;

    @SerializedName("availabilities")
    @Expose
    private ArrayList<EachDay> availableSpots = new ArrayList<>();



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public ArrayList<EachDay> getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(ArrayList<EachDay> contacts) {
        this.availableSpots = contacts;
    }


}
