/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

/**
 *
 * @author ADMIN
 */
public class QuanLyBanViewmodel {
    private int maBan;
    private String tenBan;
    private int tang;

    public QuanLyBanViewmodel() {
    }

    public QuanLyBanViewmodel(int maBan, String tenBan, int tang) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.tang = tang;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
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
    
}
