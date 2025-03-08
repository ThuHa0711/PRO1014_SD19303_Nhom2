/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.NhanVien;

/**
 *
 * @author DELL
 */
public class ChucVu {

    private Integer id;

    private String tenCV;

    public ChucVu() {
    }

    public ChucVu(Integer id, String tenCV) {
        this.id = id;
        this.tenCV = tenCV;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    @Override
    public String toString() {
        return "ChucVu{" + "id=" + id + ", tenCV=" + tenCV + '}';
    }

}
