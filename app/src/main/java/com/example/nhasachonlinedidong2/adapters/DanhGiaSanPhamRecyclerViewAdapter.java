 package com.example.nhasachonlinedidong2.adapters;

 import android.app.Activity;
 import android.graphics.BitmapFactory;
 import android.graphics.drawable.Drawable;
 import android.util.Log;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ImageView;
 import android.widget.TextView;

 import androidx.annotation.NonNull;
 import androidx.cardview.widget.CardView;
 import androidx.recyclerview.widget.RecyclerView;


 import com.example.nhasachonlinedidong2.R;
 import com.example.nhasachonlinedidong2.item.DanhGiaSanPham;
 import com.google.android.gms.tasks.OnFailureListener;
 import com.google.android.gms.tasks.OnSuccessListener;
 import com.google.firebase.storage.FileDownloadTask;
 import com.google.firebase.storage.FirebaseStorage;
 import com.google.firebase.storage.StorageReference;

 import java.io.File;
 import java.io.IOException;
 import java.util.ArrayList;

 public class DanhGiaSanPhamRecyclerViewAdapter extends RecyclerView.Adapter<DanhGiaSanPhamRecyclerViewAdapter.MyViewHolder> {
     private Activity context;
     private int resource;
     private ArrayList<DanhGiaSanPham> danhGiaSanPhams;
     private OnItemClickListener onItemClickListener;
     private Drawable backBackground;

     public DanhGiaSanPhamRecyclerViewAdapter(Activity context, int resource, ArrayList<DanhGiaSanPham> danhGiaSanPhams){
         this.context = context;
         this.resource = resource;
         this.danhGiaSanPhams = danhGiaSanPhams;
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
         final int pos = position;
         DanhGiaSanPham danhGiaSanPham = danhGiaSanPhams.get(pos);
         holder.itemDGSP_txtTenSanPham.setText(danhGiaSanPham.getTenSanPham());

         if (!danhGiaSanPham.getCheck()) {
             holder.itemDGSP.setBackground(backBackground);
         } else {
             holder.itemDGSP.setBackgroundColor(context.getResources().getColor(R.color.clickgiohang, context.getTheme()));
         }

         StorageReference storageReference = FirebaseStorage.getInstance().getReference(danhGiaSanPham.getHinhSanPham());
         try {
             File file = null;
             if (danhGiaSanPham.getHinhSanPham().contains("png")) {
                 file = File.createTempFile(danhGiaSanPham.getHinhSanPham().substring(0,danhGiaSanPham.getHinhSanPham().length()-4), "png");
             } else if (danhGiaSanPham.getHinhSanPham().contains("jpg")) {
                 file = File.createTempFile(danhGiaSanPham.getHinhSanPham().substring(0,danhGiaSanPham.getHinhSanPham().length()-4), "jpg");
             }
             final File fileHinh = file;
             storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                 @Override
                 public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                     holder.itemDGSP_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
         return danhGiaSanPhams.size();
     }

     public int getItemViewType(int position){
         return  resource;
     }

     public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
         TextView itemDGSP_txtTenSanPham;
         ImageView itemDGSP_imgHinhSanPham;
         View.OnClickListener onClickListener;
         CardView itemDGSP;

         public MyViewHolder(@NonNull View itemView){
             super(itemView);
             itemDGSP_txtTenSanPham = itemView.findViewById(R.id.itemDGSP_txtTenSanPham);
             itemDGSP_imgHinhSanPham = itemView.findViewById(R.id.itemDGSP_imgHinhSanPham);
             itemDGSP = itemView.findViewById(R.id.itemDGSP);

             itemDGSP.setOnClickListener(this);
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
