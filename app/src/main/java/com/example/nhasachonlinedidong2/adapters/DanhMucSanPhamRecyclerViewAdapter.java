package com.example.nhasachonlinedidong2.adapters;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.item.DanhMucSanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class DanhMucSanPhamRecyclerViewAdapter extends RecyclerView.Adapter<DanhMucSanPhamRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<DanhMucSanPham> danhMucSanPhams;

    public DanhMucSanPhamRecyclerViewAdapter(Activity context, int resource, ArrayList<DanhMucSanPham> danhMucSanPhams){
        this.context = context;
        this.resource = resource;
        this.danhMucSanPhams = danhMucSanPhams;
    }

    public void setFilteredList(ArrayList<DanhMucSanPham> filteredList){
        this.danhMucSanPhams = filteredList;
        notifyDataSetChanged();
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
        DanhMucSanPham danhMucSanPham = danhMucSanPhams.get(pos);
        holder.itemDMSP_tvTenSanPham.setText(danhMucSanPham.getTenSanPham());
        holder.itemDMSP_tvMaSanPham.setText(danhMucSanPham.getMaSanPham());
        holder.itemDMSP_tvGiaTien.setText(formatter.format(danhMucSanPham.getGiaTien()) + " VNĐ");
        holder.itemDMSP_tvKhuyenMai.setText(danhMucSanPham.getKhuyenMai() + "%");
        holder.itemDMSP_tvSoLuongTrongKho.setText(danhMucSanPham.getSoLuongKho() + " ");

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(danhMucSanPham.getHinhSanPham());
        try {
            File file = null;
            if (danhMucSanPham.getHinhSanPham().contains("png")) {
                file = File.createTempFile(danhMucSanPham.getHinhSanPham().substring(0, danhMucSanPham.getHinhSanPham().length()), "png");
            } else if (danhMucSanPham.getHinhSanPham().contains("jpg")) {
                file = File.createTempFile(danhMucSanPham.getHinhSanPham().substring(0, danhMucSanPham.getHinhSanPham().length()), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemDMSP_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
        return danhMucSanPhams.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemDMSP_tvTenSanPham;
        TextView itemDMSP_tvMaSanPham;
        ImageView itemDMSP_imgHinhSanPham;
        TextView itemDMSP_tvGiaTien;
        TextView itemDMSP_tvKhuyenMai;
        TextView itemDMSP_tvSoLuongTrongKho;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemDMSP_tvTenSanPham = itemView.findViewById(R.id.itemDMSP_tvTenSanPham);
            itemDMSP_tvMaSanPham = itemView.findViewById(R.id.itemDMSP_tvMaSanPham);
            itemDMSP_imgHinhSanPham = itemView.findViewById(R.id.itemDMSP_imgHinhSanPham);
            itemDMSP_tvGiaTien = itemView.findViewById(R.id.itemDMSP_tvGiaTien);
            itemDMSP_tvKhuyenMai = itemView.findViewById(R.id.itemDMSP_tvKhuyenMai);
            itemDMSP_tvSoLuongTrongKho = itemView.findViewById(R.id.itemDMSP_tvSoLuongTrongKho);
        }
    }
}
