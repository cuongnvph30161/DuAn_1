/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.BanDomainModel;
import domainmodel.HoaDonDoMainModel;

import domainmodel.NhanVienDomainModel;

import interfaceservices.INhanVienHoaDonServices;
import interfaceservices.INhanVienService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import repository.iRepository.IHoaDonRepository;
import java.util.Map;
import repository.iRepository.IBanRepository;
import repository.iRepository.IChiTietHoaDonRepository;
import repository.iRepository.IHoaDonRepository;
import repository.iRepository.INhanVienRepository;
import repositorys.BanRepository;
import repositorys.ChiTietHoaDonRepository;
import repositorys.HoaDonRepository;
import repositorys.NhanVienRepository;
import viewmodel.NhanVienHoaDonViewModel;
import viewmodel.PhaCheLichSuDanhSachSanPhamViewmodel;

/**
 *
 * @author ADMIN
 */

public class NhanVienHoaDonServices implements INhanVienHoaDonServices {

    IHoaDonRepository hoaDonRepository = new HoaDonRepository();
    INhanVienRepository nhanVienRepository = new NhanVienRepository();
    IBanRepository banRepository = new BanRepository();
    IChiTietHoaDonRepository chiTietHDRepository = new ChiTietHoaDonRepository();

    @Override
    public List<NhanVienHoaDonViewModel> getList(List<PhaCheLichSuDanhSachSanPhamViewmodel> DSSP, Map<Integer, String> mapTenNV, Map<Integer, String> mapTenBan, Map<Integer, Object> mapChiTietHD) {

        List<NhanVienHoaDonViewModel> listNVHD = new ArrayList<>();
        List<HoaDonDoMainModel> listHD = hoaDonRepository.getList();

        return listNVHD;
    }

    @Override
    public List<PhaCheLichSuDanhSachSanPhamViewmodel> getDSSP() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<Integer, String> mapTenNV() {
        Map<Integer, String> tenNV = new HashMap<>();
        List<NhanVienDomainModel> lst = nhanVienRepository.getAll();
        for (NhanVienDomainModel a : lst) {
            tenNV.put(a.getMaNhanVien(), a.getHoVaTen());
        }
        return tenNV;
    }

    @Override
    public Map<Integer, String> mapTenBan() {
        Map<Integer, String> tenBan = new HashMap<>();
        List<BanDomainModel> lst = banRepository.getList();
        for (BanDomainModel a : lst) {
            tenBan.put(a.getMaBan(), a.getTenBan());
        }
        return tenBan;
    }

    @Override
    public Map<Integer, Object> mapChiTietHD() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
