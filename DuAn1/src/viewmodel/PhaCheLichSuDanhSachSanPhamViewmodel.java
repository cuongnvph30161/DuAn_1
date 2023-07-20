/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
public class PhaCheLichSuDanhSachSanPhamViewmodel {

    private int maHoaDon;
    private int maSanPham;
    private String tenSanPham;
    private String size;
    private int soLuong;
    private BigDecimal giaBigDecimal;

    public PhaCheLichSuDanhSachSanPhamViewmodel() {
    }

    public PhaCheLichSuDanhSachSanPhamViewmodel(int maHoaDon, int maSanPham, String tenSanPham, String size, int soLuong) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.size = size;
        this.soLuong = soLuong;
    }

    public PhaCheLichSuDanhSachSanPhamViewmodel(int maSanPham, String tenSanPham, String size, int soLuong) {

        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.size = size;
        this.soLuong = soLuong;
    }

    public PhaCheLichSuDanhSachSanPhamViewmodel(int maHoaDon, int maSanPham, String tenSanPham, String size, int soLuong, BigDecimal giaBigDecimal) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.size = size;
        this.soLuong = soLuong;
        this.giaBigDecimal = giaBigDecimal;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaBigDecimal() {
        return giaBigDecimal;
    }

    public void setGiaBigDecimal(BigDecimal giaBigDecimal) {
        this.giaBigDecimal = giaBigDecimal;
    }

}
