package com.example.android.wirecardparking.logic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Base64;
import android.widget.Button;

import com.example.android.wirecardparking.BaseFragment;
import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.activities.DashboardActivity;
import com.example.android.wirecardparking.logic.registration.EnterEmailFragment;
import com.example.android.wirecardparking.rest.ApiClient;
import com.example.android.wirecardparking.rest.model.Token;
import com.example.android.wirecardparking.rest.model.registeruser.RegisterRequest;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.io.IOException;

import butterknife.BindView;
import io.reactivex.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

import static android.content.Context.MODE_PRIVATE;

@FragmentWithArgs
public class LoginFragment extends BaseFragment {

    @BindView(R.id.button_login)
    Button button_login;

    @BindView(R.id.button_signup)
    Button button_signup;

    @BindView(R.id.input_layout)
    TextInputLayout input_layout;

    @BindView(R.id.input_pwd)
    TextInputLayout input_pwd_layout;

    private RegisterRequest signUpRequestBuilder;

    public static final String TOKEN = "saved_token";


    public static LoginFragment newInstance(RegisterRequest builder) {
        LoginFragment fragment = new LoginFragment();
        fragment.signUpRequestBuilder = builder;
        return fragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        input_layout.getEditText().setText("+1651516156");
        input_pwd_layout.getEditText().setText("1111");

        if( activity.getSupportActionBar() != null)
            activity.getSupportActionBar().hide();


        RxTextView.textChangeEvents(input_layout.getEditText())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(textViewTextChangeEvent -> {
                    input_pwd_layout.setError(null);
                });

        RxTextView.textChangeEvents(input_pwd_layout.getEditText())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(textViewTextChangeEvent -> {
                    input_pwd_layout.setError(null);
                });


        button_login.setOnClickListener(v -> {

            String num = input_layout.getEditText().getText().toString();
            String pwd = input_pwd_layout.getEditText().getText().toString();

            String credentials = num + ":" + pwd;
            String autToken = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            ApiClient.getApiService().loginUser(autToken)
                    .subscribeOn(Schedulers.io())
                    .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse,
                            this::handleError );

        });

        button_signup.setOnClickListener(v -> {
            startFragment(EnterEmailFragment.newInstance(signUpRequestBuilder));
            activity.getSupportActionBar().show();
        });

    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_login;
    }


    private void handleError(Throwable throwable) throws IOException {
        System.out.println(throwable.getMessage());
        input_pwd_layout.setError("Invalid login credentials");
    }


    public void handleResponse(Token response){

        String tok = response.getToken().get(0).getValue();

        SharedPreferences settings = getContext().getSharedPreferences(TOKEN, MODE_PRIVATE);
        // Writing data to SharedPreferences
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token", tok);
        editor.putString("number", input_layout.getEditText().getText().toString());
        editor.apply();


        Intent myIntent = new Intent(getContext(), DashboardActivity.class);
        startActivity(myIntent);

    }




}

