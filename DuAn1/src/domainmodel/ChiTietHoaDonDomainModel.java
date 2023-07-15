/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
public class ChiTietHoaDonDomainModel {

    private int maHoaDon;
    private int maChiTietSanPham;
    private int soLuong;
    private BigDecimal gia;

    public ChiTietHoaDonDomainModel() {
    }

    public ChiTietHoaDonDomainModel(int maHoaDon, int maChiTietSanPham, int soLuong, BigDecimal gia) {
        this.maHoaDon = maHoaDon;
        this.maChiTietSanPham = maChiTietSanPham;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaChiTietSanPham() {
        return maChiTietSanPham;
    }

    public void setMaChiTietSanPham(int maChiTietSanPham) {
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
