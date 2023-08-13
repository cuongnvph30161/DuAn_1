/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import domainmodel.MaGiamGiaDomainModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import repositorys.iRepository.IMaGiamGiaRepository;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import utilities.DBConnect;
import utilities.JdbcHelper;

/**
 *
 * @author ADMIN
 */
public class MaGiamGiaRepository implements IMaGiamGiaRepository {

    static Connection con = null;

    static {
        try {
            con = DBConnect.getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] getLstID() {
        List<String> lst = new ArrayList<>();
        lst.add("");
        try {

            ResultSet rs = JdbcHelper.query("Select maVoucher from MaGiamGia");
            while (rs.next()) {
                lst.add(rs.getString(1));
            }
            return lst.toArray(new String[0]);
        } catch (Exception e) {
        }
        return new String[]{""};
    }

    @Override
    public Integer applyVoucher(int idVoucher, int tongThanhToan) {
        String querry = "select PhanTramGiam,HoaDonToiThieu,GiamToiDa from MaGiamGia where (getDate() between NgayBatDau and NgayKetThuc) and MaVoucher=? and soLuong>1";
        try {
            ResultSet rs = JdbcHelper.query(querry, idVoucher);
            if (rs.next()) {
                var phanTramGiam = rs.getInt(1);
                var hoaDonToiThieu = rs.getInt(2);
                var giamToiDa = rs.getInt(3);
                var soTienGiam = tongThanhToan < hoaDonToiThieu ? 0 : tongThanhToan * phanTramGiam / 100 > giamToiDa ? giamToiDa : tongThanhToan * phanTramGiam / 100;
                return tongThanhToan - soTienGiam;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Mã giảm giá đã được sử dụng hoặc hết hạn !");
        return tongThanhToan;

    }

    public Integer getReducedAmount(int idVoucher, int tongThanhToan) {
        String querry = "select PhanTramGiam,HoaDonToiThieu,GiamToiDa from MaGiamGia where (getDate() between NgayBatDau and NgayKetThuc) and MaVoucher=? and soLuong>1";
        try {
            ResultSet rs = JdbcHelper.query(querry, idVoucher);
            if (rs.next()) {
                var phanTramGiam = rs.getInt(1);
                var hoaDonToiThieu = rs.getInt(2);
                var giamToiDa = rs.getInt(3);
                var soTienGiam = tongThanhToan < hoaDonToiThieu ? 0 : tongThanhToan * phanTramGiam / 100 > giamToiDa ? giamToiDa : tongThanhToan * phanTramGiam / 100;
                return -soTienGiam;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;

    }

    @Override
    public List<MaGiamGiaDomainModel> getList() {
        try {
            List<MaGiamGiaDomainModel> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "SELECT MaVoucher,PhanTramGiam,HoaDonToiThieu,GiamToiDa,"
                    + "NgayBatDau,NgayKetThuc,MaNguoiTao,SoLuong FROM MaGiamGia";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                lst.add(new MaGiamGiaDomainModel(rs.getInt(1),
                        rs.getInt(2), rs.getInt(3),
                        rs.getBigDecimal(4), rs.getDate(5),
                        rs.getDate(6), rs.getInt(7), rs.getInt(8)));

            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public boolean checkMaGiamGia(int a) {
        try {
            Connection connection = DBConnect.getConnect();
            String query = "select MaVoucher from magiamgia where MaVoucher =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, a);
            return ps.executeQuery().next();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Integer phanTramGiamGia(Integer b) {

        Integer p = 0;
        try {
            Connection connection = DBConnect.getConnect();
            String query = "select phanTramGiam from magiamgia where MaVoucher like ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, b);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                p = rs.getInt("phanTramGiam");

            }

            return p;
        } catch (Exception ex) {
            return -1;
        }
    }

    @Override
    public ArrayList<MaGiamGiaDomainModel> getListMaGiamGia() {
        ArrayList<MaGiamGiaDomainModel> getList = new ArrayList<>();
        try {
            String query = "select * from MaGiamGia";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maGiamGia = rs.getInt("MaVoucher");
                int phanTramGiam = rs.getInt("PhanTramGiam");
                int hoaDonToiThieu = rs.getInt("HoaDonToiThieu");
                BigDecimal giamToiDa = rs.getBigDecimal("GiamToiDa");
                int soLuong = rs.getInt("SoLuong");
                int maNguoiTao = rs.getInt("MaNguoiTao");
                Date ngayBatDau = rs.getDate("NgayBatDau");
                Date ngayKetThuc = rs.getDate("NgayKetThuc");
                MaGiamGiaDomainModel maGiamGiaDomainModel = new MaGiamGiaDomainModel(maGiamGia, phanTramGiam, hoaDonToiThieu, giamToiDa, ngayBatDau, ngayKetThuc, maNguoiTao, soLuong);

                getList.add(maGiamGiaDomainModel);
            }
            return getList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insertMaGiamGia(MaGiamGiaDomainModel maGiamGiaDomainModel) {
        try {
            String query = "insert into MaGiamGia(PhanTramGiam,HoaDonToiThieu,GiamToiDa,SoLuong,MaNguoiTao,NgayBatDau,NgayKetThuc) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, maGiamGiaDomainModel.getPhanTramGiam());
            ps.setInt(2, maGiamGiaDomainModel.getDonToiThieu());
            ps.setBigDecimal(3, maGiamGiaDomainModel.getGiamToiDa());
            ps.setInt(4, maGiamGiaDomainModel.getSoLuong());
            ps.setInt(5, maGiamGiaDomainModel.getMaNguoiTao());
            ps.setDate(6, maGiamGiaDomainModel.getNgayBatDau());
            ps.setDate(7, maGiamGiaDomainModel.getNgayKetThuc());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateMaGiamGiaSoLuong(int maVouCher, MaGiamGiaDomainModel maGiamGiaDomainModel) {
        try {
            String query = "update MaGiamGia set SoLuong = ? where MaVoucher = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, maGiamGiaDomainModel.getSoLuong());
            ps.setInt(2, maVouCher);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<MaGiamGiaDomainModel> findMaGiamGiaByHoaDonToiThieu(int hoaDonToiThieuByTimKiem) {
        ArrayList<MaGiamGiaDomainModel> getList = new ArrayList<>();

        try {
            String query = "select * from MaGiamGia where HoaDonToiThieu = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, hoaDonToiThieuByTimKiem);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int maGiamGia = rs.getInt("MaVoucher");
                int phanTramGiam = rs.getInt("PhanTramGiam");
                int hoaDonToiThieu = rs.getInt("HoaDonToiThieu");
                BigDecimal giamToiDa = rs.getBigDecimal("GiamToiDa");
                int soLuong = rs.getInt("SoLuong");
                int maNguoiTao = rs.getInt("MaNguoiTao");
                Date ngayBatDau = rs.getDate("NgayBatDau");
                Date ngayKetThuc = rs.getDate("NgayKetThuc");
                MaGiamGiaDomainModel maGiamGiaDomainModel = new MaGiamGiaDomainModel(maGiamGia, phanTramGiam, hoaDonToiThieu, giamToiDa, ngayBatDau, ngayKetThuc, maNguoiTao, soLuong);

                getList.add(maGiamGiaDomainModel);
            }
            return getList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkMaGiamGia(Integer a) {
        try {
            Connection connection = DBConnect.getConnect();
            String query = "select MaVoucher from magiamgia where MaVoucher =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, a);
            return ps.executeQuery().next();
        } catch (Exception ex) {
            return false;
        }
    }

}
