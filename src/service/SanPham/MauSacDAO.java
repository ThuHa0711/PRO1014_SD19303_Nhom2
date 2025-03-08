/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.SanPham;

import entity.SanPham.MauSac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DBcontext;

/**
 *
 * @author ADMIN
 */
public class MauSacDAO {
    public ArrayList<MauSac> getAll() {
        ArrayList<MauSac> lst = new ArrayList<>();
        try {
            String sql = " select * from MauSac";
            Connection cn = DBcontext.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setIDMauSac(rs.getInt("ID"));
                ms.setTenMauSac(rs.getNString("TenMauSac"));
                lst.add(ms);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lst;
    }

    public int add(MauSac ms) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            String sql = "insert into MauSac(TenMauSac) values(?)";
            conn = DBcontext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, ms.getTenMauSac());
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
            String sql = "delete from MauSac where ID = ?";
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

    public int update(MauSac ms) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            String sql = " update MauSac set TenMauSac = ?  where ID =? ";
            conn = DBcontext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, ms.getTenMauSac());
            stm.setInt(2, ms.getIDMauSac());

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
