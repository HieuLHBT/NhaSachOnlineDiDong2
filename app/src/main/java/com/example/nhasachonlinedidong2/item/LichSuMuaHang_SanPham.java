package com.example.nhasachonlinedidong2.item;

public class LichSuMuaHang_SanPham {
    private String maSanPham;
    private String temSanPham;
    private String hinhSanPham;
    private int giaTien;
    private int khuyenMai;
    private int soLuongMua;
    private int tongTien;

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTemSanPham() {
        return temSanPham;
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

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setTemSanPham(String temSanPham) {
        this.temSanPham = temSanPham;
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

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public LichSuMuaHang_SanPham() {
    }

    public LichSuMuaHang_SanPham(String maSanPham, String temSanPham, String hinhSanPham, int giaTien, int khuyenMai, int soLuongMua) {
        this.maSanPham = maSanPham;
        this.temSanPham = temSanPham;
        this.hinhSanPham = hinhSanPham;
        this.giaTien = giaTien;
        this.khuyenMai = khuyenMai;
        this.soLuongMua = soLuongMua;
        this.tongTien = (giaTien - (giaTien * khuyenMai / 100)) * soLuongMua;
    }
}
