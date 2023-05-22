package com.example.laundryreceipt;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
public class PaketLayanan extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterLayanan adapterLayanan;
    private ArrayList<ModelLayanan> modelLayanan;
    FirebaseDatabase database;
    DatabaseReference reference;

    public PaketLayanan(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paketlayanan);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Pilihan Layanan");
        }

        reference = FirebaseDatabase.getInstance().getReference();

        getData();

        recyclerView = findViewById(R.id.recyclerview);
        adapterLayanan = new AdapterLayanan(modelLayanan);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PaketLayanan.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterLayanan);

        adapterLayanan.setOnItemClickListener(new AdapterLayanan.OnItemClickListener() {
            @Override
            public void onItemClick(ModelLayanan item) {
                Intent intent = new Intent(PaketLayanan.this, IsiLayanan.class);
                startActivity(intent);
            }
        });

    }

    private void getData() {
        modelLayanan = new ArrayList<>();
        modelLayanan.add(new ModelLayanan("Cuci Lipat Kiloan", R.drawable.cucilipatk));
        modelLayanan.add(new ModelLayanan("Cuci Lipat Satuan", R.drawable.cucilipats));
        modelLayanan.add(new ModelLayanan("Cuci Kering Kiloan", R.drawable.cucikeringk));
        modelLayanan.add(new ModelLayanan("Cuci Kering Satuan", R.drawable.cucikerings));
        modelLayanan.add(new ModelLayanan("Cuci Setrika Kiloan", R.drawable.cucisetrikak));
        modelLayanan.add(new ModelLayanan("Cuci Setrika Satuan", R.drawable.cucisetrikas));
        modelLayanan.add(new ModelLayanan("Cuci Express Kiloan", R.drawable.cuciexpressk));
        modelLayanan.add(new ModelLayanan("Cuci Express Satuan", R.drawable.cuciexpresss));
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
