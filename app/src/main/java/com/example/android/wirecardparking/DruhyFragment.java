package com.example.android.wirecardparking;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

@FragmentWithArgs
public class DruhyFragment extends BaseFragment {

    //String op = NetworkUtils.putOnServer2();

    @Arg(required = false)
    String type;

    @Override
    protected void init(Bundle savedInstanceState) {
        //activity.setSupportActionBar(((MainActivity) activity).getToolbar());

        Toast.makeText(getActivity(), type,
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_main;
    }


    public void onLick(View view){


    }


}

