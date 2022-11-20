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
import com.example.nhasachonlinedidong2.item.ItemNhanVien;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuanLyNhanVienRecyclerViewAdapter extends RecyclerView.Adapter<QuanLyNhanVienRecyclerViewAdapter.MyViewHolder> {

    private Activity context;
    private int resource;
    private ArrayList<ItemNhanVien> nhanViens;
    private OnItemClickListener onItemClickListener;

    public QuanLyNhanVienRecyclerViewAdapter(Activity context, int resource, ArrayList<ItemNhanVien> nhanViens) {
        this.context = context;
        this.resource = resource;
        this.nhanViens = nhanViens;
    }

    public void setFilteredList1(ArrayList<ItemNhanVien> filteredList1){
        this.nhanViens = filteredList1;
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
        ItemNhanVien nhanVien = nhanViens.get(pos);
        holder.itemMHQLNV_tvMaNhanVien.setText(nhanVien.getMaNhanVien());
        holder.itemMHQLNV_tvChucVu.setText(nhanVien.getNguoiDung());
        holder.itemMHQLNV_tvTenNhanVien.setText(nhanVien.getTenNhanVien());
        holder.itemMHQLNV_tvSDTNhanVien.setText(nhanVien.getSoDienThoai());
        holder.itemMHQLNV_tvDiaChiNhanVien.setText(nhanVien.getDiaChi());
        holder.itemMHQLNV_tvCMNDNhanVien.setText(nhanVien.getCmnd());
        holder.itemMHQLNV_tvLuongNhanVien.setText(nhanVien.getLuong());
        holder.itemMHQLNV_tvEmailNhanVien.setText(nhanVien.getEmail());
        holder.itemMHQLNV_tvTaiKhoanNhanVien.setText(nhanVien.getTaiKhoan());
        holder.itemMHQLNV_tvMatKhauNhanVien.setText(nhanVien.getMatKhau());

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(nhanVien.getHinhNhanVien());
        try {
            File file = null;
            if (nhanVien.getHinhNhanVien().contains("png")) {
                file = File.createTempFile(nhanVien.getHinhNhanVien().substring(0, nhanVien.getHinhNhanVien().length() - 4), "png");
            } else if (nhanVien.getHinhNhanVien().contains("jpg")) {
                file = File.createTempFile(nhanVien.getHinhNhanVien().substring(0, nhanVien.getHinhNhanVien().length() - 4), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemMHQLNV_anhNhanVien.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
        return nhanViens.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemMHQLNV_tvTroVe;
        TextView itemMHQLNV_tvChucVu;
        TextView itemMHQLNV_tvMaNhanVien;
        TextView itemMHQLNV_tvTenNhanVien;
        TextView itemMHQLNV_tvSDTNhanVien;
        TextView itemMHQLNV_tvDiaChiNhanVien;
        TextView itemMHQLNV_tvCMNDNhanVien;
        TextView itemMHQLNV_tvLuongNhanVien;
        TextView itemMHQLNV_tvEmailNhanVien;
        TextView itemMHQLNV_tvTaiKhoanNhanVien;
        TextView itemMHQLNV_tvMatKhauNhanVien;
        ImageView itemMHQLNV_anhNhanVien;
        TextView itemMHQLNV_tvThemNhanVien;
        View.OnClickListener onClickListener;
        CardView itemMHQLNV;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemMHQLNV_tvChucVu = itemView.findViewById(R.id.itemMHQLNV_tvChucVu);
            itemMHQLNV_tvMaNhanVien = itemView.findViewById(R.id.itemMHQLNV_tvMaNhanVien);
            itemMHQLNV_tvTenNhanVien = itemView.findViewById(R.id.itemMHQLNV_tvTenNhanVien);
            itemMHQLNV_tvSDTNhanVien = itemView.findViewById(R.id.itemMHQLNV_tvSDTNhanVien);
            itemMHQLNV_tvDiaChiNhanVien = itemView.findViewById(R.id.itemMHQLNV_tvDiaChiNhanVien);
            itemMHQLNV_tvCMNDNhanVien = itemView.findViewById(R.id.itemMHQLNV_tvCMNDNhanVien);
            itemMHQLNV_tvLuongNhanVien = itemView.findViewById(R.id.itemMHQLNV_tvLuongNhanVien);
            itemMHQLNV_tvEmailNhanVien = itemView.findViewById(R.id.itemMHQLNV_tvEmailNhanVien);
            itemMHQLNV_tvTaiKhoanNhanVien = itemView.findViewById(R.id.itemMHQLNV_tvTaiKhoanNhanVien);
            itemMHQLNV_tvMatKhauNhanVien = itemView.findViewById(R.id.itemMHQLNV_tvMatKhauNhanVien);
            itemMHQLNV_anhNhanVien = itemView.findViewById(R.id.itemMHQLNV_anhNhanVien);
            itemMHQLNV = itemView.findViewById(R.id.itemMHQLNV);
            itemMHQLNV_tvThemNhanVien = itemView.findViewById(R.id.layoutMHQLNV_tvThemNhanVien);
            itemMHQLNV = itemMHQLNV.findViewById(R.id.itemMHQLNV);
            itemMHQLNV_tvTroVe = itemView.findViewById(R.id.MHQLNV_tvTroVe);

            // Set event processing
          //  itemMHQLNV_tvThemNhanVien.setOnClickListener(this);
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
