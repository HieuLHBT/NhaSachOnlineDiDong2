package com.example.nhasachonlinedidong2.item;

import java.util.ArrayList;

public class LichSuMuaHang_DonHang {
    private String ngayGiaoHang;
    private String trangThai;
    private int tongTien;
    private ArrayList<LichSuMuaHang_SanPham> lichSuMuaHang_sanPhams = new ArrayList<>();

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

    public LichSuMuaHang_DonHang(String ngayGiaoHang, String trangThai, int tongTien, ArrayList<LichSuMuaHang_SanPham> lichSuMuaHang_sanPhams) {
        this.ngayGiaoHang = ngayGiaoHang;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.lichSuMuaHang_sanPhams = lichSuMuaHang_sanPhams;
    }
}
