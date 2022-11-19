package com.example.nhasachonlinedidong2.item;

public class DanhGiaSanPham_SanPham {
    private String maSanPham;
    private String tenSanPham;
    private String hinhSanPham;

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public DanhGiaSanPham_SanPham() {
    }

    public DanhGiaSanPham_SanPham(String maSanPham, String tenSanPham, String hinhSanPham) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhSanPham = hinhSanPham;
    }
}
