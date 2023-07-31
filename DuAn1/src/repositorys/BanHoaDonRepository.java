/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.*;
import domainmodel.BanDomainModel;
import domainmodel.BanHoaDonDomainModel;
import repositorys.iRepository.IBanHoaDonRepository;
import repositorys.iRepository.IBanRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utilities.DBConnect;
import utilities.JdbcHelper;

/**
 *
 * @author ADMIN
 */
public class BanHoaDonRepository implements IBanHoaDonRepository {
    
    static Connection con = null;
    public List<Integer> getIdHoaDonByIdBan(Integer[] id){
		Set<Integer> lstMaHoaDon=new HashSet<Integer>();
		var querry=new StringBuilder();
		querry.append("Select HoaDon.MaHoaDon from HoaDon right join Ban_HoaDon on Ban_HoaDon.MaHoaDon=HoaDon.MaHoaDon where TrangThaiThanhToan=0 and (MaBan ="+id[0]);
		for(int i=1;i<id.length;i++) {
			querry.append(" or MaBan ="+id[i]);
		}
		querry.append(")");
		System.out.println(querry);
		Connection conn=JdbcHelper.getConnection();
		try {
			Statement smt=conn.createStatement();
			var rs=smt.executeQuery(querry.toString());
			while(rs.next()) {
				lstMaHoaDon.add(rs.getInt(1));
			}
			return new ArrayList<Integer>(lstMaHoaDon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
    @Override
    public List<BanHoaDonDomainModel> getList() {
     try {
            List<BanHoaDonDomainModel> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "SELECT MaHoaDon,MaBan FROM Ban_HoaDon";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
               lst.add(new BanHoaDonDomainModel(rs.getInt(1),
                       rs.getInt(2)));
            }
            return lst;
        } catch (Exception e) {
        }
        return null;   
    }

	@Override
	public List<BanHoaDonDomainModel> getAll(int... page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BanHoaDonDomainModel getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(BanHoaDonDomainModel object) {
		return JdbcHelper.update("INSERT"
				+ " INTO Ban_HoaDon(MaHoaDon, MaBan)"
				+ "VALUES    (?,?)",object.getMaHoaDon(),object.getMaBan())==1;
	}

	@Override
	public boolean update(BanHoaDonDomainModel object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		try {
			return JdbcHelper.update("DELETE ChiTietHoaDon where MaHoaDon=?", id)>=0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<BanHoaDonDomainModel> getBySql(String sql, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
   
}
