/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import interfaceservices.IMaGiamGiaService;
import javax.swing.JTextField;
import repositorys.MaGiamGiaRepository;
import repositorys.iRepository.IMaGiamGiaRepository;

/**
 *
 * @author Admin
 */
public class MaGiamGiaService implements IMaGiamGiaService {

    private IMaGiamGiaRepository mggRepo = new MaGiamGiaRepository();

    @Override
    public boolean checkMaGiamGia(JTextField a) {
        return mggRepo.checkMaGiamGia(a);
    }

    @Override
    public Integer phanTramGiamGia(Integer b) {
        return mggRepo.phanTramGiamGia(b);
    }

}
