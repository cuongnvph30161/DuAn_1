/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaceservices;

import domainmodel.ChiTietHoaDonDomainModel;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import viewmodel.NhanVienHoaDonViewModel;
import viewmodel.PhaCheLichSuDanhSachSanPhamViewmodel;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public interface INhanVienHoaDonServices {

    List<NhanVienHoaDonViewModel> getList(List<PhaCheLichSuDanhSachSanPhamViewmodel> DSSP,
            Map<Integer, String> mapTenNV, Map<Integer, String> mapTenBan
    ,List<ChiTietHoaDonDomainModel> listCTHD,Map<Integer, Object> mapMaGiamGia
    );

    List<PhaCheLichSuDanhSachSanPhamViewmodel> getDSSP();

    Map<Integer, String> mapTenNV();
    Map<Integer, Object> mapMaGiamGia();

    Map<Integer, String> mapTenBan();
    List<ChiTietHoaDonDomainModel> getlistCTHD();
    List<NhanVienHoaDonViewModel> timHD(Date ngayTu,Date ngayDen,int maHoaDon,int trangThai,List<NhanVienHoaDonViewModel> listTim);
}
