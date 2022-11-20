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
import com.example.nhasachonlinedidong2.data_model.VanPhongPham;
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

public class NhapKhoVanPhongPhamActivity extends AppCompatActivity {
    private TextView layoutNKVPP_btnTroVe,
            layoutNKVPP_tvMaVanPhongPham,
            layoutNKVPP_tvTenVanPhongPham,
            layoutNKVPP_tvXuatXu,
            layoutNKVPP_tvNhaPhanPhoi,
            layoutNKVPP_tvDonVi,
            layoutNKVPP_tvGiaTien,
            layoutNKVPP_tvKhuyenMai,
            layoutNKVPP_tvSoLuongKho,
            layoutNKVPP_tvNgayNhapKho;
    private ImageView layoutNKVPP_imgHinhVanPhongPham;
    private Button layoutNKVPP_btnNhapKho, layoutNKVPP_btnNhapMoi;
    private EditText layoutNKVPP_tvNhaCungCap, layoutNKVPP_tvSoLuongNhap;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private QuanLySanPham_SanPham sanPham = new QuanLySanPham_SanPham();
    private LocalDate ngay = LocalDate.now();
    private String ngayHienTai;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhapkhovanphongpham_layout);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ngayHienTai = ngay.format(formatter);

        sanPham = (QuanLySanPham_SanPham) getIntent().getSerializableExtra("sanPham");

        layoutNKVPP_btnTroVe = findViewById(R.id.layoutNKVPP_btnTroVe);
        layoutNKVPP_imgHinhVanPhongPham = findViewById(R.id.layoutNKVPP_imgHinhVanPhongPham);
        layoutNKVPP_tvMaVanPhongPham = findViewById(R.id.layoutNKVPP_tvMaVanPhongPham);
        layoutNKVPP_tvTenVanPhongPham = findViewById(R.id.layoutNKVPP_tvTenVanPhongPham);
        layoutNKVPP_tvXuatXu = findViewById(R.id.layoutNKVPP_tvXuatXu);
        layoutNKVPP_tvNhaPhanPhoi = findViewById(R.id.layoutNKVPP_tvNhaPhanPhoi);
        layoutNKVPP_tvDonVi = findViewById(R.id.layoutNKVPP_tvDonVi);
        layoutNKVPP_tvGiaTien = findViewById(R.id.layoutNKVPP_tvGiaTien);
        layoutNKVPP_tvKhuyenMai = findViewById(R.id.layoutNKVPP_tvKhuyenMai);
        layoutNKVPP_tvSoLuongKho = findViewById(R.id.layoutNKVPP_tvSoLuongKho);
        layoutNKVPP_btnNhapKho = findViewById(R.id.layoutNKVPP_btnNhapKho);
        layoutNKVPP_btnNhapMoi = findViewById(R.id.layoutNKVPP_btnNhapMoi);
        layoutNKVPP_tvNgayNhapKho = findViewById(R.id.layoutNKVPP_tvNgayNhapKho);
        layoutNKVPP_tvNhaCungCap = findViewById(R.id.layoutNKVPP_tvNhaCungCap);
        layoutNKVPP_tvSoLuongNhap = findViewById(R.id.layoutNKVPP_tvSoLuongNhap);

        hienThiThongTinVanPhongPham();

        layoutNKVPP_btnNhapKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(NhapKhoVanPhongPhamActivity.this);
                b.setTitle("THÔNG BÁO");
                b.setMessage("Bạn đồng ý xác nhận sửa sản phẩm không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (kiemTra()) {
                            fireBaseNhaSachOnline.nhapKhoVanPhongPham(sanPham, ngayHienTai, layoutNKVPP_tvNhaCungCap.getText().toString(), layoutNKVPP_tvSoLuongNhap.getText().toString(), NhapKhoVanPhongPhamActivity.this);
                        }
                    }
                });
                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });

        layoutNKVPP_btnNhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutNKVPP_tvNhaCungCap.getText().clear();
                layoutNKVPP_tvSoLuongNhap.getText().clear();
            }
        });

        layoutNKVPP_btnTroVe.setOnClickListener(new View.OnClickListener() {
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

    public void hienThiThongTinVanPhongPham() {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        layoutNKVPP_tvNgayNhapKho.setText(ngayHienTai);
        layoutNKVPP_tvMaVanPhongPham.setText(sanPham.getMaSanPham());
        layoutNKVPP_tvTenVanPhongPham.setText(sanPham.getTenSanPham());
        layoutNKVPP_tvXuatXu.setText(sanPham.getXuatXu());
        layoutNKVPP_tvNhaPhanPhoi.setText(sanPham.getNhaPhanPhoi());
        layoutNKVPP_tvDonVi.setText(sanPham.getDonVi());
        layoutNKVPP_tvGiaTien.setText(formatter.format(sanPham.getGiaSanPham()) + " VNĐ");
        layoutNKVPP_tvKhuyenMai.setText(sanPham.getKhuyenMai() + "%");
        layoutNKVPP_tvSoLuongKho.setText(String.valueOf(sanPham.getSoLuongKho()));
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
                    layoutNKVPP_imgHinhVanPhongPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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

    protected boolean kiemTra() {
        boolean boolNhaCungCap = !layoutNKVPP_tvNhaCungCap.getText().toString().isEmpty();
        boolean boolSoLuongNhap = !layoutNKVPP_tvSoLuongNhap.getText().toString().isEmpty() && !layoutNKVPP_tvSoLuongNhap.getText().toString().equalsIgnoreCase("0");

        if (!boolNhaCungCap) {
            layoutNKVPP_tvNhaCungCap.setError("Không được bỏ trống");
        }
        if (!boolSoLuongNhap) {
            layoutNKVPP_tvSoLuongNhap.setError("Không được bỏ trống");
        }
        return boolNhaCungCap && boolSoLuongNhap;
    }
}
