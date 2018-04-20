package com.example.android.wirecardparking.logic.changepersonaldetails;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.android.wirecardparking.BaseFragment;
import com.example.android.wirecardparking.R;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import butterknife.BindView;

import static android.R.layout.simple_spinner_dropdown_item;
import static com.example.android.wirecardparking.utils.SpinnerHelper.favorite_teacher;
import static com.example.android.wirecardparking.utils.SpinnerHelper.fiend_name;
import static com.example.android.wirecardparking.utils.SpinnerHelper.first_pet;
import static com.example.android.wirecardparking.utils.SpinnerHelper.grandfather_profession;
import static com.example.android.wirecardparking.utils.SpinnerHelper.historic_character;
import static com.example.android.wirecardparking.utils.SpinnerHelper.mother_birth_place;

@FragmentWithArgs
public class ForgotPasswordFragment extends BaseFragment {

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.input_number)
    TextInputLayout input_number;

    @BindView(R.id.spinner)
    Spinner spinner;


    @Override
    protected void init(Bundle savedInstanceState) {


        String[] items = new String[]{
                mother_birth_place,
                fiend_name,
                first_pet,
                favorite_teacher,
                historic_character,
                grandfather_profession

        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);


    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_forgot_password;
    }





}

