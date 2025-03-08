/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.SanPham;

/**
 *
 * @author ADMIN
 */
public class KichCo {
    private int IDKichCo;
    private String tenKichCo;

    public KichCo() {
    }

    public KichCo(int IDKichCo, String tenKichCo) {
        this.IDKichCo = IDKichCo;
        this.tenKichCo = tenKichCo;
    }

    public int getIDKichCo() {
        return IDKichCo;
    }

    public void setIDKichCo(int IDKichCo) {
        this.IDKichCo = IDKichCo;
    }

    public String getTenKichCo() {
        return tenKichCo;
    }

    public void setTenKichCo(String tenKichCo) {
        this.tenKichCo = tenKichCo;
    }
    
    
}
