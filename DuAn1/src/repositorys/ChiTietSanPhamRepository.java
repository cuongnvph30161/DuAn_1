/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.*;
import domainmodel.BanDomainModel;
import domainmodel.ChiTietHoaDonDomainModel;
import domainmodel.ChiTietSanPhamDomainModel;
import repository.iRepository.IBanRepository;
import repository.iRepository.IChiTietHoaDonRepository;
import repository.iRepository.IChiTietSanPhamRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import utilities.DBConnect;

/**
 *
 * @author ADMIN
 */
public class ChiTietSanPhamRepository implements IChiTietSanPhamRepository {

    static Connection con = null;

    @Override
    public List<ChiTietSanPhamDomainModel> getList() {
        try {
            List<ChiTietSanPhamDomainModel> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "SELECT MaChiTietSanPham,MaSanPham,Size,Gia FROM ChiTietSanPham";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                lst.add(new ChiTietSanPhamDomainModel(rs.getInt(1),
                        rs.getInt(2), rs.getString(3),
                        rs.getBigDecimal(4)));
            }
            return lst;
        } catch (Exception e) {
        }
        return null;

    }

	@Override
	public List<ChiTietSanPhamDomainModel> selectAll(int... page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChiTietSanPhamDomainModel getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(ChiTietSanPhamDomainModel object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ChiTietSanPhamDomainModel object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ChiTietSanPhamDomainModel> getBySql(String sql, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

}
