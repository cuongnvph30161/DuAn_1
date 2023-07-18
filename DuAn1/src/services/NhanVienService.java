/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.NhanVienDomainModel;
import interfaceservices.INhanVienService;
import java.util.ArrayList;
import repository.iRepository.INhanVienRepository;
import repositorys.NhanVienRepository;
import viewmodel.NhanVienViewModel;

/**
 *
 * @author Admin
 */
public class NhanVienService implements INhanVienService {

    public INhanVienRepository iNhanVienRepository = new NhanVienRepository();

    @Override
    public int getMaNhanVienByEmail(String email) {
        return iNhanVienRepository.getMaNhanVienByEmail(email);
    }

    @Override
    public String checkEmail(String email) {
        return iNhanVienRepository.checkEmail(email);
    }

    @Override
    public ArrayList<NhanVienViewModel> getAll() {
        ArrayList<NhanVienDomainModel> listNhanVienDomainModels = iNhanVienRepository.getAll();
        ArrayList<NhanVienViewModel> listNhanVienViewModels = new ArrayList<>();
        for (NhanVienDomainModel nhanVienDomainModel : listNhanVienDomainModels) {
            NhanVienViewModel nhanVienViewModel = new NhanVienViewModel();
            nhanVienViewModel.setMaNhanVien(nhanVienDomainModel.getMaNhanVien());
            nhanVienViewModel.setHoVaTen(nhanVienDomainModel.getHoVaTen());
            nhanVienViewModel.setNgaySinh(nhanVienDomainModel.getNgaySinh());
            nhanVienViewModel.setDiaChi(nhanVienDomainModel.getDiaChi());
            nhanVienViewModel.setCCCD(nhanVienDomainModel.getCCCD());
            nhanVienViewModel.setTrangThai(nhanVienDomainModel.getTrangThai());
            nhanVienViewModel.setEmail(nhanVienDomainModel.getEmail());
            nhanVienViewModel.setSoDienThoai(nhanVienDomainModel.getSoDienThoai());
            nhanVienViewModel.setGhiChu(nhanVienDomainModel.getGhiChu());
            nhanVienViewModel.setAnh(nhanVienDomainModel.getAnh());
            nhanVienViewModel.setChucVu(nhanVienDomainModel.getChucVu());
            listNhanVienViewModels.add(nhanVienViewModel);
        }
        return listNhanVienViewModels;
    }

    @Override
    public String insertNhanVien(NhanVienViewModel nhanVienViewModel) {
        NhanVienDomainModel nhanVienDomainModel = new NhanVienDomainModel();
        nhanVienDomainModel.setMaNhanVien(nhanVienViewModel.getMaNhanVien());
        nhanVienDomainModel.setHoVaTen(nhanVienViewModel.getHoVaTen());
        nhanVienDomainModel.setNgaySinh(nhanVienViewModel.getNgaySinh());
        nhanVienDomainModel.setDiaChi(nhanVienViewModel.getDiaChi());
        nhanVienDomainModel.setCCCD(nhanVienViewModel.getCCCD());
        nhanVienDomainModel.setTrangThai(nhanVienViewModel.getTrangThai());
        nhanVienDomainModel.setEmail(nhanVienViewModel.getEmail());
        nhanVienDomainModel.setSoDienThoai(nhanVienDomainModel.getSoDienThoai());
        nhanVienDomainModel.setGhiChu(nhanVienViewModel.getGhiChu());
        nhanVienDomainModel.setAnh(nhanVienViewModel.getAnh());
        nhanVienDomainModel.setChucVu(nhanVienDomainModel.getChucVu());
        if (iNhanVienRepository.insertNhanVien(nhanVienDomainModel)) {
            return "Thêm nhân viên thành công";
        } else {
            return "Thêm nhân viên thất bại";
        }

    }

}
