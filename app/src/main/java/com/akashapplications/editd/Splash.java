package com.akashapplications.editd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1500);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Routing to Home container
                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                        finish();
                    }
                });
            }
        }).start();
    }
}
