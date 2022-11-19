package com.example.nhasachonlinedidong2.item;

public class DanhMucSanPham {
    private String tenSanPham;
    private String maSanPham;
    private String hinhSanPham;
    private int giaTien;
    private int khuyenMai;
    private int soLuongKho;

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public int getSoLuongKho() {
        return soLuongKho;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setSoLuongKho(int soLuongKho) {
        this.soLuongKho = soLuongKho;
    }

    public DanhMucSanPham() {
    }

    public DanhMucSanPham(String tenSanPham, String maSanPham, String hinhSanPham, int giaTien, int khuyenMai, int soLuongKho) {
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
        this.hinhSanPham = hinhSanPham;
        this.giaTien = giaTien;
        this.khuyenMai = khuyenMai;
        this.soLuongKho = soLuongKho;
    }
}
