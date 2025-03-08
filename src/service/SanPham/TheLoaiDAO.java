
package service.SanPham;

import entity.SanPham.TheLoai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DBcontext;

/**
 *
 * @author ADMIN
 */
public class TheLoaiDAO {
    public ArrayList<TheLoai> getAll() {
        ArrayList<TheLoai> lst = new ArrayList<>();
        try {
            String sql = " select * from TheLoai";
            Connection cn = DBcontext.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TheLoai tl = new TheLoai();
                tl.setIDTheLoai(rs.getInt("ID"));
                tl.setTenTheloai(rs.getNString("TenTheLoai"));
                lst.add(tl);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lst;
    }

    public int add(TheLoai tl) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            String sql = "insert into TheLoai(TenTheLoai) values(?)";
            conn = DBcontext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, tl.getTenTheloai());
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
            String sql = "delete from TheLoai where ID = ?";
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

    public int update(TheLoai tl) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            String sql = "  update TheLoai set TenTheLoai = ?  where ID = ? ";
            conn = DBcontext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, tl.getTenTheloai());
            stm.setInt(2, tl.getIDTheLoai());

            if (stm.executeUpdate() > 0) {
                System.out.println("update Thanh cong");
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
