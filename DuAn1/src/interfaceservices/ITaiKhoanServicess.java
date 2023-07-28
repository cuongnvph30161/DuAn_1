/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaceservices;

import domainmodel.TaiKhoanDomail;
import repositorys.iRepository.ITaiKhoanRepositorys;
import repositorys.TaiKhoanRepositorys;

import java.util.ArrayList;
import viewmodel.TaiKhoanViewModel;

/**
 *
 * @author Admin
 */
public interface ITaiKhoanServicess {

    public TaiKhoanDomail getTaiKhoanByMaTaiKhoanAndMatKhau(String maTaiKhoan, String matKhau);

    public ArrayList<TaiKhoanViewModel> getAll();

    public String updateMatKhauByMaNhanVien(String newPassWord, int maNhanVien);

    public String checkTaiKhoan(String maTaiKhoan);

    public String doiMatKhau(String matKhau, String maTaiKhoan);

    public String checkMatKhau(String maTaiKhoan);

    public String insertTaiKhoan(TaiKhoanViewModel taiKhoanViewModel);

    public String updateTaiKhoan(String maTaiKhoan, TaiKhoanViewModel taiKhoanViewModel);
//    public String getMaTaiKhoanByMaTaiKhoan(String maTaiKhoan);
//    public int  getMaNhanVienByMaNhanVien(int maNhanVien);

    public TaiKhoanViewModel getTaiKhoanByMa(String maTK);

    public boolean isMaTaiKhoanTrung(String maTk, String maTaiKhoanHienTai);

    public String getEmailByMaTaiKhoan(String maTaiKhoan);

    public ArrayList<TaiKhoanViewModel> getListTaiKhoanByMa(String maTK);

    public String getMaNhanVienByMa(String maTaiKhoan);
}
