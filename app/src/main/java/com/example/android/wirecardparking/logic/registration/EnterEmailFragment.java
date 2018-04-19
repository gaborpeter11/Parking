package com.example.android.wirecardparking.logic.registration;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;

import com.example.android.wirecardparking.BaseFragment;
import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.rest.model.registeruser.RegisterRequest;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;

import static com.example.android.wirecardparking.utils.ValidatorHelper.isEmailValid;
import static com.example.android.wirecardparking.utils.ValidatorHelper.validateEmptyField;
import static com.example.android.wirecardparking.utils.ValidatorHelper.validatePhoneNumber;

@FragmentWithArgs
public class EnterEmailFragment extends BaseFragment {

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.input_layout)
    TextInputLayout input_layout;

    @BindView(R.id.input_number)
    TextInputLayout input_number;

    private RegisterRequest signUpRequestBuilder;


    public static EnterEmailFragment newInstance(RegisterRequest builder) {
        EnterEmailFragment fragment = new EnterEmailFragment();
        fragment.signUpRequestBuilder = builder;
        return fragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        button.setOnClickListener(v -> {
            signUpRequestBuilder.setEmail(input_layout.getEditText().getText().toString());
            signUpRequestBuilder.setMobileNumber(input_number.getEditText().getText().toString());
            startFragment(VerifyEmailFragment.newInstance(signUpRequestBuilder));
        });

        RxTextView.textChangeEvents(input_layout.getEditText())
                .skip(1)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(textViewTextChangeEvent -> {
                    if(validateEmail()){
                        enableNextButton(validateNumber());
                    }
                });

        RxTextView.textChangeEvents(input_number.getEditText())
                .skip(1)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(textViewTextChangeEvent -> {
                    if(validateNumber()){
                        enableNextButton(validateEmail());
                    }
                });



    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_enter_email;
    }


    public void enableNextButton(Boolean enable){

        button.setEnabled(enable);
    }


    public boolean validateEmail(){
        if(validateEmptyField(input_layout)){
            if(isEmailValid(input_layout)) {
                button.setEnabled(true);
                return true;
            }
        }
        return false;
    }


    public boolean validateNumber(){

        if(validateEmptyField(input_number)){
            if(validatePhoneNumber(input_number)) {
                return true;
            }
        }
        return false;
    }




}

