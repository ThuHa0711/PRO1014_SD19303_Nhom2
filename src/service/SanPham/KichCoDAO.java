/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.SanPham;

import entity.SanPham.KichCo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBcontext;

/**
 *
 * @author ADMIN
 */
public class KichCoDAO {
    public ArrayList<KichCo> getAll(){
        ArrayList<KichCo> list = new ArrayList<>();
        try {
            String sql =" select * from KichCo";
            Connection cn = DBcontext.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                KichCo kc = new KichCo();
                kc.setIDKichCo(rs.getInt("ID"));
                kc.setTenKichCo(rs.getString("TenKichCo"));
                list.add(kc);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public int add(KichCo kc) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            String sql = "insert into KichCo(TenKichCo) values(?)";
            conn = DBcontext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, kc.getTenKichCo());
            if (stm.executeUpdate() > 0) {
                System.out.println("add Thanh cong");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                conn.close();
                stm.close();
            } catch (Exception e) {

            }
        }
        return -1;
    }
    public int del(String id) {

        Connection conn = null;
        PreparedStatement stm = null;
        try {
            String sql = "delete from KichCo where ID = ?";
            conn = DBcontext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
            if (stm.executeUpdate() > 0) {
                System.out.println("Xoa Thanh cong");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                conn.close();
                stm.close();
            } catch (Exception e) {

            }
        }
        return -1;
    }
     public int update(KichCo kc) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            String sql = "  update KichCo set TenKichCo = ?  where ID =? ";
            conn = DBcontext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, kc.getTenKichCo());
            stm.setInt(2, kc.getIDKichCo());
            if (stm.executeUpdate() > 0) {
                System.out.println("add Thanh cong");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                conn.close();
                stm.close();
            } catch (Exception e) {

            }
        }
        return -1;
    }
}
