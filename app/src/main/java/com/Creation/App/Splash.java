package com.Creation.App;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
@SuppressWarnings("StringEquality")
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
      //  getSupportActionBar().hide(); //hide the title bar
        final UserSession userSession= new UserSession(Splash.this);
        setContentView(R.layout.activity_splash);



        Thread myThread;
        myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(1000);
                    Intent intent = new Intent(getApplicationContext(), ClientCheck.class);
                    startActivity(intent);
                    finish();
                    /**
                    if(userSession.getEmail() !="") {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("name",userSession.getEmail());
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();
                    }
                     **/
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }

}
