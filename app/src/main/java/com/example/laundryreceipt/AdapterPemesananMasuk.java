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

public class AdapterPemesananMasuk extends RecyclerView.Adapter<AdapterPemesananMasuk.ViewHolder> {

    private List<HelperClassPemesananMasuk> pemesananmasukList;

    private DatabaseReference databaseReference;
    private Context context;
    private OnItemClickListener listener;

    public AdapterPemesananMasuk(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(AdapterPemesananMasuk.OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public List<HelperClassPemesananMasuk> getPemesananmasukList() {
        return pemesananmasukList;
    }

    public void setPemesananmasukList(List<HelperClassPemesananMasuk> pemesananmasukList) {
        this.pemesananmasukList = pemesananmasukList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterPemesananMasuk.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_pemesanan, parent, false);
        return new AdapterPemesananMasuk.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPemesananMasuk.ViewHolder holder, int position) {
        HelperClassPemesananMasuk pemesananmasuk = pemesananmasukList.get(position);

        holder.noPesanan.setText(pemesananmasuk.getNopesanan());
        holder.nama.setText(pemesananmasuk.getNama());
        holder.nohp.setText(pemesananmasuk.getNohp());
        holder.estimasi.setText(pemesananmasuk.getEstimasi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listener.onItemClick(view, position);
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return pemesananmasukList != null ? pemesananmasukList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView noPesanan;
        public TextView nama;
        public TextView nohp;
        public TextView estimasi;

        public ViewHolder(View itemView) {
            super(itemView);
            noPesanan = itemView.findViewById(R.id.textnoPesanan);
            nama = itemView.findViewById(R.id.textnama);
            nohp = itemView.findViewById(R.id.textnoHp);
            estimasi = itemView.findViewById(R.id.textestimasi_selesai);

        }
    }

}
