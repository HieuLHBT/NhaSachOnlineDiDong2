package com.example.nhasachonlinedidong2.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.item.TheoDoiDonHang;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TheoDoiDonHangRecyclerViewAdapter extends RecyclerView.Adapter<TheoDoiDonHangRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<TheoDoiDonHang> theoDoiDonHangs;
    private OnItemClickListener onItemClickListener;

    public TheoDoiDonHangRecyclerViewAdapter(Activity context, int resource, ArrayList<TheoDoiDonHang> theoDoiDonHangs) {
        this.context = context;
        this.resource = resource;
        this.theoDoiDonHangs = theoDoiDonHangs;
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
        TheoDoiDonHang theoDoiDonHang = theoDoiDonHangs.get(pos);
        holder.itemTDDH_txtMaDonHang.setText(theoDoiDonHang.getMaDonHang());
        holder.itemTDDH_tvNhanVienGiaoHang.setText(theoDoiDonHang.getTenNhanVien());
        holder.itemTDDH_tvThoiGianGiao.setText(theoDoiDonHang.getNgayGiao());
        holder.itemTDDH_tvThoiGianDatHang.setText(theoDoiDonHang.getNgayLap());
        holder.itemTDDH_tvTongTienThanhToan.setText(formatter.format(theoDoiDonHang.getTongTien()) + " VNĐ");
        holder.itemTDDH_tvHinhThucThanhToan.setText(theoDoiDonHang.getHinhThucThanhToan());

        if (theoDoiDonHang.getTrangThaiGiaoHangNV().equalsIgnoreCase("Đã xác nhận")) {
            holder.itemTDDH_tvTrangThaiDon.setVisibility(View.GONE);
        } else {
            holder.itemTDDH_tvTrangThaiDon.setVisibility(View.VISIBLE);
        }
        if (theoDoiDonHang.getTrangThaiDuyetNV().equalsIgnoreCase("Đang xử lý")) {
            holder.itemTDDH_tvTrangThaiDon.setText("Đang kiểm duyệt");
            Glide.with(context).load(R.drawable.duyetdon).into(holder.itemTDDH_imgTrangThaiDon);
        }
        else if (theoDoiDonHang.getTrangThaiDuyetNV().equalsIgnoreCase("Đã xác nhận") && theoDoiDonHang.getTrangThaiGiaoHangNV().equalsIgnoreCase("Đang xử lý")) {
            holder.itemTDDH_tvTrangThaiDon.setText("Đang giao hàng");
            Glide.with(context).load(R.drawable.giaohang).into(holder.itemTDDH_imgTrangThaiDon);
        }
        else {
            holder.itemTDDH_tvTrangThaiDon.setText(theoDoiDonHang.getTrangThaiDon());
            Glide.with(context).load(R.drawable.huy).into(holder.itemTDDH_imgTrangThaiDon);
        }

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
        return theoDoiDonHangs.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemTDDH_txtMaDonHang;
        TextView itemTDDH_tvNhanVienGiaoHang;
        TextView itemTDDH_tvThoiGianGiao;
        TextView itemTDDH_tvThoiGianDatHang;
        TextView itemTDDH_tvTongTienThanhToan;
        TextView itemTDDH_tvTrangThaiDon;
        TextView itemTDDH_tvHinhThucThanhToan;
        Button itemTDDH_btnChiTiet;
        View.OnClickListener onClickListener;
        LinearLayout itemTDDH_llDuKienGiao;
        LinearLayout itemTDDH_llHinhThuc;
        ImageView itemTDDH_imgTrangThaiDon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTDDH_txtMaDonHang = itemView.findViewById(R.id.itemTDDH_tvMaDonHang);
            itemTDDH_tvNhanVienGiaoHang = itemView.findViewById(R.id.itemTDDH_tvNhanVienGiaoHang);
            itemTDDH_tvThoiGianGiao = itemView.findViewById(R.id.itemTDDH_tvThoiGianGiao);
            itemTDDH_tvThoiGianDatHang = itemView.findViewById(R.id.itemTDDH_tvThoiGianDatHang);
            itemTDDH_tvTongTienThanhToan = itemView.findViewById(R.id.itemTDDH_tvTongTienThanhToan);
            itemTDDH_tvTrangThaiDon = itemView.findViewById(R.id.itemTDDH_tvTrangThaiDon);
            itemTDDH_tvHinhThucThanhToan = itemView.findViewById(R.id.itemTDDH_tvHinhThucThanhToan);
            itemTDDH_btnChiTiet = itemView.findViewById(R.id.itemTDDH_btnChiTiet);
            itemTDDH_llDuKienGiao = itemView.findViewById(R.id.itemTDDH_llDuKienGiao);
            itemTDDH_llHinhThuc = itemView.findViewById(R.id.itemTDDH_llHinhThuc);
            itemTDDH_imgTrangThaiDon = itemView.findViewById(R.id.itemTDDH_imgTrangThaiDon);

            // Set event processing
            itemTDDH_btnChiTiet.setOnClickListener(this);
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
