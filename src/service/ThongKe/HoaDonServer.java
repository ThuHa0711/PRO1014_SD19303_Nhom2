/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.ThongKe;
import entity.HoaDon;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import util.DBcontext;
/**
 *
 */
public class HoaDonServer {
    public static List<HoaDon> getall() {
    List<HoaDon> listhd = new ArrayList<>();
    try {
        Connection con = DBcontext.getConnection();
        String sql = "SELECT hd.ID,hd.ID_NhanVien,hd.ID_KhachHang,hd.MaHoaDon,hd.TongTien, nv.DiaChi, hd.NgayThanhToan,hd.TrangThai,hd.MoTa  FROM HOADON hd\n" +
"INNER Join NhanVien nv ON nv.ID = hd.ID_NhanVien";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int ID = rs.getInt(1);
            int ID_nhanvien = rs.getInt(2);
            int ID_khachhang = rs.getInt(3);
            String MaHoaDon = rs.getString(4);
            float TongTien = rs.getFloat(5);
            String DiaChi = rs.getString(6);
            String NgayThanhToan = rs.getString(7);
            String TrangThai = rs.getString(8);
            String MoTa = rs.getString(9);
            HoaDon hd = new HoaDon(ID, ID_nhanvien, ID_khachhang, MaHoaDon, TongTien, DiaChi, NgayThanhToan, TrangThai, MoTa);
            listhd.add(hd);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return listhd;
}

    
}
