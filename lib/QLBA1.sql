CREATE DATABASE QLBanAo;
GO
USE QLBanAo;
GO

-- Create table ChucVu
CREATE TABLE ChucVu (
    ID INT PRIMARY KEY IDENTITY,
    TenChucVu NVARCHAR(50)
);

-- Create table NhanVien
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

-- Create table KhachHang
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

-- Create table Voucher
CREATE TABLE Voucher (
    ID INT PRIMARY KEY IDENTITY,
    MaVoucher VARCHAR(50),
    GiaTri DECIMAL(10, 2),
    NgayBatDau DATE,
    NgayKetThuc DATE,
    SoLuong INT,
    MoTa NVARCHAR(50),
    TrangThai BIT
);

-- Create table HoaDon
CREATE TABLE HoaDon (
    ID INT PRIMARY KEY IDENTITY,
    ID_NhanVien INT,
    ID_KhachHang INT,
    ID_Voucher INT,
    MaHoaDon VARCHAR(40),
    TongTien DECIMAL(18, 2),
    DiaChi NVARCHAR(40),
    NgayThanhToan DATE DEFAULT GETDATE(),
    TrangThai BIT,
    MoTa NVARCHAR(255),
    FOREIGN KEY (ID_NhanVien) REFERENCES NhanVien(ID),
    FOREIGN KEY (ID_KhachHang) REFERENCES KhachHang(ID),
    FOREIGN KEY (ID_Voucher) REFERENCES Voucher(ID)
);

-- Create table KichCo
CREATE TABLE KichCo (
    ID INT PRIMARY KEY IDENTITY,
    TenKichCo NVARCHAR(50)
);

-- Create table TheLoai
CREATE TABLE TheLoai (
    ID INT PRIMARY KEY IDENTITY,
    TenTheLoai NVARCHAR(100)
);

-- Create table MauSac
CREATE TABLE MauSac (
    ID INT PRIMARY KEY IDENTITY,
    TenMauSac NVARCHAR(100)
);

-- Create table ChatLieu
CREATE TABLE ChatLieu (
    ID INT PRIMARY KEY IDENTITY,
    TenChatLieu NVARCHAR(100)
);

-- Create table SanPhamChiTiet
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

-- Create table HoaDonChiTiet
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
('NV001', N'Nguyễn Văn A', '0901234567', '1985-01-01', 1, N'Hà Nội', 'image1.jpg', N'Hoạt động', 'nguyenvana', 'matkhau1', 1),
('NV002', N'Trần Thị B', '0902345678', '1990-02-01', 0, N'TP Hồ Chí Minh', 'image2.jpg', N'Hoạt động', 'tranthib', 'matkhau2', 2),
('NV003', N'Lê Văn C', '0903456789', '1995-03-01', 1, N'Đà Nẵng', 'image3.jpg', N'Hoạt động', 'levanc', 'matkhau3', 3),
('NV004', N'Phạm Thị D', '0904567890', '2000-04-01', 0, N'Huế', 'image4.jpg', N'Hoạt động', 'phamthid', 'matkhau4', 4),
('NV005', N'Hoàng Văn E', '0905678901', '1980-05-01', 1, N'Cần Thơ', 'image5.jpg', N'Hoạt động', 'hoangvane', 'matkhau5', 5),
('NV006', N'Nguyễn Thị F', '0906789012', '1985-06-01', 0, N'Hà Nội', 'image6.jfif', N'Hoạt động', 'nguyenthif', 'matkhau6', 6),
('NV007', N'Trần Văn G', '0907890123', '1990-07-01', 1, N'TP Hồ Chí Minh', 'image7.jfif', N'Hoạt động', 'tranvang', 'matkhau7', 7),
('NV008', N'Lê Thị H', '0908901234', '1995-08-01', 0, N'Đà Nẵng', 'image8.jfif', N'Hoạt động', 'lethih', 'matkhau8', 8),
('NV009', N'Phạm Văn I', '0909012345', '2000-09-01', 1, N'Huế', 'image9.jfif', N'Hoạt động', 'phamvani', 'matkhau9', 9),
('NV010', N'Hoàng Thị J', '0910123456', '1980-10-01', 0, N'Cần Thơ', 'image10.jfif', N'Hoạt động', 'hoangthij', 'matkhau10', 10);

-- Thêm dữ liệu vào bảng KhachHang
INSERT INTO KhachHang (MaKhachHang, HoTen, SDT, DiaChi, NgaySinh, GioiTinh, TrangThai) VALUES
('KH001', N'Nguyễn Thị F', '0911234567', N'Hà Nội', '1985-06-01', 0, 1),
('KH002', N'Trần Văn G', '0912345678', N'TP Hồ Chí Minh', '1990-07-01', 1, 1),
('KH003', N'Lê Thị H', '0913456789', N'Đà Nẵng', '1995-08-01', 0, 1),
('KH004', N'Phạm Văn I', '0914567890', N'Huế', '2000-09-01', 1, 1),
('KH005', N'Hoàng Thị J', '0915678901', N'Cần Thơ', '1980-10-01', 0, 1),
('KH006', N'Nguyễn Văn K', '0916789012', N'Hải Phòng', '1985-11-01', 1, 1),
('KH007', N'Trần Thị L', '0917890123', N'Quảng Ninh', '1990-12-01', 0, 1),
('KH008', N'Lê Văn M', '0918901234', N'Thanh Hóa', '1995-01-01', 1, 1),
('KH009', N'Phạm Thị N', '0919012345', N'Nghệ An', '2000-02-01', 0, 1),
('KH010', N'Hoàng Văn O', '0910123456', N'Hà Tĩnh', '1980-03-01', 1, 1);

