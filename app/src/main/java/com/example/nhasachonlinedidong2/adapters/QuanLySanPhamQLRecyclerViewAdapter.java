package com.example.nhasachonlinedidong2.adapters;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.item.ItemQuanLySanPhamNV;
import com.example.nhasachonlinedidong2.item.ItemQuanLySanPhamQL;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuanLySanPhamQLRecyclerViewAdapter extends RecyclerView.Adapter<QuanLySanPhamQLRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<ItemQuanLySanPhamQL> itemQuanLySanPhamQLs;
    private OnItemClickListener onItemClickListener;

    public QuanLySanPhamQLRecyclerViewAdapter(Activity context, int resource, ArrayList<ItemQuanLySanPhamQL> itemQuanLySanPhamQLs){
        this.context = context;
        this.resource = resource;
        this.itemQuanLySanPhamQLs = itemQuanLySanPhamQLs;
    }
    public void setFilteredList(ArrayList<ItemQuanLySanPhamQL> filteredList){
        this.itemQuanLySanPhamQLs = filteredList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public QuanLySanPhamQLRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        ItemQuanLySanPhamQL itemQuanLySanPhamQL = itemQuanLySanPhamQLs.get(pos);
        holder.itemQLSP_QL_txtMaSanPham.setText(itemQuanLySanPhamQL.getMaSanPham());
        holder.itemQLSP_QL_txtTenSanPham.setText(itemQuanLySanPhamQL.getTenSanPham());
        holder.itemQLSP_QL_txtGiaTien.setText(formatter.format(itemQuanLySanPhamQL.getGiaTien()) + " VNĐ");
        holder.itemQLSP_QL_txtSoLuongTrongKho.setText(itemQuanLySanPhamQL.getSoLuong() + " ");

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(itemQuanLySanPhamQL.getHinhSanPham());
        try {
            File file = null;
            if (itemQuanLySanPhamQL.getHinhSanPham().contains("png")) {
                file = File.createTempFile(itemQuanLySanPhamQL.getHinhSanPham().substring(0, itemQuanLySanPhamQL.getHinhSanPham().length() - 4), "png");
            } else if (itemQuanLySanPhamQL.getHinhSanPham().contains("jpg")) {
                file = File.createTempFile(itemQuanLySanPhamQL.getHinhSanPham().substring(0, itemQuanLySanPhamQL.getHinhSanPham().length() - 4), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemQLSP_QL_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
        return itemQuanLySanPhamQLs.size();
    }

    public int getItemViewType(int position){
        return  resource;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemQLSP_QL_txtMaSanPham;
        TextView itemQLSP_QL_txtTenSanPham;
        TextView itemQLSP_QL_txtGiaTien;
        TextView itemQLSP_QL_txtSoLuongTrongKho;

        ImageView itemQLSP_QL_imgHinhSanPham;

        Button itemQLSP_QL_btnXoa;
        Button itemQLSP_QL_btnSua;
        Button itemQLSP_QL_btnNhapKho;

        View.OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemQLSP_QL_txtMaSanPham = itemView.findViewById(R.id.itemQLSP_QL_txtMaSanPham);
            itemQLSP_QL_txtTenSanPham = itemView.findViewById(R.id.itemQLSP_QL_txtTenSanPham);
            itemQLSP_QL_txtGiaTien = itemView.findViewById(R.id.itemQLSP_QL_txtGiaTien);
            itemQLSP_QL_txtSoLuongTrongKho = itemView.findViewById(R.id.itemQLSP_QL_txtSoLuongTrongKho);
            itemQLSP_QL_imgHinhSanPham = itemView.findViewById(R.id.itemQLSP_QL_imgHinhSanPham);
            itemQLSP_QL_btnXoa = itemView.findViewById(R.id.itemQLSP_QL_btnXoa);
            itemQLSP_QL_btnSua = itemView.findViewById(R.id.itemQLSP_QL_btnSua);
            itemQLSP_QL_btnNhapKho = itemView.findViewById(R.id.itemQLSP_QL_btnNhapKho);

            itemQLSP_QL_btnXoa.setOnClickListener(this);
            itemQLSP_QL_btnSua.setOnClickListener(this);
            itemQLSP_QL_btnNhapKho.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }
    public interface OnItemClickListener {
        void onItemClickListener(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
