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
import viewmodel.defaultViewModel.SanPhamViewModel;

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
    public List<SanPhamViewModel> getAll(){
    	List<SanPhamViewModel> lstSP=new ArrayList<>();
    	for(SanPhamDomainModel dmSanPham:SanPhamRepo.getAll()) {
    		SanPhamViewModel vmSanPham=new SanPhamViewModel();
    		vmSanPham.setHinh(dmSanPham.getAnhSanPham());
    		vmSanPham.setMaSanPham(dmSanPham.getMaSanPham());
    		vmSanPham.setMotTa(dmSanPham.getMotTa());
    		vmSanPham.setTenSanPham(dmSanPham.getTenSanPham());
    		vmSanPham.setTrangThai(dmSanPham.getTrangThai());
    	lstSP.add(vmSanPham);
    	}
    	return lstSP;
    }
    public List<SanPhamViewModel> getAll(String searchKey){
    	List<SanPhamViewModel> lstSP=new ArrayList<>();
    	for(SanPhamDomainModel dmSanPham:SanPhamRepo.getAll(searchKey)) {
    		SanPhamViewModel vmSanPham=new SanPhamViewModel();
    		vmSanPham.setHinh(dmSanPham.getAnhSanPham());
    		vmSanPham.setMaSanPham(dmSanPham.getMaSanPham());
    		vmSanPham.setMotTa(dmSanPham.getMotTa());
    		vmSanPham.setTenSanPham(dmSanPham.getTenSanPham());
    		vmSanPham.setTrangThai(dmSanPham.getTrangThai());
    	lstSP.add(vmSanPham);
    	}
    	return lstSP;
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
