/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.BanHang;


/**
 *
 * @author ADMIN
 */
public class HoaDon {
    private  int ID;
    private int ID_NhanVien;
    private String maNV;
    private int ID_KhachHang;
    private String maKH;
    private String maHoaDon;
    private float tongTien;
    private String diaChi;
    private String ngayThanhToan;
    private boolean trangThai;
    private String moTa;
    private String taiKhoan;

    public HoaDon() {
    }

    public HoaDon(int ID, int ID_NhanVien, String maNV, int ID_KhachHang, String maKH, String maHoaDon, float tongTien, String diaChi, String ngayThanhToan, boolean trangThai, String moTa, String taiKhoan) {
        this.ID = ID;
        this.ID_NhanVien = ID_NhanVien;
        this.maNV = maNV;
        this.ID_KhachHang = ID_KhachHang;
        this.maKH = maKH;
        this.maHoaDon = maHoaDon;
        this.tongTien = tongTien;
        this.diaChi = diaChi;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
        this.moTa = moTa;
        this.taiKhoan = taiKhoan;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_NhanVien() {
        return ID_NhanVien;
    }

    public void setID_NhanVien(int ID_NhanVien) {
        this.ID_NhanVien = ID_NhanVien;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public int getID_KhachHang() {
        return ID_KhachHang;
    }

    public void setID_KhachHang(int ID_KhachHang) {
        this.ID_KhachHang = ID_KhachHang;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
 
    
}
