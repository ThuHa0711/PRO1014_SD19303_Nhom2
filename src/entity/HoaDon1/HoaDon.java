/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.HoaDon1;

/**
 *
 * @author ADMIN
 */
public class HoaDon {
    private int ID;
    private String maNV;
    private String maKH;
    private String maHoaDon;
    private float tongTien;
    private String diaChi;
    private String ngayThanhToan;
    private boolean trangThai;
    private String moTa;
    private String taiKhoan;
    private String MaVc;

    public HoaDon() {
    }

    public HoaDon(int ID, String maNV, String maKH, String maHoaDon, float tongTien, String diaChi, String ngayThanhToan, boolean trangThai, String moTa, String taiKhoan, String MaVc) {
        this.ID = ID;
        this.maNV = maNV;
        this.maKH = maKH;
        this.maHoaDon = maHoaDon;
        this.tongTien = tongTien;
        this.diaChi = diaChi;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
        this.moTa = moTa;
        this.taiKhoan = taiKhoan;
        this.MaVc = MaVc;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
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

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
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

    public String getMaVc() {
        return MaVc;
    }

    public void setMaVc(String MaVc) {
        this.MaVc = MaVc;
    }

   
   
 public Object[] toDatata(){
        return  new  Object[]{ maHoaDon,maNV,maKH,MaVc,tongTien,ngayThanhToan,trangThai? "Đã Thanh Toán":"Chưa Thanh Toán",moTa};
    }
    
}
