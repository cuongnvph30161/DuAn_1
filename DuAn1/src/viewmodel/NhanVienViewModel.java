/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class NhanVienViewModel {

    private int maNhanVien;
    private String hoVaTen;
    private Date ngaySinh;
    private String diaChi;
    private String CCCD;
    private int trangThai;
    private String email;
    private String soDienThoai;
    private String ghiChu;
    private Blob anh;
    private String chucVu;

    public NhanVienViewModel(int maNhanVien, String hoVaTen, Date ngaySinh, String diaChi, String CCCD, int trangThai, String email, String soDienThoai, String ghiChu, Blob anh, String chucVu) {
        this.maNhanVien = maNhanVien;
        this.hoVaTen = hoVaTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.CCCD = CCCD;
        this.trangThai = trangThai;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.ghiChu = ghiChu;
        this.anh = anh;
        this.chucVu = chucVu;
    }

    public NhanVienViewModel() {
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Blob getAnh() {
        return anh;
    }

    public void setAnh(Blob anh) {
        this.anh = anh;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    @Override
    public String toString() {
        return "NhanVienViewModel{" + "maNhanVien=" + maNhanVien + ", hoVaTen=" + hoVaTen + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", CCCD=" + CCCD + ", trangThai=" + trangThai + ", email=" + email + ", soDienThoai=" + soDienThoai + ", ghiChu=" + ghiChu + ", anh=" + anh + ", chucVu=" + chucVu + '}';
    }
    
    
    
}
