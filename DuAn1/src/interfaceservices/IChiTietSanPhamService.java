/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaceservices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import viewmodel.defaultViewModel.ChiTietSanPhamViewModel;
import viewmodel.defaultViewModel.SanPhamViewModel;

/**
 *
 * @author Admin
 */
public interface IChiTietSanPhamService {

    List<SanPhamViewModel> getListSanPham();

    ChiTietSanPhamViewModel loadMouseClickSanPham(int maSanPham);

    ArrayList<SanPhamViewModel> getSanPhamByTen(String tenSanPham);

    boolean insertSanPham(SanPhamViewModel spVM);

    boolean insertChiTietSP(ChiTietSanPhamViewModel ctspVM);

    ArrayList<ChiTietSanPhamViewModel> getGiaBySize(String size);

    List<SanPhamViewModel> getSPMouclick(int maSanPham);

    List<ChiTietSanPhamViewModel> getCTSPMouclick(int maSanPham);

    boolean updateSanPham(SanPhamViewModel spVM);

    boolean deleteCTSP(int maSanPham, String size);

    boolean checkTonCTSP(int maSanPham, String size);

    boolean updateSizeCTSP(int maSanPham, String size, BigDecimal gia);
}
