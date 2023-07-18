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
import repository.iRepository.IBanRepository;
import repository.iRepository.IChiTietHoaDonRepository;
import repository.iRepository.IChiTietSanPhamRepository;
import repository.iRepository.ISanPhamRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import utilities.DBConnect;

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
		// TODO Auto-generated method stub
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

}
