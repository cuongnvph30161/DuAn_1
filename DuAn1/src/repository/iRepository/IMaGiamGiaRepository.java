/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.iRepository;

import interfacerepositorys.*;
import domainmodel.MaGiamGiaDomainModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IMaGiamGiaRepository extends DAO<String,MaGiamGiaDomainModel>{
    List<MaGiamGiaDomainModel> getList();
}
