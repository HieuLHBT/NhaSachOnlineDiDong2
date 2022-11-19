package com.example.nhasachonlinedidong2.item;

import java.io.Serializable;
import java.util.ArrayList;

public class LichSuMuaHang_DonHang implements Serializable {
    private String maDonHang;
    private String ngayGiaoHang;
    private String trangThai;
    private int tongTien;
    private ArrayList<LichSuMuaHang_SanPham> lichSuMuaHang_sanPhams = new ArrayList<>();

    public String getMaDonHang() {
        return maDonHang;
    }

    public String getNgayGiaoHang() {
        return ngayGiaoHang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public int getTongTien() {
        return tongTien;
    }

    public ArrayList<LichSuMuaHang_SanPham> getLichSuMuaHang_sanPhams() {
        return lichSuMuaHang_sanPhams;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public void setNgayGiaoHang(String ngayGiaoHang) {
        this.ngayGiaoHang = ngayGiaoHang;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public void setLichSuMuaHang_sanPhams(ArrayList<LichSuMuaHang_SanPham> lichSuMuaHang_sanPhams) {
        this.lichSuMuaHang_sanPhams = lichSuMuaHang_sanPhams;
    }

    public LichSuMuaHang_DonHang() {
    }

    public LichSuMuaHang_DonHang(String maDonHang, String ngayGiaoHang, String trangThai, int tongTien, ArrayList<LichSuMuaHang_SanPham> lichSuMuaHang_sanPhams) {
        this.maDonHang = maDonHang;
        this.ngayGiaoHang = ngayGiaoHang;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.lichSuMuaHang_sanPhams = lichSuMuaHang_sanPhams;
    }
}
