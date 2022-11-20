package com.example.nhasachonlinedidong2.item;

import java.io.Serializable;

public class QuanLySanPham_SanPham implements Serializable {
    private String maSanPham;
    private String tenSanPham;
    private String hinhSanPham;
    private String xuatXu;
    private String nhaPhanPhoi;
    private String donVi;
    private String tacGia;
    private String theLoai;
    private String ngayXuatBan;
    private String nhaXuatBan;
    private int giaSanPham = 0;
    private int soLuongKho = 0;
    private int khuyenMai = 0;

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public String getNhaPhanPhoi() {
        return nhaPhanPhoi;
    }

    public String getDonVi() {
        return donVi;
    }

    public String getTacGia() {
        return tacGia;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public String getNgayXuatBan() {
        return ngayXuatBan;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public int getSoLuongKho() {
        return soLuongKho;
    }

    public int getKhuyenMai() {
        return khuyenMai;
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

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public void setNhaPhanPhoi(String nhaPhanPhoi) {
        this.nhaPhanPhoi = nhaPhanPhoi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public void setNgayXuatBan(String ngayXuatBan) {
        this.ngayXuatBan = ngayXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public void setSoLuongKho(int soLuongKho) {
        this.soLuongKho = soLuongKho;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public QuanLySanPham_SanPham() {
    }

    public QuanLySanPham_SanPham(String maSanPham, String tenSanPham, String hinhSanPham, String xuatXu, String nhaPhanPhoi, String donVi, String tacGia, String theLoai, String ngayXuatBan, String nhaXuatBan, int giaSanPham, int soLuongKho, int khuyenMai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhSanPham = hinhSanPham;
        this.xuatXu = xuatXu;
        this.nhaPhanPhoi = nhaPhanPhoi;
        this.donVi = donVi;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.ngayXuatBan = ngayXuatBan;
        this.nhaXuatBan = nhaXuatBan;
        this.giaSanPham = giaSanPham;
        this.soLuongKho = soLuongKho;
        this.khuyenMai = khuyenMai;
    }
}
