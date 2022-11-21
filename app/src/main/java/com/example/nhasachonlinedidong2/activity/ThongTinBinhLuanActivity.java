package com.example.nhasachonlinedidong2.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.adapters.ManHinhChinhKhachHangAdapter;
import com.example.nhasachonlinedidong2.adapters.ThongTinBinhLuanRecycleViewAdapter;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.ItemSanPham;
import com.example.nhasachonlinedidong2.item.ThongTinBinhLuan;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ThongTinBinhLuanActivity extends AppCompatActivity {
    private TextView layoutTTBL_btnTroVe, layoutTTBL_tvTenSanPham, layoutTTBL_tvLuotBinhLuan;
    private ImageView layoutTTBL_imgTenSanPham,
            layoutTTBL_imgHinhSanPham,
            layoutTTBL_img1Sao,
            layoutTTBL_img2Sao,
            layoutTTBL_img3Sao,
            layoutTTBL_img4Sao,
            layoutTTBL_img5Sao;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private ArrayList<ThongTinBinhLuan> thongTinBinhLuans = new ArrayList<>();
    private ThongTinBinhLuanRecycleViewAdapter adapter;
    private ItemSanPham sanPham;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtinbinhluan_layout);

        sanPham = (ItemSanPham) getIntent().getSerializableExtra("sanPham");

        layoutTTBL_btnTroVe = findViewById(R.id.layoutTTBL_btnTroVe);
        layoutTTBL_imgHinhSanPham = findViewById(R.id.layoutTTBL_imgHinhSanPham);
        layoutTTBL_imgTenSanPham = findViewById(R.id.layoutTTBL_imgTenSanPham);
        layoutTTBL_tvTenSanPham = findViewById(R.id.layoutTTBL_tvTenSanPham);
        layoutTTBL_tvLuotBinhLuan = findViewById(R.id.layoutTTBL_tvLuotBinhLuan);
        layoutTTBL_img1Sao = findViewById(R.id.layoutTTBL_img1Sao);
        layoutTTBL_img2Sao = findViewById(R.id.layoutTTBL_img2Sao);
        layoutTTBL_img3Sao = findViewById(R.id.layoutTTBL_img3Sao);
        layoutTTBL_img4Sao = findViewById(R.id.layoutTTBL_img4Sao);
        layoutTTBL_img5Sao = findViewById(R.id.layoutTTBL_img5Sao);

        hienThi();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.layoutTTBL_rvDanhSach);
        adapter = new ThongTinBinhLuanRecycleViewAdapter(this, R.layout.thongtinbinhluan_item, thongTinBinhLuans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        layoutTTBL_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiThongTinBinhLuan(sanPham.getMaSanPham(), thongTinBinhLuans, adapter);
    }

    public void hienThi() {
        Glide.with(this).load(R.drawable.thongtinbinhluan).into(layoutTTBL_imgTenSanPham);
        layoutTTBL_tvTenSanPham.setText(sanPham.getTenSanPham());
        layoutTTBL_tvLuotBinhLuan.setText(sanPham.getSoLuongBinhLuan() + "lượt");
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(sanPham.getHinhSanPham());
        try {
            File file = null;
            if (sanPham.getHinhSanPham().contains("png")) {
                file = File.createTempFile(sanPham.getHinhSanPham().substring(0, sanPham.getHinhSanPham().length() - 4), "png");
            } else if (sanPham.getHinhSanPham().contains("jpg")) {
                file = File.createTempFile(sanPham.getHinhSanPham().substring(0, sanPham.getHinhSanPham().length() - 4), "jpg");
            }
            final File fileHinh = file;
            storageReference.getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    layoutTTBL_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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

        switch (sanPham.getTrungBinhDanhGia()){
            case 0:
                layoutTTBL_img1Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutTTBL_img2Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutTTBL_img3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutTTBL_img4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutTTBL_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 1:
                layoutTTBL_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img2Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutTTBL_img3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutTTBL_img4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutTTBL_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 2:
                layoutTTBL_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutTTBL_img4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutTTBL_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 3:
                layoutTTBL_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutTTBL_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 4:
                layoutTTBL_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img4Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 5:
                layoutTTBL_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img4Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutTTBL_img5Sao.setImageResource(R.drawable.ic_baseline_star_24);
                break;
        }
    }
}
