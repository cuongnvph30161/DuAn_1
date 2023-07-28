/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys.iRepository;

import domainmodel.TaiKhoanDomail;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface ITaiKhoanRepositorys extends DAO<String, TaiKhoanDomail> {

    public TaiKhoanDomail getTaiKhoanByMaTaiKhoanAndMatKhau(String maTaiKhoan, String matKhau);

    public ArrayList<TaiKhoanDomail> getListTaiKhoan();

    public boolean updateMatKhauByMaNhanVien(String newPassWord, int maNhanVien);

    public String checkTaiKhoan(String maTaiKhoan);

    public boolean doiMatKhau(String matKhau, String maTaiKhoan);

    public String checkMatKhau(String maTaiKhoan);

    public boolean insertTaiKhoan(TaiKhoanDomail taiKhoanDomail);

    public boolean updateTaiKhoan(String maTaiKhoan, TaiKhoanDomail taiKhoanDomail);
//    public String getMaTaiKhoanByMaTaiKhoan(String maTaiKhoan);
//    public int getMaNhanVienByMaNhanVien(int maNhanVien);

    public TaiKhoanDomail getTaiKhoanByMa(String maTK);

    public boolean isMaTaiKhoanTrung(String maTk, String maTaiKhoanHienTai);

    public String getEmailByMaTaiKhoan(String maTaiKhoan);

    public ArrayList<TaiKhoanDomail> getListTaiKhoanByMa(String maTaiKhoan);

    public String getMaNhanVienByMaTaiKhoan(String maTaiKhoan);
}
