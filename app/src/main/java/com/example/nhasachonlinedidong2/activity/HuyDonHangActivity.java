package com.example.nhasachonlinedidong2.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.tools.SharePreferences;

public class HuyDonHangActivity extends AppCompatActivity {
    private TextView layoutHDH_tvMaDonHang;
    private EditText layoutHDH_edtLyDoHuy;
    private Button layoutHDH_btnHuy, layoutHDH_btnXacNhan;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    private String maDonHang;
    private String maNhanVien;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.huydonhang_layout);

        maNhanVien = sharePreferences.layMa(this);
        maDonHang = getIntent().getStringExtra("maDonHang");

        layoutHDH_tvMaDonHang = findViewById(R.id.layoutHDH_tvMaDonHang);
        layoutHDH_edtLyDoHuy = findViewById(R.id.layoutHDH_edtLyDoHuy);
        layoutHDH_btnHuy = findViewById(R.id.layoutHDH_btnHuy);
        layoutHDH_btnXacNhan = findViewById(R.id.layoutHDH_btnXacNhan);

        layoutHDH_tvMaDonHang.setText(maDonHang);

        layoutHDH_btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutHDH_btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutHDH_edtLyDoHuy.getText().toString().trim().equalsIgnoreCase("")) {
                    AlertDialog.Builder b = new AlertDialog.Builder(HuyDonHangActivity.this);
                    b.setTitle("CẢNH BÁO");
                    b.setMessage("Vui lòng nhập nội dung lý do hủy đơn hàng!");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                } else {
                    AlertDialog.Builder b = new AlertDialog.Builder(HuyDonHangActivity.this);
                    b.setTitle("CẢNH BÁO");
                    b.setMessage("Bạn xác nhận muốn hủy đơn hàng?");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            fireBaseNhaSachOnline.huyDonHang(maNhanVien, maDonHang, layoutHDH_edtLyDoHuy.getText().toString(), HuyDonHangActivity.this);
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
            }
        });
    }

}
