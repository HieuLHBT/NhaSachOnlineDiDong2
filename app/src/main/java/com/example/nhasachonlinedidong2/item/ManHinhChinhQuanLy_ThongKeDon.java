package com.example.nhasachonlinedidong2.item;

public class ManHinhChinhQuanLy_ThongKeDon {
    private Integer donThanhCong = 0;
    private Integer donDaHuy = 0;
    private Integer donDangXyLy = 0;

    public Integer getDonThanhCong() {
        return donThanhCong;
    }

    public Integer getDonDaHuy() {
        return donDaHuy;
    }

    public Integer getDonDangXyLy() {
        return donDangXyLy;
    }

    public void setDonThanhCong(Integer donThanhCong) {
        this.donThanhCong = donThanhCong;
    }

    public void setDonDaHuy(Integer donDaHuy) {
        this.donDaHuy = donDaHuy;
    }

    public void setDonDangXyLy(Integer donDangXyLy) {
        this.donDangXyLy = donDangXyLy;
    }

    public ManHinhChinhQuanLy_ThongKeDon() {
    }

    public ManHinhChinhQuanLy_ThongKeDon(Integer donThanhCong, Integer donDaHuy, Integer donDangXyLy) {
        this.donThanhCong = donThanhCong;
        this.donDaHuy = donDaHuy;
        this.donDangXyLy = donDangXyLy;
    }
}
