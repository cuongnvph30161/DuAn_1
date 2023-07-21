package services.nhanVien;

import java.util.ArrayList;
import java.util.List;

public class Demo {
	class ThongTinSanPham {
		private String maSanPham,size;
		private Integer gia,soLuong;
		public String getMaSanPham() {
			return maSanPham;
		}
		public void setMaSanPham(String maSanPham) {
			this.maSanPham = maSanPham;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public Integer getGia() {
			return gia;
		}
		public void setGia(Integer gia) {
			this.gia = gia;
		}
		public ThongTinSanPham(String maSanPham, String size, Integer gia,Integer soLuong) {
			this.maSanPham = maSanPham;
			this.size = size;
			this.gia = gia;
			this.soLuong=soLuong;
		}
		public Integer getSoLuong() {
			return soLuong;
		}
		public void setSoLuong(Integer soLuong) {
			this.soLuong = soLuong;
		}
		
	}
	private void demo() {
		List<ThongTinSanPham> lstSanPhams=new ArrayList<>();
		ThongTinSanPham ttsp=new ThongTinSanPham("MSP1", "L", 20000,1);
		lstSanPhams.add(ttsp);
		lstSanPhams.add(ttsp);
		lstSanPhams.add(ttsp);
		System.out.println("Danh sách chưa xử lý");
		for (ThongTinSanPham thongTinSanPham : lstSanPhams) {
			System.out.println(thongTinSanPham);
		}
		List<ThongTinSanPham> lstSanPhams2 =new ArrayList<>();
		
		for (ThongTinSanPham thongTinSanPham : lstSanPhams) {
			boolean check=true;
				for (ThongTinSanPham thongTinSanPham2 : lstSanPhams2) {
					thongTinSanPham2.ge
				}
			
		}
		
	}
	public static void main(String[] args) {
		Demo dm=new Demo();
		dm.demo();
	}
}
