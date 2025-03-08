/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.SanPham;

/**
 *
 * @author ADMIN
 */
public class MauSac {
    private int IDMauSac;
    private String tenMauSac;

    public MauSac() {
    }

    public MauSac(int IDMauSac, String tenMauSac) {
        this.IDMauSac = IDMauSac;
        this.tenMauSac = tenMauSac;
    }

    public int getIDMauSac() {
        return IDMauSac;
    }

    public void setIDMauSac(int IDMauSac) {
        this.IDMauSac = IDMauSac;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }
    
    
}
