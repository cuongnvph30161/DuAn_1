/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacerepositorys;

import domainmodel.TaiKhoanDomail;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface ITaiKhoanRepositorys {
   public TaiKhoanDomail getTaiKhoanByMaTaiKhoanAndMatKhau(String maTaiKhoan, String matKhau);
   public ArrayList<TaiKhoanDomail> getListTaiKhoan();
}
