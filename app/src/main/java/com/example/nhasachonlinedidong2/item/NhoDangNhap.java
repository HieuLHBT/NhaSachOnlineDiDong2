package com.example.nhasachonlinedidong2.item;

public class NhoDangNhap {
    private String nguoiDung;
    private String taiKhoan;
    private String matKhau;
    private Boolean checkBox;

    public String getNguoiDung() {
        return nguoiDung;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public Boolean getCheckBox() {
        return checkBox;
    }

    public void setNguoiDung(String nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setCheckBox(Boolean checkBox) {
        this.checkBox = checkBox;
    }

    public NhoDangNhap() {
    }

    public NhoDangNhap(String nguoiDung, String taiKhoan, String matKhau, Boolean checkBox) {
        this.nguoiDung = nguoiDung;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.checkBox = checkBox;
    }
}
