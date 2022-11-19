package com.example.nhasachonlinedidong2.item;

public class QuanLyDonHang {
    private String tenSanPham;
    private String maSanPham;
    private String hinhSanPham;
    private String soLuongKho;
    private String giaTien;
    private String khuyenMai;

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public String getSoLuongKho() {
        return soLuongKho;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public String getKhuyenMai() {
        return khuyenMai;
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

    public void setSoLuongKho(String soLuongKho) {
        this.soLuongKho = soLuongKho;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public void setKhuyenMai(String khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public QuanLyDonHang() {
    }

    public QuanLyDonHang(String tenSanPham, String maSanPham, String hinhSanPham, String soLuongKho, String giaTien, String khuyenMai) {
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
        this.hinhSanPham = hinhSanPham;
        this.soLuongKho = soLuongKho;
        this.giaTien = giaTien;
        this.khuyenMai = khuyenMai;
    }
}
