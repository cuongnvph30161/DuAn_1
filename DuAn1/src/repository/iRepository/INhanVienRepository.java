/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.iRepository;

import domainmodel.NhanVienDomainModel;

/**
 *
 * @author Admin
 */
public interface INhanVienRepository extends DAO<String,NhanVienDomainModel>{
    public int getMaNhanVienByEmail(String email);
    public String checkEmail(String email);
}