INSERT INTO Voucher (MaVoucher, GiaTri, NgayBatDau, NgayKetThuc, SoLuong, MoTa, TrangThai) VALUES
('VC001', 10.00, '2023-01-01', '2023-12-31', 100, N'Giảm giá 10%', 1),
('VC002', 20.00, '2023-02-01', '2023-12-31', 200, N'Giảm giá 20%', 1),
('VC003', 15.00, '2023-03-01', '2023-12-31', 150, N'Giảm giá 15%', 1),
('VC004', 25.00, '2023-04-01', '2023-12-31', 250, N'Giảm giá 25%', 1),
('VC005', 30.00, '2023-05-01', '2023-12-31', 300, N'Giảm giá 30%', 1),
('VC006', 10.00, '2023-01-01', '2023-12-31', 100, N'Giảm giá 10%', 1),
('VC007', 20.00, '2023-02-01', '2023-12-31', 200, N'Giảm giá 20%', 1),
('VC008', 15.00, '2023-03-01', '2023-12-31', 150, N'Giảm giá 15%', 1),
('VC009', 25.00, '2023-04-01', '2023-12-31', 250, N'Giảm giá 25%', 1),
('VC0010', 30.00, '2023-05-01', '2023-12-31', 300, N'Giảm giá 30%', 1);

-- Thêm dữ liệu vào bảng HoaDon
INSERT INTO HoaDon (ID_NhanVien, ID_KhachHang, ID_Voucher, MaHoaDon, TongTien, NgayThanhToan, TrangThai, MoTa) VALUES
(1, 1, 1, 'HD001', 1000000.00, '2023-01-01', 1, 'Ghi chú 1'),
(2, 2, 2, 'HD002', 2000000.00, '2023-02-01', 1, 'Ghi chú 2'),
(3, 3, 3, 'HD003', 3000000.00, '2023-03-01', 1, 'Ghi chú 3'),
(4, 4, 4, 'HD004', 4000000.00, '2023-04-01', 1, 'Ghi chú 4'),
(5, 5, 5, 'HD005', 5000000.00, '2023-05-01', 1, 'Ghi chú 5'),
(6, 6, 6, 'HD006', 6000000.00, '2023-06-01', 1, 'Ghi chú 6'),
(7, 7, 7, 'HD007', 7000000.00, '2023-07-01', 1, 'Ghi chú 7'),
(8, 8, 8, 'HD008', 8000000.00, '2023-08-01', 1, 'Ghi chú 8'),
(9, 9, 9, 'HD009', 9000000.00, '2023-09-01', 1, 'Ghi chú 9'),
(10, 10, 10, 'HD010', 10000000.00, '2023-10-01', 1, 'Ghi chú 10');

-- Thêm dữ liệu vào bảng Voucher


-- Thêm dữ liệu vào bảng KichCo
INSERT INTO KichCo (TenKichCo) VALUES
(N'S'),
(N'M'),
(N'L'),
(N'XL'),
(N'XXL');

-- Thêm dữ liệu vào bảng TheLoai
INSERT INTO TheLoai (TenTheLoai) VALUES
(N'Áo thun'),
(N'Áo sơ mi'),
(N'Áo khoác'),
(N'Áo sweater');

-- Thêm dữ liệu vào bảng MauSac
INSERT INTO MauSac (TenMauSac) VALUES
(N'Đỏ'),
(N'Xanh'),
(N'Vàng'),
(N'Trắng'),
(N'Đen');

-- Thêm dữ liệu vào bảng ChatLieu
INSERT INTO ChatLieu (TenChatLieu) VALUES
(N'Cotton'),
(N'Polyester'),
(N'Lụa'),
(N'Len'),
(N'Da');

-- Thêm dữ liệu vào bảng SanPhamChiTiet
INSERT INTO SanPhamChiTiet (ID_TheLoai, ID_ChatLieu, ID_MauSac, ID_KichCo, MaSP, TenSP, Anh, MoTa, DonGia, SoLuong) VALUES
(1, 1, 1, 1, 'SP001', N'Áo thun cơm nắm', 'ao_thun_com_nam.jfif', N'Áo thun chất liệu cotton', 150000.00, 100),
(1, 2, 2, 2, 'SP002', N'Áo kẻ sọc xanh lá', 'ao_ke_soc_xanh_la.jfif', N'Áo chất liệu polyester', 200000.00, 50),
(1, 3, 3, 3, 'SP003', N'Áo thun 1978', 'ao_thun_1978.jfif', N'Họa tiết số 1978', 300000.00, 75),
(1, 4, 4, 4, 'SP004', N'Áo thun hình mèo', 'ao_thun_nau_meo.jfif', N'Họa tiết mèo', 50000.00, 200),
(1, 5, 5, 5, 'SP005', N'Áo thun summer', 'ao_thun_summer.jfif', N'Hình mèo và chữ summer', 250000.00, 30);

-- Thêm dữ liệu vào bảng HoaDonChiTiet
INSERT INTO HoaDonChiTiet (ID_SanPhamChiTiet, ID_HoaDon, SoLuong, DonGia) VALUES
(1, 1, 2, 150000.00),
(2, 2, 1, 200000.00),
(3, 3, 3, 300000.00),
(4, 4, 5, 50000.00),
(5, 5, 1, 250000.00);
