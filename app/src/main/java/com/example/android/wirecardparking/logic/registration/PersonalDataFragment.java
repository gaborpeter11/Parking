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

import butterknife.BindView;

import static android.R.layout.simple_spinner_dropdown_item;
import static com.example.android.wirecardparking.utils.ValidatorHelper.validateEmptyField;
import static com.example.android.wirecardparking.utils.ValidatorHelper.validatePasswords;

@FragmentWithArgs
public class PersonalDataFragment extends BaseFragment {

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.input_name)
    TextInputLayout input_name;

    @BindView(R.id.input_surname)
    TextInputLayout input_surname;

    @BindView(R.id.input_pwd1)
    TextInputLayout input_pwd1;

    @BindView(R.id.input_pwd2)
    TextInputLayout input_pwd2;

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
            if(validate()){
                signUpRequestBuilder.setSalutation(spinner.getSelectedItem().toString());
                signUpRequestBuilder.setFirstName(input_name.getEditText().getText().toString());
                signUpRequestBuilder.setLastName(input_surname.getEditText().getText().toString());
                signUpRequestBuilder.setPassword(input_pwd1.getEditText().getText().toString());
                signUpRequestBuilder.setUserName(signUpRequestBuilder.getMobileNumber()+"v2");
                signUpRequestBuilder.setTermsOfUseAccepted("true");
                startFragment(SecurityQuestionFragment.newInstance(signUpRequestBuilder));
            }
        });


    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_personal_data;
    }


    public boolean validate() {

        if (validateEmptyField(input_name) && validateEmptyField(input_surname)
                && validateEmptyField(input_pwd1) && validateEmptyField(input_pwd2)) {
            if(validatePasswords(input_pwd1, input_pwd2)){
                return true;
            }
        }

        return false;
    }

}

