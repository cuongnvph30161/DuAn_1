package services;

import com.toedter.calendar.JDateChooser;
import domainmodel.BanDomainModel;
import domainmodel.HoaDonDoMainModel;
import interfaceservices.IHoaDonService;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import repositorys.HoaDonRepository;
import repositorys.iRepository.IHoaDonRepository;
import viewmodel.NhanVienHoaDonViewModel;
import viewmodel.QuanLyHoaDonViewModel;
import viewmodel.VaiTroQuanLyBanViewModel;

/**
 *
 * @author Doanh
 */
public class HoaDonService implements IHoaDonService {

    IHoaDonRepository HoaDonRepo = new HoaDonRepository();

    @Override
    public List<QuanLyHoaDonViewModel> getListQLHD() {
        List<HoaDonDoMainModel> danhSachHoaDonDomain = HoaDonRepo.getList();
        List<QuanLyHoaDonViewModel> danhSachHoaDonView = new ArrayList<>();

        for (HoaDonDoMainModel hd : danhSachHoaDonDomain) {
            QuanLyHoaDonViewModel qlHoaDonView = new QuanLyHoaDonViewModel();
            qlHoaDonView.setMaHoaDon(hd.getMaHoaDon());
            qlHoaDonView.setMaNhanVien(hd.getMaNhanVien());
            qlHoaDonView.setThoiGian(hd.getThoiGian());
            qlHoaDonView.setTrangThaiThanhToan(hd.getTrangThaiThanhToan());
            qlHoaDonView.setTrangThaiOrder(hd.getTrangThaiOrder());
            qlHoaDonView.setGhiChu(hd.getGhiChu());
            qlHoaDonView.setDichVuPhatSinh(hd.getDichVuPhatSinh());
            qlHoaDonView.setMaVoucher(hd.getMaVoucher());
            danhSachHoaDonView.add(qlHoaDonView);
        }
        return danhSachHoaDonView;
    }
    // public boolean insert(String maNhanVi);

    @Override
    public double TongHoaDonQLHD(int maHoaDon) {
        return HoaDonRepo.TongHoaDonQLHD(maHoaDon);
    }

   

    @Override
    public List<QuanLyHoaDonViewModel> getListQLHDTheoMaHD(int maHoaDon) {
        List<HoaDonDoMainModel> danhSachHoaDonDomain = HoaDonRepo.getListQLHDTheoMaHD(maHoaDon);
        List<QuanLyHoaDonViewModel> danhSachHoaDonView = new ArrayList<>();

        for (HoaDonDoMainModel hd : danhSachHoaDonDomain) {

            QuanLyHoaDonViewModel qlHoaDonView = new QuanLyHoaDonViewModel();
            qlHoaDonView.setMaHoaDon(hd.getMaHoaDon());
            qlHoaDonView.setMaNhanVien(hd.getMaNhanVien());
            qlHoaDonView.setThoiGian(hd.getThoiGian());
            qlHoaDonView.setTrangThaiThanhToan(hd.getTrangThaiThanhToan());
            qlHoaDonView.setTrangThaiOrder(hd.getTrangThaiOrder());
            qlHoaDonView.setGhiChu(hd.getGhiChu());
            qlHoaDonView.setDichVuPhatSinh(hd.getDichVuPhatSinh());
            qlHoaDonView.setMaVoucher(hd.getMaVoucher());
            danhSachHoaDonView.add(qlHoaDonView);
        }
        return danhSachHoaDonView;
    }

    @Override
    public List<VaiTroQuanLyBanViewModel> getBanQLHD(int maHoaDon) {
        List<BanDomainModel> ListBanDomain = HoaDonRepo.getBanQLHD(maHoaDon);
        List<VaiTroQuanLyBanViewModel> ListBanView = new ArrayList<>();
        for (BanDomainModel ban : ListBanDomain) {
            VaiTroQuanLyBanViewModel banView = new VaiTroQuanLyBanViewModel();
            banView.setTang(ban.getTang());
            banView.setTenBan(ban.getTenBan());
            ListBanView.add(banView);
        }
        return ListBanView;
    }

    @Override
    public List<QuanLyHoaDonViewModel> TimKiemQLHoaDon(int maHoaDon) {
        List<HoaDonDoMainModel> danhSachHoaDonDomain = HoaDonRepo.TimKiemQLHoaDon(maHoaDon);
        List<QuanLyHoaDonViewModel> danhSachHoaDonView = new ArrayList<>();

        for (HoaDonDoMainModel hd : danhSachHoaDonDomain) {

            QuanLyHoaDonViewModel qlHoaDonView = new QuanLyHoaDonViewModel();
            qlHoaDonView.setMaHoaDon(hd.getMaHoaDon());
            qlHoaDonView.setMaNhanVien(hd.getMaNhanVien());
            qlHoaDonView.setThoiGian(hd.getThoiGian());
            qlHoaDonView.setTrangThaiThanhToan(hd.getTrangThaiThanhToan());
            qlHoaDonView.setTrangThaiOrder(hd.getTrangThaiOrder());
            qlHoaDonView.setGhiChu(hd.getGhiChu());
            qlHoaDonView.setDichVuPhatSinh(hd.getDichVuPhatSinh());
            qlHoaDonView.setMaVoucher(hd.getMaVoucher());
            danhSachHoaDonView.add(qlHoaDonView);
        }
        return danhSachHoaDonView;
    }

    @Override
    public List<NhanVienHoaDonViewModel> TimKiemQLHoaDonTheoNgay(java.util.Date ngay, List<NhanVienHoaDonViewModel> listTim) {
        try {
            List<NhanVienHoaDonViewModel> list = new ArrayList<>();
            for (NhanVienHoaDonViewModel a : listTim) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String thoiGian = a.getThoiGian() + "";
                java.util.Date TG = df.parse(thoiGian);
                if (TG.equals(ngay)) {

                    list.add(a);

                }
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    

    

    

}
