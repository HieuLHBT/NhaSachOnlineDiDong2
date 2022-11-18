package com.example.nhasachonlinedidong2.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.item.ItemXacNhanDonHangNV;
import com.example.nhasachonlinedidong2.item.ItemXacNhanDonHangNVSanPham;

import java.util.ArrayList;


public class MHNVXacNhanDonHangItemRecyclerViewAdapter extends RecyclerView.Adapter<MHNVXacNhanDonHangItemRecyclerViewAdapter.MyViewHolder>{
    private Activity context;
    private int resource;
    private ArrayList<ItemXacNhanDonHangNVSanPham> itemXacNhanDonHangSanPham;
    private ArrayList<ItemXacNhanDonHangNV> itemXacNhanDonHangNVS;

    public MHNVXacNhanDonHangItemRecyclerViewAdapter(Activity context, int resource, ArrayList<ItemXacNhanDonHangNVSanPham> itemXacNhanDonHangSanPham, ArrayList<ItemXacNhanDonHangNV> itemXacNhanDonHangNVS) {
        this.context = context;
        this.resource = resource;
        this.itemXacNhanDonHangSanPham = itemXacNhanDonHangSanPham;
        this.itemXacNhanDonHangNVS = itemXacNhanDonHangNVS;
    }

    @NonNull
    @Override
    public MHNVXacNhanDonHangItemRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MHNVXacNhanDonHangItemRecyclerViewAdapter.MyViewHolder holder, int position) {
        final int pos = position;
        ItemXacNhanDonHangNV itemXacNhanDonHangNV = itemXacNhanDonHangNVS.get(pos);
        holder.maHoaDon.setText(itemXacNhanDonHangNV.getMaHoaDon());

        holder.layoutMHNV_XNDH_rvDanhSachSanPham.setHasFixedSize(true);
        holder.layoutMHNV_XNDH_rvDanhSachSanPham.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(), 4));
      //  MHNVXacNhanDonHangItemSanPhamRecyclerViewAdapter childAdapter = new MHNVXacNhanDonHangItemSanPhamRecyclerViewAdapter();
        //childAdapter.setItemXacNhanDonHangSanPham(itemXacNhanDonHangNV.getItemXacNhanDonHangSanPham());
      //  holder.layoutMHNV_XNDH_rvDanhSachSanPham.setAdapter(childAdapter);
    }

    @Override
    public int getItemCount() {
        return itemXacNhanDonHangNVS.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView maHoaDon;
        RecyclerView layoutMHNV_XNDH_rvDanhSachSanPham;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            maHoaDon = itemView.findViewById(R.id.itemXNDH_tvMaHoaDon);
            layoutMHNV_XNDH_rvDanhSachSanPham = itemView.findViewById(R.id.layoutMHNV_XNDH_rvDanhSachSanPham);
        }
    }


}
