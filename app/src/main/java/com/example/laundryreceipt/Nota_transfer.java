package com.example.laundryreceipt;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

public class Nota_transfer extends AppCompatActivity {
    Button utama;
    ImageButton cetaknotaTransfer, kirimnotawaTransfer;
    private DatabaseReference notatransferRef;
    private  DatabaseReference adminAtmref;
    private DatabaseReference notawatransferRef;
    private AdapterNota adapter;
    private RecyclerView recyclerView;
    TextView Nama, Norek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_transfer);

        utama = findViewById(R.id.transferbuttonHalamanutama);
        cetaknotaTransfer = findViewById(R.id.transfercetaknota);
        kirimnotawaTransfer = findViewById(R.id.transferkirimnotawa);
        Nama = findViewById(R.id.textnamabank);
        Norek = findViewById(R.id.textnorek);

        utama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Nota_transfer.this, Utama.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.rv_notatransfer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterNota(this);
        adapter.setNotaList(new ArrayList<>()); // pass empty ArrayList using setEventsList()
        recyclerView.setAdapter(adapter);

        notatransferRef = FirebaseDatabase.getInstance().getReference("datapemesanan");
        notatransferRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataaaaSnapshot) {
                List<HelperClassNota> notaList = new ArrayList<>();
                for (DataSnapshot snapshot : dataaaaSnapshot.getChildren()) {
                    String namalayanan = snapshot.child("Paketlayanan").child("namalayanan").getValue(String.class);
                    String total = snapshot.child("pembayaran").child("Totalbayar").getValue(String.class);
                    HelperClassNota nota = new HelperClassNota(namalayanan, total);
                    notaList.add(nota);
                    String nama = snapshot.child("nama").getValue(String.class);
                    String norek = snapshot.child("norek").getValue(String.class);
                    // Display the retrieved data in TextViews
                    Nama.setText(nama);
                    Norek.setText(norek);
                }
                adapter.setNotaList(notaList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adminAtmref = FirebaseDatabase.getInstance().getReference("users");
        adminAtmref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataaSnapshot) {
                for (DataSnapshot snapshot : dataaSnapshot.getChildren()) {
                    String nama = snapshot.child("nama").getValue(String.class);
                    String norek = snapshot.child("norek").getValue(String.class);
                    // Display the retrieved data in TextViews
                    Nama.setText(nama);
                    Norek.setText(norek);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        kirimnotawaTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notawatransferRef = FirebaseDatabase.getInstance().getReference("datapemesanan");
                notawatransferRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataaaSnapshot) {
                        for (DataSnapshot snapshot : dataaaSnapshot.getChildren()) {
                            String koderesi = snapshot.child("kodeResi").getValue(String.class);
                            String nama = snapshot.child("nama").getValue(String.class);
                            String namalayanan = snapshot.child("Paketlayanan").child("namalayanan").getValue(String.class);
                            String kuantitas = snapshot.child("Paketlayanan").child("detailLayanan").child("kuantitas").getValue(String.class);
                            String estimasi = snapshot.child("Paketlayanan").child("detailLayanan").child("hari").getValue(String.class);
                            String totalbayar = snapshot.child("pembayaran").child("Totalbayar").getValue(String.class);

                            //mengirim ke nohp yang dituju
                            String nohp = snapshot.child("noTelp").getValue(String.class);
                            // Create the message string
                            StringBuilder message = new StringBuilder();
                            message.append("Nota LAUNDRYRECEIPT:\n");
                            message.append("Nomor Pemesanan: ").append(koderesi).append("\n");
                            message.append("Pelanggan yth: ").append(nama).append("\n");
                            message.append("Paket Layanan: ").append(namalayanan).append("\n");
                            message.append("Kuantitas: ").append(kuantitas).append("Kg").append("\n");
                            message.append("Estimasi selesai: ").append(estimasi).append("Hari").append("\n");
                            message.append("Total Tagihan: ").append("Rp.").append(totalbayar).append("\n");
                            message.append("Tunggu informasi selanjutnya");

                            Uri uri = Uri.parse("https://wa.me/" + nohp + "?text=" + Uri.encode(message.toString()));
                            Intent chatWA = new Intent(Intent.ACTION_VIEW, uri);

                            chatWA.setPackage("com.whatsapp");

                            try {
                                startActivity(chatWA);
                            } catch (ActivityNotFoundException e) {
                                startActivity(new Intent(Intent.ACTION_VIEW, uri));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle the error
                    }

                });
            }
        });

    }
}