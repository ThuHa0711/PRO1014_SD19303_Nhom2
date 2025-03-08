/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.NhanVien;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class NhanVien {

    private Integer id;

    private String maNV;

    private String hoTen;

    private String sdt;

    private Date ngaySinh;

    private Boolean gioiTinh;

    private String diaChi;

    private String anh;

    private String trangThai;

    private String taiKhoan;

    private String matKhau;

    private ChucVu chucVu;

    public NhanVien() {
    }

    public NhanVien(Integer id, String maNV, String hoTen, String sdt, Date ngaySinh, Boolean gioiTinh, String diaChi, String anh, String trangThai, String taiKhoan, String matKhau, ChucVu chucVu) {
        this.id = id;
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.anh = anh;
        this.trangThai = trangThai;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "id=" + id + ", maNV=" + maNV + ", hoTen=" + hoTen + ", sdt=" + sdt + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", anh=" + anh + ", trangThai=" + trangThai + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", chucVu=" + chucVu + '}';
    }

}
