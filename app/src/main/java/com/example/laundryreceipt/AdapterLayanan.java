package com.example.laundryreceipt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterLayanan extends RecyclerView.Adapter<AdapterLayanan.ViewHolder> {

    private ArrayList<ModelLayanan> modelLayanan;
    private OnItemClickListener listener;
    private DatabaseReference databaseRef;
    private int userId = 1;

    public interface OnItemClickListener {
        void onItemClick(ModelLayanan item);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public AdapterLayanan(ArrayList<ModelLayanan> modelLayanan) {
        this.modelLayanan = modelLayanan;
        this.databaseRef = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public AdapterLayanan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        ViewHolder vh = new ViewHolder(view);
        return  vh;
    }

    @Override
    public void  onBindViewHolder(@NonNull AdapterLayanan.ViewHolder holder, int position){
    holder.namalayanan.setText(modelLayanan.get(holder.getAdapterPosition()).getNamalayanan());
    holder.logolayanan.setImageResource(modelLayanan.get(holder.getAdapterPosition()).getLogolayanan());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            simpandata(modelLayanan.get(holder.getAdapterPosition()).getNamalayanan());
            if (listener != null){
                listener.onItemClick(modelLayanan.get(holder.getAdapterPosition()));
            }
        }
    });
    }
    private void simpandata(String namalayanan) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("datapemesanan");
        String userIdString1 = String.valueOf(userId);
        DataLayanan datalayanan = new DataLayanan(namalayanan);
        databaseReference.child(userIdString1).child("Paketlayanan").setValue(datalayanan);
        userId++;
    }

    @Override
    public  int getItemCount(){

        return modelLayanan.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {

        TextView namalayanan;
        ImageView logolayanan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namalayanan = itemView.findViewById(R.id.nama_layanan);
            logolayanan = itemView.findViewById(R.id.logo_layanan);
        }
    }
}
