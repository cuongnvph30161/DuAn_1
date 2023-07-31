package viewmodel.nhanVien.quanLyBan;

import java.util.Date;
import java.util.List;

import viewmodel.defaultViewModel.ChiTietHoaDonViewModel;

public class HoaDon {
	private int maHoaDon,tongTien,tongThanhToan,trangThaiOder,voucher,dichVuPhatSinh;
	private Date thoiGian;
	private List<ChiTietHoaDonViewModel> lstCTHD;
	private int maNhanVien;
	private String ghiChu;
	public int getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public int getTongTien() {
		return tongTien;
	}
	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
	public int getTongThanhToan() {
		return tongThanhToan;
	}
	public void setTongThanhToan(int tongThanhToan) {
		this.tongThanhToan = tongThanhToan;
	}
	public int getTrangThaiOder() {
		return trangThaiOder;
	}
	public void setTrangThaiOder(int trangThai) {
		this.trangThaiOder = trangThai;
	}
	public int getVoucher() {
		return voucher;
	}
	public void setVoucher(int voucher) {
		this.voucher = voucher;
	}
	public int getDichVuPhatSinh() {
		return dichVuPhatSinh;
	}
	public void setDichVuPhatSinh(int dichVuPhatSinh) {
		this.dichVuPhatSinh = dichVuPhatSinh;
	}
	public List<ChiTietHoaDonViewModel> getLstCTHD() {
		return lstCTHD;
	}
	public void setLstCTHD(List<ChiTietHoaDonViewModel> lstCTHD) {
		this.lstCTHD = lstCTHD;
	}
	public Date getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}
	public int getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
}
