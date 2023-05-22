package com.example.laundryreceipt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterPemesananbaru extends RecyclerView.Adapter<AdapterPemesananbaru.ViewHolder> {
    private List<HelperClassPemesananbaru> pemesananbaruList;

    private DatabaseReference databaseReference;
    private Context context;
    private OnItemClickListener listener;
    private int userId = 1;

    public AdapterPemesananbaru(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public List<HelperClassPemesananbaru> getPemesananbaruList() {
        return pemesananbaruList;
    }

    public void setPemesananbaruList(List<HelperClassPemesananbaru> pemesananbaruList) {
        this.pemesananbaruList = pemesananbaruList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HelperClassPemesananbaru pemesananbaru = pemesananbaruList.get(position);

        holder.namaKonsumen.setText(pemesananbaru.getNama());
        holder.nomorKonsumen.setText(pemesananbaru.getNohp());
        holder.alamatKonsumen.setText(pemesananbaru.getAlamat());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuat kode resi
                String kodeResi = generateKodeResi();
                // Menyimpan data pemesanan ke Firebase Realtime Database
                saveDataToFirebase(pemesananbaru.getNama(), pemesananbaru.getNohp(), kodeResi);


                if (listener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listener.onItemClick(view, position);
                    }
                }

            }
        });
    }

    private String generateKodeResi() {
        // Implementasikan logika untuk pembuatan kode resi di sini
        // Contoh sederhana: Menggunakan waktu saat ini
        return String.valueOf(System.currentTimeMillis());
    }

    private void saveDataToFirebase(String nama, String noTelp, String kodeResi) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("datapemesanan");
        String userIdString1 = String.valueOf(userId);
        DataPemesanan dataPemesanan = new DataPemesanan(nama, noTelp, kodeResi);
        databaseReference.child(userIdString1).setValue(dataPemesanan);
        userId++;
    }

    @Override
    public int getItemCount() {
        return pemesananbaruList != null ? pemesananbaruList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView namaKonsumen;
        public TextView nomorKonsumen;
        public TextView alamatKonsumen;

        public ViewHolder(View itemView) {
            super(itemView);
            namaKonsumen = itemView.findViewById(R.id.textnamaKonsumen);
            nomorKonsumen = itemView.findViewById(R.id.texthpKonsumen);
            alamatKonsumen = itemView.findViewById(R.id.textalamatKonsumen);
        }
    }
}