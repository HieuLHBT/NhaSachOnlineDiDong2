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

public class NhapKhoSachActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    private String maSanPham;
    //private String maNhanVien;
    private String maNhapKho = "nk1";

    private Sach sach = new Sach();
    private NhapKho nhapKho = new NhapKho();
    private String ngay;

    private EditText layoutNhapKhoSach_edtMaNhapKho;
    private EditText layoutNhapKhoSach_edtMaNhanVien;
    private EditText layoutNhapKhoSach_edtMaSanPham;
    private EditText layoutNhapKhoSach_edtTenSanPham;
    private EditText layoutNhapKhoSach_edtNgayNhapKho;
    private EditText layoutNhapKhoSach_edtGiaTien;
    private EditText layoutNhapKhoSach_edtSoLuongNhap;
    private ImageView layoutNhapKhoSach_imgHinhSach;
    private Button layoutNhapKhoSach_btnNhapKho;
    private Button layoutNhapKhoSach_btnTroVe;

    private DecimalFormat formatter = new DecimalFormat("#,###,###");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhapkhosach_layout);

        maSanPham = getIntent().getStringExtra("maSanPham");
        //maNhanVien = getIntent().getStringExtra("maNhanVien");
        layoutNhapKhoSach_edtMaNhapKho = findViewById(R.id.layoutNhapKhoSach_edtMaNhapKho);
        layoutNhapKhoSach_edtMaNhanVien = findViewById(R.id.layoutNhapKhoSach_edtMaNhanVien);
        layoutNhapKhoSach_edtMaSanPham = findViewById(R.id.layoutNhapKhoSach_edtMaSanPham);
        layoutNhapKhoSach_edtTenSanPham = findViewById(R.id.layoutNhapKhoSach_edtTenSanPham);
        layoutNhapKhoSach_edtNgayNhapKho = findViewById(R.id.layoutNhapKhoSach_edtNgayNhapKho);
        layoutNhapKhoSach_edtGiaTien = findViewById(R.id.layoutNhapKhoSach_edtGiaTien);
        layoutNhapKhoSach_edtSoLuongNhap = findViewById(R.id.layoutNhapKhoSach_edtSoLuongNhap);
        layoutNhapKhoSach_btnNhapKho = findViewById(R.id.layoutNhapKhoSach_btnNhapKho);
        layoutNhapKhoSach_btnTroVe = findViewById(R.id.layoutNhapKhoSach_btnTroVe);
        layoutNhapKhoSach_imgHinhSach = findViewById(R.id.layoutNhapKhoSach_imgHinhSach);

        fireBase.hienThiTTSachNhapKho(maSanPham, sach, this);
        SimpleDateFormat sdfDay = new SimpleDateFormat("dd.MM.yyyy");

        ngay = sdfDay.format(new Date());
        layoutNhapKhoSach_edtNgayNhapKho.setText(ngay);


        layoutNhapKhoSach_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void thongTinSach(){
        layoutNhapKhoSach_edtMaSanPham.setText(sach.getMaSach());
        layoutNhapKhoSach_edtTenSanPham.setText(sach.getTenSach());
        layoutNhapKhoSach_edtGiaTien.setText(sach.getGiaTien());

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(sach.getHinhSach());
        try {
            File file = null;
            if (sach.getHinhSach().contains("png")) {
                file = File.createTempFile(sach.getHinhSach().substring(0,sach.getHinhSach().length()), "png");
            } else if (sach.getHinhSach().contains("jpg")) {
                file = File.createTempFile(sach.getHinhSach().substring(0,sach.getHinhSach().length()), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    layoutNhapKhoSach_imgHinhSach.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
