/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.HoaDonDoMainModel;
import interfacerepositorys.IHoaDonRepository;
import interfaceservices.INhanVienHoaDonServices;
import interfaceservices.INhanVienService;
import java.util.ArrayList;
import java.util.List;
import repositorys.HoaDonRepository;
import viewmodel.NhanVienHoaDonViewModel;
import viewmodel.PhaCheLichSuDanhSachSanPhamViewmodel;

/**
 *
 * @author ADMIN
 */
public class NhanVienHoaDonServices implements INhanVienHoaDonServices{
 IHoaDonRepository hoaDonRepository = new HoaDonRepository();
    @Override
    public List<NhanVienHoaDonViewModel> getList(List<PhaCheLichSuDanhSachSanPhamViewmodel> DSSP) {
        List<NhanVienHoaDonViewModel> listNVHD=new ArrayList<>();
        List<HoaDonDoMainModel> listHD=hoaDonRepository.getList();
        
        return listNVHD;
    }

    @Override
    public List<PhaCheLichSuDanhSachSanPhamViewmodel> getDSSP() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
