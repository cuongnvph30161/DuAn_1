/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainmodel.ChiTietHoaDonDomainModel;
import domainmodel.NhanVienDomainModel;
import domainmodel.Role;
import interfaceservices.IBanService;
import interfaceservices.IChiTietSanPhamService;
import interfaceservices.IHoaDonService;
import interfaceservices.IMaGiamGiaService;
import interfaceservices.INhanVienHoaDonServices;
import java.sql.Blob; // Thêm dòng này vào đầu tệp Java
import interfaceservices.INhanVienService;
import interfaceservices.IQuanLyBanServices;
import interfaceservices.ITaiKhoanServicess;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import services.NhanVienService;
import services.TaiKhoanServicess;
import utilities.XImages;
import viewmodel.NhanVienViewModel;
import viewmodel.TaiKhoanViewModel;
import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.html.HTML;
import repositorys.NhanVienRepository;
import repositorys.iRepository.INhanVienRepository;
import services.BanService;
import services.ChiTietSanPhamService;
import services.HoaDonService;
import services.MaGiamGiaService;
import services.NhanVienHoaDonServices;
import services.QuanLyBanServices;
import utilities.DBackUpAndRestore;
import utilities.DPlaceHolder;
import utilities.Uhelper;
import utilities.dauChamKhoangCach;
import viewmodel.MaGiamGiaViewModel;
import viewmodel.NhanVienHoaDonViewModel;
import viewmodel.PhaCheLichSuDanhSachSanPhamViewmodel;
import viewmodel.QuanLyBanViewmodel;
import viewmodel.QuanLyHoaDonViewModel;
import viewmodel.VaiTroQuanLyBanViewModel;
import viewmodel.defaultViewModel.ChiTietSanPhamViewModel;
import viewmodel.defaultViewModel.SanPhamViewModel;

public class TraSua_QL extends javax.swing.JFrame {

    /////////Quản lý hóa đơn
    int demTrangQLHD = 1;
    Map<Integer, List<NhanVienHoaDonViewModel>> mapPhanTrangQLHD = new HashMap<>();
    int soTrangQLHD = 1;
    private IHoaDonService QLHDService = new HoaDonService();
    INhanVienHoaDonServices NVHoaDonSv = new NhanVienHoaDonServices();
    List<PhaCheLichSuDanhSachSanPhamViewmodel> ListDSSP = NVHoaDonSv.getDSSP();
    Map<Integer, String> mapTenNV = NVHoaDonSv.mapTenNV();
    Map<Integer, String> mapTenBan = NVHoaDonSv.mapTenBan();
    Map<Integer, Object> maGiamGia = NVHoaDonSv.mapMaGiamGia();
    List<ChiTietHoaDonDomainModel> listCTHD = NVHoaDonSv.getlistCTHD();
    ///////
    IBanService BanService = new BanService();
    List<QuanLyBanViewmodel> listBanviewmodel = new ArrayList<>();
    DefaultTableModel BanBanModel = new DefaultTableModel();
    public IQuanLyBanServices ibanServices = new QuanLyBanServices();
    public ITaiKhoanServicess iTaiKhoanServicess = new TaiKhoanServicess();
    public INhanVienService iNhanVienService = new NhanVienService();
    public IMaGiamGiaService iMaGiamGiaService = new MaGiamGiaService();
    private String maTaiKhoan;
    /////////////////////////////
    public IChiTietSanPhamService iCTSPSe = new ChiTietSanPhamService();

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;

    }

    public TraSua_QL(String maTaiKhoan) {

        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        init();
        txtMaSanPhamThem.setEnabled(false);
        loadComBoBoxTaiKhoanMaNhanVien(iNhanVienService.getAll());
        loadTableTaiKhoan(iTaiKhoanServicess.getAll());
        loadTableNhanVien(iNhanVienService.getAll());
        BanBanModel = (DefaultTableModel) tblBanBan.getModel();
        fillBanTableBan();
        fillMaBan(0);
        ///////////////
        loadTableSanPham();
        loadTableVorCher(iMaGiamGiaService.getListMaGiamGia());
        jdcTimTheoNgay.setIcon(new ImageIcon(getClass().getResource("/Img/date_1.png")));
        phanTrangQLHD();
        truyenTrangQLHD(1);
        loadChuMoTapTrungTimKiemChoMaGiamGia();
        DPlaceHolder.addPlaceHolder(txtTimKiemTen, "Nhập tên nhân viên...");
        DPlaceHolder.addPlaceHolder(txtTimKiemSanPham, "Nhập tên sản phẩm...");
        DPlaceHolder.addPlaceHolder(txtTimKiemTaiKhoan, "Nhập mã tài khoản...");
        DPlaceHolder.addPlaceHolder(txtTimKiemMaGiamGia, "Nhập hóa đơn tối thiểu...");
        DPlaceHolder.addPlaceHolder(txtTimKiemQuanLyHoaDon, "Nhập mã hóa đơn...");
    }

    public boolean isNumeric(String str) {
        return str != null && str.matches("-?\\d+(\\.\\d+)*");
    }

    private void LoadTableDSSPQLHoaDon() {
        DefaultTableModel ModelQLHDDSSanPham = (DefaultTableModel) tblQLHDDSSanPham.getModel();
        int index = tblQuanLyHoaDon.getSelectedRow();
        int MaHoaDon = (int) tblQuanLyHoaDon.getValueAt(index, 0);
        ModelQLHDDSSanPham.setRowCount(0);
        List<NhanVienHoaDonViewModel> listNhanVienHDView = NVHoaDonSv.getList(ListDSSP, mapTenNV, mapTenBan, listCTHD, maGiamGia);
        for (NhanVienHoaDonViewModel DSSP : listNhanVienHDView) {
            if (DSSP.getMaHoaDon() == MaHoaDon) {
                List<PhaCheLichSuDanhSachSanPhamViewmodel> LstSanPham = DSSP.getListSP();
                for (PhaCheLichSuDanhSachSanPhamViewmodel sp : LstSanPham) {
                    ModelQLHDDSSanPham.addRow(new Object[]{sp.getMaSanPham(), sp.getTenSanPham(), sp.getSoLuong(), sp.getSize(), sp.getGiaBigDecimal().intValue()});
                }
            }

        }
    }

    public void LoadTableQLHDPhanTrang(List<NhanVienHoaDonViewModel> list) {

        DefaultTableModel ModelQLHD = (DefaultTableModel) tblQuanLyHoaDon.getModel();
        ModelQLHD.setRowCount(0);
        for (NhanVienHoaDonViewModel a : list) {
            ModelQLHD.addRow(new Object[]{a.getMaHoaDon(), a.getThoiGian(), a.getTongThanhToan().intValue(), a.getGhiChu()});
        }

    }

    public void truyenTrangQLHD(int index) {
        List<NhanVienHoaDonViewModel> lstTruyenTrang = new ArrayList<>();
        lstTruyenTrang = mapPhanTrangQLHD.get(index);
        lblTrangQLHD.setText("Trang " + index + "/" + soTrangQLHD);
        LoadTableQLHDPhanTrang(lstTruyenTrang);
    }

    public void phanTrangQLHD() {
        List<NhanVienHoaDonViewModel> listQLHDPhanTrang = NVHoaDonSv.getList(ListDSSP, mapTenNV, mapTenBan, listCTHD, maGiamGia);
        if (listQLHDPhanTrang.size() > 100) {
            double a = listQLHDPhanTrang.size();
            double b = 100;
            soTrangQLHD = (int) Math.ceil(a / b);

        } else {
            soTrangQLHD = 1;
        }
        if (soTrangQLHD > 1) {
            for (int i = 1; i <= soTrangQLHD; i++) {
                List<NhanVienHoaDonViewModel> listTrang = new ArrayList<>();
                if (i == soTrangQLHD) {
                    int doDaiTrangCuoi = listQLHDPhanTrang.size() - (soTrangQLHD - 1) * 100;
                    for (int j = ((soTrangQLHD - 1) * 100); j < (doDaiTrangCuoi + (((soTrangQLHD - 1) * 100))); j++) {
                        listTrang.add(listQLHDPhanTrang.get(j));
                    }

                } else {
                    if (i == 1) {
                        for (int k = 0; k < 100; k++) {
                            listTrang.add(listQLHDPhanTrang.get(k));
                        }
                    } else {
                        for (int h = (i - 1) * 100; h <= ((i - 1) * 100 + 99); h++) {
                            listTrang.add(listQLHDPhanTrang.get(h));
                        }
                    }
                }
                mapPhanTrangQLHD.put(i, listTrang);
            }
        } else {
            mapPhanTrangQLHD.put(1, listQLHDPhanTrang);
        }
    }
/////////////////////////////////////////////////

    public MaGiamGiaViewModel getDataMaGiamGia() {

        MaGiamGiaViewModel maGiamGiaViewModel = new MaGiamGiaViewModel();
        String maNhanVien = iTaiKhoanServicess.getMaNhanVienByMa(maTaiKhoan);

        String phanTramGiam = txtPhanTramGiam.getText();

        String hoaDonToiThieu = txtHoaDonToiThieu.getText();

        String giamToiDa = txtGiamToiDa.getText();

        String soLuong = txtSoLuong.getText();
        String ngayBatDau = txtNgayBatDau.getText();
        String ngayKetThuc = txtNgayKetThuc.getText();

        if (phanTramGiam.trim().equals("") || hoaDonToiThieu.trim().equals("") || giamToiDa.trim().equals("") || soLuong.trim().equals("") || ngayBatDau.trim().equals("") || ngayKetThuc.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được rỗng");
            return null;
        }

        if (!isNumeric(phanTramGiam)) {
            JOptionPane.showMessageDialog(this, "Phần trăm giảm phải là số nguyên và không được chứa kí tự đặc biệt");
            return null;
        }

        int phanTramGiamInt = Integer.parseInt(phanTramGiam);
        if (phanTramGiamInt <= 0 || phanTramGiamInt > 100) {
            JOptionPane.showMessageDialog(this, "Phần trăm giảm phải thuộc trong khoảng (0,100]");
            return null;
        }

        maGiamGiaViewModel.setPhanTramGiam(phanTramGiamInt);

        if (!isNumeric(hoaDonToiThieu)) {
            JOptionPane.showMessageDialog(this, "Hoá đơn tối thiểu phải là số nguyên và không được chứa kí tự đặc biệt");
            return null;
        }

        int hoaDonToiThieuInt = Integer.parseInt(hoaDonToiThieu);
        if (hoaDonToiThieuInt <= 0) {
            JOptionPane.showMessageDialog(this, "Hoá đơn tối thiểu phải >0");
            return null;
        }

        maGiamGiaViewModel.setDonToiThieu(hoaDonToiThieuInt);

        if (!isNumeric(giamToiDa)) {
            JOptionPane.showMessageDialog(this, "Giảm tối đa phải là số nguyên và không được chứa kí tự đặc biệt");
            return null;
        }

        BigDecimal giamToiDaBigDecimal = new BigDecimal(giamToiDa);

        if (giamToiDaBigDecimal.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(this, "Giảm tối đa phải >0");
            return null;
        }

        maGiamGiaViewModel.setGiamToiDa(giamToiDaBigDecimal);
        if (!isNumeric(soLuong)) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên và không được chứa kí tự đặc biệt");
            return null;
        }
        int soLuongInt = Integer.parseInt(soLuong);
        if (soLuongInt <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải >0");
            return null;
        }
        maGiamGiaViewModel.setSoLuong(soLuongInt);

        int maNhanVienInt = Integer.parseInt(maNhanVien);

        try {
            LocalDate ngayBatDauLocalDate = LocalDate.parse(ngayBatDau);
            LocalDate ngayHienTai = LocalDate.now();

            if (ngayBatDauLocalDate.isBefore(ngayHienTai)) {
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại");
                return null;
            }

            maGiamGiaViewModel.setNgayBatDau(java.sql.Date.valueOf(ngayBatDauLocalDate));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Chưa chuẩn định dạng ngày bắt đầu");
            return null;
        }
