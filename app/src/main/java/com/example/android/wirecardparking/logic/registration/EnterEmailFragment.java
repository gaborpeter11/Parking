package com.example.android.wirecardparking.logic.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.wirecardparking.BaseFragment;
import com.example.android.wirecardparking.DruhyFragment;
import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.SignUpRequestBuilder;
import com.example.android.wirecardparking.activities.MainActivity;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import butterknife.BindView;

@FragmentWithArgs
public class EnterEmailFragment extends BaseFragment {

    @Arg(required = false)
    String type;

    @BindView(R.id.button)
    Button button;

    private SignUpRequestBuilder signUpRequestBuilder;


    public static EnterEmailFragment newInstance(SignUpRequestBuilder builder) {
        EnterEmailFragment fragment = new EnterEmailFragment();
        fragment.signUpRequestBuilder = builder;
        return fragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        ((MainActivity) activity).getToolbar().setTitle("DOBRE");


        Toast.makeText(getActivity(), "SOM KDE?",
                Toast.LENGTH_LONG).show();

        button.setOnClickListener(v -> {
            startFragment(DruhyFragment.newInstance(signUpRequestBuilder));
        });


    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_enter_email;
    }


    public void onLick(View view){


    }


}

