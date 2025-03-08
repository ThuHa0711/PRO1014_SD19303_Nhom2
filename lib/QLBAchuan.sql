CREATE DATABASE QLBAP;
GO
USE QLBAP;
GO

-- Tạo bảng ChucVu
CREATE TABLE ChucVu (
    ID INT PRIMARY KEY IDENTITY,
    TenChucVu NVARCHAR(50)
);

-- Tạo bảng NhanVien
CREATE TABLE NhanVien (
    ID INT PRIMARY KEY IDENTITY,
    MaNhanVien VARCHAR(20),
    HoTen NVARCHAR(100),
    SDT VARCHAR(20),
    NgaySinh DATE,
    GioiTinh BIT,
    DiaChi NVARCHAR(50),
    Anh VARCHAR(40),
    TrangThai NVARCHAR(50),
    TaiKhoan VARCHAR(50),
    MatKhau VARCHAR(50),
    ID_ChucVu INT,
    FOREIGN KEY (ID_ChucVu) REFERENCES ChucVu(ID)
);

-- Tạo bảng KhachHang
CREATE TABLE KhachHang (
    ID INT PRIMARY KEY IDENTITY,
    MaKhachHang VARCHAR(50),
    HoTen NVARCHAR(100),
    SDT VARCHAR(20),
    DiaChi NVARCHAR(50),
    NgaySinh DATE,
    GioiTinh BIT,
    TrangThai BIT
);



-- Tạo bảng HoaDon
CREATE TABLE HoaDon (
    ID INT PRIMARY KEY IDENTITY,
    ID_NhanVien INT,
    ID_KhachHang INT,
   
    MaHoaDon VARCHAR(40),
    TongTien DECIMAL(18, 2),
    DiaChi NVARCHAR(40),
    NgayThanhToan DATE DEFAULT GETDATE(),
    TrangThai BIT,
    MoTa NVARCHAR(255),
    FOREIGN KEY (ID_NhanVien) REFERENCES NhanVien(ID),
    FOREIGN KEY (ID_KhachHang) REFERENCES KhachHang(ID),
    
);

-- Tạo bảng KichCo
CREATE TABLE KichCo (
    ID INT PRIMARY KEY IDENTITY,
    TenKichCo NVARCHAR(50)
);

-- Tạo bảng TheLoai
CREATE TABLE TheLoai (
    ID INT PRIMARY KEY IDENTITY,
    TenTheLoai NVARCHAR(100)
);

-- Tạo bảng MauSac
CREATE TABLE MauSac (
    ID INT PRIMARY KEY IDENTITY,
    TenMauSac NVARCHAR(100)
);

-- Tạo bảng ChatLieu
CREATE TABLE ChatLieu (
    ID INT PRIMARY KEY IDENTITY,
    TenChatLieu NVARCHAR(100)
);

-- Tạo bảng SanPhamChiTiet
CREATE TABLE SanPhamChiTiet (
    ID INT PRIMARY KEY IDENTITY,
    ID_TheLoai INT,
    ID_ChatLieu INT,
    ID_MauSac INT,
    ID_KichCo INT,
    MaSP VARCHAR(30),
    TenSP NVARCHAR(50),
    Anh VARCHAR(50),
    MoTa NVARCHAR(50),
    DonGia FLOAT,
    SoLuong INT,
    FOREIGN KEY (ID_TheLoai) REFERENCES TheLoai(ID),
    FOREIGN KEY (ID_MauSac) REFERENCES MauSac(ID),
    FOREIGN KEY (ID_ChatLieu) REFERENCES ChatLieu(ID),
    FOREIGN KEY (ID_KichCo) REFERENCES KichCo(ID)
);

-- Tạo bảng HoaDonChiTiet
CREATE TABLE HoaDonChiTiet (
    ID INT PRIMARY KEY IDENTITY,
    ID_SanPhamChiTiet INT,
    ID_HoaDon INT,
    SoLuong INT,
    DonGia DECIMAL(18, 2),
    FOREIGN KEY (ID_SanPhamChiTiet) REFERENCES SanPhamChiTiet(ID),
    FOREIGN KEY (ID_HoaDon) REFERENCES HoaDon(ID)
);

