package com.Creation.App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UserId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        UserId = fAuth.getCurrentUser().getUid();
        FirebaseUser user = fAuth.getCurrentUser();

        if(!user.isEmailVerified()){
            startActivity(new Intent(getApplicationContext(),VerificationPage.class));
        }







    }

    public void LogOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }


    public void ProfileTab(View view) {
        startActivity(new Intent(getApplicationContext(),ProfileTab.class));
        finish();
    }
}