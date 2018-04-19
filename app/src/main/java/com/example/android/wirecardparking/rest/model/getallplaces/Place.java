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

    @SerializedName("display-name")
    @Expose
    private String displayID;

    @SerializedName("availabilities")
    @Expose
    private ArrayList<EachDay> availableSpots = new ArrayList<>();

    @SerializedName("user")
    @Expose
    private User users;

    @SerializedName("owner")
    @Expose
    private User owner;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayID() {
        return displayID;
    }

    public void setDisplayID(String id) {
        this.displayID = id;
    }


    public ArrayList<EachDay> getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(ArrayList<EachDay> contacts) {
        this.availableSpots = contacts;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
