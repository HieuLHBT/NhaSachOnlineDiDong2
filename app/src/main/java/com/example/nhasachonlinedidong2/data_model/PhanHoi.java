package com.example.nhasachonlinedidong2.data_model;

public class PhanHoi {
    private String binhLuan;
    private String danhGia;
    private String maDonHang;
    private String maKhachHang;
    private String maNhanVien;
    private String maSanPham;
    private String ngayBinhLuan;
    private String traLoi;

    public String getBinhLuan() {
        return binhLuan;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getNgayBinhLuan() {
        return ngayBinhLuan;
    }

    public String getTraLoi() {
        return traLoi;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setNgayBinhLuan(String ngayBinhLuan) {
        this.ngayBinhLuan = ngayBinhLuan;
    }

    public void setTraLoi(String traLoi) {
        this.traLoi = traLoi;
    }

    public PhanHoi() {
    }

    public PhanHoi(String binhLuan, String danhGia, String maDonHang, String maKhachHang, String maNhanVien, String maSanPham, String ngayBinhLuan, String traLoi) {
        this.binhLuan = binhLuan;
        this.danhGia = danhGia;
        this.maDonHang = maDonHang;
        this.maKhachHang = maKhachHang;
        this.maNhanVien = maNhanVien;
        this.maSanPham = maSanPham;
        this.ngayBinhLuan = ngayBinhLuan;
        this.traLoi = traLoi;
    }
}
