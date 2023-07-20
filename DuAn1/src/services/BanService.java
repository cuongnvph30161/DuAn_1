/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.BanDomainModel;
import interfaceservices.IBanService;
import java.util.ArrayList;
import java.util.List;
import repositorys.BanRepository;
import viewmodel.TenBanViewModel;
import viewmodel.defaultViewModel.BanViewModel;

/**
 *
 * @author Admin
 */
public class BanService implements IBanService{
     private BanRepository banRepo = new BanRepository();
    
     public List<TenBanViewModel> getTenBan() {

        List<BanDomainModel> listBanDoMain = banRepo.getList();
        List<TenBanViewModel> listTenBanVM = new ArrayList<>();
        for (BanDomainModel ban : listBanDoMain) {
            TenBanViewModel tb = new TenBanViewModel();
            tb.setTenBan(ban.getTenBan());
            listTenBanVM.add(tb);
        }
        return listTenBanVM;
    }

	@Override
	public boolean insert(BanViewModel vmBan) {
		// TODO Auto-generated method stub
		return false;
	}
    
}
