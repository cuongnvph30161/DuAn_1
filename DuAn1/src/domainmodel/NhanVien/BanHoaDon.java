/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel.NhanVien;

/**
 *
 * @author Admin
 */
public class BanHoaDon {

    private HoaDon maHoaDon;
    private Ban maBan;

    public BanHoaDon() {
    }

    public BanHoaDon(HoaDon maHoaDon, Ban maBan) {
        this.maHoaDon = maHoaDon;
        this.maBan = maBan;
    }

    public HoaDon getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(HoaDon maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Ban getMaBan() {
        return maBan;
    }

    public void setMaBan(Ban maBan) {
        this.maBan = maBan;
    }

  
   

}
