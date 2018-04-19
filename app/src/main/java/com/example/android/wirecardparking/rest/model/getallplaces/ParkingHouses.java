package com.example.android.wirecardparking.rest.model.getallplaces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by PepovPC on 4/17/2018.
 */

public class ParkingHouses {


    @SerializedName("parking-houses")
    @Expose
    private ArrayList<ParkingPlaces> houses = new ArrayList<>();


    public ArrayList<ParkingPlaces> getHouses() {
        return houses;
    }

    public void setHouses(ArrayList<ParkingPlaces> contacts) {
        this.houses = contacts;
    }





    public class ParkingPlaces {

        @SerializedName("parking-places")
        @Expose
        private ArrayList<Place> place = new ArrayList<>();

        @SerializedName("display-name")
        @Expose
        private String houseDisplayName;


        public ArrayList<Place> getPlaces() {
            return place;
        }

        public void setPlaces(ArrayList<Place> contacts) {
            this.place = contacts;
        }

        public String getHouseDisplayName() {
            return houseDisplayName;
        }

        public void setHouseDisplayName(String houseDisplayName) {
            this.houseDisplayName = houseDisplayName;
        }

    }


}