//
        try {
            LocalDate ngayKetThucLocalDate = LocalDate.parse(ngayKetThuc);
            LocalDate ngayBatDauLocalDate = maGiamGiaViewModel.getNgayBatDau().toLocalDate();

            if (ngayKetThucLocalDate.isBefore(ngayBatDauLocalDate)) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu");
                return null; // Hoặc làm xử lý phù hợp tùy trường hợp
            }

            maGiamGiaViewModel.setNgayKetThuc(java.sql.Date.valueOf(ngayKetThucLocalDate));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Chưa chuẩn định dạng ngày kết thúc");
            return null;
        }

        maGiamGiaViewModel.setMaNguoiTao(maNhanVienInt);
        System.out.println("hoa don toi thieu" + " " + maGiamGiaViewModel);

        return maGiamGiaViewModel;
    }

    public void loadTableVorCher(ArrayList<MaGiamGiaViewModel> list) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblVorCherFrom.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (MaGiamGiaViewModel maGiamGiaViewModel : list) {
            defaultTableModel.addRow(new Object[]{
                stt++, maGiamGiaViewModel.getMaVoucher(), maGiamGiaViewModel.getPhanTramGiam(),
                maGiamGiaViewModel.getDonToiThieu(),
                maGiamGiaViewModel.getGiamToiDa().intValue(),
                maGiamGiaViewModel.getSoLuong(), maGiamGiaViewModel.getNgayBatDau(),
                maGiamGiaViewModel.getNgayKetThuc(), maGiamGiaViewModel.getMaNguoiTao(),
                maGiamGiaViewModel.getHoTen()
            });
        }
    }

    private void loadTableSanPham() {
        List<SanPhamViewModel> listSanPham = iCTSPSe.getListSanPham();
        DefaultTableModel tableModelSanPham = (DefaultTableModel) tblQuanLySanPham.getModel();
        tableModelSanPham.setColumnCount(0);
        tableModelSanPham.addColumn("Mã sản phẩm");
        tableModelSanPham.addColumn("Tên sản phẩm");
        tableModelSanPham.addColumn("Trạng thái");
        tableModelSanPham.addColumn("Mô tả");
        tableModelSanPham.setRowCount(0);
        for (SanPhamViewModel spView : listSanPham) {
            tableModelSanPham.addRow(new Object[]{
                spView.getMaSanPham(),
                spView.getTenSanPham(),
                spView.getStatus(),
                spView.getMotTa()});
        }
    }

    private static Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(263, 141, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void cleanSanPham() {
        lblAnhSanPhamThem.setIcon(null);
        txtMaSanPhamThem.setText("");
        txtTenSanPhamThem.setText("");
        chkSizeSThem.setSelected(false);
        chkSizeMThem.setSelected(false);
        chkSizeLThem.setSelected(false);
        txtGiaSizeSThem.setText("");
        txtGiaSizeMThem.setText("");
        txtGiaSizeLThem.setText("");
        txtMoTaSanPhamThem.setText("");
    }

    public void CTSPMouclick() {
        chkSizeSXem.setSelected(false);
        chkSizeMXem.setSelected(false);
        chkSizeLXem.setSelected(false);
        txtGiaSizeSXem.setText("");
        txtGiaSizeMXem.setText("");
        txtGiaSizeLXem.setText("");
        int index = tblQuanLySanPham.getSelectedRow();
        int maSanPham = (int) (tblQuanLySanPham.getValueAt(index, 0));
        List<ChiTietSanPhamViewModel> listCTSPVM = iCTSPSe.getCTSPMouclick(maSanPham);
        for (ChiTietSanPhamViewModel ctspVM : listCTSPVM) {
            String size = ctspVM.getSize();
            if (size.equals("S")) {
                chkSizeSXem.setSelected(true);
                txtGiaSizeSXem.setText(ctspVM.getGia().intValue() + "");

            }
            if (size.equals("M")) {
                chkSizeMXem.setSelected(true);
                txtGiaSizeMXem.setText(ctspVM.getGia().intValue() + "");

            }
            if (size.equals("L")) {
                chkSizeLXem.setSelected(true);
                txtGiaSizeLXem.setText(ctspVM.getGia().intValue() + "");

            }

        }
    }

    public void sanPhamMouclick() {
        int index = tblQuanLySanPham.getSelectedRow();
        int maSanPham = (int) (tblQuanLySanPham.getValueAt(index, 0));
        List<SanPhamViewModel> listSPVM = iCTSPSe.getSPMouclick(maSanPham);

        for (SanPhamViewModel spVM : listSPVM) {
            txtMaSanPhamXem.setText(spVM.getMaSanPham() + "");
            txtTenSanPhamXem.setText(spVM.getTenSanPham());
            int trangThai = spVM.getTrangThai();
            if (trangThai == 0) {
                cboTrangThaiSanPhamXem.setSelectedItem("Hết hàng");
            }
            if (trangThai == 1) {
                cboTrangThaiSanPhamXem.setSelectedItem("Còn hàng");
            }
            if (trangThai == 2) {
                cboTrangThaiSanPhamXem.setSelectedItem("Ngừng kinh doanh");
            }

            Blob anh = spVM.getAnh();
            if (anh != null) {
                try {
                    BufferedImage img;
                    // Đọc dữ liệu từ đối tượng Blob thành mảng byte
                    byte[] pximg = anh.getBytes(1, (int) anh.length());
                    InputStream in = new ByteArrayInputStream(pximg);
                    try {
                        img = ImageIO.read(in);
                        ImageIcon sp = new ImageIcon(img);
                        lblAnhSanPhamXem.setIcon(resizeIcon(sp, lblAnhSanPhamXem.getWidth(), lblAnhSanPhamXem.getHeight()));
                    } catch (Exception e) {
                    }

                    // Chuyển đổi mảng byte thành ImageIcon
                    // Thiết lập ImageIcon lên JLabel
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Xử lý lỗi khi đọc dữ liệu từ Blob
                }
            } else {
                // Xử lý trường hợp đối tượng Blob là null hoặc không chứa dữ liệu ảnh
                lblAnhSanPhamXem.setIcon(null);
            }
            txtMoTaSanPhamXem.setText(spVM.getMotTa());

        }
    }

    private static boolean containsDigits(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkThemQLSP() {
        String name = txtTenSanPhamThem.getText();
        Icon icon = lblAnhSanPhamThem.getIcon();
        Blob anh = null;
        if (icon != null) {
            byte[] imageData = getImageDataFromIcon(icon);
            anh = createBlobFromImageData(imageData);
        } else {
            anh = null;
        }
        if (anh == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (name.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (name.startsWith(" ") || name.endsWith(" ") || name.contains("  ")) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được chứa khoảng trắng!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!validateTen(name)) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được chứa số và không được chứa kí tự đặc biệt!", "LỖI", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        List<SanPhamViewModel> listSanPham = iCTSPSe.getListSanPham();
        for (SanPhamViewModel sp : listSanPham) {
            if (name.trim().equals(sp.getTenSanPham())) {
                JOptionPane.showMessageDialog(this, "Tên sản phẩm không được trùng!", "LỖI", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        ////////////////////////////////checkThemCTSP
        if (chkSizeSThem.isSelected() == false && chkSizeMThem.isSelected() == false && chkSizeLThem.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn size!", "LỖI", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (chkSizeSThem.isSelected()) {
            if (txtGiaSizeSThem.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm size S không được để trống!", "LỖI", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            try {
                BigDecimal gia = new BigDecimal(txtGiaSizeSThem.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Giá size S phải là số và không được chứa khoảng trắng!", "LỖI", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (new BigDecimal(txtGiaSizeSThem.getText()).compareTo(new BigDecimal(0)) == -1) {
                JOptionPane.showMessageDialog(this, "Giá size S phải >0!", "LỖI", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        if (chkSizeMThem.isSelected()) {
            if (txtGiaSizeMThem.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm size M không được để trống!", "LỖI", JOptionPane.WARNING_MESSAGE);

                return false;
            }
            try {
                BigDecimal gia = new BigDecimal(txtGiaSizeMThem.getText());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Giá size M phải là số và không được chứa khoảng trắng!", "LỖI", JOptionPane.WARNING_MESSAGE);

                return false;
            }
            if (new BigDecimal(txtGiaSizeMThem.getText()).compareTo(new BigDecimal(0)) == -1) {
                JOptionPane.showMessageDialog(this, "Giá size M phải >0!", "LỖI", JOptionPane.WARNING_MESSAGE);
                return false;
            }

        }
        if (chkSizeLThem.isSelected()) {
            if (txtGiaSizeLThem.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm size L không được để trống!", "LỖI", JOptionPane.WARNING_MESSAGE);

                return false;
            }
            try {
                BigDecimal gia = new BigDecimal(txtGiaSizeLThem.getText());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Giá size L phải là số và không được chứa khoảng trắng!", "LỖI", JOptionPane.WARNING_MESSAGE);

                return false;
            }
            if (new BigDecimal(txtGiaSizeLThem.getText()).compareTo(new BigDecimal(0)) == -1) {
                JOptionPane.showMessageDialog(this, "Giá size L phải >0 !", "LỖI", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        //////////////////////////////////
        return true;

    }

    private void themSP() {
        String tenSanPham = txtTenSanPhamThem.getText();
        String tt = cboTrangThaiSanPhamThem.getSelectedItem().toString();
        int trangThai = -1;
        if (tt.equals("Còn hàng")) {
            trangThai = 1;
        }
        if (tt.equals("Hết hàng")) {
            trangThai = 0;
        }
        if (tt.equals("Ngừng kinh doanh")) {
            trangThai = 2;
        }
        String moTa = txtMoTaSanPhamThem.getText();
        Icon icon = lblAnhSanPhamThem.getIcon();
        Blob anh = null;
        if (icon != null) {
            byte[] imageData = getImageDataFromIcon(icon);
            anh = createBlobFromImageData(imageData);
        } else {
            anh = null;
        }

        SanPhamViewModel spVM = new SanPhamViewModel();
        spVM.setTenSanPham(tenSanPham);
        spVM.setTrangThai(trangThai);
        spVM.setMotTa(moTa);
        spVM.setAnh(anh);
        iCTSPSe.insertSanPham(spVM);
    }

    private void themSizeCTSP() {

        int index = tblQuanLySanPham.getSelectedRow();
        int maSanPham = Integer.parseInt(txtMaSanPhamXem.getText());
        if (chkSizeSXem.isSelected()) {
            BigDecimal gia = new BigDecimal(txtGiaSizeSXem.getText());
            if (iCTSPSe.checkTonCTSP(maSanPham, "S") == false) {
                ChiTietSanPhamViewModel ctspVM = new ChiTietSanPhamViewModel(maSanPham, "", 0, "S", gia, "", null);
                iCTSPSe.insertChiTietSP(ctspVM);
            }
        }
        if (chkSizeMXem.isSelected()) {
            BigDecimal gia = new BigDecimal(txtGiaSizeMXem.getText());
            if (iCTSPSe.checkTonCTSP(maSanPham, "M") == false) {
                ChiTietSanPhamViewModel ctspVM = new ChiTietSanPhamViewModel(maSanPham, "", 0, "M", gia, "", null);
                iCTSPSe.insertChiTietSP(ctspVM);
            }
        }
        if (chkSizeLXem.isSelected()) {
            BigDecimal gia = new BigDecimal(txtGiaSizeLXem.getText());
            if (iCTSPSe.checkTonCTSP(maSanPham, "L") == false) {
                ChiTietSanPhamViewModel ctspVM = new ChiTietSanPhamViewModel(maSanPham, "", 0, "L", gia, "", null);
                iCTSPSe.insertChiTietSP(ctspVM);
            }
        }

    }

    private void themCTSP() {
        try {
            int lastRow = tblQuanLySanPham.getRowCount() - 1; // Lấy chỉ số hàng cuối cùng   

            int maSanPham = (int) (tblQuanLySanPham.getValueAt(lastRow, 0));

            int maCTSP = 0;

            if (chkSizeSThem.isSelected()) {

                BigDecimal gia = new BigDecimal(txtGiaSizeSThem.getText());
                ChiTietSanPhamViewModel ctspVM = new ChiTietSanPhamViewModel(maSanPham, "", 0, "S", gia, "", null);

                iCTSPSe.insertChiTietSP(ctspVM);

            }
            if (chkSizeMThem.isSelected()) {

                BigDecimal gia = new BigDecimal(txtGiaSizeMThem.getText());
                ChiTietSanPhamViewModel ctspVM = new ChiTietSanPhamViewModel(maSanPham, "", 0, "M", gia, "", null);
                iCTSPSe.insertChiTietSP(ctspVM);
            }
            if (chkSizeLThem.isSelected()) {

                BigDecimal gia = new BigDecimal(txtGiaSizeLThem.getText());
                ChiTietSanPhamViewModel ctspVM = new ChiTietSanPhamViewModel(maSanPham, "", 0, "L", gia, "", null);

                iCTSPSe.insertChiTietSP(ctspVM);
            }

        } catch (Exception e) {
        }
    }

    private boolean checkCapNhatSP() {
        Icon icon = lblAnhSanPhamXem.getIcon();
        Blob anh = null;
        if (icon != null) {
            byte[] imageData = getImageDataFromIcon(icon);
            anh = createBlobFromImageData(imageData);
        } else {
            anh = null;
        }
        if (anh == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String name = txtTenSanPhamXem.getText();
        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (name.startsWith(" ") || name.endsWith(" ")) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được chứa khoảng trắng!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!validateTen(name)) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được chứa số và không được chứa kí tự đặc biệt!", "LỖI", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        List<SanPhamViewModel> listSanPham = iCTSPSe.getListSanPham();
        int selectedIndex = tblQuanLySanPham.getSelectedRow();
        for (int i = 0; i < listSanPham.size(); i++) {
            if (i == selectedIndex) { // Bỏ qua sản phẩm cần cập nhật
                continue;
            }
            SanPhamViewModel sp = listSanPham.get(i);
            if (name.trim().equals(sp.getTenSanPham())) {
                JOptionPane.showMessageDialog(this, "Tên sản phẩm không được trùng!", "LỖI", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        SanPhamViewModel selectedProduct = listSanPham.get(selectedIndex);
        selectedProduct.setTenSanPham(name);
        if (chkSizeSXem.isSelected() == false) {
            int maSanPham = Integer.parseInt(txtMaSanPhamXem.getText());
            if (iCTSPSe.checkTonCTSP(maSanPham, "S") == true) {
                chkSizeSXem.setSelected(true);
                JOptionPane.showMessageDialog(this, "Không được bỏ size sản phẩm", "LỖI", JOptionPane.WARNING_MESSAGE);
                CTSPMouclick();
                return false;
            }
        }
        if (chkSizeMXem.isSelected() == false) {
            int maSanPham = Integer.parseInt(txtMaSanPhamXem.getText());
            if (iCTSPSe.checkTonCTSP(maSanPham, "M") == true) {
                chkSizeMXem.setSelected(true);
                JOptionPane.showMessageDialog(this, "Không được bỏ size sản phẩm", "LỖI", JOptionPane.WARNING_MESSAGE);
                CTSPMouclick();
                return false;
            }

        }
        if (chkSizeLXem.isSelected() == false) {
            int maSanPham = Integer.parseInt(txtMaSanPhamXem.getText());
            if (iCTSPSe.checkTonCTSP(maSanPham, "L") == true) {
                chkSizeLXem.setSelected(true);
                JOptionPane.showMessageDialog(this, "Không được bỏ size sản phẩm", "LỖI", JOptionPane.WARNING_MESSAGE);
                CTSPMouclick();
                return false;
            }

        }
        ////////////////////////////////checkThemCTSP
        if (chkSizeSXem.isSelected()) {
            if (txtGiaSizeSXem.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm size S không được để trống!", "LỖI", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            try {
                BigDecimal gia = new BigDecimal(txtGiaSizeSXem.getText());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Giá size S phải là số và không được chứa khoảng trắng!", "LỖI", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (new BigDecimal(txtGiaSizeSXem.getText()).compareTo(new BigDecimal(0)) == -1) {
                JOptionPane.showMessageDialog(this, "Giá size S phải >0!", "LỖI", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        if (chkSizeMXem.isSelected()) {
            if (txtGiaSizeMXem.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm size M không được để trống!", "LỖI", JOptionPane.WARNING_MESSAGE);

                return false;
            }
            try {
                BigDecimal gia = new BigDecimal(txtGiaSizeMXem.getText());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Giá size M phải là số và không được chứa khoảng trắng!", "LỖI", JOptionPane.WARNING_MESSAGE);

                return false;
            }
            if (new BigDecimal(txtGiaSizeMXem.getText()).compareTo(new BigDecimal(0)) == -1) {
                JOptionPane.showMessageDialog(this, "Giá size M phải >0!", "LỖI", JOptionPane.WARNING_MESSAGE);
                return false;
            }

        }
        if (chkSizeLXem.isSelected()) {
            if (txtGiaSizeLXem.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm size L không được để trống!", "LỖI", JOptionPane.WARNING_MESSAGE);

                return false;
            }
            try {
                BigDecimal gia = new BigDecimal(txtGiaSizeLXem.getText());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Giá size L phải là số và không được chứa khoảng trắng!", "LỖI", JOptionPane.WARNING_MESSAGE);

                return false;
            }
            if (new BigDecimal(txtGiaSizeLXem.getText()).compareTo(new BigDecimal(0)) == -1) {
                JOptionPane.showMessageDialog(this, "Giá size L phải >0 !", "LỖI", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        //////////////////////////////////
        return true;

    }

    private void capNhatSanPham() {

        int index = tblQuanLySanPham.getSelectedRow();
        int maSanPham = (int) tblQuanLySanPham.getValueAt(index, 0);
        String tenSanPham = txtTenSanPhamXem.getText();
        String tt = cboTrangThaiSanPhamXem.getSelectedItem().toString();
        int trangThai = -1;
        if (tt.equals("Còn hàng")) {
            trangThai = 1;
        }
        if (tt.equals("Hết hàng")) {
            trangThai = 0;
        }
        if (tt.equals("Ngừng kinh doanh")) {
            trangThai = 2;
        }
        String moTa = txtMoTaSanPhamXem.getText();

        Icon icon = lblAnhSanPhamXem.getIcon();
        Blob anh = null;
        if (icon != null) {
            byte[] imageData = getImageDataFromIcon(icon);
            anh = createBlobFromImageData(imageData);

        } else {
            // Nếu không có ảnh, gán giá trị null cho trường ảnh trong nhanVienViewModel
            anh = null;

        }
        SanPhamViewModel spVM = new SanPhamViewModel(maSanPham, tenSanPham, trangThai, moTa, anh);
        iCTSPSe.updateSanPham(spVM);

    }

    private void updateGiaCTSP() {

        int index = tblQuanLySanPham.getSelectedRow();
        int maSanPham = (int) tblQuanLySanPham.getValueAt(index, 0);
        if (chkSizeSXem.isSelected()) {
            BigDecimal gia = new BigDecimal(txtGiaSizeSXem.getText());
            String size = "S";
            iCTSPSe.updateSizeCTSP(maSanPham, size, gia);
        }
        if (chkSizeMXem.isSelected()) {
            BigDecimal gia = new BigDecimal(txtGiaSizeMXem.getText());
            String size = "M";
            iCTSPSe.updateSizeCTSP(maSanPham, size, gia);
        }
        if (chkSizeLXem.isSelected()) {
            BigDecimal gia = new BigDecimal(txtGiaSizeLXem.getText());
            String size = "L";
            iCTSPSe.updateSizeCTSP(maSanPham, size, gia);
        }

    }

    public void loadComBoBoxTaiKhoanMaNhanVien(List<NhanVienViewModel> listNhanVienViewModels) {
        cbbMaNhanVienTaiKhoanThem.removeAllItems(); // Xóa tất cả các item cũ trong ComboBox
        System.out.println("xoa tat ca item");
        cbbMaNhanVienTaiKhoanSua.removeAllItems();

        for (NhanVienViewModel nhanVienViewModel : listNhanVienViewModels) {
            int maNhanVien = nhanVienViewModel.getMaNhanVien();
            String maNhanVienString = String.valueOf(maNhanVien); // Chuyển đổi từ int sang String
            cbbMaNhanVienTaiKhoanThem.addItem(maNhanVienString);
            cbbMaNhanVienTaiKhoanSua.addItem(maNhanVienString);
        }

    }

    public void showBan(int index) {

    }

    public void fillBanTableBan() {
        BanBanModel.setRowCount(0);
        listBanviewmodel = ibanServices.getListBan();
        for (QuanLyBanViewmodel a : listBanviewmodel) {
            String tang;
            if (a.getTang() == 0) {
                tang = "Mang về";
            } else {
                tang = a.getTang() + "";
            }
            BanBanModel.addRow(new Object[]{
                a.getMaBan(), a.getTenBan(), tang
            });
        }

    }

    public TaiKhoanViewModel getDataTaiKhoanThem() {
        TaiKhoanViewModel taiKhoanViewModel = new TaiKhoanViewModel();
        String maTaiKhoan1 = txtMaTaiKhoanThem.getText();
        String maNhanVien = cbbMaNhanVienTaiKhoanThem.getSelectedItem().toString();
        int maNhanVienInt = Integer.parseInt(maNhanVien);
        String matKhau = txtMatKhauThem.getText();
        String selectedRole = cbbTrangThaiVaiTroThem.getSelectedItem().toString();
        Role role = Role.valueOf(selectedRole);
        String trangThai = cbbTrangThaiTaiKhoanThem.getSelectedItem().toString();

        if (maTaiKhoan1.trim().equals("") || matKhau.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được rỗng");
            return null;
        }
        // check khoảng trắng tên tài khoản
        if (!checkKhoangTrangAndTrungMaTaiKhoan(maTaiKhoan1)) {
            return null;
        }
        // check khoảng trắng mật khẩu 
        if (!checkKhoangTrangAndTrungMaTaiKhoan(matKhau)) {
            return null;
        }

        if (hasDiacritics(maTaiKhoan1)) {
            JOptionPane.showMessageDialog(this, "Mã tài khoản không được có dấu");
            return null;
        }

        if (!checkMaNhanVien(maNhanVienInt)) {
            return null;
        }

        if (hasDiacritics(matKhau)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được có dấu");
            return null;
        }

        taiKhoanViewModel.setMaTaiKhoan(maTaiKhoan1);
        taiKhoanViewModel.setMaNhanVien(maNhanVienInt);
        taiKhoanViewModel.setMatKhau(matKhau);
        taiKhoanViewModel.setRole(role);
        if (trangThai.equals("Đã Khoá")) {
            taiKhoanViewModel.setTrangThai(0);
        } else {
            taiKhoanViewModel.setTrangThai(1);
        }

        return taiKhoanViewModel;
    }

    public TaiKhoanViewModel getDataTaiKhoanCapNhat() {
        TaiKhoanViewModel taiKhoanViewModel = new TaiKhoanViewModel();

        String maNhanVienString = cbbMaNhanVienTaiKhoanSua.getSelectedItem().toString();
        String matKhau = txtMatKhauTaiKhoanSua.getText();
        String vaiTro = cbbVaiTroTaiKhoanSua.getSelectedItem().toString();
        Role role = Role.valueOf(vaiTro); // Chuyển đổi chuỗi thành kiểu Role
        int maNhanVien = Integer.parseInt(maNhanVienString);
        String maTaiKhoan1 = txtMaTaiKhoanSua.getText();
        TaiKhoanViewModel taiKhoanCu = iTaiKhoanServicess.getTaiKhoanByMa(maTaiKhoan1);
        int maNhanVienCu = taiKhoanCu.getMaNhanVien();
        String trangThai = cbbTrangThaiTaiKhoanSua.getSelectedItem().toString();

        if (maTaiKhoan1.trim().equals("") || matKhau.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được rỗng");
            return null;
        }
        if (isMNVExists(maNhanVien, maNhanVienCu)) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại.");
            return null; // Có lỗi
        }

        if (matKhau.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được có dấu cách");
            return null;
        }

        if (hasDiacritics(matKhau)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được có dấu");
            return null;
        }

        taiKhoanViewModel.setMaNhanVien(maNhanVien);
        taiKhoanViewModel.setMatKhau(matKhau);
        taiKhoanViewModel.setRole(role);
        taiKhoanViewModel.setTrangThai(trangThai.equals("Không Khoá") ? 1 : 0);

        return taiKhoanViewModel;
    }

    public static boolean hasDiacritics(String input) {
        // Loại bỏ các dấu diacritical marks trong chuỗi
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Tạo mẫu Regex để tìm các ký tự dấu diacritical marks
        Pattern diacriticsPattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        // Kiểm tra xem chuỗi có khớp với mẫu Regex hay không
        return diacriticsPattern.matcher(normalized).find();
    }

    public boolean checkMaNhanVien(int maNhanVien) {
        Set<Integer> existingMaNhanViens = new HashSet<>();
        List<TaiKhoanViewModel> existingNhanViens = iTaiKhoanServicess.getAll();
        for (TaiKhoanViewModel nv : existingNhanViens) {
            existingMaNhanViens.add(nv.getMaNhanVien());
        }

        if (existingMaNhanViens.contains(maNhanVien)) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại. Vui lòng kiểm tra lại.");
            return false;
        }

        return true;
    }

    public boolean checkKhoangTrangAndTrungMaTaiKhoan(String taiKhoan) {
        if (taiKhoan.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Mã tài khoản và mật khẩu không được chứa khoảng trắng ở đầu, giữa hoặc cuối.");
            return false;
        }

        Set<String> existingMaTaiKhoans = new HashSet<>();
        List<TaiKhoanViewModel> existingTaiKhoans = iTaiKhoanServicess.getAll();
        for (TaiKhoanViewModel tk : existingTaiKhoans) {
            existingMaTaiKhoans.add(tk.getMaTaiKhoan());
        }

        if (existingMaTaiKhoans.contains(taiKhoan)) {
            JOptionPane.showMessageDialog(this, "Mã tài khoản đã tồn tại. Vui lòng kiểm tra lại.");
            return false;
        }

        return true;
    }

    public void loadTableNhanVien(ArrayList<NhanVienViewModel> list) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblNhanVienForm.getModel();
        defaultTableModel.setRowCount(0);
        for (NhanVienViewModel nhanVienViewModel : list) {
            defaultTableModel.addRow(new Object[]{
                nhanVienViewModel.getMaNhanVien(), nhanVienViewModel.getHoVaTen(), nhanVienViewModel.getCCCD(), nhanVienViewModel.getSoDienThoai(), nhanVienViewModel.getEmail(), nhanVienViewModel.getChucVu()
            });
        }
    }

    public void init() {
        setIconImage(XImages.getIconApp());
    }

    public void loadTableTaiKhoan(ArrayList<TaiKhoanViewModel> list) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblTaiKhoanForm.getModel();
        defaultTableModel.setRowCount(0);
        for (TaiKhoanViewModel taiKhoanViewModel : list) {
            defaultTableModel.addRow(new Object[]{
                taiKhoanViewModel.getMaTaiKhoan(), taiKhoanViewModel.getMaNhanVien(), taiKhoanViewModel.getMatKhau(), taiKhoanViewModel.getRole(), taiKhoanViewModel.getTrangThai() == 0 ? "Đã Khoá" : "Không Khoá"
            });
        }
    }
//

    public NhanVienViewModel getDataNhanVien() {
        NhanVienViewModel nhanVienViewModel = new NhanVienViewModel();
        String hoVaTen = txtHoVaTenThem.getText();
        String ngaySinh = txtNgaySinhThem.getText();
        String diaChi = txtDiaChiThem.getText();
        String cccd = txtCCCDThem.getText();
        String email = txtEmailThem.getText();
        String soDienThoai = txtSDTThem.getText();
        String ghiChu = txtGhiChuThem.getText();
        String nhanVienCu = iNhanVienService.getNhanVienByCCCD(cccd);
        String soDienThoaiCu = iNhanVienService.getSoDienThoaiBySDT(soDienThoai);
        String chucVu = cbbChucVuNhanVienThem.getSelectedItem().toString();
        String trangThai = cbbTrangThaiNhanVienThem.getSelectedItem().toString();

        // Lấy ảnh từ lblAnhNhanVien
        Icon icon = lblAnhNhanVien.getIcon();
        if (icon != null) {
            byte[] imageData = getImageDataFromIcon(icon);
            Blob anh = createBlobFromImageData(imageData);
            nhanVienViewModel.setAnh(anh);
        } else {
            // Nếu không có ảnh, gán giá trị null cho trường ảnh trong nhanVienViewModel
            nhanVienViewModel.setAnh(null);
        }

        // check rỗng 
        if (hoVaTen.trim().equals("") || ngaySinh.trim().equals("") || diaChi.trim().equals("") || cccd.trim().equals("") || email.trim().equals("") || soDienThoai.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được rỗng");
            return null;
        }

        if (!validateTen(hoVaTen)) {
            JOptionPane.showMessageDialog(this, "Tên không được là số và có kí tự đặc biệt");
            return null;
        }
        nhanVienViewModel.setHoVaTen(hoVaTen);
        // định dạng ngày sinh
        try {
            LocalDate localDate = LocalDate.parse(ngaySinh);
            nhanVienViewModel.setNgaySinh(java.sql.Date.valueOf(localDate));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chuẩn định dạng ngày sinh");
            return null;
        }

        if (diaChi.startsWith(" ")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa dấu cách ở đầu");
            return null;
        }
        nhanVienViewModel.setDiaChi(diaChi);

        if (!cccd.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "CCCD phải là số nguyên không âm và không chứa kí tự.");
            return null;
        }

        if (isCCCDExists(cccd, false, nhanVienCu)) {
            JOptionPane.showMessageDialog(this, "CCCD đã tồn tại. Vui lòng kiểm tra lại.");
            return null;
        }

        if (cccd.length() != 12) {
            JOptionPane.showMessageDialog(this, "Căn cước công dân phải đạt 12 số");
            return null;
        }

        nhanVienViewModel.setCCCD(cccd);

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Định dạng email không hợp lệ.");
            return null;
        }

        nhanVienViewModel.setEmail(email);

        if (isSoDienThoaiExists(soDienThoai, false, soDienThoaiCu)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại. Vui lòng kiểm tra lại");
            return null;
        }

        if (!isSoDienThoaiValid(soDienThoai)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là số nguyên không âm và thuộc trong khoảng 10 đến 11 số.");
            return null;
        }

        nhanVienViewModel.setSoDienThoai(soDienThoai);
        nhanVienViewModel.setChucVu(chucVu);
        if (trangThai.equals("Đã nghỉ việc")) {
            nhanVienViewModel.setTrangThai(0);
        } else {
            nhanVienViewModel.setTrangThai(1);
        }
        nhanVienViewModel.setGhiChu(ghiChu);
        return nhanVienViewModel;
    }

    public static boolean validateTen(String tenNhanVien) {
        // Kiểm tra xem tên không chứa số và không có ký tự đặc biệt
        if (tenNhanVien.matches(".*[0-9!@#$%^&*(),.?\":{}|<>].*")) {
            return false;
        }

        // Kiểm tra xem tên không bắt đầu hoặc kết thúc bằng khoảng trắng
        if (tenNhanVien.startsWith(" ") || tenNhanVien.endsWith(" ")) {
            return false;
        }

        return true;
    }

    public boolean isCCCDExists(String cccd, boolean isUpdating, String cccdCu) {

        // Kiểm tra xem CCCD mới có khác với CCCD cũ hay không
        if (isUpdating && cccd.equals(cccdCu)) {
            // Nếu CCCD mới không khác CCCD cũ, không cần kiểm tra và không báo lỗi
            return false;
        }
        // Lấy danh sách CCCD hiện có từ cơ sở dữ liệu
        Set<String> existingCCCDs = new HashSet<>();
        List<NhanVienViewModel> existingNhanViens = iNhanVienService.getAll();
        for (NhanVienViewModel nv : existingNhanViens) {
            existingCCCDs.add(nv.getCCCD());
        }

        // Kiểm tra xem CCCD mới có trong danh sách đã lấy được hay không
        if (existingCCCDs.contains(cccd)) {
            // Kiểm tra nếu đang thực hiện cập nhật (update) thì hiển thị thông báo
            if (isUpdating) {
                JOptionPane.showMessageDialog(this, "CCCD đã tồn tại.");
            }
            return true;
        }

        return false;
    }

    public boolean isSoDienThoaiValid(String soDienThoai) {
        if (soDienThoai.matches("\\d+") && (soDienThoai.length() == 10 || soDienThoai.length() == 11)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSoDienThoaiExists(String soDienThoai, boolean isUpdating, String soDienThoaiCu) {

        if (isUpdating && soDienThoai.equals(soDienThoaiCu)) {
            // Nếu số điện thoại mới không khác số điện thoại cũ, không cần kiểm tra và không báo lỗi
            return false;
        }

        // Lấy danh sách số điện thoại hiện có từ cơ sở dữ liệu
        Set<String> existingSDTs = new HashSet<>();
        List<NhanVienViewModel> existingNhanViens = iNhanVienService.getAll();
        for (NhanVienViewModel nv : existingNhanViens) {
            existingSDTs.add(nv.getSoDienThoai());
        }

        // Kiểm tra xem số điện thoại mới có trong danh sách đã lấy được hay không
        if (existingSDTs.contains(soDienThoai)) {
            // Kiểm tra nếu đang thực hiện cập nhật (update) thì hiển thị thông báo
            if (isUpdating) {
                JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại.");
            }
            return true;
        }

        return false;
    }

    private boolean isValidEmail(String email) {
        // Kiểm tra email không được viết hoa
        if (email.matches(".*[A-Z].*")) {
            return false;
        }

        String regex = "^[a-z0-9._%+-]+(\\.[a-z0-9._%+-]+)*@[a-z0-9.-]+\\.[a-z]{2,}$";
        // kiểm tra không được 2 dấu chấm liên tiếp
        boolean hasConsecutiveDots = email.contains("..");
        return email.matches(regex) && !hasConsecutiveDots;

    }

    private byte[] getImageDataFromIcon(Icon icon) {
        if (icon != null && icon instanceof ImageIcon) {
            // Chuyển đổi Icon thành mảng byte
            ImageIcon imageIcon = (ImageIcon) icon;
            Image image = imageIcon.getImage();

            // Tạo một BufferedImage trống để vẽ hình ảnh lên
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);

            // Vẽ hình ảnh lên BufferedImage
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();

            // Chuyển đổi BufferedImage thành mảng byte
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(bufferedImage, "png", baos);
                return baos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Blob createBlobFromImageData(byte[] data) {
        Blob blob = null;
        try {
            blob = new SerialBlob(data);
        } catch (SQLException e) {
            e.printStackTrace();
            // Hoặc xử lý theo cách phù hợp với ứng dụng của bạn, ví dụ:
            // throw new RuntimeException("Không thể tạo đối tượng Blob từ dữ liệu hình ảnh.", e);
        }
        return blob;
    }

    public NhanVienViewModel getDataNhanVienCapNhat() {
        NhanVienViewModel nhanVienViewModel = new NhanVienViewModel();
        String maNhanVien = txtMaNhanVienXem.getText();
        int maNhanVienInt = Integer.parseInt(maNhanVien);
        nhanVienViewModel.setMaNhanVien(maNhanVienInt);
        // Lấy thông tin từ các trường nhập liệu ở phần "Xem và cập nhật nhân viên"
        String hoVaTen = txtHoVaTenXem.getText();
        String ngaySinh = txtNgaySinhXem.getText();
        String diaChi = txtDiaChiXem.getText();
        String cccd = txtCCCDXem.getText();
        String email = txtEmailXem.getText();
        String soDienThoai = txtSDTXem.getText();
        String ghiChu = txtGhiChuXem.getText();
        String chucVu = cbbChucVuNhanVienXem.getSelectedItem().toString();
        NhanVienViewModel nhanVienCu = iNhanVienService.getNhanVienById(maNhanVienInt);
        String cccdCu = nhanVienCu.getCCCD();
        String soDienThoaiCu = nhanVienCu.getSoDienThoai();
        String trangThai = cbbTrangThaiNhanVienXem.getSelectedItem().toString();

        // Lấy ảnh từ lblAnhNhanVien
        Icon icon = lblAnhNhanVienSua.getIcon();
        if (icon != null) {
            byte[] imageData = getImageDataFromIcon(icon);
            Blob anh = createBlobFromImageData(imageData);
            nhanVienViewModel.setAnh(anh);
        } else {
            // Nếu không có ảnh, gán giá trị null cho trường ảnh trong nhanVienViewModel
            nhanVienViewModel.setAnh(null);
        }
        // check rỗng 
        if (hoVaTen.trim().equals("") || ngaySinh.trim().equals("") || cccd.trim().equals("") || email.trim().equals("") || soDienThoai.trim().equals("") || diaChi.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được rỗng");
            return null;
        }

        if (!validateTen(hoVaTen)) {
            JOptionPane.showMessageDialog(this, "Tên không được là số và có kí tự đặc biệt");
            return null;
        }
        nhanVienViewModel.setHoVaTen(hoVaTen);

        try {
            LocalDate localDate = LocalDate.parse(ngaySinh);
            nhanVienViewModel.setNgaySinh(java.sql.Date.valueOf(localDate));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chuẩn định dạng ngày sinh");
            return null;
        }

        if (diaChi.startsWith(" ")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa dấu cách ở đầu");
            return null;
        }

        nhanVienViewModel.setDiaChi(diaChi);

        if (!cccd.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "CCCD phải là số nguyên không âm và không chứa kí tự.");
            return null;
        }
        if (isCCCDExists(cccd, true, cccdCu)) {
            return null;
        }
        if (cccd.length() != 12) {
            JOptionPane.showMessageDialog(this, "Căn cước công dân phải đạt 12 số");
            return null;
        }
        nhanVienViewModel.setCCCD(cccd);

        Set<String> existingEmails = new HashSet<>();
        List<NhanVienViewModel> existingNhanViens = iNhanVienService.getAll();
        for (NhanVienViewModel nv : existingNhanViens) {
            existingEmails.add(nv.getEmail());
        }

        if (!email.equals(nhanVienCu.getEmail()) && existingEmails.contains(email)) {
            JOptionPane.showMessageDialog(this, "Email không được trùng.");
            return null;
        }
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Định dạng email không hợp lệ.");
            return null;
        }

        nhanVienViewModel.setEmail(email);

        if (isSoDienThoaiExists(soDienThoai, true, soDienThoaiCu)) {
            return null;
        } else if (!isSoDienThoaiValid(soDienThoai)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là số nguyên không âm và thuộc trong khoảng 10 đến 11 số.");
            return null;
        }
        nhanVienViewModel.setSoDienThoai(soDienThoai);

        nhanVienViewModel.setChucVu(chucVu);

        if (trangThai.equals("Đã nghỉ việc")) {
            nhanVienViewModel.setTrangThai(0);
        } else {
            nhanVienViewModel.setTrangThai(1);
        }

        nhanVienViewModel.setGhiChu(ghiChu);
        return nhanVienViewModel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnMenu = new javax.swing.JPanel();
        lblTraSua = new javax.swing.JLabel();
        lblquanly = new javax.swing.JLabel();
        btnKhieuNaiHoTro = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblThietLap = new javax.swing.JLabel();
        jpnNenSanPham = new javax.swing.JPanel();
        lblSanPham = new javax.swing.JLabel();
        jpnNenTaiKhoan = new javax.swing.JPanel();
        lblTaiKhoan = new javax.swing.JLabel();
        jpnNenHoaDon = new javax.swing.JPanel();
        lblHoaDon = new javax.swing.JLabel();
        jpnNenVoucher = new javax.swing.JPanel();
        lblVoucher = new javax.swing.JLabel();
        jpnNenNhanVien = new javax.swing.JPanel();
        lblNhanVien = new javax.swing.JLabel();
        jpnNenBan = new javax.swing.JPanel();
        lblQuanLyBan = new javax.swing.JLabel();
        jpnNenDoiMatKhau = new javax.swing.JPanel();
        lblDoiMatKhau = new javax.swing.JLabel();
        jpnNenBackupHeThong = new javax.swing.JPanel();
        lblBackupHeThong = new javax.swing.JLabel();
        jpnTong = new javax.swing.JPanel();
        jpnNhanVien = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        lblAnhNhanVienSua = new javax.swing.JLabel();
        btnAnhNhanVienSua = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtGhiChuXem = new javax.swing.JTextArea();
        btnCapNhatNhanVien = new javax.swing.JButton();
        txtMaNhanVienXem = new javax.swing.JTextField();
        txtHoVaTenXem = new javax.swing.JTextField();
        txtNgaySinhXem = new javax.swing.JTextField();
        txtDiaChiXem = new javax.swing.JTextField();
        txtCCCDXem = new javax.swing.JTextField();
        txtEmailXem = new javax.swing.JTextField();
        txtSDTXem = new javax.swing.JTextField();
        cbbChucVuNhanVienXem = new javax.swing.JComboBox<>();
        cbbTrangThaiNhanVienXem = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        txtCCCDThem = new javax.swing.JTextField();
        txtHoVaTenThem = new javax.swing.JTextField();
        txtSDTThem = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        txtMaNhanVienThem = new javax.swing.JTextField();
        cbbTrangThaiNhanVienThem = new javax.swing.JComboBox<>();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        btnThemNhanVien = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtGhiChuThem = new javax.swing.JTextArea();
        txtEmailThem = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        txtDiaChiThem = new javax.swing.JTextField();
        lblAnhNhanVien = new javax.swing.JLabel();
        btnAnhNhanVien = new javax.swing.JButton();
        cbbChucVuNhanVienThem = new javax.swing.JComboBox<>();
        jLabel97 = new javax.swing.JLabel();
        txtNgaySinhThem = new javax.swing.JTextField();
        btnClean = new javax.swing.JButton();
        jLabel75 = new javax.swing.JLabel();
        txtTimKiemTen = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblNhanVienForm = new javax.swing.JTable();
        jLabel86 = new javax.swing.JLabel();
        cbbTimKiemTrangThai = new javax.swing.JComboBox<>();
        jpnSanPham = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        lblAnhSanPhamXem = new javax.swing.JLabel();
        btnChonAnhSanPhamXem = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTaSanPhamXem = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        btnCapNhatSanPham = new javax.swing.JButton();
        txtMaSanPhamXem = new javax.swing.JTextField();
        txtTenSanPhamXem = new javax.swing.JTextField();
        txtGiaSizeSXem = new javax.swing.JTextField();
        txtGiaSizeMXem = new javax.swing.JTextField();
        cboTrangThaiSanPhamXem = new javax.swing.JComboBox<>();
        chkSizeSXem = new javax.swing.JCheckBox();
        chkSizeMXem = new javax.swing.JCheckBox();
        chkSizeLXem = new javax.swing.JCheckBox();
        jLabel96 = new javax.swing.JLabel();
        txtGiaSizeLXem = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        chkSizeMThem = new javax.swing.JCheckBox();
        cboTrangThaiSanPhamThem = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtGiaSizeMThem = new javax.swing.JTextField();
        txtMaSanPhamThem = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTaSanPhamThem = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        chkSizeSThem = new javax.swing.JCheckBox();
        btnThemSanPham = new javax.swing.JButton();
        btnChonAnhSanPhamThem = new javax.swing.JButton();
        lblAnhSanPhamThem = new javax.swing.JLabel();
        txtGiaSizeSThem = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTenSanPhamThem = new javax.swing.JTextField();
        chkSizeLThem = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        txtGiaSizeLThem = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        txtTimKiemSanPham = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQuanLySanPham = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jpnQLBan = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        txtBanCapNhatTenBan = new javax.swing.JTextField();
        cboBanCapNhatTang = new javax.swing.JComboBox<>();
        btnBanCapNhatClear = new javax.swing.JButton();
        btnBanCapNhat = new javax.swing.JButton();
        lblBanCapNhatMaBan = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        txtBanThemTenBan = new javax.swing.JTextField();
        cboBanThemTang = new javax.swing.JComboBox<>();
        btnBanThemClear = new javax.swing.JButton();
        btnBanThem = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblBanBan = new javax.swing.JTable();
        jLabel103 = new javax.swing.JLabel();
        cboTimBanTheoTang = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jpnHoaDon = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblQLHDDSSanPham = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtQLHDGhiChu = new javax.swing.JTextArea();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtQLHDMaHoaDon = new javax.swing.JTextField();
        txtQLHDMaNhanVien = new javax.swing.JTextField();
        txtQLHDThoiGian = new javax.swing.JTextField();
        txtQLHDTang = new javax.swing.JTextField();
        txtQLHDBan = new javax.swing.JTextField();
        txtQLHDTrangThaiThanhToan = new javax.swing.JTextField();
        txtQLHDMaGiamGia = new javax.swing.JTextField();
        txtQLHDTongHoaDon = new javax.swing.JTextField();
        txtTongThanhToanQLHD = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtQLHDDichVuPhatSinh = new javax.swing.JTextField();
        txtTimKiemQuanLyHoaDon = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblQuanLyHoaDon = new javax.swing.JTable();
        lblQLHDDau = new javax.swing.JLabel();
        lblQLHDLui = new javax.swing.JLabel();
        lblQLHDTien = new javax.swing.JLabel();
        lblQLHDCuoi = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        lblTimKiemTheoNgay = new javax.swing.JLabel();
        jdcTimTheoNgay = new com.toedter.calendar.JDateChooser();
        lblTrangQLHD = new javax.swing.JLabel();
        btnTimKiemQLHD = new javax.swing.JButton();
        btnXemQLHD = new javax.swing.JButton();
        jpnVoucher = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        btnCleanMaGiamGia = new javax.swing.JButton();
        txtMaVorCher = new javax.swing.JTextField();
        txtPhanTramGiam = new javax.swing.JTextField();
        txtHoaDonToiThieu = new javax.swing.JTextField();
        txtGiamToiDa = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        btnTaoMoi = new javax.swing.JButton();
        jLabel100 = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        jLabel109 = new javax.swing.JLabel();
        txtMaNguoiTao = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        txtNgayKetThuc = new javax.swing.JTextField();
        btnThuHoiMaVouCher = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblVorCherFrom = new javax.swing.JTable();
        txtTimKiemMaGiamGia = new javax.swing.JTextField();
        jpnTaiKhoan = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        btnCapNhatTaiKhoanNhanVien = new javax.swing.JButton();
        txtMaTaiKhoanSua = new javax.swing.JTextField();
        txtMatKhauTaiKhoanSua = new javax.swing.JTextField();
        cbbMaNhanVienTaiKhoanSua = new javax.swing.JComboBox<>();
        cbbVaiTroTaiKhoanSua = new javax.swing.JComboBox<>();
        cbbTrangThaiTaiKhoanSua = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        txtMaTaiKhoanThem = new javax.swing.JTextField();
        cbbMaNhanVienTaiKhoanThem = new javax.swing.JComboBox<>();
        txtMatKhauThem = new javax.swing.JTextField();
        cbbTrangThaiVaiTroThem = new javax.swing.JComboBox<>();
        cbbTrangThaiTaiKhoanThem = new javax.swing.JComboBox<>();
        btnThemTaiKhoan = new javax.swing.JButton();
        btnCleanTaiKhoan = new javax.swing.JButton();
        txtTimKiemTaiKhoan = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblTaiKhoanForm = new javax.swing.JTable();
        jpnBackupHeThong = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jLabel107 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblBackUp = new javax.swing.JTable();
        jButton21 = new javax.swing.JButton();
        jLabel105 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ thống quản lý quán trà sữa ToTo");
        setBounds(new java.awt.Rectangle(0, 0, 1920, 1080));
        setMinimumSize(new java.awt.Dimension(1920, 1080));

        jpnMenu.setBackground(new java.awt.Color(0, 65, 123));
        jpnMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTraSua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTraSua.setForeground(new java.awt.Color(255, 255, 255));
        lblTraSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logoXoaNen2.png"))); // NOI18N
        lblTraSua.setText("TRÀ SỮA TOTO");
        jpnMenu.add(lblTraSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblquanly.setBackground(new java.awt.Color(45, 132, 252));
        lblquanly.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblquanly.setForeground(new java.awt.Color(45, 132, 252));
        lblquanly.setText("QUẢN LÝ");
        lblquanly.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblquanlyMouseClicked(evt);
            }
        });
        jpnMenu.add(lblquanly, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        btnKhieuNaiHoTro.setBackground(new java.awt.Color(2, 82, 155));
        btnKhieuNaiHoTro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKhieuNaiHoTro.setForeground(new java.awt.Color(255, 255, 255));
        btnKhieuNaiHoTro.setText("KHIẾU NẠI HỖ TRỢ ?");
        btnKhieuNaiHoTro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKhieuNaiHoTroMouseClicked(evt);
            }
           
        });
      
        jpnMenu.add(btnKhieuNaiHoTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 650, -1, 40));

        btnDangXuat.setBackground(new java.awt.Color(45, 132, 252));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnDangXuat.setText("ĐĂNG XUẤT");
        btnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseClicked(evt);
            }
        });
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        jpnMenu.add(btnDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 710, 170, -1));
        jpnMenu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 230, 10));
        jpnMenu.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 230, 10));

        lblThietLap.setBackground(new java.awt.Color(45, 132, 252));
        lblThietLap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThietLap.setForeground(new java.awt.Color(45, 132, 252));
        lblThietLap.setText("THIẾT LẬP");
        lblThietLap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThietLapMouseClicked(evt);
            }
        });
        jpnMenu.add(lblThietLap, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        jpnNenSanPham.setBackground(new java.awt.Color(0, 65, 123));

        lblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sanPham.png"))); // NOI18N
        lblSanPham.setText("  SẢN PHẨM");
        lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpnNenSanPhamLayout = new javax.swing.GroupLayout(jpnNenSanPham);
        jpnNenSanPham.setLayout(jpnNenSanPhamLayout);
        jpnNenSanPhamLayout.setHorizontalGroup(
            jpnNenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNenSanPhamLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jpnNenSanPhamLayout.setVerticalGroup(
            jpnNenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNenSanPhamLayout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(lblSanPham))
        );

        jpnMenu.add(jpnNenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 230, 40));

        jpnNenTaiKhoan.setBackground(new java.awt.Color(0, 65, 123));

        lblTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/taiKhoan.png"))); // NOI18N
        lblTaiKhoan.setText("  TÀI KHOẢN");
        lblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTaiKhoanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpnNenTaiKhoanLayout = new javax.swing.GroupLayout(jpnNenTaiKhoan);
        jpnNenTaiKhoan.setLayout(jpnNenTaiKhoanLayout);
        jpnNenTaiKhoanLayout.setHorizontalGroup(
            jpnNenTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNenTaiKhoanLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTaiKhoan)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jpnNenTaiKhoanLayout.setVerticalGroup(
            jpnNenTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNenTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTaiKhoan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnMenu.add(jpnNenTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 230, 40));

        jpnNenHoaDon.setBackground(new java.awt.Color(0, 65, 123));

        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/hoaDon.png"))); // NOI18N
        lblHoaDon.setText("  HÓA ĐƠN");
        lblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpnNenHoaDonLayout = new javax.swing.GroupLayout(jpnNenHoaDon);
        jpnNenHoaDon.setLayout(jpnNenHoaDonLayout);
        jpnNenHoaDonLayout.setHorizontalGroup(
            jpnNenHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNenHoaDonLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jpnNenHoaDonLayout.setVerticalGroup(
            jpnNenHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNenHoaDonLayout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(lblHoaDon))
        );

        jpnMenu.add(jpnNenHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, -1, 40));

        jpnNenVoucher.setBackground(new java.awt.Color(0, 65, 123));

        lblVoucher.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblVoucher.setForeground(new java.awt.Color(255, 255, 255));
        lblVoucher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/voucher.png"))); // NOI18N
        lblVoucher.setText("  VOUCHER");
        lblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVoucherMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpnNenVoucherLayout = new javax.swing.GroupLayout(jpnNenVoucher);
        jpnNenVoucher.setLayout(jpnNenVoucherLayout);
        jpnNenVoucherLayout.setHorizontalGroup(
            jpnNenVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNenVoucherLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jpnNenVoucherLayout.setVerticalGroup(
            jpnNenVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNenVoucherLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVoucher)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnMenu.add(jpnNenVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 230, 40));

        jpnNenNhanVien.setBackground(new java.awt.Color(0, 65, 123));

        lblNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nhanVien.png"))); // NOI18N
        lblNhanVien.setText("  NHÂN VIÊN");
        lblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNhanVienMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNhanVienMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jpnNenNhanVienLayout = new javax.swing.GroupLayout(jpnNenNhanVien);
        jpnNenNhanVien.setLayout(jpnNenNhanVienLayout);
        jpnNenNhanVienLayout.setHorizontalGroup(
            jpnNenNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNenNhanVienLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jpnNenNhanVienLayout.setVerticalGroup(
            jpnNenNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNenNhanVienLayout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(lblNhanVien))
        );

        jpnMenu.add(jpnNenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 230, 40));

        jpnNenBan.setBackground(new java.awt.Color(0, 65, 123));

        lblQuanLyBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuanLyBan.setForeground(new java.awt.Color(255, 255, 255));
        lblQuanLyBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ban2.png"))); // NOI18N
        lblQuanLyBan.setText("  BÀN");
        lblQuanLyBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuanLyBanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpnNenBanLayout = new javax.swing.GroupLayout(jpnNenBan);
        jpnNenBan.setLayout(jpnNenBanLayout);
        jpnNenBanLayout.setHorizontalGroup(
            jpnNenBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNenBanLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblQuanLyBan, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jpnNenBanLayout.setVerticalGroup(
            jpnNenBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNenBanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblQuanLyBan)
                .addContainerGap())
        );

        jpnMenu.add(jpnNenBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 230, 40));

        jpnNenDoiMatKhau.setBackground(new java.awt.Color(0, 65, 123));

        lblDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/doiMatKhau.png"))); // NOI18N
        lblDoiMatKhau.setText("  ĐỔI MẬT KHẨU");
        lblDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoiMatKhauMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpnNenDoiMatKhauLayout = new javax.swing.GroupLayout(jpnNenDoiMatKhau);
        jpnNenDoiMatKhau.setLayout(jpnNenDoiMatKhauLayout);
        jpnNenDoiMatKhauLayout.setHorizontalGroup(
            jpnNenDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNenDoiMatKhauLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jpnNenDoiMatKhauLayout.setVerticalGroup(
            jpnNenDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNenDoiMatKhauLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoiMatKhau)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnMenu.add(jpnNenDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 230, 40));

        jpnNenBackupHeThong.setBackground(new java.awt.Color(0, 65, 123));

        lblBackupHeThong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBackupHeThong.setForeground(new java.awt.Color(255, 255, 255));
        lblBackupHeThong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/backup.png"))); // NOI18N
        lblBackupHeThong.setText("BACKUP HỆ THỐNG");
        lblBackupHeThong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackupHeThongMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpnNenBackupHeThongLayout = new javax.swing.GroupLayout(jpnNenBackupHeThong);
        jpnNenBackupHeThong.setLayout(jpnNenBackupHeThongLayout);
        jpnNenBackupHeThongLayout.setHorizontalGroup(
            jpnNenBackupHeThongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNenBackupHeThongLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblBackupHeThong)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jpnNenBackupHeThongLayout.setVerticalGroup(
            jpnNenBackupHeThongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNenBackupHeThongLayout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(lblBackupHeThong))
        );

        jpnMenu.add(jpnNenBackupHeThong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 230, 40));

        jpnTong.setBackground(new java.awt.Color(0, 153, 51));
        jpnTong.setLayout(new java.awt.CardLayout());

        jpnNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        jpnNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Quản lý nhân viên");
        jpnNhanVien.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, -1, -1));

        jTabbedPane4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAnhNhanVienSua.setText("Hình ảnh");
        lblAnhNhanVienSua.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAnhNhanVienSua.setBackground(new java.awt.Color(45, 132, 252));
        btnAnhNhanVienSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAnhNhanVienSua.setForeground(new java.awt.Color(255, 255, 255));
        btnAnhNhanVienSua.setText("Chọn ảnh");
        btnAnhNhanVienSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnhNhanVienSuaActionPerformed(evt);
            }
        });

        jLabel76.setText("Mã nhân viên");

        jLabel77.setText("Họ và tên");

        jLabel78.setText("Ngày sinh");

        jLabel79.setText("Địa chỉ");

        jLabel80.setText("CCCD");

        jLabel81.setText("Email");

        jLabel82.setText("SDT");

        jLabel83.setText("Chức vụ");

        jLabel84.setText("Trạng thái");

        jLabel85.setText("Ghi chú");

        txtGhiChuXem.setColumns(20);
        txtGhiChuXem.setRows(5);
        jScrollPane9.setViewportView(txtGhiChuXem);

        btnCapNhatNhanVien.setBackground(new java.awt.Color(45, 132, 252));
        btnCapNhatNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCapNhatNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhatNhanVien.setText("Cập nhật nhân viên");
        btnCapNhatNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatNhanVienActionPerformed(evt);
            }
        });

        txtMaNhanVienXem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        txtMaNhanVienXem.setEnabled(false);

        txtHoVaTenXem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtNgaySinhXem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtDiaChiXem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtCCCDXem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        txtCCCDXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCCCDXemActionPerformed(evt);
            }
        });

        txtEmailXem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtSDTXem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        cbbChucVuNhanVienXem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên", "Pha Chế" }));

        cbbTrangThaiNhanVienXem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang làm việc", "Đã nghỉ việc" }));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCapNhatNhanVien)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(lblAnhNhanVienSua, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(btnAnhNhanVienSua))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel85, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel84, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaNhanVienXem)
                                    .addComponent(txtHoVaTenXem)
                                    .addComponent(txtNgaySinhXem)
                                    .addComponent(txtDiaChiXem)
                                    .addComponent(txtCCCDXem)
                                    .addComponent(txtEmailXem)
                                    .addComponent(txtSDTXem)
                                    .addComponent(cbbChucVuNhanVienXem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbTrangThaiNhanVienXem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblAnhNhanVienSua, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnAnhNhanVienSua)))
                .addGap(10, 10, 10)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(txtMaNhanVienXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(txtHoVaTenXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(txtNgaySinhXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(txtDiaChiXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(txtCCCDXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(txtEmailXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(txtSDTXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(cbbChucVuNhanVienXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(cbbTrangThaiNhanVienXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jLabel85)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btnCapNhatNhanVien)
                .addContainerGap())
        );

        jTabbedPane4.addTab("Thông tin nhân viên", jPanel11);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel87.setText("Địa chỉ");

        jLabel88.setText("Trạng thái");

        jLabel89.setText("SDT");

        jLabel90.setText("Họ và tên");

        txtCCCDThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtHoVaTenThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtSDTThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jLabel91.setText("Ghi chú");

        txtMaNhanVienThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        txtMaNhanVienThem.setEnabled(false);

        cbbTrangThaiNhanVienThem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang làm việc", "Đã nghỉ việc" }));

        jLabel92.setText("Mã nhân viên");

        jLabel93.setText("Email");

        jLabel94.setText("Ngày sinh");

        btnThemNhanVien.setBackground(new java.awt.Color(45, 132, 252));
        btnThemNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNhanVien.setText("Thêm");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        txtGhiChuThem.setColumns(20);
        txtGhiChuThem.setRows(5);
        jScrollPane11.setViewportView(txtGhiChuThem);

        txtEmailThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jLabel95.setText("CCCD");

        txtDiaChiThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblAnhNhanVien.setText("Hình ảnh");
        lblAnhNhanVien.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAnhNhanVien.setBackground(new java.awt.Color(45, 132, 252));
        btnAnhNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAnhNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnAnhNhanVien.setText("Chọn ảnh");
        btnAnhNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnhNhanVienActionPerformed(evt);
            }
        });

        cbbChucVuNhanVienThem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên", "Pha Chế" }));
        cbbChucVuNhanVienThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChucVuNhanVienThemActionPerformed(evt);
            }
        });

        jLabel97.setText("Chức vụ");

        txtNgaySinhThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        btnClean.setBackground(new java.awt.Color(45, 132, 252));
        btnClean.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClean.setForeground(new java.awt.Color(255, 255, 255));
        btnClean.setText("Xóa trắng");
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(lblAnhNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAnhNhanVien))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addComponent(btnClean)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThemNhanVien))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel91, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel88, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel87, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel94, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel90, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel92, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMaNhanVienThem)
                                .addComponent(txtHoVaTenThem)
                                .addComponent(txtNgaySinhThem)
                                .addComponent(txtDiaChiThem)
                                .addComponent(txtCCCDThem)
                                .addComponent(txtEmailThem)
                                .addComponent(txtSDTThem)
                                .addComponent(cbbChucVuNhanVienThem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbbTrangThaiNhanVienThem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblAnhNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnAnhNhanVien)))
                .addGap(10, 10, 10)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel92)
                    .addComponent(txtMaNhanVienThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90)
                    .addComponent(txtHoVaTenThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(txtNgaySinhThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(txtDiaChiThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(txtCCCDThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(txtEmailThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(txtSDTThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97)
                    .addComponent(cbbChucVuNhanVienThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(cbbTrangThaiNhanVienThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jLabel91)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNhanVien)
                    .addComponent(btnClean))
                .addContainerGap())
        );

        jTabbedPane4.addTab("Thêm mới nhân viên", jPanel12);

        jpnNhanVien.add(jTabbedPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 43, -1, -1));

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tim3_1.png"))); // NOI18N
        jpnNhanVien.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 50, -1, 20));

        txtTimKiemTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemTenActionPerformed(evt);
            }
        });
        txtTimKiemTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemTenKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemTenKeyReleased(evt);
            }
        });
        jpnNhanVien.add(txtTimKiemTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 43, 965, 35));

        tblNhanVienForm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ và tên", "CCCD", "SDT", "Email", "Chức vụ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVienForm.setRowHeight(40);
        tblNhanVienForm.setSelectionBackground(new java.awt.Color(51, 204, 255));
        tblNhanVienForm.setSelectionForeground(new java.awt.Color(255, 0, 0));
        tblNhanVienForm.setShowGrid(true);
        tblNhanVienForm.setSurrendersFocusOnKeystroke(true);
        tblNhanVienForm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienFormMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblNhanVienForm);

        jpnNhanVien.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 90, 965, 680));

        jLabel86.setText("Trạng thái");
        jpnNhanVien.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(1026, 15, 69, -1));

        cbbTimKiemTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang làm việc", "Đã nghỉ việc", "Hiển thị tất cả" }));
        cbbTimKiemTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimKiemTrangThaiActionPerformed(evt);
            }
        });
        cbbTimKiemTrangThai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbbTimKiemTrangThaiKeyPressed(evt);
            }
        });
        jpnNhanVien.add(cbbTimKiemTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1128, 12, 170, -1));

        jpnTong.add(jpnNhanVien, "card2");

        jpnSanPham.setBackground(new java.awt.Color(255, 255, 255));
        jpnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnSanPhamMouseClicked(evt);
            }
        });
        jpnSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Quản Lý Sản Phẩm");
        jpnSanPham.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 16, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        lblAnhSanPhamXem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnChonAnhSanPhamXem.setBackground(new java.awt.Color(45, 132, 252));
        btnChonAnhSanPhamXem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnChonAnhSanPhamXem.setForeground(new java.awt.Color(255, 255, 255));
        btnChonAnhSanPhamXem.setText("Chọn ảnh");
        btnChonAnhSanPhamXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhSanPhamXemActionPerformed(evt);
            }
        });

        jLabel11.setText("Mã sản phẩm");

        jLabel12.setText("Tên sản phẩm");

        jLabel13.setText("Trạng thái");

        jLabel14.setText("Size");

        jLabel15.setText("Giá size S");

        jLabel16.setText("Giá size M");

        txtMoTaSanPhamXem.setColumns(20);
        txtMoTaSanPhamXem.setRows(5);
        jScrollPane1.setViewportView(txtMoTaSanPhamXem);

        jLabel17.setText("Mô tả");

        btnCapNhatSanPham.setBackground(new java.awt.Color(45, 132, 252));
        btnCapNhatSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCapNhatSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhatSanPham.setText("Cập nhật");
        btnCapNhatSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSanPhamActionPerformed(evt);
            }
        });

        txtMaSanPhamXem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        txtMaSanPhamXem.setEnabled(false);

        txtTenSanPhamXem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtGiaSizeSXem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtGiaSizeMXem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        cboTrangThaiSanPhamXem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn hàng", "Hết hàng", "Ngừng kinh doanh" }));

        chkSizeSXem.setText("S");

        chkSizeMXem.setText("M");

        chkSizeLXem.setText("L");

        jLabel96.setText("Giá size L");

        txtGiaSizeLXem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        txtGiaSizeLXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaSizeLXemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCapNhatSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtMaSanPhamXem))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTenSanPhamXem))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel96, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(32, 32, 32)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtGiaSizeSXem)
                                        .addComponent(txtGiaSizeMXem)
                                        .addComponent(cboTrangThaiSanPhamXem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(chkSizeSXem)
                                            .addGap(18, 18, 18)
                                            .addComponent(chkSizeMXem)
                                            .addGap(18, 18, 18)
                                            .addComponent(chkSizeLXem))
                                        .addComponent(txtGiaSizeLXem, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(lblAnhSanPhamXem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnChonAnhSanPhamXem, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)))))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblAnhSanPhamXem, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnChonAnhSanPhamXem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMaSanPhamXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTenSanPhamXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(cboTrangThaiSanPhamXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel14)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkSizeSXem)
                            .addComponent(chkSizeMXem)
                            .addComponent(chkSizeLXem))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtGiaSizeSXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtGiaSizeMXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96)
                    .addComponent(txtGiaSizeLXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCapNhatSanPham)
                .addGap(14, 14, 14))
        );

        jTabbedPane1.addTab("Thông tin sản phẩm", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chkSizeMThem.setText("M");

        cboTrangThaiSanPhamThem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn hàng", "Hết hàng", "Ngừng kinh doanh" }));

        jLabel18.setText("Giá size S");

        jLabel19.setText("Giá size M");

        jLabel20.setText("Mô tả");

        jLabel21.setText("Tên sản phẩm");

        txtGiaSizeMThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtMaSanPhamThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtMoTaSanPhamThem.setColumns(20);
        txtMoTaSanPhamThem.setRows(5);
        jScrollPane3.setViewportView(txtMoTaSanPhamThem);

        jLabel22.setText("Trạng thái");

        jLabel23.setText("Mã sản phẩm");

        chkSizeSThem.setText("S");

        btnThemSanPham.setBackground(new java.awt.Color(45, 132, 252));
        btnThemSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnThemSanPham.setText("Thêm");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        btnChonAnhSanPhamThem.setBackground(new java.awt.Color(45, 132, 252));
        btnChonAnhSanPhamThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnChonAnhSanPhamThem.setForeground(new java.awt.Color(255, 255, 255));
        btnChonAnhSanPhamThem.setText("Chọn ảnh");
        btnChonAnhSanPhamThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhSanPhamThemActionPerformed(evt);
            }
        });

        lblAnhSanPhamThem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtGiaSizeSThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jLabel25.setText("Size");

        txtTenSanPhamThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        chkSizeLThem.setText("L");

        jLabel24.setText("Giá size L");

        txtGiaSizeLThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        btnClear.setBackground(new java.awt.Color(45, 132, 252));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Xóa trắng");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemSanPham))
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSanPhamThem))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenSanPhamThem))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaSizeSThem)
                            .addComponent(txtGiaSizeMThem)
                            .addComponent(cboTrangThaiSanPhamThem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(chkSizeSThem)
                                .addGap(18, 18, 18)
                                .addComponent(chkSizeMThem)
                                .addGap(18, 18, 18)
                                .addComponent(chkSizeLThem)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtGiaSizeLThem, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(lblAnhSanPhamThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChonAnhSanPhamThem, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblAnhSanPhamThem, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnChonAnhSanPhamThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtMaSanPhamThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtTenSanPhamThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(cboTrangThaiSanPhamThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel25)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkSizeSThem)
                            .addComponent(chkSizeMThem)
                            .addComponent(chkSizeLThem))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtGiaSizeSThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtGiaSizeMThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtGiaSizeLThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSanPham)
                    .addComponent(btnClear))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thêm sản phẩm", jPanel3);

        jpnSanPham.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 53, -1, -1));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tim3.png"))); // NOI18N
        jpnSanPham.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 90, -1, -1));

        txtTimKiemSanPham.setForeground(new java.awt.Color(153, 153, 153));
        txtTimKiemSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtTimKiemSanPhamMouseEntered(evt);
            }
        });
        txtTimKiemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemSanPhamActionPerformed(evt);
            }
        });
        txtTimKiemSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSanPhamKeyReleased(evt);
            }
        });
        jpnSanPham.add(txtTimKiemSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 85, 931, 37));

        tblQuanLySanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblQuanLySanPham.setGridColor(new java.awt.Color(0, 65, 123));
        tblQuanLySanPham.setRowHeight(40);
        tblQuanLySanPham.setSelectionBackground(new java.awt.Color(51, 204, 255));
        tblQuanLySanPham.setSelectionForeground(new java.awt.Color(255, 0, 0));
        tblQuanLySanPham.setShowGrid(true);
        tblQuanLySanPham.setSurrendersFocusOnKeystroke(true);
        tblQuanLySanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanLySanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblQuanLySanPham);

        jpnSanPham.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 140, 931, 614));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(45, 132, 252));
        jLabel10.setText("Tìm sản phẩm theo tên");
        jpnSanPham.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 63, -1, -1));

        jpnTong.add(jpnSanPham, "card3");

        jpnQLBan.setBackground(new java.awt.Color(255, 255, 255));
        jpnQLBan.setForeground(new java.awt.Color(204, 0, 153));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Quản lý bàn");

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("Mã bàn");

        jLabel98.setText("Tên bàn");

        jLabel99.setText("Tầng");

        txtBanCapNhatTenBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        cboBanCapNhatTang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "Mang về" }));

        btnBanCapNhatClear.setBackground(new java.awt.Color(45, 132, 252));
        btnBanCapNhatClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBanCapNhatClear.setForeground(new java.awt.Color(255, 255, 255));
        btnBanCapNhatClear.setText("Xóa trắng");
        btnBanCapNhatClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanCapNhatClearActionPerformed(evt);
            }
        });

        btnBanCapNhat.setBackground(new java.awt.Color(45, 132, 252));
        btnBanCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBanCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnBanCapNhat.setText("Cập nhật");
        btnBanCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanCapNhatActionPerformed(evt);
            }
        });

        lblBanCapNhatMaBan.setText("1000");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                            .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBanCapNhatTenBan, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(cboBanCapNhatTang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBanCapNhatMaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnBanCapNhatClear, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(btnBanCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblBanCapNhatMaBan))
                .addGap(28, 28, 28)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98)
                    .addComponent(txtBanCapNhatTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel99)
                    .addComponent(cboBanCapNhatTang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBanCapNhatClear)
                    .addComponent(btnBanCapNhat))
                .addGap(17, 17, 17))
        );

        jPanel13Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboBanCapNhatTang, jLabel8, jLabel98, jLabel99, lblBanCapNhatMaBan, txtBanCapNhatTenBan});

        jTabbedPane5.addTab("Thông tin bàn", jPanel13);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel101.setText("Tên bàn");

        jLabel102.setText("Tầng");

        txtBanThemTenBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        cboBanThemTang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "Mang về" }));

        btnBanThemClear.setBackground(new java.awt.Color(45, 132, 252));
        btnBanThemClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBanThemClear.setForeground(new java.awt.Color(255, 255, 255));
        btnBanThemClear.setText("Xóa trắng");
        btnBanThemClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanThemClearActionPerformed(evt);
            }
        });

        btnBanThem.setBackground(new java.awt.Color(45, 132, 252));
        btnBanThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBanThem.setForeground(new java.awt.Color(255, 255, 255));
        btnBanThem.setText("Thêm");
        btnBanThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnBanThemClear, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBanThem, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboBanThemTang, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBanThemTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101)
                    .addComponent(txtBanThemTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102)
                    .addComponent(cboBanThemTang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBanThemClear)
                    .addComponent(btnBanThem))
                .addGap(17, 17, 17))
        );

        jPanel14Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboBanThemTang, jLabel101, jLabel102, txtBanThemTenBan});

        jTabbedPane5.addTab("Thêm mới bàn", jPanel14);

        tblBanBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã bàn", "Tên bàn", "Tầng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBanBan.setGridColor(new java.awt.Color(0, 65, 123));
        tblBanBan.setRowHeight(40);
        tblBanBan.setSelectionBackground(new java.awt.Color(51, 204, 255));
        tblBanBan.setSelectionForeground(new java.awt.Color(255, 0, 0));
        tblBanBan.setShowGrid(true);
        tblBanBan.setSurrendersFocusOnKeystroke(true);
        tblBanBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBanBanMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblBanBan);

        jLabel103.setText("Tầng ");

        cboTimBanTheoTang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "Mang về" }));
        cboTimBanTheoTang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimBanTheoTangActionPerformed(evt);
            }
        });
        cboTimBanTheoTang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboTimBanTheoTangKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Làm mới");
        jLabel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpnQLBanLayout = new javax.swing.GroupLayout(jpnQLBan);
        jpnQLBan.setLayout(jpnQLBanLayout);
        jpnQLBanLayout.setHorizontalGroup(
            jpnQLBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQLBanLayout.createSequentialGroup()
                .addGroup(jpnQLBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnQLBanLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnQLBanLayout.createSequentialGroup()
                        .addContainerGap(81, Short.MAX_VALUE)
                        .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpnQLBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpnQLBanLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 977, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnQLBanLayout.createSequentialGroup()
                                .addGap(763, 763, 763)
                                .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboTimBanTheoTang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(722, Short.MAX_VALUE))
        );
        jpnQLBanLayout.setVerticalGroup(
            jpnQLBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQLBanLayout.createSequentialGroup()
                .addGroup(jpnQLBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnQLBanLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnQLBanLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addGroup(jpnQLBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnQLBanLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnQLBanLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jpnQLBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTimBanTheoTang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel103))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(328, Short.MAX_VALUE))
        );

        jpnQLBanLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboTimBanTheoTang, jLabel103});

        jpnTong.add(jpnQLBan, "card4");

        jpnHoaDon.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Quản lý hóa đơn");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Chi tiết hóa đơn");

        jLabel27.setText("Mã hóa đơn");

        jLabel28.setText("Mã nhân viên");

        jLabel29.setText("Thời gian");

        jLabel30.setText("Tầng");

        jLabel31.setText("Bàn");

        jLabel32.setText("Trạng thái thanh toán");

        jLabel33.setText("Mã giảm giá");

        tblQLHDDSSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Size", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblQLHDDSSanPham);

        jLabel34.setText("Ghi chú");

        txtQLHDGhiChu.setColumns(20);
        txtQLHDGhiChu.setRows(5);
        txtQLHDGhiChu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane5.setViewportView(txtQLHDGhiChu);

        jLabel35.setText("Tổng hóa đơn");

        jLabel36.setText("Tổng thanh toán(*)");

        txtQLHDMaHoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtQLHDMaNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtQLHDThoiGian.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtQLHDTang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtQLHDBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtQLHDTrangThaiThanhToan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtQLHDMaGiamGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtQLHDTongHoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        txtTongThanhToanQLHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jLabel44.setText("Dịch vụ phát sinh");

        txtQLHDDichVuPhatSinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQLHDDichVuPhatSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQLHDMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQLHDMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQLHDThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQLHDTang, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQLHDBan, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addComponent(txtQLHDTrangThaiThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQLHDMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTongThanhToanQLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQLHDTongHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtQLHDMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtQLHDMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtQLHDThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtQLHDTang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtQLHDBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtQLHDTrangThaiThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtQLHDMaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtQLHDTongHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtQLHDDichVuPhatSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtTongThanhToanQLHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtTimKiemQuanLyHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemQuanLyHoaDonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemQuanLyHoaDonKeyTyped(evt);
            }
        });

        tblQuanLyHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Thời gian", "Tổng thanh toán", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQuanLyHoaDon.setGridColor(new java.awt.Color(0, 65, 123));
        tblQuanLyHoaDon.setRowHeight(40);
        tblQuanLyHoaDon.setSelectionBackground(new java.awt.Color(51, 204, 255));
        tblQuanLyHoaDon.setSelectionForeground(new java.awt.Color(255, 0, 0));
        tblQuanLyHoaDon.setShowGrid(true);
        tblQuanLyHoaDon.setSurrendersFocusOnKeystroke(true);
        tblQuanLyHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanLyHoaDonMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblQuanLyHoaDon);

        lblQLHDDau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/2.png"))); // NOI18N
        lblQLHDDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQLHDDauMouseClicked(evt);
            }
        });

        lblQLHDLui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/4.png"))); // NOI18N
        lblQLHDLui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQLHDLuiMouseClicked(evt);
            }
        });

        lblQLHDTien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/3.png"))); // NOI18N
        lblQLHDTien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQLHDTienMouseClicked(evt);
            }
        });

        lblQLHDCuoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/1.png"))); // NOI18N
        lblQLHDCuoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQLHDCuoiMouseClicked(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel41.setText("*Số tiền cần thanh toán sau khi áp dụng mã voucher");

        lblTimKiemTheoNgay.setForeground(new java.awt.Color(0, 0, 255));
        lblTimKiemTheoNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tim3_1.png"))); // NOI18N
        lblTimKiemTheoNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTimKiemTheoNgayMouseClicked(evt);
            }
        });

        jdcTimTheoNgay.setDateFormatString("yyyy-MM-dd");
        jdcTimTheoNgay.setFocusCycleRoot(true);
        jdcTimTheoNgay.setIcon(null);
        jdcTimTheoNgay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jdcTimTheoNgayKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jdcTimTheoNgayKeyTyped(evt);
            }
        });

        lblTrangQLHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnTimKiemQLHD.setBackground(new java.awt.Color(45, 132, 252));
        btnTimKiemQLHD.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnTimKiemQLHD.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemQLHD.setText("Tìm kiếm");
        btnTimKiemQLHD.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(51, 204, 255)));
        btnTimKiemQLHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemQLHDActionPerformed(evt);
            }
        });

        btnXemQLHD.setBackground(new java.awt.Color(45, 132, 252));
        btnXemQLHD.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnXemQLHD.setForeground(new java.awt.Color(255, 255, 255));
        btnXemQLHD.setText("Xem tất cả");
        btnXemQLHD.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(51, 204, 255)));
        btnXemQLHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemQLHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnHoaDonLayout = new javax.swing.GroupLayout(jpnHoaDon);
        jpnHoaDon.setLayout(jpnHoaDonLayout);
        jpnHoaDonLayout.setHorizontalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jpnHoaDonLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                                .addComponent(lblQLHDDau)
                                .addGap(18, 18, 18)
                                .addComponent(lblQLHDLui)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTrangQLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblQLHDTien)
                                .addGap(18, 18, 18)
                                .addComponent(lblQLHDCuoi))
                            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                                .addComponent(txtTimKiemQuanLyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTimKiemQLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jpnHoaDonLayout.createSequentialGroup()
                                    .addComponent(lblTimKiemTheoNgay)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jdcTimTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnHoaDonLayout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112)
                                .addComponent(btnXemQLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(800, Short.MAX_VALUE))
        );
        jpnHoaDonLayout.setVerticalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnHoaDonLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnHoaDonLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcTimTheoNgay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTimKiemTheoNgay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnHoaDonLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiemQuanLyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiemQLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXemQLHD)
                            .addComponent(jLabel41))
                        .addGap(8, 8, 8)
                        .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblQLHDDau)
                            .addComponent(lblQLHDLui, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQLHDTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTrangQLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQLHDCuoi, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(304, Short.MAX_VALUE))
        );

        jpnHoaDonLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblQLHDCuoi, lblQLHDDau, lblQLHDLui, lblQLHDTien, lblTrangQLHD});

        jpnTong.add(jpnHoaDon, "card5");

        jpnVoucher.setBackground(new java.awt.Color(255, 255, 255));
        jpnVoucher.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Quản lý voucher");
        jpnVoucher.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 6, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel59.setText("Mã voucher");
        jPanel7.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 21, -1, -1));

        jLabel60.setText("Phần trăm giảm(%)");
        jPanel7.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 57, -1, -1));

        jLabel61.setText("Hóa đơn tối thiểu:");
        jPanel7.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 93, 100, -1));

        jLabel62.setText("Giảm tối đa:");
        jPanel7.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 129, 85, -1));

        jLabel63.setText("Số lượng:");
        jPanel7.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 165, 85, -1));

        btnCleanMaGiamGia.setBackground(new java.awt.Color(45, 132, 252));
        btnCleanMaGiamGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCleanMaGiamGia.setForeground(new java.awt.Color(255, 255, 255));
        btnCleanMaGiamGia.setText("Xóa trắng");
        btnCleanMaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanMaGiamGiaActionPerformed(evt);
            }
        });
        jPanel7.add(btnCleanMaGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 500, -1, -1));

        txtMaVorCher.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        txtMaVorCher.setEnabled(false);
        jPanel7.add(txtMaVorCher, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 21, 202, -1));

        txtPhanTramGiam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel7.add(txtPhanTramGiam, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 57, 202, -1));

        txtHoaDonToiThieu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        txtHoaDonToiThieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHoaDonToiThieuKeyReleased(evt);
            }
        });
        jPanel7.add(txtHoaDonToiThieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 93, 202, -1));

        txtGiamToiDa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        txtGiamToiDa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiamToiDaKeyReleased(evt);
            }
        });
        jPanel7.add(txtGiamToiDa, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 129, 202, -1));

        txtSoLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel7.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 165, 202, -1));

        btnTaoMoi.setBackground(new java.awt.Color(45, 132, 252));
        btnTaoMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoMoi.setText("Tạo mới");
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
            }
        });
        jPanel7.add(btnTaoMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 500, -1, -1));

        jLabel100.setText("Ngày bắt đầu:");
        jPanel7.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 85, -1));

        txtNgayBatDau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        txtNgayBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayBatDauActionPerformed(evt);
            }
        });
        jPanel7.add(txtNgayBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 202, -1));

        jLabel109.setText("Mã người tạo");
        jPanel7.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 85, -1));

        txtMaNguoiTao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        txtMaNguoiTao.setEnabled(false);
        jPanel7.add(txtMaNguoiTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 202, -1));

        jLabel110.setText("Ngày kết thúc:");
        jPanel7.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 85, -1));

        txtNgayKetThuc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel7.add(txtNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 202, -1));

        btnThuHoiMaVouCher.setBackground(new java.awt.Color(45, 132, 252));
        btnThuHoiMaVouCher.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThuHoiMaVouCher.setForeground(new java.awt.Color(255, 255, 255));
        btnThuHoiMaVouCher.setText("Thu hồi");
        btnThuHoiMaVouCher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuHoiMaVouCherActionPerformed(evt);
            }
        });
        jPanel7.add(btnThuHoiMaVouCher, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, -1, -1));

        jTabbedPane2.addTab("Tạo voucher", jPanel7);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnVoucher.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 38, 430, 730));

        tblVorCherFrom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã voucher", "Phần trăm giảm", "Hóa đơn tối thiểu", "Giảm tối đa", "Số lượng", "Ngày bắt đầu", "Ngày kết thúc", "Mã người tạo", "Tên người tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVorCherFrom.setGridColor(new java.awt.Color(0, 65, 123));
        tblVorCherFrom.setRowHeight(40);
        tblVorCherFrom.setSelectionBackground(new java.awt.Color(51, 204, 255));
        tblVorCherFrom.setSelectionForeground(new java.awt.Color(255, 0, 0));
        tblVorCherFrom.setShowGrid(true);
        tblVorCherFrom.setSurrendersFocusOnKeystroke(true);
        tblVorCherFrom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVorCherFromMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblVorCherFrom);

        jpnVoucher.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 820, 680));

        txtTimKiemMaGiamGia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemMaGiamGiaFocusGained(evt);
            }
        });
        txtTimKiemMaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemMaGiamGiaActionPerformed(evt);
            }
        });
        txtTimKiemMaGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemMaGiamGiaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemMaGiamGiaKeyReleased(evt);
            }
        });
        jpnVoucher.add(txtTimKiemMaGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 42, 820, 30));

        jpnTong.add(jpnVoucher, "card6");

        jpnTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Quản lý tài khoản");

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel65.setText("Mã tài khoản");

        jLabel66.setText("Mã nhân viên");

        jLabel67.setText("Mật khẩu");

        jLabel68.setText("Vai trò");

        jLabel69.setText("Trạng thái");

        btnCapNhatTaiKhoanNhanVien.setBackground(new java.awt.Color(45, 132, 252));
        btnCapNhatTaiKhoanNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCapNhatTaiKhoanNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhatTaiKhoanNhanVien.setText("Cập nhật");
        btnCapNhatTaiKhoanNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatTaiKhoanNhanVienActionPerformed(evt);
            }
        });

        txtMaTaiKhoanSua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        txtMaTaiKhoanSua.setEnabled(false);

        txtMatKhauTaiKhoanSua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        cbbMaNhanVienTaiKhoanSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaNhanVienTaiKhoanSuaActionPerformed(evt);
            }
        });

        cbbVaiTroTaiKhoanSua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "QuanLy", "PhaChe", "NhanVien" }));

        cbbTrangThaiTaiKhoanSua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không Khoá", "Đã Khoá" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCapNhatTaiKhoanNhanVien))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMatKhauTaiKhoanSua, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addComponent(txtMaTaiKhoanSua)
                                .addComponent(cbbMaNhanVienTaiKhoanSua, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(cbbVaiTroTaiKhoanSua, 0, 175, Short.MAX_VALUE)
                            .addComponent(cbbTrangThaiTaiKhoanSua, 0, 175, Short.MAX_VALUE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(txtMaTaiKhoanSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(cbbMaNhanVienTaiKhoanSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(txtMatKhauTaiKhoanSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(cbbVaiTroTaiKhoanSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(cbbTrangThaiTaiKhoanSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 324, Short.MAX_VALUE)
                .addComponent(btnCapNhatTaiKhoanNhanVien)
                .addGap(23, 23, 23))
        );

        jTabbedPane3.addTab("Thông tin tài khoản", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel70.setText("Mã tài khoản");

        jLabel71.setText("Mã nhân viên");

        jLabel72.setText("Mật khẩu");

        jLabel73.setText("Vai trò");

        jLabel74.setText("Trạng thái");

        txtMaTaiKhoanThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        cbbMaNhanVienTaiKhoanThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaNhanVienTaiKhoanThemActionPerformed(evt);
            }
        });

        txtMatKhauThem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        cbbTrangThaiVaiTroThem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "QuanLy", "PhaChe", "NhanVien" }));

        cbbTrangThaiTaiKhoanThem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không Khoá", "Đã Khoá" }));

        btnThemTaiKhoan.setBackground(new java.awt.Color(45, 132, 252));
        btnThemTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnThemTaiKhoan.setText("Thêm");
        btnThemTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTaiKhoanActionPerformed(evt);
            }
        });

        btnCleanTaiKhoan.setBackground(new java.awt.Color(45, 132, 252));
        btnCleanTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCleanTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnCleanTaiKhoan.setText("Xóa trắng");
        btnCleanTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanTaiKhoanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnCleanTaiKhoan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemTaiKhoan))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMatKhauThem, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addComponent(txtMaTaiKhoanThem)
                                .addComponent(cbbMaNhanVienTaiKhoanThem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(cbbTrangThaiVaiTroThem, 0, 175, Short.MAX_VALUE)
                            .addComponent(cbbTrangThaiTaiKhoanThem, 0, 175, Short.MAX_VALUE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(txtMaTaiKhoanThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(cbbMaNhanVienTaiKhoanThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(txtMatKhauThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(cbbTrangThaiVaiTroThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(cbbTrangThaiTaiKhoanThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 326, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemTaiKhoan)
                    .addComponent(btnCleanTaiKhoan))
                .addGap(21, 21, 21))
        );

        jTabbedPane3.addTab("Thêm mới tài khoản", jPanel10);

        txtTimKiemTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemTaiKhoanActionPerformed(evt);
            }
        });
        txtTimKiemTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemTaiKhoanKeyPressed(evt);
            }
        });

        tblTaiKhoanForm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã tài khoản", "Mã nhân viên", "Mật khẩu", "Vai trò", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTaiKhoanForm.setGridColor(new java.awt.Color(0, 65, 123));
        tblTaiKhoanForm.setRowHeight(40);
        tblTaiKhoanForm.setSelectionBackground(new java.awt.Color(51, 204, 255));
        tblTaiKhoanForm.setSelectionForeground(new java.awt.Color(255, 0, 0));
        tblTaiKhoanForm.setShowGrid(true);
        tblTaiKhoanForm.setSurrendersFocusOnKeystroke(true);
        tblTaiKhoanForm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoanFormMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblTaiKhoanForm);

        javax.swing.GroupLayout jpnTaiKhoanLayout = new javax.swing.GroupLayout(jpnTaiKhoan);
        jpnTaiKhoan.setLayout(jpnTaiKhoanLayout);
        jpnTaiKhoanLayout.setHorizontalGroup(
            jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTaiKhoanLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jpnTaiKhoanLayout.createSequentialGroup()
                        .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiemTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(831, Short.MAX_VALUE))
        );
        jpnTaiKhoanLayout.setVerticalGroup(
            jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTaiKhoanLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addGap(38, 38, 38)
                .addGroup(jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnTaiKhoanLayout.createSequentialGroup()
                        .addComponent(txtTimKiemTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8))
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(308, Short.MAX_VALUE))
        );

        jpnTong.add(jpnTaiKhoan, "card7");

        jpnBackupHeThong.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Backup hệ thống");

        jTabbedPane6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane6MouseClicked(evt);
            }
        });

        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel106.setText("Backup hệ thống");

        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jCheckBox13.setSelected(true);
        jCheckBox13.setText("Sản phẩm");
        jCheckBox13.setEnabled(false);

        jCheckBox14.setSelected(true);
        jCheckBox14.setText("Hóa đơn");
        jCheckBox14.setEnabled(false);

        jCheckBox15.setSelected(true);
        jCheckBox15.setText("Mã giảm giá");
        jCheckBox15.setEnabled(false);

        jCheckBox16.setSelected(true);
        jCheckBox16.setText("Tài khoản");
        jCheckBox16.setEnabled(false);

        jCheckBox17.setSelected(true);
        jCheckBox17.setText("Nhân viên");
        jCheckBox17.setEnabled(false);
        jCheckBox17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox17ActionPerformed(evt);
            }
        });

        jCheckBox18.setSelected(true);
        jCheckBox18.setText("Bàn");
        jCheckBox18.setEnabled(false);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox14, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox15))
                .addContainerGap(355, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jCheckBox13)
                .addGap(27, 27, 27)
                .addComponent(jCheckBox14)
                .addGap(30, 30, 30)
                .addComponent(jCheckBox15)
                .addGap(26, 26, 26)
                .addComponent(jCheckBox16)
                .addGap(28, 28, 28)
                .addComponent(jCheckBox17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jCheckBox18)
                .addGap(15, 15, 15))
        );

        jLabel107.setText("Các thông tin backup");

        jButton22.setBackground(new java.awt.Color(45, 132, 252));
        jButton22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText("Tạo bản sao lưu");
        jButton22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton22MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel106))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel107)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel106)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel107)
                .addGap(9, 9, 9)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton22)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Backup", jPanel16);

        jLabel104.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel104.setText("Backup hệ thống");

        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblBackUp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(tblBackUp);

        jButton21.setBackground(new java.awt.Color(45, 132, 252));
        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("Khôi phục");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(137, 137, 137))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton21)
                .addGap(18, 18, 18))
        );

        jLabel105.setText("Chọn bản sao lưu");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel104))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel105)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel104)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel105)
                .addGap(9, 9, 9)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Restore", jPanel15);

        javax.swing.GroupLayout jpnBackupHeThongLayout = new javax.swing.GroupLayout(jpnBackupHeThong);
        jpnBackupHeThong.setLayout(jpnBackupHeThongLayout);
        jpnBackupHeThongLayout.setHorizontalGroup(
            jpnBackupHeThongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBackupHeThongLayout.createSequentialGroup()
                .addGroup(jpnBackupHeThongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnBackupHeThongLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel7))
                    .addGroup(jpnBackupHeThongLayout.createSequentialGroup()
                        .addGap(363, 363, 363)
                        .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1198, Short.MAX_VALUE))
        );
        jpnBackupHeThongLayout.setVerticalGroup(
            jpnBackupHeThongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBackupHeThongLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel7)
                .addGap(73, 73, 73)
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(473, Short.MAX_VALUE))
        );

        jpnTong.add(jpnBackupHeThong, "card8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        DangXuat dangXuat = new DangXuat();
        dangXuat.show();
        dangXuat.thongBao("Bạn có chắc chắn muốn đăng xuất không?");
        dangXuat.yes(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                dangXuat.dispose();
                new DangNhap().setVisible(true);
            }
        });
        dangXuat.no(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dangXuat.dispose();
            }
        });
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseClicked


    }//GEN-LAST:event_btnDangXuatMouseClicked
    private HoTroKhachHang htkh=new HoTroKhachHang();
    private void btnKhieuNaiHoTroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhieuNaiHoTroMouseClicked
