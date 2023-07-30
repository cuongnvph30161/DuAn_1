/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys.iRepository;

import com.toedter.calendar.JDateChooser;
import domainmodel.BanDomainModel;
import domainmodel.HoaDonDoMainModel;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IHoaDonRepository extends DAO<Integer,HoaDonDoMainModel>{
        List<HoaDonDoMainModel> getList();
        Integer capNhatTrangThai(int maHD,int trangThai);
        
        ///Quản lý hóa đơn (vai tò quản lý)
        double TongHoaDonQLHD(int maHoaDon);
        
        List<HoaDonDoMainModel> getListQLHDTheoMaHD(int maHoaDon);
        List<BanDomainModel> getBanQLHD(int maHoaDon);
        List<HoaDonDoMainModel> TimKiemQLHoaDon(int maHoaDon);
}
