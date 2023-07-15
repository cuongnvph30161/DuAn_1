<<<<<<< HEAD
﻿
CREATE DATABASE ToTo
=======

create database ToTo
>>>>>>> 8ede6b8d9c02bb4e1da77ef70f528ac573499940
GO
USE ToTo
GO

CREATE TABLE NhanVien (
MaNhanVien INT IDENTITY(1000,1) PRIMARY KEY,
HoVaTen NVARCHAR(40) NOT NULL,
NgaySinh DATE NOT NULL,
DiaChi NVARCHAR(100) NOT NULL,
CCCD VARCHAR(12) NOT NULL,
TrangThai INT NOT NULL,
Email VARCHAR(50) NOT NULL,
SoDienThoai VARCHAR(15) NOT NULL,
GhiChu NVARCHAR(1000),
Anh VARBINARY(MAX),
ChucVu NVARCHAR(100) NOT NULL
);


GO

CREATE TABLE TaiKhoan (
MaTaiKhoan VARCHAR(20) PRIMARY KEY,
MaNhanVien INT UNIQUE NOT NULL,
MatKhau VARCHAR(255) NOT NULL,
VaiTro NVARCHAR(100) NOT NULL,
TrangThai INT NOT NULL,
FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);



GO

CREATE TABLE SanPham (
MaSanPham INT IDENTITY(1000,1) PRIMARY KEY,
TenSanPham NVARCHAR(70) NOT NULL,
TrangThai INT NOT NULL,
MoTa NVARCHAR(200),
Anh VARBINARY(MAX)
);

GO

CREATE TABLE MaGiamGia(
MaVoucher INT IDENTITY(1000, 1) PRIMARY KEY,
PhanTramGiam INT NOT NULL DEFAULT 0,
HoaDonToiThieu INT NOT NULL  DEFAULT 0,
GiamToiDa DECIMAL(19,4) NOT NULL  DEFAULT 0,
HanSuDung DATE NOT NULL,
MaNguoiTao INT NOT NULL,
TrangThai INT NOT NULL,
FOREIGN KEY (MaNguoiTao) REFERENCES NhanVien(MaNhanVien)
);

GO

CREATE TABLE Ban(
MaBan INT IDENTITY(1000, 1) PRIMARY KEY,
TenBan NVARCHAR(100) NOT NULL,
Tang INT NOT NULL,
TrangThai INT NOT NULL
);

GO

CREATE TABLE HoaDon (
MaHoaDon INT IDENTITY(1000, 1) PRIMARY KEY,
MaNhanVien INT NOT NULL,
ThoiGian DATETIME NOT NULL,
TrangThaiThanhToan INT NOT NULL,
TrangThaiOrder INT NOT NULL,
MaVoucher INT,
FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
FOREIGN KEY (MaVoucher) REFERENCES MaGiamGia(MaVoucher)
);

GO

CREATE TABLE Ban_HoaDon(
MaHoaDon INT NOT NULL,
MaBan INT NOT NULL,
PRIMARY KEY (MaHoaDon,MaBan),
FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
FOREIGN KEY (MaBan) REFERENCES Ban(MaBan)
);

GO
CREATE TABLE ChiTietSanPham(
MaChiTietSanPham INT IDENTITY(1000,1) PRIMARY KEY,
MaSanPham INT NOT NULL,
Size VARCHAR(15) NOT NULL,
Gia DECIMAL(19,4) NOT NULL,
FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)
);

GO

CREATE TABLE ChiTietHoaDon(
MaHoaDon INT NOT NULL,
MaChiTietSanPham INT NOT NULL,
SoLuong INT NOT NULL,
Gia DECIMAL(19,4) NOT NULL,
PRIMARY KEY (MaHoaDon,MaChiTietSanPham),
FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
FOREIGN KEY (MaChiTietSanPham) REFERENCES ChiTietSanPham(MaChiTietSanPham)
);

-- nạp dữ liệu bảng nhân viên
INSERT INTO NhanVien(HoVaTen,NgaySinh,DiaChi,CCCD,TrangThai,Email,SoDienThoai,GhiChu,Anh,ChucVu)
VALUES 
(N'Dương Thanh Tùng','1999-10-10',N'Bắc Giang','22222222222',1,'tungdtph30319@gmail.com','0339306033',N'chăm chỉ',(select * from openrowset (bulk 'D:\SUMMER-2023\1.png', single_blob) as T),N'quản lý'),
(N'Nguyễn Văn Nam','1999-07-10',N'Bắc Ninh','333333333333',0,'tungdtph30319@gmail.com','0339306033',N'chăm chỉ',(select * from openrowset (bulk 'D:\SUMMER-2023\1.png', single_blob) as T),N'nhân viên'),
(N'Nguyễn Văn Duy','1999-07-10',N'Bắc Ninh','333333333333',0,'tungdtph30319@gmail.com','0339306033',N'chăm chỉ',(select * from openrowset (bulk 'D:\SUMMER-2023\1.png', single_blob) as T),N'nhân viên')

--nạp dữ liệu bảng tài khoản
INSERT INTO TaiKhoan(MaTaiKhoan,MaNhanVien,MatKhau,VaiTro,TrangThai)
VALUES
('TK01',1001,'admin123',N'quản lý',1),
('TK02',1002,'admin123',N'thu ngân',1),
('TK03',1003,'admin123',N'pha chế',1)

--nạp dữ liệu bảng sản phẩm
INSERT INTO SanPham(TenSanPham,TrangThai,MoTa,Anh)
VALUES
(N'Hồng trà sữa',1,N'thơm ngon',(select * from openrowset (bulk 'D:\SUMMER-2023\1.png', single_blob) as T)),
(N'Bạc sỉu',1,N'thơm ngon',(select * from openrowset (bulk 'D:\SUMMER-2023\1.png', single_blob) as T)),
(N'Tranh đào',1,N'thơm ngon',(select * from openrowset (bulk 'D:\SUMMER-2023\1.png', single_blob) as T))