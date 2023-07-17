/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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

    List<PhaCheLichSuDanhSachSanPhamViewmodel> danhSachSP;

    public PhaCheLichSuViewModel() {
    }

    public PhaCheLichSuViewModel(int maHoaDon, String tenBan, int tang, Timestamp thoiGian, String ghiChu, List<PhaCheLichSuDanhSachSanPhamViewmodel> danhSachSP) {
        this.maHoaDon = maHoaDon;
        this.tenBan = tenBan;
        this.tang = tang;
        this.thoiGian = thoiGian;
        this.ghiChu = ghiChu;
        this.danhSachSP = danhSachSP;
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

    public List<PhaCheLichSuDanhSachSanPhamViewmodel> getDanhSachSP() {
        return danhSachSP;
    }

    public void setDanhSachSP(List<PhaCheLichSuDanhSachSanPhamViewmodel> danhSachSP) {
        this.danhSachSP = danhSachSP;
    }

}
