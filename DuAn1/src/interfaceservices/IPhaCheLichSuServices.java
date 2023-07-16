/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaceservices;

import java.util.List;
import java.util.Map;
import viewmodel.PhaCheLichSuViewModel;

/**
 *
 * @author ADMIN
 */
public interface IPhaCheLichSuServices {
    List<PhaCheLichSuViewModel> getList(Map<String, String> maBan);
    Map<String, Object> getMaBan();
}
