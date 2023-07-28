/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.Role;
import domainmodel.TaiKhoanDomail;
import java.util.ArrayList;
import repositorys.TaiKhoanRepositorys;
import interfaceservices.INhanVienService;
import interfaceservices.ITaiKhoanServicess;
import repositorys.iRepository.ITaiKhoanRepositorys;

import javax.swing.JOptionPane;
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
        if (iTaiKhoanRepository.updateMatKhauByMaNhanVien(newPassWord, maNhanVien)) {
            return "Đổi mật khẩu thành công";
        } else {
            return "Đổi mật khẩu thất bại";
        }

    }

    @Override
    public String checkTaiKhoan(String maTaiKhoan) {
        return iTaiKhoanRepository.checkTaiKhoan(maTaiKhoan);
    }

    @Override
    public String doiMatKhau(String matKhau, String maTaiKhoan) {
        if (iTaiKhoanRepository.doiMatKhau(matKhau, maTaiKhoan)) {
            System.out.println("test mat khau" + " " + iTaiKhoanRepository.doiMatKhau(matKhau, maTaiKhoan));
            return "Đổi mật khẩu thành công";
        } else {
            return "Đổi mật khẩu thất bại";
        }
    }

    @Override
    public String checkMatKhau(String maTaiKhoan) {
        return iTaiKhoanRepository.checkMatKhau(maTaiKhoan);
    }

    @Override
    public String insertTaiKhoan(TaiKhoanViewModel taiKhoanViewModel) {
        TaiKhoanDomail taiKhoanDomail = new TaiKhoanDomail();
        taiKhoanDomail.setMaTaiKhoan(taiKhoanViewModel.getMaTaiKhoan());
        taiKhoanDomail.setMaNhanVien(taiKhoanViewModel.getMaNhanVien());
        taiKhoanDomail.setMatKhau(taiKhoanViewModel.getMatKhau());
        taiKhoanDomail.setRole(taiKhoanViewModel.getRole());
        taiKhoanDomail.setTrangThai(taiKhoanViewModel.getTrangThai());
        if (iTaiKhoanRepository.insertTaiKhoan(taiKhoanDomail)) {
            return "Thêm tài khoản thành công";
        } else {
            return "Thêm tài khoản thất bại";
        }
    }

    @Override
    public String updateTaiKhoan(String maTaiKhoan, TaiKhoanViewModel taiKhoanViewModel) {
        TaiKhoanDomail taiKhoanDomail = new TaiKhoanDomail();
        taiKhoanDomail.setMaNhanVien(taiKhoanViewModel.getMaNhanVien());
        taiKhoanDomail.setMatKhau(taiKhoanViewModel.getMatKhau());
        taiKhoanDomail.setRole(taiKhoanViewModel.getRole());
        taiKhoanDomail.setTrangThai(taiKhoanViewModel.getTrangThai());
        if (iTaiKhoanRepository.updateTaiKhoan(maTaiKhoan, taiKhoanDomail)) {
            return "Cập nhật tài khoản thành công";
        } else {
            return "Cập nhật tài khoản thất bại";
        }
    }

//    @Override
//    public String getMaTaiKhoanByMaTaiKhoan(String maTaiKhoan) {
//      return iTaiKhoanRepository.getMaTaiKhoanByMaTaiKhoan(maTaiKhoan);
//    }
//
//    @Override
//    public int getMaNhanVienByMaNhanVien(int maNhanVien) {
//       return iTaiKhoanRepository.getMaNhanVienByMaNhanVien(maNhanVien);
//    }
    @Override
    public TaiKhoanViewModel getTaiKhoanByMa(String maTK) {

        TaiKhoanDomail taiKhoanDomail = iTaiKhoanRepository.getTaiKhoanByMa(maTK);
        TaiKhoanViewModel taiKhoanViewModel = new TaiKhoanViewModel();
        if (taiKhoanDomail == null) {
            // Trả về null nếu không tìm thấy mã tài khoản
            return null;
        }
        taiKhoanViewModel.setMaTaiKhoan(taiKhoanDomail.getMaTaiKhoan());
        taiKhoanViewModel.setMaNhanVien(taiKhoanDomail.getMaNhanVien());
        taiKhoanViewModel.setMatKhau(taiKhoanDomail.getMatKhau());
        taiKhoanViewModel.setRole(taiKhoanDomail.getRole());
        taiKhoanViewModel.setTrangThai(taiKhoanDomail.getTrangThai());
        return taiKhoanViewModel;
    }

    @Override
    public boolean isMaTaiKhoanTrung(String maTk, String maTaiKhoanHienTai) {
        return iTaiKhoanRepository.isMaTaiKhoanTrung(maTk, maTaiKhoanHienTai);
    }

    @Override
    public String getEmailByMaTaiKhoan(String maTaiKhoan) {
        return iTaiKhoanRepository.getEmailByMaTaiKhoan(maTaiKhoan);
    }

    @Override
    public ArrayList<TaiKhoanViewModel> getListTaiKhoanByMa(String maTK) {
        ArrayList<TaiKhoanDomail> listTaiKhoan = iTaiKhoanRepository.getListTaiKhoanByMa(maTK);
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
    public String getMaNhanVienByMa(String maTaiKhoan) {
        return iTaiKhoanRepository.getMaNhanVienByMaTaiKhoan(maTaiKhoan);
    }

}
