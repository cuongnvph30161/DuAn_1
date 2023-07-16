/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.TaiKhoanDomail;
import java.util.ArrayList;
import repositorys.TaiKhoanRepositorys;
import interfacerepositorys.ITaiKhoanRepositorys;
import interfaceservices.INhanVienService;
import interfaceservices.ITaiKhoanServicess;
import viewmodel.TaiKhoanViewModel;

/**
 *
 * @author Admin
 */
public class TaiKhoanServicess implements ITaiKhoanServicess {

    public ITaiKhoanRepositorys iTaiKhoanRepository = new TaiKhoanRepositorys();

    @Override
    public TaiKhoanDomail getTaiKhoanByMaTaiKhoanAndMatKhau(String maTaiKhoan, String matKhau) {
        return iTaiKhoanRepository.getTaiKhoanByMaTaiKhoanAndMatKhau(maTaiKhoan, matKhau);
    }

    @Override
    public ArrayList<TaiKhoanViewModel> getAll() {
        ArrayList<TaiKhoanDomail> listTaiKhoan = iTaiKhoanRepository.getListTaiKhoan();
        ArrayList<TaiKhoanViewModel> listTaiKhoanViewModel = new ArrayList<>();
        for (TaiKhoanDomail taiKhoanDomail : listTaiKhoan) {
            TaiKhoanViewModel taiKhoanViewModel = new TaiKhoanViewModel();
            taiKhoanViewModel.setMaTaiKhoan(taiKhoanDomail.getMaTaiKhoan());
            taiKhoanViewModel.setMaNhanVien(taiKhoanDomail.getMaNhanVien());
            taiKhoanViewModel.setMatKhau(taiKhoanDomail.getMatKhau());
            taiKhoanViewModel.setRole(taiKhoanDomail.getRole());
            taiKhoanViewModel.setTrangThai(taiKhoanDomail.getTrangThai());
            listTaiKhoanViewModel.add(taiKhoanViewModel);
        }
        return listTaiKhoanViewModel;
    }

    @Override
    public String updateMatKhauByMaNhanVien(String newPassWord, int maNhanVien) {
        return iTaiKhoanRepository.updateMatKhauByMaNhanVien(newPassWord, maNhanVien);
    }

}
