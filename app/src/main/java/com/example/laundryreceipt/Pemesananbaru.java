package com.example.laundryreceipt;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Pemesananbaru extends AppCompatActivity {
    Button btnkonsumenbaru;
    private DatabaseReference pemesananbaruRef;
    private RecyclerView recyclerView;
    private AdapterPemesananbaru adapter;

    public Pemesananbaru() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesananbaru);

        recyclerView = findViewById(R.id.rv_pemesananbaru);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterPemesananbaru(this);
        adapter.setPemesananbaruList(new ArrayList<>()); // pass empty ArrayList using setEventsList()
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdapterPemesananbaru.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(Pemesananbaru.this, PaketLayanan.class));
            }
        });

        pemesananbaruRef = FirebaseDatabase.getInstance().getReference().child("konsumen");

        // Listen for changes to the events data and update the adapter
        pemesananbaruRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<HelperClassPemesananbaru> pemesananbaruList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String nama = snapshot.child("nama").getValue(String.class);
                    String nohp = snapshot.child("nohp").getValue(String.class);
                    String alamat = snapshot.child("alamat").getValue(String.class);
                    HelperClassPemesananbaru pemesananbaru = new HelperClassPemesananbaru(nama, nohp, alamat);
                    pemesananbaruList.add(pemesananbaru);
                }
                adapter.setPemesananbaruList(pemesananbaruList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
            }
        });


        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Pemesanan Baru");
        }

        btnkonsumenbaru = findViewById(R.id.buttonTambahKonsumen);
        btnkonsumenbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pemesananbaru.this, Konsumenbaru.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}