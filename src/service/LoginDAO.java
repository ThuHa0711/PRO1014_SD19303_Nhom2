/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.KhachHang.Login;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBcontext;

/**
 *
 * @author ADMIN
 */
public class LoginDAO {

    Connection con = DBcontext.getConnection();

    public List<Login> getAll() {
        List<Login> listacc = new ArrayList<>();
        String sql = "select TaiKhoan,MatKhau,HoTen,ID_ChucVu,TenChucVu from NhanVien join ChucVu on NhanVien.ID_ChucVu=ChucVu.ID";
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Login acc = new Login();
                acc.setTaiKhoan(rs.getString("TaiKhoan"));
                acc.setMatKhau(rs.getString("MatKhau"));
                acc.setHoTen(rs.getString("HoTen"));
                acc.setIdChucVu(rs.getInt("ID_ChucVu"));
                acc.setTenChucVu(rs.getString("TenChucVu"));

                listacc.add(acc);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return listacc;
    }
}
