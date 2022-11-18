package com.example.nhasachonlinedidong2.item;

import java.io.Serializable;

public class ItemSanPham implements Serializable {
    private String maSanPham;
    private String tenSanPham;
    private String hinhSanPham;
    private String tacGia;
    private String xuatXu;
    private String theLoai;
    private String namSanXuat;
    private String nhaXuatBan;
    private String nhaPhanPhoi;
    private String donVi;
    private int giaSanPham = 0;
    private int soLuong = 1;
    private int khuyenMai = 0;
    private int trungBinhDanhGia = 0;
    private int soLuongBinhLuan = 0;

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public String getTacGia() {
        return tacGia;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public String getNamSanXuat() {
        return namSanXuat;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public String getNhaPhanPhoi() {
        return nhaPhanPhoi;
    }

    public String getDonVi() {
        return donVi;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public int getTrungBinhDanhGia() {
        return trungBinhDanhGia;
    }

    public int getSoLuongBinhLuan() {
        return soLuongBinhLuan;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public void setNamSanXuat(String namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public void setNhaPhanPhoi(String nhaPhanPhoi) {
        this.nhaPhanPhoi = nhaPhanPhoi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setTrungBinhDanhGia(int trungBinhDanhGia) {
        this.trungBinhDanhGia = trungBinhDanhGia;
    }

    public void setSoLuongBinhLuan(int soLuongBinhLuan) {
        this.soLuongBinhLuan = soLuongBinhLuan;
    }

    public ItemSanPham() {
    }

    public ItemSanPham(String maSanPham, String tenSanPham, String hinhSanPham, String tacGia, String xuatXu, String theLoai, String namSanXuat, String nhaXuatBan, String nhaPhanPhoi, String donVi, int giaSanPham, int soLuong, int khuyenMai, int trungBinhDanhGia, int soLuongBinhLuan) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhSanPham = hinhSanPham;
        this.tacGia = tacGia;
        this.xuatXu = xuatXu;
        this.theLoai = theLoai;
        this.namSanXuat = namSanXuat;
        this.nhaXuatBan = nhaXuatBan;
        this.nhaPhanPhoi = nhaPhanPhoi;
        this.donVi = donVi;
        this.giaSanPham = giaSanPham;
        this.soLuong = soLuong;
        this.khuyenMai = khuyenMai;
        this.trungBinhDanhGia = trungBinhDanhGia;
        this.soLuongBinhLuan = soLuongBinhLuan;
    }
}
