package com.marvel.android.a1000salama;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.marvel.android.a1000salama.Login.Login;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    //   Thread.sleep(1000);
                    Intent i;
                    i = new Intent(Splash.this, Login.class);
                    startActivity(i);

                    finish();
                } catch (Exception e) {

                }
            }
        }, 1500);
    }
}
