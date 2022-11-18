package com.example.nhasachonlinedidong2.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.item.LichSuMuaHang_DonHang;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LichSuMuaHangDonHangRecyclerViewAdapter extends RecyclerView.Adapter<LichSuMuaHangDonHangRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<LichSuMuaHang_DonHang> lichSuMuaHang_donHangs;
    private LichSuMuaHangSanPhamRecyclerViewAdapter adapter;
    private OnItemClickListener onItemClickListener;

    public LichSuMuaHangDonHangRecyclerViewAdapter(Activity context, int resource, ArrayList<LichSuMuaHang_DonHang> lichSuMuaHang_donHangs){
        this.context = context;
        this.resource = resource;
        this.lichSuMuaHang_donHangs = lichSuMuaHang_donHangs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuMuaHangDonHangRecyclerViewAdapter.MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        LichSuMuaHang_DonHang lichSuMuaHang = lichSuMuaHang_donHangs.get(pos);
        holder.itemLSMH_tvNgayGiaoHang.setText(lichSuMuaHang.getNgayGiaoHang());
        holder.itemLSMH_tvTrangThai.setText(lichSuMuaHang.getTrangThai());
        holder.itemLSMH_tvTongTien.setText(formatter.format(lichSuMuaHang.getTongTien()) + " VNĐ");

        adapter = new LichSuMuaHangSanPhamRecyclerViewAdapter(this.context, R.layout.lichsumuahang_sanpham_item, lichSuMuaHang.getLichSuMuaHang_sanPhams());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        holder.lichSuMuaHangSanPhamRecyclerViewAdapter.setLayoutManager(layoutManager);
        holder.lichSuMuaHangSanPhamRecyclerViewAdapter.setAdapter(adapter);

        // Event processing
        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickListener(pos, holder.itemView);
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return lichSuMuaHang_donHangs.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemLSMH_tvNgayGiaoHang;
        TextView itemLSMH_tvTrangThai;
        TextView itemLSMH_tvTongTien;
        Button itemLSMH_btnDanhGia;
        Button itemLSMH_btnMuaLai;
        RecyclerView lichSuMuaHangSanPhamRecyclerViewAdapter;
        View.OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemLSMH_tvNgayGiaoHang = itemView.findViewById(R.id.itemLSMH_tvNgayGiaoHang);
            itemLSMH_tvTrangThai = itemView.findViewById(R.id.itemLSMH_tvTrangThai);
            itemLSMH_tvTongTien = itemView.findViewById(R.id.itemLSMH_tvTongTien);
            itemLSMH_btnDanhGia = itemView.findViewById(R.id.itemLSMH_btnDanhGia);
            itemLSMH_btnMuaLai = itemView.findViewById(R.id.itemLSMH_btnMuaLai);
            lichSuMuaHangSanPhamRecyclerViewAdapter = itemView.findViewById(R.id.layoutLSMH_SanPham_rvLichSuMuaHang);

            // Set event processing
            itemLSMH_btnDanhGia.setOnClickListener(this);
            itemLSMH_btnMuaLai.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }
    // Interface for event processing
    public interface OnItemClickListener {
        void onItemClickListener(int position, View view);
    }

    public void setOnItemClickListener(LichSuMuaHangDonHangRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = (LichSuMuaHangDonHangRecyclerViewAdapter.OnItemClickListener) onItemClickListener;
    }
}
