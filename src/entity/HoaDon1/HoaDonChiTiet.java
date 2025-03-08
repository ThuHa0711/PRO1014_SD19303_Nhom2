/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.HoaDon1;



public class HoaDonChiTiet {

    private int ID;
    private int ID_SanPhamChiTiet;
    private String TenSP;
    private int ID_HoaDon;
    private  String maHoaDon;
    private int soLuong;
    private float tongTien;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int ID, int ID_SanPhamChiTiet, String TenSP, int ID_HoaDon, String maHoaDon, int soLuong, float tongTien) {
        this.ID = ID;
        this.ID_SanPhamChiTiet = ID_SanPhamChiTiet;
        this.TenSP = TenSP;
        this.ID_HoaDon = ID_HoaDon;
        this.maHoaDon = maHoaDon;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
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

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public int getID_HoaDon() {
        return ID_HoaDon;
    }

    public void setID_HoaDon(int ID_HoaDon) {
        this.ID_HoaDon = ID_HoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
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

    
    
public Object[] toDatata(){
        return new Object[]{ ID,TenSP,maHoaDon,soLuong,tongTien};
    }
    

    
}
