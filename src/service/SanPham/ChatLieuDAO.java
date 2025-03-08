
package service.SanPham;

import entity.SanPham.ChatLieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DBcontext;

/**
 *
 * @author ADMIN
 */
public class ChatLieuDAO {
    public ArrayList<ChatLieu> getAll(){
        ArrayList<ChatLieu> list = new ArrayList<>();
        try {
            String sql = "select * from ChatLieu";
            Connection cn = DBcontext.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                ChatLieu cl = new ChatLieu();
                cl.setIDChatLieu(rs.getInt("ID"));
                cl.setTenChatLieu(rs.getNString("TenChatLieu"));
                list.add(cl);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public int add(ChatLieu cl) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            String sql = "insert into ChatLieu(TenChatLieu) values(?)";
            conn = DBcontext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, cl.getTenChatLieu());
            if (stm.executeUpdate() > 0) {
                System.out.println("Thêm Thành Công");
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
            String sql = "delete from ChatLieu where ID = ?";
            conn = DBcontext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
            if (stm.executeUpdate() > 0) {
                System.out.println("Xóa Thành Công");
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
     public int update(ChatLieu cl) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            String sql = " update ChatLieu set TenChatLieu = ?  where ID =? ";
            conn = DBcontext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, cl.getTenChatLieu());
            stm.setInt(2, cl.getIDChatLieu());
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
