package com.example.nhasachonlinedidong2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.NhoDangNhap;
import com.example.nhasachonlinedidong2.tools.SharePreferences;

public class DangNhapActivity extends AppCompatActivity {
    private Button layoutDN_btnDangNhap, layoutDN_btnDangKy, layoutDN_btnQuenMatKhau;
    private RadioButton layoutDN_rdbNhanVien, layoutDN_rdbQuanly, layoutDN_rdbKhachHang;
    private EditText layoutDN_edtTaiKhoan, layoutDN_edtNhapMatKhau;
    private CheckBox layoutDN_cbNhoMatKHau;

    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    private NhoDangNhap nhoDangNhap = new NhoDangNhap();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap_layout);
        setControl();
        setEvent();

    }

    private void setControl() {
        layoutDN_btnDangNhap = findViewById(R.id.layoutDN_bntDangNhap);
        layoutDN_btnDangKy = findViewById(R.id.layoutDN_btnDangKy);
        layoutDN_btnQuenMatKhau = findViewById(R.id.layoutDN_btnQuenMatKhau);
        layoutDN_rdbNhanVien = findViewById(R.id.layoutDN_rdbNhanVien);
        layoutDN_rdbKhachHang = findViewById(R.id.layoutDN_rdbKhachHang);
        layoutDN_rdbQuanly = findViewById(R.id.layoutDN_rdbQuanLi);
        layoutDN_edtTaiKhoan = findViewById(R.id.layoutDN_edtTaiKhoan);
        layoutDN_edtNhapMatKhau = findViewById(R.id.layoutDN_edtNhapMatKhau);
        layoutDN_cbNhoMatKHau = findViewById(R.id.layoutDN_cbNhoMatKHau);
        layoutDN_rdbKhachHang.setChecked(true);
    }

    private void setEvent() {

        //BTN DANG NHAP
        layoutDN_btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateLogin()) {
                    String _taikhoan = layoutDN_edtTaiKhoan.getText().toString();
                    String _matkhau = layoutDN_edtNhapMatKhau.getText().toString();
                    boolean checkBox = layoutDN_cbNhoMatKHau.isChecked();
                    fireBaseNhaSachOnline.dangNhap(DangNhapActivity.this, reTurnType(), _taikhoan, _matkhau, checkBox);
                }
            }
        });

        //BTN DANG KY
        layoutDN_btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
            }
        });

        //BTN quen mat khau
        layoutDN_btnQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangNhapActivity.this, QuenMatKhauActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        nhoDangNhap = sharePreferences.layDangNhap(this);
        if (nhoDangNhap.getCheckBox()) {
            layoutDN_edtTaiKhoan.setText(nhoDangNhap.getTaiKhoan());
            layoutDN_edtNhapMatKhau.setText(nhoDangNhap.getMatKhau());
            layoutDN_cbNhoMatKHau.setChecked(true);
            switch (nhoDangNhap.getNguoiDung()) {
                case "khachhang":
                    layoutDN_rdbKhachHang.setChecked(true);
                    break;
                case "nhanvien":
                    layoutDN_rdbNhanVien.setChecked(true);
                    break;
                case "quanly":
                    layoutDN_rdbQuanly.setChecked(true);
                    break;
            }
        } else {
            layoutDN_edtTaiKhoan.getText().clear();
            layoutDN_edtNhapMatKhau.getText().clear();
            layoutDN_cbNhoMatKHau.setChecked(false);
        }
    }

    //Đăng nhập
    public boolean validateLogin() {
        boolean valAccount = !layoutDN_edtTaiKhoan.getText().toString().isEmpty();
        boolean valPass = !layoutDN_edtNhapMatKhau.getText().toString().isEmpty();
        if (!valAccount) {
            layoutDN_edtTaiKhoan.setError("Ban phai dien tai khoan");
        }
        if (!valPass) {
            layoutDN_edtNhapMatKhau.setError("Ban phai dien mat khau");
        }
        return valAccount && valPass;
    }

    public String reTurnType() {
        if (layoutDN_rdbQuanly.isChecked()) {
            return "quanly";
        } else if (layoutDN_rdbNhanVien.isChecked()) {
            return "nhanvien";
        }
        return "khachhang";
    }
}

