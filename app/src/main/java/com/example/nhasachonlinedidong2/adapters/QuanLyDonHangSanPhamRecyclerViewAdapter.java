package com.example.nhasachonlinedidong2.adapters;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.item.QuanLyDonHang_SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuanLyDonHangSanPhamRecyclerViewAdapter extends RecyclerView.Adapter<QuanLyDonHangSanPhamRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<QuanLyDonHang_SanPham> quanLyDonHang_sanPhams;

    public QuanLyDonHangSanPhamRecyclerViewAdapter(Activity context, int resource, ArrayList<QuanLyDonHang_SanPham> quanLyDonHang_sanPhams) {
        this.context = context;
        this.resource = resource;
        this.quanLyDonHang_sanPhams = quanLyDonHang_sanPhams;
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
        QuanLyDonHang_SanPham quanLyDonHang_sanPham = quanLyDonHang_sanPhams.get(pos);
        holder.itemQLDH_tvTenSanPham.setText(quanLyDonHang_sanPham.getTenSanPham());
        holder.itemQLDH_tvMaSanPham.setText(quanLyDonHang_sanPham.getMaSanPham());
        holder.itemQLDH_tvSoLuongTrongKho.setText(String.valueOf(quanLyDonHang_sanPham.getSoLuongKho()));
        holder.itemQLDH_tvSoLuongBan.setText(String.valueOf(quanLyDonHang_sanPham.getSoLuongBan()));
        holder.itemQLDH_tvGiaTien.setText(formatter.format(quanLyDonHang_sanPham.getGiaTien()) + " VNĐ");
        holder.itemQLDH_tvTongTien.setText(formatter.format(quanLyDonHang_sanPham.getTongTien()) + " VNĐ");

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(quanLyDonHang_sanPham.getHinhSanPham());
        try {
            File file = null;
            if (quanLyDonHang_sanPham.getHinhSanPham().contains("png")) {
                file = File.createTempFile(quanLyDonHang_sanPham.getHinhSanPham().substring(0, quanLyDonHang_sanPham.getHinhSanPham().length() - 4), "png");
            } else if (quanLyDonHang_sanPham.getHinhSanPham().contains("jpg")) {
                file = File.createTempFile(quanLyDonHang_sanPham.getHinhSanPham().substring(0, quanLyDonHang_sanPham.getHinhSanPham().length() - 4), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemQLDH_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("onCancelled", "Lỗi!" + e.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return quanLyDonHang_sanPhams.size();
    }

    public int getItemViewType(int position) {
        return resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemQLDH_tvTenSanPham;
        TextView itemQLDH_tvMaSanPham;
        TextView itemQLDH_tvSoLuongTrongKho;
        TextView itemQLDH_tvSoLuongBan;
        TextView itemQLDH_tvGiaTien;
        TextView itemQLDH_tvTongTien;
        ImageView itemQLDH_imgHinhSanPham;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemQLDH_imgHinhSanPham = itemView.findViewById(R.id.itemQLDH_imgHinhSanPham);
            itemQLDH_tvTenSanPham = itemView.findViewById(R.id.itemQLDH_tvTenSanPham);
            itemQLDH_tvMaSanPham = itemView.findViewById(R.id.itemQLDH_tvMaSanPham);
            itemQLDH_tvSoLuongTrongKho = itemView.findViewById(R.id.itemQLDH_tvSoLuongTrongKho);
            itemQLDH_tvSoLuongBan = itemView.findViewById(R.id.itemQLDH_tvSoLuongBan);
            itemQLDH_tvGiaTien = itemView.findViewById(R.id.itemQLDH_tvGiaTien);
            itemQLDH_tvTongTien = itemView.findViewById(R.id.itemQLDH_tvTongTien);
        }
    }
}
