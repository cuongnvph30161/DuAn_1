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
public class ChiTietSanPhamDomainModel {

    private int maChiTietSanPham;
    private int maSanPham;
    private String size;
    private BigDecimal gia;

    public ChiTietSanPhamDomainModel() {
    }

    public ChiTietSanPhamDomainModel(int maChiTietSanPham, int maSanPham, String size, BigDecimal gia) {
        this.maChiTietSanPham = maChiTietSanPham;
        this.maSanPham = maSanPham;
        this.size = size;
        this.gia = gia;
    }

    public int getMaChiTietSanPham() {
        return maChiTietSanPham;
    }

    public void setMaChiTietSanPham(int maChiTietSanPham) {
        this.maChiTietSanPham = maChiTietSanPham;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

}
