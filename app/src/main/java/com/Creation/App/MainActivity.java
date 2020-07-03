package com.Creation.App;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UserId;
    Button VerifyEmail;
    TextView textEmailVerified;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        VerifyEmail = findViewById(R.id.verifyEmail);
        textEmailVerified = findViewById(R.id.textEmailVerified);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        UserId = fAuth.getCurrentUser().getUid();
        FirebaseUser user = fAuth.getCurrentUser();

        if(!user.isEmailVerified()) {
            VerifyEmail.setVisibility(View.VISIBLE);
            textEmailVerified.setVisibility(View.VISIBLE);
            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(MainActivity.this, "Email Verification Link is Send to your Email.", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("TAG", "onFailure: Email Verification link is not sent"+ e.getMessage());
                }
            });
        }
    }
    public void LogOut(View view) {
        FirebaseAuth.getInstance().signOut();
         new UserSession(MainActivity.this).removeUser();
        startActivity(new Intent(MainActivity.this,Login.class));
        finish();
    }
    public void ProfileTab(View view) {
        startActivity(new Intent(getApplicationContext(),ProfileTab.class));
        finish();
    }
}