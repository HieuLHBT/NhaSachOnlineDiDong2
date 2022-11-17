 package com.example.nhasachonlinedidong2.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.item.DanhGia;

import java.util.ArrayList;

public class DanhGiaSanPhamRecyclerViewAdapter extends RecyclerView.Adapter<DanhGiaSanPhamRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<DanhGia> danhGias;
    private OnItemClickListener onItemClickListener;

    public DanhGiaSanPhamRecyclerViewAdapter(Activity context, int resource, ArrayList<DanhGia> danhGias){
        this.context = context;
        this.resource = resource;
        this.danhGias = danhGias;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhGiaSanPhamRecyclerViewAdapter.MyViewHolder holder, int position) {
        final int pos = position;
        DanhGia danhGia = danhGias.get(pos);
        holder.itemDGSP_txtTenSanPham.setText(danhGia.getTenSanPham());
        //holder.itemDGSP_imgHinhSanPham.setText(danhGia.getTenSanPham());

    }

    @Override
    public int getItemCount() {
        return danhGias.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemDGSP_txtTenSanPham;
        ImageView itemDGSP_imgHinhSanPham;

        LinearLayout itemDGSP_llCardView;
        CardView itemDGSP;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemDGSP_txtTenSanPham = itemView.findViewById(R.id.itemDGSP_txtTenSanPham);
            itemDGSP_imgHinhSanPham = itemView.findViewById(R.id.itemDGSP_imgHinhSanPham);

            itemDGSP_llCardView = itemView.findViewById(R.id.layoutDGSP_rvDanhGiaSanPham);
            itemDGSP = itemView.findViewById(R.id.itemDGSP);
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
