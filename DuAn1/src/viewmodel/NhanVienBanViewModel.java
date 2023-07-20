/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import domainmodel.NhanVien.HoaDon;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class NhanVienBanViewModel {

    private HoaDon maHoaDon;
    private Timestamp thoiGian;
    private int trangThaiOrder;
    private String chiTiet;
    private int soLuong;
    private BigDecimal gia;
    private BigDecimal tongThanhToan;

    public NhanVienBanViewModel() {
    }

    public NhanVienBanViewModel(HoaDon maHoaDon, Timestamp thoiGian, int trangThaiOrder, String chiTiet, int soLuong, BigDecimal gia, BigDecimal tongThanhToan) {
        this.maHoaDon = maHoaDon;
        this.thoiGian = thoiGian;
        this.trangThaiOrder = trangThaiOrder;
        this.chiTiet = chiTiet;
        this.soLuong = soLuong;
        this.gia = gia;
        this.tongThanhToan = tongThanhToan;
    }

    public HoaDon getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(HoaDon maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Timestamp getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getTrangThaiOrder() {
        return trangThaiOrder;
    }

    public void setTrangThaiOrder(int trangThaiOrder) {
        this.trangThaiOrder = trangThaiOrder;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
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

    public BigDecimal getTongThanhToan() {
        return tongThanhToan;
    }

    public void setTongThanhToan(BigDecimal tongThanhToan) {
        this.tongThanhToan = tongThanhToan;
    }

   

   

}
