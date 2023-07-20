/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.ChiTietHoaDonDomainModel;
import domainmodel.HoaDonDoMainModel;
import java.util.ArrayList;
import java.util.List;
import repositorys.ChiTietHoaDonRepository;
import viewmodel.NhanVienBanViewModel;

/**
 *
 * @author Admin
 */
public class NhanVienBanService {
    
    private ChiTietHoaDonRepository cthdRepo = new ChiTietHoaDonRepository();
     
    
    public List<NhanVienBanViewModel> getAllNhanVienBan() {
        List<NhanVienBanViewModel> listNV = new ArrayList<>();
        List<ChiTietHoaDonDomainModel> list = cthdRepo.getAllNhanVienBan();
        
        for (ChiTietHoaDonDomainModel cthd : list) {
            NhanVienBanViewModel nvbVM = new NhanVienBanViewModel();
            HoaDonDoMainModel hd = new HoaDonDoMainModel();
            if (cthd.getMaHoaDon() == hd.getMaHoaDon()) {
                nvbVM.setMaHoaDon(cthd.getMaHoaDon());
                nvbVM.setThoiGian(hd.getThoiGian());
                nvbVM.setSoLuong(cthd.getSoLuong());
                nvbVM.setGia(cthd.getGia());
                nvbVM.setTrangThaiOrder(hd.getTrangThaiOrder());
//                nvbVM.setChiTiet("");
                listNV.add(nvbVM);
            }
        }
        return listNV;
        
    }
    
}
