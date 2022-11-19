package com.example.nhasachonlinedidong2.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.tools.SharePreferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoiMatKhauActivity extends AppCompatActivity {
    private EditText layoutTDMK_edXacNhanMatKhauMoi, layoutTDMK_edMatKhauMoi, layoutTDMK_edMatKhauHienTai;
    private Button layoutTDMK_btnDoiMatKhau;
    private ImageButton layoutTDMK_imgbtnTroVe;
    private FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();

    String maKhachHang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doimatkhau_layout);

        maKhachHang = sharePreferences.layMa(this);

        layoutTDMK_imgbtnTroVe = findViewById(R.id.layoutTDMK_imgbtnTroVe);

        layoutTDMK_imgbtnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setControl();
        setEvent();

    }

    private void setEvent() {
        layoutTDMK_btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    //get current userID
                    String oldPass = layoutTDMK_edMatKhauHienTai.getText().toString();
                    String newPass = layoutTDMK_edMatKhauMoi.getText().toString();

                    Log.d("Test", "" + maKhachHang);
                    Log.d("Test", "" + layoutTDMK_edMatKhauHienTai.getText().toString());
                    Log.d("Test", "" + layoutTDMK_edMatKhauMoi.getText().toString());
                    fireBaseNhaSachOnline.doiMatkhau(DoiMatKhauActivity.this, oldPass, newPass, maKhachHang);
                }
            }
        });
    }

    private boolean validate() {

        //More than 8 char, required at least: 1 char, 1 number, 1 special char.
        Pattern patternPassword = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        Matcher matcherPassword = patternPassword.matcher(layoutTDMK_edMatKhauMoi.getText().toString());

        Boolean boolMatKhauHienTai = !layoutTDMK_edMatKhauHienTai.getText().toString().isEmpty();
        Boolean boolMatKhauMoi = matcherPassword.find();
        Boolean boolXacNhanMatKhauMoi = layoutTDMK_edMatKhauMoi.getText().toString().equals(layoutTDMK_edXacNhanMatKhauMoi.getText().toString());

        if (!boolMatKhauHienTai) {
            layoutTDMK_edMatKhauHienTai.setError("Bạn phải nhập mật khẩu cũ");
        }
        if (!boolMatKhauMoi) {
            layoutTDMK_edMatKhauMoi.setError("Mật khẩu mới cần ít nhất 8 ký tự, một chữ cái, một chữ số và một ký tự đặc biệt");
        }
        if (!boolXacNhanMatKhauMoi) {
            layoutTDMK_edXacNhanMatKhauMoi.setError("Mật khẩu mới và xác nhận mật khẩu mới không giống nhau");
        }

        return boolMatKhauHienTai && boolMatKhauMoi && boolXacNhanMatKhauMoi;
    }

    private void setControl() {
        layoutTDMK_edXacNhanMatKhauMoi = findViewById(R.id.layoutTDMK_edXacNhanMatKhauMoi);
        layoutTDMK_edMatKhauMoi = findViewById(R.id.layoutTDMK_edMatKhauMoi);
        layoutTDMK_edMatKhauHienTai = findViewById(R.id.layoutTDMK_edMatKhauHienTai);
        layoutTDMK_btnDoiMatKhau = findViewById(R.id.layoutTDMK_btnDoiMatKhau);

    }


}
