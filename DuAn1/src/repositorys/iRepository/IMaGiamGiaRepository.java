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

    public List<MaGiamGiaDomainModel> getList();

    boolean checkMaGiamGia(JTextField a);

    Integer phanTramGiamGia(Integer b);

    public ArrayList<MaGiamGiaDomainModel> getListMaGiamGia();

    public boolean insertMaGiamGia(MaGiamGiaDomainModel maGiamGiaDomainModel);
}
