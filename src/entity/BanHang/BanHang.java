/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.BanHang;

/**
 *
 * @author ADMIN
 */
public class BanHang {
    private int ID;
    private int ID_SanPhamChiTiet;
    private int ID_HoaDon;
    private int soLuong;
    private float tongTien;
    private float donGia;
    private String maSP;
    private String tenSP;
    private String maHD;

    public BanHang() {
    }

    public BanHang(int ID, int ID_SanPhamChiTiet, int ID_HoaDon, int soLuong, float tongTien, float donGia, String maSP, String tenSP, String maHD) {
        this.ID = ID;
        this.ID_SanPhamChiTiet = ID_SanPhamChiTiet;
        this.ID_HoaDon = ID_HoaDon;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.donGia = donGia;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maHD = maHD;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_SanPhamChiTiet() {
        return ID_SanPhamChiTiet;
    }

    public void setID_SanPhamChiTiet(int ID_SanPhamChiTiet) {
        this.ID_SanPhamChiTiet = ID_SanPhamChiTiet;
    }

    public int getID_HoaDon() {
        return ID_HoaDon;
    }

    public void setID_HoaDon(int ID_HoaDon) {
        this.ID_HoaDon = ID_HoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    
    
}
