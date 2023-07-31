/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys.iRepository;

import domainmodel.NhanVienDomainModel;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface INhanVienRepository extends DAO<String, NhanVienDomainModel> {

    public int getMaNhanVienByEmail(String email);

    public String checkEmail(String email);

    public ArrayList<NhanVienDomainModel> getAll();

    public boolean insertNhanVien(NhanVienDomainModel nhanVienDomainModel);

    public NhanVienDomainModel loadMouseClick(int maNhanVien);

    public boolean update(int maNhanVien, NhanVienDomainModel nhanVienDomainModel);

    public boolean checkTrungEmail(String email);

    public NhanVienDomainModel getNhanVienById(int maNhanVien);

    public ArrayList<NhanVienDomainModel> getNhanVienByTen(String ten);

    public ArrayList<NhanVienDomainModel> getNhanVienByTrangThai(int trangThai);

    public boolean getNhanVienByCCCD(String cccd);
    
    public String getSoDienThoaiBySDT(String sdt);
    public int getByIdAccount(String idAccount);
}
