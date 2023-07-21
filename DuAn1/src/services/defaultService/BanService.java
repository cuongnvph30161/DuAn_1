
package services.defaultService;

import interfaceservices.IBanService;
import java.util.List;
import repositorys.BanRepository;
import viewmodel.TenBanViewModel;
import viewmodel.defaultViewModel.BanViewModel;

/**
 *
 * @author Doanh
 */
public class BanService implements IBanService{
	private BanRepository rpBan=new BanRepository();
	@Override
	public boolean insert(BanViewModel vmBan) {
		// TODO Auto-generated method stub
		return false;
	}

    @Override
    public List<TenBanViewModel> getTang1() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TenBanViewModel> getTang2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TenBanViewModel> getTang3() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TenBanViewModel> getTang4() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TenBanViewModel> getTang5() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
