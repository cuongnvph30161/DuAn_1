/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacerepositorys;

import domainmodel.ChiTietSanPhamDomainModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IChiTietSanPhamRepository {
        List<ChiTietSanPhamDomainModel> getList();
}