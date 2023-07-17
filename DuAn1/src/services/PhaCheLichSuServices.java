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
import interfacerepositorys.IBanHoaDonRepository;
import interfacerepositorys.IBanRepository;
import interfacerepositorys.IChiTietHoaDonRepository;
import interfacerepositorys.IChiTietSanPhamRepository;
import interfacerepositorys.IHoaDonRepository;
import interfacerepositorys.ISanPhamRepository;
import interfaceservices.IPhaCheLichSuServices;
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
public class PhaCheLichSuServices implements IPhaCheLichSuServices {

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
            if (hoaDon.getTrangThaiThanhToan() == 1) {
                for (PhaCheLichSuDanhSachSanPhamViewmodel b : DSSP) {
                    if (b.getMaHoaDon() == a.getMaHoaDon()) {
                        listLichSu.add(new PhaCheLichSuViewModel(a.getMaHoaDon(),
                                ban.getTenBan(), ban.getTang(),
                                hoaDon.getThoiGian(), hoaDon.getGhiChu(),
                                DSSP));

                    } else {
                        listLichSu.add(new PhaCheLichSuViewModel(a.getMaHoaDon(),
                                ban.getTenBan(), ban.getTang(),
                                hoaDon.getThoiGian(), hoaDon.getGhiChu(),
                                null));
                    }
                }
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
}
