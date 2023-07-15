/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;
import java.sql.Date;
import java.sql.Blob;
/**
 *
 * @author ADMIN
 */
public class NhanVienViewModel {
    private int maNhanVien;
    private Date ngaySinh;
    private String diaChi;
    private String CCCD;
    private int trangThai;
    private String email;
    private String soDienThoai;
    private String ghiChu;
    private Blob anh;
    private String chucVu;

    public NhanVienViewModel() {
    }

    public NhanVienViewModel(int maNhanVien, Date ngaySinh, String diaChi, String CCCD, int trangThai, String email, String soDienThoai, String ghiChu, Blob anh, String chucVu) {
        this.maNhanVien = maNhanVien;
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
    
    
}
