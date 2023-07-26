/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainmodel.NhanVienDomainModel;
import domainmodel.Role;
import interfaceservices.IBanService;
import java.sql.Blob; // Thêm dòng này vào đầu tệp Java
import interfaceservices.INhanVienService;
import interfaceservices.IQuanLyBanServices;
import interfaceservices.ITaiKhoanServicess;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTML;
import repositorys.NhanVienRepository;
import repositorys.iRepository.INhanVienRepository;
import services.BanService;
import services.QuanLyBanServices;
import utilities.DBackUpAndRestore;
import utilities.Uhelper;
import viewmodel.QuanLyBanViewmodel;

public class TraSua_QL extends javax.swing.JFrame {

    List<QuanLyBanViewmodel> listBanviewmodel = new ArrayList<>();
    DefaultTableModel BanBanModel = new DefaultTableModel();
    public IQuanLyBanServices ibanServices = new QuanLyBanServices();
    public ITaiKhoanServicess iTaiKhoanServicess = new TaiKhoanServicess();
    public INhanVienService iNhanVienService = new NhanVienService();
    private String maTaiKhoan;

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;

    }

    public TraSua_QL(String maTaiKhoan) {

        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        init();
        lblMaSanPham2.setEnabled(false);
        loadComBoBoxTaiKhoanMaNhanVien(iNhanVienService.getAll());
        loadTableTaiKhoan(iTaiKhoanServicess.getAll());
        loadTableNhanVien(iNhanVienService.getAll());
        BanBanModel = (DefaultTableModel) tblBanBan.getModel();
        fillBanTableBan();
        fillMaBan(0);
    }

    public void loadComBoBoxTaiKhoanMaNhanVien(List<NhanVienViewModel> listNhanVienViewModels) {
        cbbMaNhanVienTaiKhoanThem.removeAllItems(); // Xóa tất cả các item cũ trong ComboBox
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
            BanBanModel.addRow(new Object[]{
                a.getMaBan(), a.getTenBan(), a.getTang()
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

        if (!checkMaTaiKhoan(maTaiKhoan1)) {
            return null;
        }

        if (!checkMaNhanVien(maNhanVienInt)) {
            return null;
        }

        if (matKhau.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được có dấu cách");
            return null;
        } else if (maTaiKhoan1.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Mã tài khoản không được có dấu cách");
            return null;
        }

        if (trangThai.equals("Đã Khoá")) {
            taiKhoanViewModel.setTrangThai(0);
        } else {
            taiKhoanViewModel.setTrangThai(1);
        }

        taiKhoanViewModel.setMaTaiKhoan(maTaiKhoan1);
        taiKhoanViewModel.setMatKhau(matKhau);
        taiKhoanViewModel.setMaNhanVien(maNhanVienInt);
        taiKhoanViewModel.setRole(role);
        return taiKhoanViewModel;
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

    public boolean checkMaTaiKhoan(String maTaiKhoan) {
        if (maTaiKhoan.startsWith(" ") || maTaiKhoan.endsWith(" ") || maTaiKhoan.contains("  ")) {
            JOptionPane.showMessageDialog(this, "Mã tài khoản không được chứa khoảng trắng ở đầu, giữa hoặc cuối.");
            return false;
        }
        Set<String> existingMaTaiKhoans = new HashSet<>();
        List<TaiKhoanViewModel> existingTaiKhoans = iTaiKhoanServicess.getAll();
        for (TaiKhoanViewModel tk : existingTaiKhoans) {
            existingMaTaiKhoans.add(tk.getMaTaiKhoan());
        }
        if (existingMaTaiKhoans.contains(maTaiKhoan)) {
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
        if (hoVaTen.trim().equals("") || ngaySinh.trim().equals("") || diaChi.trim().equals("")||cccd.trim().equals("") || email.trim().equals("") || soDienThoai.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được rỗng");
            return null;
        }

        
        // Kiểm tra dấu cách ở đầu và cuối tên, CCCD và địa chỉ
        if (hoVaTen.startsWith(" ")) {
            JOptionPane.showMessageDialog(this, "Tên không được chứa dấu cách ở đầu");
            return null;
        } else if (cccd.startsWith(" ")) {
            JOptionPane.showMessageDialog(this, "CCCD không được chứa dấu cách ở đầu");
            return null;
        } else if (diaChi.startsWith(" ")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa dấu cách ở đầu");
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
        nhanVienViewModel.setDiaChi(diaChi);
        if (!cccd.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "CCCD phải là dạng số.");
            return null;
        } else if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Định dạng email không hợp lệ.");
            return null;
        } else if (isSoDienThoaiExists(soDienThoai)) {
            return null;
        } else if (isCCCDExists(cccd, false, nhanVienCu)) {
            JOptionPane.showMessageDialog(this, "CCCD đã tồn tại. Vui lòng kiểm tra lại.");
            return null;
        }
        nhanVienViewModel.setCCCD(cccd);
        nhanVienViewModel.setEmail(email);
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

    public boolean isCCCDExists(String cccd, boolean isUpdating, String cccdCu) {
        // ...
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

    public boolean isSoDienThoaiExists(String soDienThoai) {
        if (soDienThoai.isEmpty() || !soDienThoai.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ.");
            return true;
        }else if(){
            
        }

// Kiểm tra độ dài của số điện thoại là 10 hoặc 11 số
        if (soDienThoai.length() != 10 && soDienThoai.length() != 11) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 hoặc 11 số.");
            return true;
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        // Kiểm tra email không được viết hoa
        if (email.matches(".*[A-Z].*")) {
            return false;
        }

        String lowercaseEmail = email.toLowerCase();
        String regex = "^[a-z0-9._%+-]+(\\.[a-z0-9._%+-]+)*@[a-z0-9.-]+\\.[a-z]{2,}$";
        boolean hasConsecutiveDots = lowercaseEmail.contains("..");
        return lowercaseEmail.matches(regex) && !hasConsecutiveDots;
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
        // check rỗng 
        if (hoVaTen.trim().equals(" ") || ngaySinh.trim().equals(" ") || cccd.trim().equals(" ") || email.trim().equals(" ") || soDienThoai.trim().equals(" ") || diaChi.trim().equals(" ")) {
            JOptionPane.showMessageDialog(this, "Không được rỗng");
            return null;
        }
        if (!cccd.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "CCCD phải là dạng số.");
            return null;
        }
        // Kiểm tra dấu cách ở đầu và cuối tên, CCCD và địa chỉ
        if (hoVaTen.startsWith(" ")) {
            JOptionPane.showMessageDialog(this, "Tên không được chứa dấu cách ở đầu");
            return null;
        }

        if (cccd.startsWith(" ")) {
            JOptionPane.showMessageDialog(this, "CCCD không được chứa dấu cách ở đầu");
            return null;
        }

        if (diaChi.startsWith(" ")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa dấu cách ở đầu");
            return null;
        }
        // Lấy trạng thái từ ComboBox
        String trangThai = cbbTrangThaiNhanVienXem.getSelectedItem().toString();
        if (trangThai.equals("Đã nghỉ việc")) {
            nhanVienViewModel.setTrangThai(0);
        } else {
            nhanVienViewModel.setTrangThai(1);
        }

        Set<String> existingEmails = new HashSet<>();
        List<NhanVienViewModel> existingNhanViens = iNhanVienService.getAll();
        for (NhanVienViewModel nv : existingNhanViens) {
            existingEmails.add(nv.getEmail());
        }
        NhanVienViewModel nhanVienCu = iNhanVienService.getNhanVienById(maNhanVienInt);
        String cccdCu = nhanVienCu.getCCCD();

        if (!email.equals(nhanVienCu.getEmail()) && existingEmails.contains(email)) {
            JOptionPane.showMessageDialog(this, "Email không được trùng.");
            return null;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Định dạng email không hợp lệ.");
            return null;
        }
        if (isCCCDExists(cccd, true, cccdCu)) {
            return null;
        }

        try {
            LocalDate localDate = LocalDate.parse(ngaySinh);
            nhanVienViewModel.setNgaySinh(java.sql.Date.valueOf(localDate));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chuẩn định dạng ngày sinh");
            return null;
        }

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
        if (isSoDienThoaiExists(soDienThoai)) {
            return null;
        }
        nhanVienViewModel.setEmail(email);
        nhanVienViewModel.setCCCD(cccd);
        nhanVienViewModel.setChucVu(chucVu);
        nhanVienViewModel.setSoDienThoai(soDienThoai);
        nhanVienViewModel.setHoVaTen(hoVaTen);
        nhanVienViewModel.setDiaChi(diaChi);
        nhanVienViewModel.setGhiChu(ghiChu);
        return nhanVienViewModel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnMenu = new javax.swing.JPanel();
        lblTraSua = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        lblSanPham = new javax.swing.JLabel();
        lblQuanLyBan = new javax.swing.JLabel();
        lblHoaDon = new javax.swing.JLabel();
        lblVoucher = new javax.swing.JLabel();
        lblquanly = new javax.swing.JLabel();
        lblBackupHeThong = new javax.swing.JLabel();
        lblDoiMatKhau = new javax.swing.JLabel();
        btnKhieuNaiHoTro = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblTaiKhoan = new javax.swing.JLabel();
        lblTaiKhoan2 = new javax.swing.JLabel();
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
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox4 = new javax.swing.JCheckBox();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        lblMaSanPham2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jCheckBox6 = new javax.swing.JCheckBox();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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
        jTable2 = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblMaSanPham3 = new javax.swing.JTextField();
        lblMaSanPham4 = new javax.swing.JTextField();
        lblMaSanPham5 = new javax.swing.JTextField();
        lblMaSanPham6 = new javax.swing.JTextField();
        lblMaSanPham7 = new javax.swing.JTextField();
        lblMaSanPham8 = new javax.swing.JTextField();
        lblMaSanPham9 = new javax.swing.JTextField();
        lblMaSanPham10 = new javax.swing.JTextField();
        lblMaSanPham11 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jpnVoucher = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        lblMaSanPham30 = new javax.swing.JTextField();
        lblMaSanPham31 = new javax.swing.JTextField();
        lblMaSanPham32 = new javax.swing.JTextField();
        lblMaSanPham33 = new javax.swing.JTextField();
        lblMaSanPham34 = new javax.swing.JTextField();
        lblMaSanPham35 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        lblMaSanPham40 = new javax.swing.JTextField();
        lblMaSanPham41 = new javax.swing.JTextField();
        lblMaSanPham42 = new javax.swing.JTextField();
        lblMaSanPham43 = new javax.swing.JTextField();
        lblMaSanPham44 = new javax.swing.JTextField();
        lblMaSanPham45 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        lblMaSanPham36 = new javax.swing.JTextField();
        lblMaSanPham37 = new javax.swing.JTextField();
        lblMaSanPham38 = new javax.swing.JTextField();
        lblMaSanPham39 = new javax.swing.JTextField();
        jComboBox16 = new javax.swing.JComboBox<>();
        jComboBox17 = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextField10 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel58 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
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
        jpnMenu.add(lblTraSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

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
        jpnMenu.add(lblNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 220, -1));

        lblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sanPham.png"))); // NOI18N
        lblSanPham.setText("  SẢN PHẨM");
        lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseClicked(evt);
            }
        });
        jpnMenu.add(lblSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 220, -1));

        lblQuanLyBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuanLyBan.setForeground(new java.awt.Color(255, 255, 255));
        lblQuanLyBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ban2.png"))); // NOI18N
        lblQuanLyBan.setText("  QUẢN LÝ BÀN");
        lblQuanLyBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuanLyBanMouseClicked(evt);
            }
        });
        jpnMenu.add(lblQuanLyBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 220, -1));

        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/hoaDon.png"))); // NOI18N
        lblHoaDon.setText("  HÓA ĐƠN");
        lblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseClicked(evt);
            }
        });
        jpnMenu.add(lblHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 220, -1));

        lblVoucher.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblVoucher.setForeground(new java.awt.Color(255, 255, 255));
        lblVoucher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/voucher.png"))); // NOI18N
        lblVoucher.setText("  VOUCHER");
        lblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVoucherMouseClicked(evt);
            }
        });
        jpnMenu.add(lblVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 220, -1));

        lblquanly.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblquanly.setForeground(new java.awt.Color(255, 255, 255));
        lblquanly.setText("QUẢN LÝ");
        lblquanly.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblquanlyMouseClicked(evt);
            }
        });
        jpnMenu.add(lblquanly, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        lblBackupHeThong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBackupHeThong.setForeground(new java.awt.Color(255, 255, 255));
        lblBackupHeThong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/backup.png"))); // NOI18N
        lblBackupHeThong.setText("BACKUP HỆ THỐNG");
        lblBackupHeThong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackupHeThongMouseClicked(evt);
            }
        });
        jpnMenu.add(lblBackupHeThong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 220, -1));

        lblDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/doiMatKhau.png"))); // NOI18N
        lblDoiMatKhau.setText("  ĐỔI MẬT KHẨU");
        lblDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoiMatKhauMouseClicked(evt);
            }
        });
        jpnMenu.add(lblDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 220, -1));

        btnKhieuNaiHoTro.setBackground(new java.awt.Color(2, 82, 155));
        btnKhieuNaiHoTro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKhieuNaiHoTro.setForeground(new java.awt.Color(255, 255, 255));
        btnKhieuNaiHoTro.setText("KHIẾU NẠI HỖ TRỢ ?");
        btnKhieuNaiHoTro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKhieuNaiHoTroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKhieuNaiHoTroMouseEntered(evt);
            }
        });
        btnKhieuNaiHoTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhieuNaiHoTroActionPerformed(evt);
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
        jpnMenu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 220, 10));
        jpnMenu.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 220, 10));

        lblTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/taiKhoan.png"))); // NOI18N
        lblTaiKhoan.setText("  TÀI KHOẢN");
        lblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTaiKhoanMouseClicked(evt);
            }
        });
        jpnMenu.add(lblTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 220, -1));

        lblTaiKhoan2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTaiKhoan2.setForeground(new java.awt.Color(255, 255, 255));
        lblTaiKhoan2.setText("THIẾT LẬP");
        lblTaiKhoan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTaiKhoan2MouseClicked(evt);
            }
        });
        jpnMenu.add(lblTaiKhoan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, -1, -1));

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
        btnClean.setText("Clean");
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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Quản Lý Sản Phẩm");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("Hình ảnh");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setBackground(new java.awt.Color(45, 132, 252));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Chọn ảnh");

        jLabel11.setText("Mã sản phẩm");

        jLabel12.setText("Tên sản phẩm");

        jLabel13.setText("Trạng thái");

        jLabel14.setText("Size");

        jLabel15.setText("Giá size S");

        jLabel16.setText("Giá size M");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel17.setText("Mô tả");

        jButton2.setBackground(new java.awt.Color(45, 132, 252));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cập nhật");

        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCheckBox1.setText("S");

        jCheckBox2.setText("M");

        jCheckBox3.setText("L");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField1))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField2))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(32, 32, 32)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField3)
                                .addComponent(jTextField4)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jCheckBox1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jCheckBox2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jCheckBox3)
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel14)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox2)
                            .addComponent(jCheckBox3))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin sản phẩm", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jCheckBox4.setText("M");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setText("Giá size S");

        jLabel19.setText("Giá size M");

        jLabel20.setText("Mô tả");

        jLabel21.setText("Tên sản phẩm");

        jTextField6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jLabel22.setText("Trạng thái");

        jLabel23.setText("Mã sản phẩm");

        jCheckBox5.setText("S");

        jButton3.setBackground(new java.awt.Color(45, 132, 252));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Cập nhật");

        jButton4.setBackground(new java.awt.Color(45, 132, 252));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Chọn ảnh");

        jLabel24.setText("Hình ảnh");
        jLabel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jLabel25.setText("Size");

        jTextField9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jCheckBox6.setText("L");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addGap(18, 18, 18)
                            .addComponent(lblMaSanPham2))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField9))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(32, 32, 32)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField8)
                                .addComponent(jTextField6)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jCheckBox5)
                                    .addGap(18, 18, 18)
                                    .addComponent(jCheckBox4)
                                    .addGap(18, 18, 18)
                                    .addComponent(jCheckBox6)
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblMaSanPham2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel25)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox5)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox6))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thêm sản phẩm", jPanel3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Size", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jpnSanPhamLayout = new javax.swing.GroupLayout(jpnSanPham);
        jpnSanPham.setLayout(jpnSanPhamLayout);
        jpnSanPhamLayout.setHorizontalGroup(
            jpnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSanPhamLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jpnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jpnSanPhamLayout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jpnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField5)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE))))
                .addContainerGap(687, Short.MAX_VALUE))
        );
        jpnSanPhamLayout.setVerticalGroup(
            jpnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSanPhamLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addGroup(jpnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnSanPhamLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnSanPhamLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)))
                .addContainerGap(326, Short.MAX_VALUE))
        );

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

        cboBanCapNhatTang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        btnBanCapNhatClear.setBackground(new java.awt.Color(45, 132, 252));
        btnBanCapNhatClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBanCapNhatClear.setForeground(new java.awt.Color(255, 255, 255));
        btnBanCapNhatClear.setText("Clear");
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

        cboBanThemTang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        btnBanThemClear.setBackground(new java.awt.Color(45, 132, 252));
        btnBanThemClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBanThemClear.setForeground(new java.awt.Color(255, 255, 255));
        btnBanThemClear.setText("Clear");
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
        tblBanBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBanBanMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblBanBan);

        jLabel103.setText("Tầng ");

        cboTimBanTheoTang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
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
        jLabel9.setText("reset");
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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnQLBanLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnQLBanLayout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
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
                .addContainerGap(662, Short.MAX_VALUE))
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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable2);

        jLabel34.setText("Ghi chú");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane5.setViewportView(jTextArea3);

        jLabel35.setText("Tổng hóa đơn");

        jLabel36.setText("Tổng thanh toán(*)");

        lblMaSanPham3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaSanPham3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaSanPham4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaSanPham5, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaSanPham6, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaSanPham7, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addComponent(lblMaSanPham8, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaSanPham9, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaSanPham10, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaSanPham11, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(lblMaSanPham3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(lblMaSanPham4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(lblMaSanPham5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(lblMaSanPham6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(lblMaSanPham7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(lblMaSanPham8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(lblMaSanPham9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lblMaSanPham10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(lblMaSanPham11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jTable3);

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/double_left_30px.png"))); // NOI18N

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/chevron_left_30px.png"))); // NOI18N

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/chevron_right_30px.png"))); // NOI18N

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/double_right_30px.png"))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel41.setText("*Số tiền cần thanh toán sau khi áp dụng mã voucher");

        jLabel42.setText("Thời gian");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
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
                        .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpnHoaDonLayout.createSequentialGroup()
                                        .addComponent(jLabel42)
                                        .addGap(50, 50, 50)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField7)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
                                        .addGroup(jpnHoaDonLayout.createSequentialGroup()
                                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(32, 32, 32)
                                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(33, 33, 33)
                                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(33, 33, 33)
                                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                                .addGap(438, 438, 438)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(679, Short.MAX_VALUE))
        );
        jpnHoaDonLayout.setVerticalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnHoaDonLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(jpnHoaDonLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnHoaDonLayout.createSequentialGroup()
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel41)
                        .addGap(7, 7, 7)
                        .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(318, Short.MAX_VALUE))
        );

        jpnTong.add(jpnHoaDon, "card5");

        jpnVoucher.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Quản lý voucher");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setText("Tạo voucher");

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel59.setText("Mã voucher");

        jLabel60.setText("Tỉ lệ voucher(%)");

        jLabel61.setText("Hóa đơn tối thi:");

        jLabel62.setText("Giảm tối đa");

        jLabel63.setText("Ngày bắt đầu");

        jLabel64.setText("Ngày kết thúc");

        jButton10.setBackground(new java.awt.Color(45, 132, 252));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Tạo mới");

        lblMaSanPham30.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham31.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham32.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham33.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham34.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham35.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(lblMaSanPham35, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel59)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSanPham30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaSanPham31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaSanPham32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaSanPham33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaSanPham34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton10)))
                .addGap(16, 16, 16))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(lblMaSanPham30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(lblMaSanPham31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(lblMaSanPham32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(lblMaSanPham33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(lblMaSanPham34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(lblMaSanPham35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Tạo voucher", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel44.setText("Mã voucher");

        jLabel45.setText("Tỉ lệ voucher(%)");

        jLabel46.setText("Hóa đơn tối thi:");

        jLabel47.setText("Giảm tối đa");

        jLabel48.setText("Ngày bắt đầu");

        jLabel49.setText("Ngày kết thúc");

        jButton9.setBackground(new java.awt.Color(45, 132, 252));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Tạo mới");

        lblMaSanPham40.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham41.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham42.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham43.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham44.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham45.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel44)
                                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMaSanPham45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblMaSanPham40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaSanPham41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaSanPham42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaSanPham43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaSanPham44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton9)))
                .addGap(16, 16, 16))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel45)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel46)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel47)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel48)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel49))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lblMaSanPham40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblMaSanPham41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblMaSanPham42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblMaSanPham43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblMaSanPham44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblMaSanPham45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Tạo voucher hàng loạt", jPanel8);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel50.setText("Quản lý mã voucher");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel52.setText("Thời gian tạo");

        jLabel53.setText("Hóa đơn tối thiểu");

        jLabel54.setText("Giảm tối đa");

        jLabel55.setText("Trạng thái");

        jLabel56.setText("Ngày bắt đầu");

        jLabel57.setText("Ngày kết thúc");

        lblMaSanPham36.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham37.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham38.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblMaSanPham39.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel52)
                        .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMaSanPham36, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(lblMaSanPham37, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(lblMaSanPham38, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(lblMaSanPham39, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(jComboBox16, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox17, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(lblMaSanPham36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(lblMaSanPham37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(lblMaSanPham38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(lblMaSanPham39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jLabel51.setText("*Lưu ý cập nhật chỉ áp dụng với các mã voucher chưa được sử dụng");

        jButton5.setBackground(new java.awt.Color(45, 132, 252));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("In tất cả");

        jButton6.setBackground(new java.awt.Color(45, 132, 252));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Xuất file excel");

        jButton7.setBackground(new java.awt.Color(45, 132, 252));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Xóa tất cả");

        jButton8.setBackground(new java.awt.Color(45, 132, 252));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Cập nhật");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addGap(18, 18, 18)
                                .addComponent(jButton6)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8))
                            .addComponent(jLabel50)
                            .addComponent(jLabel51))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel51)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã voucher", "Tỉ lệ giảm", "Hóa đơn tối thiểu", "Giảm tối đa", "Ngày bắt đầu", "Ngày kết thúc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable4);

        jLabel58.setText("Thời gian tạo");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnVoucherLayout = new javax.swing.GroupLayout(jpnVoucher);
        jpnVoucher.setLayout(jpnVoucherLayout);
        jpnVoucherLayout.setHorizontalGroup(
            jpnVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnVoucherLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jpnVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jpnVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnVoucherLayout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addGap(34, 34, 34)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(702, Short.MAX_VALUE))
        );
        jpnVoucherLayout.setVerticalGroup(
            jpnVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnVoucherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel58)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnVoucherLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnVoucherLayout.createSequentialGroup()
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7)))
                .addContainerGap(350, Short.MAX_VALUE))
        );

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
        btnCleanTaiKhoan.setText("Clean");
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
                .addContainerGap(711, Short.MAX_VALUE))
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
                .addGap(31, 31, 31)
                .addComponent(jButton22)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Restore", jPanel16);

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
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(137, 137, 137))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton21)
                .addGap(12, 12, 12))
        );

        jLabel105.setText("Các thông tin backup");

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

        jTabbedPane6.addTab("Backup", jPanel15);

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
                        .addGap(148, 148, 148)
                        .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1293, Short.MAX_VALUE))
        );
        jpnBackupHeThongLayout.setVerticalGroup(
            jpnBackupHeThongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBackupHeThongLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel7)
                .addGap(42, 42, 42)
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(504, Short.MAX_VALUE))
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

    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseClicked

        new DangXuat().setVisible(true);
    }//GEN-LAST:event_btnDangXuatMouseClicked

    private void btnKhieuNaiHoTroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhieuNaiHoTroMouseClicked
        jpnNhanVien.setVisible(false);
        jpnSanPham.setVisible(false);
        jpnQLBan.setVisible(false);
        jpnHoaDon.setVisible(false);
        jpnVoucher.setVisible(false);
        jpnTaiKhoan.setVisible(false);
        jpnBackupHeThong.setVisible(false);
        new HoTroKhachHang().setVisible(true);

    }//GEN-LAST:event_btnKhieuNaiHoTroMouseClicked

    private void lblDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMatKhauMouseClicked
        lblSanPham.setOpaque(false);
        lblSanPham.setBackground(Color.red);
        lblTaiKhoan.setOpaque(false);
        lblTaiKhoan.setBackground(Color.red);
        lblHoaDon.setOpaque(false);
        lblHoaDon.setBackground(Color.red);
        lblVoucher.setOpaque(false);
        lblVoucher.setBackground(Color.red);
        lblNhanVien.setOpaque(false);
        lblNhanVien.setBackground(Color.red);
        lblQuanLyBan.setOpaque(false);
        lblQuanLyBan.setBackground(Color.red);
        lblDoiMatKhau.setOpaque(true);
        lblDoiMatKhau.setBackground(Color.gray);
        lblBackupHeThong.setOpaque(false);
        lblBackupHeThong.setBackground(Color.red);

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
        lblSanPham.setOpaque(false);
        lblSanPham.setBackground(Color.red);
        lblTaiKhoan.setOpaque(false);
        lblTaiKhoan.setBackground(Color.red);
        lblHoaDon.setOpaque(false);
        lblHoaDon.setBackground(Color.red);
        lblVoucher.setOpaque(false);
        lblVoucher.setBackground(Color.red);
        lblNhanVien.setOpaque(false);
        lblNhanVien.setBackground(Color.red);
        lblQuanLyBan.setOpaque(false);
        lblQuanLyBan.setBackground(Color.red);
        lblDoiMatKhau.setOpaque(false);
        lblDoiMatKhau.setBackground(Color.red);
        lblBackupHeThong.setOpaque(true);
        lblBackupHeThong.setBackground(Color.gray);

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
        lblSanPham.setOpaque(false);
        lblSanPham.setBackground(Color.red);
        lblTaiKhoan.setOpaque(false);
        lblTaiKhoan.setBackground(Color.red);
        lblHoaDon.setOpaque(false);
        lblHoaDon.setBackground(Color.red);
        lblVoucher.setOpaque(true);
        lblVoucher.setBackground(Color.gray);
        lblNhanVien.setOpaque(false);
        lblNhanVien.setBackground(Color.red);
        lblQuanLyBan.setOpaque(false);
        lblQuanLyBan.setBackground(Color.red);
        lblDoiMatKhau.setOpaque(false);
        lblDoiMatKhau.setBackground(Color.red);
        lblBackupHeThong.setOpaque(false);
        lblBackupHeThong.setBackground(Color.red);

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
        lblSanPham.setOpaque(false);
        lblSanPham.setBackground(Color.red);
        lblTaiKhoan.setOpaque(false);
        lblTaiKhoan.setBackground(Color.red);
        lblHoaDon.setOpaque(true);
        lblHoaDon.setBackground(Color.gray);
        lblVoucher.setOpaque(false);
        lblVoucher.setBackground(Color.red);
        lblNhanVien.setOpaque(false);
        lblNhanVien.setBackground(Color.red);
        lblQuanLyBan.setOpaque(false);
        lblQuanLyBan.setBackground(Color.red);
        lblDoiMatKhau.setOpaque(false);
        lblDoiMatKhau.setBackground(Color.red);
        lblBackupHeThong.setOpaque(false);
        lblBackupHeThong.setBackground(Color.red);

        jpnSanPham.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnTaiKhoan.setVisible(false);
        jpnHoaDon.setVisible(true);
        jpnVoucher.setVisible(false);
        jpnNhanVien.setVisible(false);
        jpnQLBan.setVisible(false);
        jpnBackupHeThong.setVisible(false);
        new DoiMatKhau(maTaiKhoan).setVisible(false);
    }//GEN-LAST:event_lblHoaDonMouseClicked

    private void lblQuanLyBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLyBanMouseClicked
        lblSanPham.setOpaque(false);
        lblSanPham.setBackground(Color.red);
        lblTaiKhoan.setOpaque(false);
        lblTaiKhoan.setBackground(Color.red);
        lblHoaDon.setOpaque(false);
        lblHoaDon.setBackground(Color.red);
        lblVoucher.setOpaque(false);
        lblVoucher.setBackground(Color.red);
        lblNhanVien.setOpaque(false);
        lblNhanVien.setBackground(Color.red);
        lblQuanLyBan.setOpaque(true);
        lblQuanLyBan.setBackground(Color.gray);
        lblDoiMatKhau.setOpaque(false);
        lblDoiMatKhau.setBackground(Color.red);
        lblBackupHeThong.setOpaque(false);
        lblBackupHeThong.setBackground(Color.red);

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
        lblSanPham.setOpaque(true);
        lblSanPham.setBackground(Color.gray);
        lblTaiKhoan.setOpaque(false);
        lblTaiKhoan.setBackground(Color.red);
        lblHoaDon.setOpaque(false);
        lblHoaDon.setBackground(Color.red);
        lblVoucher.setOpaque(false);
        lblVoucher.setBackground(Color.red);
        lblNhanVien.setOpaque(false);
        lblNhanVien.setBackground(Color.red);
        lblQuanLyBan.setOpaque(false);
        lblQuanLyBan.setBackground(Color.red);
        lblDoiMatKhau.setOpaque(false);
        lblDoiMatKhau.setBackground(Color.red);
        lblBackupHeThong.setOpaque(false);
        lblBackupHeThong.setBackground(Color.red);

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
        lblSanPham.setOpaque(false);
        lblSanPham.setBackground(Color.red);
        lblTaiKhoan.setOpaque(false);
        lblTaiKhoan.setBackground(Color.red);
        lblHoaDon.setOpaque(false);
        lblHoaDon.setBackground(Color.red);
        lblVoucher.setOpaque(false);
        lblVoucher.setBackground(Color.red);
        lblNhanVien.setOpaque(true);
        lblNhanVien.setBackground(Color.gray);
        lblQuanLyBan.setOpaque(false);
        lblQuanLyBan.setBackground(Color.red);
        lblDoiMatKhau.setOpaque(false);
        lblDoiMatKhau.setBackground(Color.red);
        lblBackupHeThong.setOpaque(false);
        lblBackupHeThong.setBackground(Color.red);

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

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed

    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed

    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void lblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoanMouseClicked
        lblSanPham.setOpaque(false);
        lblSanPham.setBackground(Color.red);
        lblTaiKhoan.setOpaque(true);
        lblTaiKhoan.setBackground(Color.gray);
        lblHoaDon.setOpaque(false);
        lblHoaDon.setBackground(Color.red);
        lblVoucher.setOpaque(false);
        lblVoucher.setBackground(Color.red);
        lblNhanVien.setOpaque(false);
        lblNhanVien.setBackground(Color.red);
        lblQuanLyBan.setOpaque(false);
        lblQuanLyBan.setBackground(Color.red);
        lblDoiMatKhau.setOpaque(false);
        lblDoiMatKhau.setBackground(Color.red);
        lblBackupHeThong.setOpaque(false);
        lblBackupHeThong.setBackground(Color.red);

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

    private void lblTaiKhoan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoan2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTaiKhoan2MouseClicked

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

                // Thiết lập ImageIcon lên JLabel
                lblAnhNhanVienSua.setIcon(imageIcon);
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
        // TODO add your handling code here:
        DBackUpAndRestore.restoreDatabase(tblBackUp.getValueAt(tblBackUp.getSelectedRow(), 0).toString());
        this.dispose();
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
    public boolean isMNVExists(int maNhanVien, boolean isUpdating, int maNhanVienCu) {
        if (isUpdating && maNhanVien == (maNhanVienCu)) {
            return false;
        }
        // Lấy danh sách CCCD hiện có từ cơ sở dữ liệu
        Set<Integer> existingMANVs = new HashSet<>();
        List<TaiKhoanViewModel> existingTaiKhoans = iTaiKhoanServicess.getAll();
        for (TaiKhoanViewModel tk : existingTaiKhoans) {
            existingMANVs.add(tk.getMaNhanVien());
        }

        // Kiểm tra xem CCCD mới có trong danh sách đã lấy được hay không
        if (existingMANVs.contains(maNhanVien)) {
            // Kiểm tra nếu đang thực hiện cập nhật (update) thì hiển thị thông báo
            if (isUpdating) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại.");
            }
            return true;
        }

        return false;
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
        if (isMNVExists(maNhanVien, true, maNhanVienCu)) {
            return null;
        }
        if (matKhau.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được có dấu cách");
            return null;
        }
        taiKhoanViewModel.setMaTaiKhoan(maTaiKhoan1);

        taiKhoanViewModel.setMaNhanVien(maNhanVien);
        taiKhoanViewModel.setMatKhau(matKhau);
        String trangThai = cbbTrangThaiTaiKhoanSua.getSelectedItem().toString();
        taiKhoanViewModel.setTrangThai(trangThai.equals("Không Khoá") ? 1 : 0);

        taiKhoanViewModel.setRole(role);

        return taiKhoanViewModel;
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
            if (Uhelper.checkNullText(txtBanThemTenBan, "tên bàn trống")) {
                return;
            }
            QuanLyBanViewmodel ban = new QuanLyBanViewmodel(11, txtBanThemTenBan.getText(),
                    Integer.parseInt(cboBanThemTang.getSelectedItem() + ""));
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
            if (Uhelper.checkNullText(txtBanCapNhatTenBan, "tên bàn trống")) {
                return;
            }
            QuanLyBanViewmodel ban
                    = new QuanLyBanViewmodel(Integer.parseInt(lblBanCapNhatMaBan.getText()),
                            txtBanCapNhatTenBan.getText(), Integer.parseInt(cboBanCapNhatTang.getSelectedItem() + ""));
            int thongBao = ibanServices.CapNhatBan(ban);
            if (thongBao == 1) {
                JOptionPane.showMessageDialog(this, "cập nhật thành công");
                fillBanTableBan();
                return;
            } else {
                JOptionPane.showMessageDialog(this, "cập nhật thất bại");
                return;
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBanCapNhatActionPerformed

    private void btnKhieuNaiHoTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhieuNaiHoTroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKhieuNaiHoTroActionPerformed

    private void cboTimBanTheoTangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboTimBanTheoTangKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboTimBanTheoTangKeyPressed

    private void cboTimBanTheoTangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimBanTheoTangActionPerformed
        // TODO add your handling code here:
        int tang = Integer.parseInt(cboTimBanTheoTang.getSelectedItem() + "");
        BanBanModel.setRowCount(0);
        for (QuanLyBanViewmodel a : listBanviewmodel) {
            if (a.getTang() == tang) {
                BanBanModel.addRow(new Object[]{
                    a.getMaBan(), a.getTenBan(), a.getTang()
                });
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

    public void fillMaBan(int index) {
        lblBanCapNhatMaBan.setText(listBanviewmodel.get(index).getMaBan() + "");
        txtBanCapNhatTenBan.setText(listBanviewmodel.get(index).getTenBan());
        txtBanCapNhatTenBan.setText(listBanviewmodel.get(index).getTenBan());
        cboBanCapNhatTang.setSelectedItem(listBanviewmodel.get(index).getTang() + "");
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
    private javax.swing.JButton btnCapNhatTaiKhoanNhanVien;
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnCleanTaiKhoan;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnKhieuNaiHoTro;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnThemTaiKhoan;
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel jpnBackupHeThong;
    private javax.swing.JPanel jpnHoaDon;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnNhanVien;
    private javax.swing.JPanel jpnQLBan;
    private javax.swing.JPanel jpnSanPham;
    private javax.swing.JPanel jpnTaiKhoan;
    private javax.swing.JPanel jpnTong;
    private javax.swing.JPanel jpnVoucher;
    private javax.swing.JLabel lblAnhNhanVien;
    private javax.swing.JLabel lblAnhNhanVienSua;
    private javax.swing.JLabel lblBackupHeThong;
    private javax.swing.JLabel lblBanCapNhatMaBan;
    private javax.swing.JLabel lblDoiMatKhau;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JTextField lblMaSanPham10;
    private javax.swing.JTextField lblMaSanPham11;
    private javax.swing.JTextField lblMaSanPham2;
    private javax.swing.JTextField lblMaSanPham3;
    private javax.swing.JTextField lblMaSanPham30;
    private javax.swing.JTextField lblMaSanPham31;
    private javax.swing.JTextField lblMaSanPham32;
    private javax.swing.JTextField lblMaSanPham33;
    private javax.swing.JTextField lblMaSanPham34;
    private javax.swing.JTextField lblMaSanPham35;
    private javax.swing.JTextField lblMaSanPham36;
    private javax.swing.JTextField lblMaSanPham37;
    private javax.swing.JTextField lblMaSanPham38;
    private javax.swing.JTextField lblMaSanPham39;
    private javax.swing.JTextField lblMaSanPham4;
    private javax.swing.JTextField lblMaSanPham40;
    private javax.swing.JTextField lblMaSanPham41;
    private javax.swing.JTextField lblMaSanPham42;
    private javax.swing.JTextField lblMaSanPham43;
    private javax.swing.JTextField lblMaSanPham44;
    private javax.swing.JTextField lblMaSanPham45;
    private javax.swing.JTextField lblMaSanPham5;
    private javax.swing.JTextField lblMaSanPham6;
    private javax.swing.JTextField lblMaSanPham7;
    private javax.swing.JTextField lblMaSanPham8;
    private javax.swing.JTextField lblMaSanPham9;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblQuanLyBan;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTaiKhoan2;
    private javax.swing.JLabel lblTraSua;
    private javax.swing.JLabel lblVoucher;
    private javax.swing.JLabel lblquanly;
    private javax.swing.JTable tblBackUp;
    private javax.swing.JTable tblBanBan;
    private javax.swing.JTable tblNhanVienForm;
    private javax.swing.JTable tblTaiKhoanForm;
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
    private javax.swing.JTextField txtHoVaTenThem;
    private javax.swing.JTextField txtHoVaTenXem;
    private javax.swing.JTextField txtMaNhanVienThem;
    private javax.swing.JTextField txtMaNhanVienXem;
    private javax.swing.JTextField txtMaTaiKhoanSua;
    private javax.swing.JTextField txtMaTaiKhoanThem;
    private javax.swing.JTextField txtMatKhauTaiKhoanSua;
    private javax.swing.JTextField txtMatKhauThem;
    private javax.swing.JTextField txtNgaySinhThem;
    private javax.swing.JTextField txtNgaySinhXem;
    private javax.swing.JTextField txtSDTThem;
    private javax.swing.JTextField txtSDTXem;
    private javax.swing.JTextField txtTimKiemTaiKhoan;
    private javax.swing.JTextField txtTimKiemTen;
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
