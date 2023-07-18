/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NhanVienHoaDonViewModel {

    private int maHoaDon;
    private int maGiamGia;
    private String maNguoiTao;
    private String tenBan;
    private String tenNguoiTao;
    private Timestamp thoiGian;
    private BigDecimal tongThanhToan;
    private BigDecimal dichVuPhatSinh;
    private int trangThai;
    private String ghiChu;
    List<PhaCheLichSuDanhSachSanPhamViewmodel> listSP;

    public NhanVienHoaDonViewModel() {
    }

    public NhanVienHoaDonViewModel(int maHoaDon, int maGiamGia, String maNguoiTao, String tenBan, String tenNguoiTao, Timestamp thoiGian, BigDecimal tongThanhToan, BigDecimal dichVuPhatSinh, int trangThai, String ghiChu, List<PhaCheLichSuDanhSachSanPhamViewmodel> listSP) {
        this.maHoaDon = maHoaDon;
        this.maGiamGia = maGiamGia;
        this.maNguoiTao = maNguoiTao;
        this.tenBan = tenBan;
        this.tenNguoiTao = tenNguoiTao;
        this.thoiGian = thoiGian;
        this.tongThanhToan = tongThanhToan;
        this.dichVuPhatSinh = dichVuPhatSinh;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
        this.listSP = listSP;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(int maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public String getMaNguoiTao() {
        return maNguoiTao;
    }

    public void setMaNguoiTao(String maNguoiTao) {
        this.maNguoiTao = maNguoiTao;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public String getTenNguoiTao() {
        return tenNguoiTao;
    }

    public void setTenNguoiTao(String tenNguoiTao) {
        this.tenNguoiTao = tenNguoiTao;
    }

    public Timestamp getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        this.thoiGian = thoiGian;
    }

    public BigDecimal getTongThanhToan() {
        return tongThanhToan;
    }

    public void setTongThanhToan(BigDecimal tongThanhToan) {
        this.tongThanhToan = tongThanhToan;
    }

    public BigDecimal getDichVuPhatSinh() {
        return dichVuPhatSinh;
    }

    public void setDichVuPhatSinh(BigDecimal dichVuPhatSinh) {
        this.dichVuPhatSinh = dichVuPhatSinh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public List<PhaCheLichSuDanhSachSanPhamViewmodel> getListSP() {
        return listSP;
    }

    public void setListSP(List<PhaCheLichSuDanhSachSanPhamViewmodel> listSP) {
        this.listSP = listSP;
    }

}