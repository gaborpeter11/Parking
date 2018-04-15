package com.example.android.wirecardparking.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.android.wirecardparking.BaseActivity;
import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.SignUpRequestBuilder;
import com.example.android.wirecardparking.logic.registration.EnterEmailFragment;

import butterknife.BindView;


public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @SuppressLint("RestrictedApi")
    @Override
    public void init(Bundle savedInstanceState) {

        //setSupportActionBar(toolbar);

        toolbar.setTitle("popular");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //changeTo(new MainFragmentBuilder().type("popular").build());

        SignUpRequestBuilder builder = new SignUpRequestBuilder();

        changeTo(EnterEmailFragment.newInstance(builder));


    }


    public Toolbar getToolbar() {
        return toolbar;
    }


    @Override
    public int getResLayoutId() {
        return R.layout.activity_base;
    }




}

