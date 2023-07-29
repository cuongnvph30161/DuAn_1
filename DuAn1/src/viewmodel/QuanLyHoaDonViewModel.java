/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author ttuan
 */
public class QuanLyHoaDonViewModel {
    private int maHoaDon;
    private int maNhanVien;
    private Timestamp thoiGian;
    private int trangThaiThanhToan;
    private int trangThaiOrder;
    private int maVoucher;
    private BigDecimal dichVuPhatSinh;
    private String ghiChu;
    
    public QuanLyHoaDonViewModel() {
    }

    public QuanLyHoaDonViewModel(int maHoaDon, int maNhanVien, Timestamp thoiGian, int trangThaiThanhToan, int trangThaiOrder, int maVoucher, BigDecimal dichVuPhatSinh, String ghiChu) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.thoiGian = thoiGian;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.trangThaiOrder = trangThaiOrder;
        this.maVoucher = maVoucher;
        this.dichVuPhatSinh = dichVuPhatSinh;
        this.ghiChu = ghiChu;
        
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Timestamp getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(int trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public int getTrangThaiOrder() {
        return trangThaiOrder;
    }

    public void setTrangThaiOrder(int trangThaiOrder) {
        this.trangThaiOrder = trangThaiOrder;
    }

    public int getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(int maVoucher) {
        this.maVoucher = maVoucher;
    }

    public BigDecimal getDichVuPhatSinh() {
        return dichVuPhatSinh;
    }

    public void setDichVuPhatSinh(BigDecimal dichVuPhatSinh) {
        this.dichVuPhatSinh = dichVuPhatSinh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    

    

    
    

    
    
}
