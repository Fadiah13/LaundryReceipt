package com.example.laundryreceipt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Konsumenbaru extends AppCompatActivity {
    EditText namaKonsumen, nomorKonsumen, alamatKonsumen;
    Button buttonkonsumen;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsumenbaru);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Konsumen Baru");
        }

        namaKonsumen = findViewById(R.id.textnamaKonsumen);
        nomorKonsumen = findViewById(R.id.texthpKonsumen);
        alamatKonsumen = findViewById(R.id.textalamatKonsumen);
        buttonkonsumen = findViewById(R.id.simpandata);

        buttonkonsumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("konsumen");
                String nama = namaKonsumen.getText().toString();
                String nohp = nomorKonsumen.getText().toString();
                String alamat = alamatKonsumen.getText().toString();
                HelperClassPemesananbaru HelperClassPemesananbaru = new HelperClassPemesananbaru(nama, nohp, alamat);
                reference.child(nama).setValue(HelperClassPemesananbaru);

                Intent intent = new Intent(Konsumenbaru.this, Pemesananbaru.class);
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