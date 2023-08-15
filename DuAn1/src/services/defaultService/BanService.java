
package services.defaultService;

import interfaceservices.IBanService;
import java.util.List;
import java.util.stream.Collectors;

import domainmodel.BanDomainModel;
import repositorys.BanRepository;
import viewmodel.QuanLyBanViewmodel;
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
	public List<BanViewModel> getAll() {
		return rpBan.getAll().stream()
				.map(a -> new BanViewModel(a.getMaBan(), a.getTenBan(), a.getTang(), a.getTrangThai()))
				.collect(Collectors.toList());
	}

	public BanViewModel getById(int id) {
		BanViewModel vmBan = new BanViewModel();
		BanDomainModel dmBan = rpBan.getById(id);
		vmBan.setMaBan(dmBan.getMaBan());
		vmBan.setTang(dmBan.getTang());
		vmBan.setTenBan(dmBan.getTenBan());
		vmBan.setTrangThai(dmBan.getTrangThai());
		return vmBan;
	}

	public void actives(Integer maBan, boolean status) {
		rpBan.actives(maBan, status);

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

    @Override
    public boolean CheckTrungTenBan(String ten, int tang) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
