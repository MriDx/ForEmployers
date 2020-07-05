package com.Creation.App;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {
    Context context;



    public void removeUser(){
        sharedPreferences.edit().clear().apply();
    }

    public String getEmail() {
        email = sharedPreferences.getString("userdata", "");
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        sharedPreferences.edit().putString("userdata", email).apply();
    }

    private String email;


    SharedPreferences sharedPreferences;


    public UserSession(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Userinfo", Context.MODE_PRIVATE);

    }

}
