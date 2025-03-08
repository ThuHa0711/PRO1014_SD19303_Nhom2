/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.KhachHang;

import java.util.Date;

/**
 *
 * @author my account
 */
public class KhachHang {

    private Integer idKH;
    private String maKhachHang;
    private String hoTen;
    private String SDT;
    private Date ngaySinh;
    private boolean gioiTinh;
    private boolean trangThai;

    private String DiaChi;

    public KhachHang() {
    }

    public KhachHang(Integer idKH, String maKhachHang, String hoTen, String SDT, Date ngaySinh, boolean gioiTinh, boolean trangThai, String DiaChi) {
        this.idKH = idKH;
        this.maKhachHang = maKhachHang;
        this.hoTen = hoTen;
        this.SDT = SDT;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
        this.DiaChi = DiaChi;
    }

    

    public Integer getIdKH() {
        return idKH;
    }

    public void setIdKH(Integer idKH) {
        this.idKH = idKH;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "idKH=" + idKH + ", maKhachHang=" + maKhachHang + ", hoTen=" + hoTen + ", SDT=" + SDT + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", DiaChi=" + DiaChi + '}';
    }

}
