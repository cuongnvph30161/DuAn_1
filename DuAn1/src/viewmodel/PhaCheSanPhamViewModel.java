/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;
import java.sql.Blob;
/**
 *
 * @author ADMIN
 */
public class PhaCheSanPhamViewModel {
    private Blob Anh;
    private String ten;
    private int TrangThai;

    public PhaCheSanPhamViewModel() {
    }

    public PhaCheSanPhamViewModel(Blob Anh, String ten, int TrangThai) {
        this.Anh = Anh;
        this.ten = ten;
        this.TrangThai = TrangThai;
    }

    public Blob getAnh() {
        return Anh;
    }

    public void setAnh(Blob Anh) {
        this.Anh = Anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    public String getTrangThaiSP(){
        if(TrangThai==0){
            return "Hết hàng";
        }
        if(TrangThai==1){
            return "Còn hàng";
        }
        if(TrangThai==2){
            return"Ngừng kinh doanh";
        }
        return null;
    }
}
