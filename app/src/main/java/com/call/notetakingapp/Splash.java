package com.call.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Boolean notFirstTimeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkFirstTimeUser();
            }
        },5000);
    }

    private void checkFirstTimeUser() {
        sharedPreferences = SharedPreferencesManager.createSharedPreferences(this);
        Intent intent;
        if(sharedPreferences.getBoolean("IS_USER_FIRST_TIME", false)){
            intent = new Intent(Splash.this, Login.class);
        }
        else
        {


            intent = new Intent(Splash.this, OnBoarding.class);
        }
        startActivity(intent);
        finish();
    }
}