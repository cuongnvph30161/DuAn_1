/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacerepositorys;

import domainmodel.ChiTietHoaDonDomainModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IChiTietHoaDonRepository {
        List<ChiTietHoaDonDomainModel> getList();
}
