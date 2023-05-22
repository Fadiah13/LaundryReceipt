package com.example.laundryreceipt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DpPayment extends AppCompatActivity {
    Button utamaa,Hitung;
    ImageButton dpcetak, dpkirim;
    private RecyclerView recyclerViewdp;
    private DatabaseReference dpRef, dpkirimwaRef;
    private AdapterNotaTunai adapter;
    private int userId = 1;
    EditText Bayar;
    TextView Sisa, Nama, Notelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dp_payment);

        utamaa = findViewById(R.id.dpbuttonHalamanutama);
        dpcetak = findViewById(R.id.dpcetaknota);
        dpkirim = findViewById(R.id.dpkirimnotawa);
        Nama = findViewById(R.id.textNama);
        Notelp = findViewById(R.id.textnotelp);
        Sisa = findViewById(R.id.textsisa);

        utamaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DpPayment.this, Utama.class);
                startActivity(intent);
            }
        });

        recyclerViewdp = findViewById(R.id.rv_dp);
        recyclerViewdp.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterNotaTunai(this);
        adapter.setNotaTunaiList(new ArrayList<>()); // pass empty ArrayList using setEventsList()
        recyclerViewdp.setAdapter(adapter);

        dpRef = FirebaseDatabase.getInstance().getReference("datapemesanan");
        dpRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<HelperClassNotaTunai> notaTunaiList = new ArrayList<>();
                double Total = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String namalayanan = snapshot.child("Paketlayanan").child("namalayanan").getValue(String.class);
                    String total = snapshot.child("Totalbayar").getValue(String.class);
                    double totalValue = Double.parseDouble(total);
                    HelperClassNotaTunai notaTunai = new HelperClassNotaTunai(namalayanan, total);
                    notaTunaiList.add(notaTunai);
                    Total += totalValue;
                    String nama = snapshot.child("nama").getValue(String.class);
                    String notelp = snapshot.child("noTelp").getValue(String.class);
                    // Display the retrieved data in TextViews
                    Nama.setText(nama);
                    Notelp.setText(notelp);

                }
                adapter.setNotaTunaiList(notaTunaiList);
                Bayar.setEnabled(true); // Enable the EditText for input
                adapter.setTotalBayar(String.valueOf(Total));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
            }
        });

        Bayar = findViewById(R.id.editbayar);
        Sisa = findViewById(R.id.textsisa);
        Hitung = findViewById(R.id.hitung);
        Hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitungKembalian();

            }
        });
        dpkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpkirimwaRef = FirebaseDatabase.getInstance().getReference("datapemesanan");
                dpkirimwaRef.addValueEventListener(new ValueEventListener() {
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

    private void hitungKembalian() {
        String total = adapter.getTotalBayar();
        String bayarStr = Bayar.getText().toString();

        if (!total.isEmpty() && !bayarStr.isEmpty()) {
            double totall = Double.parseDouble(total);
            double bayar = Double.parseDouble(bayarStr);

            double kembalian = bayar - totall;
            Sisa.setText(String.valueOf((int) kembalian));
        }
    }
}