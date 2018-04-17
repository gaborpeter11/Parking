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
        private EachDayUser eachDayUsers;



        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }


        public EachDayUser getEachDayUser() {
            return eachDayUsers;
        }

        public void setEachDayUser(EachDayUser contacts) {
            this.eachDayUsers = contacts;
        }


    public class EachDayUser {

        @SerializedName("mobile-number")
        @Expose
        private String mobile;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

    }

}

