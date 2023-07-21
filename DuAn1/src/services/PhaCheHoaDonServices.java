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
import domainmodel.SanPhamDomainModel;
import interfaceservices.IPhaCheLichSuServices;
import repositorys.iRepository.IBanHoaDonRepository;
import repositorys.iRepository.IBanRepository;
import repositorys.iRepository.IChiTietHoaDonRepository;
import repositorys.iRepository.IChiTietSanPhamRepository;
import repositorys.iRepository.IHoaDonRepository;
import repositorys.iRepository.ISanPhamRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import repositorys.BanHoaDonRepository;
import repositorys.BanRepository;
import repositorys.ChiTietHoaDonRepository;
import repositorys.ChiTietSanPhamRepository;
import repositorys.HoaDonRepository;
import repositorys.SanPhamRepository;
import viewmodel.PhaCheLichSuDanhSachSanPhamViewmodel;
import viewmodel.PhaCheLichSuViewModel;

/**
 *
 * @author ADMIN
 */
public class PhaCheHoaDonServices implements IPhaCheLichSuServices {

    IHoaDonRepository hoaDonRepository = new HoaDonRepository();
    IBanRepository banRepository = new BanRepository();
    IBanHoaDonRepository banHoaDonRepository = new BanHoaDonRepository();
    IChiTietHoaDonRepository chiTietHoaDonRepository = new ChiTietHoaDonRepository();
    IChiTietSanPhamRepository chiTietSPRepository = new ChiTietSanPhamRepository();
    ISanPhamRepository sanPhamRepository = new SanPhamRepository();
    BanDomainModel ban = new BanDomainModel();
    HoaDonDoMainModel hoaDon = new HoaDonDoMainModel();

    @Override
    public List<PhaCheLichSuViewModel> getList(Map<String, Object> mapBan,
            Map<String, Object> mapHoaDon, List<PhaCheLichSuDanhSachSanPhamViewmodel> DSSP) {
        List<PhaCheLichSuViewModel> listLichSu = new ArrayList();
        List<BanHoaDonDomainModel> listBanHoaDon = banHoaDonRepository.getList();

        for (BanHoaDonDomainModel a : listBanHoaDon) {
            ban = (BanDomainModel) mapBan.get(a.getMaBan() + "");
            hoaDon = (HoaDonDoMainModel) mapHoaDon.get(a.getMaHoaDon() + "");
            List<PhaCheLichSuDanhSachSanPhamViewmodel> listDSSP = new ArrayList<>();
            for (PhaCheLichSuDanhSachSanPhamViewmodel b : DSSP) {
                if (a.getMaHoaDon() == b.getMaHoaDon()) {
                    listDSSP.add(b);
                }
            }
            if (hoaDon.getTrangThaiOrder()== 0) {
                listLichSu.add(new PhaCheLichSuViewModel(a.getMaHoaDon(),
                        ban.getTenBan(), ban.getTang(),
                        hoaDon.getThoiGian(), hoaDon.getGhiChu(),
                        listDSSP));
            }

        }
        return listLichSu;
    }

    @Override
    public Map<String, Object> getBan() {
        List<BanDomainModel> listBanDomain = banRepository.getList();
        Map<String, Object> map = new HashMap<>();
        for (BanDomainModel a : listBanDomain) {
            //System.out.println(a.getMaBan());
            map.put(a.getMaBan() + "", a);
        }
        return map;
    }

    @Override
    public Map<String, Object> getHoaDon() {
        List<HoaDonDoMainModel> listHoaDonDomain = hoaDonRepository.getList();
        Map<String, Object> map = new HashMap<>();
        for (HoaDonDoMainModel a : listHoaDonDomain) {
            map.put(a.getMaHoaDon() + "", a);
        }
        return map;
    }

    @Override
    public List<PhaCheLichSuDanhSachSanPhamViewmodel> getDSSP() {
        Map<String, Object> mapDs = new HashMap<>();
        List<ChiTietHoaDonDomainModel> lstCTHD = chiTietHoaDonRepository.getList();
        List<ChiTietSanPhamDomainModel> lstCTSP = chiTietSPRepository.getList();
        List<SanPhamDomainModel> lstSP = sanPhamRepository.getList();

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
    public Integer capNhatTrangThai(int maHD, int trangThai) {
       return hoaDonRepository.capNhatTrangThai(maHD, trangThai);
    
    }
}
