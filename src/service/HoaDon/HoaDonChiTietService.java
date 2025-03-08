/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.HoaDon;

import entity.HoaDon1.HoaDonChiTiet;
import util.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietService {

    public List<HoaDonChiTiet> getallHDCT() {
        try {
            String sql = "SELECT hdct.ID, spct.TenSP, hd.MaHoadon, hdct.Soluong, hdct.DonGia\n"
                    + "FROM HoaDonChiTiet hdct\n"
                    + "JOIN SanPhamChiTiet spct ON spct.ID = hdct.ID_SanPhamChiTiet\n"
                    + "JOIN HoaDon hd ON hd.ID = hdct.ID_HoaDon";
            try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
                try (ResultSet rs = ps.executeQuery();) {
                    List<HoaDonChiTiet> list = new ArrayList<>();
                    while (rs.next()) {
                        HoaDonChiTiet HDCT = new HoaDonChiTiet();
                        HDCT.setID(rs.getInt(1));
                        HDCT.setTenSP(rs.getString(2));
                        HDCT.setMaHoaDon(rs.getString(3));
                        HDCT.setSoLuong(rs.getInt(4));
                        HDCT.setTongTien(rs.getFloat(5));

                        list.add(HDCT);
                    }
                    return list;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<HoaDonChiTiet> showHDCT(Integer ID) {
        List<HoaDonChiTiet> lis = new ArrayList<>();
        String sql = "SELECT hdct.ID, spct.TenSP, HD.MaHoaDon, hdct.SoLuong, hdct.DonGia " +
                     "FROM HoaDonChiTiet hdct " +
                     "JOIN SanPhamChiTiet spct ON spct.ID = hdct.ID_SanPhamChiTiet " +
                     "JOIN HoaDon HD ON HD.ID = hdct.ID_HoaDon " +
                     "WHERE hdct.ID_HoaDon = ?";


        try {
            Connection connect = DBcontext.getConnection();
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setObject(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet HDCT = new HoaDonChiTiet();
                    HDCT.setID(rs.getInt("ID"));
                    HDCT.setTenSP(rs.getString("TenSP"));
                    HDCT.setMaHoaDon(rs.getString("MaHoaDon"));
                    HDCT.setSoLuong(rs.getInt("SoLuong"));
                    HDCT.setTongTien(rs.getFloat("DonGia"));

                lis.add(HDCT);

            }

            return lis;
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }

    }

    public int delectHDCT(int ID) {
        String sql = "DELETE FROM HoaDonChiTiet where ID=?";
        Connection connect = DBcontext.getConnection();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, ID);
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonChiTietService.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

}
