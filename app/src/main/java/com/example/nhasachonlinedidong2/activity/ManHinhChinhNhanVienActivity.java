package com.example.nhasachonlinedidong2.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.adapters.ManHinhChinhNhanVienRecyclerViewAdapter;
import com.example.nhasachonlinedidong2.data_model.NhanVien;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.ItemManHinhChinhNhanVien;
import com.example.nhasachonlinedidong2.tools.SharePreferences;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManHinhChinhNhanVienActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private String maNhanVien = "nv2";
    private String ngay;
    private String thoiGian;
    private String mocThoiGian = "12.00";


    private ManHinhChinhNhanVienRecyclerViewAdapter adapter;
    private ArrayList<ItemManHinhChinhNhanVien> itemManHinhChinhNhanViens = new ArrayList<>();
    private NhanVien nhanVien = new NhanVien();

    private TextView layoutMHCNV_txtNgay;
    private TextView layoutMHCNV_NV_txtHoTen;
    private TextView layoutMHCNV_NV_txtMaNhanVien;
    private Button layoutMHCNV_btnCheckin;
    private Button layoutMHCNV_btnCheckout;
    private Button layoutMHCNV_btnQuanLyDonHang;
    private Button layoutMHCNV_btnQuanLySanPham;
    private Button layoutMHCNV_btnLichLamViec;
    private Button layoutMHCNV_btnBangCong;
    private Button layoutMHCNV_btnPhanHoiKhachHang;
    private ImageView layoutMHCNV_NV_imgHinhNhanVien;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchinh_nhanvien_layout);

        maNhanVien = getIntent().getStringExtra("maNhanVien");
        maNhanVien = "nv1";

        layoutMHCNV_txtNgay = findViewById(R.id.layoutMHCNV_txtNgay);
        layoutMHCNV_btnCheckin = findViewById(R.id.layoutMHCNV_btnCheckin);
        layoutMHCNV_btnCheckout = findViewById(R.id.layoutMHCNV_btnCheckout);
        layoutMHCNV_btnQuanLyDonHang = findViewById(R.id.layoutMHCNV_btnQuanLyDonHang);
        layoutMHCNV_btnQuanLySanPham = findViewById(R.id.layoutMHCNV_btnQuanLySanPham);
        layoutMHCNV_btnLichLamViec = findViewById(R.id.layoutMHCNV_btnLichLamViec);
        layoutMHCNV_btnBangCong = findViewById(R.id.layoutMHCNV_btnBangCong);
        layoutMHCNV_btnPhanHoiKhachHang = findViewById(R.id.layoutMHCNV_btnPhanHoiKhachHang);
        layoutMHCNV_NV_txtHoTen = findViewById(R.id.layoutMHCNV_NV_txtHoTen);
        layoutMHCNV_NV_txtMaNhanVien = findViewById(R.id.layoutMHCNV_NV_txtMaNhanVien);
        layoutMHCNV_NV_imgHinhNhanVien = findViewById(R.id.layoutMHCNV_NV_imgHinhNhanVien);

        adapter = new ManHinhChinhNhanVienRecyclerViewAdapter(this, R.layout.manhinhchinh_nhanvien_item, itemManHinhChinhNhanViens);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);


        SimpleDateFormat sdfDay = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("hh.mm");

        ngay = sdfDay.format(new Date());
        thoiGian = sdfTime.format(new Date());

        layoutMHCNV_txtNgay.setText(ngay);

        fireBase.hienThiManHinhChinhNhanVien(maNhanVien, nhanVien, this);

        layoutMHCNV_btnCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ItemManHinhChinhNhanVien> mhcnv = new ArrayList<>();
                ThongBaoXacNhanCheckin(mhcnv);
            }
        });
        layoutMHCNV_btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ItemManHinhChinhNhanVien> mhcnv = new ArrayList<>();
                ThongBaoXacNhanCheckout(mhcnv);
            }
        });
        layoutMHCNV_btnQuanLyDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, QuanLyDonHangNVActivity.class);
                intent.putExtra("maNhanVien", maNhanVien);
                ManHinhChinhNhanVienActivity.this.startActivity(intent);
            }
        });
        layoutMHCNV_btnQuanLySanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, QuanLySanPhamNVActivity.class);
                intent.putExtra("maNhanVien", maNhanVien);
                ManHinhChinhNhanVienActivity.this.startActivity(intent);
            }
        });
        layoutMHCNV_btnLichLamViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, LichLamViecActivity.class);
                intent.putExtra("maNhanVien", maNhanVien);
                ManHinhChinhNhanVienActivity.this.startActivity(intent);
            }
        });
        layoutMHCNV_btnBangCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, BangChamCongActivity.class);
                intent.putExtra("maNhanVien", maNhanVien);
                ManHinhChinhNhanVienActivity.this.startActivity(intent);
            }
        });
        layoutMHCNV_btnPhanHoiKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, PhanHoiYKienKhachHangActivity.class);
                intent.putExtra("maNhanVien", maNhanVien);
                ManHinhChinhNhanVienActivity.this.startActivity(intent);
            }
        });
    }
    public void hienThiManHinhChinhNhanVien(){
        layoutMHCNV_NV_txtMaNhanVien.setText(nhanVien.getMaNhanVien());
        layoutMHCNV_NV_txtHoTen.setText(nhanVien.getTenNhanVien());

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(nhanVien.getHinhNhanVien());
        try {
            File file = null;
            if (nhanVien.getHinhNhanVien().contains("png")) {
                file = File.createTempFile(nhanVien.getHinhNhanVien().substring(0,nhanVien.getHinhNhanVien().length()-4), "png");
            } else if (nhanVien.getHinhNhanVien().contains("jpg")) {
                file = File.createTempFile(nhanVien.getHinhNhanVien().substring(0,nhanVien.getHinhNhanVien().length()-4), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    layoutMHCNV_NV_imgHinhNhanVien.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
    public void ThongBaoXacNhanCheckin(ArrayList<ItemManHinhChinhNhanVien> manHinhChinhNhanViens) {
        AlertDialog.Builder b = new AlertDialog.Builder(ManHinhChinhNhanVienActivity.this);
        b.setTitle("Xác nhận checkin");
        b.setMessage("Ngày checkin: " + ngay + " Thời gian checkin: " + thoiGian);
        b.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, ManHinhChinhNhanVienActivity.class);
                startActivity(intent);
            }
        });
        b.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog al = b.create();
        al.show();
    }
    public void ThongBaoXacNhanCheckout(ArrayList<ItemManHinhChinhNhanVien> manHinhChinhNhanViens) {
        AlertDialog.Builder b = new AlertDialog.Builder(ManHinhChinhNhanVienActivity.this);
        b.setTitle("Xác nhận checkout");
        b.setMessage("Ngày checkout: " + ngay + " Thời gian checkout: " + thoiGian);
        b.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(ManHinhChinhNhanVienActivity.this, ManHinhChinhNhanVienActivity.class);
                startActivity(intent);
            }
        });
        b.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog al = b.create();
        al.show();
    }
}
