/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 */
public class HoaDon {
    private int ID;
    private  int ID_nhanvien;
    private  int ID_khachhang;
    private String  MaHoaDon;
    private float TongTien;
    private String DiaChi;
    private String NgayThanhToan;
    private String TrangThai;
    private String Mota;

    public HoaDon() {
    }

    public HoaDon(int ID, int ID_nhanvien, int ID_khachhang, String MaHoaDon, float TongTien, String DiaChi, String NgayThanhToan, String TrangThai, String Mota) {
        this.ID = ID;
        this.ID_nhanvien = ID_nhanvien;
        this.ID_khachhang = ID_khachhang;
        this.MaHoaDon = MaHoaDon;
        this.TongTien = TongTien;
        this.DiaChi = DiaChi;
        this.NgayThanhToan = NgayThanhToan;
        this.TrangThai = TrangThai;
        this.Mota = Mota;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_nhanvien() {
        return ID_nhanvien;
    }

    public void setID_nhanvien(int ID_nhanvien) {
        this.ID_nhanvien = ID_nhanvien;
    }

    public int getID_khachhang() {
        return ID_khachhang;
    }

    public void setID_khachhang(int ID_khachhang) {
        this.ID_khachhang = ID_khachhang;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(String NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String Mota) {
        this.Mota = Mota;
    }
     
    
    
}
