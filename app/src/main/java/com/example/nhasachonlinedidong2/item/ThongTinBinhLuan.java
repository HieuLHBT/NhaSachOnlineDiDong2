package com.example.nhasachonlinedidong2.item;

public class ThongTinBinhLuan {
    private int danhGia;
    private String maKhachHang;
    private String tenKhachHang;
    private String binhLuan;
    private String ngayBinhLuan;
    private String maNhanVien;
    private String tenNhanVien;
    private String traLoi;

    public ThongTinBinhLuan() {
    }

    public ThongTinBinhLuan(int danhGia, String maKhachHang, String tenKhachHang, String binhLuan, String ngayBinhLuan, String maNhanVien, String tenNhanVien, String traLoi) {
        this.danhGia = danhGia;
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.binhLuan = binhLuan;
        this.ngayBinhLuan = ngayBinhLuan;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.traLoi = traLoi;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public void setNgayBinhLuan(String ngayBinhLuan) {
        this.ngayBinhLuan = ngayBinhLuan;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setTraLoi(String traLoi) {
        this.traLoi = traLoi;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public String getNgayBinhLuan() {
        return ngayBinhLuan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getTraLoi() {
        return traLoi;
    }
}
