/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.Statement;
import java.util.List;

import domainmodel.NhanVienDomainModel;
import repositorys.iRepository.INhanVienRepository;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Date;
import utilities.DBConnect;
import utilities.JdbcHelper;

/**
 *
 * @author Admin
 */
public class NhanVienRepository implements INhanVienRepository {

    private static Connection connection = null;

    static {
        try {
            connection = DBConnect.getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
	public int getByIdAccount(String idAccount) {
		return (int) JdbcHelper.value("select NhanVien.MaNhanVien from NhanVien right join TaiKhoan on TaiKhoan.MaNhanVien=NhanVien.MaNhanVien where MaTaiKhoan=?", idAccount);
	}
    @Override
    public int getMaNhanVienByEmail(String email) {
        int maNhanVien = 0;
        try {
            String query = "select MaNhanVien from NhanVien  where email =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maNhanVien = rs.getInt("MaNhanVien");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maNhanVien;
    }

    @Override
    public String checkEmail(String email) {
        try {
            String query = "select Email from NhanVien  where email = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Email");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NhanVienDomainModel> getAll(int... page) {
        try {
            List<NhanVienDomainModel> lst = new ArrayList<>();
            connection = DBConnect.getConnect();
            String lenh = "SELECT MaNhanVien,HoVaTen,NgaySinh,DiaChi,CCCD,"
                    + "TrangThai,Email,SoDienThoai,GhiChu,Anh,ChucVu FROM NhanVien";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                lst.add(new NhanVienDomainModel(rs.getInt(1),
                        rs.getString(2), rs.getDate(3),
                        rs.getString(4), rs.getString(5),
                        rs.getInt(6), rs.getString(7),
                        rs.getString(8), rs.getString(9),
                        rs.getBlob(10), rs.getString(11)));
            }
            return lst;
        } catch (Exception e) {
        }
        return null;

    }

    @Override
    public NhanVienDomainModel getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean insert(NhanVienDomainModel nhanVienDomainModel) {
        //
        return false;

    }

    @Override
    public boolean update(NhanVienDomainModel object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<NhanVienDomainModel> getBySql(String sql, Object... args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<NhanVienDomainModel> getAll() {
        ArrayList<NhanVienDomainModel> getList = new ArrayList<>();
        try {
            String query = "select * from NhanVien";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maNhanVien = rs.getInt("MaNhanVien");
                String hoVaTen = rs.getString("HoVaTen");
                Date ngaySinh = rs.getDate("NgaySinh");
                String diaChi = rs.getString("DiaChi");
                String cccd = rs.getString("CCCD");
                int trangThai = rs.getInt("TrangThai");
                String email = rs.getString("Email");
                String soDienThoai = rs.getString("SoDienThoai");
                String ghiChu = rs.getString("GhiChu");
                Blob anh = rs.getBlob("Anh");
                String chucVu = rs.getString("ChucVu");
                NhanVienDomainModel nhanVienDomainModel = new NhanVienDomainModel(maNhanVien, hoVaTen, ngaySinh, diaChi, cccd, trangThai, email, soDienThoai, ghiChu, anh, chucVu);
                getList.add(nhanVienDomainModel);
            }
            return getList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insertNhanVien(NhanVienDomainModel nhanVienDomainModel) {
        try {
            String query = "insert into NhanVien values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nhanVienDomainModel.getHoVaTen());
            ps.setDate(2, nhanVienDomainModel.getNgaySinh());
            ps.setString(3, nhanVienDomainModel.getDiaChi());
            ps.setString(4, nhanVienDomainModel.getCCCD());
            ps.setInt(5, nhanVienDomainModel.getTrangThai());
            ps.setString(6, nhanVienDomainModel.getEmail());
            ps.setString(7, nhanVienDomainModel.getSoDienThoai());
            ps.setString(8, nhanVienDomainModel.getGhiChu());
            ps.setBlob(9, nhanVienDomainModel.getAnh());
            ps.setString(10, nhanVienDomainModel.getChucVu());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public NhanVienDomainModel loadMouseClick(int maNhanVien) {
        NhanVienDomainModel nhanVienDomainModel = null; // Khởi tạo đối tượng ngoài vòng lặp

        try {
            String query = "select Anh,NgaySinh,DiaChi,TrangThai,GhiChu from NhanVien where MaNhanVien =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Blob anh = rs.getBlob("Anh");
                Date ngaySinh = rs.getDate("NgaySinh");
                String diaChi = rs.getString("DiaChi");
                int trangThai = rs.getInt("TrangThai");
                String ghiChu = rs.getString("GhiChu");
                nhanVienDomainModel = new NhanVienDomainModel();
                nhanVienDomainModel.setAnh(anh);
                nhanVienDomainModel.setNgaySinh(ngaySinh);
                nhanVienDomainModel.setDiaChi(diaChi);
                nhanVienDomainModel.setTrangThai(trangThai);
                nhanVienDomainModel.setGhiChu(ghiChu);
            }

            return nhanVienDomainModel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(int maNhanVien, NhanVienDomainModel nhanVienDomainModel) {
        try {
            String query = "update NhanVien set HoVaTen = ?,NgaySinh = ?,DiaChi= ?,CCCD= ?,TrangThai= ?,Email=?,SoDienThoai= ?,GhiChu= ?,Anh= ?,ChucVu= ? where MaNhanVien =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nhanVienDomainModel.getHoVaTen());
            ps.setDate(2, new Date(nhanVienDomainModel.getNgaySinh().getTime()));
            ps.setString(3, nhanVienDomainModel.getDiaChi());
            ps.setString(4, nhanVienDomainModel.getCCCD());
            ps.setInt(5, nhanVienDomainModel.getTrangThai());
            ps.setString(6, nhanVienDomainModel.getEmail());
            ps.setString(7, nhanVienDomainModel.getSoDienThoai());
            ps.setString(8, nhanVienDomainModel.getGhiChu());
            ps.setBlob(9, nhanVienDomainModel.getAnh());
            ps.setString(10, nhanVienDomainModel.getChucVu());
            ps.setInt(11, maNhanVien);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkTrungEmail(String email) {
        try {
            String query = "select Email from NhanVien where Email =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public NhanVienDomainModel getNhanVienById(int maNhanVien) {

        NhanVienDomainModel nhanVienDomainModel = null; // Khởi tạo đối tượng rỗng

        try {
            String query = "SELECT * FROM NhanVien WHERE MaNhanVien = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, maNhanVien);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nhanVienDomainModel = new NhanVienDomainModel(); // Khởi tạo đối tượng NhanVienDomainModel mới
                nhanVienDomainModel.setMaNhanVien(rs.getInt("MaNhanVien"));
                nhanVienDomainModel.setHoVaTen(rs.getString("HoVaTen"));
                nhanVienDomainModel.setNgaySinh(rs.getDate("NgaySinh"));
                nhanVienDomainModel.setDiaChi(rs.getString("DiaChi"));
                nhanVienDomainModel.setCCCD(rs.getString("CCCD"));
                nhanVienDomainModel.setTrangThai(rs.getInt("TrangThai"));
                nhanVienDomainModel.setEmail(rs.getString("Email"));
                nhanVienDomainModel.setSoDienThoai(rs.getString("SoDienThoai"));
                nhanVienDomainModel.setGhiChu(rs.getString("GhiChu"));
                nhanVienDomainModel.setAnh(rs.getBlob("Anh"));
                nhanVienDomainModel.setChucVu(rs.getString("ChucVu"));
            }
        } catch (Exception e) {
            return null;
        }

        return nhanVienDomainModel;
    }

    @Override
    public ArrayList<NhanVienDomainModel> getNhanVienByTen(String ten) {
        ArrayList<NhanVienDomainModel> getList = new ArrayList<>();
        try {
            String query = "select MaNhanVien ,HoVaTen,CCCD,SoDienThoai,Email,ChucVu from NhanVien where HoVaTen LIKE ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + ten + "%"); // Truyền tham số vào câu truy vấn
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maNhanVien = rs.getInt("MaNhanVien");
                String hoVaTen = rs.getString("HoVaTen");
                String cccd = rs.getString("CCCD");
                String sdt = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                String chucVu = rs.getString("ChucVu");
                NhanVienDomainModel nhanVienDomainModel = new NhanVienDomainModel();
                nhanVienDomainModel.setMaNhanVien(maNhanVien);
                nhanVienDomainModel.setHoVaTen(hoVaTen);
                nhanVienDomainModel.setCCCD(cccd);
                nhanVienDomainModel.setEmail(email);
                nhanVienDomainModel.setSoDienThoai(sdt);
                nhanVienDomainModel.setChucVu(chucVu);
                getList.add(nhanVienDomainModel);
            }
            return getList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<NhanVienDomainModel> getNhanVienByTrangThai(int trangThai) {
        ArrayList<NhanVienDomainModel> getList = new ArrayList<>();
        try {
            String query = "select MaNhanVien ,HoVaTen,CCCD,SoDienThoai,Email,ChucVu from NhanVien where TrangThai =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, trangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maNhanVien = rs.getInt("MaNhanVien");
                String hoVaTen = rs.getString("HoVaTen");
                String cccd = rs.getString("CCCD");
                String sdt = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                String chucVu = rs.getString("ChucVu");
                NhanVienDomainModel nhanVienDomainModel = new NhanVienDomainModel();
                nhanVienDomainModel.setMaNhanVien(maNhanVien);
                nhanVienDomainModel.setHoVaTen(hoVaTen);
                nhanVienDomainModel.setCCCD(cccd);
                nhanVienDomainModel.setEmail(email);
                nhanVienDomainModel.setSoDienThoai(sdt);
                nhanVienDomainModel.setChucVu(chucVu);
                getList.add(nhanVienDomainModel);
            }
            return getList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean getNhanVienByCCCD(String cccd) {
        try {
            String query = "select CCCD from NhanVien where CCCD =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, cccd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getSoDienThoaiBySDT(String sdt) {
        try {
            String query = "select SoDienThoai from NhanVien where SoDienThoai =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(sdt);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
