package com.example.nhasachonlinedidong2.activity;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.data_model.KhachHang;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.item.ItemKhachHang;
import com.example.nhasachonlinedidong2.tools.SharePreferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DangKyActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private ArrayList<com.example.nhasachonlinedidong2.data_model.KhachHang> khachHangsModel = new ArrayList<>();


    Button
            layoutDK_btnTaoTaiKhoan;
    ImageButton
            layoutDK_btnBack;
    EditText layoutDK_edtNhapTaiKhoan,
            layoutDK_edtEmail,
            layoutDK_edtNhapMatKhau,
            layoutDK_edtNhapLaiMatKhau,
            layoutDK_edtHoTen,
            layoutDK_edtNgaySinh,
            layoutDK_edtSDT,
            layoutDK_edtDiaChi;
    Spinner
            layoutDK_spGioiTinh;
    String
            email,
            account;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky_layout);
        setControl();
        setEvent();
        account = layoutDK_edtNhapTaiKhoan.getText().toString();
        email = layoutDK_edtEmail.getText().toString();
    }

    private void setEvent() {

        //BTN tao tai khoan - dang ky
        layoutDK_btnTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFillorNot()) {
                    if (passwordValidate()) {
                        if (emailValidate()) {
                            checkAccountIstExist(account);
                        } else {
                            //canh bao email khong hop le
                            AlertDialog alertDialog = new AlertDialog.Builder(DangKyActivity.this).create();
                            alertDialog.setTitle("LỖI ĐĂNG Ký");
                            alertDialog.setMessage("Email bạn muốn đăng ký không hợp lệ!");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            alertDialog.show();
                        }
                    } else {
                        //Canh mat khau khong trung khop
                        AlertDialog alertDialog = new AlertDialog.Builder(DangKyActivity.this).create();
                        alertDialog.setTitle("LỖI ĐĂNG Ký");
                        alertDialog.setMessage("Mật khẩu và xác nhận mật khẩu không giống nhau");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }

                } else {
                    //Canh bao nhap thieu thong tin
                    AlertDialog alertDialog = new AlertDialog.Builder(DangKyActivity.this).create();
                    alertDialog.setTitle("LỖI ĐĂNG Ký");
                    alertDialog.setMessage("Bạn chưa nhập đầy đủ thông tin");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            }
        });

        //BTN back
        layoutDK_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setControl() {
        layoutDK_btnTaoTaiKhoan = findViewById(R.id.layoutDK_btnTaoTaiKhoan);
        layoutDK_btnBack = findViewById(R.id.layoutDK_btnBack);
        layoutDK_edtNhapTaiKhoan = findViewById(R.id.layoutDK_edtNhapTaiKhoan);
        layoutDK_edtEmail = findViewById(R.id.layoutDK_edtEmail);
        layoutDK_edtNhapMatKhau = findViewById(R.id.layoutDK_edtNhapMatKhau);
        layoutDK_edtNhapLaiMatKhau = findViewById(R.id.layoutDK_edtNhapLaiMatKhau);
        layoutDK_edtHoTen = findViewById(R.id.layoutDK_edtHoTen);
        layoutDK_edtNgaySinh = findViewById(R.id.layoutDK_edtNgaySinh);
        layoutDK_edtSDT = findViewById(R.id.layoutDK_edtSDT);
        layoutDK_edtDiaChi = findViewById(R.id.layoutDK_edtDiaChi);
        layoutDK_spGioiTinh = findViewById(R.id.layoutDK_spGioiTinh);
    }

    //Minh
    protected boolean checkFillorNot() {
        //kiem tra du lieu nhap vao phai khac null
        if (!layoutDK_edtNhapTaiKhoan.getText().toString().isEmpty()
                && !layoutDK_edtNhapMatKhau.getText().toString().isEmpty()
                && !layoutDK_edtNhapLaiMatKhau.getText().toString().isEmpty()
                && !layoutDK_edtEmail.getText().toString().isEmpty()
                && !layoutDK_edtHoTen.getText().toString().isEmpty()
                && !layoutDK_edtNgaySinh.getText().toString().isEmpty()
                && !layoutDK_edtSDT.getText().toString().isEmpty()
                && !layoutDK_edtDiaChi.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    //Minh
    protected boolean passwordValidate() {
        String pass = layoutDK_edtNhapMatKhau.getText().toString();
        String rePass = layoutDK_edtNhapLaiMatKhau.getText().toString();
        return pass.equals(rePass);
    }

    public void checkAccountIstExist(String account) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG").child("khachhang");
        nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int counter = (int)snapshot.getChildrenCount() + 1;
                int isActivated = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    KhachHang khachHang = dataSnapshot.getValue(KhachHang.class);
                    if(khachHang.getTaiKhoan().equals(account.toLowerCase())){
                        AlertDialog alertDialog = new AlertDialog.Builder(DangKyActivity.this).create();
                        alertDialog.setTitle("Lỗi");
                        alertDialog.setMessage("tên tài khoản đã tồn tại");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                        isActivated = 1;
                        break;
                    }
                }
                if (isActivated == 0)
                {
                    addUser(counter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    protected boolean emailValidate() {
        Pattern pattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(layoutDK_edtEmail.getText().toString());

        return matcher.find();
    }

    protected void addUser(int counter){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG").child("khachhang");
        nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                KhachHang khachHangPUSH = new KhachHang();
                khachHangPUSH.setMaKhachHang("kh" + counter);
                khachHangPUSH.setTaiKhoan(layoutDK_edtNhapTaiKhoan.getText().toString().toLowerCase());
                khachHangPUSH.setMatKhau(layoutDK_edtNhapMatKhau.getText().toString());
                khachHangPUSH.setDiaChi(layoutDK_edtDiaChi.getText().toString());
                khachHangPUSH.setEmail(layoutDK_edtEmail.getText().toString());
                khachHangPUSH.setSoDienThoai(layoutDK_edtSDT.getText().toString());
                khachHangPUSH.setTenKhachHang(layoutDK_edtHoTen.getText().toString());
                khachHangPUSH.setNguoiDung("khachhang");
                khachHangPUSH.setNganHang("");
                khachHangPUSH.setSoTaiKhoan("");
                nguoiDungDatabase.child(khachHangPUSH.getMaKhachHang()).setValue(khachHangPUSH);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}
