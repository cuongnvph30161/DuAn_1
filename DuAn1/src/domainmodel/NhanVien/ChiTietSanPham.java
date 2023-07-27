/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel.NhanVien;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class ChiTietSanPham {

    private int maChiTietSanPham;
    private SanPham maSanPham;
    private String size;
    private BigDecimal gia;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(int maChiTietSanPham, SanPham maSanPham, String size, BigDecimal gia) {
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

    public SanPham getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(SanPham maSanPham) {
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
