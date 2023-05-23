package com.example.laundryreceipt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.bottomSettings);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottomHome:
                    startActivity(new Intent(Settings.this, Utama.class));
                    break;
                case R.id.bottomStatistik:
                    startActivity(new Intent(Settings.this, Statistik.class));
                   break;
                case R.id.bottomPemesanan:
                    startActivity(new Intent(Settings.this, Pemesanan.class));
                    break;
                case R.id.bottomSettings:
                    break;

            }
            return true;
        });
    }
}