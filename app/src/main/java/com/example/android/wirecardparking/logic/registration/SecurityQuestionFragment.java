package com.example.android.wirecardparking.logic.registration;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.wirecardparking.BaseFragment;
import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.rest.ApiClient;
import com.example.android.wirecardparking.rest.model.RegisterRequest;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

import static android.R.layout.simple_spinner_dropdown_item;
import static com.example.android.wirecardparking.utils.SpinnerHelper.favorite_teacher;
import static com.example.android.wirecardparking.utils.SpinnerHelper.fiend_name;
import static com.example.android.wirecardparking.utils.SpinnerHelper.first_pet;
import static com.example.android.wirecardparking.utils.SpinnerHelper.grandfather_profession;
import static com.example.android.wirecardparking.utils.SpinnerHelper.historic_character;
import static com.example.android.wirecardparking.utils.SpinnerHelper.mother_birth_place;
import static com.example.android.wirecardparking.utils.SpinnerHelper.securityAnswerSpinner;
import static com.example.android.wirecardparking.utils.ValidatorHelper.validateEmptyField;
import static com.example.android.wirecardparking.utils.ValidatorHelper.validateLength;

@FragmentWithArgs
public class SecurityQuestionFragment extends BaseFragment {

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.security_answer_input)
    TextInputLayout security_answer_input;

    private RegisterRequest signUpRequestBuilder;


    public static SecurityQuestionFragment newInstance(RegisterRequest builder) {
        SecurityQuestionFragment fragment = new SecurityQuestionFragment();
        fragment.signUpRequestBuilder = builder;
        return fragment;
    }

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


        button.setOnClickListener(v -> {
            signUpRequestBuilder.setSecurityQuestion(securityAnswerSpinner(spinner.getSelectedItem().toString()));
            signUpRequestBuilder.setSecurityAnswer(security_answer_input.getEditText().getText().toString());

            ApiClient.getApiService().putNewUser(signUpRequestBuilder)
                    .subscribeOn(Schedulers.io())
                    .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse,
                            this::handleError );

            //startFragment(VerifyEmailFragment.newInstance(signUpRequestBuilder));
        });


        RxTextView.textChangeEvents(security_answer_input.getEditText())
                .skip(1)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(textViewTextChangeEvent -> validate());


    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_security_question;
    }


    public void validate(){
        if(validateEmptyField(security_answer_input)){
            if(validateLength(security_answer_input, 5)) {
                button.setEnabled(true);
                return;
            }
        }
        button.setEnabled(false);
    }


    private void handleError(Throwable throwable) throws IOException {
//        HttpException err = (HttpException) throwable;
//        String errBody = err.response().errorBody().string();
//        System.out.println(errBody);
        Toast.makeText(getActivity(), "Failed to register",
                Toast.LENGTH_SHORT).show();
    }


    public void handleResponse(RegisterRequest response){

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Success")
                .setMessage("You are now registered! Go log in")
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    //spusti login fragment
                })
                //.setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


}

