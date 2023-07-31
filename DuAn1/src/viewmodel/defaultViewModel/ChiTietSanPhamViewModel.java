/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel.defaultViewModel;

import java.math.BigDecimal;
import java.sql.Blob;

/**
 *
 * @author ADMIN
 */
public class ChiTietSanPhamViewModel {

    private int maChiTietSanPham;
    private int maSanPham;
    private String tenSanPham;
    private int trangThai;
    private String size;
    private BigDecimal gia;
    private String motTa;
    private Blob anh;

    public ChiTietSanPhamViewModel() {
    }

    public ChiTietSanPhamViewModel(int maSanPham, String tenSanPham, int trangThai, String size, BigDecimal gia, String motTa, Blob anh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.trangThai = trangThai;
        this.size = size;
        this.gia = gia;
        this.motTa = motTa;
        this.anh = anh;
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

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
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

    public String getMotTa() {
        return motTa;
    }

    public void setMotTa(String motTa) {
        this.motTa = motTa;
    }

    public Blob getAnh() {
        return anh;
    }

    public void setAnh(Blob anh) {
        this.anh = anh;
    }

}
