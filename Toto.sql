USE master
GO

DROP DATABASE if exists ToTo
GO
CREATE DATABASE ToTo
GO
USE ToTo
GO
-- tuan anh abc
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
-- duy 123
-- duy an cut
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
GhiChu NVARCHAR(MAX),
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

go
-- Sửa bảng hóa đơn thêm cột chi dịch vụ phát sinh
alter table HoaDon
ADD DichVuPhatSinh money Default 0

go
-- nạp dữ liệu bảng nhân viên
INSERT INTO NhanVien(HoVaTen,NgaySinh,DiaChi,CCCD,TrangThai,Email,SoDienThoai,GhiChu,Anh,ChucVu)
VALUES 
(N'Dương Thanh Tùng','1999-10-10',N'Bắc Giang','22222222222',1,'tungdtph30319@gmail.com','0339306033',N'chăm chỉ',(select * from openrowset (bulk 'D:\SUMMER-2023\1.png', single_blob) as T),N'quản lý'),
(N'Nguyễn Văn Nam','1999-07-10',N'Bắc Ninh','333333333333',0,'tungdtph30319@gmail.com','0339306033',N'chăm chỉ',(select * from openrowset (bulk 'D:\SUMMER-2023\1.png', single_blob) as T),N'nhân viên'),
(N'Nguyễn Văn Duy','1999-07-10',N'Bắc Ninh','333333333333',0,'tungdtph30319@gmail.com','0339306033',N'chăm chỉ',(select * from openrowset (bulk 'D:\SUMMER-2023\1.png', single_blob) as T),N'nhân viên')
go

