/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import domainmodel.Role;

/**
 *
 * @author Admin
 */
public class TaiKhoanViewModel {

    private String maTaiKhoan;
    private int maNhanVien;
    private String matKhau;
    private Role role;
    private int trangThai;

    public TaiKhoanViewModel(String maTaiKhoan, int maNhanVien, String matKhau, Role role, int trangThai) {
        this.maTaiKhoan = maTaiKhoan;
        this.maNhanVien = maNhanVien;
        this.matKhau = matKhau;
        this.role = role;
        this.trangThai = trangThai;
    }

    public TaiKhoanViewModel() {
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "TaiKhoanViewModel{" + "maTaiKhoan=" + maTaiKhoan + ", maNhanVien=" + maNhanVien + ", matKhau=" + matKhau + ", role=" + role + ", trangThai=" + trangThai + '}';
    }

}
