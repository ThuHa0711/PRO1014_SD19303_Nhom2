/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.NhanVien;

import entity.NhanVien.NhanVien;
import entity.NhanVien.ChucVu;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBcontext;

/**
 *
 * @author DELL
 */
public class NhanVienRepository {

    public List<NhanVien> getAll() {
        List<NhanVien> lists = new ArrayList<>();
        String sql = """
                     SELECT dbo.NhanVien.ID, dbo.NhanVien.MaNhanVien, dbo.NhanVien.HoTen, dbo.NhanVien.SDT, dbo.NhanVien.NgaySinh, dbo.NhanVien.GioiTinh, dbo.NhanVien.DiaChi, dbo.NhanVien.Anh, dbo.NhanVien.TrangThai, dbo.NhanVien.TaiKhoan, 
                                       dbo.NhanVien.MatKhau, dbo.ChucVu.TenChucVu
                     FROM     dbo.NhanVien INNER JOIN
                                       dbo.ChucVu ON dbo.NhanVien.ID_ChucVu = dbo.ChucVu.ID
                     				 where TrangThai like N'Hoạt động'
                     """;
        try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu cv = new ChucVu();
                cv.setTenCV(rs.getString(12));
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt(1));
                nv.setMaNV(rs.getString(2));
                nv.setHoTen(rs.getString(3));
                nv.setSdt(rs.getString(4));
                nv.setNgaySinh(rs.getDate(5));
                nv.setGioiTinh(rs.getBoolean(6));
                nv.setDiaChi(rs.getString(7));
                nv.setAnh(rs.getString(8));
                nv.setTrangThai(rs.getString(9));
                nv.setTaiKhoan(rs.getString(10));
                nv.setMatKhau(rs.getString(11));
                nv.setChucVu(cv);
                lists.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public boolean add(NhanVien nv) {
        int check = 0;
        String sql = """
                     INSERT INTO [dbo].[NhanVien]
                                ([MaNhanVien]
                                ,[HoTen]
                                ,[SDT]
                                ,[NgaySinh]
                                ,[GioiTinh]
                                ,[DiaChi]
                                ,[Anh]
                                ,[TrangThai]
                                ,[TaiKhoan]
                                ,[MatKhau]
                                ,[ID_ChucVu])
                          VALUES
                                (?,?,?,?,?,?,?,N'Hoạt động',?,?,?)
                     """;
        try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            String countQuery = "SELECT COUNT(*) AS total FROM NhanVien";
            Statement statement = (Statement) con.createStatement();
            ResultSet rs = statement.executeQuery(countQuery);
            rs.next();
            int totalProducts = rs.getInt("total");
            rs.close();
            statement.close();
            String newMaNV = String.format("NV%03d", totalProducts + 1);
            ps.setObject(1, newMaNV);
            ps.setObject(2, nv.getHoTen());
            ps.setObject(3, nv.getSdt());
            ps.setObject(4, nv.getNgaySinh());
            ps.setObject(5, nv.getGioiTinh());
            ps.setObject(6, nv.getDiaChi());
            ps.setObject(7, nv.getAnh());
            ps.setObject(8, nv.getTaiKhoan());
            ps.setObject(9, nv.getMatKhau());
            ps.setObject(10, nv.getChucVu().getId());
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean update(Integer id, NhanVien newNV) {
        String sql = """
                     UPDATE [dbo].[NhanVien]
                        SET [MaNhanVien] = ?
                           ,[HoTen] = ?
                           ,[SDT] = ?
                           ,[NgaySinh] = ?
                           ,[GioiTinh] = ?
                           ,[DiaChi] = ?
                           ,[Anh] = ?
                           ,[TrangThai] = ?
                           ,[TaiKhoan] = ?
                           ,[MatKhau] = ?
                           ,[ID_ChucVu] = ?
                      WHERE ID = ?
                     """;
        int check = 0;
        try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, newNV.getMaNV());
            ps.setObject(2, newNV.getHoTen());
            ps.setObject(3, newNV.getSdt());
            ps.setObject(4, newNV.getNgaySinh());
            ps.setObject(5, newNV.getGioiTinh());
            ps.setObject(6, newNV.getDiaChi());
            ps.setObject(7, newNV.getAnh());
            ps.setObject(8, newNV.getTrangThai());
            ps.setObject(9, newNV.getTaiKhoan());
            ps.setObject(10, newNV.getMatKhau());
            ps.setObject(11, newNV.getChucVu().getId());
            ps.setObject(12, id);
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean remove(Integer id) {
        String sql = """
                     UPDATE [dbo].[NhanVien]
                        SET 
                           [TrangThai] = N'Không hoạt động'
                      WHERE ID = ?
                     """;
        int check = 0;
        try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public List<NhanVien> search(String keyWord) {
        List<NhanVien> lists = new ArrayList<>();
        String sql = """
                     SELECT dbo.NhanVien.ID, dbo.NhanVien.MaNhanVien, dbo.NhanVien.HoTen, dbo.NhanVien.SDT, dbo.NhanVien.NgaySinh, dbo.NhanVien.GioiTinh, dbo.NhanVien.DiaChi, dbo.NhanVien.Anh, dbo.NhanVien.TrangThai, dbo.NhanVien.TaiKhoan, 
                                       dbo.NhanVien.MatKhau, dbo.ChucVu.TenChucVu
                     FROM     dbo.NhanVien INNER JOIN
                                       dbo.ChucVu ON dbo.NhanVien.ID_ChucVu = dbo.ChucVu.ID
                     				  where 
                                                  dbo.NhanVien.MaNhanVien LIKE ? OR
                                                  dbo.NhanVien.HoTen LIKE ? OR
                                                  dbo.NhanVien.SDT LIKE ? OR
                                                  dbo.NhanVien.NgaySinh LIKE ? OR
                                                  dbo.NhanVien.GioiTinh = ? OR
                                                  dbo.NhanVien.DiaChi LIKE ? OR
                                                  dbo.NhanVien.Anh LIKE ? OR
                                                  dbo.NhanVien.TrangThai LIKE ? OR
                                                  dbo.NhanVien.TaiKhoan LIKE ? OR
                                                  dbo.NhanVien.MatKhau LIKE ? OR
                                                  dbo.ChucVu.TenChucVu LIKE ? 
                     """;
        try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            String timKiem = "%" + keyWord + "%";
            for (int i = 1; i <= 11; i++) {
                if (i == 5) {
                    if (keyWord.equalsIgnoreCase("Nữ")) {
                        ps.setBoolean(i, false);
                    } else if (keyWord.equalsIgnoreCase("Nam")) {
                        ps.setBoolean(i, true);
                    } else {
                        ps.setObject(i, null);
                    }
                } else {
                    ps.setString(i, timKiem);
                }
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu cv = new ChucVu();
                cv.setTenCV(rs.getString(12));
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt(1));
                nv.setMaNV(rs.getString(2));
                nv.setHoTen(rs.getString(3));
                nv.setSdt(rs.getString(4));
                nv.setNgaySinh(rs.getDate(5));
                nv.setGioiTinh(rs.getBoolean(6));
                nv.setDiaChi(rs.getString(7));
                nv.setAnh(rs.getString(8));
                nv.setTrangThai(rs.getString(9));
                nv.setTaiKhoan(rs.getString(10));
                nv.setMatKhau(rs.getString(11));
                nv.setChucVu(cv);
                lists.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }
    
    public boolean checkMa(String maNV) {
        List<NhanVien> lists = new ArrayList<>();
        String sql = """
                     SELECT 
                            [MaNhanVien]
                       FROM [dbo].[NhanVien]
                     where MaNhanVien = ?
                     """;
        try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maNV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean checkSDT(String sdt) {
        List<NhanVien> lists = new ArrayList<>();
        String sql = """
                     SELECT 
                            [SDT]
                       FROM [dbo].[NhanVien]
                     where SDT = ?
                     """;
        try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
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

    public List<String> loadDiaChi() {
        List<String> lists = new ArrayList<>();
        String sql = """
                     SELECT DISTINCT DiaChi FROM NhanVien                      				  
                     """;
        try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String diaChi = rs.getString("DiaChi");
                lists.add(diaChi);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public List<NhanVien> locDuLieu(String gioiTinhSelected, String chucVuSelected, String trangThaiSelected) {
        List<NhanVien> lists = new ArrayList<>();
        String sql = """
                     SELECT dbo.NhanVien.ID, dbo.NhanVien.MaNhanVien, dbo.NhanVien.HoTen, dbo.NhanVien.SDT, dbo.NhanVien.NgaySinh, dbo.NhanVien.GioiTinh, dbo.NhanVien.DiaChi, dbo.NhanVien.Anh, dbo.NhanVien.TrangThai, dbo.NhanVien.TaiKhoan, 
                                       dbo.NhanVien.MatKhau, dbo.ChucVu.TenChucVu
                     FROM     dbo.NhanVien INNER JOIN
                                       dbo.ChucVu ON dbo.NhanVien.ID_ChucVu = dbo.ChucVu.ID
                     	where 1 = 1
                     
                     """;
        if (!"Tất cả".equals(gioiTinhSelected)) {
            if (gioiTinhSelected.equals("Nam")) {
                sql += " AND NhanVien.GioiTinh = 1";
            }
            if (gioiTinhSelected.equals("Nữ")) {
                sql += " AND NhanVien.GioiTinh = 0";
            }
        }

        if (!"Tất cả".equals(chucVuSelected)) {
            sql += " AND ChucVu.TenChucVu = ?";
        }
        if (!"Tất cả".equals(trangThaiSelected)) {
            sql += " AND NhanVien.TrangThai = ?";
        }
        try (Connection con = DBcontext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            int parameterIndex = 1;

            if (!"Tất cả".equals(chucVuSelected)) {
                ps.setString(parameterIndex++, chucVuSelected);
            }
            if (!"Tất cả".equals(trangThaiSelected)) {
                ps.setString(parameterIndex++, trangThaiSelected);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu cv = new ChucVu();
                cv.setTenCV(rs.getString(12));
                NhanVien nv = new NhanVien();
                nv.setId(rs.getInt(1));
                nv.setMaNV(rs.getString(2));
                nv.setHoTen(rs.getString(3));
                nv.setSdt(rs.getString(4));
                nv.setNgaySinh(rs.getDate(5));
                nv.setGioiTinh(rs.getBoolean(6));
                nv.setDiaChi(rs.getString(7));
                nv.setAnh(rs.getString(8));
                nv.setTrangThai(rs.getString(9));
                nv.setTaiKhoan(rs.getString(10));
                nv.setMatKhau(rs.getString(11));
                nv.setChucVu(cv);
                lists.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public static void main(String[] args) {
        System.out.println(new NhanVienRepository().getAll());
    }
}
