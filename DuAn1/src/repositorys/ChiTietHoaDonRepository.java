/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.*;
import domainmodel.BanDomainModel;
import domainmodel.ChiTietHoaDonDomainModel;
import domainmodel.HoaDonDoMainModel;
import repositorys.iRepository.IBanRepository;
import repositorys.iRepository.IChiTietHoaDonRepository;

import java.util.ArrayList;
import java.util.List;
import utilities.DBConnect;
import utilities.JdbcHelper;

/**
 *
 * @author ADMIN
 */
public class ChiTietHoaDonRepository implements IChiTietHoaDonRepository {

    static Connection con = null;

    @Override
    public List<ChiTietHoaDonDomainModel> getList() {
        try {
            List<ChiTietHoaDonDomainModel> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "SELECT MaHoaDon,MaChiTietSanPham,SoLuong,Gia FROM ChiTietHoaDon";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                lst.add(new ChiTietHoaDonDomainModel(rs.getInt(1),
                        rs.getInt(2), rs.getInt(3),
                        rs.getBigDecimal(4)));

            }
            return lst;
        } catch (Exception e) {
        }
        return null;

    }

    public List<ChiTietHoaDonDomainModel> getAllNhanVienBan() {
        try {
            List<ChiTietHoaDonDomainModel> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "select ChiTietHoaDon.MaHoaDon,HoaDon.ThoiGian,ChiTietHoaDon.SoLuong,ChiTietHoaDon.Gia,HoaDon.TrangThaiOrder from ChiTietHoaDon \n"
                    + "inner join HoaDon on ChiTietHoaDon.MaHoaDon=HoaDon.MaHoaDon";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {

                ChiTietHoaDonDomainModel cthd = new ChiTietHoaDonDomainModel();
                cthd.setMaHoaDon(rs.getInt(1));
                HoaDonDoMainModel hd = new HoaDonDoMainModel();

                hd.setThoiGian(rs.getTimestamp(2));
                cthd.setMaHoaDon(hd.getMaHoaDon());
                cthd.setSoLuong(rs.getInt(3));
                cthd.setGia(rs.getBigDecimal(4));
                hd.setTrangThaiOrder(rs.getInt(5));
                lst.add(cthd);
                System.out.println("du lieu" + lst);

            }
            return lst;
        } catch (Exception e) {
        }
        return null;

    }

    @Override
    public List<ChiTietHoaDonDomainModel> getAll(int... page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChiTietHoaDonDomainModel getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean insert(ChiTietHoaDonDomainModel object) {
        String querry="INSERT  INTO  ChiTietHoaDon(MaHoaDon, MaChiTietSanPham, SoLuong, Gia) VALUES    (?,?,?,?)";
        return JdbcHelper.update(querry,object.getMaHoaDon(),object.getMaChiTietSanPham(),object.getSoLuong(),object.getGia())==1;
    }

    @Override
    public boolean update(ChiTietHoaDonDomainModel object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<ChiTietHoaDonDomainModel> getBySql(String sql, Object... args) {
        // TODO Auto-generated method stub
        return null;
    }

}
