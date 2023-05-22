package com.example.laundryreceipt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class AdapterDetailLayanan extends RecyclerView.Adapter<AdapterDetailLayanan.ViewHolder> {

    private List<HelperClassDetailLayanan> detaillayananList;
    private DatabaseReference databaseReference;
    private Context context;
    private OnItemClickListener listener;
    private int userId = 1;

    public AdapterDetailLayanan(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public List<HelperClassDetailLayanan> getDetaillayananList() {
        return detaillayananList;
    }

    public void setDetaillayananList(List<HelperClassDetailLayanan> detaillayananList) {
        this.detaillayananList = detaillayananList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterDetailLayanan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_pesanan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HelperClassDetailLayanan detaillayanan = detaillayananList.get(position);

        holder.namalayanan.setText(detaillayanan.getNamalayanan());
        holder.kuantitas.setText(detaillayanan.getKuantitas());
        holder.hari.setText(detaillayanan.getHari());
        holder.harga.setText(detaillayanan.getHarga());

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
        return detaillayananList != null ? detaillayananList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView namalayanan;
        public TextView kuantitas;
        public TextView hari;
        public TextView harga;


        public ViewHolder(View itemView) {
            super(itemView);
            namalayanan = itemView.findViewById(R.id.textpaketLayanan);
            kuantitas = itemView.findViewById(R.id.textKuantitas);
            hari = itemView.findViewById(R.id.textHari);
            harga = itemView.findViewById(R.id.textHarga);
        }
    }

}
