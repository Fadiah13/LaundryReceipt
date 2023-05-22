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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IsiLayanan extends AppCompatActivity {

    private EditText Kuantitas;
    private EditText Hari;
    private EditText Harga;
    private Button butLanjutkan;
    private DatabaseReference databaseRef;
    private int userId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_layanan);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Detail Layanan");
        }


        Kuantitas = findViewById(R.id.textKuantitas);
        Hari = findViewById(R.id.textHari);
        Harga = findViewById(R.id.textHarga);
        butLanjutkan = findViewById(R.id.buttonpopuplayanan);

        butLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kuantitas = Kuantitas.getText().toString();
                String hari = Hari.getText().toString();
                String harga = Harga.getText().toString();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("datapemesanan");
                String userIdString1 = String.valueOf(userId);
                HelperClassDetailLayanan HelperClassdetaillayanan = new HelperClassDetailLayanan(null,kuantitas, hari, harga);
                databaseReference.child(userIdString1).child("Paketlayanan").child("detailLayanan").setValue(HelperClassdetaillayanan);
                userId++;

                Intent intent = new Intent(IsiLayanan.this, DetailLayanan.class);
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