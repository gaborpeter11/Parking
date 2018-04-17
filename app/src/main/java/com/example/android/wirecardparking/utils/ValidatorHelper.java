package com.example.android.wirecardparking.utils;

import android.support.design.widget.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by PepovPC on 4/16/2018.
 */

public final class ValidatorHelper {



    public static boolean validateEmptyField(TextInputLayout input){
        if(input.getEditText().getText().toString().isEmpty()){
            input.setError("Required field");
            return false;
        }else{
            input.setError(null);
            return true;
        }
    }



    public static boolean isEmailValid(TextInputLayout input){
        String email = input.getEditText().getText().toString();
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        if(matcher.matches()) {
            input.setError(null);
            return true;
        }
        else {
            input.setError("Email in wrong format");
            return false;
        }
    }


    public static boolean validatePasswords(TextInputLayout input, TextInputLayout input2){
        if(input.getEditText().length() < 4){
            input.setError("Password needs minimum 4 characters.");
            return false;
        }else if(!input.getEditText().getText().toString().equals(input2.getEditText().getText().toString())){
            input.setError("Password does not match.");
            return false;
        }else{
            input.setError(null);
            return true;
        }

    }


    public static boolean validatePhoneNumber(TextInputLayout input){

        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(regex);


        Matcher matcher = pattern.matcher(input.getEditText().getText().toString());

        if(matcher.matches()) {
            input.setError(null);
            return true;
        }

        input.setError("Phone number in wrong format");
        return false;

    }


    public static boolean validateLength(TextInputLayout input, int len){

        if(input.getEditText().length()<len){
            input.setError("Need at least "+ len + " characters.");
            return false;
        }

        input.setError(null);
        return true;

    }



}
