package com.example.android.wirecardparking.utils;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by PepovPC on 4/16/2018.
 */

public final class ValidatorHelper {



    public static boolean validateEmptyField(EditText input){
        if(input.getText().toString().isEmpty()){
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




}
