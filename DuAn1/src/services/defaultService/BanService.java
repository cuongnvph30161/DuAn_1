
package services.defaultService;

import interfaceservices.IBanService;
import repositorys.BanRepository;
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
    
}
