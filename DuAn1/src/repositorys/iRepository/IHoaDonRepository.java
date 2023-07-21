/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys.iRepository;

import domainmodel.HoaDonDoMainModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IHoaDonRepository extends DAO<Integer,HoaDonDoMainModel>{
        List<HoaDonDoMainModel> getList();
        Integer capNhatTrangThai(int maHD,int trangThai);
}
