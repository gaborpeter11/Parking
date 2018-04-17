package com.example.android.wirecardparking.utils;

/**
 * Created by PepovPC on 4/17/2018.
 */

public final class SpinnerHelper {

    public static final String mother_birth_place= "Mother place of birth";
    public static final String fiend_name= "Best childhood friend name";
    public static final String first_pet= "First pet name";
    public static final String favorite_teacher= "Favourite teacher name";
    public static final String historic_character= "Favourite historic character";
    public static final String grandfather_profession= "Grandfather profession";


    public static String securityAnswerSpinner(String id){

        switch (id){
            case mother_birth_place:
                return "MOTHER_PLACE_OF_BIRTH";
            case fiend_name:
                return "BEST_CHILDHOOD_FRIEND_NAME";
            case first_pet:
                return "FIRST_PET_NAME";
            case favorite_teacher:
                return "FAVOURITE_TEACHER_NAME";
            case historic_character:
                return "FAVOURITE_HISTORIC_CHARACTER";
            case grandfather_profession:
                return "GRANDFATHER_PROFESSION";
            default:
                return id;
        }
    }
}
