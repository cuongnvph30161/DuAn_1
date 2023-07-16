/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.*;
import domainmodel.BanDomainModel;
import domainmodel.ChiTietHoaDonDomainModel;
import domainmodel.ChiTietSanPhamDomainModel;
import interfacerepositorys.IBanRepository;
import interfacerepositorys.IChiTietHoaDonRepository;
import interfacerepositorys.IChiTietSanPhamRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import utilities.DBConnect;

/**
 *
 * @author ADMIN
 */
public class ChiTietSanPhamRepository implements IChiTietSanPhamRepository {

    static Connection con = null;

    @Override
    public List<ChiTietSanPhamDomainModel> getList() {
        try {
            List<ChiTietSanPhamDomainModel> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "SELECT MaChiTietSanPham,MaSanPham,Size,Gia FROM ChiTietSanPham";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                lst.add(new ChiTietSanPhamDomainModel(rs.getInt(1),
                        rs.getInt(2), rs.getString(3),
                        rs.getBigDecimal(4)));
            }
            return lst;
        } catch (Exception e) {
        }
        return null;

    }

}
