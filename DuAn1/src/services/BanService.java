/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.BanDomainModel;
import domainmodel.NhanVien.Ban;
import interfaceservices.IBanService;
import java.util.ArrayList;
import java.util.List;
import repositorys.BanRepository;
import viewmodel.QuanLyBanViewmodel;
import viewmodel.TenBanViewModel;
import viewmodel.defaultViewModel.BanViewModel;

/**
 *
 * @author Admin
 */
public class BanService implements IBanService {

    private BanRepository banRepo = new BanRepository();

    @Override
    public boolean insert(BanViewModel vmBan) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<TenBanViewModel> getTang1() {
        List<Ban> listBan = banRepo.getTang1();
        List<TenBanViewModel> listTenBanVM = new ArrayList<>();
        for (Ban b : listBan) {
            TenBanViewModel tb = new TenBanViewModel();
            tb.setTenBan(b.getTenBan());
            listTenBanVM.add(tb);
        }
        return listTenBanVM;
    }

    @Override
    public List<TenBanViewModel> getTang2() {
        List<Ban> listBan = banRepo.getTang2();
        List<TenBanViewModel> listTenBanVM = new ArrayList<>();
        for (Ban b : listBan) {
            TenBanViewModel tb = new TenBanViewModel();
            tb.setTenBan(b.getTenBan());
            listTenBanVM.add(tb);
        }
        return listTenBanVM;
    }

    @Override
    public List<TenBanViewModel> getTang3() {
        List<Ban> listBan = banRepo.getTang3();
        List<TenBanViewModel> listTenBanVM = new ArrayList<>();
        for (Ban b : listBan) {
            TenBanViewModel tb = new TenBanViewModel();
            tb.setTenBan(b.getTenBan());
            listTenBanVM.add(tb);
        }
        return listTenBanVM;
    }

    @Override
    public List<TenBanViewModel> getTang4() {
        List<Ban> listBan = banRepo.getTang4();
        List<TenBanViewModel> listTenBanVM = new ArrayList<>();
        for (Ban b : listBan) {
            TenBanViewModel tb = new TenBanViewModel();
            tb.setTenBan(b.getTenBan());
            listTenBanVM.add(tb);
        }
        return listTenBanVM;
    }

    @Override
    public List<TenBanViewModel> getTang5() {
        List<Ban> listBan = banRepo.getTang5();
        List<TenBanViewModel> listTenBanVM = new ArrayList<>();
        for (Ban b : listBan) {
            TenBanViewModel tb = new TenBanViewModel();
            tb.setTenBan(b.getTenBan());
            listTenBanVM.add(tb);
        }
        return listTenBanVM;
    }
}
