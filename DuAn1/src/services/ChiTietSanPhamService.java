/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.ChiTietSanPhamDomainModel;
import domainmodel.NhanVien.ChiTietSanPham;
import domainmodel.NhanVien.SanPham;
import interfaceservices.IChiTietSanPhamService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import repositorys.ChiTietSanPhamRepository;
import repositorys.iRepository.IChiTietSanPhamRepository;
import viewmodel.defaultViewModel.ChiTietSanPhamViewModel;
import viewmodel.defaultViewModel.SanPhamViewModel;
import java.util.List;
import java.util.stream.Collectors;

import domainmodel.ChiTietSanPhamDomainModel;
import interfaceservices.IChiTietSanPhamService;
import repositorys.ChiTietSanPhamRepository;
import viewmodel.defaultViewModel.ChiTietSanPhamViewModel;

/**
 *
 * @author Admin
 */
public class ChiTietSanPhamService implements IChiTietSanPhamService {

    private IChiTietSanPhamRepository ctspRepo = new ChiTietSanPhamRepository();
    private ChiTietSanPhamRepository rpCTSP=new ChiTietSanPhamRepository();
    public List<ChiTietSanPhamViewModel> getAll(int... maSanPham){
   
    	return rpCTSP.getAll(maSanPham).stream().map(dmCTSP->{
    		ChiTietSanPhamViewModel vmCTSP=new ChiTietSanPhamViewModel();
    		vmCTSP.setGia(dmCTSP.getGia());
    		vmCTSP.setMaChiTietSanPham(dmCTSP.getMaChiTietSanPham());
    		vmCTSP.setMaSanPham(dmCTSP.getMaSanPham());
    		vmCTSP.setSize(dmCTSP.getSize());
    		return vmCTSP;
    	}).collect(Collectors.toList());
    }
    public ChiTietSanPhamViewModel getById(int id) {
    	ChiTietSanPhamViewModel vmCTSP=new ChiTietSanPhamViewModel();
    	ChiTietSanPhamDomainModel dmCTSP=rpCTSP.getById(id);
    	vmCTSP.setGia(dmCTSP.getGia());
    	vmCTSP.setMaChiTietSanPham(dmCTSP.getMaChiTietSanPham());
    	vmCTSP.setMaSanPham(dmCTSP.getMaSanPham());
    	vmCTSP.setSize(dmCTSP.getSize());
    	return vmCTSP;
    }
    @Override
    public List<SanPhamViewModel> getListSanPham() {
        List<SanPhamViewModel> listNV = new ArrayList<>();
        List<SanPham> list = ctspRepo.getListSanPham();
        for (SanPham sp : list) {
            SanPhamViewModel spVM = new SanPhamViewModel();
            spVM.setMaSanPham(sp.getMaSanPham());
            spVM.setTenSanPham(sp.getTenSanPham());
            spVM.setTrangThai(sp.getTrangThai());
            spVM.setMotTa(sp.getMotTa());
            spVM.setAnh(sp.getAnh());
            listNV.add(spVM);
        }
        return listNV;
    }

    @Override
    public ChiTietSanPhamViewModel loadMouseClickSanPham(int maSanPham) {
        ChiTietSanPham ctspDomail = ctspRepo.loadMouseClickSanPham(maSanPham);
        ChiTietSanPhamViewModel ctspViewModel = new ChiTietSanPhamViewModel();
        ctspViewModel.setAnh(ctspDomail.getMaSanPham().getAnh());
        ctspViewModel.setTrangThai(ctspDomail.getMaSanPham().getTrangThai());
        ctspViewModel.setSize(ctspDomail.getSize());
        ctspViewModel.setGia(ctspDomail.getGia());
        return ctspViewModel;
    }

    @Override
    public ArrayList<SanPhamViewModel> getSanPhamByTen(String tenSanPham) {
        ArrayList<SanPham> listSP = ctspRepo.getSanPhamByTen(tenSanPham);
        ArrayList<SanPhamViewModel> listSPVM = new ArrayList<>();
        for (SanPham sp : listSP) {
            SanPhamViewModel spVM = new SanPhamViewModel();
            spVM.setMaSanPham(sp.getMaSanPham());
            spVM.setTenSanPham(sp.getTenSanPham());
            spVM.setTrangThai(sp.getTrangThai());
            spVM.setMotTa(sp.getMotTa());
            listSPVM.add(spVM);
        }
        return listSPVM;
    }

