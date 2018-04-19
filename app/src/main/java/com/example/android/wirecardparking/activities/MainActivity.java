package com.example.android.wirecardparking.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.android.wirecardparking.BaseActivity;
import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.logic.LoginFragment;
import com.example.android.wirecardparking.rest.model.registeruser.RegisterRequest;

import butterknife.BindView;


public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void init(Bundle savedInstanceState) {

        getToolbar().setTitle(R.string.app_title);
        setSupportActionBar(getToolbar());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RegisterRequest builder = new RegisterRequest();
        changeTo(LoginFragment.newInstance(builder));
        //changeTo(Tab1Fragment.newInstance());
    }


    public Toolbar getToolbar() {
        return toolbar;
    }


    @Override
    public int getResLayoutId() {
        return R.layout.activity_base;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                } else {
                    //nerob nic
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



}

