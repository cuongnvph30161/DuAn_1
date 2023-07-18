/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.BanDomainModel;
import domainmodel.BanHoaDonDomainModel;
import domainmodel.ChiTietHoaDonDomainModel;
import domainmodel.ChiTietSanPhamDomainModel;
import domainmodel.HoaDonDoMainModel;
import domainmodel.NhanVienDomainModel;
import domainmodel.SanPhamDomainModel;

import interfaceservices.INhanVienHoaDonServices;
import interfaceservices.INhanVienService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import repository.iRepository.IBanHoaDonRepository;
import repository.iRepository.IBanRepository;
import repository.iRepository.IChiTietHoaDonRepository;
import repository.iRepository.IChiTietSanPhamRepository;
import repository.iRepository.IHoaDonRepository;
import repository.iRepository.INhanVienRepository;
import repository.iRepository.ISanPhamRepository;
import repositorys.BanHoaDonRepository;
import repositorys.BanRepository;
import repositorys.ChiTietHoaDonRepository;
import repositorys.ChiTietSanPhamRepository;
import repositorys.HoaDonRepository;
import repositorys.NhanVienRepository;
import repositorys.SanPhamRepository;
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
    IChiTietSanPhamRepository chiTietSPRepository = new ChiTietSanPhamRepository();
    ISanPhamRepository SPRepository = new SanPhamRepository();
    IBanHoaDonRepository banHoaDonRepository = new BanHoaDonRepository();

    @Override
    public List<NhanVienHoaDonViewModel> getList(List<PhaCheLichSuDanhSachSanPhamViewmodel> DSSP,
            Map<Integer, String> mapTenNV, Map<Integer, String> mapTenBan, List<ChiTietHoaDonDomainModel> lstCTHD) {

        List<NhanVienHoaDonViewModel> listNVHD = new ArrayList<>();
        List<HoaDonDoMainModel> listHD = hoaDonRepository.getList();
        String tenBan = "";
        String tenNguoiTao = "";
        double tongThanhToan = 0;

        for (HoaDonDoMainModel a : listHD) {
            tenBan = mapTenBan.get(a.getMaHoaDon());
            tenNguoiTao = mapTenNV.get(a.getMaNhanVien());

            for (PhaCheLichSuDanhSachSanPhamViewmodel b : DSSP) {
                if (a.getMaHoaDon() == b.getMaHoaDon()) {
                    for (ChiTietHoaDonDomainModel c : lstCTHD) {
                        if (a.getMaHoaDon() == c.getMaHoaDon()) {
                            tongThanhToan += c.getSoLuong() * c.getGia().doubleValue();
                        }
                    }
                    listNVHD.add(new NhanVienHoaDonViewModel(a.getMaHoaDon(),
                            a.getMaVoucher(),
                            a.getMaNhanVien() + "", tenBan, tenNguoiTao, a.getThoiGian(),
                            BigDecimal.valueOf(tongThanhToan), a.getDichVuPhatSinh(),
                            a.getTrangThaiThanhToan(), a.getGhiChu(), DSSP));

                } else {
                    listNVHD.add(new NhanVienHoaDonViewModel(a.getMaHoaDon(),
                            a.getMaVoucher(),
                            a.getMaNhanVien() + "", tenBan, tenNguoiTao, a.getThoiGian(),
                            BigDecimal.valueOf(0), a.getDichVuPhatSinh(),
                            a.getTrangThaiThanhToan(), a.getGhiChu(), null));

                }
            }
        }

        return listNVHD;
    }

    @Override
    public List<PhaCheLichSuDanhSachSanPhamViewmodel> getDSSP() {

        Map<String, Object> mapDs = new HashMap<>();
        List<ChiTietHoaDonDomainModel> lstCTHD = chiTietHDRepository.getList();
        List<ChiTietSanPhamDomainModel> lstCTSP = chiTietSPRepository.getList();
        List<SanPhamDomainModel> lstSP = SPRepository.getList();

        List<PhaCheLichSuDanhSachSanPhamViewmodel> listdssp = new ArrayList<>();
        for (ChiTietHoaDonDomainModel a : lstCTHD) {
            for (ChiTietSanPhamDomainModel b : lstCTSP) {
                for (SanPhamDomainModel c : lstSP) {
                    if (a.getMaChiTietSanPham() == b.getMaChiTietSanPham()
                            && b.getMaSanPham() == c.getMaSanPham()) {
                        listdssp.add(new PhaCheLichSuDanhSachSanPhamViewmodel(
                                a.getMaHoaDon(), c.getMaSanPham(),
                                c.getTenSanPham(), b.getSize(),
                                a.getSoLuong()));
                    }
                }
            }
        }
        return listdssp;
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
        List<BanHoaDonDomainModel> lstbanhd = banHoaDonRepository.getList();
        for (BanHoaDonDomainModel a : lstbanhd) {
            for (BanDomainModel b : lst) {
                if (a.getMaBan() == b.getMaBan()) {
                    tenBan.put(a.getMaHoaDon(), b.getTenBan());
                }
            }
        }

        return tenBan;
    }

    @Override
    public List<ChiTietHoaDonDomainModel> getlistCTHD() {
        List<ChiTietHoaDonDomainModel> lst = chiTietHDRepository.getList();
        return lst;
    }
}
