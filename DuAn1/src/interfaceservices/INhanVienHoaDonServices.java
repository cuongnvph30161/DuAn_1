/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaceservices;

import java.util.List;
import java.util.Map;
import viewmodel.NhanVienHoaDonViewModel;
import viewmodel.PhaCheLichSuDanhSachSanPhamViewmodel;

/**
 *
 * @author ADMIN
 */
public interface INhanVienHoaDonServices {

    List<NhanVienHoaDonViewModel> getList(List<PhaCheLichSuDanhSachSanPhamViewmodel> DSSP,
            Map<Integer, String> mapTenNV, Map<Integer, String> mapTenBan);

    List<PhaCheLichSuDanhSachSanPhamViewmodel> getDSSP();

    Map<Integer, String> mapTenNV();

    Map<Integer, String> mapTenBan();
}
