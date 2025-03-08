/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.HoaDon;

import entity.HoaDon1.HoaDon;
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
public class HoaDonService {

    public List<HoaDon> getallHD() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "select  hd.ID,hd.MaHoaDon,nv.MaNhanVien,kh.MaKhachHang,vc.MaVoucher,TongTien,NgayThanhToan,hd.TrangThai,hd.MoTa from HoaDon hd\n"
                + "join KhachHang kh on kh.ID= hd.ID_KhachHang \n"
                + "join NhanVien nv on nv.ID= hd.ID_NhanVien \n"
                + "join Voucher vc on vc.ID=hd.ID_Voucher ";
        try {
            Connection connect = DBcontext.getConnection();
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon HD = new HoaDon();
                HD.setID(rs.getInt(1));
                HD.setMaHoaDon(rs.getString(2));
                HD.setMaNV(rs.getString(3));
                HD.setMaKH(rs.getString(4));
                HD.setMaVc(rs.getString(5));
                HD.setTongTien(rs.getFloat(6));

                HD.setNgayThanhToan(rs.getString(7));
                HD.setTrangThai(rs.getBoolean(8));
                HD.setMoTa(rs.getString(9));
                list.add(HD);

            }
            return list;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
//
//    public int ThnhToanHoaDon(HoaDon HD) {
//
//        String sql = "update HoaDon set MaHoaDon=?,TongTien=?,DiaChi=?,NgayThanhToan=?,TrangThai=?,MoTa=? where ID=?";
//        try {
//            Connection connect = DBcontext.getConnection();
//            PreparedStatement ps = connect.prepareStatement(sql);
//            ps.setString(1, HD.getMaHoaDon());
//            ps.setFloat(2, HD.getTongTien());
//            ps.setString(3, HD.getDiaChi());
//            ps.setString(4, HD.getNgayThanhToan());
//            ps.setBoolean(5, HD.isTrangThai());
//            ps.setString(6, HD.getMoTa());
//            ps.setInt(7, HD.getID());
//
//            int x = ps.executeUpdate();
//            return x;
//        } catch (Exception e) {
//            return 0;
//        }
//    }

//public int HuyHoaDon(HoaDon1 HD){
//        String sql = "DELETE FROM HoaDon WHERE ID = ? AND ID_NhanVien = ? AND ID_KhachHang = ?";
//    try {
//        Connection connect = DBConnect.getConnection();
//        PreparedStatement ps = connect.prepareStatement(sql);
//        
//        ps.setInt(1, HD.getID());
//        ps.setInt(2, HD.getID_NhanVien());
//        ps.setInt(3, HD.getID_KhachHang());
//        
//        int x = ps.executeUpdate();
//        return x;
//    } catch (Exception e) {
//        e.printStackTrace(); // In chi tiết lỗi để giúp gỡ lỗi
//        return 0;
//    }
//    }
//public int themHoaDon(HoaDon1 HD){
//        String sql="insert into HoaDon(ID_NhanVien,ID_KhachHang,MaHoaDon,TongTien,DiaChi,NgayThanhToan,TrangThai,MoTa) values(?,?,?,?,?,?,?,?)";
//        try {
//            Connection connect=DBConnect.getConnection();
//            PreparedStatement ps=connect.prepareStatement(sql);
//            ps.setInt(1, HD.getID_NhanVien());
//            ps.setInt(2, HD.getID_KhachHang());
//            ps.setString(3, HD.getMaHoaDon());
//            ps.setFloat(4, HD.getTongTien());
//            ps.setString(5, HD.getDiaChi());
//            ps.setString(6, HD.getNgayThanhToan());
//            ps.setBoolean(7,HD.isTrangThai());
//            ps.setString(8, HD.getMoTa());
//           
//            
//            int x=ps.executeUpdate();
//            return x;
//            
//        } catch (Exception e) {
//            System.out.println(e);
//            return 0;
//        }
}
