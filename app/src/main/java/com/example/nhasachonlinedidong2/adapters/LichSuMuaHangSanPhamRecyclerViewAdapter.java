package com.example.nhasachonlinedidong2.adapters;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.item.LichSuMuaHang_SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class LichSuMuaHangSanPhamRecyclerViewAdapter extends RecyclerView.Adapter<LichSuMuaHangSanPhamRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<LichSuMuaHang_SanPham> lichSuMuaHang_sanPhams;

    public LichSuMuaHangSanPhamRecyclerViewAdapter(Activity context, int resource, ArrayList<LichSuMuaHang_SanPham> lichSuMuaHang_sanPhams) {
        this.context = context;
        this.resource = resource;
        this.lichSuMuaHang_sanPhams = lichSuMuaHang_sanPhams;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuMuaHangSanPhamRecyclerViewAdapter.MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        LichSuMuaHang_SanPham lichSuMuaHang_sanPham = lichSuMuaHang_sanPhams.get(pos);
        holder.itemLSMHSP_tvTenSanPham.setText(lichSuMuaHang_sanPham.getTemSanPham());
        holder.itemLSMHSP_tvGiaSanPham.setText(formatter.format(lichSuMuaHang_sanPham.getGiaTien()) + " VNĐ");
        holder.itemLSMHSP_tvSoLuong.setText("(sl: " + lichSuMuaHang_sanPham.getSoLuongMua() + ")");
        holder.itemLSMHSP_tvTongTien.setText(formatter.format(lichSuMuaHang_sanPham.getTongTien()) + " VNĐ");

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(lichSuMuaHang_sanPham.getHinhSanPham());
        try {
            File file = null;
            if (lichSuMuaHang_sanPham.getHinhSanPham().contains("png")) {
                file = File.createTempFile(lichSuMuaHang_sanPham.getHinhSanPham().substring(0, lichSuMuaHang_sanPham.getHinhSanPham().length() - 4), "png");
            } else if (lichSuMuaHang_sanPham.getHinhSanPham().contains("jpg")) {
                file = File.createTempFile(lichSuMuaHang_sanPham.getHinhSanPham().substring(0, lichSuMuaHang_sanPham.getHinhSanPham().length() - 4), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemLSMHSP_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
        return lichSuMuaHang_sanPhams.size();
    }

    public int getItemViewType(int position) {
        return resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemLSMHSP_tvTenSanPham;
        TextView itemLSMHSP_tvGiaSanPham;
        TextView itemLSMHSP_tvSoLuong;
        TextView itemLSMHSP_tvTongTien;
        ImageView itemLSMHSP_imgHinhSanPham;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemLSMHSP_imgHinhSanPham = itemView.findViewById(R.id.itemLSMHSP_imgHinhSanPham);
            itemLSMHSP_tvTenSanPham = itemView.findViewById(R.id.itemLSMHSP_tvTenSanPham);
            itemLSMHSP_tvGiaSanPham = itemView.findViewById(R.id.itemLSMHSP_tvGiaSanPham);
            itemLSMHSP_tvSoLuong = itemView.findViewById(R.id.itemLSMHSP_tvSoLuong);
            itemLSMHSP_tvTongTien = itemView.findViewById(R.id.itemLSMHSP_tvTongTien);
        }
    }
}
