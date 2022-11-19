package com.example.nhasachonlinedidong2.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.data_model.KhachHang;
import com.example.nhasachonlinedidong2.firebase.FireBaseNhaSachOnline;
import com.example.nhasachonlinedidong2.tools.SharePreferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThayDoiThongTinKhachHangActivity extends AppCompatActivity {
    private FireBaseNhaSachOnline fireBase = new FireBaseNhaSachOnline();
    private SharePreferences sharePreferences = new SharePreferences();
    private String maKhachHang;
    private KhachHang khachHang = new KhachHang();

    private EditText layoutCSTTKH_edtHoTen, layoutCSTTKH_edtEmail, layoutCSTTKH_edtSoDienThoai, layoutCSTTKH_edtTenNganHang, layoutCSTTKH_edtSTKNganHang, layoutCSTTKH_edtDiaChi;
    private Button layoutCSTTKH_btnLuu;
    private ImageButton layoutCSTTKH_btnTroVe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thaydoithongtin_layout);

        maKhachHang = sharePreferences.layMa(this);

        layoutCSTTKH_edtHoTen = findViewById(R.id.layoutCSTTKH_edtHoTen);
        layoutCSTTKH_edtEmail = findViewById(R.id.layoutCSTTKH_edtEmail);
        layoutCSTTKH_edtSoDienThoai = findViewById(R.id.layoutCSTTKH_edtSoDienThoai);
        layoutCSTTKH_edtTenNganHang = findViewById(R.id.layoutCSTTKH_edtTenNganHang);
        layoutCSTTKH_edtSTKNganHang = findViewById(R.id.layoutCSTTKH_edtSTKNganHang);
        layoutCSTTKH_edtDiaChi = findViewById(R.id.layoutCSTTKH_edtDiaChi);
        layoutCSTTKH_btnTroVe = findViewById(R.id.layoutCSTTKH_btnTroVe);
        layoutCSTTKH_btnLuu = findViewById(R.id.layoutCSTTKH_btnLuu);

        layoutCSTTKH_edtHoTen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                khachHang.setTenKhachHang(String.valueOf(s));
            }
        });

        layoutCSTTKH_edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                khachHang.setEmail(String.valueOf(s));
            }
        });

        layoutCSTTKH_edtSoDienThoai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                khachHang.setSoDienThoai(String.valueOf(s));
            }
        });

        layoutCSTTKH_edtTenNganHang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                khachHang.setNganHang(String.valueOf(s));
            }
        });

        layoutCSTTKH_edtSTKNganHang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                khachHang.setSoTaiKhoan(String.valueOf(s));
            }
        });

        layoutCSTTKH_edtDiaChi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                khachHang.setDiaChi(String.valueOf(s));
            }
        });

        layoutCSTTKH_btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutCSTTKH_btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTra()) {
                    fireBase.suaThongTinKhachHang(khachHang, ThayDoiThongTinKhachHangActivity.this);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fireBase.thayDoiThongTinKhachHang(maKhachHang, khachHang, ThayDoiThongTinKhachHangActivity.this);
    }

    public void thongTinKhachHang() {
        layoutCSTTKH_edtHoTen.setText(khachHang.getTenKhachHang());
        layoutCSTTKH_edtEmail.setText(khachHang.getEmail());
        layoutCSTTKH_edtSoDienThoai.setText(khachHang.getSoDienThoai());
        layoutCSTTKH_edtTenNganHang.setText(khachHang.getNganHang());
        layoutCSTTKH_edtSTKNganHang.setText(khachHang.getSoTaiKhoan());
        layoutCSTTKH_edtDiaChi.setText(khachHang.getDiaChi());
    }

    protected boolean kiemTra() {
        Pattern patternHoTen = Pattern.compile("^[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*(?:[ ][A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*)*$");
        Matcher matcherHoTen = patternHoTen.matcher(layoutCSTTKH_edtHoTen.getText().toString());

        Pattern patternEmail = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcherEmail = patternEmail.matcher(layoutCSTTKH_edtEmail.getText().toString());

        Pattern patternSoDienThoai = Pattern.compile("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
        Matcher matcherSoDienThoai = patternSoDienThoai.matcher(layoutCSTTKH_edtSoDienThoai.getText().toString());

        Pattern patternTenNganHang = Pattern.compile("^[A-Za-z0-9]*$");
        Matcher matcherTenNganHang = patternTenNganHang.matcher(layoutCSTTKH_edtTenNganHang.getText().toString());

        Pattern patternTaiKhoanNganHang = Pattern.compile("^[0-9]*$");
        Matcher matcherTaiKhoanNganHang = patternTaiKhoanNganHang.matcher(layoutCSTTKH_edtSTKNganHang.getText().toString());


        boolean boolHoTen = matcherHoTen.find();
        boolean boolEmail = matcherEmail.find();
        boolean boolSoDienThoai = matcherSoDienThoai.find();
        boolean boolTenNganHang = matcherTenNganHang.find();
        boolean boolTaiKhoanNganHang = matcherTaiKhoanNganHang.find();
        boolean boolDiaChi = !layoutCSTTKH_edtDiaChi.getText().toString().isEmpty();

        if (!boolHoTen) {
            layoutCSTTKH_edtHoTen.setError("Tên Tiếng Việt hoặc Tiếng Anh, viết hoa đầu từ");
        }
        if (!boolEmail) {
            layoutCSTTKH_edtEmail.setError("Vui lòng nhập email hợp lệ (abc@gmail.com)");
        }
        if (!boolSoDienThoai) {
            layoutCSTTKH_edtSoDienThoai.setError("Số điện thoại bạn nhập không hợp lệ");
        }
        if (!boolTenNganHang) {
            layoutCSTTKH_edtTenNganHang.setError("Tên ngân hàng không khoảng trắng và ký tự đặc biệt");
        }
        if (!boolTaiKhoanNganHang) {
            layoutCSTTKH_edtSTKNganHang.setError("Tài khoản ngân hàng chỉ chứa ký tự số");
        }
        if (!boolDiaChi) {
            layoutCSTTKH_edtDiaChi.setError("Không được bỏ trống địa chỉ");
        }
        return boolHoTen && boolEmail && boolSoDienThoai && boolTenNganHang && boolTaiKhoanNganHang && boolDiaChi;
    }
}