-- Thêm dữ liệu vào bảng ChucVu
INSERT INTO ChucVu (TenChucVu) VALUES
(N'Quản lý'),
(N'Thu ngân'),
(N'Nhân viên bán hàng'),
(N'Nhân viên kho'),
(N'Chăm sóc khách hàng'),
(N'Tiếp thị'),
(N'Hỗ trợ IT'),
(N'Chuyên viên nhân sự'),
(N'Kế toán'),
(N'Bảo vệ');

-- Thêm dữ liệu vào bảng NhanVien
INSERT INTO NhanVien (MaNhanVien, HoTen, SDT, NgaySinh, GioiTinh, DiaChi, Anh, TrangThai, TaiKhoan, MatKhau, ID_ChucVu) VALUES
('NV001', N'Nguyễn Văn A', '0901234567', '1985-01-01', 1, N'Hà Nội', 'anh1.jpg', N'Hoạt động', 'nguyenvana', 'matkhau1', 1),
('NV002', N'Trần Thị B', '0902345678', '1990-02-01', 0, N'TP Hồ Chí Minh', 'anh2.jpg', N'Hoạt động', 'tranthib', 'matkhau2', 2),
('NV003', N'Lê Văn C', '0903456789', '1995-03-01', 1, N'Đà Nẵng', 'anh3.jpg', N'Hoạt động', 'levanc', 'matkhau3', 3),
('NV004', N'Phạm Thị D', '0904567890', '2000-04-01', 0, N'Huế', 'anh4.jpg', N'Hoạt động', 'phamthid', 'matkhau4', 4),
('NV005', N'Hoàng Văn E', '0905678901', '1980-05-01', 1, N'Cần Thơ', 'anh5.jpg', N'Hoạt động', 'hoangvane', 'matkhau5', 5),
('NV006', N'Nguyễn Thị F', '0906789012', '1985-06-01', 0, N'Hà Nội', 'anh6.jpg', N'Hoạt động', 'nguyenthif', 'matkhau6', 6),
('NV007', N'Trần Văn G', '0907890123', '1990-07-01', 1, N'TP Hồ Chí Minh', 'anh7.jpg', N'Hoạt động', 'tranvang', 'matkhau7', 7),
('NV008', N'Lê Thị H', '0908901234', '1995-08-01', 0, N'Đà Nẵng', 'anh8.jpg', N'Hoạt động', 'lethih', 'matkhau8', 8),
('NV009', N'Phạm Văn I', '0909012345', '2000-09-01', 1, N'Huế', 'anh9.jpg', N'Hoạt động', 'phamvani', 'matkhau9', 9),
('NV010', N'Hoàng Thị J', '0910123456', '1980-10-01', 0, N'Cần Thơ', 'anh10.jpg', N'Hoạt động', 'hoangthij', 'matkhau10', 10);

-- Thêm dữ liệu vào bảng KhachHang
INSERT INTO KhachHang (MaKhachHang, HoTen, SDT, DiaChi, NgaySinh, GioiTinh, TrangThai) VALUES
('KH001', N'Nguyễn Thị F', '0911234567', N'Hà Nội', '1985-06-01', 0, 1),
('KH002', N'Trần Văn G', '0912345678', N'TP Hồ Chí Minh', '1990-07-01', 1, 1),
('KH003', N'Lê Thị H', '0913456789', N'Đà Nẵng', '1995-08-01', 0, 1),
('KH004', N'Phạm Văn I', '0914567890', N'Huế', '2000-09-01', 1, 1),
('KH005', N'Hoàng Thị J', '0915678901', N'Cần Thơ', '1980-10-01', 0, 1),
('KH006', N'Nguyễn Văn K', '0916789012', N'Hải Phòng', '1985-11-01', 1, 1),
('KH007', N'Trần Thị L', '0917890123', N'Quảng Ninh', '1990-12-01', 0, 1),
('KH008', N'Lê Văn M', '0918901234', N'Nam Định', '1995-01-01', 1, 1),
('KH009', N'Phạm Thị N', '0919012345', N'Thái Bình', '2000-02-01', 0, 1),
('KH010', N'Hoàng Văn O', '0920123456', N'Hưng Yên', '1980-03-01', 1, 1);

