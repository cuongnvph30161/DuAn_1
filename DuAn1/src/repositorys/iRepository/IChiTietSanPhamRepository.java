/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys.iRepository;

import domainmodel.ChiTietSanPhamDomainModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IChiTietSanPhamRepository extends DAO<String,ChiTietSanPhamDomainModel>{
        List<ChiTietSanPhamDomainModel> getList();
}
