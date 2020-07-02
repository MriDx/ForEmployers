package com.Creation.App;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

public class UserSession {
    FirebaseAuth fAuth;
    Context context;

    public void removeuser() {
        sharedPreferences.edit().clear().commit();
    }

    public String getEmail() {
        email = sharedPreferences.getString("userdata", "");
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        sharedPreferences.edit().putString("userdata", email).commit();
    }

    private String email;


    SharedPreferences sharedPreferences;


    public UserSession(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Userinfo", Context.MODE_PRIVATE);

    }

}
