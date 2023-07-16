/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.*;
import domainmodel.BanDomainModel;
import domainmodel.ChiTietHoaDonDomainModel;
import interfacerepositorys.IBanRepository;
import interfacerepositorys.IChiTietHoaDonRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import utilities.DBConnect;

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
    
}