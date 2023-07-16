/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import domainmodel.Role;
import domainmodel.TaiKhoanDomail;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import interfacerepositorys.ITaiKhoanRepositorys;
import java.util.ArrayList;
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
                Role role = Role.valueOf(rs.getString("role"));
                int trangThai = rs.getInt("TrangThai");
                TaiKhoanDomail taiKhoanDomail = new TaiKhoanDomail(maTaiKhoan, maNhanVien, matKhau, role, trangThai);
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
}
