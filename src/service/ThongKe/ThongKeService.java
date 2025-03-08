package service.ThongKe;

import entity.ThongKe;

    import java.sql.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.DBcontext;

public class ThongKeService {
    public ArrayList<ThongKe> getAll() {
        ArrayList<ThongKe> danhSach = new ArrayList<>();
        String sql = " SELECT MaHoaDon,NgayThanhToan,TongTien FROM HoaDon";
        try (Connection con = DBcontext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String MaHD ; Date Ngay ; int TongTien;
                MaHD = rs .getString(1);
                Ngay = rs.getDate(2);
                TongTien = rs.getInt(3);
                ThongKe hd = new ThongKe(MaHD, Ngay, TongTien);
                danhSach.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }
    
    
    public static List<ThongKe> getTimKiem(Date ngayBatDau , Date NgayKetThuc){
        List<ThongKe> hoadon = new ArrayList<>();
        try {
            Connection con = DBcontext.getConnection();
            String Sql = " SELECT MaHoaDon,NgayThanhToan,TongTien FROM HoaDon where NgayThanhToan BETWEEN ? AND ?";
            
            PreparedStatement ps = con.prepareStatement(Sql);
            java.sql.Date sqlDate1 = new java.sql.Date(ngayBatDau.getTime());
            java.sql.Date sqlDate2 = new java.sql.Date(NgayKetThuc.getTime());
            ps.setDate(1, sqlDate1);
            ps.setDate(2, sqlDate2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {   
                  String MaHD ; Date Ngay ; int TongTien;
                MaHD = rs .getString(1);
                Ngay = rs.getDate(2);
                TongTien = rs.getInt(3);
                ThongKe hd = new  ThongKe(MaHD, Ngay, TongTien);
                hoadon.add(hd);
                
                       
                
            }
            return hoadon;
        } catch (Exception e) {
    }
        return null;
  }
}