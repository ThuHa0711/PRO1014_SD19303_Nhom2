/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.SanPham;

import entity.SanPham.SanPham;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DBcontext;

/**
 *
 * @author ADMIN
 */
public class SanPhamDAO {
   

    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> lst = new ArrayList<>();
        try {
            String sql = "select *,b.TenTheLoai as TheLoai, c.TenChatLieu as ChatLieu, d.TenMauSac as MauSac, e.TenKichCo as KichCo  from SanPhamChiTiet a \n"
                    + "inner join TheLoai b on  a.ID_TheLoai = b.ID \n"
                    + "inner join ChatLieu c on a.ID_ChatLieu = c.ID \n"
                    + "inner join MauSac d on a.ID_MauSac = d.ID \n"
                    + "inner join KichCo e on a.ID_KichCo = e.ID ";

            Connection cnn = DBcontext.getConnection();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSp(rs.getString("MaSP"));
                sp.setTenSp(rs.getString("TenSP"));
                sp.setTheLoai(rs.getString("TheLoai"));
                sp.setChatLieu(rs.getString("ChatLieu"));
                sp.setMauSac(rs.getString("MauSac"));
                sp.setKichCo(rs.getString("KichCo"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setDonGia(rs.getFloat("DonGia"));
                sp.setAnh(rs.getString("Anh"));
                sp.setMoTa(rs.getString("MoTa"));
                
                sp.setIdTheLoai(rs.getInt("ID"));
                sp.setIdChatLieu(rs.getInt("ID"));
                sp.setIdMauSac(rs.getInt("ID"));
                sp.setIdKichCo(rs.getInt("ID"));
                lst.add(sp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lst;
    }

    public int add(SanPham sp) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet resultSet = null;
        try {
            
            conn = DBcontext.getConnection();
            // Tạo mã sản phẩm tự động
            String countQuery = "SELECT COUNT(*) AS total FROM SanPhamChiTiet";
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(countQuery);
            resultSet.next();
            int totalProducts = resultSet.getInt("total");
            resultSet.close();
            statement.close();
            String newMasp = String.format("SP%04d", totalProducts + 1);

            String sql = "INSERT INTO SanPhamChiTiet(ID_TheLoai, ID_ChatLieu, ID_MauSac, ID_KichCo, MaSP,TenSP,Anh,MoTa,DonGia,SoLuong) VALUES (?,?,?,?,?,?,?,?,?,?)";
            
            stm = conn.prepareStatement(sql);
            
            stm.setInt(1, sp.getIdTheLoai());
            stm.setInt(2, sp.getIdChatLieu());
            stm.setInt(3, sp.getIdMauSac());
            stm.setInt(4, sp.getIdKichCo());
            stm.setString(5, newMasp);
            stm.setString(6, sp.getTenSp());
            stm.setString(7, sp.getAnh());
            stm.setString(8, sp.getMoTa());
            stm.setFloat(9, sp.getDonGia());
            stm.setInt(10, sp.getSoLuong());
            
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

    public int del(String masp) {

        Connection conn = null;
        PreparedStatement stm = null;
        try {
            String sql = "delete from SanPhamChiTiet where MaSP = ?";
            conn = DBcontext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, masp);
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

    public int update(SanPham sp) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            String sql = " update SanPhamChiTiet set TenSP=? ,Anh =?,MoTa = ?,DonGia = ?, SoLuong =?, ID_TheLoai = ?, ID_ChatLieu = ?, ID_MauSac = ?, ID_KichCo = ?\n"
                    + " where MaSP = ?";
            conn = DBcontext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, sp.getTenSp());
            stm.setString(2, sp.getAnh());
            stm.setString(3, sp.getMoTa());
            stm.setFloat(4, sp.getDonGia());
            stm.setInt(5, sp.getSoLuong());
            stm.setInt(6, sp.getIdTheLoai());
            stm.setInt(7, sp.getIdChatLieu());
            stm.setInt(8, sp.getIdMauSac());
            stm.setInt(9, sp.getIdKichCo());
            stm.setString(10, sp.getMaSp());
            if (stm.executeUpdate() > 0) {
                System.out.println("Sửa Thành Công");
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

    public ArrayList<SanPham> getAlltop3() {
        ArrayList<SanPham> lst3 = new ArrayList<>();
       try {
           
            String sql = "select Top 3 *,b.TenTheLoai as TheLoai, c.TenChatLieu as ChatLieu, d.TenMauSac as MauSac, e.TenKichCo as KichCo  from SanPhamChiTiet a \n"
                    + "inner join TheLoai b on  a.ID_TheLoai = b.ID \n"
                    + "inner join ChatLieu c on a.ID_ChatLieu = c.ID \n"
                    + "inner join MauSac d on a.ID_MauSac = d.ID \n"
                    + "inner join KichCo e on a.ID_KichCo = e.ID \n"
                    +"ORDER BY a.SoLuong DESC";
            
                    
            Connection cnn = DBcontext.getConnection();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSp(rs.getString("MaSP"));
                sp.setTenSp(rs.getString("TenSP"));
                sp.setTheLoai(rs.getString("TheLoai"));
                sp.setChatLieu(rs.getString("ChatLieu"));
                sp.setMauSac(rs.getString("MauSac"));
                sp.setKichCo(rs.getString("KichCo"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setDonGia(rs.getFloat("DonGia"));
                sp.setAnh(rs.getString("Anh"));
                sp.setMoTa(rs.getString("MoTa"));
                
                sp.setIdTheLoai(rs.getInt("ID"));
                sp.setIdChatLieu(rs.getInt("ID"));
                sp.setIdMauSac(rs.getInt("ID"));
                sp.setIdKichCo(rs.getInt("ID"));
                lst3.add(sp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lst3;
    }
}
