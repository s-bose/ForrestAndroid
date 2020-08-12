package com.example.logindemo;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;


public class WelcomeActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        preferences = PreferenceManager.getDefaultSharedPreferences(WelcomeActivity.this);
        userName = preferences.getString("USER_MAIL", "");


        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if (userName.equals("")) {
                            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        }
                        else {
                            startActivity(new Intent(WelcomeActivity.this, HomePageActivity.class));
                        }
                        WelcomeActivity.this.finish();
                    }
                }, 4000);
    }




}