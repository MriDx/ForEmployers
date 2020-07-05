package com.Creation.App;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;

import java.util.concurrent.Executor;

/**
 * Created by Belal on 2/3/2016.
 */

//Our class extending fragment
@SuppressWarnings({"ConstantConditions", "FinalizeCalledExplicitly"})
public class Tab4 extends Fragment {
    TextView fullName,phone,email,companyName;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UserId;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.tab4, container, false);
        fullName = view.findViewById(R.id.profileName);
        phone = view.findViewById(R.id.profilePhone);
        email = view.findViewById(R.id.profileEmail);
        companyName = view.findViewById(R.id.profileCompanyName);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        UserId = fAuth.getCurrentUser().getUid();



        return view;
    }
}