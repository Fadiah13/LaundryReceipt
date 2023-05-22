package com.example.laundryreceipt;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Saat_ambil extends AppCompatActivity {

    ImageButton cetaknota, kirimnota;
    Button beranda;
    private DatabaseReference saatAmbilRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saat_ambil);

        cetaknota = findViewById(R.id.cetaknotaSaatambil);
        kirimnota = findViewById(R.id.kirimnotawa);
        beranda = findViewById(R.id.buttonHalamanutama);

        beranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Saat_ambil.this, Utama.class);
                startActivity(intent);
            }
        });

        kirimnota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saatAmbilRef = FirebaseDatabase.getInstance().getReference("datapemesanan");
                saatAmbilRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dtSnapshot) {
                        for (DataSnapshot snapshot : dtSnapshot.getChildren()) {
                            String koderesi = snapshot.child("kodeResi").getValue(String.class);
                            String nama = snapshot.child("nama").getValue(String.class);
                            String namalayanan = snapshot.child("Paketlayanan").child("namalayanan").getValue(String.class);
                            String kuantitas = snapshot.child("Paketlayanan").child("detailLayanan").child("kuantitas").getValue(String.class);
                            String estimasi = snapshot.child("Paketlayanan").child("detailLayanan").child("hari").getValue(String.class);
                            String totalbayar = snapshot.child("Totalbayar").getValue(String.class);

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