/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys.iRepository;

import domainmodel.BanDomainModel;
import domainmodel.NhanVien.Ban;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IBanRepository extends DAO<Integer, BanDomainModel> {

    List<BanDomainModel> getList();

    Integer ThemBan(BanDomainModel ban);

    Integer CapNhatBan(BanDomainModel ban);

    List<Ban> getTang1();
    
    boolean CheckTrungTenBan(String ten, int tang);

}
