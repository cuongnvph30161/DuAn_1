/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaceservices;

import domainmodel.TaiKhoanDomail;
import repositorys.TaiKhoanRepositorys;
import interfacerepositorys.ITaiKhoanRepositorys;
import java.util.ArrayList;
import viewmodel.TaiKhoanViewModel;

/**
 *
 * @author Admin
 */
public interface ITaiKhoanServicess {

    public TaiKhoanDomail getTaiKhoanByMaTaiKhoanAndMatKhau(String maTaiKhoan, String matKhau);
    public ArrayList<TaiKhoanViewModel> getAll();
}
