/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaceservices;

import java.util.List;
import viewmodel.QuanLyBanViewmodel;

/**
 *
 * @author ADMIN
 */
public interface IQuanLyBanServices {
    List<QuanLyBanViewmodel> getListBan();
    Integer themBan(QuanLyBanViewmodel ban);
    Integer CapNhatBan(QuanLyBanViewmodel ban);
    
}
