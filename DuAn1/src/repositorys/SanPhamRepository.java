/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.*;
import domainmodel.BanDomainModel;
import domainmodel.ChiTietHoaDonDomainModel;
import domainmodel.ChiTietSanPhamDomainModel;
import domainmodel.SanPhamDomainModel;
import repositorys.iRepository.IBanRepository;
import repositorys.iRepository.IChiTietHoaDonRepository;
import repositorys.iRepository.IChiTietSanPhamRepository;
import repositorys.iRepository.ISanPhamRepository;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import utilities.DBConnect;
import utilities.JdbcHelper;

/**
 *
 * @author ADMIN
 */
public class SanPhamRepository implements ISanPhamRepository {

    static Connection con = null;

    @Override
    public List<SanPhamDomainModel> getList() {
        try {
            List<SanPhamDomainModel> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "SELECT MaSanPham,TenSanPham,TrangThai,MoTa,Anh FROM SanPham";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                lst.add(new SanPhamDomainModel(rs.getInt(1),
                        rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getBlob(5)));
            }
            return lst;
        } catch (Exception e) {
        }
        return null;

    }

    @Override
	public List<SanPhamDomainModel> getAll(int... page) {
		List<SanPhamDomainModel>lstDMSP=new ArrayList<SanPhamDomainModel>();
		try {
			ResultSet rs=JdbcHelper.query("SELECT MaSanPham,TenSanPham,TrangThai,MoTa,Anh FROM SanPham");
			SanPhamDomainModel dmSanPham=null;
			while (rs.next()) {
				dmSanPham=new SanPhamDomainModel();
				dmSanPham.setMaSanPham(rs.getInt(1));
				dmSanPham.setTenSanPham(rs.getString(2));
				dmSanPham.setTrangThai(rs.getInt(3));
				dmSanPham.setMotTa(rs.getString(4));
				dmSanPham.setAnhSanPham(new ImageIcon(ImageIO.read(rs.getBinaryStream(5)).getScaledInstance(210, 240, Image.SCALE_SMOOTH)));
				lstDMSP.add(dmSanPham);
			}
			return lstDMSP;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
    public List<SanPhamDomainModel> getAll(String searchKey) {
		List<SanPhamDomainModel>lstDMSP=new ArrayList<SanPhamDomainModel>();
		try {
			ResultSet rs=JdbcHelper.query("SELECT MaSanPham,TenSanPham,TrangThai,MoTa,Anh FROM SanPham where TenSanPham like '%"+searchKey+"%'");
			SanPhamDomainModel dmSanPham=null;
			while (rs.next()) {
				dmSanPham=new SanPhamDomainModel();
				dmSanPham.setMaSanPham(rs.getInt(1));
				dmSanPham.setTenSanPham(rs.getString(2));
				dmSanPham.setTrangThai(rs.getInt(3));
				dmSanPham.setMotTa(rs.getString(4));
				dmSanPham.setAnhSanPham(new ImageIcon(ImageIO.read(rs.getBinaryStream(5)).getScaledInstance(210, 240, Image.SCALE_SMOOTH)));
				lstDMSP.add(dmSanPham);
			}
			return lstDMSP;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}


	@Override
	public SanPhamDomainModel getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(SanPhamDomainModel object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(SanPhamDomainModel object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SanPhamDomainModel> getBySql(String sql, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public List<SanPhamDomainModel> getSanPhamTheoTen(String ten) {
        try {
            List<SanPhamDomainModel> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "select MaSanPham,TenSanPham,TrangThai,MoTa,Anh from SanPham where TenSanPham like N'"+ten+"%'";
            Statement sta = con.createStatement();
            
            ResultSet rs = sta.executeQuery(lenh);
            while (rs.next()) {
                lst.add(new SanPhamDomainModel(rs.getInt("MaSanPham"),
                        rs.getString("TenSanPham"), rs.getInt("TrangThai"),
                        rs.getString("MoTa"), rs.getBlob("Anh")));
            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

}
