package com.example.android.wirecardparking;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.wirecardparking.rest.ApiClient;
import com.example.android.wirecardparking.rest.model.RegisterRequest;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

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


    private SignUpRequestBuilder signUpRequestBuilder;


    public static BaseFragment newInstance(SignUpRequestBuilder builder) {
        MainFragment fragment = new MainFragment();
        fragment.signUpRequestBuilder = builder;
        //fragment.signUpFlowManager = manager;
        return fragment;
    }


    @Override
    protected void init(Bundle savedInstanceState) {


        Toast.makeText(getActivity(), "Fragment PRVY",
                Toast.LENGTH_SHORT).show();


        signUpRequestBuilder.setText("Teeeeeeext ideeeeeee");

          button.setOnClickListener(v -> {
        RegisterRequest skus = new RegisterRequest();
        skus.setSalutation("MR");
        skus.setFirstName("Pavol");
        skus.setLastName("Mate");
        skus.setMobileNumber("+44875464469");
        skus.setEmail("pavol.mate@wirecard.com");
        skus.setUserName("44875464467i7407v1.14.2");
        skus.setPassword("aaaa1111");
        skus.setSecurityQuestion("BEST_CHILDHOOD_FRIEND_NAME");
        skus.setSecurityAnswer("Willi");
        skus.setTermsOfUseAccepted("true");
        ApiClient.getApiService().putNewUser(skus)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse,
                            this::handleError );


              startFragment(DruhyFragment.newInstance(signUpRequestBuilder));

          });



    }

    private void handleError(Throwable throwable) throws IOException {
        HttpException err = (HttpException) throwable;
        String errBody = err.response().errorBody().string();
        System.out.println(errBody);
    }


    public void handleResponse(RegisterRequest response){

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    Toast.makeText(getActivity(), "Registered!!",
                            Toast.LENGTH_LONG).show();
                })
                //.setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_main;
    }



}

