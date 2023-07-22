/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaceservices;

import java.util.List;
import viewmodel.PhaCheSanPhamViewModel;

/**
 *
 * @author Admin
 */
public interface ISanPhamService {
    public List<PhaCheSanPhamViewModel> getList();
    public List<PhaCheSanPhamViewModel> getSanPhamTheoTen(String ten);
}
