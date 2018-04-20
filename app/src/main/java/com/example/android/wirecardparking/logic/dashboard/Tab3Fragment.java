package com.example.android.wirecardparking.logic.dashboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.android.wirecardparking.BaseFragment;
import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.activities.ChangePasswordActivity;
import com.example.android.wirecardparking.activities.MainActivity;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import butterknife.BindView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.android.wirecardparking.logic.LoginFragment.TOKEN;

@FragmentWithArgs
public class Tab3Fragment extends BaseFragment {

    @BindView(R.id.button_change_pwd)
    LinearLayout button_change_pwd;

    @BindView(R.id.button_logout)
    LinearLayout button_logout;

    @Override
    protected void init(Bundle savedInstanceState) {

        button_change_pwd.setOnClickListener(v -> {
            Intent myIntent = new Intent(getContext(), ChangePasswordActivity.class);
            startActivity(myIntent);
        });


        button_logout.setOnClickListener(v -> {
            SharedPreferences settings = getContext().getSharedPreferences(TOKEN, MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("token", "");
            editor.putString("number", "");
            editor.apply();

            Intent myIntent = new Intent(getContext(), MainActivity.class);
            startActivity(myIntent);
        });


    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_tab3;
    }



}

