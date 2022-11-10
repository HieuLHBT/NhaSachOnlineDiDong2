package com.example.nhasachonlinedidong2.adapters;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.item.GioHang;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangRecyclerViewAdapter extends RecyclerView.Adapter<GioHangRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    private int resource;
    private ArrayList<GioHang> gioHangs;
    private OnItemClickListener onItemClickListener;
    private Drawable backBackground;

    public GioHangRecyclerViewAdapter(Activity context, int resource, ArrayList<GioHang> gioHangs) {
        this.context = context;
        this.resource = resource;
        this.gioHangs = gioHangs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView viewItem = (CardView) context.getLayoutInflater().inflate(viewType, parent, false);
        backBackground = viewItem.getBackground();
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        final int pos = position;
        GioHang gioHang = gioHangs.get(pos);
        holder.itemGH_tvTenSanPham.setText(gioHang.getTenSanPham());
        holder.itemGH_tvGiaTien.setText(formatter.format(gioHang.getGiaSanPham()) + " VNĐ");
        holder.itemGH_tvKhuyenMai.setText(gioHang.getKhuyenMai() + " %");
        holder.itemGH_tvSoLuong.setText(gioHang.getSoLuongSanPham() + "");
        holder.itemGH_tvTongTien.setText(formatter.format(gioHang.getTongTien()) + " VNĐ");

        if (gioHang.getCheck() == 0) {
            holder.itemGH.setBackground(backBackground);
        } else if (gioHang.getCheck() == 1) {
            holder.itemGH.setBackgroundColor(context.getResources().getColor(R.color.clickgiohang, context.getTheme()));
        }

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(gioHang.getHinhSanpham());
        try {
            File file = null;
            if (gioHang.getHinhSanpham().contains("png")) {
                file = File.createTempFile(gioHang.getHinhSanpham().substring(0,gioHang.getHinhSanpham().length()-4), "png");
            } else if (gioHang.getHinhSanpham().contains("jpg")) {
                file = File.createTempFile(gioHang.getHinhSanpham().substring(0,gioHang.getHinhSanpham().length()-4), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    holder.itemGH_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
        return gioHangs.size();
    }

    @Override
    public int getItemViewType(int position) {
        return resource;
    }

    // ViewHolder definition
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemGH_tvTenSanPham;
        TextView itemGH_tvGiaTien;
        TextView itemGH_tvKhuyenMai;
        TextView itemGH_tvSoLuong;
        TextView itemGH_tvTongTien;
        ImageView itemGH_imgHinhSanPham;
        ImageButton itemGH_btnTru;
        ImageButton itemGH_btnCong;
        View.OnClickListener onClickListener;
        LinearLayout itemGH_llCardView;
        CardView itemGH;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemGH_tvTenSanPham = itemView.findViewById(R.id.itemGH_tvTenSanPham);
            itemGH_tvGiaTien = itemView.findViewById(R.id.itemGH_tvGiaTien);
            itemGH_tvKhuyenMai = itemView.findViewById(R.id.itemGH_tvKhuyenMai);
            itemGH_tvSoLuong = itemView.findViewById(R.id.itemGH_tvSoLuong);
            itemGH_tvTongTien = itemView.findViewById(R.id.itemGH_tvTongTien);
            itemGH_imgHinhSanPham = itemView.findViewById(R.id.itemGH_imgHinhSanPham);
            itemGH_btnTru = itemView.findViewById(R.id.itemGH_btnTru);
            itemGH_btnCong = itemView.findViewById(R.id.itemGH_btnCong);
            itemGH_llCardView = itemView.findViewById(R.id.itemGH_llCardView);
            itemGH = itemView.findViewById(R.id.itemGH);

            // Set event processing
            itemGH.setOnClickListener(this);
            itemGH_btnTru.setOnClickListener(this);
            itemGH_btnCong.setOnClickListener(this);
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
