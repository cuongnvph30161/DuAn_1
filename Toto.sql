go
USE master
go
DROP DATABASE if exists ToTo
GO
CREATE DATABASE ToTo
GO
USE ToTo
GO

CREATE TABLE NhanVien (
MaNhanVien INT IDENTITY(1000,1) PRIMARY KEY,
HoVaTen NVARCHAR(40) NOT NULL,
NgaySinh DATE NOT NULL,
DiaChi NVARCHAR(100) NOT NULL,
CCCD VARCHAR(12) UNIQUE NOT NULL,
TrangThai INT NOT NULL,
Email VARCHAR(50) UNIQUE NOT NULL,
SoDienThoai VARCHAR(15) UNIQUE NOT NULL,
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
MaVoucher INT IDENTITY(80293992,949) PRIMARY KEY,
PhanTramGiam INT NOT NULL ,
HoaDonToiThieu INT NOT NULL  ,
GiamToiDa DECIMAL(19,4) NOT NULL ,
SoLuong INT NOT NULL ,
MaNguoiTao INT NOT NULL,
NgayBatDau Date NOT NULL DEFAULT GETDATE(),
NgayKetThuc Date NOT NULL,
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
MaHoaDon INT  PRIMARY KEY,
MaNhanVien INT NOT NULL,
ThoiGian DATETIME DEFAULT GETDATE(),
TrangThaiThanhToan INT DEFAULT 0,
TrangThaiOrder INT DEFAULT 0,
MaVoucher INT,
GhiChu NVARCHAR(MAX),
DichVuPhatSinh money DEFAULT 0
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


-- nạp dữ liệu bảng nhân viên
INSERT INTO NhanVien(HoVaTen,NgaySinh,DiaChi,CCCD,TrangThai,Email,SoDienThoai,GhiChu,Anh,ChucVu)
VALUES 
(N'Dương Thanh Tùng','1999-10-10',N'Bắc Giang','22222222222',1,'tung@gmail.com','0339306033',N'chăm chỉ',(select * from openrowset (bulk 'D:\Anh tra sua\choco ngu coc kem ca phe.png', single_blob) as T),N'Quản lý'),
(N'Nguyễn Văn Nam','1999-07-10',N'Bắc Ninh','3333533333',0,'nam@gmail.com','03393061033',N'chăm chỉ',(select * from openrowset (bulk 'D:\Anh tra sua\choco ngu coc kem ca phe.png', single_blob) as T),N'Nhân viên'),
(N'Nguyễn Văn Duy','1999-07-10',N'Bắc Ninh','33333333',0,'duy@gmail.com','03393026033',N'chăm chỉ',(select * from openrowset (bulk 'D:\Anh tra sua\choco ngu coc kem ca phe.png', single_blob) as T),N'Nhân viên')
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
(N'Trà sữa Matcha',2,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\tra sua matcha.jpg', single_blob) as T)),
(N'Trà sữa ô long',1,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\tra sua o long.jpg', single_blob) as T)),
(N'Trà sữa panda',0,N'thơm ngon',(select * from openrowset (bulk 'D:\Anh tra sua\tra sua panda.jpg', single_blob) as T))
go
select MaSanPham,TenSanPham,TrangThai,MoTa,Anh from SanPham where TenSanPham like N'%'
--nạp dữ liệu bảng mã giảm giá
INSERT INTO MaGiamGia(PhanTramGiam,HoaDonToiThieu,GiamToiDa,NgayBatDau,NgayKetThuc,MaNguoiTao,SoLuong)
VALUES (20,1,40000.4,'2022-06-06','2022-06-06',1001,100),
(20,1,300000.4,'2022-06-06','2024-06-06',1001,10),
(10,1,20000.4,'2022-06-06','2025-06-06',1001,1000)
go
--nạp dữ liệu bảng bàn
INSERT INTO Ban(TenBan,Tang,TrangThai)
VALUES (N'1',5,0),
	   (N'2',5,1),
	   (N'3',5,0)
go
select * from Ban
--nạp dữ liệu hóa đơn
INSERT INTO HoaDon
VALUES(1000,1001,GETDATE(),0,1,80293992,N'ít đường',33.3),
(1001,1001,GETDATE(),1,0,80294941,N'nhiều đường',33.3),
(1002,1001,GETDATE(),1,0,80295890,N'nhiều đá',33.3),
(1003,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1004,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1005,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1006,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1007,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1008,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1009,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1010,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1011,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1012,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1013,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1014,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1015,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1016,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1017,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1018,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1019,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1020,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1021,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1022,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1023,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1024,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1025,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1026,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1027,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1028,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1029,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1030,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1031,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1032,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1033,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1034,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1035,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1036,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1037,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1038,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1039,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1040,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1041,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1042,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1043,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1044,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1045,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1046,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1047,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1048,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1049,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1050,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1051,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1052,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1053,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1054,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1055,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1056,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1057,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1058,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1059,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1060,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1061,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1062,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1063,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1064,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1065,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1066,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1067,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1068,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1069,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1070,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1071,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1072,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1073,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1074,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1075,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1076,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1077,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1078,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1079,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1080,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1081,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1082,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1083,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1084,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1085,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1086,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1087,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1088,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1089,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1090,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1091,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1092,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1093,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1094,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1095,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1096,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1097,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1098,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1099,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1100,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1101,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1102,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1103,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1104,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1105,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1106,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1107,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1108,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1109,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1110,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1111,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1112,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1113,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1114,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1115,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1116,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1117,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1118,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1119,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1120,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1121,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1122,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1123,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1124,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1125,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1126,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1127,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1128,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1129,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1130,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1131,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1132,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1133,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1134,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1135,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1136,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1137,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1138,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1139,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1140,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1141,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1142,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1143,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1144,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1145,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1146,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1147,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1148,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1149,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1150,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1151,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1152,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1153,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1154,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1155,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1156,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1157,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1158,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1159,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1160,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1161,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1162,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1163,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1164,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1165,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1166,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1167,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1168,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1169,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1170,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1171,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1172,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1173,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1174,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1175,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1176,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1177,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1178,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1179,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1180,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1181,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1182,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1183,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1184,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1185,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1186,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1187,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1188,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1189,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1190,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1191,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1192,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1193,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1194,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1195,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1196,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1197,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1198,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1199,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1200,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1201,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1202,1001,GETDATE(),0,1,80293992,N'it duong',33.3),
(1203,1001,GETDATE(),0,1,80293992,N'it duong',33.3)
go
select * from HoaDon
--nạp dữ liệu bàn-hóa đơn
INSERT INTO Ban_HoaDon(MaHoaDon,MaBan)
VALUES(1003,1000),
(1001,1001),
(1002,1000)
go
--nạp dữ liệu chi tiết sản phẩm
INSERT INTO ChiTietSanPham(MaSanPham,Size,Gia)
VALUES(1000,'M',45.4),
(1001,'S',45.4)
go
--nạp dữ liệu chi tiết hóa đơn
INSERT INTO ChiTietHoaDon(MaHoaDon,MaChiTietSanPham,SoLuong,Gia)
VALUES(1000,1001,4,44.5),
(1003,1001,2,22.25),
(1001,1000,4,44.5),
(1002,1000,5,44.5),
(1002,1001,3,44.5)
go
select * from ChiTietHoaDon

-- Sửa bảng hóa đơn thêm cột chi dịch vụ phát sinh
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
--SELECT MaVoucher,PhanTramGiam,HoaDonToiThieu,GiamToiDa,HanSuDung,MaNguoiTao,TrangThai FROM MaGiamGia 
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
select * from HoaDon
update HoaDon set TrangThaiOrder=0 where MaHoaDon=1002
select * from SanPham
select * from ChiTietSanPham
select * from HoaDon
select * from ChiTietHoaDon

