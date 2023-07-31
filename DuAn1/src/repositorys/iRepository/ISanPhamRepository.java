/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys.iRepository;


import domainmodel.SanPhamDomainModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface ISanPhamRepository extends DAO<String,SanPhamDomainModel>{
        List<SanPhamDomainModel> getList();
        public List<SanPhamDomainModel> getSanPhamTheoTen(String ten);
        public List<SanPhamDomainModel> getAll(String searchKey);
}
