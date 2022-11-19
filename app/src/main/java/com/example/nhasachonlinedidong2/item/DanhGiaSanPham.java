package com.example.nhasachonlinedidong2.item;

public class DanhGiaSanPham {
    private String maSanPham;
    private String tenSanPham;
    private String hinhSanPham;
    private String binhLuan;
    private Integer danhGia = 0;
    private Boolean check = false;

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public Integer getDanhGia() {
        return danhGia;
    }

    public Boolean getCheck() {
        return check;
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

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public void setDanhGia(Integer danhGia) {
        this.danhGia = danhGia;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public DanhGiaSanPham() {
    }

    public DanhGiaSanPham(String maSanPham, String tenSanPham, String hinhSanPham, String binhLuan, Integer danhGia) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.hinhSanPham = hinhSanPham;
        this.binhLuan = binhLuan;
        this.danhGia = danhGia;
    }
}
