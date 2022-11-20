package com.example.nhasachonlinedidong2.data_model;

public class NhapKho {
    private String maNhapKho;
    private String maSanPham;
    private String ngayNhapKho;
    private String nhaCungCap;
    private String soLuongNhap;

    public String getMaNhapKho() {
        return maNhapKho;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getNgayNhapKho() {
        return ngayNhapKho;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public String getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setMaNhapKho(String maNhapKho) {
        this.maNhapKho = maNhapKho;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setNgayNhapKho(String ngayNhapKho) {
        this.ngayNhapKho = ngayNhapKho;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public void setSoLuongNhap(String soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public NhapKho() {
    }

    public NhapKho(String maNhapKho, String maSanPham, String ngayNhapKho, String nhaCungCap, String soLuongNhap) {
        this.maNhapKho = maNhapKho;
        this.maSanPham = maSanPham;
        this.ngayNhapKho = ngayNhapKho;
        this.nhaCungCap = nhaCungCap;
        this.soLuongNhap = soLuongNhap;
    }
}
