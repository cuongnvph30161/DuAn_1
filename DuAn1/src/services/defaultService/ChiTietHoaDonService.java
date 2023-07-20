package services.defaultService;

import domainmodel.ChiTietHoaDonDomainModel;
import interfaceservices.IChiTietHoaDonService;
import repositorys.ChiTietHoaDonRepository;
import repositorys.iRepository.IChiTietHoaDonRepository;
import viewmodel.defaultViewModel.ChiTietHoaDonViewModel;

/**
 *
 * @author Doanh
 */
public class ChiTietHoaDonService implements IChiTietHoaDonService{
	private IChiTietHoaDonRepository rpChiTietHoaDon=null;
	public ChiTietHoaDonService() {
		rpChiTietHoaDon=new ChiTietHoaDonRepository();
	}
	@Override
	public boolean insert(ChiTietHoaDonViewModel vmChiTietHoaDon) {
		ChiTietHoaDonDomainModel dmChiTietHoaDon=new ChiTietHoaDonDomainModel();
		dmChiTietHoaDon.setMaHoaDon(vmChiTietHoaDon.getMaHoaDon());
		dmChiTietHoaDon.setMaChiTietSanPham(vmChiTietHoaDon.getMaChiTietSanPham());
		dmChiTietHoaDon.setSoLuong(vmChiTietHoaDon.getSoLuong());
		dmChiTietHoaDon.setGia(vmChiTietHoaDon.getGia());
		return rpChiTietHoaDon.insert(dmChiTietHoaDon);
	}
	public boolean deleteById(int maHoaDon) {
		return rpChiTietHoaDon.deleteById(null);
		
	}
    
}
