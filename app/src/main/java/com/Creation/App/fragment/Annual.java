package com.Creation.App.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.Creation.App.R;

public class Annual extends Fragment {
    public Annual() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_annual, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //do your work here
    }
}
