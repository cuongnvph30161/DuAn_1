/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel.NhanVien;

import domainmodel.ChiTietSanPhamDomainModel;
import domainmodel.HoaDonDoMainModel;
import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDon {

    private HoaDon maHoaDon;
    private ChiTietSanPham maChiTietSanPham;
    private int soLuong;
    private BigDecimal gia;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(HoaDon maHoaDon, ChiTietSanPham maChiTietSanPham, int soLuong, BigDecimal gia) {
        this.maHoaDon = maHoaDon;
        this.maChiTietSanPham = maChiTietSanPham;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public HoaDon getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(HoaDon maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public ChiTietSanPham getMaChiTietSanPham() {
        return maChiTietSanPham;
    }

    public void setMaChiTietSanPham(ChiTietSanPham maChiTietSanPham) {
        this.maChiTietSanPham = maChiTietSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

}
