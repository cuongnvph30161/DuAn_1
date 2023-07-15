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
import utilities.DbConnector;
import interfacerepositorys.ITaiKhoanRepositorys;

/**
 *
 * @author Admin
 */
public class TaiKhoanRepositorys implements ITaiKhoanRepositorys {

    private static Connection connection = null;

    static {
        try {
            connection = DbConnector.getConnection();
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

}
