package com.example.heathmonitor;


import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.window.SplashScreen;

/**
 * App opening splash screen.
 */

public class Splash_screen extends AppCompatActivity {

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                i = new Intent(Splash_screen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },3000);

    }


}