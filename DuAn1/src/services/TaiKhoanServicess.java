/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.TaiKhoanDomail;
import java.util.ArrayList;
import repositorys.TaiKhoanRepositorys;
import interfacerepositorys.ITaiKhoanRepositorys;
import interfaceservices.ITaiKhoanServicess;
/**
 *
 * @author Admin
 */
public class TaiKhoanServicess implements ITaiKhoanServicess{
public ITaiKhoanRepositorys iTaiKhoanRepository = new TaiKhoanRepositorys();
    @Override
    public TaiKhoanDomail getTaiKhoanByMaTaiKhoanAndMatKhau(String maTaiKhoan, String matKhau) {
        return iTaiKhoanRepository.getTaiKhoanByMaTaiKhoanAndMatKhau(maTaiKhoan, matKhau);
    }




  
    
}
