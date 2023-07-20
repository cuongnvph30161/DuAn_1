package services.nhanVien;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import services.defaultService.BanHoaDonService;
import services.defaultService.ChiTietHoaDonService;
import services.defaultService.HoaDonServices;
import utilities.Uhelper;
import viewmodel.defaultViewModel.BanHoaDonViewModel;
import viewmodel.defaultViewModel.ChiTietHoaDonViewModel;
import viewmodel.defaultViewModel.HoaDonViewModel;
import viewmodel.nhanVien.sanPham.Order;

/**
 *
 * @author Doanh
 */
public class SanPhamService {
	private HoaDonServices svHoaDon = null;
	private ChiTietHoaDonService svChiTietHoaDon = null;
	private BanHoaDonService svBanHoaDon = null;

	public SanPhamService() {
		svHoaDon = new HoaDonServices();
		svChiTietHoaDon = new ChiTietHoaDonService();
		svBanHoaDon = new BanHoaDonService();

	}

	public boolean themHoaDon(Order order) {
		boolean flag = order.getLstMaChiTietSanPham().size() <= 0 || order.getLstMaBan().size() <= 0
				|| Uhelper.checkErrInt(order.getDichVuPhatSinh() + "");
		boolean results = true;
		if (flag) {
			//Tạo hóa đơn
			int maHoaDon=svHoaDon.getLastId();
			HoaDonViewModel vmHoaDon = new HoaDonViewModel();
			vmHoaDon.setMaHoaDon(maHoaDon);
			vmHoaDon.setDichVuPhatSinh(BigDecimal.valueOf(order.getDichVuPhatSinh()));
			vmHoaDon.setGhiChu(order.getGhiChu());
			vmHoaDon.setMaNhanVien(order.getMaNhanVien());
			vmHoaDon.setMaVoucher(order.getMaVoucher());
			results = results && svHoaDon.insert(vmHoaDon);
			// Tạo chi tiết hóa đơn
			ChiTietHoaDonViewModel vmChiTietHoaDon=new ChiTietHoaDonViewModel();
			vmChiTietHoaDon.setMaHoaDon(maHoaDon);
			for(int maChiTietSanPham:order.getLstMaChiTietSanPham()) {
				vmChiTietHoaDon.setMaChiTietSanPham(maChiTietSanPham);
				results=results&&svChiTietHoaDon.insert(vmChiTietHoaDon);
			}
			// Tạo bàn hóa đơn
			BanHoaDonViewModel vmBanHoaDon=new BanHoaDonViewModel();
			vmBanHoaDon.setMaHoaDon(maHoaDon);
			for(int maBan:order.getLstMaBan()) {
				vmBanHoaDon.setMaBan(maBan);
				results=results&&svBanHoaDon.insert(vmBanHoaDon);
			}
			// Kiểm tra toàn vẹn dữ liệu
			if(!results) {
				for(int maBan:order.getLstMaBan()) {
					svBanHoaDon.deleteById(maBan);
				}
				svChiTietHoaDon.deleteById(maHoaDon);
		
			svHoaDon.delete(maHoaDon);
			svBanHoaDon.dele
			}
		
			

		} else {
			System.out.println("Lỗi dữ liệu đầu vào ở " + this.getClass().getName());
		}
	}

	public static void main(String[] args) {
		while (true) {
			System.out.println(new Date().getTime());

		}
	}

}
