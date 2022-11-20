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
import com.example.nhasachonlinedidong2.item.QuanLySanPham_SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class QuanLySanPhamRecyclerViewAdapter extends RecyclerView.Adapter<QuanLySanPhamRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<QuanLySanPham_SanPham> quanLySanPham_sanPhams;
    private QuanLySanPhamRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    public QuanLySanPhamRecyclerViewAdapter(Activity context, int resource, ArrayList<QuanLySanPham_SanPham> quanLySanPham_sanPhams) {
        this.context = context;
        this.resource = resource;
        this.quanLySanPham_sanPhams = quanLySanPham_sanPhams;
    }

    public void setFilteredList(ArrayList<QuanLySanPham_SanPham> filteredList) {
        this.quanLySanPham_sanPhams = filteredList;
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
        QuanLySanPham_SanPham quanLyDonHang_sanPham = quanLySanPham_sanPhams.get(pos);
        holder.itemQLSP_tvTenSanPham.setText(quanLyDonHang_sanPham.getTenSanPham());
        holder.itemQLSP_tvMaSanPham.setText(quanLyDonHang_sanPham.getMaSanPham());
        holder.itemQLSP_tvGiaTien.setText(formatter.format(quanLyDonHang_sanPham.getGiaSanPham()) + " VNĐ");
        holder.itemQLSP_tvKhuyenMai.setText(quanLyDonHang_sanPham.getKhuyenMai() + "%");
        holder.itemQLSP_tvSoLuongKho.setText(quanLyDonHang_sanPham.getSoLuongKho() + "");

        if (quanLyDonHang_sanPham.getMaSanPham().contains("s")) {
            holder.itemQLSP_llXuatXu.setVisibility(View.GONE);
            holder.itemQLSP_llNhaPhanPhoi.setVisibility(View.GONE);
            holder.itemQLSP_llDonVi.setVisibility(View.GONE);
            holder.itemQLSP_llTheLoai.setVisibility(View.VISIBLE);
            holder.itemQLSP_llTacGia.setVisibility(View.VISIBLE);
            holder.itemQLSP_llNhaXuatBan.setVisibility(View.VISIBLE);
            holder.itemQLSP_llNgayXuatBan.setVisibility(View.VISIBLE);
            holder.itemQLSP_tvTheLoai.setText(quanLyDonHang_sanPham.getTheLoai());
            holder.itemQLSP_tvTacGia.setText(quanLyDonHang_sanPham.getTacGia());
            holder.itemQLSP_tvNhaXuatBan.setText(quanLyDonHang_sanPham.getNhaXuatBan());
            holder.itemQLSP_tvNgayXuatBan.setText(quanLyDonHang_sanPham.getNgayXuatBan());
        } else if (quanLyDonHang_sanPham.getMaSanPham().contains("vpp")) {
            holder.itemQLSP_llXuatXu.setVisibility(View.VISIBLE);
            holder.itemQLSP_llNhaPhanPhoi.setVisibility(View.VISIBLE);
            holder.itemQLSP_llDonVi.setVisibility(View.VISIBLE);
            holder.itemQLSP_llTheLoai.setVisibility(View.GONE);
            holder.itemQLSP_llTacGia.setVisibility(View.GONE);
            holder.itemQLSP_llNhaXuatBan.setVisibility(View.GONE);
            holder.itemQLSP_llNgayXuatBan.setVisibility(View.GONE);
            holder.itemQLSP_tvXuatXu.setText(quanLyDonHang_sanPham.getXuatXu());
            holder.itemQLSP_tvNhaPhanPhoi.setText(quanLyDonHang_sanPham.getNhaPhanPhoi());
            holder.itemQLSP_tvDonVi.setText(quanLyDonHang_sanPham.getDonVi());
        }

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(quanLyDonHang_sanPham.getHinhSanPham());
        try {
            File file = null;
            if (quanLyDonHang_sanPham.getHinhSanPham().contains("png")) {
                file = File.createTempFile(quanLyDonHang_sanPham.getHinhSanPham().substring(0,quanLyDonHang_sanPham.getHinhSanPham().length()-4), "png");
            } else if (quanLyDonHang_sanPham.getHinhSanPham().contains("jpg")) {
                file = File.createTempFile(quanLyDonHang_sanPham.getHinhSanPham().substring(0,quanLyDonHang_sanPham.getHinhSanPham().length()-4), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemQLSP_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
        return quanLySanPham_sanPhams.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    // ViewHolder definition
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemQLSP_tvTenSanPham;
        TextView itemQLSP_tvMaSanPham;
        TextView itemQLSP_tvXuatXu;
        TextView itemQLSP_tvNhaPhanPhoi;
        TextView itemQLSP_tvDonVi;
        TextView itemQLSP_tvTheLoai;
        TextView itemQLSP_tvTacGia;
        TextView itemQLSP_tvNhaXuatBan;
        TextView itemQLSP_tvNgayXuatBan;
        TextView itemQLSP_tvGiaTien;
        TextView itemQLSP_tvKhuyenMai;
        TextView itemQLSP_tvSoLuongKho;
        TextView itemQLSP_tvMucLuc;
        ImageView itemQLSP_imgHinhSanPham;
        LinearLayout itemQLSP_llXuatXu;
        LinearLayout itemQLSP_llNhaPhanPhoi;
        LinearLayout itemQLSP_llDonVi;
        LinearLayout itemQLSP_llTheLoai;
        LinearLayout itemQLSP_llTacGia;
        LinearLayout itemQLSP_llNhaXuatBan;
        LinearLayout itemQLSP_llNgayXuatBan;
        View.OnClickListener onClickListener;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemQLSP_tvTenSanPham = itemView.findViewById(R.id.itemQLSP_tvTenSanPham);
            itemQLSP_tvMaSanPham = itemView.findViewById(R.id.itemQLSP_tvMaSanPham);
            itemQLSP_tvXuatXu = itemView.findViewById(R.id.itemQLSP_tvXuatXu);
            itemQLSP_tvNhaPhanPhoi = itemView.findViewById(R.id.itemQLSP_tvNhaPhanPhoi);
            itemQLSP_tvDonVi = itemView.findViewById(R.id.itemQLSP_tvDonVi);
            itemQLSP_tvTheLoai = itemView.findViewById(R.id.itemQLSP_tvTheLoai);
            itemQLSP_tvTacGia = itemView.findViewById(R.id.itemQLSP_tvTacGia);
            itemQLSP_tvNhaXuatBan = itemView.findViewById(R.id.itemQLSP_tvNhaXuatBan);
            itemQLSP_tvNgayXuatBan = itemView.findViewById(R.id.itemQLSP_tvNgayXuatBan);
            itemQLSP_tvGiaTien = itemView.findViewById(R.id.itemQLSP_tvGiaTien);
            itemQLSP_tvKhuyenMai = itemView.findViewById(R.id.itemQLSP_tvKhuyenMai);
            itemQLSP_tvSoLuongKho = itemView.findViewById(R.id.itemQLSP_tvSoLuongKho);
            itemQLSP_imgHinhSanPham = itemView.findViewById(R.id.itemQLSP_imgHinhSanPham);
            itemQLSP_tvMucLuc = itemView.findViewById(R.id.itemQLSP_tvMucLuc);
            itemQLSP_llXuatXu = itemView.findViewById(R.id.itemQLSP_llXuatXu);
            itemQLSP_llNhaPhanPhoi = itemView.findViewById(R.id.itemQLSP_llNhaPhanPhoi);
            itemQLSP_llDonVi = itemView.findViewById(R.id.itemQLSP_llDonVi);
            itemQLSP_llTheLoai = itemView.findViewById(R.id.itemQLSP_llTheLoai);
            itemQLSP_llTacGia = itemView.findViewById(R.id.itemQLSP_llTacGia);
            itemQLSP_llNhaXuatBan = itemView.findViewById(R.id.itemQLSP_llNhaXuatBan);
            itemQLSP_llNgayXuatBan = itemView.findViewById(R.id.itemQLSP_llNgayXuatBan);

            // Set event processing
            itemQLSP_tvMucLuc.setOnClickListener(this);
        }

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