//        jpnNhanVien.setVisible(false);
//        jpnSanPham.setVisible(false);
//        jpnQLBan.setVisible(false);
//        jpnHoaDon.setVisible(false);
//        jpnVoucher.setVisible(false);
//        jpnTaiKhoan.setVisible(false);
//        jpnBackupHeThong.setVisible(false);
      htkh.setVisible(true);

    }//GEN-LAST:event_btnKhieuNaiHoTroMouseClicked

    private void lblDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMatKhauMouseClicked
        jpnNenDoiMatKhau.setBackground(new Color(0, 88, 166));
        jpnNenSanPham.setBackground(new Color(0, 65, 123));
        jpnNenTaiKhoan.setBackground(new Color(0, 65, 123));
        jpnNenHoaDon.setBackground(new Color(0, 65, 123));
        jpnNenVoucher.setBackground(new Color(0, 65, 123));
        jpnNenNhanVien.setBackground(new Color(0, 65, 123));
        jpnNenBan.setBackground(new Color(0, 65, 123));
        jpnNenBackupHeThong.setBackground(new Color(0, 65, 123));

        jpnSanPham.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnTaiKhoan.setVisible(false);
        jpnHoaDon.setVisible(false);
        jpnVoucher.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnQLBan.setVisible(false);
        jpnBackupHeThong.setVisible(false);
        new DoiMatKhau(maTaiKhoan).setVisible(true);

    }//GEN-LAST:event_lblDoiMatKhauMouseClicked

    private void lblBackupHeThongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackupHeThongMouseClicked
        jpnNenBackupHeThong.setBackground(new Color(0, 88, 166));
        jpnNenSanPham.setBackground(new Color(0, 65, 123));
        jpnNenTaiKhoan.setBackground(new Color(0, 65, 123));
        jpnNenHoaDon.setBackground(new Color(0, 65, 123));
        jpnNenVoucher.setBackground(new Color(0, 65, 123));
        jpnNenNhanVien.setBackground(new Color(0, 65, 123));
        jpnNenBan.setBackground(new Color(0, 65, 123));
        jpnNenDoiMatKhau.setBackground(new Color(0, 65, 123));

        jpnSanPham.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnTaiKhoan.setVisible(false);
        jpnHoaDon.setVisible(false);
        jpnVoucher.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnQLBan.setVisible(false);
        jpnBackupHeThong.setVisible(true);
        new DoiMatKhau(maTaiKhoan).setVisible(false);
    }//GEN-LAST:event_lblBackupHeThongMouseClicked

    private void lblquanlyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblquanlyMouseClicked

        jpnTaiKhoan.setVisible(true);
        jpnNhanVien.setVisible(false);
        jpnSanPham.setVisible(false);
        jpnQLBan.setVisible(false);
        jpnHoaDon.setVisible(false);
        jpnVoucher.setVisible(false);
    }//GEN-LAST:event_lblquanlyMouseClicked

    private void lblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVoucherMouseClicked
        jpnNenVoucher.setBackground(new Color(0, 88, 166));
        jpnNenSanPham.setBackground(new Color(0, 65, 123));
        jpnNenTaiKhoan.setBackground(new Color(0, 65, 123));
        jpnNenHoaDon.setBackground(new Color(0, 65, 123));
        jpnNenNhanVien.setBackground(new Color(0, 65, 123));
        jpnNenBan.setBackground(new Color(0, 65, 123));
        jpnNenDoiMatKhau.setBackground(new Color(0, 65, 123));
        jpnNenBackupHeThong.setBackground(new Color(0, 65, 123));

        jpnSanPham.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnTaiKhoan.setVisible(false);
        jpnHoaDon.setVisible(false);
        jpnVoucher.setVisible(true);
        jpnNhanVien.setVisible(false);
        jpnQLBan.setVisible(false);
        jpnBackupHeThong.setVisible(false);
        new DoiMatKhau(maTaiKhoan).setVisible(false);
    }//GEN-LAST:event_lblVoucherMouseClicked

    private void lblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseClicked
        jpnNenHoaDon.setBackground(new Color(0, 88, 166));
        jpnNenSanPham.setBackground(new Color(0, 65, 123));
        jpnNenTaiKhoan.setBackground(new Color(0, 65, 123));
        jpnNenVoucher.setBackground(new Color(0, 65, 123));
        jpnNenNhanVien.setBackground(new Color(0, 65, 123));
        jpnNenBan.setBackground(new Color(0, 65, 123));
        jpnNenDoiMatKhau.setBackground(new Color(0, 65, 123));
        jpnNenBackupHeThong.setBackground(new Color(0, 65, 123));

        jpnSanPham.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnTaiKhoan.setVisible(false);
        jpnHoaDon.setVisible(true);
        jpnVoucher.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnQLBan.setVisible(false);
        jpnBackupHeThong.setVisible(false);
        new DoiMatKhau(maTaiKhoan).setVisible(false);
        phanTrangQLHD();
        truyenTrangQLHD(1);
    }//GEN-LAST:event_lblHoaDonMouseClicked

    private void lblQuanLyBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLyBanMouseClicked
        jpnNenBan.setBackground(new Color(0, 88, 166));
        jpnNenSanPham.setBackground(new Color(0, 65, 123));
        jpnNenTaiKhoan.setBackground(new Color(0, 65, 123));
        jpnNenHoaDon.setBackground(new Color(0, 65, 123));
        jpnNenVoucher.setBackground(new Color(0, 65, 123));
        jpnNenNhanVien.setBackground(new Color(0, 65, 123));
        jpnNenDoiMatKhau.setBackground(new Color(0, 65, 123));
        jpnNenBackupHeThong.setBackground(new Color(0, 65, 123));

        jpnSanPham.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnTaiKhoan.setVisible(false);
        jpnHoaDon.setVisible(false);
        jpnVoucher.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnQLBan.setVisible(true);
        jpnBackupHeThong.setVisible(false);
        new DoiMatKhau(maTaiKhoan).setVisible(false);
    }//GEN-LAST:event_lblQuanLyBanMouseClicked

    private void lblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseClicked
        jpnNenSanPham.setBackground(new Color(0, 88, 166));
        jpnNenTaiKhoan.setBackground(new Color(0, 65, 123));
        jpnNenHoaDon.setBackground(new Color(0, 65, 123));
        jpnNenVoucher.setBackground(new Color(0, 65, 123));
        jpnNenNhanVien.setBackground(new Color(0, 65, 123));
        jpnNenBan.setBackground(new Color(0, 65, 123));
        jpnNenDoiMatKhau.setBackground(new Color(0, 65, 123));
        jpnNenBackupHeThong.setBackground(new Color(0, 65, 123));

        jpnSanPham.setVisible(true);
        jpnNhanVien.setVisible(false);
        jpnTaiKhoan.setVisible(false);
        jpnHoaDon.setVisible(false);
        jpnVoucher.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnQLBan.setVisible(false);
        jpnBackupHeThong.setVisible(false);
        new DoiMatKhau(maTaiKhoan).setVisible(false);
    }//GEN-LAST:event_lblSanPhamMouseClicked

    private void lblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhanVienMouseClicked
        jpnNenNhanVien.setBackground(new Color(0, 88, 166));
        jpnNenSanPham.setBackground(new Color(0, 65, 123));
        jpnNenTaiKhoan.setBackground(new Color(0, 65, 123));
        jpnNenHoaDon.setBackground(new Color(0, 65, 123));
        jpnNenVoucher.setBackground(new Color(0, 65, 123));
        jpnNenBan.setBackground(new Color(0, 65, 123));
        jpnNenDoiMatKhau.setBackground(new Color(0, 65, 123));
        jpnNenBackupHeThong.setBackground(new Color(0, 65, 123));

        jpnSanPham.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnTaiKhoan.setVisible(false);
        jpnHoaDon.setVisible(false);
        jpnVoucher.setVisible(false);
        jpnNhanVien.setVisible(true);
        jpnQLBan.setVisible(false);
        jpnBackupHeThong.setVisible(false);
        new DoiMatKhau(maTaiKhoan).setVisible(false);
    }//GEN-LAST:event_lblNhanVienMouseClicked

    private void btnKhieuNaiHoTroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhieuNaiHoTroMouseEntered

    }//GEN-LAST:event_btnKhieuNaiHoTroMouseEntered

    private void lblNhanVienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhanVienMouseEntered
    }//GEN-LAST:event_lblNhanVienMouseEntered

    private void lblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoanMouseClicked
        jpnNenTaiKhoan.setBackground(new Color(0, 88, 166));
        jpnNenSanPham.setBackground(new Color(0, 65, 123));
        jpnNenHoaDon.setBackground(new Color(0, 65, 123));
        jpnNenVoucher.setBackground(new Color(0, 65, 123));
        jpnNenNhanVien.setBackground(new Color(0, 65, 123));
        jpnNenBan.setBackground(new Color(0, 65, 123));
        jpnNenDoiMatKhau.setBackground(new Color(0, 65, 123));
        jpnNenBackupHeThong.setBackground(new Color(0, 65, 123));

        jpnSanPham.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnTaiKhoan.setVisible(true);
        jpnHoaDon.setVisible(false);
        jpnVoucher.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnQLBan.setVisible(false);
        jpnBackupHeThong.setVisible(false);
        new DoiMatKhau(maTaiKhoan).setVisible(false);
    }//GEN-LAST:event_lblTaiKhoanMouseClicked

    private void lblThietLapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThietLapMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblThietLapMouseClicked

    private void cbbMaNhanVienTaiKhoanThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaNhanVienTaiKhoanThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbMaNhanVienTaiKhoanThemActionPerformed

    private void btnAnhNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnhNhanVienActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            // Tạo ImageIcon từ đường dẫn tệp tin ảnh
            ImageIcon imageIcon = new ImageIcon(filePath);

            // Lấy kích thước của JLabel
            int labelWidth = lblAnhNhanVien.getWidth();
            int labelHeight = lblAnhNhanVien.getHeight();

            // Lấy Image từ ImageIcon
            Image image = imageIcon.getImage();

            // Thay đổi kích thước của ảnh để khớp với kích thước của JLabel
            Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

            // Tạo ImageIcon mới từ ảnh đã được thay đổi kích thước
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Thiết lập ImageIcon mới cho JLabel
            lblAnhNhanVien.setIcon(scaledIcon);
    }//GEN-LAST:event_btnAnhNhanVienActionPerformed
    }

    public void clean() {
        lblAnhNhanVien.setIcon(null);
        txtHoVaTenThem.setText("");
        txtNgaySinhThem.setText("");
        txtDiaChiThem.setText("");
        txtCCCDThem.setText("");
        txtEmailThem.setText("");
        txtSDTThem.setText("");
        txtGhiChuThem.setText("");
    }
    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        NhanVienViewModel nhanVienViewModel = getDataNhanVien();
        if (nhanVienViewModel == null) {
            return;
        }
        JOptionPane.showMessageDialog(this, iNhanVienService.insertNhanVien(nhanVienViewModel));
        loadTableNhanVien(iNhanVienService.getAll());
        loadComBoBoxTaiKhoanMaNhanVien(iNhanVienService.getAll());

    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void cbbChucVuNhanVienThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChucVuNhanVienThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbChucVuNhanVienThemActionPerformed

    private void tblNhanVienFormMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienFormMouseClicked
        int row = tblNhanVienForm.getSelectedRow();
        String maNhanVien = tblNhanVienForm.getValueAt(row, 0).toString();
        String hoVaTen = tblNhanVienForm.getValueAt(row, 1).toString();
        String cccd = tblNhanVienForm.getValueAt(row, 2).toString();
        String sdt = tblNhanVienForm.getValueAt(row, 3).toString();
        String email = tblNhanVienForm.getValueAt(row, 4).toString();
        String chucVu = tblNhanVienForm.getValueAt(row, 5).toString();
        int maNhanVienInt = Integer.parseInt(maNhanVien);
        txtMaNhanVienXem.setText(maNhanVien);
        txtHoVaTenXem.setText(hoVaTen);
        txtCCCDXem.setText(cccd);
        txtSDTXem.setText(sdt);
        txtEmailXem.setText(email);
        cbbChucVuNhanVienXem.setSelectedItem(chucVu);
        //
        NhanVienViewModel nhanVienViewModel = iNhanVienService.loadMouseclicked(maNhanVienInt);
        Date ngaySinh = nhanVienViewModel.getNgaySinh();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngaySinhString = sdf.format(ngaySinh);
        txtNgaySinhXem.setText(ngaySinhString);
        String diaChi = nhanVienViewModel.getDiaChi();
        txtDiaChiXem.setText(diaChi);
        String ghiChu = nhanVienViewModel.getGhiChu();
        txtGhiChuXem.setText(ghiChu);
        int trangThai = nhanVienViewModel.getTrangThai();
        if (trangThai == 0) {
            cbbTrangThaiNhanVienXem.setSelectedItem("Đã nghỉ việc");
        } else {
            cbbTrangThaiNhanVienXem.setSelectedItem("Đang làm việc");
        }

        Blob anh = nhanVienViewModel.getAnh();
        if (anh != null) {
            try {
                // Đọc dữ liệu từ đối tượng Blob thành mảng byte
                byte[] imageData = anh.getBytes(1, (int) anh.length());

                // Chuyển đổi mảng byte thành ImageIcon
                ImageIcon imageIcon = new ImageIcon(imageData);
                ///////////////tuan anh

                // Lấy kích thước của JLabel
                int labelWidth = lblAnhNhanVienSua.getWidth();
                int labelHeight = lblAnhNhanVienSua.getHeight();

                // Lấy Image từ ImageIcon
                Image image = imageIcon.getImage();

                // Thay đổi kích thước của ảnh để khớp với kích thước của JLabel
                Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

                // Tạo ImageIcon mới từ ảnh đã được thay đổi kích thước
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                // Thiết lập ImageIcon mới cho JLabel
                lblAnhNhanVienSua.setIcon(scaledIcon);
                ///////////////tuan anh

            } catch (SQLException e) {
                e.printStackTrace();
                // Xử lý lỗi khi đọc dữ liệu từ Blob
            }
        } else {
            // Xử lý trường hợp đối tượng Blob là null hoặc không chứa dữ liệu ảnh
            lblAnhNhanVienSua.setIcon(null);
        }


    }//GEN-LAST:event_tblNhanVienFormMouseClicked

    private void btnCapNhatNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatNhanVienActionPerformed
        int row = tblNhanVienForm.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn cập nhật");
            return;
        }
        NhanVienViewModel nhanVienViewModel = getDataNhanVienCapNhat();
        if (nhanVienViewModel == null) {
            return;
        }
        String maNhanVien = tblNhanVienForm.getValueAt(row, 0).toString();
        int maNhanVienInt = Integer.parseInt(maNhanVien);
        JOptionPane.showMessageDialog(this, iNhanVienService.update(maNhanVienInt, nhanVienViewModel));
        loadTableNhanVien(iNhanVienService.getAll());
    }//GEN-LAST:event_btnCapNhatNhanVienActionPerformed

    private void btnAnhNhanVienSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnhNhanVienSuaActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            // Tạo ImageIcon từ đường dẫn tệp tin ảnh
            ImageIcon imageIcon = new ImageIcon(filePath);

            // Lấy kích thước của JLabel
            int labelWidth = lblAnhNhanVienSua.getWidth();
            int labelHeight = lblAnhNhanVienSua.getHeight();

            // Lấy Image từ ImageIcon
            Image image = imageIcon.getImage();

            // Thay đổi kích thước của ảnh để khớp với kích thước của JLabel
            Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

            // Tạo ImageIcon mới từ ảnh đã được thay đổi kích thước
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Thiết lập ImageIcon mới cho JLabel
            lblAnhNhanVienSua.setIcon(scaledIcon);
        }
    }//GEN-LAST:event_btnAnhNhanVienSuaActionPerformed

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        clean();
    }//GEN-LAST:event_btnCleanActionPerformed

    private void jCheckBox17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox17ActionPerformed

    }//GEN-LAST:event_jCheckBox17ActionPerformed

    private void jTabbedPane6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane6MouseClicked
        // TODO add your handling code here:
        loadToTableBackUp();
    }//GEN-LAST:event_jTabbedPane6MouseClicked

    private void jButton22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton22MouseClicked
        // TODO add your handling code here:
        DBackUpAndRestore.createBackup();
    }//GEN-LAST:event_jButton22MouseClicked

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        if (tblBackUp.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn bản sao lưu");
            return;
        }
