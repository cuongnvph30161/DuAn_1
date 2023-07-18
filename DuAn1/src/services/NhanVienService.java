/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import interfaceservices.INhanVienService;
import repository.iRepository.INhanVienRepository;
import repositorys.NhanVienRepository;

/**
 *
 * @author Admin
 */
public class NhanVienService implements INhanVienService{
public INhanVienRepository iNhanVienRepository = new NhanVienRepository();
    @Override
    public int getMaNhanVienByEmail(String email) {
        return iNhanVienRepository.getMaNhanVienByEmail(email);
    }

    @Override
    public String checkEmail(String email) {
        return iNhanVienRepository.checkEmail(email);
    }
    
}