-- Thêm dữ liệu vào bảng Voucher



-- Thêm dữ liệu vào bảng HoaDon
INSERT INTO HoaDon (ID_NhanVien, ID_KhachHang, MaHoaDon, TongTien, DiaChi, NgayThanhToan, TrangThai, MoTa) VALUES
(1, 1, 'HD001', 1000.00, N'Hà Nội', '2023-07-25', 1, N'Hóa đơn thanh toán tại cửa hàng'),
(2, 2, 'HD002', 2000.00, N'TP Hồ Chí Minh', '2023-07-25', 1, N'Hóa đơn thanh toán trực tuyến'),
(3, 3, 'HD003', 1500.00, N'Đà Nẵng', '2023-07-26', 1, N'Hóa đơn thanh toán tại cửa hàng'),
(4, 4, 'HD004', 3000.00, N'Huế', '2023-07-26', 0, N'Hóa đơn thanh toán trực tuyến'),
(5, 5, 'HD005', 2500.00, N'Cần Thơ', '2023-07-27', 1, N'Hóa đơn thanh toán tại cửa hàng');

-- Thêm dữ liệu vào bảng KichCo
INSERT INTO KichCo (TenKichCo) VALUES
(N'Nhỏ'),
(N'Vừa'),
(N'Lớn');

-- Thêm dữ liệu vào bảng TheLoai
INSERT INTO TheLoai (TenTheLoai) VALUES
(N'Áo'),
(N'Quần'),
(N'Giày');

-- Thêm dữ liệu vào bảng MauSac
INSERT INTO MauSac (TenMauSac) VALUES
(N'Đỏ'),
(N'Xanh'),
(N'Vàng');

-- Thêm dữ liệu vào bảng ChatLieu
INSERT INTO ChatLieu (TenChatLieu) VALUES
(N'Cotton'),
(N'Jean'),
(N'Polyester');

-- Thêm dữ liệu vào bảng SanPhamChiTiet
INSERT INTO SanPhamChiTiet (ID_TheLoai, ID_ChatLieu, ID_MauSac, ID_KichCo, MaSP, TenSP, Anh, MoTa, DonGia, SoLuong) VALUES
(1, 1, 1, 1, 'SP001', N'Áo Phông Cổ Tròn', 'ao1.jpg', N'Áo phông cotton cổ tròn', 100.00, 10),
(1, 2, 2, 2, 'SP002', N'Áo Phông Cổ Tim', 'ao2.jpg', N'Áo phông cổ tim', 200.00, 20),
(1, 3, 3, 3, 'SP003', N'Áo Phông Dài Tay', 'ao3.jpg', N'Áo phông dài tay', 300.00, 30),
(1, 2, 3, 1, 'SP004', N'Áo Phông Sát Nách', 'ao4.jpg', N'Áo phông sát nách', 400.00, 40),
(1, 3, 1, 2, 'SP005', N'Áo Phông Họa Tiết', 'ao5.jpg', N'Áo phông họa tiết', 500.00, 50);

-- Thêm dữ liệu vào bảng HoaDonChiTiet
INSERT INTO HoaDonChiTiet (ID_SanPhamChiTiet, ID_HoaDon, SoLuong, DonGia) VALUES
(1, 1, 1, 100.00),
(2, 2, 2, 200.00),
(3, 3, 3, 300.00),
(4, 4, 4, 400.00),
(5, 5, 5, 500.00);