package com.example.laundryreceipt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utama extends AppCompatActivity {

    Button buttonPM, buttonPL, pesananmasuk, pesananankeluar;
    TextView timestampTextView, nmlr;

    private DatabaseReference utamaRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama_coba);

        buttonPM = findViewById(R.id.buttonPemesanan);
        nmlr = findViewById(R.id.namaLaundry);
        pesananmasuk = findViewById(R.id.buttonPesanmasuk);
        pesananankeluar = findViewById(R.id.buttonPesankeluar);
        buttonPL = findViewById(R.id.buttonPelunasan);
        timestampTextView = (TextView) findViewById(R.id.timestamp_TextView);
        Calendar calender = Calendar .getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String timestamp = simpleDateFormat.format(calender.getTime());
        timestampTextView.setText(timestamp);

        buttonPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUtama = new Intent(Utama.this, Pemesananbaru.class);
                startActivity(intentUtama);
            }
        });

        pesananmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Utama.this, Pemesanan.class);
                startActivity(intent);
            }
        });

        pesananankeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Utama.this, PemesananKeluar.class);
                startActivity(intent);
            }
        });

        buttonPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Utama.this, Pengambilan.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.bottomHome);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottomHome:
                    break;
                case R.id.bottomStatistik:
                    startActivity(new Intent(getApplicationContext(), Statistik.class));
                    break;
                case R.id.bottomPemesanan:
                    startActivity(new Intent(getApplicationContext(), Pemesanan.class));
                    break;
                case R.id.bottomSettings:
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                    break;
            }
            return true;
        });

        utamaRef = FirebaseDatabase.getInstance().getReference("users");
        utamaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String nama = snapshot.child("nama").getValue(String.class);
                    // Display the retrieved data in TextViews
                    nmlr.setText(nama);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
            }
        });
    }
}