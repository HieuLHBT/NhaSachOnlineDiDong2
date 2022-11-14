package com.example.nhasachonlinedidong2.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhasachonlinedidong2.R;
import com.example.nhasachonlinedidong2.data_model.KhachHang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LayLaimatKhauActivity extends AppCompatActivity {
    EditText
            layoutQMK_edtNhapTaiKhoan,
            layoutQMK_edtNhapEmail;
    Button
            layoutQMK_btnLayLaiMK,
            layoutQMK_btnQuayLai;
    String
            email,
            account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quenmatkhau_layout);
        setControl();
        setEvent();
    }

    public void sendEmailReset(String account, String email) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference nguoiDungDatabase = firebaseDatabase.getReference("NGUOIDUNG").child("khachhang");
        nguoiDungDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    KhachHang khachHang = dataSnapshot.getValue(KhachHang.class);
                    if (khachHang.getTaiKhoan().equals(account) && khachHang.getEmail().equals(email)){
                        //dung tai khoan, gui email xac nhan
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                       auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful()){
                                   Log.d("Gui email", "Gui email thanh cong");
                               }
                           }
                       });
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    protected void warningToUser() {
        AlertDialog alertDialog = new AlertDialog.Builder(LayLaimatKhauActivity.this).create();
        alertDialog.setTitle("LỖI NHẬP");
        alertDialog.setMessage("Tài khoản của bạn không tồn tại hoặc email nhập sai");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private boolean checkFillOrNot() {
        return (!layoutQMK_edtNhapTaiKhoan.getText().toString().isEmpty() &&
                !layoutQMK_edtNhapEmail.getText().toString().isEmpty());
    }

    private void setEvent() {
        layoutQMK_btnLayLaiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFillOrNot()) {
                    sendEmailReset(account, email);

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(LayLaimatKhauActivity.this).create();
                    alertDialog.setTitle("LỖI NHẬP");
                    alertDialog.setMessage("Bạn cần nhập đầy đủ thông tin trước");
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


        layoutQMK_btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setControl() {
        layoutQMK_edtNhapTaiKhoan = findViewById(R.id.layoutQMK_edtNhapTaiKhoan);
        layoutQMK_edtNhapEmail = findViewById(R.id.layoutQMK_edtNhapEmail);
        layoutQMK_btnLayLaiMK = findViewById(R.id.layoutQMK_btnLayLaiMK);
        layoutQMK_btnQuayLai = findViewById(R.id.layoutQMK_btnQuayLai);
    }

}
