package com.example.android.wirecardparking.logic.registration;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;

import com.example.android.wirecardparking.BaseFragment;
import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.rest.model.RegisterRequest;
import com.example.android.wirecardparking.utils.ValidatorHelper;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;

@FragmentWithArgs
public class EnterEmailFragment extends BaseFragment {

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.input_layout)
    TextInputLayout input_layout;

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
            startFragment(VerifyEmailFragment.newInstance(signUpRequestBuilder));
        });

        RxTextView.textChangeEvents(input_layout.getEditText())
                .skip(1)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(textViewTextChangeEvent -> validate());


    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_enter_email;
    }


    public void validate(){
        if(ValidatorHelper.validateEmptyField(input_layout.getEditText())){
            if(ValidatorHelper.isEmailValid(input_layout)) {
                button.setEnabled(true);
                return;
            }
        }
        button.setEnabled(false);
    }




}

