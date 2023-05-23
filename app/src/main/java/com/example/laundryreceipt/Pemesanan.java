package com.example.laundryreceipt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Pemesanan extends AppCompatActivity {

    private DatabaseReference pemesananmasukRef;
    private RecyclerView recyclerView;
    private AdapterPemesananMasuk adapter;

    public Pemesanan(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        recyclerView = findViewById(R.id.rv_pemesananmasuk);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterPemesananMasuk(this);
        adapter.setPemesananmasukList(new ArrayList<>()); // pass empty ArrayList using setEventsList()
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdapterPemesananMasuk.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                HelperClassPemesananMasuk helperClassPemesananMasuk = adapter.getPemesananmasukList().get(position);
                // Pass the selected data to the Pengambilan activity
                Intent intent = new Intent(Pemesanan.this, Pengambilan.class);
                intent.putExtra("helperclassPemesananMasuk", helperClassPemesananMasuk);
                startActivity(intent);

                // Remove the selected item from the pemesananmasukList
                adapter.getPemesananmasukList().remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

        pemesananmasukRef = FirebaseDatabase.getInstance().getReference().child("datapemesanan");
        pemesananmasukRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataaaaaaaSnapshot) {
                List<HelperClassPemesananMasuk> pemesananmasukList = new ArrayList<>();
                for (DataSnapshot snapshot : dataaaaaaaSnapshot.getChildren()) {
                    String nopesanan = snapshot.child("kodeResi").getValue(String.class);
                    String nama = snapshot.child("nama").getValue(String.class);
                    String nohp = snapshot.child("noTelp").getValue(String.class);
                    String estimasi = snapshot.child("Paketlayanan").child("detailLayanan").child("hari").getValue(String.class);
                    HelperClassPemesananMasuk pemesananmasuk = new HelperClassPemesananMasuk(nopesanan, nama, nohp,estimasi);
                    pemesananmasukList.add(pemesananmasuk);
                }
                adapter.setPemesananmasukList(pemesananmasukList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
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