--nạp dữ liệu bảng tài khoản
INSERT INTO TaiKhoan(MaTaiKhoan,MaNhanVien,MatKhau,VaiTro,TrangThai)
VALUES
('TK01',1000,'admin123',N'QuanLy',1),
('TK02',1001,'admin123',N'PhaChe',1),
('TK03',1002,'admin123',N'NhanVien',1)
go
--nạp dữ liệu bảng sản phẩm
INSERT INTO SanPham(TenSanPham,TrangThai,MoTa,Anh)
VALUES
(N'Trà xoài bưởi hồng',1,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\tra xoai buoi hong.png', single_blob) as T)),
(N'Choco ngũ cốc kem cafe',1,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\choco ngu coc kem ca phe.png', single_blob) as T)),
(N'Hồng trà sữa ngũ cốc kem cafe',1,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\hong tra ngu coc kem cafe.png', single_blob) as T)),
(N'Ô long xoài kem cafe',1,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\O long xoài kem ca phe.jpg', single_blob) as T)),
(N'Tiger sugar',1,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\tiger sugar.jpg', single_blob) as T)),
(N'Trà đào bưởi hồng',1,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\Tra dao buoi hong.jpg', single_blob) as T)),
(N'Trà dứa nhiệt đới',1,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\tra dua nhiet doi.png', single_blob) as T)),
(N'Trà sữa chân châu',1,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\tra sua chan chau.jpg', single_blob) as T)),
(N'Trà sữa Matcha',1,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\tra sua matcha.jpg', single_blob) as T)),
(N'Trà sữa ô long',1,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\tra sua o long.jpg', single_blob) as T)),
(N'Trà sữa panda',1,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\tra sua panda.jpg', single_blob) as T))
SELECT * FROM SanPham
go
--nạp dữ liệu bảng mã giảm giá
INSERT INTO MaGiamGia(PhanTramGiam,HoaDonToiThieu,GiamToiDa,HanSuDung,MaNguoiTao,TrangThai)
VALUES (24,1,40.4,'2022-06-06',1001,1),
(20,1,30.4,'2022-06-06',1001,1),
(10,1,20.4,'2022-06-06',1001,1)
go
--nạp dữ liệu bảng bàn
INSERT INTO Ban(TenBan,Tang,TrangThai)
VALUES (N'bàn 01',1,0),
(N'bàn 02',1,0),
(N'bàn 03',1,0)
go

--nạp dữ liệu hóa đơn
INSERT INTO HoaDon
VALUES(1001,GETDATE(),1,1,1000,N'ít đường'),
(1001,GETDATE(),1,1,1000,N'nhiều đường'),
(1001,GETDATE(),1,1,1000,N'nhiều đá')

go
--nạp dữ liệu bàn-hóa đơn
INSERT INTO Ban_HoaDon(MaHoaDon,MaBan)
VALUES(1000,1000),
(1001,1001)
go
--nạp dữ liệu chi tiết sản phẩm
INSERT INTO ChiTietSanPham(MaSanPham,Size,Gia)
VALUES(1000,'M',45.4),
(1001,'S',45.4)
go
--nạp dữ liệu chi tiết hóa đơn
INSERT INTO ChiTietHoaDon(MaHoaDon,MaChiTietSanPham,SoLuong,Gia)
VALUES(1000,1000,4,44.5)

go
-- Sửa bảng hóa đơn thêm cột chi dịch vụ phát sinh
alter table HoaDon
ADD DichVuPhatSinh money Default 0
go


--1 .NhanVien
-- SELECT MaNhanVien,HoVaTen,NgaySinh,DiaChi,CCCD,TrangThai,Email,SoDienThoai,GhiChu,Anh,ChucVu FROM NhanVien
--INSERT INTO NhanVien(HoVaTen,NgaySinh,DiaChi,CCCD,TrangThai,Email,SoDienThoai,GhiChu,Anh,ChucVu) 
--VALUES(?,?,?,?,?,?,?,?,?,?)
--DELETE FROM NhanVien WHERE 
--UPDATE NhanVien SET ? WHERE ?

--2.TaiKhoan
--SELECT MaTaiKhoan,MaNhanVien,MatKhau,VaiTro,TrangThai FROM TaiKhoan
--INSERT INTO TaiKhoan(MaNhanVien,MatKhau,VaiTro,TrangThai) 
--VALUES(?,?,?,?)
--DELETE FROM TaiKhoan WHERE
--UPDATE TaiKhoan SET =? WHERE =?

--3.SanPham
--SELECT MaSanPham,TenSanPham,TrangThai,MoTa,Anh FROM SanPham
--INSERT INTO SanPham(TenSanPham,TrangThai,MoTa,Anh)
--VALUES(?,?,?,?)
--DELETE FROM SanPham WHERE =?
--UPDATE SanPham SET =? WHERE =?

--4.MaGiamGia
--SELECT MaVoucher,PhanTramGiam,GiamToiDa,HanSuDung,MaNguoiTao,TrangThai FROM MaGiamGia 
--INSERT INTO MaGiamGia(PhanTramGiam,GiamToiDa,HanSuDung,MaNguoiTao,TrangThai)
--VALUES(?,?,?,?,?)
--DELETE FROM MaGiamGia WHERE =?
--UPDATE MaGiamGia SET? WHERE?

--5.Ban
--SELECT MaBan,TenBan,Tang,TrangThai FROM Ban
--INSERT INTO BAN(TenBan,Tang,TrangThai) VALUES(?,?,?)
--DELETE FROM BAN WHERE=?
--UPDATE BAN SET =? WHERE =?

--6.HoaDon
--SELECT MaHoaDon,MaNhanVien,ThoiGian,TrangThaiThanhToan,TrangThaiOrder,MaVoucher FROM HoaDon
--INSERT INTO HoaDon(ThoiGian,TrangThaiThanhToan,TrangThaiOrder,MaVoucher)
--VALUES(?,?,?,?)
--DELETE FROM HoaDon WHERE=ThoiGian=?
--UPDATE HoaDon SET ThoiGian=? WHERE MaHoaDon=?

--7.Ban_HoaDon
--SELECT MaHoaDon,MaBan FROM Ban_HoaDon
--INSERT INTO Ban_HoaDon(MaHoaDon,MaBan) 
--VALUES(?,?)
--DELETE FROM Ban_HoaDon WHERE MaHoaDon=?
--UPDATE Ban_HoaDon SET MaBan WHERE MaHoaDon=?

--8.ChiTietSanPham
--SELECT MaChiTietSanPham,MaSanPham,Size,Gia FROM ChiTietSanPham
--INSERT INTO ChiTietSanPham(MaSanPham,Size,Gia) 
--VALUES(?,?,?) 
--DELETE FROM ChiTietSanPham WHERE MaChiTietSanPham=?
--UPDATE ChiTietSanPham SET MaChiTietSanPham=? WHERE MaChiTietSanPham=?

--9.ChiTietHoaDon
--SELECT MaHoaDon,MaChiTietSanPham,SoLuong,Gia FROM ChiTietHoaDon
--INSERT INTO ChiTietHoaDon(MaHoaDon,MaChiTietSanPham,SoLuong,Gia)
--VALUES(?,?,?,?)
--DELETE FROM ChiTietHoaDon WHERE MaHoaDon=?
--UPDATE ChiTietHoaDon SET MaChiTietSanPham=? WHERE MaHoaDon=?
