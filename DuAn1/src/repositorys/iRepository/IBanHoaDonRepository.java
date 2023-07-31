/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys.iRepository;

import domainmodel.BanHoaDonDomainModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IBanHoaDonRepository extends DAO<Integer,BanHoaDonDomainModel>{
        List<BanHoaDonDomainModel> getList();

;
}
