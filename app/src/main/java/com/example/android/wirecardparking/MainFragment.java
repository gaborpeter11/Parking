package com.example.android.wirecardparking;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.wirecardparking.rest.ApiClient;
import com.example.android.wirecardparking.rest.model.getallplaces.ParkingHouses;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@FragmentWithArgs
public class MainFragment extends BaseFragment {

    //String op = NetworkUtils.putOnServer2();

    @Arg(required = false)
    String type;

    @BindView(R.id.button)
    Button button;



    public static BaseFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }


    @Override
    protected void init(Bundle savedInstanceState) {

//activity.setSupportActionBar(((MainActivity) activity).getToolbar());
        Toast.makeText(getActivity(), "Fragment PRVY",
                Toast.LENGTH_SHORT).show();


        //signUpRequestBuilder.setText("Teeeeeeext ideeeeeee");



//        RegisterRequest skus = new RegisterRequest();
//        skus.setSalutation("MR");
//        skus.setFirstName("Pavol");
//        skus.setLastName("Mate");
//        skus.setMobileNumber("+44875464469");
//        skus.setEmail("pavol.mate@wirecard.com");
//        skus.setUserName("44875464467i7407v1.14.2");
//        skus.setPassword("aaaa1111");
//        skus.setSecurityQuestion("BEST_CHILDHOOD_FRIEND_NAME");
//        skus.setSecurityAnswer("Willi");
//        skus.setTermsOfUseAccepted("true");



        ApiClient.getApiService().getAllPlaces("Bearer 9623e0a6-12d1-4251-b36b-5529e8546a60")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse,
                            this::handleError );



    }

    private void handleError(Throwable throwable) throws IOException {
//        HttpException err = (HttpException) throwable;
//        String errBody = err.response().errorBody().string();
        System.out.println(throwable.getMessage());
    }


    public void handleResponse(ParkingHouses response){

        ArrayList<ParkingHouses.ParkingPlaces> hih = new ArrayList<>(response.getHouses());


        for(int i = 0; i < hih.size(); i++){
            for(int j = 0; j < hih.get(i).getPlaces().size(); j++){
                for(int k = 0; k < hih.get(i).getPlaces().get(j).getAvailableSpots().size(); k++){
                    String sop = hih.get(i).getPlaces().get(j).getAvailableSpots().get(k).getDay();
                    if(hih.get(i).getPlaces().get(j).getAvailableSpots().get(k).getEachDayUser()!= null) {
                        String sopel = hih.get(i).getPlaces().get(j).getAvailableSpots().get(k).getEachDayUser().getMobile();
                        System.out.println(sopel);
                    }
                }
            }
        }


    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_main;
    }



}

