package com.example.nhasachonlinedidong2.data_model;

public class DonHang {
    private String maDonHang;
    private String diaChiGiao;
    private String maGiamGia;
    private String maKhachHang;
    private String maNhanVien;
    private String thoiGianGiao;
    private String thoiGianLap;
    private String phiVanChuyen;

    public String getMaDonHang() {
        return maDonHang;
    }

    public String getDiaChiGiao() {
        return diaChiGiao;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getThoiGianGiao() {
        return thoiGianGiao;
    }

    public String getThoiGianLap() {
        return thoiGianLap;
    }

    public String getPhiVanChuyen() {
        return phiVanChuyen;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public void setDiaChiGiao(String diaChiGiao) {
        this.diaChiGiao = diaChiGiao;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setThoiGianGiao(String thoiGianGiao) {
        this.thoiGianGiao = thoiGianGiao;
    }

    public void setThoiGianLap(String thoiGianLap) {
        this.thoiGianLap = thoiGianLap;
    }

    public void setPhiVanChuyen(String phiVanChuyen) {
        this.phiVanChuyen = phiVanChuyen;
    }

    public DonHang() {
    }

    public DonHang(String maDonHang, String diaChiGiao, String maGiamGia, String maKhachHang, String maNhanVien, String thoiGianGiao, String thoiGianLap, String phiVanChuyen) {
        this.maDonHang = maDonHang;
        this.diaChiGiao = diaChiGiao;
        this.maGiamGia = maGiamGia;
        this.maKhachHang = maKhachHang;
        this.maNhanVien = maNhanVien;
        this.thoiGianGiao = thoiGianGiao;
        this.thoiGianLap = thoiGianLap;
        this.phiVanChuyen = phiVanChuyen;
    }
}
