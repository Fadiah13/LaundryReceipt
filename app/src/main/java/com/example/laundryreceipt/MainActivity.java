package com.example.laundryreceipt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIMEOUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menunda beralih halaman selama beberapa detik
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentSplashScreen = new Intent(MainActivity.this, Login.class);
                startActivity(intentSplashScreen);

                // Menutup activity splash screen
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);

    }
}