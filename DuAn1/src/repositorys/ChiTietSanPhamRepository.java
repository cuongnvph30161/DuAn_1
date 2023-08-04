/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys;

import java.sql.*;
import domainmodel.BanDomainModel;
import domainmodel.ChiTietHoaDonDomainModel;
import domainmodel.ChiTietSanPhamDomainModel;
import domainmodel.NhanVien.ChiTietSanPham;
import domainmodel.NhanVien.SanPham;
import repositorys.iRepository.IBanRepository;
import repositorys.iRepository.IChiTietHoaDonRepository;
import repositorys.iRepository.IChiTietSanPhamRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import utilities.DBConnect;
import utilities.JdbcHelper;

/**
 *
 * @author ADMIN
 */
public class ChiTietSanPhamRepository implements IChiTietSanPhamRepository {

     static Connection con = null;
     @Override
 	public List<ChiTietSanPhamDomainModel> getAll(int... maSanPham) {
 		List<ChiTietSanPhamDomainModel> lstChiTietSanPhamDomainModels=new ArrayList<ChiTietSanPhamDomainModel>();
 		StringBuilder querry=new StringBuilder("SELECT MaChiTietSanPham,MaSanPham,Size,Gia FROM ChiTietSanPham ");
 		if(maSanPham.length>0) {
 			querry.append(" Where maSanPham="+maSanPham[0]);
 		}	
 		try {
 			ResultSet rs=JdbcHelper.query(querry.toString());
 			while(rs.next()) {
 				ChiTietSanPhamDomainModel dmCTSP=new ChiTietSanPhamDomainModel();
 				dmCTSP.setMaChiTietSanPham(rs.getInt(1));
 				dmCTSP.setMaSanPham(rs.getInt(2));
 				dmCTSP.setSize(rs.getString(3));
 				dmCTSP.setGia(rs.getBigDecimal(4));
 				lstChiTietSanPhamDomainModels.add(dmCTSP);
 			}
 			return lstChiTietSanPhamDomainModels;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		return null;
 	}

 	@Override
 	public ChiTietSanPhamDomainModel getById(Integer id) {
 		List<ChiTietSanPhamDomainModel> lstChiTietSanPhamDomainModels=new ArrayList<ChiTietSanPhamDomainModel>();
 		StringBuilder querry=new StringBuilder("SELECT MaChiTietSanPham,MaSanPham,Size,Gia FROM ChiTietSanPham where MaChiTietSanPham="+id);
 		
 		try {
 			ResultSet rs=JdbcHelper.query(querry.toString());
 			if(rs.next()) {
 				ChiTietSanPhamDomainModel dmCTSP=new ChiTietSanPhamDomainModel();
 				dmCTSP.setMaChiTietSanPham(rs.getInt(1));
 				dmCTSP.setMaSanPham(rs.getInt(2));
 				dmCTSP.setSize(rs.getString(3));
 				dmCTSP.setGia(rs.getBigDecimal(4));
 				return dmCTSP;
 			}
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		return null;
 	}
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
    public List<ChiTietSanPhamDomainModel> getBySql(String sql, Object... args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SanPham> getListSanPham() {
        try {
            List<SanPham> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "select MaSanPham,TenSanPham,TrangThai,MoTa,anh\n"
                    + "from sanPham ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(lenh);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(rs.getInt(1));
                sp.setTenSanPham(rs.getString(2));
                sp.setTrangThai(rs.getInt(3));
                sp.setMotTa(rs.getString(4));
                sp.setAnh(rs.getBlob(5));
                lst.add(sp);
            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public ChiTietSanPham loadMouseClickSanPham(int maSanPham) {
        ChiTietSanPham ctsp = null;
        try {
            String query = "select sp.anh,sp.TrangThai,ctsp.Size,ctsp.Gia from SanPham as sp inner join ChiTietSanPham as ctsp\n"
                    + "on sp.MaSanPham=ctsp.MaSanPham where sp.maSanPham=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, maSanPham);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ctsp = new ChiTietSanPham();
                Blob anh = rs.getBlob(1);
                int trangThai = rs.getInt(2);
                String size = rs.getString(3);
                BigDecimal gia = rs.getBigDecimal(4);
                SanPham sp = new SanPham();
                sp.setAnh(anh);
                sp.setTrangThai(trangThai);
                ctsp.setSize(size);
                ctsp.setGia(gia);
                ctsp.setMaSanPham(sp);
            }
            return ctsp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<SanPham> getSanPhamByTen(String tenSanPham) {
        ArrayList<SanPham> getList = new ArrayList<>();
        try {
            String query = "select MaSanPham,TenSanPham,TrangThai,MoTa from SanPham where TenSanPham like N'%" + tenSanPham + "%'";
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {

                int maSanPham = rs.getInt("MaSanPham");
                String ten = rs.getString("TenSanPham");
                int trangThai = rs.getInt("TrangThai");
                String moTa = rs.getString("MoTa");
                SanPham sp = new SanPham();
                sp.setMaSanPham(maSanPham);
                sp.setTenSanPham(ten);
                sp.setTrangThai(trangThai);
                sp.setMotTa(moTa);
                getList.add(sp);
            }
            return getList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insertSanPham(SanPham sp) {
        try {
            String query1 = "insert into SanPham(TenSanPham,TrangThai,MoTa,Anh) values(?,?,?,?)";
            PreparedStatement ps1 = con.prepareStatement(query1);

            ps1.setString(1, sp.getTenSanPham());
            ps1.setInt(2, sp.getTrangThai());
            ps1.setString(3, sp.getMotTa());
            ps1.setBlob(4, sp.getAnh());
            ps1.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertCTSP(ChiTietSanPhamDomainModel ctsp) {
        int maSanPham = ctsp.getMaSanPham();
        String size = ctsp.getSize();
        BigDecimal gia = ctsp.getGia();
        try {
            ctsp = new ChiTietSanPhamDomainModel();
            String query2 = "insert into ChiTietSanPham(MaSanPham,Size,Gia) values(?,?,?)";
            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.setInt(1, maSanPham);
            ps2.setString(2, size);
            ps2.setBigDecimal(3, gia);
            ps2.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ChiTietSanPham> getGiaBySize(String size) {
        try {
            List<ChiTietSanPham> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "select gia from chiTietSanPham where size=?";
            PreparedStatement ps = con.prepareStatement(lenh);
            ps.setString(1, size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setGia(rs.getBigDecimal("gia"));
                lst.add(ctsp);
            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<SanPham> getSPMouclick(int maSanPham) {
        try {
            List<SanPham> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "select MaSanPham,TenSanPham,TrangThai,MoTa,Anh from SanPham where MaSanPham=?";
            PreparedStatement ps = con.prepareStatement(lenh);
            ps.setInt(1, maSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(rs.getInt("MaSanPham"));
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setTrangThai(rs.getInt("TrangThai"));
                sp.setMotTa(rs.getString("MoTa"));
                sp.setAnh(rs.getBlob("Anh"));
                lst.add(sp);
            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<ChiTietSanPham> getCTSPMouclick(int maSanPham) {
        try {
            List<ChiTietSanPham> lst = new ArrayList<>();
            con = DBConnect.getConnect();
            String lenh = "select Size,Gia from ChiTietSanPham where MaSanPham=?";
            PreparedStatement ps = con.prepareStatement(lenh);
            ps.setInt(1, maSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSanPham ctsp = new ChiTietSanPham();
                ctsp.setSize(rs.getString("Size"));
                ctsp.setGia(rs.getBigDecimal("Gia"));
                lst.add(ctsp);
            }
            return lst;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public boolean updateSanPham(SanPham sp) {
        try {
            //update chiTietSanPham
//            String query1 = "update ChiTietSanPham set size=?,gia=? where MaSanPham=?";
//            PreparedStatement ps1 = con.prepareStatement(query1);
//            ps1.setString(1, ctsp.getSize());
//            ps1.setBigDecimal(2, ctsp.getGia());
//            ps1.setInt(3, maSanPham);
//            ps1.executeUpdate();
            //update sanPham
            String query2 = "update SanPham set TenSanPham=?,TrangThai=?,MoTa=?,Anh=? where MaSanPham=?";
            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.setString(1, sp.getTenSanPham());
            ps2.setInt(2, sp.getTrangThai());
            ps2.setString(3, sp.getMotTa());
            ps2.setBlob(4, sp.getAnh());
            ps2.setInt(5, sp.getMaSanPham());
            ps2.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCTSP(int maSanPham, String size) {
        try {
            String query1 = "delete from ChiTietSanPham where MaSanPham=? and Size like ?";
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setInt(1, maSanPham);
            ps1.setString(2, size);
            ps1.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkTonCTSP(int maSanPham, String size) {
        try {
            con = DBConnect.getConnect();
            String query = "select maSanPham,Size from ChiTietSanPham where MaSanPham=? and Size like ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, maSanPham);
            ps.setString(2, size);
            return ps.executeQuery().next();

        } catch (Exception ex) {
              return false;
        }
      

    }

    @Override
    public boolean updateSizeCTSP(int maSanPham, String size, BigDecimal gia) {
        try {
            con = DBConnect.getConnect();
            String query = "update chiTietSanPham set Gia=? where maSanPham=? and Size like ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setBigDecimal(1, gia);
            ps.setInt(2, maSanPham);
            ps.setString(3, size);
            return ps.executeQuery().next();
        } catch (Exception ex) {
        }
        return false;
    }

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
}
