/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.*;
import domainmodel.BanDomainModel;
import domainmodel.NhanVien.Ban;
import repositorys.iRepository.IBanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DBConnect;
import utilities.JdbcHelper;

/**
 *
 * @author ADMIN
 */
public class BanRepository implements IBanRepository {

    static Connection con = DBConnect.getConnect();

    @Override
    public BanDomainModel getById(Integer id) {

        try {
            ResultSet rs = JdbcHelper.query("SELECT MaBan,TenBan,Tang,TrangThai FROM Ban where maBan=" + id);
            if (rs.next()) {
                return new BanDomainModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void actives(int maBan, boolean status) {
        if (status) {
            try {
                JdbcHelper.update("Update ban set trangthai = 1 where maBan=?", maBan);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                JdbcHelper.update("Update ban set trangthai = 0 where maBan=?", maBan);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<BanDomainModel> getList() {
        try {
            List<BanDomainModel> lst = new ArrayList<>();
            String lenh = "SELECT MaBan,TenBan,Tang,TrangThai FROM Ban";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                lst.add(new BanDomainModel(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getInt(4)));
            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<Ban> getTang1() {
        try {
            List<Ban> lst = new ArrayList<>();
            String lenh = "select TenBan from Ban where Tang=1";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                Ban b = new Ban();
                b.setTenBan(rs.getString(1));
                lst.add(b);
            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Ban> getTang2() {
        try {
            List<Ban> lst = new ArrayList<>();
            String lenh = "select TenBan from Ban where Tang=2";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                Ban b = new Ban();
                b.setTenBan(rs.getString(1));
                lst.add(b);
            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Ban> getTang3() {
        try {
            List<Ban> lst = new ArrayList<>();
            String lenh = "select TenBan from Ban where Tang=3";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                Ban b = new Ban();
                b.setTenBan(rs.getString(1));
                lst.add(b);
            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Ban> getTang4() {
        try {
            List<Ban> lst = new ArrayList<>();
            String lenh = "select TenBan from Ban where Tang=4";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                Ban b = new Ban();
                b.setTenBan(rs.getString(1));
                lst.add(b);
            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Ban> getTang5() {
        try {
            List<Ban> lst = new ArrayList<>();
            String lenh = "select TenBan from Ban where Tang=5";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                Ban b = new Ban();
                b.setTenBan(rs.getString(1));
                lst.add(b);
            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<BanDomainModel> getAll(int... page) {

        try {
            ResultSet rs = JdbcHelper.query("SELECT MaBan,TenBan,Tang,TrangThai FROM Ban");
            List<BanDomainModel> lstBan = new ArrayList<>();
            while (rs.next()) {
                lstBan.add(new BanDomainModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
            }
            return lstBan;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(BanDomainModel object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(BanDomainModel object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<BanDomainModel> getBySql(String sql, Object... args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer ThemBan(BanDomainModel ban) {
        try {
            String lenh = "INSERT INTO BAN(TenBan,Tang,TrangThai) VALUES(?,?,?)";
            PreparedStatement rs = con.prepareStatement(lenh);
            rs.setString(1, ban.getTenBan());
            rs.setInt(2, ban.getTang());
            rs.setInt(3, 0);
            return rs.executeUpdate();
        } catch (Exception e) {
        }
        return -1;
    }

    @Override
    public Integer CapNhatBan(BanDomainModel ban) {
        try {
            String lenh = "UPDATE BAN SET TenBan=?,Tang=? WHERE MaBan=?";
            PreparedStatement rs = con.prepareStatement(lenh);
            rs.setString(1, ban.getTenBan());
            rs.setInt(2, ban.getTang());
            rs.setInt(3, ban.getMaBan());
            return rs.executeUpdate();
        } catch (Exception e) {
        }
        return -1;

    }

    @Override
    public boolean deleteById(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean CheckTrungTenBan(String ten, int tang) {
        try {
            String lenh = "SELECT TenBan,Tang FROM Ban where TenBan like ? and Tang=?";
            PreparedStatement ps = con.prepareStatement(lenh);
            ps.setString(1, ten);
            ps.setInt(2, tang);
            return ps.executeQuery().next();
        } catch (SQLException ex) {
            return false;
        }
    }
}
