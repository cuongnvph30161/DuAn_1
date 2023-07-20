package viewmodel.nhanVien.sanPham;

import java.util.List;

public class Order {
	private int maNhanVien ,dichVuPhatSinh,maVoucher;
	private List<Integer> lstMaBan,lstMaChiTietSanPham;
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
	public List<Integer> getLstMaChiTietSanPham() {
		return lstMaChiTietSanPham;
	}
	public void setLstMaChiTietSanPham(List<Integer> maChiTietSanPham) {
		this.lstMaChiTietSanPham = maChiTietSanPham;
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
	
}
