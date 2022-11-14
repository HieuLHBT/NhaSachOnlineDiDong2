package com.example.nhasachonlinedidong2.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.activity.DangKyActivity;
import com.example.nhasachonlinedidong2.activity.ManHinhChinhNhanVienActivity;
import com.example.nhasachonlinedidong2.activity.QuenMatKhauActivity;
import com.example.nhasachonlinedidong2.data_model.KhachHang;
import com.example.nhasachonlinedidong2.data_model.NhanVien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DangNhapActivity extends AppCompatActivity {
    Button
            layoutDN_btnDangNhap,
            layoutDN_btnDangKy,
            layoutDN_btnQuenMatKhau;
    RadioButton
            layoutDN_rdbNhanVien,
            layoutDN_rdbKhachHang;
    EditText
            layoutDN_edtTaiKhoan,
            layoutDN_edtNhapMatKhau;
    CheckBox
            layoutDN_cbNhoMatKHau;

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
                dangNhap();
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
                startActivity(new Intent(DangNhapActivity.this, LayLaimatKhauActivity.class));
            }
        });
    }

    //Đăng nhập
    protected void dangNhap() {
        final String _taiKhoan = layoutDN_edtTaiKhoan.getText().toString();
        final String _matKhau = layoutDN_edtNhapMatKhau.getText().toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference();

        if (layoutDN_rdbNhanVien.isChecked()) {
            nguoiDungDatabase.child("NGUOIDUNG").child("nhanvien").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        NhanVien nhanVien = dataSnapshot.getValue(NhanVien.class);
                        if (nhanVien.getTaiKhoan().equals(_taiKhoan)) {
                            if (nhanVien.getMatKhau().equals(_matKhau)) {
                                //Mo man hinh chinh nhan vien, gui intent
                                Intent intent = new Intent(DangNhapActivity.this, ManHinhChinhNhanVienActivity.class);
                                intent.putExtra("KEY_maNhanVien", nhanVien.getMaNhanVien());
                                startActivity(intent);
                            } else {
                                //Thong bao sai mat khau
                                AlertDialog alertDialog = new AlertDialog.Builder(DangNhapActivity.this).create();
                                alertDialog.setTitle("LỖI ĐĂNG NHẬP");
                                alertDialog.setMessage("Bạn đã nhập sai mật khẩu!");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                alertDialog.show();
                                break;
                            }
                        } else {
                            //Thong bao khong tim thay tai khoan
                            AlertDialog alertDialog = new AlertDialog.Builder(DangNhapActivity.this).create();
                            alertDialog.setTitle("LỖI ĐĂNG NHẬP");
                            alertDialog.setMessage("Không tìm thấy tài khoản!");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            });
                            alertDialog.show();
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else {
            nguoiDungDatabase.child("NGUOIDUNG").child("khachhang").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        KhachHang khachHang = dataSnapshot.getValue(KhachHang.class);
                        if (khachHang.getTaiKhoan().equals(_taiKhoan)) {
                            if (khachHang.getMatKhau().equals(_matKhau)) {
                                //Mo man hinh chinh nhan vien, gui intent
                                Intent intent = new Intent(DangNhapActivity.this, ManHinhChinhKhachHangActivity.class);
                                intent.putExtra("KEY_maKhachHang", khachHang.getMaKhachHang());
                                startActivity(intent);
                            }
                            //Thong bao sai mat khau
                            AlertDialog alertDialog = new AlertDialog.Builder(DangNhapActivity.this).create();
                            alertDialog.setTitle("LỖI ĐĂNG NHẬP");
                            alertDialog.setMessage("Bạn đã nhập sai mật khẩu!");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            alertDialog.show();
                        }
                        //Thong bao khong tim thay tai khoan
                        AlertDialog alertDialog = new AlertDialog.Builder(DangNhapActivity.this).create();
                        alertDialog.setTitle("LỖI ĐĂNG NHẬP");
                        alertDialog.setMessage("Không tìm thấy tài khoản!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}

