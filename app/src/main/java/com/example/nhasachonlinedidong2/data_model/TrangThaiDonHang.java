package com.example.nhasachonlinedidong2.data_model;

public class TrangThaiDonHang {
    private String maDonHang;
    private String kieuThanhToan;
    private String lyDoHuy;
    private String trangThaiDon;
    private String trangThaiDuyetNV;
    private String trangThaiGiaoHangKH;
    private String trangThaiGiaoHangNV;

    public String getMaDonHang() {
        return maDonHang;
    }

    public String getKieuThanhToan() {
        return kieuThanhToan;
    }

    public String getLyDoHuy() {
        return lyDoHuy;
    }

    public String getTrangThaiDon() {
        return trangThaiDon;
    }

    public String getTrangThaiDuyetNV() {
        return trangThaiDuyetNV;
    }

    public String getTrangThaiGiaoHangKH() {
        return trangThaiGiaoHangKH;
    }

    public String getTrangThaiGiaoHangNV() {
        return trangThaiGiaoHangNV;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public void setKieuThanhToan(String kieuThanhToan) {
        this.kieuThanhToan = kieuThanhToan;
    }

    public void setLyDoHuy(String lyDoHuy) {
        this.lyDoHuy = lyDoHuy;
    }

    public void setTrangThaiDon(String trangThaiDon) {
        this.trangThaiDon = trangThaiDon;
    }

    public void setTrangThaiDuyetNV(String trangThaiDuyetNV) {
        this.trangThaiDuyetNV = trangThaiDuyetNV;
    }

    public void setTrangThaiGiaoHangKH(String trangThaiGiaoHangKH) {
        this.trangThaiGiaoHangKH = trangThaiGiaoHangKH;
    }

    public void setTrangThaiGiaoHangNV(String trangThaiGiaoHangNV) {
        this.trangThaiGiaoHangNV = trangThaiGiaoHangNV;
    }

    public TrangThaiDonHang() {
    }

    public TrangThaiDonHang(String maDonHang, String kieuThanhToan, String lyDoHuy, String trangThaiDon, String trangThaiDuyetNV, String trangThaiGiaoHangKH, String trangThaiGiaoHangNV) {
        this.maDonHang = maDonHang;
        this.kieuThanhToan = kieuThanhToan;
        this.lyDoHuy = lyDoHuy;
        this.trangThaiDon = trangThaiDon;
        this.trangThaiDuyetNV = trangThaiDuyetNV;
        this.trangThaiGiaoHangKH = trangThaiGiaoHangKH;
        this.trangThaiGiaoHangNV = trangThaiGiaoHangNV;
    }
}