// TODO add your handling code here:
        DBackUpAndRestore.restoreDatabase(tblBackUp.getValueAt(tblBackUp.getSelectedRow(), 0).toString());
        this.dispose();
        new DangNhap().setVisible(true);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void txtTimKiemTenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTenKeyPressed
        String searchTen = txtTimKiemTen.getText();
        if (searchTen.isEmpty()) {
            loadTableNhanVien(iNhanVienService.getAll());
        } else {
            ArrayList<NhanVienViewModel> listNhanVien = iNhanVienService.getNhanVienByTen(searchTen);
            loadTableNhanVien(listNhanVien);
        }
    }//GEN-LAST:event_txtTimKiemTenKeyPressed

    private void txtTimKiemTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemTenKeyReleased

    private void cbbTimKiemTrangThaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbbTimKiemTrangThaiKeyPressed


    }//GEN-LAST:event_cbbTimKiemTrangThaiKeyPressed

    private void cbbTimKiemTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimKiemTrangThaiActionPerformed
        String trangThai = cbbTimKiemTrangThai.getSelectedItem().toString();
        if (trangThai.equals("Đang làm việc")) {
            ArrayList<NhanVienViewModel> listNhanVien = iNhanVienService.getNhanVienByTrangThai(1);
            loadTableNhanVien(listNhanVien);
        } else if (trangThai.equals("Đã nghỉ việc")) {
            ArrayList<NhanVienViewModel> listNhanVien = iNhanVienService.getNhanVienByTrangThai(0);
            loadTableNhanVien(listNhanVien);
        } else {
            loadTableNhanVien(iNhanVienService.getAll());
        }
    }//GEN-LAST:event_cbbTimKiemTrangThaiActionPerformed

    private void txtTimKiemTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemTenActionPerformed


    }//GEN-LAST:event_txtTimKiemTenActionPerformed

    private void btnThemTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTaiKhoanActionPerformed
        TaiKhoanViewModel taiKhoanViewModel = getDataTaiKhoanThem();
        if (taiKhoanViewModel == null) {
            return;
        }

        JOptionPane.showMessageDialog(this, iTaiKhoanServicess.insertTaiKhoan(taiKhoanViewModel));
        loadTableTaiKhoan(iTaiKhoanServicess.getAll());
    }//GEN-LAST:event_btnThemTaiKhoanActionPerformed
    public void Clean() {
        txtMaTaiKhoanThem.setText("");
        txtMatKhauThem.setText("");
    }
    private void btnCleanTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanTaiKhoanActionPerformed
        Clean();
    }//GEN-LAST:event_btnCleanTaiKhoanActionPerformed

    private void tblTaiKhoanFormMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanFormMouseClicked
        int row = tblTaiKhoanForm.getSelectedRow();
        String maTaiKhoan = tblTaiKhoanForm.getValueAt(row, 0).toString();
        String maNhanVien = tblTaiKhoanForm.getValueAt(row, 1).toString();
        String matKhau = tblTaiKhoanForm.getValueAt(row, 2).toString();
        String vaiTro = tblTaiKhoanForm.getValueAt(row, 3).toString();
        String trangThai = tblTaiKhoanForm.getValueAt(row, 4).toString();
        txtMaTaiKhoanSua.setText(maTaiKhoan);
        txtMatKhauTaiKhoanSua.setText(matKhau);
        cbbMaNhanVienTaiKhoanSua.setSelectedItem(maNhanVien);
        cbbVaiTroTaiKhoanSua.setSelectedItem(vaiTro);
        cbbTrangThaiTaiKhoanSua.setSelectedItem(trangThai);

    }//GEN-LAST:event_tblTaiKhoanFormMouseClicked
    public boolean isMNVExists(int maNhanVien, int maNhanVienCu) {
        if (maNhanVien == maNhanVienCu) {
            return false; // Không trùng lặp
        }

        Set<Integer> existingMANVs = new HashSet<>();
        List<TaiKhoanViewModel> existingTaiKhoans = iTaiKhoanServicess.getAll();
        for (TaiKhoanViewModel tk : existingTaiKhoans) {
            existingMANVs.add(tk.getMaNhanVien());
        }

        if (existingMANVs.contains(maNhanVien)) {
            // Mã nhân viên đã tồn tại, nhưng không cần hiển thị thông báo ở đây
            return true;
        }

        return false; // Không trùng lặp
    }


    private void btnCapNhatTaiKhoanNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatTaiKhoanNhanVienActionPerformed
        int row = tblTaiKhoanForm.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để cập nhật");
            return;
        }

        TaiKhoanViewModel taiKhoanViewModel = getDataTaiKhoanCapNhat();

        if (taiKhoanViewModel == null) {
            return;
        }

        String maTaiKhoan1 = tblTaiKhoanForm.getValueAt(row, 0).toString();
        JOptionPane.showMessageDialog(this, iTaiKhoanServicess.updateTaiKhoan(maTaiKhoan1, taiKhoanViewModel));
        loadTableTaiKhoan(iTaiKhoanServicess.getAll());
    }//GEN-LAST:event_btnCapNhatTaiKhoanNhanVienActionPerformed

    private void btnBanCapNhatClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanCapNhatClearActionPerformed
        // TODO add your handling code here:
        txtBanCapNhatTenBan.setText("");
    }//GEN-LAST:event_btnBanCapNhatClearActionPerformed

    private void btnBanThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanThemActionPerformed
        // TODO add your handling code here:           
        try {
            String nameBan = txtBanThemTenBan.getText();
            if (nameBan.trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Tên bàn không được để trống!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (nameBan.startsWith(" ") || nameBan.endsWith(" ") || nameBan.contains("  ")) {
                JOptionPane.showMessageDialog(this, "Tên bàn không được chứa khoảng trắng!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
                return;
            }
            List<QuanLyBanViewmodel> listBanviewmodel = ibanServices.getListBan();
//            for (QuanLyBanViewmodel qlban : listBanviewmodel) {
//                if (nameBan.trim().equals(qlban.getTenBan())) {
//                    JOptionPane.showMessageDialog(this, "Tên bàn không được trùng!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
//                    return;
//                }
//            }
            int layTang;
            if ((cboBanThemTang.getSelectedItem() + "").equalsIgnoreCase("Mang về")) {
                layTang = 0;
            } else {
                layTang = Integer.parseInt(cboBanThemTang.getSelectedItem() + "");
            }
            if (BanService.CheckTrungTenBan(nameBan, layTang) == true) {
                JOptionPane.showMessageDialog(this, "Tên bàn đã tồn tại, vui lòng kiểm tra lại!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int tangCN;
            if ((cboBanThemTang.getSelectedItem() + "").equalsIgnoreCase("Mang về")) {
                tangCN = 0;
            } else {
                tangCN = Integer.parseInt(cboBanThemTang.getSelectedItem() + "");
            }
            QuanLyBanViewmodel ban = new QuanLyBanViewmodel(11, txtBanThemTenBan.getText(),
                    tangCN);
            int thongBao = ibanServices.themBan(ban);
            if (thongBao == 1) {
                JOptionPane.showMessageDialog(this, "thêm thành công");
                fillBanTableBan();
                return;
            } else {
                JOptionPane.showMessageDialog(this, "thêm thất bại");
                return;
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBanThemActionPerformed

    private void btnBanThemClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanThemClearActionPerformed
        // TODO add your handling code here:
        txtBanThemTenBan.setText("");
    }//GEN-LAST:event_btnBanThemClearActionPerformed

    private void btnBanCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanCapNhatActionPerformed
        // TODO add your handling code here:
        try {
            List<QuanLyBanViewmodel> listBanviewmodel = ibanServices.getListBan();
            int selectedIndex = tblBanBan.getSelectedRow();
            String nameBan = txtBanCapNhatTenBan.getText();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn muốn cập nhật!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                if (nameBan.trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Tên bàn không được để trống!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (nameBan.startsWith(" ") || nameBan.endsWith(" ") || nameBan.contains("  ")) {
                    JOptionPane.showMessageDialog(this, "Tên bàn không được chứa khoảng trắng!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String selectedName = listBanviewmodel.get(selectedIndex).getTenBan();
//                for (int i = 0; i < listBanviewmodel.size(); i++) {
//                    if (i == selectedIndex) {
//                        continue;
//                    }
//                    QuanLyBanViewmodel ban = listBanviewmodel.get(i);
//                    if (nameBan.trim().equals(String.valueOf(ban.getTenBan())) && !selectedName.equals(nameBan.trim())) {
//                        JOptionPane.showMessageDialog(this, "Tên bàn không được trùng!", "LỖI", JOptionPane.WARNING_MESSAGE);
//                        return;
//                    }
//                }
                int layTang;
                if ((cboBanCapNhatTang.getSelectedItem() + "").equalsIgnoreCase("Mang về")) {
                    layTang = 0;
                } else {
                    layTang = Integer.parseInt(cboBanCapNhatTang.getSelectedItem() + "");
                }
                if (BanService.CheckTrungTenBan(nameBan, layTang) == true) {
                    JOptionPane.showMessageDialog(this, "Tên bàn đã tồn tại, vui lòng kiểm tra lại!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                QuanLyBanViewmodel selectedBan = listBanviewmodel.get(selectedIndex);
                selectedBan.setTenBan(String.valueOf(nameBan));
                int tangCN;
                if ((cboBanCapNhatTang.getSelectedItem() + "").equalsIgnoreCase("Mang về")) {
                    tangCN = 0;
                } else {
                    tangCN = Integer.parseInt(cboBanCapNhatTang.getSelectedItem() + "");
                }
                QuanLyBanViewmodel ban
                        = new QuanLyBanViewmodel(Integer.parseInt(lblBanCapNhatMaBan.getText()),
                                txtBanCapNhatTenBan.getText(), tangCN);
                int thongBao = ibanServices.CapNhatBan(ban);
                if (thongBao == 1) {
                    JOptionPane.showMessageDialog(this, "cập nhật thành công");
                    fillBanTableBan();
                    return;
                } else {
                    JOptionPane.showMessageDialog(this, "cập nhật thất bại");
                    return;
                }

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBanCapNhatActionPerformed

   

    private void cboTimBanTheoTangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboTimBanTheoTangKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboTimBanTheoTangKeyPressed

    private void cboTimBanTheoTangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimBanTheoTangActionPerformed
        // TODO add your handling code here:
        if (cboTimBanTheoTang.getSelectedItem().equals("Mang về")) {
            BanBanModel.setRowCount(0);
            for (QuanLyBanViewmodel a : listBanviewmodel) {
                if (a.getTang() == 0) {
                    BanBanModel.addRow(new Object[]{
                        a.getMaBan(), a.getTenBan(), "Mang về"
                    });
                }
            }
        } else {
            int tang = Integer.parseInt(cboTimBanTheoTang.getSelectedItem() + "");
            BanBanModel.setRowCount(0);
            for (QuanLyBanViewmodel a : listBanviewmodel) {
                if (a.getTang() == tang) {
                    BanBanModel.addRow(new Object[]{
                        a.getMaBan(), a.getTenBan(), a.getTang()
                    });
                }
            }
        }
    }//GEN-LAST:event_cboTimBanTheoTangActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        try {
            Thread.sleep(200);
        } catch (Exception e) {
        }
        fillBanTableBan();
        JOptionPane.showMessageDialog(null, "danh sách đã hiển thị");
    }//GEN-LAST:event_jLabel9MouseClicked

    private void tblBanBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBanBanMouseClicked
        // TODO add your handling code here:
        int index = tblBanBan.getSelectedRow();
        fillMaBan(index);
    }//GEN-LAST:event_tblBanBanMouseClicked

    private void txtTimKiemTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTaiKhoanKeyPressed
        String searchMaTaiKhoan = txtTimKiemTaiKhoan.getText();
        if (searchMaTaiKhoan.isEmpty()) {
            loadTableTaiKhoan(iTaiKhoanServicess.getAll());
        } else {
            ArrayList<TaiKhoanViewModel> listTaiKhoan = iTaiKhoanServicess.getListTaiKhoanByMa(searchMaTaiKhoan);
            loadTableTaiKhoan(listTaiKhoan);
        }
    }//GEN-LAST:event_txtTimKiemTaiKhoanKeyPressed

    private void txtTimKiemTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemTaiKhoanActionPerformed

    private void tblQuanLySanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanLySanPhamMouseClicked
        sanPhamMouclick();
        CTSPMouclick();

    }//GEN-LAST:event_tblQuanLySanPhamMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        cleanSanPham();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnChonAnhSanPhamXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhSanPhamXemActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            // Tạo ImageIcon từ đường dẫn tệp tin ảnh
            ImageIcon imageIcon = new ImageIcon(filePath);

            // Lấy kích thước của JLabel
            int labelWidth = lblAnhSanPhamXem.getWidth();
            int labelHeight = lblAnhSanPhamXem.getHeight();

            // Lấy Image từ ImageIcon
            Image image = imageIcon.getImage();

            // Thay đổi kích thước của ảnh để khớp với kích thước của JLabel
            Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

            // Tạo ImageIcon mới từ ảnh đã được thay đổi kích thước
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Thiết lập ImageIcon mới cho JLabel
            lblAnhSanPhamXem.setIcon(scaledIcon);
        }
    }//GEN-LAST:event_btnChonAnhSanPhamXemActionPerformed
    private void clearCapNhat() {
        lblAnhSanPhamXem.setIcon(null);
        txtMaSanPhamXem.setText("");
        txtTenSanPhamXem.setText("");
        chkSizeSXem.setSelected(false);
        chkSizeMXem.setSelected(false);
        chkSizeLXem.setSelected(false);
        txtGiaSizeSXem.setText("");
        txtGiaSizeMXem.setText("");
        txtGiaSizeLXem.setText("");
        txtMoTaSanPhamXem.setText("");

    }
    private void btnCapNhatSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSanPhamActionPerformed
        int dong = tblQuanLySanPham.getSelectedRow();
        if (dong < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn cập nhật", "LỖI", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            if (checkCapNhatSP()) {
                int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật không?", "THÔNG BÁO", JOptionPane.YES_NO_OPTION);
                if (chon == JOptionPane.YES_OPTION) {
                    themSizeCTSP();
                    updateGiaCTSP();
                    capNhatSanPham();
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    loadTableSanPham();
                } else {
                    return;
                }
            }
        }
    }//GEN-LAST:event_btnCapNhatSanPhamActionPerformed

    private void btnChonAnhSanPhamThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhSanPhamThemActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            // Tạo ImageIcon từ đường dẫn tệp tin ảnh
            ImageIcon imageIcon = new ImageIcon(filePath);

            // Lấy kích thước của JLabel
            int labelWidth = lblAnhSanPhamThem.getWidth();
            int labelHeight = lblAnhSanPhamThem.getHeight();

            // Lấy Image từ ImageIcon
            Image image = imageIcon.getImage();

            // Thay đổi kích thước của ảnh để khớp với kích thước của JLabel
            Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

            // Tạo ImageIcon mới từ ảnh đã được thay đổi kích thước
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Thiết lập ImageIcon mới cho JLabel
            lblAnhSanPhamThem.setIcon(scaledIcon);
        }
    }//GEN-LAST:event_btnChonAnhSanPhamThemActionPerformed

    private void txtTimKiemSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamKeyReleased
        SanPhamViewModel ctsp = new SanPhamViewModel();
        String searchTen = txtTimKiemSanPham.getText();
        ArrayList<SanPhamViewModel> listSPVM = iCTSPSe.getSanPhamByTen(searchTen);
        DefaultTableModel tableModelSanPham = (DefaultTableModel) tblQuanLySanPham.getModel();
        tableModelSanPham.setRowCount(0);
        for (SanPhamViewModel spView : listSPVM) {
            tableModelSanPham.addRow(new Object[]{
                spView.getMaSanPham(),
                spView.getTenSanPham(),
                spView.getStatus(),
                spView.getMotTa()});
            System.out.println(spView.getTenSanPham());

        }
        if (listSPVM.isEmpty()) {
            tblQuanLySanPham.removeAll();
        }
    }//GEN-LAST:event_txtTimKiemSanPhamKeyReleased

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        if (checkThemQLSP()) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?", "THÔNG BÁO", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                themSP();
                loadTableSanPham();
                themCTSP();
                loadTableSanPham();
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed
    public void cleanMaGiamGia() {
        txtPhanTramGiam.setText("");
        txtHoaDonToiThieu.setText("");
        txtGiamToiDa.setText("");
        txtSoLuong.setText("");
        txtNgayKetThuc.setText("");
        txtMaVorCher.setText("");
        txtNgayBatDau.setText("");
        txtMaNguoiTao.setText("");
    }
    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        MaGiamGiaViewModel maGiamGiaViewModel = getDataMaGiamGia();
        if (maGiamGiaViewModel == null) {
            return;
        }
        JOptionPane.showMessageDialog(this, iMaGiamGiaService.insertMaGiamGia(maGiamGiaViewModel));
        loadTableVorCher(iMaGiamGiaService.getListMaGiamGia());
    }//GEN-LAST:event_btnTaoMoiActionPerformed

    private void tblQuanLyHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanLyHoaDonMouseClicked
        // TODO add your handling code here:
        DecimalFormat dcf = new DecimalFormat("###,###,###");
        int index = tblQuanLyHoaDon.getSelectedRow();
        int maHoaDon = (int) tblQuanLyHoaDon.getValueAt(index, 0);

        List<QuanLyHoaDonViewModel> qlhd = QLHDService.getListQLHDTheoMaHD(maHoaDon);

        for (QuanLyHoaDonViewModel hd : qlhd) {
            txtTongThanhToanQLHD.setText(dcf.format(tblQuanLyHoaDon.getValueAt(index, 2)) + " VND");
            txtQLHDTongHoaDon.setText(dcf.format(BigDecimal.valueOf(QLHDService.TongHoaDonQLHD(hd.getMaHoaDon()))) + " VND");

            txtQLHDMaHoaDon.setText(hd.getMaHoaDon() + "");
            txtQLHDMaNhanVien.setText(hd.getMaNhanVien() + "");
            txtQLHDThoiGian.setText(hd.getThoiGian() + "");

            if (hd.getTrangThaiThanhToan() == 0) {
                txtQLHDTrangThaiThanhToan.setText("Chưa thanh toán");
            }
            if (hd.getTrangThaiThanhToan() == 1) {
                txtQLHDTrangThaiThanhToan.setText("Đã thanh toán");
            }
            if (hd.getTrangThaiThanhToan() == 2) {
                txtQLHDTrangThaiThanhToan.setText("Đã hủy");
            }
            txtQLHDMaGiamGia.setText(hd.getMaVoucher() + "");
            if (hd.getMaVoucher() == 0) {
                txtQLHDMaGiamGia.setText("Không có");
            }
            txtQLHDGhiChu.setText(hd.getGhiChu());
            txtQLHDDichVuPhatSinh.setText(dcf.format(hd.getDichVuPhatSinh()) + " VND");
        }
        txtQLHDTang.setText("");
        txtQLHDBan.setText("");
        List<VaiTroQuanLyBanViewModel> banView = QLHDService.getBanQLHD(maHoaDon);
        if (banView.size() > 0) {
            for (VaiTroQuanLyBanViewModel ban : banView) {
                txtQLHDTang.setText(ban.getTang() + "");
                txtQLHDBan.setText(ban.getTenBan());
            }
        }

        LoadTableDSSPQLHoaDon();
    }//GEN-LAST:event_tblQuanLyHoaDonMouseClicked

    private void txtTimKiemQuanLyHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemQuanLyHoaDonKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemQuanLyHoaDonKeyReleased

    private void jdcTimTheoNgayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdcTimTheoNgayKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_jdcTimTheoNgayKeyReleased

    private void jdcTimTheoNgayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdcTimTheoNgayKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_jdcTimTheoNgayKeyTyped

    private void lblTimKiemTheoNgayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTimKiemTheoNgayMouseClicked
        // TODO add your handling code here:
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            String ngay = df.format(jdcTimTheoNgay.getDate());

            java.util.Date ngayTim = (java.util.Date) df.parse(ngay);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mời chọn ngày muốn tìm!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
            jdcTimTheoNgay.requestFocus();
            return;
        }
        try {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String ngay = df.format(jdcTimTheoNgay.getDate());

            java.util.Date ngayTim = (java.util.Date) df.parse(ngay);

            List<NhanVienHoaDonViewModel> lst = NVHoaDonSv.getList(ListDSSP, mapTenNV, mapTenBan, listCTHD, maGiamGia);
            List<NhanVienHoaDonViewModel> listQLHDPhanTrang = QLHDService.TimKiemQLHoaDonTheoNgay(ngayTim, lst);
            if (listQLHDPhanTrang.size() <= 0) {
                tblQuanLyHoaDon.removeAll();
                JOptionPane.showMessageDialog(this, "Ngày này không có hóa đơn nào!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ///////////
            if (listQLHDPhanTrang.size() > 100) {
                double a = listQLHDPhanTrang.size();
                double b = 100;
                soTrangQLHD = (int) Math.ceil(a / b);

            } else {
                soTrangQLHD = 1;
            }
            if (soTrangQLHD > 1) {
                for (int i = 1; i <= soTrangQLHD; i++) {
                    List<NhanVienHoaDonViewModel> listTrang = new ArrayList<>();
                    if (i == soTrangQLHD) {
                        int doDaiTrangCuoi = listQLHDPhanTrang.size() - (soTrangQLHD - 1) * 100;
                        for (int j = ((soTrangQLHD - 1) * 100); j < (doDaiTrangCuoi + (((soTrangQLHD - 1) * 100))); j++) {
                            listTrang.add(listQLHDPhanTrang.get(j));
                        }

                    } else {
                        if (i == 1) {
                            for (int k = 0; k < 100; k++) {
                                listTrang.add(listQLHDPhanTrang.get(k));
                            }
                        } else {
                            for (int h = (i - 1) * 100; h <= ((i - 1) * 100 + 99); h++) {
                                listTrang.add(listQLHDPhanTrang.get(h));
                            }
                        }
                    }
                    mapPhanTrangQLHD.put(i, listTrang);
                }
                demTrangQLHD = 1;
                truyenTrangQLHD(demTrangQLHD);

            } else {
                demTrangQLHD = 1;
                mapPhanTrangQLHD.put(1, listQLHDPhanTrang);
                truyenTrangQLHD(demTrangQLHD);
            }

            ///////////
        } catch (Exception e) {
        }
    }//GEN-LAST:event_lblTimKiemTheoNgayMouseClicked

    private void txtTimKiemQuanLyHoaDonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemQuanLyHoaDonKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemQuanLyHoaDonKeyTyped

    private void btnTimKiemQLHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemQLHDActionPerformed
        // TODO add your handling code here:
        if (txtTimKiemQuanLyHoaDon.getText().equals("")) {
            phanTrangQLHD();
            truyenTrangQLHD(1);
            JOptionPane.showMessageDialog(this, "Mời nhập mã hóa đơn muốn tìm kiếm!", "LỖI", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int checkTKQLHD = 0;
            int maHoaDon = -1;
            String HoaDon = txtTimKiemQuanLyHoaDon.getText();
            if (HoaDon.equals("")) {
                maHoaDon = -1;
            } else {
                maHoaDon = Integer.parseInt(HoaDon);
            }
            DefaultTableModel QLHDModel = (DefaultTableModel) tblQuanLyHoaDon.getModel();
            QLHDModel.setRowCount(0);
            List<NhanVienHoaDonViewModel> lstDaTim = new ArrayList<>();
            List<NhanVienHoaDonViewModel> listTKQLHD = NVHoaDonSv.getList(ListDSSP, mapTenNV, mapTenBan, listCTHD, maGiamGia);
            for (NhanVienHoaDonViewModel n : listTKQLHD) {
                if (maHoaDon == n.getMaHoaDon()) {
                    lstDaTim.add(n);
                    checkTKQLHD = 1;
                    break;
                }

            }
            if (checkTKQLHD == 0) {
                tblQuanLyHoaDon.removeAll();
                JOptionPane.showMessageDialog(this, "Mã hóa đơn không tồn tại, vui lòng kiểm tra lại!", "LỖI", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (lstDaTim.size() > 100) {
                double a = lstDaTim.size();
                double b = 100;
                soTrangQLHD = (int) Math.ceil(a / b);

            } else {
                soTrangQLHD = 1;
            }
            if (soTrangQLHD > 1) {
                for (int i = 1; i <= soTrangQLHD; i++) {
                    List<NhanVienHoaDonViewModel> listTrang = new ArrayList<>();
                    if (i == soTrangQLHD) {
                        int doDaiTrangCuoi = lstDaTim.size() - (soTrangQLHD - 1) * 100;
                        for (int j = ((soTrangQLHD - 1) * 100); j < (doDaiTrangCuoi + (((soTrangQLHD - 1) * 100))); j++) {
                            listTrang.add(lstDaTim.get(j));
                        }

                    } else {
                        if (i == 1) {
                            for (int k = 0; k < 100; k++) {
                                listTrang.add(lstDaTim.get(k));
                            }
                        } else {
                            for (int h = (i - 1) * 100; h <= ((i - 1) * 100 + 99); h++) {
                                listTrang.add(lstDaTim.get(h));
                            }
                        }
                    }
                    mapPhanTrangQLHD.put(i, listTrang);
                }
                demTrangQLHD = 1;
                truyenTrangQLHD(demTrangQLHD);
                JOptionPane.showMessageDialog(this, "Tìm thành công!");

            } else {
                demTrangQLHD = 1;
                mapPhanTrangQLHD.put(1, lstDaTim);
                truyenTrangQLHD(demTrangQLHD);
                JOptionPane.showMessageDialog(this, "Tìm thành công!");

            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã hóa đơn phải là số tự nhiên và không được chứa khoảng trắng, vui lòng kiểm tra lại!", "LỖI", JOptionPane.WARNING_MESSAGE);
            return;
        }

    }//GEN-LAST:event_btnTimKiemQLHDActionPerformed

    private void btnXemQLHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemQLHDActionPerformed
        // TODO add your handling code here:
        demTrangQLHD = 1;
        phanTrangQLHD();
        truyenTrangQLHD(demTrangQLHD);
    }//GEN-LAST:event_btnXemQLHDActionPerformed
    private void tblVorCherFromMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVorCherFromMouseClicked
        int row = tblVorCherFrom.getSelectedRow();
        String ma = tblVorCherFrom.getValueAt(row, 1).toString();
        String phanTramGiam = tblVorCherFrom.getValueAt(row, 2).toString();
        String hoaDonToiThieu = tblVorCherFrom.getValueAt(row, 3).toString();
        String giamToiDa = tblVorCherFrom.getValueAt(row, 4).toString();
        String soLuong = tblVorCherFrom.getValueAt(row, 5).toString();
        String ngayBatDau = tblVorCherFrom.getValueAt(row, 6).toString();
        String ngayKetThuc = tblVorCherFrom.getValueAt(row, 7).toString();
        String maNguoiTao = tblVorCherFrom.getValueAt(row, 8).toString();
        txtMaVorCher.setText(ma);
        txtPhanTramGiam.setText(phanTramGiam);
        txtHoaDonToiThieu.setText(hoaDonToiThieu);
        txtGiamToiDa.setText(giamToiDa);
        txtSoLuong.setText(soLuong);
        txtNgayBatDau.setText(ngayBatDau);
        txtNgayKetThuc.setText(ngayKetThuc);
        txtMaNguoiTao.setText(maNguoiTao);
    }//GEN-LAST:event_tblVorCherFromMouseClicked

    private void btnCleanMaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanMaGiamGiaActionPerformed
        cleanMaGiamGia();
    }//GEN-LAST:event_btnCleanMaGiamGiaActionPerformed

    private void btnThuHoiMaVouCherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuHoiMaVouCherActionPerformed

        int row = tblVorCherFrom.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một voucher để thu hồi.");
            return;
        }

        MaGiamGiaViewModel maGiamGiaViewModel = new MaGiamGiaViewModel();
        maGiamGiaViewModel.setSoLuong(0);
        String maVouCher = tblVorCherFrom.getValueAt(row, 1).toString();
        int maVouCherInt = Integer.parseInt(maVouCher);
        JOptionPane.showMessageDialog(this, iMaGiamGiaService.updateMaGiamGiaSoLuong(maVouCherInt, maGiamGiaViewModel));
        loadTableVorCher(iMaGiamGiaService.getListMaGiamGia());

    }//GEN-LAST:event_btnThuHoiMaVouCherActionPerformed

    private void lblQLHDDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQLHDDauMouseClicked
        // TODO add your handling code here:
        demTrangQLHD = 1;
        truyenTrangQLHD(demTrangQLHD);
    }//GEN-LAST:event_lblQLHDDauMouseClicked

    private void lblQLHDLuiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQLHDLuiMouseClicked
        // TODO add your handling code here:
        if (demTrangQLHD > 1) {
            demTrangQLHD--;
            truyenTrangQLHD(demTrangQLHD);
        } else {
            JOptionPane.showMessageDialog(this, "Đã hiện thị trang đầu tiên, không thể lùi nữa!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }//GEN-LAST:event_lblQLHDLuiMouseClicked

    private void lblQLHDTienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQLHDTienMouseClicked
        // TODO add your handling code here:
        if (demTrangQLHD < soTrangQLHD) {
            demTrangQLHD++;
            truyenTrangQLHD(demTrangQLHD);
        } else {
            JOptionPane.showMessageDialog(this, "Đã hiện thị trang cuối cùng, không thể tiến nữa!", "CẢNH BÁO", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }//GEN-LAST:event_lblQLHDTienMouseClicked

    private void lblQLHDCuoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQLHDCuoiMouseClicked
        // TODO add your handling code here:
        demTrangQLHD = soTrangQLHD;
        truyenTrangQLHD(soTrangQLHD);
    }//GEN-LAST:event_lblQLHDCuoiMouseClicked

    private void txtTimKiemMaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemMaGiamGiaActionPerformed

    }//GEN-LAST:event_txtTimKiemMaGiamGiaActionPerformed

    private void txtTimKiemMaGiamGiaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemMaGiamGiaFocusGained

    }//GEN-LAST:event_txtTimKiemMaGiamGiaFocusGained

    private void txtTimKiemMaGiamGiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaGiamGiaKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String searchHoaDonToiThieu = txtTimKiemMaGiamGia.getText().trim();

            if (searchHoaDonToiThieu.isEmpty()) {
                loadTableVorCher(iMaGiamGiaService.getListMaGiamGia());
            } else {
                try {
                    int searchHoaDonToiThieuInt = Integer.parseInt(searchHoaDonToiThieu);
                    ArrayList<MaGiamGiaViewModel> listMaGiamGia = iMaGiamGiaService.getFindHoaDonToiThieu(searchHoaDonToiThieuInt);
                    loadTableVorCher(listMaGiamGia);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên hợp lệ.");
                    txtTimKiemMaGiamGia.setText("");
                }
            }
        }
    }//GEN-LAST:event_txtTimKiemMaGiamGiaKeyPressed

    private void txtTimKiemMaGiamGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemMaGiamGiaKeyReleased

    }//GEN-LAST:event_txtTimKiemMaGiamGiaKeyReleased

    private void txtCCCDXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCCCDXemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCCCDXemActionPerformed

    private void txtTimKiemSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamMouseClicked

    }//GEN-LAST:event_txtTimKiemSanPhamMouseClicked

    private void jpnSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnSanPhamMouseClicked

    }//GEN-LAST:event_jpnSanPhamMouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked

    }//GEN-LAST:event_jPanel2MouseClicked

    private void txtTimKiemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemSanPhamActionPerformed

    private void txtTimKiemSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemSanPhamMouseEntered

    }//GEN-LAST:event_txtTimKiemSanPhamMouseEntered

    private void txtGiaSizeLXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaSizeLXemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaSizeLXemActionPerformed

    private void txtHoaDonToiThieuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoaDonToiThieuKeyReleased

    }//GEN-LAST:event_txtHoaDonToiThieuKeyReleased

    private void txtGiamToiDaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamToiDaKeyReleased

    }//GEN-LAST:event_txtGiamToiDaKeyReleased

    private void txtNgayBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayBatDauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayBatDauActionPerformed

    private void cbbMaNhanVienTaiKhoanSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaNhanVienTaiKhoanSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbMaNhanVienTaiKhoanSuaActionPerformed
    private void loadChuMoTapTrungTimKiemChoMaGiamGia() {
        // Đặt placeholder ban đầu cho thanh tìm kiếm
        txtTimKiemMaGiamGia.setText("Nhập hoá đơn tối thiểu...");

        // Thêm sự kiện focusGained
        txtTimKiemMaGiamGia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                // Kiểm tra nếu là placeholder hoặc rỗng thì xóa và đặt lại giá trị thành rỗng
                if (txtTimKiemMaGiamGia.getText().equalsIgnoreCase("Nhập hoá đơn tối thiểu...") || txtTimKiemMaGiamGia.getText().trim().isEmpty()) {
                    txtTimKiemMaGiamGia.setText("");
                }
            }
        });

        // Thêm sự kiện focusLost
        txtTimKiemMaGiamGia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                // Kiểm tra nếu là rỗng thì đặt lại giá trị thành placeholder
                if (txtTimKiemMaGiamGia.getText().trim().isEmpty()) {
                    txtTimKiemMaGiamGia.setText("Nhập hoá đơn tối thiểu...");
                }
            }
        });
    }

    public void fillMaBan(int index) {
        lblBanCapNhatMaBan.setText(listBanviewmodel.get(index).getMaBan() + "");
        txtBanCapNhatTenBan.setText(listBanviewmodel.get(index).getTenBan());
        txtBanCapNhatTenBan.setText(listBanviewmodel.get(index).getTenBan());

        if (Integer.parseInt(listBanviewmodel.get(index).getTang() + "") == 0) {
            cboBanCapNhatTang.setSelectedItem("Mang về");

        } else {
            cboBanCapNhatTang.setSelectedItem(listBanviewmodel.get(index).getTang() + "");
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String maTaiKhoan = ""; // Lấy mã tài khoản từ giao diện đăng nhập
                TraSua_QL traSua_QL = new TraSua_QL(maTaiKhoan);
                traSua_QL.setMaTaiKhoan(maTaiKhoan);
                traSua_QL.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnhNhanVien;
    private javax.swing.JButton btnAnhNhanVienSua;
    private javax.swing.JButton btnBanCapNhat;
    private javax.swing.JButton btnBanCapNhatClear;
    private javax.swing.JButton btnBanThem;
    private javax.swing.JButton btnBanThemClear;
    private javax.swing.JButton btnCapNhatNhanVien;
    private javax.swing.JButton btnCapNhatSanPham;
    private javax.swing.JButton btnCapNhatTaiKhoanNhanVien;
    private javax.swing.JButton btnChonAnhSanPhamThem;
    private javax.swing.JButton btnChonAnhSanPhamXem;
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnCleanMaGiamGia;
    private javax.swing.JButton btnCleanTaiKhoan;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnKhieuNaiHoTro;
    private javax.swing.JButton btnTaoMoi;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnThemTaiKhoan;
    private javax.swing.JButton btnThuHoiMaVouCher;
    private javax.swing.JButton btnTimKiemQLHD;
    private javax.swing.JButton btnXemQLHD;
    private javax.swing.JComboBox<String> cbbChucVuNhanVienThem;
    private javax.swing.JComboBox<String> cbbChucVuNhanVienXem;
    private javax.swing.JComboBox<String> cbbMaNhanVienTaiKhoanSua;
    private javax.swing.JComboBox<String> cbbMaNhanVienTaiKhoanThem;
    private javax.swing.JComboBox<String> cbbTimKiemTrangThai;
    private javax.swing.JComboBox<String> cbbTrangThaiNhanVienThem;
    private javax.swing.JComboBox<String> cbbTrangThaiNhanVienXem;
    private javax.swing.JComboBox<String> cbbTrangThaiTaiKhoanSua;
    private javax.swing.JComboBox<String> cbbTrangThaiTaiKhoanThem;
    private javax.swing.JComboBox<String> cbbTrangThaiVaiTroThem;
    private javax.swing.JComboBox<String> cbbVaiTroTaiKhoanSua;
    private javax.swing.JComboBox<String> cboBanCapNhatTang;
    private javax.swing.JComboBox<String> cboBanThemTang;
    private javax.swing.JComboBox<String> cboTimBanTheoTang;
    private javax.swing.JComboBox<String> cboTrangThaiSanPhamThem;
    private javax.swing.JComboBox<String> cboTrangThaiSanPhamXem;
    private javax.swing.JCheckBox chkSizeLThem;
    private javax.swing.JCheckBox chkSizeLXem;
    private javax.swing.JCheckBox chkSizeMThem;
    private javax.swing.JCheckBox chkSizeMXem;
    private javax.swing.JCheckBox chkSizeSThem;
    private javax.swing.JCheckBox chkSizeSXem;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private com.toedter.calendar.JDateChooser jdcTimTheoNgay;
    private javax.swing.JPanel jpnBackupHeThong;
    private javax.swing.JPanel jpnHoaDon;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnNenBackupHeThong;
    private javax.swing.JPanel jpnNenBan;
    private javax.swing.JPanel jpnNenDoiMatKhau;
    private javax.swing.JPanel jpnNenHoaDon;
    private javax.swing.JPanel jpnNenNhanVien;
    private javax.swing.JPanel jpnNenSanPham;
    private javax.swing.JPanel jpnNenTaiKhoan;
    private javax.swing.JPanel jpnNenVoucher;
    private javax.swing.JPanel jpnNhanVien;
    private javax.swing.JPanel jpnQLBan;
    private javax.swing.JPanel jpnSanPham;
    private javax.swing.JPanel jpnTaiKhoan;
    private javax.swing.JPanel jpnTong;
    private javax.swing.JPanel jpnVoucher;
    private javax.swing.JLabel lblAnhNhanVien;
    private javax.swing.JLabel lblAnhNhanVienSua;
    private javax.swing.JLabel lblAnhSanPhamThem;
    private javax.swing.JLabel lblAnhSanPhamXem;
    private javax.swing.JLabel lblBackupHeThong;
    private javax.swing.JLabel lblBanCapNhatMaBan;
    private javax.swing.JLabel lblDoiMatKhau;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblQLHDCuoi;
    private javax.swing.JLabel lblQLHDDau;
    private javax.swing.JLabel lblQLHDLui;
    private javax.swing.JLabel lblQLHDTien;
    private javax.swing.JLabel lblQuanLyBan;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblThietLap;
    private javax.swing.JLabel lblTimKiemTheoNgay;
    private javax.swing.JLabel lblTraSua;
    private javax.swing.JLabel lblTrangQLHD;
    private javax.swing.JLabel lblVoucher;
    private javax.swing.JLabel lblquanly;
    private javax.swing.JTable tblBackUp;
    private javax.swing.JTable tblBanBan;
    private javax.swing.JTable tblNhanVienForm;
    private javax.swing.JTable tblQLHDDSSanPham;
    private javax.swing.JTable tblQuanLyHoaDon;
    private javax.swing.JTable tblQuanLySanPham;
    private javax.swing.JTable tblTaiKhoanForm;
    private javax.swing.JTable tblVorCherFrom;
    private javax.swing.JTextField txtBanCapNhatTenBan;
    private javax.swing.JTextField txtBanThemTenBan;
    private javax.swing.JTextField txtCCCDThem;
    private javax.swing.JTextField txtCCCDXem;
    private javax.swing.JTextField txtDiaChiThem;
    private javax.swing.JTextField txtDiaChiXem;
    private javax.swing.JTextField txtEmailThem;
    private javax.swing.JTextField txtEmailXem;
    private javax.swing.JTextArea txtGhiChuThem;
    private javax.swing.JTextArea txtGhiChuXem;
    private javax.swing.JTextField txtGiaSizeLThem;
    private javax.swing.JTextField txtGiaSizeLXem;
    private javax.swing.JTextField txtGiaSizeMThem;
    private javax.swing.JTextField txtGiaSizeMXem;
    private javax.swing.JTextField txtGiaSizeSThem;
    private javax.swing.JTextField txtGiaSizeSXem;
    private javax.swing.JTextField txtGiamToiDa;
    private javax.swing.JTextField txtHoVaTenThem;
    private javax.swing.JTextField txtHoVaTenXem;
    private javax.swing.JTextField txtHoaDonToiThieu;
    private javax.swing.JTextField txtMaNguoiTao;
    private javax.swing.JTextField txtMaNhanVienThem;
    private javax.swing.JTextField txtMaNhanVienXem;
    private javax.swing.JTextField txtMaSanPhamThem;
    private javax.swing.JTextField txtMaSanPhamXem;
    private javax.swing.JTextField txtMaTaiKhoanSua;
    private javax.swing.JTextField txtMaTaiKhoanThem;
    private javax.swing.JTextField txtMaVorCher;
    private javax.swing.JTextField txtMatKhauTaiKhoanSua;
    private javax.swing.JTextField txtMatKhauThem;
    private javax.swing.JTextArea txtMoTaSanPhamThem;
    private javax.swing.JTextArea txtMoTaSanPhamXem;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private javax.swing.JTextField txtNgaySinhThem;
    private javax.swing.JTextField txtNgaySinhXem;
    private javax.swing.JTextField txtPhanTramGiam;
    private javax.swing.JTextField txtQLHDBan;
    private javax.swing.JTextField txtQLHDDichVuPhatSinh;
    private javax.swing.JTextArea txtQLHDGhiChu;
    private javax.swing.JTextField txtQLHDMaGiamGia;
    private javax.swing.JTextField txtQLHDMaHoaDon;
    private javax.swing.JTextField txtQLHDMaNhanVien;
    private javax.swing.JTextField txtQLHDTang;
    private javax.swing.JTextField txtQLHDThoiGian;
    private javax.swing.JTextField txtQLHDTongHoaDon;
    private javax.swing.JTextField txtQLHDTrangThaiThanhToan;
    private javax.swing.JTextField txtSDTThem;
    private javax.swing.JTextField txtSDTXem;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPhamThem;
    private javax.swing.JTextField txtTenSanPhamXem;
    private javax.swing.JTextField txtTimKiemMaGiamGia;
    private javax.swing.JTextField txtTimKiemQuanLyHoaDon;
    private javax.swing.JTextField txtTimKiemSanPham;
    private javax.swing.JTextField txtTimKiemTaiKhoan;
    private javax.swing.JTextField txtTimKiemTen;
    private javax.swing.JTextField txtTongThanhToanQLHD;
    // End of variables declaration//GEN-END:variables

    private void loadToTableBackUp() {
        DefaultTableModel model = (DefaultTableModel) tblBackUp.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("File Name");
        model.addColumn("Time");
        SimpleDateFormat spd = new SimpleDateFormat("yyyy_M_d____hh_mm_ss");
        SimpleDateFormat spd2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        List<String> lstRow = new ArrayList<>();
        lstRow = DBackUpAndRestore.getListFileBackUp();
        Collections.sort(lstRow, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -o1.compareTo(o2);
            }

        });
        for (String file : lstRow) {
            try {
                String time = file.substring(0, file.indexOf("."));
                model.addRow(new Object[]{file, spd2.format(spd.parse(time))});

            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }
}
