package com.example.laundryreceipt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    //TextView profil, bhsAplikasi, help, logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView profil = findViewById(R.id.profil);
        TextView bhsAplikasi = findViewById(R.id.bahasa);
        TextView help = findViewById(R.id.help);
        TextView logOut =findViewById(R.id.logOut);

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Settings.this, Login.class);
                startActivity(i);

            }
        });

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