/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys.iRepository;

import domainmodel.ChiTietSanPhamDomainModel;
import domainmodel.NhanVien.ChiTietSanPham;
import domainmodel.NhanVien.SanPham;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IChiTietSanPhamRepository extends DAO<Integer, ChiTietSanPhamDomainModel> {

    List<ChiTietSanPhamDomainModel> getList();

    List<SanPham> getListSanPham();

    ChiTietSanPham loadMouseClickSanPham(int maSanPham);

    boolean updateSanPham(SanPham sp);

    ArrayList<SanPham> getSanPhamByTen(String tenSanPham);

    boolean insertSanPham(SanPham sp);

    boolean insertCTSP(ChiTietSanPhamDomainModel ctsp);

    List<ChiTietSanPham> getGiaBySize(String size);

    List<SanPham> getSPMouclick(int maSanPham);

    List<ChiTietSanPham> getCTSPMouclick(int maSanPham);

    boolean deleteCTSP(int maSanPham, String size);

    boolean checkTonCTSP(int maSanPham, String size);

    boolean updateSizeCTSP(int maSanPham, String size, BigDecimal gia);
}
