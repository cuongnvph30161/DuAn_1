/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaceservices;


import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import viewmodel.NhanVienHoaDonViewModel;
import viewmodel.QuanLyHoaDonViewModel;
import viewmodel.VaiTroQuanLyBanViewModel;

/**
 *
 * @author Admin
 */
public interface IHoaDonService {
    List<QuanLyHoaDonViewModel> getListQLHD();
    double TongHoaDonQLHD(int maHoaDon);
    
    List<QuanLyHoaDonViewModel> getListQLHDTheoMaHD(int maHoaDon);
    List<VaiTroQuanLyBanViewModel> getBanQLHD(int maHoaDon);
    List<QuanLyHoaDonViewModel> TimKiemQLHoaDon(int maHoaDon);
    List<NhanVienHoaDonViewModel> TimKiemQLHoaDonTheoNgay(Date ngay,List<NhanVienHoaDonViewModel> listTim);
}
