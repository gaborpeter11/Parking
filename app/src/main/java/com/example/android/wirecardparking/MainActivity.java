package com.example.android.wirecardparking;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;


// maybe create job for updating movies from web
public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void init(Bundle savedInstanceState) {

        //setSupportActionBar(toolbar);

        toolbar.setTitle("popular");
        changeTo(new MainFragmentBuilder().type("popular").build());
        //changeTo(new MainFragment());


    }

    public Toolbar getToolbar() {
        return toolbar;
    }


    @Override
    public int getResLayoutId() {
        return R.layout.activity_base;
    }





}

