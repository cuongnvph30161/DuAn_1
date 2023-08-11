/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.NhanVienDomainModel;
import interfaceservices.INhanVienService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import repositorys.iRepository.INhanVienRepository;
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
        nhanVienDomainModel.setHoVaTen(nhanVienViewModel.getHoVaTen());
        nhanVienDomainModel.setNgaySinh(nhanVienViewModel.getNgaySinh());
        nhanVienDomainModel.setDiaChi(nhanVienViewModel.getDiaChi());
        nhanVienDomainModel.setCCCD(nhanVienViewModel.getCCCD());
        nhanVienDomainModel.setTrangThai(nhanVienViewModel.getTrangThai());
        nhanVienDomainModel.setEmail(nhanVienViewModel.getEmail());
        nhanVienDomainModel.setSoDienThoai(nhanVienViewModel.getSoDienThoai());
        nhanVienDomainModel.setGhiChu(nhanVienViewModel.getGhiChu());
        nhanVienDomainModel.setAnh(nhanVienViewModel.getAnh());
        nhanVienDomainModel.setChucVu(nhanVienViewModel.getChucVu());

        if (iNhanVienRepository.checkTrungEmail(nhanVienDomainModel.getEmail())) {
            System.out.println("email service");
            return "Email không được trùng";
        }
        if (iNhanVienRepository.insertNhanVien(nhanVienDomainModel)) {
            return "Thêm nhân viên thành công";
        } else {
            return "Thêm nhân viên thất bại";
        }

    }

    @Override
    public NhanVienViewModel loadMouseclicked(int maNhanVien) {
        NhanVienDomainModel nhanVienDomainModel = iNhanVienRepository.loadMouseClick(maNhanVien);
        NhanVienViewModel nhanVienViewModel = new NhanVienViewModel();
        nhanVienViewModel.setAnh(nhanVienDomainModel.getAnh());
        nhanVienViewModel.setNgaySinh(nhanVienDomainModel.getNgaySinh());
        nhanVienViewModel.setDiaChi(nhanVienDomainModel.getDiaChi());
        nhanVienViewModel.setTrangThai(nhanVienDomainModel.getTrangThai());
        nhanVienViewModel.setGhiChu(nhanVienDomainModel.getGhiChu());
        return nhanVienViewModel;
    }

    @Override
    public String update(int maNhanVien, NhanVienViewModel nhanVienViewModel) {
        NhanVienDomainModel nhanVienDomainModel = new NhanVienDomainModel();
        nhanVienDomainModel.setHoVaTen(nhanVienViewModel.getHoVaTen());
        nhanVienDomainModel.setNgaySinh(nhanVienViewModel.getNgaySinh());
        nhanVienDomainModel.setDiaChi(nhanVienViewModel.getDiaChi());
        nhanVienDomainModel.setCCCD(nhanVienViewModel.getCCCD());
        nhanVienDomainModel.setTrangThai(nhanVienViewModel.getTrangThai());
        nhanVienDomainModel.setEmail(nhanVienViewModel.getEmail());
        nhanVienDomainModel.setSoDienThoai(nhanVienViewModel.getSoDienThoai());
        nhanVienDomainModel.setGhiChu(nhanVienViewModel.getGhiChu());
        nhanVienDomainModel.setAnh(nhanVienViewModel.getAnh());
        nhanVienDomainModel.setChucVu(nhanVienViewModel.getChucVu());

        if (iNhanVienRepository.update(maNhanVien, nhanVienDomainModel)) {
            return "Cập nhật nhân viên thành công";
        } else {
            return "Cập nhật nhân viên thất bại";
        }
    }

    @Override
    public NhanVienViewModel getNhanVienById(int maNhanVien) {
        NhanVienDomainModel nhanVienDomainModel = iNhanVienRepository.getNhanVienById(maNhanVien);
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
        return nhanVienViewModel;
    }

    @Override
    public ArrayList<NhanVienViewModel> getNhanVienByTen(String ten) {
        ArrayList<NhanVienDomainModel> getNhanVienDomainModels = iNhanVienRepository.getNhanVienByTen(ten);
        ArrayList<NhanVienViewModel> getNhanVienViewModels = new ArrayList<>();
        for (NhanVienDomainModel nhanVienDomainModel : getNhanVienDomainModels) {
            NhanVienViewModel nhanVienViewModel = new NhanVienViewModel();
            nhanVienViewModel.setMaNhanVien(nhanVienDomainModel.getMaNhanVien());
            nhanVienViewModel.setHoVaTen(nhanVienDomainModel.getHoVaTen());
            nhanVienViewModel.setCCCD(nhanVienDomainModel.getCCCD());
            nhanVienViewModel.setSoDienThoai(nhanVienDomainModel.getSoDienThoai());
            nhanVienViewModel.setEmail(nhanVienDomainModel.getEmail());
            nhanVienViewModel.setChucVu(nhanVienDomainModel.getChucVu());
            getNhanVienViewModels.add(nhanVienViewModel);
        }
        return getNhanVienViewModels;
    }

    @Override
    public ArrayList<NhanVienViewModel> getNhanVienByTrangThai(int trangThai) {
        ArrayList<NhanVienDomainModel> getNhanVienDomainModels = iNhanVienRepository.getNhanVienByTrangThai(trangThai);
        ArrayList<NhanVienViewModel> getNhanVienViewModels = new ArrayList<>();
        for (NhanVienDomainModel nhanVienDomainModel : getNhanVienDomainModels) {
            NhanVienViewModel nhanVienViewModel = new NhanVienViewModel();
            nhanVienViewModel.setMaNhanVien(nhanVienDomainModel.getMaNhanVien());
            nhanVienViewModel.setHoVaTen(nhanVienDomainModel.getHoVaTen());
            nhanVienViewModel.setCCCD(nhanVienDomainModel.getCCCD());
            nhanVienViewModel.setSoDienThoai(nhanVienDomainModel.getSoDienThoai());
            nhanVienViewModel.setEmail(nhanVienDomainModel.getEmail());
            nhanVienViewModel.setChucVu(nhanVienDomainModel.getChucVu());
            getNhanVienViewModels.add(nhanVienViewModel);
        }
        return getNhanVienViewModels;
    }

    @Override
    public String getNhanVienByCCCD(String cccd) {
        if (iNhanVienRepository.getNhanVienByCCCD(cccd)) {
            return "CCCD đã tồn tại";
        } else {
            return null;
        }
    }

    @Override
    public String getSoDienThoaiBySDT(String sdt) {
        return iNhanVienRepository.getSoDienThoaiBySDT(sdt);
    }
    public int getByIdAccount(String idAccount) {
    	return iNhanVienRepository.getByIdAccount(idAccount);
    }
}
