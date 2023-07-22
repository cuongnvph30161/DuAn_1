/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.SanPhamDomainModel;
import interfaceservices.ISanPhamService;
import java.util.ArrayList;
import java.util.List;
import repositorys.SanPhamRepository;
import repositorys.iRepository.ISanPhamRepository;
import viewmodel.PhaCheSanPhamViewModel;

/**
 *
 * @author Admin
 */
public class SanPhamService implements ISanPhamService{
    ISanPhamRepository SanPhamRepo= new SanPhamRepository();
    @Override
    public List<PhaCheSanPhamViewModel> getList() {
        List<SanPhamDomainModel> LstSanPhamDomain=SanPhamRepo.getList();
              
        List<PhaCheSanPhamViewModel> LstPhaCheSP= new ArrayList<>();
        
        for (SanPhamDomainModel SPDomain : LstSanPhamDomain) {
            PhaCheSanPhamViewModel PhaCheSanPham= new PhaCheSanPhamViewModel();
            PhaCheSanPham.setAnh(SPDomain.getAnh());
            PhaCheSanPham.setTen(SPDomain.getTenSanPham());
            PhaCheSanPham.setTrangThai(SPDomain.getTrangThai());
                
            LstPhaCheSP.add(PhaCheSanPham);
        }
        
        return LstPhaCheSP;
    }

    @Override
    public List<PhaCheSanPhamViewModel> getSanPhamTheoTen(String ten) {
        List<SanPhamDomainModel> LstSanPhamDomain=SanPhamRepo.getSanPhamTheoTen(ten);
              
        List<PhaCheSanPhamViewModel> LstPhaCheSP= new ArrayList<>();
        
        for (SanPhamDomainModel SPDomain : LstSanPhamDomain) {
            PhaCheSanPhamViewModel PhaCheSanPham= new PhaCheSanPhamViewModel();
            PhaCheSanPham.setAnh(SPDomain.getAnh());
            PhaCheSanPham.setTen(SPDomain.getTenSanPham());
            PhaCheSanPham.setTrangThai(SPDomain.getTrangThai());
                
            LstPhaCheSP.add(PhaCheSanPham);
        }
        
        return LstPhaCheSP;
    }
    
}
