package com.joker.foodcourtapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.joker.foodcourtapp.utils.Variable;

public class SplashActivity extends Activity {

    private Context c;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        c = getApplicationContext();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!Variable.getFirstOpen(c)){
                }else {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }

                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
