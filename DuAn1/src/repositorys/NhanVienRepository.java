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
            String query = "insert into NhanVien values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, nhanVienDomainModel.getMaNhanVien());
            ps.setString(2, nhanVienDomainModel.getHoVaTen());
            ps.setDate(3, nhanVienDomainModel.getNgaySinh());
            ps.setString(4, nhanVienDomainModel.getDiaChi());
            ps.setString(5, nhanVienDomainModel.getCCCD());
            ps.setInt(6, nhanVienDomainModel.getTrangThai());
            ps.setString(7, nhanVienDomainModel.getEmail());
            ps.setString(8, nhanVienDomainModel.getSoDienThoai());
            ps.setString(9, nhanVienDomainModel.getGhiChu());
            ps.setBlob(10, nhanVienDomainModel.getAnh());
            ps.setString(11, nhanVienDomainModel.getChucVu());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
