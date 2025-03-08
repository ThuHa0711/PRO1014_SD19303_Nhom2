/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package entity.Voucher;

import java.util.Date;

/**
*
* @author Admin
*/
public class Voucher {
private int id;
private String maVoucher;
private double giaTri;
private String ngayBatDau;
private String ngayKetThuc;
private int soLuong;
private String moTa;
private boolean trangThai;

public Voucher() {
}

public Voucher(int id, String maVoucher, double giaTri, String ngayBatDau, String ngayKetThuc, int soLuong, String moTa, boolean trangThai) {
    this.id = id;
    this.maVoucher = maVoucher;
    this.giaTri = giaTri;
    this.ngayBatDau = ngayBatDau;
    this.ngayKetThuc = ngayKetThuc;
    this.soLuong = soLuong;
    this.moTa = moTa;
    this.trangThai = trangThai;
}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getMaVoucher() {
    return maVoucher;
}

public void setMaVoucher(String maVoucher) {
    this.maVoucher = maVoucher;
}

public double getGiaTri() {
    return giaTri;
}

public void setGiaTri(double giaTri) {
    this.giaTri = giaTri;
}

public String getNgayBatDau() {
    return ngayBatDau;
}

public void setNgayBatDau(String ngayBatDau) {
    this.ngayBatDau = ngayBatDau;
}

public String getNgayKetThuc() {
    return ngayKetThuc;
}

public void setNgayKetThuc(String ngayKetThuc) {
    this.ngayKetThuc = ngayKetThuc;
}

public int getSoLuong() {
    return soLuong;
}

public void setSoLuong(int soLuong) {
    this.soLuong = soLuong;
}

public String getMoTa() {
    return moTa;
}

public void setMoTa(String moTa) {
    this.moTa = moTa;
}

public boolean isTrangThai() {
    return trangThai;
}

public void setTrangThai(boolean trangThai) {
    this.trangThai = trangThai;
}

public Object[] toDatata3(){
    return new Object[]{ maVoucher,giaTri,ngayBatDau,ngayKetThuc, soLuong,moTa,trangThai?"Đang áp dụng":"Dừng áp dụng"};
}

}
