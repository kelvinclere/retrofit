package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent myIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(myIntent);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
    }
}