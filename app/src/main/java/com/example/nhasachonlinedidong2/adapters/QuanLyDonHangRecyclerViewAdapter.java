package com.example.nhasachonlinedidong2.adapters;

import android.app.Activity;
import android.graphics.Color;
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
import com.example.nhasachonlinedidong2.item.QuanLyDonHang_DonHang;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuanLyDonHangRecyclerViewAdapter extends RecyclerView.Adapter<QuanLyDonHangRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<QuanLyDonHang_DonHang> quanLyDonHang_donHangs;
    private QuanLyDonHangSanPhamRecyclerViewAdapter adapter;
    private OnItemClickListener onItemClickListener;

    public QuanLyDonHangRecyclerViewAdapter(Activity context, int resource, ArrayList<QuanLyDonHang_DonHang> quanLyDonHang_donHangs) {
        this.context = context;
        this.resource = resource;
        this.quanLyDonHang_donHangs = quanLyDonHang_donHangs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        QuanLyDonHang_DonHang quanLyDonHang_donHang = quanLyDonHang_donHangs.get(pos);
        holder.itemQLDH_tvTrangThaiDon.setText(quanLyDonHang_donHang.getTrangThai());
        holder.itemQLDH_tvMaHoaDon.setText(quanLyDonHang_donHang.getMaDonHang());
        holder.itemQLDH_tvTenNhanVien.setText(quanLyDonHang_donHang.getTenNhanVien());
        holder.itemQLDH_tvNgayLap.setText(quanLyDonHang_donHang.getNgayLapHoaDon());
        holder.itemQLDH_tvNgayGiao.setText(quanLyDonHang_donHang.getNgayDuKienGiao());
        holder.itemQLDH_tvTenKhachHang.setText(quanLyDonHang_donHang.getTenKhachHang());
        holder.itemQLDH_tvEmail.setText(quanLyDonHang_donHang.getEmail());
        holder.itemQLDH_tvSoDienThoai.setText(quanLyDonHang_donHang.getSoDienThoai());
        holder.itemQLDH_tvDiaChiGiaoHang.setText(quanLyDonHang_donHang.getDiaChi());
        holder.itemQLDH_tvTienGiamGia.setText(formatter.format(quanLyDonHang_donHang.getGiamGia()) + " VNĐ");
        holder.itemQLDH_tvPhiVanChuyen.setText(formatter.format(quanLyDonHang_donHang.getPhiVanChuyen()) + " VNĐ");
        holder.itemQLDH_tvTongTienSanPham.setText(formatter.format(quanLyDonHang_donHang.getTongTien()) + " VNĐ");
        holder.itemQLDH_tvLyDoHuy.setText(quanLyDonHang_donHang.getLyDoHuy());

        if (quanLyDonHang_donHang.getTrangThai().equalsIgnoreCase("Hủy")) {
            holder.itemQLDH_llDiaChiGiaoHang.setVisibility(View.GONE);
            holder.itemQLDH_llLyDoHuy.setVisibility(View.VISIBLE);
            holder.itemQLDH_tvTrangThaiDon.setTextColor(Color.RED);
        } else if (quanLyDonHang_donHang.getTrangThai().equalsIgnoreCase("Thành công")) {
            holder.itemQLDH_llDiaChiGiaoHang.setVisibility(View.VISIBLE);
            holder.itemQLDH_llLyDoHuy.setVisibility(View.GONE);
            holder.itemQLDH_tvTrangThaiDon.setTextColor(Color.BLUE);
        } else if (quanLyDonHang_donHang.getTrangThai().equalsIgnoreCase("Đang xử lý")) {
            holder.itemQLDH_llDiaChiGiaoHang.setVisibility(View.VISIBLE);
            holder.itemQLDH_llLyDoHuy.setVisibility(View.GONE);
            holder.itemQLDH_tvTrangThaiDon.setTextColor(Color.GREEN);
        }

        if (!quanLyDonHang_donHang.getTrangThai().equalsIgnoreCase("Hủy") && quanLyDonHang_donHang.getTrangThaiDuyetNV().equalsIgnoreCase("Đang xử lý")) {
            holder.itemQLDH_btnDuyetDon.setVisibility(View.VISIBLE);
            holder.itemQLDH_btnGiaoHang.setVisibility(View.GONE);
            holder.itemQLDH_btnHuyDon.setVisibility(View.VISIBLE);
        } else if (!quanLyDonHang_donHang.getTrangThai().equalsIgnoreCase("Hủy") && !quanLyDonHang_donHang.getTrangThai().equalsIgnoreCase("Thành công") && quanLyDonHang_donHang.getTrangThaiGiaoHangNV().equalsIgnoreCase("Đang xử lý")) {
            holder.itemQLDH_btnDuyetDon.setVisibility(View.GONE);
            holder.itemQLDH_btnGiaoHang.setVisibility(View.VISIBLE);
            holder.itemQLDH_btnHuyDon.setVisibility(View.VISIBLE);
        } else if (!quanLyDonHang_donHang.getTrangThai().equalsIgnoreCase("Hủy") && !quanLyDonHang_donHang.getTrangThai().equalsIgnoreCase("Thành công") && quanLyDonHang_donHang.getTrangThaiGiaoHangNV().equalsIgnoreCase("Đã xác nhận")) {
            holder.itemQLDH_btnDuyetDon.setVisibility(View.GONE);
            holder.itemQLDH_btnGiaoHang.setVisibility(View.GONE);
            holder.itemQLDH_btnHuyDon.setVisibility(View.GONE);
        } else if (quanLyDonHang_donHang.getTrangThai().equalsIgnoreCase("Hủy") || quanLyDonHang_donHang.getTrangThai().equalsIgnoreCase("Thành công")) {
            holder.itemQLDH_btnDuyetDon.setVisibility(View.GONE);
            holder.itemQLDH_btnGiaoHang.setVisibility(View.GONE);
            holder.itemQLDH_btnHuyDon.setVisibility(View.GONE);
        }

        adapter = new QuanLyDonHangSanPhamRecyclerViewAdapter(this.context, R.layout.quanlydonhang_sanpham_item, quanLyDonHang_donHang.getQuanLyDonHang_sanPhams());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        holder.itemQLDH_rvSanPham.setLayoutManager(layoutManager);
        holder.itemQLDH_rvSanPham.setAdapter(adapter);

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
        return quanLyDonHang_donHangs.size();
    }

    public int getItemViewType(int position) {
        return resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemQLDH_tvTrangThaiDon;
        TextView itemQLDH_tvMaHoaDon;
        TextView itemQLDH_tvLyDoHuy;
        TextView itemQLDH_tvTenNhanVien;
        TextView itemQLDH_tvNgayLap;
        TextView itemQLDH_tvNgayGiao;
        TextView itemQLDH_tvTenKhachHang;
        TextView itemQLDH_tvEmail;
        TextView itemQLDH_tvSoDienThoai;
        TextView itemQLDH_tvDiaChiGiaoHang;
        TextView itemQLDH_tvTienGiamGia;
        TextView itemQLDH_tvPhiVanChuyen;
        TextView itemQLDH_tvTongTienSanPham;
        Button itemQLDH_btnDuyetDon;
        Button itemQLDH_btnGiaoHang;
        Button itemQLDH_btnHuyDon;
        RecyclerView itemQLDH_rvSanPham;
        LinearLayout itemQLDH_llDiaChiGiaoHang;
        LinearLayout itemQLDH_llLyDoHuy;
        View.OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemQLDH_tvTrangThaiDon = itemView.findViewById(R.id.itemQLDH_tvTrangThaiDon);
            itemQLDH_tvMaHoaDon = itemView.findViewById(R.id.itemQLDH_tvMaHoaDon);
            itemQLDH_tvLyDoHuy = itemView.findViewById(R.id.itemQLDH_tvLyDoHuy);
            itemQLDH_tvTenNhanVien = itemView.findViewById(R.id.itemQLDH_tvTenNhanVien);
            itemQLDH_tvNgayLap = itemView.findViewById(R.id.itemQLDH_tvNgayLap);
            itemQLDH_tvNgayGiao = itemView.findViewById(R.id.itemQLDH_tvNgayGiao);
            itemQLDH_tvTenKhachHang = itemView.findViewById(R.id.itemQLDH_tvTenKhachHang);
            itemQLDH_tvEmail = itemView.findViewById(R.id.itemQLDH_tvEmail);
            itemQLDH_tvSoDienThoai = itemView.findViewById(R.id.itemQLDH_tvSoDienThoai);
            itemQLDH_tvDiaChiGiaoHang = itemView.findViewById(R.id.itemQLDH_tvDiaChiGiaoHang);
            itemQLDH_tvTienGiamGia = itemView.findViewById(R.id.itemQLDH_tvTienGiamGia);
            itemQLDH_tvPhiVanChuyen = itemView.findViewById(R.id.itemQLDH_tvPhiVanChuyen);
            itemQLDH_tvTongTienSanPham = itemView.findViewById(R.id.itemQLDH_tvTongTienSanPham);
            itemQLDH_btnDuyetDon = itemView.findViewById(R.id.itemQLDH_btnDuyetDon);
            itemQLDH_btnGiaoHang = itemView.findViewById(R.id.itemQLDH_btnGiaoHang);
            itemQLDH_btnHuyDon = itemView.findViewById(R.id.itemQLDH_btnHuyDon);
            itemQLDH_rvSanPham = itemView.findViewById(R.id.itemQLDH_rvSanPham);
            itemQLDH_llDiaChiGiaoHang = itemView.findViewById(R.id.itemQLDH_llDiaChiGiaoHang);
            itemQLDH_llLyDoHuy = itemView.findViewById(R.id.itemQLDH_llLyDoHuy);

            // Set event processing
            itemQLDH_btnDuyetDon.setOnClickListener(this);
            itemQLDH_btnGiaoHang.setOnClickListener(this);
            itemQLDH_btnHuyDon.setOnClickListener(this);
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

    public void setOnItemClickListener(QuanLyDonHangRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = (QuanLyDonHangRecyclerViewAdapter.OnItemClickListener) onItemClickListener;
    }
}
