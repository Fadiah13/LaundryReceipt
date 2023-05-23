package com.example.laundryreceipt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

public class Pengambilan extends AppCompatActivity {

    private HelperClassPemesananMasuk selectedPemesananMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengambilan);

        selectedPemesananMasuk = (HelperClassPemesananMasuk) getIntent().getSerializableExtra("selectedPemesananMasuk");

        // Use the selectedPemesananMasuk object as needed
        if (selectedPemesananMasuk != null) {
            // Example usage: Display the selected data
            String nopesanan = selectedPemesananMasuk.getNopesanan();
            String nama = selectedPemesananMasuk.getNama();
            String nohp = selectedPemesananMasuk.getNohp();
            String estimasi = selectedPemesananMasuk.getEstimasi();
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Detail Layanan");
        }

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