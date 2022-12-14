package com.example.nhasachonlinedidong2.activity;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.QuanLySanPham_SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NhapKhoSachActivity extends AppCompatActivity {
    private EditText layoutNKS_tvNhaCungCap, layoutNKS_tvSoLuongNhap;
    private TextView layoutNKS_btnTroVe,
            layoutNKS_tvMaSach,
            layoutNKS_tvTenSach,
            layoutNKS_tvTheLoai,
            layoutNKS_tvTacGia,
            layoutNKS_tvNhaXuatBan,
            layoutNKS_tvNgayXuatBan,
            layoutNKS_tvGiaTien,
            layoutNKS_tvKhuyenMai,
            layoutNKS_tvSoLuongKho,
            layoutNKS_tvNgayNhapKho;
    private ImageView layoutNKS_imgHinhSach;
    private Button layoutNKS_btnNhapKho, layoutNKS_btnNhapMoi;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    private QuanLySanPham_SanPham sanPham = new QuanLySanPham_SanPham();
    private LocalDate ngay = LocalDate.now();
    private String ngayHienTai;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhapkhosach_layout);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ngayHienTai = ngay.format(formatter);
        sanPham = (QuanLySanPham_SanPham) getIntent().getSerializableExtra("sanPham");

        layoutNKS_btnTroVe = findViewById(R.id.layoutNKS_btnTroVe);
        layoutNKS_imgHinhSach = findViewById(R.id.layoutNKS_imgHinhSach);
        layoutNKS_tvMaSach = findViewById(R.id.layoutNKS_tvMaSach);
        layoutNKS_tvTenSach = findViewById(R.id.layoutNKS_tvTenSach);
        layoutNKS_tvTheLoai = findViewById(R.id.layoutNKS_tvTheLoai);
        layoutNKS_tvTacGia = findViewById(R.id.layoutNKS_tvTacGia);
        layoutNKS_tvNhaXuatBan = findViewById(R.id.layoutNKS_tvNhaXuatBan);
        layoutNKS_tvNgayXuatBan = findViewById(R.id.layoutNKS_tvNgayXuatBan);
        layoutNKS_tvGiaTien = findViewById(R.id.layoutNKS_tvGiaTien);
        layoutNKS_tvKhuyenMai = findViewById(R.id.layoutNKS_tvKhuyenMai);
        layoutNKS_tvSoLuongKho = findViewById(R.id.layoutNKS_tvSoLuongKho);
        layoutNKS_btnNhapKho = findViewById(R.id.layoutNKS_btnNhapKho);
        layoutNKS_btnNhapMoi = findViewById(R.id.layoutNKS_btnNhapMoi);
        layoutNKS_tvNgayNhapKho = findViewById(R.id.layoutNKS_tvNgayNhapKho);
        layoutNKS_tvNhaCungCap = findViewById(R.id.layoutNKS_tvNhaCungCap);
        layoutNKS_tvSoLuongNhap = findViewById(R.id.layoutNKS_tvSoLuongNhap);

        hienThiThongTinSach();

        layoutNKS_btnNhapKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(NhapKhoSachActivity.this);
                b.setTitle("TH??NG B??O");
                b.setMessage("B???n ?????ng ?? x??c nh???n s???a s???n ph???m kh??ng?");
                b.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (kiemTra()) {
                            fireBaseNhaSachOnline.nhapKhoSach(sanPham, ngayHienTai, layoutNKS_tvNhaCungCap.getText().toString(), layoutNKS_tvSoLuongNhap.getText().toString(), NhapKhoSachActivity.this);
                        }
                    }
                });
                b.setNegativeButton("Kh??ng ?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });


        layoutNKS_btnNhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutNKS_tvNhaCungCap.getText().clear();
                layoutNKS_tvSoLuongNhap.getText().clear();
            }
        });

        layoutNKS_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void hienThiThongTinSach() {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        layoutNKS_tvNgayNhapKho.setText(ngayHienTai);
        layoutNKS_tvMaSach.setText(sanPham.getMaSanPham());
        layoutNKS_tvTenSach.setText(sanPham.getTenSanPham());
        layoutNKS_tvTheLoai.setText(sanPham.getTheLoai());
        layoutNKS_tvTacGia.setText(sanPham.getTacGia());
        layoutNKS_tvNhaXuatBan.setText(sanPham.getNhaXuatBan());
        layoutNKS_tvNgayXuatBan.setText(sanPham.getNgayXuatBan());
        layoutNKS_tvGiaTien.setText(formatter.format(sanPham.getGiaSanPham()) + " VN??");
        layoutNKS_tvKhuyenMai.setText(sanPham.getKhuyenMai() + "%");
        layoutNKS_tvSoLuongKho.setText(String.valueOf(sanPham.getSoLuongKho()));
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
                    layoutNKS_imgHinhSach.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("onCancelled", "L???i!" + e.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected boolean kiemTra() {
        boolean boolNhaCungCap = !layoutNKS_tvNhaCungCap.getText().toString().isEmpty();
        boolean boolSoLuongNhap = !layoutNKS_tvSoLuongNhap.getText().toString().isEmpty() && !layoutNKS_tvSoLuongNhap.getText().toString().equalsIgnoreCase("0");

        if (!boolNhaCungCap) {
            layoutNKS_tvNhaCungCap.setError("Kh??ng ???????c b??? tr???ng");
        }
        if (!boolSoLuongNhap) {
            layoutNKS_tvSoLuongNhap.setError("Kh??ng ???????c b??? tr???ng");
        }
        return boolNhaCungCap && boolSoLuongNhap;
    }
}
