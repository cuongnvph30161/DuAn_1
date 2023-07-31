package viewmodel.nhanVien.sanPham;

import java.util.List;

import viewmodel.defaultViewModel.ChiTietHoaDonViewModel;

public class Order {
	private int maNhanVien ,dichVuPhatSinh,maVoucher;
	private List<Integer> lstMaBan;
	private List<ChiTietHoaDonViewModel> lstChiTietHoaDonViewModels;
	private String ghiChu;
	public int getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public int getDichVuPhatSinh() {
		return dichVuPhatSinh;
	}
	public void setDichVuPhatSinh(int dichVuPhatSinh) {
		this.dichVuPhatSinh = dichVuPhatSinh;
	}
	public List<Integer> getLstMaBan() {
		return lstMaBan;
	}
	public void setLstMaBan(List<Integer> lstMaBan) {
		this.lstMaBan = lstMaBan;
	}

	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public int getMaVoucher() {
		return maVoucher;
	}
	public void setMaVoucher(int maVoucher) {
		this.maVoucher = maVoucher;
	}
	public Order(int maNhanVien, int dichVuPhatSinh, int maVoucher, List<Integer> lstMaBan,
			List<ChiTietHoaDonViewModel> lstChiTietHoaDonViewModels, String ghiChu) {
		super();
		this.maNhanVien = maNhanVien;
		this.dichVuPhatSinh = dichVuPhatSinh;
		this.maVoucher = maVoucher;
		this.lstMaBan = lstMaBan;
		this.lstChiTietHoaDonViewModels = lstChiTietHoaDonViewModels;
		this.ghiChu = ghiChu;
	}
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	public List<ChiTietHoaDonViewModel> getLstChiTietHoaDonViewModels() {
		return lstChiTietHoaDonViewModels;
	}
	@Override
	public String toString() {
		return "Order [maNhanVien=" + maNhanVien + ", dichVuPhatSinh=" + dichVuPhatSinh + ", maVoucher=" + maVoucher
				+ ", lstMaBan=" + lstMaBan + ", lstChiTietHoaDonViewModels=" + lstChiTietHoaDonViewModels + ", ghiChu="
				+ ghiChu + "]";
	}
	public void setLstChiTietHoaDonViewModels(List<ChiTietHoaDonViewModel> lstChiTietHoaDonViewModels) {
		this.lstChiTietHoaDonViewModels = lstChiTietHoaDonViewModels;
	}
	
	
}
