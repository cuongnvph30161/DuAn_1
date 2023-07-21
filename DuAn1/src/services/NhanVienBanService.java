/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.ChiTietHoaDonDomainModel;
import domainmodel.HoaDonDoMainModel;
import domainmodel.NhanVien.ChiTietHoaDon;
import interfaceservices.INhanVienBanService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import repositorys.ChiTietHoaDonRepository;
import viewmodel.NhanVienBanViewModel;

/**
 *
 * @author Admin
 */
public class NhanVienBanService implements INhanVienBanService{

    private ChiTietHoaDonRepository cthdRepo = new ChiTietHoaDonRepository();
    double tongTien = 0;

    @Override
    public List<NhanVienBanViewModel> getAllNhanVienBan() {
        List<NhanVienBanViewModel> listNV = new ArrayList<>();
        List<ChiTietHoaDon> list = cthdRepo.getDuLieuBan();
        for (ChiTietHoaDon cthd : list) {
            NhanVienBanViewModel nvbVM = new NhanVienBanViewModel();
            nvbVM.setMaHoaDon(cthd.getMaHoaDon());
            nvbVM.setThoiGian(cthd.getMaHoaDon().getThoiGian());

            tongTien = (cthd.getSoLuong() * cthd.getGia().doubleValue());

            nvbVM.setTrangThaiOrder(cthd.getMaHoaDon().getTrangThaiOrder());
            nvbVM.setTongThanhToan(BigDecimal.valueOf(tongTien));
            listNV.add(nvbVM);
        }
        return listNV;
    }

}
