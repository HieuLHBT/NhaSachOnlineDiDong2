package com.example.nhasachonlinedidong2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuenMatKhauActivity extends AppCompatActivity {
    EditText layoutQMK_edtNhapTaiKhoan, layoutQMK_edtNhapSDT;
    Button layoutQMK_btnLayLaiMK, layoutQMK_btnQuayLai;
    FireBaseNhaSachOnline fireBaseNhaSachOnline = new FireBaseNhaSachOnline();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quenmatkhau_layout);

        layoutQMK_edtNhapTaiKhoan = findViewById(R.id.layoutQMK_edtNhapTaiKhoan);
        layoutQMK_edtNhapSDT = findViewById(R.id.layoutQMK_edtNhapSDT);
        layoutQMK_btnLayLaiMK = findViewById(R.id.layoutQMK_btnLayLaiMK);
        layoutQMK_btnQuayLai = findViewById(R.id.layoutQMK_btnQuayLai);

        layoutQMK_btnLayLaiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidateField()) {
                    fireBaseNhaSachOnline.guiYeuCauQuaSoDienThoai(layoutQMK_edtNhapTaiKhoan.getText().toString(), layoutQMK_edtNhapSDT.getText().toString(), QuenMatKhauActivity.this);
                }
            }
        });

        layoutQMK_btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private boolean checkValidateField() {
        //Tu 6 den 20 ky tu khong khoang trang va ky tu dac biet
        Pattern patternAccount = Pattern.compile("^[A-Za-z][A-Za-z0-9]{5,20}$");
        Matcher matcherAccount = patternAccount.matcher(layoutQMK_edtNhapTaiKhoan.getText().toString());

        boolean boolAccount = matcherAccount.find();
        boolean boolPhone = !layoutQMK_edtNhapSDT.getText().toString().isEmpty();
        if (!boolAccount) {
            layoutQMK_edtNhapTaiKhoan.setError("T??n ????ng nh???p t??? 6 ?????n 20 k?? t???, kh??ng kho???ng tr???ng v?? k?? t??? ?????c bi???t");
        }
        if (!boolPhone) {
            layoutQMK_edtNhapSDT.setError("Vui l??ng nh???p s??? ??i???n tho???i");
        }

        return boolAccount && boolPhone;
    }
}
