/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import domainmodel.Role;
import domainmodel.TaiKhoanDomail;
import repositorys.iRepository.ITaiKhoanRepositorys;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import utilities.DBConnect;

/**
 *
 * @author Admin
 */
public class TaiKhoanRepositorys implements ITaiKhoanRepositorys {

    private static Connection connection = null;

    static {
        try {
            connection = DBConnect.getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public TaiKhoanDomail getTaiKhoanByMaTaiKhoanAndMatKhau(String maTaiKhoan, String matKhau) {
        try {
            String query = "select * from TaiKhoan where MaTaiKhoan =? and MatKhau =? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, maTaiKhoan);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiKhoanDomail taiKhoan = new TaiKhoanDomail();
                taiKhoan.setMaTaiKhoan(rs.getString("MaTaiKhoan"));
                taiKhoan.setMatKhau(rs.getString("MatKhau"));
                taiKhoan.setRole(Role.valueOf(rs.getString("VaiTro")));
                taiKhoan.setTrangThai(rs.getInt("TrangThai"));
                return taiKhoan;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public ArrayList<TaiKhoanDomail> getListTaiKhoan() {
        ArrayList<TaiKhoanDomail> getList = new ArrayList<>();
        try {
            String query = "select * from TaiKhoan";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maTaiKhoan = rs.getString("MaTaiKhoan");
                int maNhanVien = rs.getInt("MaNhanVien");
                String matKhau = rs.getString("MatKhau");
                Role role = Role.valueOf(rs.getString("VaiTro"));
                int trangThai = rs.getInt("TrangThai");
                TaiKhoanDomail taiKhoanDomail = new TaiKhoanDomail();
                taiKhoanDomail.setMaTaiKhoan(maTaiKhoan);
                taiKhoanDomail.setMaNhanVien(maNhanVien);
                taiKhoanDomail.setMatKhau(matKhau);
                taiKhoanDomail.setRole(role);
                taiKhoanDomail.setTrangThai(trangThai);
                getList.add(taiKhoanDomail);
            }
            return getList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateMatKhauByMaNhanVien(String newPassWord, int maNhanVien) {
        try {
            String query = "update TaiKhoan set MatKhau =? where MaNhanVien =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newPassWord);
            ps.setInt(2, maNhanVien);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String checkTaiKhoan(String maTaiKhoan) {

        try {
            String query = "select MaTaiKhoan from TaiKhoan where maTaiKhoan =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, maTaiKhoan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("MaTaiKhoan");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean doiMatKhau(String matKhau, String maTaiKhoan) {
        try {

            String query = "update TaiKhoan set MatKhau = ? where MaTaiKhoan = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, matKhau);
            ps.setString(2, maTaiKhoan);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String checkMatKhau(String maTaiKhoan) {
        try {
            String query = "select MatKhau from TaiKhoan where MaTaiKhoan =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, maTaiKhoan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("MatKhau");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TaiKhoanDomail> getAll(int... page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TaiKhoanDomail getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean insert(TaiKhoanDomail object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(TaiKhoanDomail object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<TaiKhoanDomail> getBySql(String sql, Object... args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean insertTaiKhoan(TaiKhoanDomail taiKhoanDomail) {
        try {
            String query = "insert into TaiKhoan values(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, taiKhoanDomail.getMaTaiKhoan());
            ps.setInt(2, taiKhoanDomail.getMaNhanVien());
            ps.setString(3, taiKhoanDomail.getMatKhau());
            ps.setString(4, taiKhoanDomail.getRole().toString());
            ps.setInt(5, taiKhoanDomail.getTrangThai());
            ps.executeUpdate();
            System.out.println("repo" + " " + taiKhoanDomail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTaiKhoan(String maTaiKhoan, TaiKhoanDomail taiKhoanDomail) {
        try {
            String query = "update TaiKhoan set MaNhanVien =?,MatKhau =?,VaiTro =?,TrangThai =? where MaTaiKhoan =? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, taiKhoanDomail.getMaNhanVien());
            ps.setString(2, taiKhoanDomail.getMatKhau());
            ps.setString(3, taiKhoanDomail.getRole().toString());
            ps.setInt(4, taiKhoanDomail.getTrangThai());
            ps.setString(5, maTaiKhoan);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    @Override
//    public String getMaTaiKhoanByMaTaiKhoan(String maTaiKhoan) {
//        try {
//            String query = "SELECT MaTaiKhoan FROM TaiKhoan WHERE MaTaiKhoan = ?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, maTaiKhoan);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return rs.getString("MaTaiKhoan");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public int getMaNhanVienByMaNhanVien(int maNhanVien) {
//        try {
//            String query = "SELECT MaNhanVien FROM TaiKhoan WHERE MaNhanVien = ?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setInt(1, maNhanVien);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return rs.getInt("MaNhanVien");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
    @Override
    public TaiKhoanDomail getTaiKhoanByMa(String maTK) {
        try {
            String query = "SELECT * FROM TaiKhoan WHERE MaTaiKhoan = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, maTK);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TaiKhoanDomail taiKhoanDomail = new TaiKhoanDomail();
                String maTaiKhoan = rs.getString("MaTaiKhoan");
                int maNhanVien = rs.getInt("MaNhanVien");
                String matKhau = rs.getString("MatKhau");
                Role role = Role.valueOf(rs.getString("VaiTro"));
                int trangThai = rs.getInt("TrangThai");
                taiKhoanDomail.setMaTaiKhoan(maTaiKhoan);
                taiKhoanDomail.setMaNhanVien(maNhanVien);
                taiKhoanDomail.setMatKhau(matKhau);
                taiKhoanDomail.setRole(role);
                taiKhoanDomail.setTrangThai(trangThai);
                System.out.println("repo matk" + " " + taiKhoanDomail);
                return taiKhoanDomail;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean isMaTaiKhoanTrung(String maTk, String maTaiKhoanHienTai) {
        try {
            String query = "SELECT COUNT(*) FROM TaiKhoan WHERE maTaiKhoan = ? AND maTaiKhoan <> ?";
            try ( PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, maTk);
                statement.setString(2, maTaiKhoanHienTai);
                try ( ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        long count = resultSet.getLong(1);
                        return count > 0;
                    }
                }
            }
        } catch (Exception e) {
            // Xử lý exception nếu cần thiết
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getEmailByMaTaiKhoan(String maTaiKhoan) {
        try {
            String query = "select Email from NhanVien\n"
                    + "inner join TaiKhoan on NhanVien.MaNhanVien = TaiKhoan.MaNhanVien\n"
                    + "where MaTaiKhoan =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, maTaiKhoan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Email");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<TaiKhoanDomail> getListTaiKhoanByMa(String maTK) {
        ArrayList<TaiKhoanDomail> getList = new ArrayList<>();
        try {
            String query = "select * from TaiKhoan where MaTaiKhoan LIKE ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + maTK + "%"); // Truyền tham số vào câu truy vấn
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maTaiKhoan = rs.getString("MaTaiKhoan");
                int maNhanVien = rs.getInt("MaNhanVien");
                String matKhau = rs.getString("MatKhau");
                Role role = Role.valueOf(rs.getString("VaiTro"));
                int trangThai = rs.getInt("TrangThai");
                TaiKhoanDomail taiKhoanDomail = new TaiKhoanDomail();
                taiKhoanDomail.setMaTaiKhoan(maTaiKhoan);
                taiKhoanDomail.setMaNhanVien(maNhanVien);
                taiKhoanDomail.setMatKhau(matKhau);
                taiKhoanDomail.setRole(role);
                taiKhoanDomail.setTrangThai(trangThai);
                getList.add(taiKhoanDomail);
            }
            return getList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getMaNhanVienByMaTaiKhoan(String maTaiKhoan) {
        try {
            String query = " select MaNhanVien from TaiKhoan where MaTaiKhoan =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, maTaiKhoan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("MaNhanVien");
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

}
