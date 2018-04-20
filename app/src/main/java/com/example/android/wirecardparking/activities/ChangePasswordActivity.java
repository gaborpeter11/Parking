package com.example.android.wirecardparking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.android.wirecardparking.BaseActivity;
import com.example.android.wirecardparking.R;

import butterknife.BindView;


public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void init(Bundle savedInstanceState) {

        getToolbar().setTitle(R.string.app_title);
        setSupportActionBar(getToolbar());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    public Toolbar getToolbar() {
        return toolbar;
    }


    @Override
    public int getResLayoutId() {
        return R.layout.activity_change_password;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent myIntent = new Intent(this, DashboardActivity.class);
                startActivity(myIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



}

