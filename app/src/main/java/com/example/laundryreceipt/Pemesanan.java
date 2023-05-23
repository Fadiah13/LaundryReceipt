package com.example.laundryreceipt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Pemesanan extends AppCompatActivity {

    private DatabaseReference pemesananmasukRef;
    private RecyclerView recyclerView;
    private AdapterPemesananbaru adapter;

    public Pemesanan(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        recyclerView = findViewById(R.id.rv_pemesananmasuk);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterPemesananbaru(this);
        adapter.setPemesananbaruList(new ArrayList<>()); // pass empty ArrayList using setEventsList()
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdapterPemesananbaru.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                startActivity(new Intent(Pemesanan.this, PaketLayanan.class));
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.bottomPemesanan);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottomHome:
                    startActivity(new Intent(Pemesanan.this, Utama.class));
                    break;
                case R.id.bottomStatistik:
                    startActivity(new Intent(Pemesanan.this, Statistik.class));
                    break;
                case R.id.bottomPemesanan:
                    break;
                case R.id.bottomSettings:
                    startActivity(new Intent(Pemesanan.this, Settings.class));
                    break;
            }
            return true;
        });
    }
}