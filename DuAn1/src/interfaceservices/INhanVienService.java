/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaceservices;

import java.util.ArrayList;
import viewmodel.NhanVienViewModel;

/**
 *
 * @author Admin
 */
public interface INhanVienService {

    public int getMaNhanVienByEmail(String email);

    public String checkEmail(String email); 
    public ArrayList<NhanVienViewModel> getAll();
    public String insertNhanVien(NhanVienViewModel nhanVienViewModel);
}
