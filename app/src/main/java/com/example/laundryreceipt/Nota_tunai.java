package com.example.laundryreceipt;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
public class Nota_tunai extends AppCompatActivity {

    Button halamanutama,Hitung;
    ImageButton cetaknotatunai, kirimnotatunai;
    private RecyclerView recyclerView;
    private DatabaseReference notatunaiRef, notawaRef;
    private AdapterNotaTunai adapter;
    private int userId = 1;
    EditText Bayar;
    TextView Kembalian, Nama, Notelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_tunai);

        halamanutama = findViewById(R.id.tunaibuttonHalamanutama);
        cetaknotatunai = findViewById(R.id.tunaicetaknota);
        kirimnotatunai = findViewById(R.id.tunaikirimnotawa);
        Nama = findViewById(R.id.textnama);
        Notelp = findViewById(R.id.textnotlp);

        halamanutama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Nota_tunai.this, Utama.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.rv_notatunai);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterNotaTunai(this);
        adapter.setNotaTunaiList(new ArrayList<>()); // pass empty ArrayList using setEventsList()
        recyclerView.setAdapter(adapter);

        notatunaiRef = FirebaseDatabase.getInstance().getReference("datapemesanan");
        notatunaiRef.addValueEventListener(new ValueEventListener() {
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
        Kembalian = findViewById(R.id.textkembali);
        Hitung = findViewById(R.id.hitung);
        Hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitungKembalian();

            }
        });

        kirimnotatunai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notawaRef = FirebaseDatabase.getInstance().getReference("datapemesanan");
                notawaRef.addValueEventListener(new ValueEventListener() {
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
            Kembalian.setText(String.valueOf((int) kembalian));
        }
    }
}