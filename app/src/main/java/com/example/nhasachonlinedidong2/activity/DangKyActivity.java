package com.example.nhasachonlinedidong2.activity;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DangKyActivity extends AppCompatActivity {
    private SharePreferences sharePreferences = new SharePreferences();
    private ArrayList<com.example.nhasachonlinedidong2.data_model.KhachHang> khachHangsModel = new ArrayList<>();


    private Button
            layoutDK_btnTaoTaiKhoan;
    private ImageButton
            layoutDK_btnBack;
    private EditText
            layoutDK_edtNhapTaiKhoan,
            layoutDK_edtEmail,
            layoutDK_edtNhapMatKhau,
            layoutDK_edtNhapLaiMatKhau,
            layoutDK_edtHoTen,
            layoutDK_edtNgaySinh,
            layoutDK_edtSDT,
            layoutDK_edtDiaChi;
    FireBaseNhaSachOnline
            fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private int
            mYear,
            mMonth,
            mDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky_layout);
        setControl();
        setEvent();

        }

    private void setEvent() {

        //BTN tao tai khoan - dang ky
        layoutDK_btnTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateRegister()){
                    String _taiKhoan= layoutDK_edtNhapTaiKhoan.getText().toString().toLowerCase();
                    String _matKhau= layoutDK_edtNhapMatKhau.getText().toString();
                    String _diaChi= layoutDK_edtDiaChi.getText().toString();
                    String _email= layoutDK_edtEmail.getText().toString();
                    String _sdt= layoutDK_edtSDT.getText().toString();
                    String _tenKhachHang= layoutDK_edtHoTen.getText().toString();
                    fireBaseNhaSachOnline.dangKy(DangKyActivity.this,_taiKhoan,_matKhau,_diaChi,_email,_sdt,_tenKhachHang);
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

        layoutDK_edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(DangKyActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                layoutDK_edtNgaySinh.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
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

    }

    //Minh
    protected boolean validateRegister() {

        //Tu 6 den 20 ky tu khong khoang trang va ky tu dac biet
        Pattern patternAccount = Pattern.compile("^[A-Za-z][A-Za-z0-9]{5,20}$");
        Matcher matcherAccount = patternAccount.matcher(layoutDK_edtNhapTaiKhoan.getText().toString());

        //email regex
        Pattern patternEmail = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcherEmail = patternEmail.matcher(layoutDK_edtEmail.getText().toString());

        //More than 8 char, required at least: 1 char, 1 number, 1 special char.
        Pattern patternPassword = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        Matcher matcherPassword = patternPassword.matcher(layoutDK_edtNhapMatKhau.getText().toString());

        //Ten tieng viet hoac tieng anh, yeu cau viet hoa dau tu
        Pattern patternName = Pattern.compile("^[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*(?:[ ][A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*)*$");
        Matcher matcherName = patternName.matcher(layoutDK_edtHoTen.getText().toString());

        //So dien thoai Viet Nam
        Pattern patternPhone = Pattern.compile("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
        Matcher matcherPhone = patternPhone.matcher(layoutDK_edtSDT.getText().toString());

        boolean boolAccount = matcherAccount.find();
        boolean boolEmail = matcherEmail.find();
        boolean boolPassword = matcherPassword.find();
        boolean boolRePassword = layoutDK_edtNhapMatKhau.getText().toString().equals(layoutDK_edtNhapLaiMatKhau.getText().toString());
        boolean boolName = matcherName.find();
        boolean boolBirth = !layoutDK_edtNgaySinh.getText().toString().isEmpty();
        boolean boolPhone = matcherPhone.find();
        boolean boolAddress = !layoutDK_edtDiaChi.getText().toString().isEmpty();

        if (!boolAccount) {
            layoutDK_edtNhapTaiKhoan.setError("Tên đăng nhập từ 6 đến 20 ký tự, không khoảng trắng và ký tự đặc biệt");
        }
        if (!boolEmail) {
            layoutDK_edtEmail.setError("Vui lòng nhập email hợp lệ");
        }
        if (!boolPassword) {
            layoutDK_edtNhapMatKhau.setError("Mật khẩu cần ít nhất 8 ký tự, một chữ cái, một chữ số và một ký tự đặc biệt");
        }
        if (!boolRePassword) {
            layoutDK_edtNhapLaiMatKhau.setError("Mật khẩu và xác nhận mật khẩu không giống nhau");
        }
        if (!boolName) {
            layoutDK_edtHoTen.setError("Tên Tiếng Việt hoặc Tiếng Anh, viết hoa đầu từ");
        }
        if (!boolPhone) {
            layoutDK_edtSDT.setError("Số điện thoại bạn nhập không hợp lệ");
        }
        if (!boolBirth) {
            layoutDK_edtNgaySinh.setError("Không được bỏ trống hoặc ngày sinh không hợp lệ");
        }
        if (!boolAddress) {
            layoutDK_edtDiaChi.setError("Không được bỏ trống ");
        }
        return boolAccount && boolEmail && boolPassword && boolRePassword && boolName && boolBirth && boolPhone && boolAddress;
    }

}
