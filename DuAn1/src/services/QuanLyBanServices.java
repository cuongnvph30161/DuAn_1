/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodel.BanDomainModel;
import interfaceservices.IQuanLyBanServices;
import java.util.ArrayList;
import java.util.List;
import repositorys.BanRepository;
import repositorys.iRepository.IBanRepository;
import viewmodel.QuanLyBanViewmodel;

/**
 *
 * @author ADMIN
 */
public class QuanLyBanServices implements IQuanLyBanServices {

    IBanRepository banRepo = new BanRepository();

    @Override
    public List<QuanLyBanViewmodel> getListBan() {
        List<QuanLyBanViewmodel> lst = new ArrayList<>();
        List<BanDomainModel> lst1 = banRepo.getList();
        for (BanDomainModel a : lst1) {
            lst.add(new QuanLyBanViewmodel(a.getMaBan(),
                    a.getTenBan(), a.getTang()));
        }
        return lst;

    }

    @Override
    public Integer themBan(QuanLyBanViewmodel ban) {
        BanDomainModel bandomain = new BanDomainModel(ban.getTenBan(),
                ban.getTang(), 0);

        return banRepo.ThemBan(bandomain);
    }

    @Override
    public Integer CapNhatBan(QuanLyBanViewmodel ban) {
        BanDomainModel bandomain = new BanDomainModel(ban.getMaBan(), ban.getTenBan(),
                 ban.getTang(), 0);

        return banRepo.CapNhatBan(bandomain);

    }

}
