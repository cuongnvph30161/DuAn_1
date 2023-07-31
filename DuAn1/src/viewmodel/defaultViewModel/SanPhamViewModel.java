/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel.defaultViewModel;

import java.sql.*;

import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class SanPhamViewModel {

    private int maSanPham;
    private String tenSanPham;
    private int trangThai;
    private String motTa;
    private Blob anh;
    private ImageIcon hinh;

    public SanPhamViewModel() {
    }

    public SanPhamViewModel(int maSanPham, String tenSanPham, int trangThai, String motTa, Blob anh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.trangThai = trangThai;
        this.motTa = motTa;
        this.anh = anh;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMotTa() {
        return motTa;
    }

    public void setMotTa(String motTa) {
        this.motTa = motTa;
    }

    public Blob getAnh() {
        return anh;
    }

    public void setAnh(Blob anh) {
        this.anh = anh;
    }

    public String getStatus() {
        if (trangThai == 0) {
            return "Hết hàng";
        }
        if (trangThai == 1) {
            return "Còn hàng";
        }
        if (trangThai == 2) {
            return "Ngừng kinh doanh";
        }
        return null;
    }

	public ImageIcon getHinh() {
		return hinh;
	}

	public void setHinh(ImageIcon hinh) {
		this.hinh = hinh;
	}

}
