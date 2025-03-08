/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.BanHang;

import entity.BanHang.BanHang;
import entity.BanHang.HoaDon;
import entity.SanPham.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBcontext;

/**
 *
 * @author ADMIN
 */
public class BanHangDAO {

    public ArrayList<HoaDon> GetListHD() {
        ArrayList<HoaDon> listhd = new ArrayList<>();
        try {
            String sql = "select * from HoaDon where TrangThai = 0";
            Connection cnn = DBcontext.getConnection();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setID(rs.getInt("ID"));
                hd.setID_NhanVien(rs.getInt("ID_NhanVien"));
                hd.setID_KhachHang(rs.getInt("ID_KhachHang"));
                hd.setMaHoaDon(rs.getString("MaHoaDon"));
                hd.setTongTien(rs.getFloat("TongTien"));
         
                hd.setNgayThanhToan(rs.getString("NgayThanhToan"));
                hd.setTrangThai(rs.getBoolean("TrangThai"));
                hd.setMoTa(rs.getString("MoTa"));
                listhd.add(hd);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return listhd;
    }

    public int addhdcho(HoaDon hd) {
        ResultSet resultSet = null;
        try {
            Connection cnn = DBcontext.getConnection();

            String sqlIdNV = "SELECT ID FROM NhanVien WHERE TaiKhoan = ?";
            String sql = "INSERT INTO HoaDon(ID_NhanVien, MaHoaDon, NgayThanhToan, TrangThai) VALUES (?, ?, ?, ?)";

            PreparedStatement pstmtGetId = cnn.prepareStatement(sqlIdNV);
            pstmtGetId.setString(1, hd.getTaiKhoan());
            ResultSet rs = pstmtGetId.executeQuery();

            if (rs.next()) {
                int idNhanVien = rs.getInt("ID");

                String countQuery = "SELECT COUNT(*) AS total FROM HoaDon";
                Statement statement = cnn.createStatement();
                resultSet = statement.executeQuery(countQuery);
                resultSet.next();
                int totalProducts = resultSet.getInt("total");
                resultSet.close();
                statement.close();
                String newMaHD = String.format("HD01%04d", totalProducts + 1);

                PreparedStatement stm = cnn.prepareStatement(sql);
                stm.setInt(1, idNhanVien);
                stm.setString(2, newMaHD);
                stm.setString(3, hd.getNgayThanhToan());
                stm.setBoolean(4, hd.isTrangThai());

                int rowAFF = stm.executeUpdate();
                return rowAFF;
            } else {
                System.out.println("Không tìm thấy MaNhanVien.");
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public List<BanHang> GetListGH(String mahd) {
        List<BanHang> listgh = new ArrayList<>();
        try {
            String sql = "select MaSP, TenSP,SanPhamChiTiet.DonGia, HoaDonChiTiet.SoLuong, DonGiaMua from HoaDonChiTiet inner join SanPhamChiTiet on HoaDonChiTiet.ID_SanPhamChiTiet=SanPhamChiTiet.ID inner join HoaDon on HoaDonChiTiet.ID_HoaDon = HoaDon.ID where MaHoaDon = ? ";
            Connection cnn = DBcontext.getConnection();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, mahd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BanHang s = new BanHang();
                s.setMaSP(rs.getString("MaSP"));
                s.setTenSP(rs.getString("TenSP"));
                s.setDonGia(rs.getFloat("DonGia"));
                s.setSoLuong(rs.getInt("SoLuong"));
                s.setTongTien(rs.getFloat("DonGiaMua"));
                listgh.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return listgh;
    }

    public HoaDon getHoaDonByMaHD(String mahd) {
        HoaDon hoaDon = null;
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
        try {
            Connection cnn = DBcontext.getConnection();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, mahd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hoaDon = new HoaDon();
                hoaDon.setID(rs.getInt("ID"));
                hoaDon.setID_KhachHang(rs.getInt("ID_KhachHang"));
                hoaDon.setID_NhanVien(rs.getInt("ID_NhanVien"));
                hoaDon.setMaHoaDon(rs.getString("MaHoaDon"));
                hoaDon.setTongTien(rs.getFloat("TongTien"));
                hoaDon.setNgayThanhToan(rs.getString("NgayThanhToan"));
                hoaDon.setTrangThai(rs.getBoolean("TrangThai"));
                hoaDon.setMoTa(rs.getString("MoTa"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return hoaDon;
    }

    //banhang
    public int updatehd(HoaDon hd) {

        try {
             Connection cnn = DBcontext.getConnection();
            
            String sqlIdHoaDon = "SELECT ID FROM KhachHang WHERE HoTen = ?";
            PreparedStatement pstmtGetIdHoaDon = cnn.prepareStatement(sqlIdHoaDon);
            pstmtGetIdHoaDon.setString(1, hd.getMaKH());
            ResultSet rsHoaDon = pstmtGetIdHoaDon.executeQuery();
            rsHoaDon.next();
            int idKhachHang = rsHoaDon.getInt("ID");
            
            String sql = "UPDATE HoaDon SET ID_KhachHang = ? ,TongTien = ? ,MoTa = ? ,TrangThai = 1 WHERE MaHoaDon = ?";
           
            PreparedStatement stm = cnn.prepareStatement(sql);

            stm.setInt(1, idKhachHang);
            stm.setFloat(2, hd.getTongTien());
            stm.setString(3, hd.getMoTa());
            stm.setString(4, hd.getMaHoaDon());

            int rowAFF = stm.executeUpdate();
            return rowAFF;

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public int addhdct(BanHang bh) {
        ResultSet resultSet = null;
        try {
            Connection cnn = DBcontext.getConnection();
            // Truy vấn để lấy ID_HoaDon từ MaHD
            String sqlIdHoaDon = "SELECT ID FROM HoaDon WHERE MaHoaDon = ?";
            PreparedStatement pstmtGetIdHoaDon = cnn.prepareStatement(sqlIdHoaDon);
            pstmtGetIdHoaDon.setString(1, bh.getMaHD());
            ResultSet rsHoaDon = pstmtGetIdHoaDon.executeQuery();
            rsHoaDon.next();
            int idHoaDon = rsHoaDon.getInt("ID");

            // Truy vấn để lấy ID_SanPhamChiTiet từ MaSP
            String sqlIdSPCT = "SELECT ID FROM SanPhamChiTiet WHERE MaSP = ?";
            PreparedStatement pstmtGetId = cnn.prepareStatement(sqlIdSPCT);
            pstmtGetId.setString(1, bh.getMaSP());
            ResultSet rsSPCT = pstmtGetId.executeQuery();

            if (rsSPCT.next()) {
                int idSPCT = rsSPCT.getInt("ID");
                String sql = "insert into HoaDonChiTiet(ID_SanPhamChiTiet,ID_HoaDon,SoLuong,DonGiaMua) values (?,?,?,?);";
                PreparedStatement stm = cnn.prepareStatement(sql);

                stm.setInt(1, idSPCT);
                stm.setInt(2, idHoaDon);
                stm.setInt(3, bh.getSoLuong());
                stm.setFloat(4, bh.getTongTien());

                int rowAFF = stm.executeUpdate();
                return rowAFF;
            } else {
                System.out.println("Không tìm thấy MaSP.");
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public int updatehdct(BanHang bh) {
        try {
            Connection cnn = DBcontext.getConnection();

            // Truy vấn để lấy ID_HoaDon từ MaHD
            String sqlIdHoaDon = "SELECT ID FROM HoaDon WHERE MaHoaDon = ?";
            PreparedStatement pstmtGetIdHoaDon = cnn.prepareStatement(sqlIdHoaDon);
            pstmtGetIdHoaDon.setString(1, bh.getMaHD());
            ResultSet rsHoaDon = pstmtGetIdHoaDon.executeQuery();
            if (!rsHoaDon.next()) {
                System.out.println("Không tìm thấy MaHD.");
                return 0;
            }
            int idHoaDon = rsHoaDon.getInt("ID");

            // Truy vấn để lấy ID_SanPhamChiTiet từ MaSP
            String sqlIdSPCT = "SELECT ID FROM SanPhamChiTiet WHERE MaSP = ?";
            PreparedStatement pstmtGetId = cnn.prepareStatement(sqlIdSPCT);
            pstmtGetId.setString(1, bh.getMaSP());
            ResultSet rsSPCT = pstmtGetId.executeQuery();
            if (!rsSPCT.next()) {
                System.out.println("Không tìm thấy MaSP.");
                return 0;
            }
            int idSPCT = rsSPCT.getInt("ID");

            // Truy vấn để cập nhật thông tin trong bảng HoaDonChiTiet
            String sqlUpdate = "UPDATE HoaDonChiTiet SET SoLuong = ?, DonGiaMua = ? WHERE ID_SanPhamChiTiet = ? AND ID_HoaDon = ?";
            PreparedStatement stm = cnn.prepareStatement(sqlUpdate);
            stm.setInt(1, bh.getSoLuong());
            stm.setFloat(2, bh.getTongTien());
            stm.setInt(3, idSPCT);
            stm.setInt(4, idHoaDon);

            int rowAFF = stm.executeUpdate();
            return rowAFF;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public int deletehdct(BanHang bh) {
        try {
            Connection cnn = DBcontext.getConnection();

            // Truy vấn để lấy ID_HoaDon từ MaHD
            String sqlIdHoaDon = "SELECT ID FROM HoaDon WHERE MaHoaDon = ?";
            PreparedStatement pstmtGetIdHoaDon = cnn.prepareStatement(sqlIdHoaDon);
            pstmtGetIdHoaDon.setString(1, bh.getMaHD());
            ResultSet rsHoaDon = pstmtGetIdHoaDon.executeQuery();
            if (!rsHoaDon.next()) {
                System.out.println("Không tìm thấy MaHD.");
                return 0;
            }
            int idHoaDon = rsHoaDon.getInt("ID");

            // Truy vấn để lấy ID_SanPhamChiTiet từ MaSP
            String sqlIdSPCT = "SELECT ID FROM SanPhamChiTiet WHERE MaSP = ?";
            PreparedStatement pstmtGetId = cnn.prepareStatement(sqlIdSPCT);
            pstmtGetId.setString(1, bh.getMaSP());
            ResultSet rsSPCT = pstmtGetId.executeQuery();
            if (!rsSPCT.next()) {
                System.out.println("Không tìm thấy MaSP.");
                return 0;
            }
            int idSPCT = rsSPCT.getInt("ID");

            // Xóa hóa đơn chi tiết khỏi cơ sở dữ liệu
            String sqlDelete = "DELETE FROM HoaDonChiTiet WHERE ID_SanPhamChiTiet = ? AND ID_HoaDon = ?";
            PreparedStatement stm = cnn.prepareStatement(sqlDelete);
            stm.setInt(1, idSPCT);
            stm.setInt(2, idHoaDon);

            int rowAFF = stm.executeUpdate();
            return rowAFF;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public int updatesp(SanPham sp) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            String sql = " update SanPhamChiTiet SET SoLuong = ? where MaSP = ?\n";

            conn = DBcontext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, sp.getSoLuong());
            stm.setString(2, sp.getMaSp());
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

    public int updatespsauxoa(SanPham sp) {
        try {
            Connection cnn = DBcontext.getConnection();

            // Truy vấn để lấy số lượng sản phẩm hiện tại từ MaSP
            String sqlGetCurrentQuantity = "SELECT SoLuong FROM SanPhamChiTiet WHERE MaSP = ?";
            PreparedStatement pstmtGetCurrentQuantity = cnn.prepareStatement(sqlGetCurrentQuantity);
            pstmtGetCurrentQuantity.setString(1, sp.getMaSp());
            ResultSet rs = pstmtGetCurrentQuantity.executeQuery();
            if (rs.next()) {
                int currentQuantity = rs.getInt("SoLuong");

                // Cập nhật số lượng sản phẩm mới
                int newQuantity = currentQuantity + sp.getSoLuong();

                // Cập nhật lại số lượng trong cơ sở dữ liệu
                String sqlUpdate = "UPDATE SanPhamChiTiet SET SoLuong = ? WHERE MaSP = ?";
                PreparedStatement pstmtUpdate = cnn.prepareStatement(sqlUpdate);
                pstmtUpdate.setInt(1, newQuantity);
                pstmtUpdate.setString(2, sp.getMaSp());

                int rowAFF = pstmtUpdate.executeUpdate();
                return rowAFF;
            } else {
                System.out.println("Không tìm thấy MaSP.");
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public void deleteAllHdct(BanHang bh) {
        String maHD = bh.getMaHD();
        try {
            Connection cnn = DBcontext.getConnection();// Tạo câu lệnh SQL để xóa tất cả các hóa đơn chi tiết dựa trên maHD
            String sqlIdHoaDon = "SELECT ID FROM HoaDon WHERE MaHoaDon = ?";
            PreparedStatement pstmtGetIdHoaDon = cnn.prepareStatement(sqlIdHoaDon);
            pstmtGetIdHoaDon.setString(1, bh.getMaHD());
            ResultSet rsHoaDon = pstmtGetIdHoaDon.executeQuery();
            rsHoaDon.next();

            int idHoaDon = rsHoaDon.getInt("ID");

            String sql = "DELETE FROM HoaDonChiTiet WHERE ID_HoaDon = ?";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setInt(1, idHoaDon);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // Phương thức cập nhật số lượng sản phẩm khi xóa
    public void updatespsauxoaall(SanPham sp) {
        String maSp = sp.getMaSp();
        int soLuong = sp.getSoLuong();
        // Tạo câu lệnh SQL để cập nhật số lượng sản phẩm
        try {
            Connection cnn = DBcontext.getConnection(); // Tạo câu lệnh SQL để xóa tất cả các hóa đơn chi tiết dựa trên maHD
            String sql = "UPDATE SanPham SET SoLuong = SoLuong + ? WHERE MaSp = ?";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setInt(1, soLuong);
            pstmt.setString(2, maSp);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public int updatespsausua(SanPham sp) {
        try {
            Connection cnn = DBcontext.getConnection();

            // Truy vấn để lấy số lượng sản phẩm hiện tại từ MaSP
            String sqlGetCurrentQuantity = "SELECT SoLuong FROM SanPhamChiTiet WHERE MaSP = ?";
            PreparedStatement pstmtGetCurrentQuantity = cnn.prepareStatement(sqlGetCurrentQuantity);
            pstmtGetCurrentQuantity.setString(1, sp.getMaSp());
            ResultSet rs = pstmtGetCurrentQuantity.executeQuery();
            if (rs.next()) {
                int currentQuantity = rs.getInt("SoLuong");

                // Cập nhật số lượng sản phẩm mới
                int newQuantity = currentQuantity - sp.getSoLuong();

                // Cập nhật lại số lượng trong cơ sở dữ liệu
                String sqlUpdate = "UPDATE SanPhamChiTiet SET SoLuong = ? WHERE MaSP = ?";
                PreparedStatement pstmtUpdate = cnn.prepareStatement(sqlUpdate);
                pstmtUpdate.setInt(1, newQuantity);
                pstmtUpdate.setString(2, sp.getMaSp());

                int rowAFF = pstmtUpdate.executeUpdate();
                return rowAFF;
            } else {
                System.out.println("Không tìm thấy MaSP.");
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public String findMaKHbangsdt(String phoneNumber) {
        String tenKhachHang = null;
        String sql = "SELECT HoTen FROM KhachHang WHERE SDT = ?";
        Connection cn = DBcontext.getConnection();

        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, phoneNumber);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                tenKhachHang = rs.getString("HoTen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tenKhachHang;
    }

}
