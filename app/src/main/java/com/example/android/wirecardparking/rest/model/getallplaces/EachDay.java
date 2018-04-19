package com.example.android.wirecardparking.rest.model.getallplaces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PepovPC on 4/17/2018.
 */

public class EachDay {

        @SerializedName("day")
        @Expose
        private String day;

        @SerializedName("user")
        @Expose
        private User eachDayUsers;



        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }


        public User getEachDayUser() {
            return eachDayUsers;
        }

        public void setEachDayUser(User contacts) {
            this.eachDayUsers = contacts;
        }



}

