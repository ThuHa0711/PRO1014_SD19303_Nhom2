/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.KhachHang;

import util.DBcontext;
import entity.KhachHang.KhachHang;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author my account
 */
public class RepositoryKhachHang {

    public ArrayList<KhachHang> getAll() {
        String sql = """
                   SELECT [ID]
                         ,[MaKhachHang]
                         ,[HoTen]
                         ,[SDT]
                         ,[DiaChi]
                         ,[NgaySinh]
                         ,[GioiTinh]
                        
                     FROM [dbo].[KhachHang]
                    Where TrangThai=1
                   """;
        ArrayList<KhachHang> list = new ArrayList<>();
        try (Connection con = DBcontext.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setIdKH(rs.getInt(1));
                kh.setMaKhachHang(rs.getString(2));
                kh.setHoTen(rs.getString(3));
                kh.setSDT(rs.getString(4));
                kh.setDiaChi(rs.getString(5));
                kh.setNgaySinh(rs.getDate(6));
                kh.setGioiTinh(rs.getBoolean(7));

//                kh.setTrangThai(rs.getBoolean(8));
                list.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean add(KhachHang kh) {

        String sql = """
                   INSERT INTO [dbo].[KhachHang]
                              ([MaKhachHang]
                              ,[HoTen]
                              ,[SDT]
                              ,[DiaChi]
                              ,[NgaySinh]
                              ,[GioiTinh]
                              ,TrangThai)
                        VALUES
                              (?,?,?,?,?,?,1)
                   """;
        int check = 0;
        try (Connection con = DBcontext.getConnection();) {

            String countQuery = "SELECT COUNT(*) AS total FROM KhachHang";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(countQuery);
            resultSet.next();
            int totalProducts = resultSet.getInt("total");
            resultSet.close();
            statement.close();
            String newMasp = String.format("KH%03d", totalProducts + 1);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, newMasp);
            ps.setObject(2, kh.getHoTen());
            ps.setObject(3, kh.getSDT());
            ps.setObject(4, kh.getDiaChi());
            ps.setObject(5, kh.getNgaySinh());
            ps.setObject(6, kh.isGioiTinh());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public boolean update(int id, KhachHang newKhachHang) {
        String sql = """
                   UPDATE [dbo].[KhachHang]
                      SET 
                         [HoTen] = ?
                         ,[SDT] =?
                         ,[DiaChi] = ?
                         ,[NgaySinh] = ?
                         ,[GioiTinh] = ?
                    WHERE MaKhachHang=?
                   """;
        int check = 0;
        try (Connection con = DBcontext.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(6, newKhachHang.getMaKhachHang());
            ps.setObject(1, newKhachHang.getHoTen());
            ps.setObject(2, newKhachHang.getSDT());
            ps.setObject(3, newKhachHang.getDiaChi());
            ps.setObject(4, newKhachHang.getNgaySinh());
            ps.setObject(5, newKhachHang.isGioiTinh());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public boolean delete(int id, KhachHang newKhachHang) {
        String sql = """
                   UPDATE [dbo].[KhachHang]
                      SET 
                         [HoTen] = ?
                         ,[SDT] =?
                         ,[DiaChi] = ?
                         ,[NgaySinh] = ?
                         ,[GioiTinh] = ?
                         ,TrangThai=?
                    WHERE MaKhachHang=?
                   """;
        int check = 0;
        try (Connection con = DBcontext.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(7, newKhachHang.getMaKhachHang());
            ps.setObject(1, newKhachHang.getHoTen());
            ps.setObject(2, newKhachHang.getSDT());
            ps.setObject(3, newKhachHang.getDiaChi());
            ps.setObject(4, newKhachHang.getNgaySinh());
            ps.setObject(5, newKhachHang.isGioiTinh());
            ps.setObject(6, newKhachHang.isTrangThai());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public ArrayList<KhachHang> search(String keyWord) {
        String sql = """
                   Select ID ,MaKhachHang,HoTen,SDT,DiaChi,NgaySinh,GioiTinh
                   from KhachHang
                   where MaKhachHang like ? or HoTen like ? or SDT like ? or DiaChi like ? or NgaySinh like ? or GioiTinh like ?
                   """;
        ArrayList<KhachHang> list = new ArrayList<>();
        try (Connection con = DBcontext.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            String timKiem = "%" + keyWord + "%";
            for (int i = 1; i <= 6; i++) {
                if (i == 6) {
                    if (keyWord.equalsIgnoreCase("Nam")) {
                        ps.setBoolean(6, true);
                    } else if (keyWord.equalsIgnoreCase("Nữ")) {
                        ps.setBoolean(6, false);

                    } else {
                        ps.setObject(i, null);
                    }

                } else {
                    ps.setString(i, timKiem);
                }

            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setIdKH(rs.getInt(1));
                kh.setMaKhachHang(rs.getString(2));
                kh.setHoTen(rs.getString(3));
                kh.setSDT(rs.getString(4));
                kh.setDiaChi(rs.getString(5));
                kh.setNgaySinh(rs.getDate(6));
                kh.setGioiTinh(rs.getBoolean(7));
                list.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<KhachHang> locKhachHangNam() {
        String sql = """
                   Select ID ,MaKhachHang,HoTen,SDT,DiaChi,NgaySinh,GioiTinh
                   from KhachHang
                   where GioiTinh =1 and TrangThai=1
                   """;
        ArrayList<KhachHang> list = new ArrayList<>();
        try (Connection con = DBcontext.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setIdKH(rs.getInt(1));
                kh.setMaKhachHang(rs.getString(2));
                kh.setHoTen(rs.getString(3));
                kh.setSDT(rs.getString(4));
                kh.setDiaChi(rs.getString(5));
                kh.setNgaySinh(rs.getDate(6));
                kh.setGioiTinh(rs.getBoolean(7));
                list.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<KhachHang> locKhachHangNu() {
        String sql = """
                   Select ID ,MaKhachHang,HoTen,SDT,DiaChi,NgaySinh,GioiTinh
                   from KhachHang
                   where GioiTinh =0 and TrangThai=1
                   """;
        ArrayList<KhachHang> list = new ArrayList<>();
        try (Connection con = DBcontext.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setIdKH(rs.getInt(1));
                kh.setMaKhachHang(rs.getString(2));
                kh.setHoTen(rs.getString(3));
                kh.setSDT(rs.getString(4));
                kh.setDiaChi(rs.getString(5));
                kh.setNgaySinh(rs.getDate(6));
                kh.setGioiTinh(rs.getBoolean(7));
                list.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean checkSDT(String sdt) {
        String sql = """
                   Select SDT
                   from KhachHang
                   where SDT =?
                     
                   """;
        ArrayList<KhachHang> list = new ArrayList<>();
        try (Connection con = DBcontext.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sdt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;

            }

        } catch (Exception e) {
            return false;

        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new RepositoryKhachHang().getAll());
    }

}
