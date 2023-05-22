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

public class AdapterNotaTunai extends RecyclerView.Adapter<AdapterNotaTunai.ViewHolder> {
    private List<HelperClassNotaTunai> notaTunaiList;
    private DatabaseReference databaseReference;
    private Context context;
    private OnItemClickListener listener;
    private int userId = 1;
    private String totalBayar;

    public String getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(String totalBayar) {
        this.totalBayar = totalBayar;
    }

    public AdapterNotaTunai(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(AdapterNotaTunai.OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public List<HelperClassNotaTunai> getNotaTunaiList() {
        return notaTunaiList;
    }

    public void setNotaTunaiList(List<HelperClassNotaTunai> notaTunaiList) {
        this.notaTunaiList = notaTunaiList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterNotaTunai.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notatunai, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotaTunai.ViewHolder holder, int position) {
        HelperClassNotaTunai notaTunai = notaTunaiList.get(position);

        holder.namalayanan.setText(notaTunai.getNamalayanan());
        holder.total.setText(notaTunai.getTotal());

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
        return notaTunaiList != null ? notaTunaiList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView namalayanan;
        public TextView total;


        public ViewHolder(View itemView) {
            super(itemView);
            namalayanan = itemView.findViewById(R.id.textpaketLayanan);
            total = itemView.findViewById(R.id.textTotal);
        }
    }
}
