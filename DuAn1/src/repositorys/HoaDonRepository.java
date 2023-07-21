/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.*;
import domainmodel.BanDomainModel;
import domainmodel.HoaDonDoMainModel;
import repositorys.iRepository.IBanRepository;
import repositorys.iRepository.IHoaDonRepository;

import java.util.ArrayList;
import java.util.List;
import utilities.DBConnect;
import utilities.JdbcHelper;

/**
 *
 * @author ADMIN
 */
public class HoaDonRepository implements IHoaDonRepository {

    static Connection con = null;

    @Override
    public List<HoaDonDoMainModel> getList() {
        try {
            List<HoaDonDoMainModel> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "SELECT MaHoaDon,MaNhanVien,ThoiGian,"
                    + "TrangThaiThanhToan,TrangThaiOrder,MaVoucher,GhiChu FROM HoaDon";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                lst.add(new HoaDonDoMainModel(rs.getInt(1), rs.getInt(1), rs.getTimestamp(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6), rs.getString(7)));

            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<HoaDonDoMainModel> getAll(int... page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HoaDonDoMainModel getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean insert(HoaDonDoMainModel object) {
        String querry = "INSERT INTO [dbo].[HoaDon]  ([MaHoaDon],[MaNhanVien] ,[MaVoucher] ,[GhiChu] ,[DichVuPhatSinh])"
                + "VALUES (?,?,?,?,?)";

        return JdbcHelper.update(querry, object.getMaHoaDon(), object.getMaNhanVien(),
                 object.getMaVoucher() == 0 ? null : object.getMaVoucher(), object.getGhiChu(), object.getDichVuPhatSinh()) == 1;
    }

    @Override
    public boolean update(HoaDonDoMainModel object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<HoaDonDoMainModel> getBySql(String sql, Object... args) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getLastId() {
        return (int) JdbcHelper.value("Select top 1 MaHoaDon from HoaDon order by MaHoaDon desc");
    }

    @Override
    public Integer capNhatTrangThai(int maHD, int trangThai) {
        try {
            String lenh = "UPDATE HoaDon SET TrangThaiOrder=? WHERE MaHoaDon=?";
            PreparedStatement st = con.prepareStatement(lenh);
            st.setInt(1, trangThai);

            st.setInt(2, maHD);
            return st.executeUpdate();
        } catch (Exception e) {
        }
        return -1;
    }
}