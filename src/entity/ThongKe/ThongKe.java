/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class ThongKe {
    
     private String MaHD;
    private Date Ngaythanhtoan;
    private float Tien;

    public ThongKe() {
    }

    public ThongKe(String MaHD, Date Ngaythanhtoan, float Tien) {
        this.MaHD = MaHD;
        this.Ngaythanhtoan = Ngaythanhtoan;
        this.Tien = Tien;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Date getNgaythanhtoan() {
        return Ngaythanhtoan;
    }

    public void setNgaythanhtoan(Date Ngaythanhtoan) {
        this.Ngaythanhtoan = Ngaythanhtoan;
    }

    public float getTien() {
        return Tien;
    }

    public void setTien(float Tien) {
        this.Tien = Tien;
    }

   

    
}
