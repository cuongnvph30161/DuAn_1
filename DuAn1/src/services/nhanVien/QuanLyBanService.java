package services.nhanVien;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domainmodel.HoaDonDoMainModel;
import repositorys.BanHoaDonRepository;
import repositorys.ChiTietHoaDonRepository;
import repositorys.HoaDonRepository;
import utilities.DBill;
import viewmodel.defaultViewModel.ChiTietHoaDonViewModel;
import viewmodel.nhanVien.quanLyBan.HoaDon;

public class QuanLyBanService {
	private ChiTietHoaDonRepository rpChiTietHoaDon = new ChiTietHoaDonRepository();
	private BanHoaDonRepository rpBanHoaDon = new BanHoaDonRepository();
	private HoaDonRepository rpHoaDon = new HoaDonRepository();
	
	public List<viewmodel.nhanVien.quanLyBan.HoaDon> getLstHoaDonTheoBan(Integer[] lstMaBan) {
		var lstIdHoaDon = rpBanHoaDon.getIdHoaDonByIdBan(lstMaBan);
		List<viewmodel.nhanVien.quanLyBan.HoaDon> lstHoaDonNV = new ArrayList<>();
		for (Integer maHoaDon : lstIdHoaDon) {
			System.out.println(maHoaDon);
			HoaDonDoMainModel dmHoaDon = rpHoaDon.getById(maHoaDon);
			viewmodel.nhanVien.quanLyBan.HoaDon hoaDon = new viewmodel.nhanVien.quanLyBan.HoaDon();
			hoaDon.setMaHoaDon(dmHoaDon.getMaHoaDon());
			hoaDon.setGhiChu(dmHoaDon.getGhiChu());
			hoaDon.setMaNhanVien(dmHoaDon.getMaNhanVien());
			hoaDon.setDichVuPhatSinh(dmHoaDon.getDichVuPhatSinh().intValue());
			hoaDon.setLstCTHD(rpChiTietHoaDon.getLstByMaHoaDon(maHoaDon).stream().map(dmCTHD -> {
				ChiTietHoaDonViewModel cthd = new ChiTietHoaDonViewModel();
				cthd.setGia(dmCTHD.getGia());
				cthd.setMaChiTietSanPham(dmCTHD.getMaChiTietSanPham());
				cthd.setMaHoaDon(dmCTHD.getMaHoaDon());
				cthd.setSoLuong(dmCTHD.getSoLuong());
				return cthd;
			}).collect(Collectors.toList()));
			hoaDon.setTrangThaiOder(dmHoaDon.getTrangThaiOrder());
			hoaDon.setThoiGian(dmHoaDon.getThoiGian());
			lstHoaDonNV.add(hoaDon);
		}
		return lstHoaDonNV;
	}

	public boolean thanhToanHoaDon(Integer[] maHoaDon, int maVouCher) {
		new DBill().printHoaDon(maHoaDon, maVouCher);
		return rpHoaDon.thanhToanHoaDon(maHoaDon, maVouCher);
	}

}
