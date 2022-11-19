package com.example.nhasachonlinedidong2.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.data_model.NhanVien;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.tools.SharePreferences;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManHinhChinhNhanVienActivity extends AppCompatActivity {
    private TextView layoutMHCNV_tvHoTen, layoutMHCNV_tvMaNhanVien, layoutMHCNV_tvNgay;
    private Button layoutMHCNV_btnDanhMucSanPham, layoutMHCNV_btnQuanLyDonHang, layoutMHCNV_btnXacNhanGiaoHang, layoutMHCNV_btnPhanHoiBinhLuan;
    private ImageButton layoutMHCNV_btnLogout;
    private ImageView layoutMHCNV_imgHinhNhanVien;

    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private String maNhanVien;
    private String ngayHienTai;

    private NhanVien nhanVien = new NhanVien();

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchinh_nhanvien_layout);

        ngayHienTai = LocalDate.now().format(formatter);
        maNhanVien = sharePreferences.layMa(this);

        layoutMHCNV_btnLogout = findViewById(R.id.layoutMHCNV_btnLogout);
        layoutMHCNV_tvHoTen = findViewById(R.id.layoutMHCNV_tvHoTen);
        layoutMHCNV_tvMaNhanVien = findViewById(R.id.layoutMHCNV_tvMaNhanVien);
        layoutMHCNV_imgHinhNhanVien = findViewById(R.id.layoutMHCNV_imgHinhNhanVien);
        layoutMHCNV_tvNgay = findViewById(R.id.layoutMHCNV_tvNgay);
        layoutMHCNV_btnDanhMucSanPham = findViewById(R.id.layoutMHCNV_btnDanhMucSanPham);
        layoutMHCNV_btnQuanLyDonHang = findViewById(R.id.layoutMHCNV_btnQuanLyDonHang);
        layoutMHCNV_btnXacNhanGiaoHang = findViewById(R.id.layoutMHCNV_btnXacNhanGiaoHang);
        layoutMHCNV_btnPhanHoiBinhLuan = findViewById(R.id.layoutMHCNV_btnPhanHoiBinhLuan);

        layoutMHCNV_btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePreferences.dangXuat(ManHinhChinhNhanVienActivity.this);
                finish();
            }
        });

        layoutMHCNV_btnDanhMucSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDMSP = new Intent(ManHinhChinhNhanVienActivity.this, DanhMucSanPhamActivity.class);
                startActivity(intentDMSP);
            }
        });

        layoutMHCNV_btnQuanLyDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDMSP = new Intent(ManHinhChinhNhanVienActivity.this, QuanLyDonHangActivity.class);
                startActivity(intentDMSP);
            }
        });

        layoutMHCNV_btnPhanHoiBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDMSP = new Intent(ManHinhChinhNhanVienActivity.this, PhanHoiYKienKhachHangActivity.class);
                startActivity(intentDMSP);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBaseNhaSachOnline.hienThiThongTinNhanVien(maNhanVien, nhanVien, this);
    }

    public void hienThiManHinhChinhNhanVien() {
        layoutMHCNV_tvHoTen.setText(nhanVien.getTenNhanVien());
        layoutMHCNV_tvMaNhanVien.setText(nhanVien.getMaNhanVien());
        layoutMHCNV_tvNgay.setText(ngayHienTai);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(nhanVien.getHinhNhanVien());
        try {
            File file = null;
            if (nhanVien.getHinhNhanVien().contains("png")) {
                file = File.createTempFile(nhanVien.getHinhNhanVien().substring(0, nhanVien.getHinhNhanVien().length() - 4), "png");
            } else if (nhanVien.getHinhNhanVien().contains("jpg")) {
                file = File.createTempFile(nhanVien.getHinhNhanVien().substring(0, nhanVien.getHinhNhanVien().length() - 4), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    layoutMHCNV_imgHinhNhanVien.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
