package com.example.android.wirecardparking.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by PepovPC on 4/18/2018.
 */

public class Token {


    @SerializedName("token")
    @Expose
    private List<Token> token = null;


    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("expires-in")
    @Expose
    private Integer expiresIn;
    @SerializedName("remaining-usages")
    @Expose
    private Integer remainingUsages;

    public List<Token> getToken() {
        return token;
    }

    public void setToken(List<Token> token) {
        this.token = token;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Integer getRemainingUsages() {
        return remainingUsages;
    }

    public void setRemainingUsages(Integer remainingUsages) {
        this.remainingUsages = remainingUsages;
    }





}
