package com.example.android.wirecardparking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.fragmentargs.FragmentArgs;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    public BaseActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (BaseActivity) getActivity();
        FragmentArgs.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getResLayoutId(), container, false);
        ButterKnife.bind(this, view);
        init(savedInstanceState);
        return view;
    }


    public void startFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }



    protected abstract void init(Bundle savedInstanceState);

    protected abstract int getResLayoutId();
}
