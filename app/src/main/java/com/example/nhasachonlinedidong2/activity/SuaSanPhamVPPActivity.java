package com.example.nhasachonlinedidong2.activity;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;

import com.example.nhasachonlinedidong2.data_model.VanPhongPham;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;

import com.example.nhasachonlinedidong2.item.ItemSanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SuaSanPhamVPPActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();

    ArrayList<ItemSanPham> sanPhams = new ArrayList<>();
    EditText MHSSP_VPP_edtMaVPP, MHSSP_VPP_edtTenVPP, MHSSP_VPP_edtNhanPhanPhoi, MHSSP_VPP_edtXuatXu, MHSSP_VPP_edtDonVi
            , MHSSP_VPP_edtGiaTien, MHSSP_VPP_edtSoLuongKho;
    ImageView MHSSP_VPP_imgHinhSP;
    Button MHSSP_VPP_btnNhapMoi, MHSSP_VPP_btnSuaSach;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinh_suasanpham_vpp_layout);
        MHSSP_VPP_edtMaVPP = findViewById(R.id.MHSSP_VPP_edtMaVPP);
        MHSSP_VPP_edtTenVPP = findViewById(R.id.MHSSP_VPP_edtTenVPP);
        MHSSP_VPP_edtNhanPhanPhoi = findViewById(R.id.MHSSP_VPP_edtXuatXu);
        MHSSP_VPP_edtXuatXu = findViewById(R.id.MHSSP_VPP_edtXuatXu);
        MHSSP_VPP_edtDonVi = findViewById(R.id.MHSSP_VPP_edtDonVi);
        MHSSP_VPP_edtGiaTien = findViewById(R.id.MHSSP_VPP_edtGiaTien);
        MHSSP_VPP_edtSoLuongKho = findViewById(R.id.MHSSP_VPP_edtSoLuongKho);
        MHSSP_VPP_imgHinhSP = findViewById(R.id.MHSSP_VPP_imgHinhSP);
        MHSSP_VPP_btnNhapMoi = findViewById(R.id.MHSSP_VPP_btnNhapMoi);
        MHSSP_VPP_btnSuaSach = findViewById(R.id.MHSSP_VPP_btnSuaSach);
        MHSSP_VPP_btnSuaSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(SuaSanPhamVPPActivity.this);
                b.setTitle("CẢNH BÁO");
                b.setMessage("Bạn có muốn sửa thông tin Sản Phẩm không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fireBase.suaVPP(SuaSanPhamVPPActivity.this,
                                "",
                                MHSSP_VPP_imgHinhSP.getResources().toString(),
                                MHSSP_VPP_edtTenVPP.getText().toString(),
                                MHSSP_VPP_edtNhanPhanPhoi.getText().toString(),
                                MHSSP_VPP_edtXuatXu.getText().toString(),
                                MHSSP_VPP_edtDonVi.getText().toString(),
                                MHSSP_VPP_edtGiaTien.getText().toString(),
                                MHSSP_VPP_edtSoLuongKho.getText().toString()
                        );
                        //Kiểm tra các trường bỏ trống
                        if(MHSSP_VPP_edtMaVPP.getTouchables().isEmpty() || MHSSP_VPP_edtTenVPP.getTouchables().isEmpty() || MHSSP_VPP_edtNhanPhanPhoi.getTouchables().isEmpty() ||
                                MHSSP_VPP_edtXuatXu.getTouchables().isEmpty() || MHSSP_VPP_edtDonVi.getTouchables().isEmpty() ||
                                MHSSP_VPP_edtGiaTien.getTouchables().isEmpty() || MHSSP_VPP_edtSoLuongKho.getTouchables().isEmpty()){
                            Toast.makeText(SuaSanPhamVPPActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
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
    }
    public void thongTinSanPhamVPP(VanPhongPham vanPhongPham){
        MHSSP_VPP_edtMaVPP.setText(vanPhongPham.getMaVanPhongPham());
        MHSSP_VPP_edtTenVPP.setText(vanPhongPham.getTenVanPhongPham());
        MHSSP_VPP_edtNhanPhanPhoi.setText(vanPhongPham.getNhaPhanPhoi());
        MHSSP_VPP_edtXuatXu.setText(vanPhongPham.getXuatXu());
        MHSSP_VPP_edtDonVi.setText(vanPhongPham.getDonVi());
        MHSSP_VPP_edtGiaTien.setText(vanPhongPham.getGiaTien());
        MHSSP_VPP_edtSoLuongKho.setText(vanPhongPham.getSoLuongKho());

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(vanPhongPham.getHinhVanPhongPham());
        try {
            File file = null;
            if (vanPhongPham.getHinhVanPhongPham().contains("png")) {
                file = File.createTempFile(vanPhongPham.getHinhVanPhongPham().substring(0,vanPhongPham.getHinhVanPhongPham().length()-4), "png");
            } else if (vanPhongPham.getHinhVanPhongPham().contains("jpg")) {
                file = File.createTempFile(vanPhongPham.getHinhVanPhongPham().substring(0,vanPhongPham.getHinhVanPhongPham().length()-4), "jpg");
            }
            final File fileHinh = file;
            ((StorageReference) storageReference).getFile(fileHinh).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    MHSSP_VPP_imgHinhSP.setImageBitmap(BitmapFactory.decodeFile(fileHinh.getAbsolutePath()));
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
