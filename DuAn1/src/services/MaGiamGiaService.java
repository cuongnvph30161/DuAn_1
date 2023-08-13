/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.MaGiamGiaDomainModel;
import domainmodel.NhanVienDomainModel;
import interfaceservices.IMaGiamGiaService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import repositorys.MaGiamGiaRepository;
import repositorys.NhanVienRepository;
import repositorys.iRepository.IMaGiamGiaRepository;
import repositorys.iRepository.INhanVienRepository;
import viewmodel.MaGiamGiaViewModel;
import viewmodel.NhanVienViewModel;
import viewmodel.defaultViewModel.HoaDonViewModel;

/**
 *
 * @author Admin
 */
public class MaGiamGiaService implements IMaGiamGiaService {

    private IMaGiamGiaRepository mggRepo = new MaGiamGiaRepository();
    public IMaGiamGiaRepository iMaGiamGiaRepository = new MaGiamGiaRepository();
    public INhanVienRepository iNhanVienRepository = new NhanVienRepository();

    @Override
    public boolean checkMaGiamGia(int a) {
        return mggRepo.checkMaGiamGia(a);
    }

    @Override
    public Integer applyVoucher(int voucher, int tongThanhToan) {
        return mggRepo.applyVoucher(voucher, tongThanhToan);
    }

    @Override
    public String[] getLstID() {
        return mggRepo.getLstID();
    }

    @Override
    public boolean checkMaGiamGia(Integer a) {
        return mggRepo.checkMaGiamGia(a);
    }

    @Override
    public Integer phanTramGiamGia(Integer b) {
        return mggRepo.phanTramGiamGia(b);
    }

    @Override
    public ArrayList<MaGiamGiaViewModel> getListMaGiamGia() {
        ArrayList<MaGiamGiaDomainModel> maGiamGiaDomainModel = iMaGiamGiaRepository.getListMaGiamGia();
        ArrayList<MaGiamGiaViewModel> list = new ArrayList<>();
        for (MaGiamGiaDomainModel mgg : maGiamGiaDomainModel) {
            MaGiamGiaViewModel maGiamGiaViewModel = new MaGiamGiaViewModel();
            maGiamGiaViewModel.setMaVoucher(mgg.getMaVoucher());
            maGiamGiaViewModel.setPhanTramGiam(mgg.getPhanTramGiam());
            maGiamGiaViewModel.setDonToiThieu(mgg.getDonToiThieu());
            maGiamGiaViewModel.setGiamToiDa(mgg.getGiamToiDa());
            maGiamGiaViewModel.setSoLuong(mgg.getSoLuong());
            maGiamGiaViewModel.setMaNguoiTao(mgg.getMaNguoiTao());
            NhanVienDomainModel nhanVien = iNhanVienRepository.getNhanVienById(mgg.getMaNguoiTao());
            String hoTen = nhanVien.getHoVaTen();
            maGiamGiaViewModel.setHoTen(hoTen);
            maGiamGiaViewModel.setNgayBatDau(mgg.getNgayBatDau());
            maGiamGiaViewModel.setNgayKetThuc(mgg.getNgayKetThuc());
            list.add(maGiamGiaViewModel);
        }
        return list;
    }

    @Override
    public String insertMaGiamGia(MaGiamGiaViewModel maGiamGiaViewModel) {
        MaGiamGiaDomainModel maGiamGiaDomainModel = new MaGiamGiaDomainModel();
        maGiamGiaDomainModel.setPhanTramGiam(maGiamGiaViewModel.getPhanTramGiam());
        maGiamGiaDomainModel.setDonToiThieu(maGiamGiaViewModel.getDonToiThieu());
        maGiamGiaDomainModel.setGiamToiDa(maGiamGiaViewModel.getGiamToiDa());
        maGiamGiaDomainModel.setSoLuong(maGiamGiaViewModel.getSoLuong());
        maGiamGiaDomainModel.setMaNguoiTao(maGiamGiaViewModel.getMaNguoiTao());
        maGiamGiaDomainModel.setNgayBatDau(maGiamGiaViewModel.getNgayBatDau());
        maGiamGiaDomainModel.setNgayKetThuc(maGiamGiaViewModel.getNgayKetThuc());
        if (iMaGiamGiaRepository.insertMaGiamGia(maGiamGiaDomainModel)) {
            return "Thêm mã giảm giá thành công";
        } else {
            return "Thêm mã giảm giá thất bại";
        }

    }

    @Override
    public String updateMaGiamGiaSoLuong(int maVouCher, MaGiamGiaViewModel maGiamGiaViewModel) {
        MaGiamGiaDomainModel maGiamGiaDomainModel = new MaGiamGiaDomainModel();

        maGiamGiaDomainModel.setMaVoucher(maGiamGiaViewModel.getMaVoucher());
        maGiamGiaDomainModel.setSoLuong(maGiamGiaViewModel.getSoLuong());

        if (iMaGiamGiaRepository.updateMaGiamGiaSoLuong(maVouCher, maGiamGiaDomainModel)) {
            return "Thu hồi mã giảm giá thành công";
        } else {
            return "Thu hồi mã giảm giá thất bại";
        }

    }

    @Override
    public ArrayList<MaGiamGiaViewModel> getFindHoaDonToiThieu(int hoaDonToiThieuByTimKiem) {
        ArrayList<MaGiamGiaDomainModel> maGiamGiaDomainModel = iMaGiamGiaRepository.findMaGiamGiaByHoaDonToiThieu(hoaDonToiThieuByTimKiem);
        ArrayList<MaGiamGiaViewModel> list = new ArrayList<>();
        for (MaGiamGiaDomainModel mgg : maGiamGiaDomainModel) {
            MaGiamGiaViewModel maGiamGiaViewModel = new MaGiamGiaViewModel();
            maGiamGiaViewModel.setMaVoucher(mgg.getMaVoucher());
            maGiamGiaViewModel.setPhanTramGiam(mgg.getPhanTramGiam());
            maGiamGiaViewModel.setDonToiThieu(mgg.getDonToiThieu());
            maGiamGiaViewModel.setGiamToiDa(mgg.getGiamToiDa());
            maGiamGiaViewModel.setSoLuong(mgg.getSoLuong());
            maGiamGiaViewModel.setMaNguoiTao(mgg.getMaNguoiTao());
            NhanVienDomainModel nhanVien = iNhanVienRepository.getNhanVienById(mgg.getMaNguoiTao());
            String hoTen = nhanVien.getHoVaTen();
            maGiamGiaViewModel.setHoTen(hoTen);
            maGiamGiaViewModel.setNgayBatDau(mgg.getNgayBatDau());
            maGiamGiaViewModel.setNgayKetThuc(mgg.getNgayKetThuc());
            list.add(maGiamGiaViewModel);
        }
        return list;
    }

}
