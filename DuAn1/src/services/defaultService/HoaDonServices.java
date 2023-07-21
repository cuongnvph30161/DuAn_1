package services.defaultService;

import java.util.List;

import domainmodel.HoaDonDoMainModel;
import repositorys.HoaDonRepository;
import utilities.Uhelper;
import viewmodel.defaultViewModel.HoaDonViewModel;
import viewmodel.defaultViewModel.SanPhamViewModel;

public class HoaDonServices {
	private HoaDonRepository rpHoaDon = null;

	public HoaDonServices() {
		rpHoaDon = new HoaDonRepository();
	}

	public boolean insert(HoaDonViewModel vmHoaDon) {
			HoaDonDoMainModel dmHoaDon = new HoaDonDoMainModel();
			dmHoaDon.setMaNhanVien(vmHoaDon.getMaNhanVien());
			dmHoaDon.setDichVuPhatSinh(vmHoaDon.getDichVuPhatSinh());
			dmHoaDon.setMaHoaDon(vmHoaDon.getMaHoaDon());
			dmHoaDon.setGhiChu(vmHoaDon.getGhiChu());
			//Tạo hóa đơn		
			return rpHoaDon.insert(dmHoaDon);
		

	}

	public int getLastId() {
		return rpHoaDon.getLastId();
	}
	public boolean deleteById(int maHoaDon) {
		return rpHoaDon.deleteById(maHoaDon);
	}
	
}
