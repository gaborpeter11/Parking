package com.example.android.wirecardparking.logic.registration;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.android.wirecardparking.BaseFragment;
import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.rest.model.RegisterRequest;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;

import static android.R.layout.simple_spinner_dropdown_item;

@FragmentWithArgs
public class PersonalDataFragment extends BaseFragment {

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.input_name)
    TextInputLayout input_name;

    @BindView(R.id.spinner)
    Spinner spinner;

    private RegisterRequest signUpRequestBuilder;


    public static PersonalDataFragment newInstance(RegisterRequest builder) {
        PersonalDataFragment fragment = new PersonalDataFragment();
        fragment.signUpRequestBuilder = builder;
        return fragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {


        String[] items = new String[]{"Mr.", "Mrs."};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);



        button.setOnClickListener(v -> {
            signUpRequestBuilder.setEmail(input_name.getEditText().getText().toString());
            startFragment(VerifyEmailFragment.newInstance(signUpRequestBuilder));
        });

        RxTextView.textChangeEvents(input_name.getEditText())
                .skip(1)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(textViewTextChangeEvent -> {
                    validate();
                });


    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_personal_data;
    }


    public void validate(){
//        if(ValidatorHelper.validateEmptyField(input_layout)){
//            if(ValidatorHelper.isEmailValid(input_layout)) {
//                button.setEnabled(true);
//                return;
//            }
//        }
//        button.setEnabled(false);
    }




}

