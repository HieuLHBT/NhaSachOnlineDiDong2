package com.example.nhasachonlinedidong2.tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nhasachonlinedidong2.item.NhoDangNhap;

public class SharePreferences {

    // Hieu SharePreferences
    // Đơn hàng
    public void themMaDonHang(Context context, String maDonHang) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("maDonHang", maDonHang);
        editor.commit();
    }

    public String layMaDonHang(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        return sharedPreferences.getString("maDonHang", null);
    }

    public void xoaMaDonHang(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("maDonHang");
        editor.commit();
    }

    ////////////////////////////////
    public void dangNhap(Context context, String maNguoiDung) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("maNguoiDung", maNguoiDung);
        editor.commit();
    }

    public String layMa(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        return sharedPreferences.getString("maNguoiDung", null);
    }

    public void dangXuat(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("maNguoiDung");
        editor.commit();
    }

    public void luuDangNhap(String nguoiDung, String taikhoan, String matKhau, boolean checkBox, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nguoiDung", nguoiDung);
        editor.putString("taiKhoan", taikhoan);
        editor.putString("matKhau", matKhau);
        editor.putBoolean("checkBox", checkBox);
        editor.commit();
    }

    public NhoDangNhap layDangNhap(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        NhoDangNhap nhoDangNhap = new NhoDangNhap();
        nhoDangNhap.setNguoiDung(sharedPreferences.getString("nguoiDung", null));
        nhoDangNhap.setTaiKhoan(sharedPreferences.getString("taiKhoan", null));
        nhoDangNhap.setMatKhau(sharedPreferences.getString("matKhau", null));
        nhoDangNhap.setCheckBox(sharedPreferences.getBoolean("checkBox", false));
        return nhoDangNhap;
    }

    public void xoaDangNhap(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dulieu", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("nguoiDung");
        editor.remove("taiKhoan");
        editor.remove("matKhau");
        editor.remove("checkBox");
        editor.commit();
    }
}
