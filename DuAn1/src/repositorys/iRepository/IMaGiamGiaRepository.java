/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys.iRepository;

//import interfacerepositorys.*;
import domainmodel.MaGiamGiaDomainModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public interface IMaGiamGiaRepository {
	boolean checkMaGiamGia(Integer a);
    boolean checkMaGiamGia(int a);
    Integer applyVoucher(int voucher,int tongThanhToan);
    String [] getLstID();
    
    public List<MaGiamGiaDomainModel> getList();

  

    Integer phanTramGiamGia(Integer b);

    public ArrayList<MaGiamGiaDomainModel> getListMaGiamGia();

    public boolean insertMaGiamGia(MaGiamGiaDomainModel maGiamGiaDomainModel);
    public boolean updateMaGiamGiaSoLuong(int maVouCher ,MaGiamGiaDomainModel maGiamGiaDomainModel);
    public ArrayList<MaGiamGiaDomainModel> findMaGiamGiaByHoaDonToiThieu(int hoaDonToiThieuByTimKiem);
}
