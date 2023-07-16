/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.*;
import domainmodel.BanDomainModel;
import domainmodel.BanHoaDonDomainModel;
import interfacerepositorys.IBanHoaDonRepository;
import interfacerepositorys.IBanRepository;
import java.util.ArrayList;
import java.util.List;
import utilities.DBConnect;

/**
 *
 * @author ADMIN
 */
public class BanHoaDonRepository implements IBanHoaDonRepository {
    
    static Connection con = null;

    @Override
    public List<BanHoaDonDomainModel> getList() {
     try {
            List<BanHoaDonDomainModel> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "SELECT MaHoaDon,MaBan FROM Ban_HoaDon";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
               lst.add(new BanHoaDonDomainModel(rs.getInt(1),
                       rs.getInt(2)));
            }
            return lst;
        } catch (Exception e) {
        }
        return null;   
    }
   
}
