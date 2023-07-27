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
import domainmodel.MaGiamGiaDomainModel;

import domainmodel.NhanVienDomainModel;
import domainmodel.SanPhamDomainModel;

import interfaceservices.INhanVienHoaDonServices;
import interfaceservices.INhanVienService;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import repositorys.iRepository.IHoaDonRepository;
import java.util.Map;
import repositorys.iRepository.IBanHoaDonRepository;
import repositorys.iRepository.IBanRepository;
import repositorys.iRepository.IChiTietHoaDonRepository;
import repositorys.iRepository.IChiTietSanPhamRepository;
import repositorys.iRepository.IHoaDonRepository;
import repositorys.iRepository.INhanVienRepository;
import repositorys.iRepository.ISanPhamRepository;
import repositorys.BanHoaDonRepository;
import repositorys.BanRepository;
import repositorys.ChiTietHoaDonRepository;
import repositorys.ChiTietSanPhamRepository;
import repositorys.HoaDonRepository;
import repositorys.NhanVienRepository;
import repositorys.SanPhamRepository;
import viewmodel.NhanVienHoaDonViewModel;
import viewmodel.PhaCheLichSuDanhSachSanPhamViewmodel;
import java.sql.Date;
import java.text.SimpleDateFormat;
import repositorys.MaGiamGiaRepository;
import repositorys.iRepository.IMaGiamGiaRepository;

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
    IMaGiamGiaRepository maGGRepository = new MaGiamGiaRepository();

    @Override
    public List<NhanVienHoaDonViewModel> getList(List<PhaCheLichSuDanhSachSanPhamViewmodel> DSSP,
            Map<Integer, String> mapTenNV, Map<Integer, String> mapTenBan,
            List<ChiTietHoaDonDomainModel> lstCTHD, Map<Integer, Object> mapMaGiamGia) {

        List<NhanVienHoaDonViewModel> listNVHD = new ArrayList<>();
        List<HoaDonDoMainModel> listHD = hoaDonRepository.getList();
        String tenBan = "";
        String tenNguoiTao = "";

        for (HoaDonDoMainModel a : listHD) {
            tenBan = mapTenBan.get(a.getMaHoaDon());
            tenNguoiTao = mapTenNV.get(a.getMaNhanVien());
            List<PhaCheLichSuDanhSachSanPhamViewmodel> listDSSP = new ArrayList<>();
            for (PhaCheLichSuDanhSachSanPhamViewmodel b : DSSP) {
                if (a.getMaHoaDon() == b.getMaHoaDon()) {
                    listDSSP.add(b);
                }
            }
            double tongThanhToan = 0;
            double tienGiamTheoMa = 0;
            double tienChuaGiam = 0;
            double giamToiDa = 0;
            int phanTramGiam = 0;
            for (ChiTietHoaDonDomainModel c : lstCTHD) {
                if (a.getMaHoaDon() == c.getMaHoaDon()) {
                    tienChuaGiam += (c.getSoLuong() * c.getGia().doubleValue());
                }
            }
            if (a.getMaVoucher() != 0) {
                MaGiamGiaDomainModel maGiamGia = (MaGiamGiaDomainModel) mapMaGiamGia.get(a.getMaVoucher());

                giamToiDa = maGiamGia.getGiamToiDa().doubleValue();
                phanTramGiam = maGiamGia.getPhanTramGiam();

                tienGiamTheoMa = tienChuaGiam / 100 * phanTramGiam;
            }

            if (tienGiamTheoMa <= giamToiDa) {
                tongThanhToan = tienChuaGiam - tienGiamTheoMa + a.getDichVuPhatSinh().doubleValue();
            } else {
                tongThanhToan = tienChuaGiam - giamToiDa + a.getDichVuPhatSinh().doubleValue();
            }
            listNVHD.add(new NhanVienHoaDonViewModel(a.getMaHoaDon(),
                    a.getMaVoucher(), a.getMaNhanVien() + "", tenBan,
                    tenNguoiTao, a.getThoiGian(), BigDecimal.valueOf(tongThanhToan),
                    a.getDichVuPhatSinh(), phanTramGiam, giamToiDa,
                    tienChuaGiam, a.getTrangThaiThanhToan(), a.getGhiChu(), listDSSP));
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
                                a.getSoLuong(), a.getGia()));
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

    @Override
    public List<NhanVienHoaDonViewModel> timHD(java.util.Date ngayTu, java.util.Date ngayDen, int maHoaDon, int trangThai, List<NhanVienHoaDonViewModel> listTim) {
        try {
            List<NhanVienHoaDonViewModel> list = new ArrayList<>();
            for (NhanVienHoaDonViewModel a : listTim) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String thoiGian = a.getThoiGian() + "";
                java.util.Date TG = df.parse(thoiGian);
                if ((TG.compareTo(ngayTu) >= 0 && TG.compareTo(ngayDen) <= 0) && a.getTrangThai() == trangThai && Integer.parseInt(a.getMaNguoiTao()) == maHoaDon) {

                    list.add(a);

                }
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Map<Integer, Object> mapMaGiamGia() {
        Map<Integer, Object> maGiamGia = new HashMap<>();
        List<MaGiamGiaDomainModel> lst = maGGRepository.getList();
        for (MaGiamGiaDomainModel a : lst) {
            maGiamGia.put(a.getMaVoucher(), a);
        }
        return maGiamGia;

    }
}
