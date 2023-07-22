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
import javax.swing.JTextField;
import utilities.DBConnect;

/**
 *
 * @author ADMIN
 */
public class MaGiamGiaRepository implements IMaGiamGiaRepository {

    static Connection con = null;

    @Override
    public List<MaGiamGiaDomainModel> getList() {
        try {
            List<MaGiamGiaDomainModel> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "SELECT MaVoucher,PhanTramGiam,HoaDonToiThieu,GiamToiDa,HanSuDung,MaNguoiTao,TrangThai FROM MaGiamGia";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                lst.add(new MaGiamGiaDomainModel(rs.getInt(1),
                        rs.getInt(2), rs.getInt(3),
                        rs.getBigDecimal(4), rs.getDate(5),
                        rs.getInt(6), rs.getInt(7)));
            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public boolean checkMaGiamGia(JTextField a) {
        try {
            Connection connection = DBConnect.getConnect();
            String query = "select MaVoucher from magiamgia where MaVoucher like ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(a.getText()));
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
}
