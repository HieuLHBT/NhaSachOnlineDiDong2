package com.example.nhasachonlinedidong2.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.data_model.Sach;
import com.example.nhasachonlinedidong2.data_model.VanPhongPham;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.ItemSanPham;
import com.example.nhasachonlinedidong2.tools.SharePreferences;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();
    private String maKhachHang;

    private ImageView layoutCTSP_imgHinhSanPham, layoutCTSP_img1Sao, layoutCTSP_img2Sao, layoutCTSP_img3Sao, layoutCTSP_img4Sao, layoutCTSP_img5Sao;
    private TextView layoutCTSP_btnTroVe,
            layoutCTSP_tvTenSanPham,
            layoutCTSP_tvTenTacGia,
            layoutCTSP_tvTheLoai,
            layoutCTSP_tvNhaXuatBan,
            layoutCTSP_tvNgayXuatBan,
            layoutCTSP_tvXuatXu,
            layoutCTSP_tvNhaPhanPhoi,
            layoutCTSP_tvDonVi,
            layoutCTSP_tvGiaSanPham,
            layoutCTSP_tvGiaKhuyenMai,
            layoutCTSP_tvKhuyenMai,
            layoutCTSP_tvSoLuong,
            layoutCTSP_tvSoLuongBinhLuan;
    private Button layoutCTSP_btnThemVaoGioHang;
    private ImageButton layoutCTSP_imgbtnCong, layoutCTSP_imgbtnTru;
    private LinearLayout layoutCTSP_llTenTacGia,
            layoutCTSP_llTheLoai,
            layoutCTSP_llNgayXuatBan,
            layoutCTSP_llNhaXuatBan,
            layoutCTSP_llXuatXu,
            layoutCTSP_llNhaPhanPhoi,
            layoutCTSP_llDonVi;
    private int soLuongMua = 1;

    private ItemSanPham sanPham = new ItemSanPham();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanpham_layout);
        sanPham = (ItemSanPham) getIntent().getSerializableExtra("sanPham");
        maKhachHang = sharePreferences.layMa(this);

        layoutCTSP_btnTroVe = findViewById(R.id.layoutCTSP_btnTroVe);
        layoutCTSP_imgHinhSanPham = findViewById(R.id.layoutCTSP_imgHinhSanPham);
        layoutCTSP_tvTenSanPham = findViewById(R.id.layoutCTSP_tvTenSanPham);
        layoutCTSP_tvTenTacGia = findViewById(R.id.layoutCTSP_tvTenTacGia);
        layoutCTSP_tvTheLoai = findViewById(R.id.layoutCTSP_tvTheLoai);
        layoutCTSP_tvNhaXuatBan = findViewById(R.id.layoutCTSP_tvNhaXuatBan);
        layoutCTSP_tvNgayXuatBan = findViewById(R.id.layoutCTSP_tvNgayXuatBan);
        layoutCTSP_tvXuatXu = findViewById(R.id.layoutCTSP_tvXuatXu);
        layoutCTSP_tvNhaPhanPhoi = findViewById(R.id.layoutCTSP_tvNhaPhanPhoi);
        layoutCTSP_tvDonVi = findViewById(R.id.layoutCTSP_tvDonVi);
        layoutCTSP_tvGiaSanPham = findViewById(R.id.layoutCTSP_tvGiaSanPham);
        layoutCTSP_tvGiaKhuyenMai = findViewById(R.id.layoutCTSP_tvGiaKhuyenMai);
        layoutCTSP_tvKhuyenMai = findViewById(R.id.layoutCTSP_tvKhuyenMai);
        layoutCTSP_imgbtnCong = findViewById(R.id.layoutCTSP_imgbtnCong);
        layoutCTSP_imgbtnTru = findViewById(R.id.layoutCTSP_imgbtnTru);
        layoutCTSP_tvSoLuong = findViewById(R.id.layoutCTSP_tvSoLuong);
        layoutCTSP_img1Sao = findViewById(R.id.layoutCTSP_img1Sao);
        layoutCTSP_img2Sao = findViewById(R.id.layoutCTSP_img2Sao);
        layoutCTSP_img3Sao = findViewById(R.id.layoutCTSP_img3Sao);
        layoutCTSP_img4Sao = findViewById(R.id.layoutCTSP_img4Sao);
        layoutCTSP_img5Sao = findViewById(R.id.layoutCTSP_img5Sao);
        layoutCTSP_tvSoLuongBinhLuan = findViewById(R.id.layoutCTSP_tvSoLuongBinhLuan);
        layoutCTSP_btnThemVaoGioHang = findViewById(R.id.layoutCTSP_btnThemVaoGioHang);
        layoutCTSP_llTenTacGia = findViewById(R.id.layoutCTSP_llTenTacGia);
        layoutCTSP_llTheLoai = findViewById(R.id.layoutCTSP_llTheLoai);
        layoutCTSP_llNgayXuatBan = findViewById(R.id.layoutCTSP_llNgayXuatBan);
        layoutCTSP_llNhaXuatBan = findViewById(R.id.layoutCTSP_llNhaXuatBan);
        layoutCTSP_llXuatXu = findViewById(R.id.layoutCTSP_llXuatXu);
        layoutCTSP_llNhaPhanPhoi = findViewById(R.id.layoutCTSP_llNhaPhanPhoi);
        layoutCTSP_llDonVi = findViewById(R.id.layoutCTSP_llDonVi);

        thongTinSanPham();

        layoutCTSP_imgbtnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutCTSP_tvSoLuong.setText(String.valueOf(++soLuongMua));
            }
        });

        layoutCTSP_imgbtnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutCTSP_tvSoLuong.setText(String.valueOf(--soLuongMua));
            }
        });

        layoutCTSP_btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireBase.themVaoGioHang(maKhachHang, sanPham.getMaSanPham(), String.valueOf(soLuongMua));
                Intent intent1 = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(intent1);
            }
        });

        layoutCTSP_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void thongTinSanPham() {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        Log.d("test", sanPham.getMaSanPham().contains("s") + "");
        if (sanPham.getMaSanPham().contains("s")) {
            layoutCTSP_tvTenTacGia.setText(sanPham.getTacGia());
            layoutCTSP_tvTheLoai.setText(sanPham.getTheLoai());
            layoutCTSP_tvNhaXuatBan.setText(sanPham.getNhaXuatBan());
            layoutCTSP_tvNgayXuatBan.setText(sanPham.getNamSanXuat());
            layoutCTSP_llTenTacGia.setVisibility(View.VISIBLE);
            layoutCTSP_llTheLoai.setVisibility(View.VISIBLE);
            layoutCTSP_llNhaXuatBan.setVisibility(View.VISIBLE);
            layoutCTSP_llNgayXuatBan.setVisibility(View.VISIBLE);
            layoutCTSP_llNhaPhanPhoi.setVisibility(View.GONE);
            layoutCTSP_llXuatXu.setVisibility(View.GONE);
            layoutCTSP_llDonVi.setVisibility(View.GONE);
        } else if (sanPham.getMaSanPham().contains("vpp")) {
            layoutCTSP_tvXuatXu.setText(sanPham.getXuatXu());
            layoutCTSP_tvNhaPhanPhoi.setText(sanPham.getNhaPhanPhoi());
            layoutCTSP_tvDonVi.setText(sanPham.getDonVi());
            layoutCTSP_llTenTacGia.setVisibility(View.GONE);
            layoutCTSP_llTheLoai.setVisibility(View.GONE);
            layoutCTSP_llNhaXuatBan.setVisibility(View.GONE);
            layoutCTSP_llNgayXuatBan.setVisibility(View.GONE);
            layoutCTSP_llNhaPhanPhoi.setVisibility(View.VISIBLE);
            layoutCTSP_llXuatXu.setVisibility(View.VISIBLE);
            layoutCTSP_llDonVi.setVisibility(View.VISIBLE);
        }
        layoutCTSP_tvTenSanPham.setText(sanPham.getTenSanPham());
        layoutCTSP_tvGiaSanPham.setText(formatter.format(sanPham.getGiaSanPham()) + " VNĐ");
        int giaKhuyenMai = sanPham.getGiaSanPham() - (sanPham.getGiaSanPham() * sanPham.getKhuyenMai() / 100);
        layoutCTSP_tvGiaKhuyenMai.setText(formatter.format(giaKhuyenMai) + " VNĐ");
        layoutCTSP_tvKhuyenMai.setText(formatter.format(sanPham.getKhuyenMai()) + "%");
        layoutCTSP_tvSoLuong.setText(String.valueOf(soLuongMua));
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
                    layoutCTSP_imgHinhSanPham.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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

        layoutCTSP_tvSoLuongBinhLuan.setText(String.valueOf(sanPham.getSoLuongBinhLuan()));
        switch (sanPham.getTrungBinhDanhGia()) {
            case 0:
                layoutCTSP_img1Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutCTSP_img2Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutCTSP_img3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutCTSP_img4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutCTSP_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 1:
                layoutCTSP_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img2Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutCTSP_img3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutCTSP_img4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutCTSP_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;

            case 2:
                layoutCTSP_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img3Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutCTSP_img4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutCTSP_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 3:
                layoutCTSP_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img4Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                layoutCTSP_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 4:
                layoutCTSP_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img4Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img5Sao.setImageResource(R.drawable.ic_baseline_star_outline_24);
                break;
            case 5:
                layoutCTSP_img1Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img2Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img3Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img4Sao.setImageResource(R.drawable.ic_baseline_star_24);
                layoutCTSP_img5Sao.setImageResource(R.drawable.ic_baseline_star_24);
                break;
        }
    }

}
