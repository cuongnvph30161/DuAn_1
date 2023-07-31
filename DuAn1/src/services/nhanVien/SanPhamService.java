package services.nhanVien;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

import services.BanService;
import services.ChiTietSanPhamService;
import services.defaultService.BanHoaDonService;
import services.defaultService.ChiTietHoaDonService;
import services.defaultService.HoaDonServices;
import utilities.Uhelper;
import viewmodel.defaultViewModel.BanHoaDonViewModel;
import viewmodel.defaultViewModel.ChiTietHoaDonViewModel;
import viewmodel.defaultViewModel.ChiTietSanPhamViewModel;
import viewmodel.defaultViewModel.HoaDonViewModel;
import viewmodel.defaultViewModel.SanPhamViewModel;
import viewmodel.nhanVien.sanPham.Order;
import views.element.SanPham;

/**
 *
 * @author Doanh
 */
public class SanPhamService {
	private HoaDonServices svHoaDon = null;
	private ChiTietHoaDonService svChiTietHoaDon = null;
	private BanHoaDonService svBanHoaDon = null;
	private services.SanPhamService svSanPham=null;
	private ChiTietSanPhamService svCTSP=null;
	private BanService svBan=null;

	public SanPhamService() {
		svHoaDon = new HoaDonServices();
		svChiTietHoaDon = new ChiTietHoaDonService();
		svBanHoaDon = new BanHoaDonService();
		svSanPham=new services.SanPhamService();
		svCTSP=new ChiTietSanPhamService();
		svBan=new BanService();
		

	}

	public boolean themHoaDon(Order order) {
		boolean flag = order.getLstChiTietHoaDonViewModels().size() <= 0 || order.getLstMaBan().size() <= 0
				|| Uhelper.checkErrInt(order.getDichVuPhatSinh() + "");
		boolean results = true;
		if (!flag) {
			// Tạo hóa đơn
			int maHoaDon = svHoaDon.getLastId() + 1;
			HoaDonViewModel vmHoaDon = new HoaDonViewModel();
			vmHoaDon.setMaHoaDon(maHoaDon);
			vmHoaDon.setDichVuPhatSinh(BigDecimal.valueOf(order.getDichVuPhatSinh()));
			vmHoaDon.setGhiChu(order.getGhiChu());
			vmHoaDon.setMaNhanVien(order.getMaNhanVien());
			vmHoaDon.setMaVoucher(order.getMaVoucher());
			results = results && svHoaDon.insert(vmHoaDon);
			// Tạo chi tiết hóa đơn
			
			for (ChiTietHoaDonViewModel vmChiTietHoaDon: order.getLstChiTietHoaDonViewModels()) {
				vmChiTietHoaDon.setMaHoaDon(maHoaDon);
				results = results && svChiTietHoaDon.insert(vmChiTietHoaDon);
				if(!results) break;
			}
			// Tạo bàn hóa đơn
			BanHoaDonViewModel vmBanHoaDon = new BanHoaDonViewModel();
			vmBanHoaDon.setMaHoaDon(maHoaDon);
			for (int maBan : order.getLstMaBan()) {
				svBan.actives(maBan,true);
				vmBanHoaDon.setMaBan(maBan);
				boolean bl=svBanHoaDon.insert(vmBanHoaDon);
				results = results && bl;
			}
			// Kiểm tra toàn vẹn dữ liệu
			if (!results) {
				
				for (int maBan : order.getLstMaBan()) {
					
					svBan.actives(maBan,false);
				}
				
				svChiTietHoaDon.deleteById(maHoaDon);
				svHoaDon.deleteById(maHoaDon);
			}

		} else {
			System.out.println("Lỗi dữ liệu đầu vào ở " + this.getClass().getName());
		}
		
		return results;
	}

	public static void main(String[] args) {
		List<Integer> lstIntegers = new ArrayList<>();
		lstIntegers.add(1000);
		ChiTietHoaDonViewModel vmChiTietHoaDon=new ChiTietHoaDonViewModel(0, 1002, 2,BigDecimal.valueOf(20000));
		List<ChiTietHoaDonViewModel>lstChiTietHoaDonViewModels=new ArrayList<>();
		lstChiTietHoaDonViewModels.add(vmChiTietHoaDon);
		Order od = new Order();
		od.setMaNhanVien(1001);
		od.setDichVuPhatSinh(0);
		od.setMaVoucher(0);
		od.setLstMaBan(lstIntegers);
		od.setLstChiTietHoaDonViewModels(lstChiTietHoaDonViewModels);
		
		od.setGhiChu("GhiChuc");

		SanPhamService svSP = new SanPhamService();
		System.out.println(svSP.themHoaDon(od));;

	}
	public List<SanPham> getAllSanPham(DefaultTableModel model){
		return svSanPham.getAll().stream().map(vmSP->{
			System.out.println(this.getClass().getName());
			return new SanPham(vmSP.getMaSanPham(),vmSP.getHinh(),vmSP.getTenSanPham(),vmSP.getTrangThai()==1,model);
		}).collect(Collectors.toList());
	}
	public List<SanPham> getAllSanPham(String searchKey,DefaultTableModel model){
		return svSanPham.getAll(searchKey).stream().map(vmSP->{
			System.out.println(this.getClass().getName());
			return new SanPham(vmSP.getMaSanPham(),vmSP.getHinh(),vmSP.getTenSanPham(),vmSP.getTrangThai()==1,model);
		}).collect(Collectors.toList());
	}

}
