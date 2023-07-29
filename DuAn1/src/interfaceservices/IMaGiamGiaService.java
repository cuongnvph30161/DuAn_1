/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaceservices;

import java.util.ArrayList;
import javax.swing.JTextField;
import viewmodel.MaGiamGiaViewModel;

/**
 *
 * @author Admin
 */
public interface IMaGiamGiaService {

    boolean checkMaGiamGia(JTextField a);

    Integer phanTramGiamGia(Integer b);
    public ArrayList<MaGiamGiaViewModel> getListMaGiamGia();
    public String  insertMaGiamGia(MaGiamGiaViewModel maGiamGiaViewModel);
    public String updateMaGiamGiaSoLuong(int maVouCher ,MaGiamGiaViewModel maGiamGiaViewModel);
}
