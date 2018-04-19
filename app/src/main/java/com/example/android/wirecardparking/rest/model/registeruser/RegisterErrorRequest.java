package com.example.android.wirecardparking.rest.model.registeruser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterErrorRequest {

    @SerializedName("error-code")
    @Expose
    private String errorCode;
    @SerializedName("error-key")
    @Expose
    private String errorKey;
    @SerializedName("error-message")
    @Expose
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}