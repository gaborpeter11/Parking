package com.example.android.wirecardparking;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.wirecardparking.rest.ApiClient;
import com.example.android.wirecardparking.rest.model.MoviesResponse;
import com.example.android.wirecardparking.rest.model.SkusPUT;
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

    @Override
    protected void init(Bundle savedInstanceState) {


//        button.setOnClickListener(v -> {
            //changeTo(new MainFragmentBuilder().type("popular").build());
//            ApiClient.getApiService().putNewUser("MR", "Pavol", "Mate", "+44875464469",
//                    "pavol.mate@wirecard.com", "44875464467i7407v1.14.2", "aaaa1111", "BEST_CHILDHOOD_FRIEND_NAME",
//                    "Willi", "true")
        SkusPUT skus = new SkusPUT();
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
//        });


        Toast.makeText(getActivity(), type,
                Toast.LENGTH_LONG).show();
    }

    private void handleError(Throwable throwable) throws IOException {
        HttpException err = (HttpException) throwable;
        String errBody = err.response().errorBody().string();
        System.out.println(errBody);
    }


    public void handleResponse(MoviesResponse response){
//        String str1 = response.getFirstName();
//        String str = response.getLastName();
//        String op  = response.getSecurityQuestion().getQuestion();
        System.out.println("" + "de");
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_main;
    }



}