    @Override
    public boolean insertSanPham(SanPhamViewModel spVM) {
        SanPham sp = new SanPham();

        sp.setAnh(spVM.getAnh());
        sp.setMotTa(spVM.getMotTa());
//        sp.setMaSanPham(spVM.getMaSanPham());
        sp.setTenSanPham(spVM.getTenSanPham());
        sp.setTrangThai(spVM.getTrangThai());

        if (ctspRepo.insertSanPham(sp)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean insertChiTietSP(ChiTietSanPhamViewModel ctspVM) {
        ChiTietSanPhamDomainModel ctsp = new ChiTietSanPhamDomainModel();
        ctsp.setMaSanPham(ctspVM.getMaSanPham());
        ctsp.setSize(ctspVM.getSize());
        ctsp.setGia(ctspVM.getGia());
        if (ctspRepo.insertCTSP(ctsp)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<ChiTietSanPhamViewModel> getGiaBySize(String size) {
        ArrayList<ChiTietSanPham> listCTSP = (ArrayList<ChiTietSanPham>) ctspRepo.getGiaBySize(size);
        ArrayList<ChiTietSanPhamViewModel> listCTSPVM = new ArrayList<>();
        for (ChiTietSanPham ctsp : listCTSP) {
            ChiTietSanPhamViewModel ctspVM = new ChiTietSanPhamViewModel();
            ctspVM.setGia(ctsp.getGia());
            listCTSPVM.add(ctspVM);
        }
        return listCTSPVM;
    }

    @Override
    public List<SanPhamViewModel> getSPMouclick(int maSanPham) {
        ArrayList<SanPham> listSP = (ArrayList<SanPham>) ctspRepo.getSPMouclick(maSanPham);
        ArrayList<SanPhamViewModel> listSPVM = new ArrayList<>();
        for (SanPham sp : listSP) {
            SanPhamViewModel spVM = new SanPhamViewModel();
            spVM.setMaSanPham(sp.getMaSanPham());
            spVM.setTenSanPham(sp.getTenSanPham());
            spVM.setTrangThai(sp.getTrangThai());
            spVM.setMotTa(sp.getMotTa());
            spVM.setAnh(sp.getAnh());
            listSPVM.add(spVM);
        }
        return listSPVM;
    }

    @Override
    public List<ChiTietSanPhamViewModel> getCTSPMouclick(int maSanPham) {
        ArrayList<ChiTietSanPham> listCTSP = (ArrayList<ChiTietSanPham>) ctspRepo.getCTSPMouclick(maSanPham);
        ArrayList<ChiTietSanPhamViewModel> listCTSPVM = new ArrayList<>();
        for (ChiTietSanPham ctsp : listCTSP) {
            ChiTietSanPhamViewModel ctspVM = new ChiTietSanPhamViewModel();
            ctspVM.setSize(ctsp.getSize());
            ctspVM.setGia(ctsp.getGia());
            listCTSPVM.add(ctspVM);
        }
        return listCTSPVM;
    }

    @Override
    public boolean updateSanPham(SanPhamViewModel spVM) {
        SanPham sp = new SanPham();
        sp.setMaSanPham(spVM.getMaSanPham());
        sp.setTenSanPham(spVM.getTenSanPham());
        sp.setTrangThai(spVM.getTrangThai());
        sp.setMotTa(spVM.getMotTa());
        sp.setAnh(spVM.getAnh());
        if (ctspRepo.updateSanPham(sp)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean deleteCTSP(int maSanPham, String size) {
        return ctspRepo.deleteCTSP(maSanPham, size);
    }

    @Override
    public boolean checkTonCTSP(int maSanPham, String size) {
        return ctspRepo.checkTonCTSP(maSanPham, size);
    }

    @Override
    public boolean updateSizeCTSP(int maSanPham, String size, BigDecimal gia) {
        return ctspRepo.updateSizeCTSP(maSanPham, size, gia);
    }


}
