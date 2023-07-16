/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class PhaCheLichSuViewModel {

    private int maHoaDon;
    private String tenBan;
    private int tang;
    private Timestamp thoiGian;
    private String ghiChu;
    
    private int maSanPham;
    private String tenSanPham;
    private String size;
    private int  soLuong;

    public PhaCheLichSuViewModel() {
    }

    public PhaCheLichSuViewModel(int maHoaDon, String tenBan, int tang, Timestamp thoiGian, String ghiChu, int maSanPham, String tenSanPham, String size, int soLuong) {
        this.maHoaDon = maHoaDon;
        this.tenBan = tenBan;
        this.tang = tang;
        this.thoiGian = thoiGian;
        this.ghiChu = ghiChu;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.size = size;
        this.soLuong = soLuong;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public int getTang() {
        return tang;
    }

    public void setTang(int tang) {
        this.tang = tang;
    }

    public Timestamp getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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
    
    
    

}
