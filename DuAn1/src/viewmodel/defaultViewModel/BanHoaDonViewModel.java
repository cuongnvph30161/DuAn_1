/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel.defaultViewModel;

/**
 *
 * @author ADMIN
 */
public class BanHoaDonViewModel {
    private int maHoaDon;
    private int maBan;

    public BanHoaDonViewModel() {
    }

    public BanHoaDonViewModel(int maHoaDon, int maBan) {
        this.maHoaDon = maHoaDon;
        this.maBan = maBan;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }
    
}
