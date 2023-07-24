/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

/**
 *
 * @author ADMIN
 */
public class BanDomainModel {

    private int maBan;
    private String tenBan;
    private int tang;
    private int trangThai;

    public BanDomainModel() {
    }

    public BanDomainModel(int maBan, String tenBan, int tang, int trangThai) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.tang = tang;
        this.trangThai = trangThai;
    }

    public BanDomainModel(String tenBan, int tang, int trangThai) {

        this.tenBan = tenBan;
        this.tang = tang;
        this.trangThai = trangThai;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
