/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class MaGiamGiaDomainModel {

    private int maVoucher;
    private int phanTramGiam;
    private int donToiThieu;
    private BigDecimal giamToiDa;
    private Date NgayBatDau;
    private Date NgayKetThuc;
    private int maNguoiTao;
    private int SoLuong;

    public MaGiamGiaDomainModel() {
    }

    public MaGiamGiaDomainModel(int maVoucher, int phanTramGiam, int donToiThieu, BigDecimal giamToiDa, Date NgayBatDau, Date NgayKetThuc, int maNguoiTao, int SoLuong) {
        this.maVoucher = maVoucher;
        this.phanTramGiam = phanTramGiam;
        this.donToiThieu = donToiThieu;
        this.giamToiDa = giamToiDa;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.maNguoiTao = maNguoiTao;
        this.SoLuong = SoLuong;
    }

    public int getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(int maVoucher) {
        this.maVoucher = maVoucher;
    }

    public int getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(int phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public int getDonToiThieu() {
        return donToiThieu;
    }

    public void setDonToiThieu(int donToiThieu) {
        this.donToiThieu = donToiThieu;
    }

    public BigDecimal getGiamToiDa() {
        return giamToiDa;
    }

    public void setGiamToiDa(BigDecimal giamToiDa) {
        this.giamToiDa = giamToiDa;
    }

    public int getMaNguoiTao() {
        return maNguoiTao;
    }

    public void setMaNguoiTao(int maNguoiTao) {
        this.maNguoiTao = maNguoiTao;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

}
