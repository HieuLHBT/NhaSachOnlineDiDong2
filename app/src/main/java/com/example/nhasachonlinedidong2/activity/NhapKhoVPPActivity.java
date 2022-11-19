package com.example.nhasachonlinedidong2.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.data_model.NhapKho;
import com.example.nhasachonlinedidong2.data_model.Sach;
import com.example.nhasachonlinedidong2.data_model.VanPhongPham;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NhapKhoVPPActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private String maSanPham;
    //private String maNhanVien;
    private String maNhapKho = "nk1";
    private VanPhongPham vanPhongPham = new VanPhongPham();
    private NhapKho nhapKho = new NhapKho();
    private String ngay;

    private EditText layoutNhapKhoVPP_edtMaNhapKho;
    private EditText layoutNhapKhoVPP_edtMaNhanVien;
    private EditText layoutNhapKhoVPP_edtMaSanPham;
    private EditText layoutNhapKhoVPP_edtTenSanPham;
    private EditText layoutNhapKhoVPP_edtNgayNhapKho;
    private EditText layoutNhapKhoVPP_edtGiaTien;
    private EditText layoutNhapKhoVPP_edtSoLuongNhap;
    private ImageView layoutNhapKhoVPP_imgHinhVPP;
    private Button layoutNhapKhoVPP_btnNhapKho;
    private Button layoutNhapKhoVPP_btnTroVe;

    private DecimalFormat formatter = new DecimalFormat("#,###,###");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhapkhovanphongpham_layout);

        maSanPham = getIntent().getStringExtra("maSanPham");
        //maNhanVien = getIntent().getStringExtra("maNhanVien");
        layoutNhapKhoVPP_edtMaNhapKho = findViewById(R.id.layoutNhapKhoVPP_edtMaNhapKho);
        layoutNhapKhoVPP_edtMaNhanVien = findViewById(R.id.layoutNhapKhoVPP_edtMaNhanVien);
        layoutNhapKhoVPP_edtMaSanPham = findViewById(R.id.layoutNhapKhoVPP_edtMaSanPham);
        layoutNhapKhoVPP_edtTenSanPham = findViewById(R.id.layoutNhapKhoVPP_edtTenSanPham);
        layoutNhapKhoVPP_edtNgayNhapKho = findViewById(R.id.layoutNhapKhoVPP_edtNgayNhapKho);
        layoutNhapKhoVPP_edtGiaTien = findViewById(R.id.layoutNhapKhoVPP_edtGiaTien);
        layoutNhapKhoVPP_edtSoLuongNhap = findViewById(R.id.layoutNhapKhoVPP_edtSoLuongNhap);
        layoutNhapKhoVPP_btnNhapKho = findViewById(R.id.layoutNhapKhoVPP_btnNhapKho);
        layoutNhapKhoVPP_btnTroVe = findViewById(R.id.layoutNhapKhoVPP_btnTroVe);
        layoutNhapKhoVPP_imgHinhVPP = findViewById(R.id.layoutNhapKhoVPP_imgHinhVPP);

        fireBase.hienThiTTNhapKhoVPP(maNhapKho, nhapKho, this);
        fireBase.hienThiTTVPPNhapKho(maSanPham, vanPhongPham, this);

        SimpleDateFormat sdfDay = new SimpleDateFormat("dd.MM.yyyy");
        ngay = sdfDay.format(new Date());
        layoutNhapKhoVPP_edtNgayNhapKho.setText(ngay);


        layoutNhapKhoVPP_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void thongTinNhapKho(){
        layoutNhapKhoVPP_edtMaNhapKho.setText(nhapKho.getMaNhapKho());
        layoutNhapKhoVPP_edtMaNhanVien.setText(nhapKho.getMaNhanVien());

    }
    public void thongTinVPP(){
        layoutNhapKhoVPP_edtMaSanPham.setText(vanPhongPham.getMaVanPhongPham());
        layoutNhapKhoVPP_edtTenSanPham.setText(vanPhongPham.getTenVanPhongPham());
        layoutNhapKhoVPP_edtGiaTien.setText(vanPhongPham.getGiaTien());

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(vanPhongPham.getHinhVanPhongPham());
        try {
            File file = null;
            if (vanPhongPham.getHinhVanPhongPham().contains("png")) {
                file = File.createTempFile(vanPhongPham.getHinhVanPhongPham().substring(0,vanPhongPham.getHinhVanPhongPham().length()), "png");
            } else if (vanPhongPham.getHinhVanPhongPham().contains("jpg")) {
                file = File.createTempFile(vanPhongPham.getHinhVanPhongPham().substring(0,vanPhongPham.getHinhVanPhongPham().length()), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    layoutNhapKhoVPP_imgHinhVPP.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
}
