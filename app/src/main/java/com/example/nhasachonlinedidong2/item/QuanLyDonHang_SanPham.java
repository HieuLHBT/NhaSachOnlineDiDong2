package com.example.nhasachonlinedidong2.item;

public class QuanLyDonHang_SanPham {
    private String tenSanPham;
    private String maSanPham;
    private String hinhSanPham;
    private int soLuongKho;
    private int soLuongBan;
    private int giaTien;
    private int khuyenMai;
    private int tongTien;

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public void setSoLuongKho(int soLuongKho) {
        this.soLuongKho = soLuongKho;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public int getSoLuongKho() {
        return soLuongKho;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public int getTongTien() {
        return tongTien;
    }

    public QuanLyDonHang_SanPham(String tenSanPham, String maSanPham, String hinhSanPham, int soLuongKho, int soLuongBan, int giaTien, int khuyenMai) {
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
        this.hinhSanPham = hinhSanPham;
        this.soLuongKho = soLuongKho;
        this.soLuongBan = soLuongBan;
        this.khuyenMai = khuyenMai;
        this.giaTien = (giaTien - (giaTien * khuyenMai / 100));
        this.tongTien = (giaTien - (giaTien * khuyenMai / 100)) * soLuongBan;
    }
}
