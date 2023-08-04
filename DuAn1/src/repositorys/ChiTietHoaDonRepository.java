/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.*;
import domainmodel.BanDomainModel;
import domainmodel.ChiTietHoaDonDomainModel;
import domainmodel.HoaDonDoMainModel;
import domainmodel.NhanVien.ChiTietHoaDon;
import domainmodel.NhanVien.HoaDon;
import repositorys.iRepository.IBanRepository;
import repositorys.iRepository.IChiTietHoaDonRepository;

import java.util.ArrayList;
import java.util.List;
import utilities.DBConnect;
import utilities.JdbcHelper;

/**
 *
 * @author ADMIN
 */
public class ChiTietHoaDonRepository implements IChiTietHoaDonRepository {

    static Connection con = null;
    public List<ChiTietHoaDonDomainModel> getLstByMaHoaDon(int maHoaDon){
    	List<ChiTietHoaDonDomainModel> lstChiTietHoaDon=new ArrayList<>();
    	
    	try {
			ResultSet rs=JdbcHelper.query("Select MaChiTietSanPham,SoLuong,Gia from ChiTietHoaDon where MaHoaDon=?", maHoaDon);
			while(rs.next()) {
				ChiTietHoaDonDomainModel dmCthd=new ChiTietHoaDonDomainModel();
		    	dmCthd.setMaHoaDon(maHoaDon);
				dmCthd.setGia(rs.getBigDecimal(3));
				dmCthd.setSoLuong(rs.getInt(2));
				dmCthd.setMaChiTietSanPham(rs.getInt(1));
				lstChiTietHoaDon.add(dmCthd);
			}
			return lstChiTietHoaDon;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    	
    }
    @Override
    public List<ChiTietHoaDonDomainModel> getList() {
        try {
            List<ChiTietHoaDonDomainModel> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "SELECT MaHoaDon,MaChiTietSanPham,SoLuong,Gia FROM ChiTietHoaDon";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                lst.add(new ChiTietHoaDonDomainModel(rs.getInt(1),
                        rs.getInt(2), rs.getInt(3),
                        rs.getBigDecimal(4)));

            }
            return lst;
        } catch (Exception e) {
        }
        return null;

    }
    
     public List<ChiTietHoaDon> getDuLieuBan() {
        try {
            List<ChiTietHoaDon> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "select HoaDon.MaHoaDon,HoaDon.ThoiGian,ChiTietHoaDon.SoLuong,ChiTietHoaDon.Gia,HoaDon.TrangThaiOrder from HoaDon \n"
                    + "inner join ChiTietHoaDon on HoaDon.MaHoaDon=ChiTietHoaDon.MaHoaDon";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getInt(1));
                hd.setThoiGian(rs.getTimestamp(2));
                hd.setTrangThaiOrder(rs.getInt(5));
                cthd.setMaHoaDon(hd);
                cthd.setSoLuong(rs.getInt(3));
                cthd.setGia(rs.getBigDecimal(4));
                lst.add(cthd);

            }
            return lst;
        } catch (Exception e) {
        }
        return null;

    }

   

    @Override
    public List<ChiTietHoaDonDomainModel> getAll(int... page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChiTietHoaDonDomainModel getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean insert(ChiTietHoaDonDomainModel object) {
        String querry="INSERT  INTO  ChiTietHoaDon(MaHoaDon, MaChiTietSanPham, SoLuong, Gia) VALUES    (?,?,?,?)";
        return JdbcHelper.update(querry,object.getMaHoaDon(),object.getMaChiTietSanPham(),object.getSoLuong(),object.getGia())==1;
    }

    @Override
    public boolean update(ChiTietHoaDonDomainModel object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<ChiTietHoaDonDomainModel> getBySql(String sql, Object... args) {
        // TODO Auto-generated method stub
        return null;
    }
	public ResultSet getBill(Integer maHoaDon) {
		try {
			return JdbcHelper.query("select TenSanPham,ChiTietSanPham.Size,ChiTietHoaDon.SoLuong,ChiTietHoaDon.Gia from ChiTietHoaDon left join ChiTietSanPham on ChiTietSanPham.MaChiTietSanPham=ChiTietHoaDon.MaChiTietSanPham left join SanPham on ChiTietSanPham.MaSanPham= SanPham.MaSanPham where MaHoaDon=?", maHoaDon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
