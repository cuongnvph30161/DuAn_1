/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.iRepository;

import domainmodel.BanDomainModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IBanRepository extends DAO<String,BanDomainModel>{
    List<BanDomainModel> getList();
   
    
}
