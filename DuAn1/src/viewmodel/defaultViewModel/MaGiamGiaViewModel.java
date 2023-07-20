/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel.defaultViewModel;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class MaGiamGiaViewModel {

    private int maVoucher;
    private int phanTramGiam;
    private int donToiThieu;
    private BigDecimal giamToiDa;
    private Date hanSuDung;
    private int maNguoiTao;
    private int trangThai;

    public MaGiamGiaViewModel() {
    }

    public MaGiamGiaViewModel(int maVoucher, int phanTramGiam, int donToiThieu, BigDecimal giamToiDa, Date hanSuDung, int maNguoiTao, int trangThai) {
        this.maVoucher = maVoucher;
        this.phanTramGiam = phanTramGiam;
        this.donToiThieu = donToiThieu;
        this.giamToiDa = giamToiDa;
        this.hanSuDung = hanSuDung;
        this.maNguoiTao = maNguoiTao;
        this.trangThai = trangThai;
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

    public Date getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(Date hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    public int getMaNguoiTao() {
        return maNguoiTao;
    }

    public void setMaNguoiTao(int maNguoiTao) {
        this.maNguoiTao = maNguoiTao;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
