/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.*;
import domainmodel.BanDomainModel;
import interfacerepositorys.IBanRepository;
import java.util.ArrayList;
import java.util.List;
import utilities.DBConnect;

/**
 *
 * @author ADMIN
 */
public class BanRepository implements IBanRepository {
    
    static Connection con = null;
    
    @Override
    public List<BanDomainModel> getList() {
        try {
            List<BanDomainModel> lst = new ArrayList<>();
            con = DBConnect.getConnect();
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
}
