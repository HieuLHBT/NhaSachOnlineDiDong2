package com.example.nhasachonlinedidong2.item;

import java.io.Serializable;
import java.util.ArrayList;

public class QuanLyDonHang_DonHang implements Serializable {
    private String trangThai;
    private String trangThaiDuyetNV;
    private String trangThaiGiaoHangKH;
    private String trangThaiGiaoHangNV;
    private String lyDoHuy;
    private String maDonHang;
    private String maNhanVien;
    private String tenNhanVien;
    private String ngayLapHoaDon;
    private String ngayDuKienGiao;
    private String maGiamGia;
    private String maKhachHang;
    private String tenKhachHang;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private int giamGia;
    private int phiVanChuyen;
    private int tongTien;
    private ArrayList<QuanLyDonHang_SanPham> quanLyDonHang_sanPhams = new ArrayList<>();

    public String getTrangThai() {
        return trangThai;
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

    public String getLyDoHuy() {
        return lyDoHuy;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    public String getNgayDuKienGiao() {
        return ngayDuKienGiao;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public int getPhiVanChuyen() {
        return phiVanChuyen;
    }

    public int getTongTien() {
        return tongTien;
    }

    public ArrayList<QuanLyDonHang_SanPham> getQuanLyDonHang_sanPhams() {
        return quanLyDonHang_sanPhams;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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

    public void setLyDoHuy(String lyDoHuy) {
        this.lyDoHuy = lyDoHuy;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public void setNgayLapHoaDon(String ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    public void setNgayDuKienGiao(String ngayDuKienGiao) {
        this.ngayDuKienGiao = ngayDuKienGiao;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public void setPhiVanChuyen(int phiVanChuyen) {
        this.phiVanChuyen = phiVanChuyen;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public void setQuanLyDonHang_sanPhams(ArrayList<QuanLyDonHang_SanPham> quanLyDonHang_sanPhams) {
        this.quanLyDonHang_sanPhams = quanLyDonHang_sanPhams;
    }

    public QuanLyDonHang_DonHang() {
    }

    public QuanLyDonHang_DonHang(String trangThai, String trangThaiDuyetNV, String trangThaiGiaoHangKH, String trangThaiGiaoHangNV, String lyDoHuy, String maDonHang, String maNhanVien, String tenNhanVien, String ngayLapHoaDon, String ngayDuKienGiao, String maGiamGia, String maKhachHang, String tenKhachHang, String diaChi, String soDienThoai, String email, int giamGia, int phiVanChuyen, int tongTien, ArrayList<QuanLyDonHang_SanPham> quanLyDonHang_sanPhams) {
        this.trangThai = trangThai;
        this.trangThaiDuyetNV = trangThaiDuyetNV;
        this.trangThaiGiaoHangKH = trangThaiGiaoHangKH;
        this.trangThaiGiaoHangNV = trangThaiGiaoHangNV;
        this.lyDoHuy = lyDoHuy;
        this.maDonHang = maDonHang;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.ngayDuKienGiao = ngayDuKienGiao;
        this.maGiamGia = maGiamGia;
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.giamGia = giamGia;
        this.phiVanChuyen = phiVanChuyen;
        this.tongTien = tongTien;
        this.quanLyDonHang_sanPhams = quanLyDonHang_sanPhams;
    }
}
