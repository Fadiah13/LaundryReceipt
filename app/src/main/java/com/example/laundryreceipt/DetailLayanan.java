package com.example.laundryreceipt;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class DetailLayanan extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference detailLayananref;
    private AdapterDetailLayanan adapter;
    Button paketLayanan,bayar;
    private int userId = 1;
    EditText totalBayar;


    public DetailLayanan(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layanan);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Detail Layanan");
        }

        totalBayar = findViewById(R.id.textTotalbayar);
        bayar = findViewById(R.id.buttonbayar);
        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String total = totalBayar.getText().toString();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("datapemesanan");
                String userIdString1 = String.valueOf(userId);
                databaseReference.child(userIdString1).child("pembayaran").child("Totalbayar").setValue(total);
                Intent J = new Intent(DetailLayanan.this, Pembayaran.class);
                J.putExtra("Totalbayar",total);
                startActivity(J);
                userId++;
            }
        });

        paketLayanan = findViewById(R.id.buttonTambahLayanan);
        paketLayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailLayanan.this, PaketLayanan.class);
                startActivity(i);
            }
        });

        recyclerView = findViewById(R.id.pemesanan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterDetailLayanan(this);
        adapter.setDetaillayananList(new ArrayList<>()); // pass empty ArrayList using setEventsList()
        recyclerView.setAdapter(adapter);

        detailLayananref = FirebaseDatabase.getInstance().getReference("datapemesanan");
        detailLayananref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<HelperClassDetailLayanan> detailLayananList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String namalayanan = snapshot.child("Paketlayanan").child("namalayanan").getValue(String.class);
                        String kuantitas = snapshot.child("Paketlayanan").child("detailLayanan").child("kuantitas").getValue(String.class);
                        String hari = snapshot.child("Paketlayanan").child("detailLayanan").child("hari").getValue(String.class);
                        String harga = snapshot.child("Paketlayanan").child("detailLayanan").child("harga").getValue(String.class);
                        HelperClassDetailLayanan detailLayanan = new HelperClassDetailLayanan(namalayanan, kuantitas, hari, harga);
                        detailLayananList.add(detailLayanan);
                }
                adapter.setDetaillayananList(detailLayananList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
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