/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.Voucher;

import entity.Voucher.Voucher;
import util.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class VoucherService {
    
    public List<Voucher> getallVC() {
      try {
            String sql = "select * from Voucher ";
            try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
                try (ResultSet rs = ps.executeQuery();) {
                    List<Voucher> list = new ArrayList<>();
                    while (rs.next()) {
                        Voucher VC = new Voucher();
                VC.setId(rs.getInt(1));
                VC.setMaVoucher(rs.getString(2));
                VC.setGiaTri(rs.getDouble(3));
                VC.setNgayBatDau(rs.getString(4));
                VC.setNgayKetThuc(rs.getString(5));
                VC.setSoLuong(rs.getInt(6));
                VC.setMoTa(rs.getString(7));
                VC.setTrangThai(rs.getBoolean(8));
                   
                list.add(VC);
                    }
                    return list;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void addVoucher(Voucher voucher) {
        String sql = "INSERT INTO Voucher (maVoucher, giaTri, ngayBatDau, ngayKetThuc, soLuong, moTa, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, voucher.getMaVoucher());
            ps.setDouble(2, voucher.getGiaTri());
            ps.setString(3, voucher.getNgayBatDau());
            ps.setString(4, voucher.getNgayKetThuc());
            ps.setInt(5, voucher.getSoLuong());
            ps.setString(6, voucher.getMoTa());
            ps.setBoolean(7, voucher.isTrangThai());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateVoucher(Voucher voucher) {
        String sql = "UPDATE Voucher SET maVoucher = ?, giaTri = ?, ngayBatDau = ?, ngayKetThuc = ?, soLuong = ?, moTa = ?, trangThai = ? WHERE id = ?";
        try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, voucher.getMaVoucher());
            ps.setDouble(2, voucher.getGiaTri());
            ps.setString(3, voucher.getNgayBatDau());
            ps.setString(4, voucher.getNgayKetThuc());
            ps.setInt(5, voucher.getSoLuong());
            ps.setString(6, voucher.getMoTa());
            ps.setBoolean(7, voucher.isTrangThai());
            ps.setInt(8, voucher.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteVoucher(int id) {
        String sql = "DELETE FROM Voucher WHERE id = ?";
        try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
