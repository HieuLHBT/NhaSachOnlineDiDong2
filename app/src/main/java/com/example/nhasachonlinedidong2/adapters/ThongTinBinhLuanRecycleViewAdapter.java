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
import com.example.nhasachonlinedidong2.item.ThongTinBinhLuan;

import java.util.ArrayList;

public class ThongTinBinhLuanRecycleViewAdapter extends RecyclerView.Adapter<ThongTinBinhLuanRecycleViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ThongTinBinhLuan> thongTinBinhLuans;
    private int chieuDaiMacDinh = 0;

    public ThongTinBinhLuanRecycleViewAdapter(Activity context, int resource, ArrayList<ThongTinBinhLuan> thongTinBinhLuans) {
        this.context = context;
        this.resource = resource;
        this.thongTinBinhLuans = thongTinBinhLuans;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        chieuDaiMacDinh = parent.getHeight();
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int pos = position;
        ThongTinBinhLuan thongTinBinhLuan = thongTinBinhLuans.get(pos);
        holder.itemTTBL_tvTenKhachHang.setText(thongTinBinhLuan.getTenKhachHang());
        holder.itemTTBL_tvNgayBinhLuan.setText(thongTinBinhLuan.getNgayBinhLuan());
        holder.itemTTBL_tvNoiDungBinhLuan.setText(thongTinBinhLuan.getBinhLuan());
        holder.itemTTBL_tvTenNhanVien.setText(thongTinBinhLuan.getTenNhanVien());
        holder.itemTTBL_tvNoiDungTraLoi.setText(thongTinBinhLuan.getTraLoi());

        if (thongTinBinhLuan.getTenNhanVien().equalsIgnoreCase("")) {
            holder.itemTTBL_llNhanVienTraLoi.setVisibility(View.GONE);
            ViewGroup.LayoutParams layoutParams = holder.itemTTBL.getLayoutParams();
            layoutParams.height = 700;
        } else {
            holder.itemTTBL_llNhanVienTraLoi.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams layoutParams = holder.itemTTBL.getLayoutParams();
            layoutParams.height = chieuDaiMacDinh;
        }

        switch (thongTinBinhLuan.getDanhGia()){
            case 0:
                holder.itemTTBL_img1Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                holder.itemTTBL_img2Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                holder.itemTTBL_img3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                holder.itemTTBL_img4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                holder.itemTTBL_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 1:
                holder.itemTTBL_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img2Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                holder.itemTTBL_img3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                holder.itemTTBL_img4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                holder.itemTTBL_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 2:
                holder.itemTTBL_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                holder.itemTTBL_img4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                holder.itemTTBL_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 3:
                holder.itemTTBL_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                holder.itemTTBL_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 4:
                holder.itemTTBL_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img4Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 5:
                holder.itemTTBL_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img4Sao.setImageResource(R.drawable.ic_baseline_star_24);
                holder.itemTTBL_img5Sao.setImageResource(R.drawable.ic_baseline_star_24);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return thongTinBinhLuans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    // ViewHolder definition
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTTBL_tvTenKhachHang;
        TextView itemTTBL_tvNgayBinhLuan;
        TextView itemTTBL_tvNoiDungBinhLuan;
        TextView itemTTBL_tvTenNhanVien;
        TextView itemTTBL_tvNoiDungTraLoi;
        ImageView itemTTBL_img1Sao;
        ImageView itemTTBL_img2Sao;
        ImageView itemTTBL_img3Sao;
        ImageView itemTTBL_img4Sao;
        ImageView itemTTBL_img5Sao;
        LinearLayout itemTTBL_llNhanVienTraLoi;
        CardView itemTTBL;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTTBL_img1Sao = itemView.findViewById(R.id.itemTTBL_img1Sao);
            itemTTBL_img2Sao = itemView.findViewById(R.id.itemTTBL_img2Sao);
            itemTTBL_img3Sao = itemView.findViewById(R.id.itemTTBL_img3Sao);
            itemTTBL_img4Sao = itemView.findViewById(R.id.itemTTBL_img4Sao);
            itemTTBL_img5Sao = itemView.findViewById(R.id.itemTTBL_img5Sao);
            itemTTBL_tvTenKhachHang = itemView.findViewById(R.id.itemTTBL_tvTenKhachHang);
            itemTTBL_tvNgayBinhLuan = itemView.findViewById(R.id.itemTTBL_tvNgayBinhLuan);
            itemTTBL_tvNoiDungBinhLuan = itemView.findViewById(R.id.itemTTBL_tvNoiDungBinhLuan);
            itemTTBL_tvTenNhanVien = itemView.findViewById(R.id.itemTTBL_tvTenNhanVien);
            itemTTBL_tvNoiDungTraLoi = itemView.findViewById(R.id.itemTTBL_tvNoiDungTraLoi);
            itemTTBL_llNhanVienTraLoi = itemView.findViewById(R.id.itemTTBL_llNhanVienTraLoi);
            itemTTBL = itemView.findViewById(R.id.itemTTBL);

        }
    }
}
