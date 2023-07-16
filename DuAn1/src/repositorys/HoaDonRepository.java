/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.*;
import domainmodel.BanDomainModel;
import domainmodel.HoaDonDoMainModel;
import interfacerepositorys.IBanRepository;
import interfacerepositorys.IHoaDonRepository;
import java.util.ArrayList;
import java.util.List;
import utilities.DBConnect;

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
                    + "TrangThaiThanhToan,TrangThaiOrder,MaVoucher FROM HoaDon";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
//            while (rs.next()) {
//                lst.add(new HoaDonDoMainModel(rs.getInt(1),
//                        rs.getInt(2), rs.getTimestamp(3),
//                        rs.getInt(4), rs.getInt(5),
//                        rs.getInt(6)));
//
//            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

}
