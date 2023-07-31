package services.defaultService;

import domainmodel.BanHoaDonDomainModel;
import interfaceservices.IBanHoaDonService;
import repositorys.BanHoaDonRepository;
import repositorys.iRepository.IBanHoaDonRepository;
import viewmodel.defaultViewModel.BanHoaDonViewModel;

/**
 *
 * @author Doanh
 */
public class BanHoaDonService implements IBanHoaDonService{
	private IBanHoaDonRepository rpBanHoaDon=new BanHoaDonRepository();
	@Override
	public boolean insert(BanHoaDonViewModel vmBanHoaDon) {
		BanHoaDonDomainModel dmBanHoaDon=new BanHoaDonDomainModel();
		dmBanHoaDon.setMaBan(vmBanHoaDon.getMaBan());
		dmBanHoaDon.setMaHoaDon(vmBanHoaDon.getMaHoaDon());
		return rpBanHoaDon.insert(dmBanHoaDon);
	}
	public boolean deleteById(int maBan) {
		return rpBanHoaDon.deleteById(maBan);
		
	}
	

    
}
