/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainmodel.ChiTietHoaDonDomainModel;
import interfaceservices.INhanVienHoaDonServices;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.NhanVienBanService;
import services.NhanVienHoaDonServices;
import utilities.Uhelper;
import utilities.XImages;
import viewmodel.NhanVienBanViewModel;
import viewmodel.NhanVienHoaDonViewModel;
import viewmodel.PhaCheLichSuDanhSachSanPhamViewmodel;
import viewmodel.TaiKhoanViewModel;

public class TraSua_NV extends javax.swing.JFrame {

    int demTrang = 1;
    List<NhanVienHoaDonViewModel> lstTruyenTrang = new ArrayList<>();
    Map<Integer, List<NhanVienHoaDonViewModel>> mapPhanTrang = new HashMap<>();
    INhanVienHoaDonServices NVHoaDonSv = new NhanVienHoaDonServices();
    List<PhaCheLichSuDanhSachSanPhamViewmodel> ListDSSP = NVHoaDonSv.getDSSP();
    Map<Integer, String> mapTenNV = NVHoaDonSv.mapTenNV();
    Map<Integer, String> mapTenBan = NVHoaDonSv.mapTenBan();
    Map<Integer, Object> maGiamGia = NVHoaDonSv.mapMaGiamGia();
    List<ChiTietHoaDonDomainModel> listCTHD = NVHoaDonSv.getlistCTHD();
    DefaultTableModel modelNVhoaDon = new DefaultTableModel();
    List<NhanVienHoaDonViewModel> listNhanVienHDView = NVHoaDonSv.getList(ListDSSP, mapTenNV, mapTenBan, listCTHD, maGiamGia);
    private String maTaiKhoan;
    int soTrang = 1;

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;

    }
    ////////////////////////////////////////////////////////////////
    NhanVienBanService nvBanSe = new NhanVienBanService();
    DefaultTableModel tableModelBan = new DefaultTableModel();

    public TraSua_NV(String maTaiKhoan) {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        FillTableBan();
        modelNVhoaDon = (DefaultTableModel) tblNhanVienHoaDon.getModel();
        btnThem.setVisible(false);
        btnthem2.setVisible(false);
        init();
        jdcTu.setIcon(new ImageIcon(getClass().getResource("/Img/date_1.png")));
        jdcDen.setIcon(new ImageIcon(getClass().getResource("/Img/date_1.png")));

        phanTrang();
        truyenTrang(1);
        fillTableNVHD(lstTruyenTrang);

    }

    private void FillTableBan() {
        int stt = 1;
        tableModelBan = (DefaultTableModel) tblNhanVienBan.getModel();
        List<NhanVienBanViewModel> listNVban = nvBanSe.getAllNhanVienBan();
        tableModelBan.setRowCount(0);
        for (NhanVienBanViewModel nv : listNVban) {
            tableModelBan.addRow(new Object[]{
                stt++,
                nv.getMaHoaDon(),
                nv.getThoiGian(),
                nv.getTrangThaiOrder(),});

        }
    }

    public void truyenTrang(int index) {
        lstTruyenTrang = mapPhanTrang.get(index);
        lblNhanVienTrangThaiTrang.setText("trang " + index + "/" + soTrang);
    }

    public void phanTrang() {
        //lấy số trang
        if (listNhanVienHDView.size() > 100) {
            double so = listNhanVienHDView.size() / 100;
            soTrang = (int) Math.ceil(so);
        } else {
            soTrang = 1;
        }
        if (soTrang > 1) {
            int viTriChay = 1;
            int viTriDung = 100;
            //add map
            for (int i = 1; i <= soTrang; i++) {
                List<NhanVienHoaDonViewModel> listTrang = new ArrayList<>();
                //add list

                if (i <= soTrang - 1) {
                    for (int k = viTriChay; k <= viTriDung; k++) {
                        listTrang.add(listNhanVienHDView.get(k));
                    }
                    viTriDung += 100;
                    viTriChay += 100;
                } else {
                    if (i == soTrang) {
                        for (int j = (i - 1) * 100 + 1; j <= listNhanVienHDView.size(); j++) {
                            listTrang.add(listNhanVienHDView.get(j));
                        }
                    }
                }

                mapPhanTrang.put(i, listTrang);
            }
        } else {
            mapPhanTrang.put(1, listNhanVienHDView);
        }
    }

    public void init() {
        setIconImage(XImages.getIconApp());
    }

    public void fillTableNVHD(List<NhanVienHoaDonViewModel> list) {
        modelNVhoaDon.setRowCount(0);
        String trangThai = "";
        for (NhanVienHoaDonViewModel a : list) {
            trangThai = a.getTrangThai() == 1 ? "đã thanh toán" : "chưa thanh toán";
            modelNVhoaDon.addRow(new Object[]{
                a.getMaHoaDon(), a.getMaNguoiTao(), a.getThoiGian(), a.getTongThanhToan(), trangThai,
                a.getGhiChu()
            });
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnMenu = new javax.swing.JPanel();
        lblTraSua = new javax.swing.JLabel();
        lblThietLap = new javax.swing.JLabel();
        lblSanPham = new javax.swing.JLabel();
        lblQuanLyBan = new javax.swing.JLabel();
        lblHoaDon = new javax.swing.JLabel();
        lblDoiMatKhau = new javax.swing.JLabel();
        btnKhieuNaiHoTro = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        lblBanHang = new javax.swing.JLabel();
        jpnTong = new javax.swing.JPanel();
        jpnBanHang1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jpnQuanLyBan = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        btnTang1 = new javax.swing.JButton();
        btnTang2 = new javax.swing.JButton();
        btnTang3 = new javax.swing.JButton();
        btnTang4 = new javax.swing.JButton();
        btnTang5 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jpnBan = new javax.swing.JPanel();
        jpnTang1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jpnTang2 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jpnTang3 = new javax.swing.JPanel();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jpnTang4 = new javax.swing.JPanel();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jpnTang5 = new javax.swing.JPanel();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jButton61 = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblNhanVienBan = new javax.swing.JTable();
        txtVoucher = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        btnApDungBan = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lblGiaSauKhiGiam = new javax.swing.JLabel();
        btnThanhToanBan = new javax.swing.JButton();
        btnThemHoaDonBan = new javax.swing.JButton();
        jpnQLBan = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jpnBanHang = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpnSanPham = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblHinhAnh1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        btnthem2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblHinhAnh4 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jpnHoaDon = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jpnKhieuNaiHoTro = new javax.swing.JPanel();
        jpnHoaDon1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lblTimKiem = new javax.swing.JLabel();
        txtNhanVienNhapMaHD = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblNhanVienHoaDon = new javax.swing.JTable();
        jpnTrangThai = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cboNhanVienHDTrangThai = new javax.swing.JComboBox<>();
        jpnThoiGian1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jdcTu = new com.toedter.calendar.JDateChooser();
        jdcDen = new com.toedter.calendar.JDateChooser();
        lblNVTrangDau = new javax.swing.JLabel();
        lblNVlui = new javax.swing.JLabel();
        lblNhanVienTrangThaiTrang = new javax.swing.JLabel();
        lblNVTien = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();

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

        lblThietLap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThietLap.setForeground(new java.awt.Color(255, 255, 255));
        lblThietLap.setText("THIẾT LẬP");
        lblThietLap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThietLapMouseClicked(evt);
            }
        });
        jpnMenu.add(lblThietLap, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 120, -1));

        lblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sanPham.png"))); // NOI18N
        lblSanPham.setText("  SẢN PHẨM");
        lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseClicked(evt);
            }
        });
        jpnMenu.add(lblSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 220, -1));

        lblQuanLyBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuanLyBan.setForeground(new java.awt.Color(255, 255, 255));
        lblQuanLyBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ban2.png"))); // NOI18N
        lblQuanLyBan.setText("  QUẢN LÝ BÀN");
        lblQuanLyBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuanLyBanMouseClicked(evt);
            }
        });
        jpnMenu.add(lblQuanLyBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 220, -1));

        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/hoaDon.png"))); // NOI18N
        lblHoaDon.setText("  HÓA ĐƠN");
        lblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseClicked(evt);
            }
        });
        jpnMenu.add(lblHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 300, 220, -1));

        lblDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/doiMatKhau.png"))); // NOI18N
        lblDoiMatKhau.setText("  ĐỔI MẬT KHẨU");
        lblDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoiMatKhauMouseClicked(evt);
            }
        });
        jpnMenu.add(lblDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 420, 220, -1));

        btnKhieuNaiHoTro.setBackground(new java.awt.Color(2, 82, 155));
        btnKhieuNaiHoTro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKhieuNaiHoTro.setForeground(new java.awt.Color(255, 255, 255));
        btnKhieuNaiHoTro.setText("KHIẾU NẠI HỖ TRỢ ?");
        btnKhieuNaiHoTro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKhieuNaiHoTroMouseClicked(evt);
            }
        });
        jpnMenu.add(btnKhieuNaiHoTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, -1, 40));

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
        jpnMenu.add(btnDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 740, 170, -1));
        jpnMenu.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 220, 10));
        jpnMenu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 220, 10));

        lblBanHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBanHang.setForeground(new java.awt.Color(255, 255, 255));
        lblBanHang.setText("  BÁN HÀNG");
        lblBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBanHangMouseClicked(evt);
            }
        });
        jpnMenu.add(lblBanHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 120, -1));

        jpnTong.setBackground(new java.awt.Color(0, 153, 51));
        jpnTong.setLayout(new java.awt.CardLayout());

        jpnBanHang1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
        jLabel2.setText("Bán Hàng");

        jpnQuanLyBan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel28.setText("QUẢN LÝ BÀN");

        btnTang1.setBackground(new java.awt.Color(45, 132, 252));
        btnTang1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnTang1.setForeground(new java.awt.Color(255, 255, 255));
        btnTang1.setText("Tầng 1");
        btnTang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTang1ActionPerformed(evt);
            }
        });

        btnTang2.setBackground(new java.awt.Color(45, 132, 252));
        btnTang2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnTang2.setForeground(new java.awt.Color(255, 255, 255));
        btnTang2.setText("Tầng 2");
        btnTang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTang2ActionPerformed(evt);
            }
        });

        btnTang3.setBackground(new java.awt.Color(45, 132, 252));
        btnTang3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnTang3.setForeground(new java.awt.Color(255, 255, 255));
        btnTang3.setText("Tầng 3");
        btnTang3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTang3ActionPerformed(evt);
            }
        });

        btnTang4.setBackground(new java.awt.Color(45, 132, 252));
        btnTang4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnTang4.setForeground(new java.awt.Color(255, 255, 255));
        btnTang4.setText("Tầng 4");
        btnTang4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTang4ActionPerformed(evt);
            }
        });

        btnTang5.setBackground(new java.awt.Color(45, 132, 252));
        btnTang5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnTang5.setForeground(new java.awt.Color(255, 255, 255));
        btnTang5.setText("Tầng 5");
        btnTang5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTang5ActionPerformed(evt);
            }
        });

        jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 12))); // NOI18N
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel29.setText("Tầng 1");

        jButton4.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton4.setText("1");

        jButton5.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton5.setText("3");

        jButton6.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton6.setText("2");

        jButton7.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton7.setText("4");

        jButton8.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton8.setText("7");

        jButton9.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton9.setText("5");

        jButton10.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton10.setText("6");

        jButton11.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton11.setText("8");

        jButton12.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton12.setText("9");

        jButton13.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton13.setText("10");

        jButton14.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton14.setText("11");

        jButton15.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton15.setText("12");

        javax.swing.GroupLayout jpnTang1Layout = new javax.swing.GroupLayout(jpnTang1);
        jpnTang1.setLayout(jpnTang1Layout);
        jpnTang1Layout.setHorizontalGroup(
            jpnTang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTang1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jpnTang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnTang1Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnTang1Layout.createSequentialGroup()
                        .addGroup(jpnTang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnTang1Layout.createSequentialGroup()
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnTang1Layout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnTang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jpnTang1Layout.createSequentialGroup()
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnTang1Layout.createSequentialGroup()
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jpnTang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))))
        );
        jpnTang1Layout.setVerticalGroup(
            jpnTang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTang1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel29)
                .addGap(37, 37, 37)
                .addGroup(jpnTang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jpnTang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jpnTang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jpnTang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        jButton16.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton16.setText("11");

        jButton17.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton17.setText("12");

        jButton18.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton18.setText("3");

        jButton19.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton19.setText("2");

        jButton20.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton20.setText("4");

        jButton21.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton21.setText("7");

        jButton22.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton22.setText("5");

        jButton23.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton23.setText("6");

        jButton24.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton24.setText("8");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel30.setText("Tầng 2");

        jButton25.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton25.setText("9");

        jButton26.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton26.setText("1");

        jButton27.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton27.setText("10");

        javax.swing.GroupLayout jpnTang2Layout = new javax.swing.GroupLayout(jpnTang2);
        jpnTang2.setLayout(jpnTang2Layout);
        jpnTang2Layout.setHorizontalGroup(
            jpnTang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTang2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jpnTang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnTang2Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnTang2Layout.createSequentialGroup()
                        .addGroup(jpnTang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnTang2Layout.createSequentialGroup()
                                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnTang2Layout.createSequentialGroup()
                                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnTang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jpnTang2Layout.createSequentialGroup()
                                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnTang2Layout.createSequentialGroup()
                                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39)
                                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jpnTang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))))
        );
        jpnTang2Layout.setVerticalGroup(
            jpnTang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTang2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel30)
                .addGap(37, 37, 37)
                .addGroup(jpnTang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jpnTang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jpnTang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jpnTang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        jButton28.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton28.setText("11");

        jButton29.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton29.setText("12");

        jButton30.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton30.setText("3");

        jButton31.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton31.setText("2");

        jButton32.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton32.setText("4");

        jButton33.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton33.setText("7");

        jButton34.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton34.setText("5");

        jButton35.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton35.setText("6");

        jButton36.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton36.setText("8");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel31.setText("Tầng 3");

        jButton37.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton37.setText("9");

        jButton38.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton38.setText("1");

        jButton39.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton39.setText("10");

        javax.swing.GroupLayout jpnTang3Layout = new javax.swing.GroupLayout(jpnTang3);
        jpnTang3.setLayout(jpnTang3Layout);
        jpnTang3Layout.setHorizontalGroup(
            jpnTang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTang3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jpnTang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnTang3Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnTang3Layout.createSequentialGroup()
                        .addGroup(jpnTang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnTang3Layout.createSequentialGroup()
                                .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnTang3Layout.createSequentialGroup()
                                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnTang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jpnTang3Layout.createSequentialGroup()
                                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnTang3Layout.createSequentialGroup()
                                    .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39)
                                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jpnTang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))))
        );
        jpnTang3Layout.setVerticalGroup(
            jpnTang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTang3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel31)
                .addGap(37, 37, 37)
                .addGroup(jpnTang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jpnTang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jpnTang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jpnTang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        jButton40.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton40.setText("11");

        jButton41.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton41.setText("12");

        jButton42.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton42.setText("3");

        jButton43.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton43.setText("2");

        jButton44.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton44.setText("4");

        jButton45.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton45.setText("7");

        jButton46.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton46.setText("5");

        jButton47.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton47.setText("6");

        jButton48.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton48.setText("8");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel32.setText("Tầng 4");

        jButton49.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton49.setText("9");

        jButton50.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton50.setText("1");

        jButton51.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton51.setText("10");

        javax.swing.GroupLayout jpnTang4Layout = new javax.swing.GroupLayout(jpnTang4);
        jpnTang4.setLayout(jpnTang4Layout);
        jpnTang4Layout.setHorizontalGroup(
            jpnTang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTang4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jpnTang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnTang4Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnTang4Layout.createSequentialGroup()
                        .addGroup(jpnTang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnTang4Layout.createSequentialGroup()
                                .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnTang4Layout.createSequentialGroup()
                                .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnTang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jpnTang4Layout.createSequentialGroup()
                                    .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnTang4Layout.createSequentialGroup()
                                    .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39)
                                    .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jpnTang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))))
        );
        jpnTang4Layout.setVerticalGroup(
            jpnTang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTang4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel32)
                .addGap(37, 37, 37)
                .addGroup(jpnTang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jpnTang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jpnTang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jpnTang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        jButton52.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton52.setText("11");

        jButton53.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton53.setText("12");

        jButton54.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton54.setText("3");

        jButton55.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton55.setText("2");

        jButton56.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton56.setText("4");

        jButton57.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton57.setText("7");

        jButton58.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton58.setText("5");

        jButton59.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton59.setText("6");

        jButton60.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton60.setText("8");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel34.setText("Tầng 5");

        jButton61.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton61.setText("9");

        jButton62.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton62.setText("1");

        jButton63.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jButton63.setText("10");

        javax.swing.GroupLayout jpnTang5Layout = new javax.swing.GroupLayout(jpnTang5);
        jpnTang5.setLayout(jpnTang5Layout);
        jpnTang5Layout.setHorizontalGroup(
            jpnTang5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTang5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jpnTang5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnTang5Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnTang5Layout.createSequentialGroup()
                        .addGroup(jpnTang5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnTang5Layout.createSequentialGroup()
                                .addComponent(jButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnTang5Layout.createSequentialGroup()
                                .addComponent(jButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnTang5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jpnTang5Layout.createSequentialGroup()
                                    .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnTang5Layout.createSequentialGroup()
                                    .addComponent(jButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39)
                                    .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jpnTang5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))))
        );
        jpnTang5Layout.setVerticalGroup(
            jpnTang5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTang5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel34)
                .addGap(37, 37, 37)
                .addGroup(jpnTang5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jpnTang5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jpnTang5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jpnTang5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnBanLayout = new javax.swing.GroupLayout(jpnBan);
        jpnBan.setLayout(jpnBanLayout);
        jpnBanLayout.setHorizontalGroup(
            jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
            .addGroup(jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnBanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpnTang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnBanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpnTang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnBanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpnTang3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnBanLayout.createSequentialGroup()
                    .addContainerGap(16, Short.MAX_VALUE)
                    .addComponent(jpnTang4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnBanLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpnTang5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE)))
        );
        jpnBanLayout.setVerticalGroup(
            jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 662, Short.MAX_VALUE)
            .addGroup(jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnBanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpnTang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnBanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpnTang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnBanLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpnTang3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnBanLayout.createSequentialGroup()
                    .addContainerGap(16, Short.MAX_VALUE)
                    .addComponent(jpnTang4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnBanLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpnTang5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE)))
        );

        jScrollPane6.setViewportView(jpnBan);

        tblNhanVienBan.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblNhanVienBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Thời gian", "Tổng tiền", "Trạng thái ", "Chi tiết"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblNhanVienBan);

        txtVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVoucherActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel35.setText("Voucher");

        btnApDungBan.setBackground(new java.awt.Color(45, 132, 252));
        btnApDungBan.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnApDungBan.setForeground(new java.awt.Color(255, 255, 255));
        btnApDungBan.setText("Áp dụng");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel36.setText("Tổng tiền: ");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblTongTien.setText("0 VNĐ");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel38.setText("Giá sau khi giảm: ");

        lblGiaSauKhiGiam.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblGiaSauKhiGiam.setText("0 VNĐ");

        btnThanhToanBan.setBackground(new java.awt.Color(45, 132, 252));
        btnThanhToanBan.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnThanhToanBan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToanBan.setText("Thanh toán");

        btnThemHoaDonBan.setBackground(new java.awt.Color(45, 132, 252));
        btnThemHoaDonBan.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        btnThemHoaDonBan.setForeground(new java.awt.Color(255, 255, 255));
        btnThemHoaDonBan.setText("Thêm hóa đơn");

        javax.swing.GroupLayout jpnQuanLyBanLayout = new javax.swing.GroupLayout(jpnQuanLyBan);
        jpnQuanLyBan.setLayout(jpnQuanLyBanLayout);
        jpnQuanLyBanLayout.setHorizontalGroup(
            jpnQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLyBanLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jpnQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel28)
                    .addGroup(jpnQuanLyBanLayout.createSequentialGroup()
                        .addComponent(btnTang1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnTang2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnTang3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnTang4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnTang5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6))
                .addGap(42, 42, 42)
                .addGroup(jpnQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpnQuanLyBanLayout.createSequentialGroup()
                            .addComponent(txtVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(66, 66, 66)
                            .addComponent(btnApDungBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel35)
                        .addGroup(jpnQuanLyBanLayout.createSequentialGroup()
                            .addGroup(jpnQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel38)
                                .addComponent(jLabel36))
                            .addGap(27, 27, 27)
                            .addGroup(jpnQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblGiaSauKhiGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpnQuanLyBanLayout.createSequentialGroup()
                        .addComponent(btnThanhToanBan, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130)
                        .addComponent(btnThemHoaDonBan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1648, Short.MAX_VALUE))
        );
        jpnQuanLyBanLayout.setVerticalGroup(
            jpnQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLyBanLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel28)
                .addGap(36, 36, 36)
                .addGroup(jpnQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTang1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTang2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTang3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTang4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTang5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jpnQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnQuanLyBanLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel35)
                        .addGap(0, 0, 0)
                        .addGroup(jpnQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnApDungBan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jpnQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jpnQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(lblGiaSauKhiGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jpnQuanLyBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThanhToanBan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemHoaDonBan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(881, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnBanHang1Layout = new javax.swing.GroupLayout(jpnBanHang1);
        jpnBanHang1.setLayout(jpnBanHang1Layout);
        jpnBanHang1Layout.setHorizontalGroup(
            jpnBanHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBanHang1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnQuanLyBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnBanHang1Layout.setVerticalGroup(
            jpnBanHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBanHang1Layout.createSequentialGroup()
                .addGroup(jpnBanHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnBanHang1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel2))
                    .addGroup(jpnBanHang1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpnQuanLyBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnTong.add(jpnBanHang1, "card2");

        jpnQLBan.setBackground(new java.awt.Color(102, 204, 255));

        jLabel4.setText("QUẢN LÝ BÀN");

        javax.swing.GroupLayout jpnQLBanLayout = new javax.swing.GroupLayout(jpnQLBan);
        jpnQLBan.setLayout(jpnQLBanLayout);
        jpnQLBanLayout.setHorizontalGroup(
            jpnQLBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQLBanLayout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jLabel4)
                .addContainerGap(2809, Short.MAX_VALUE))
        );
        jpnQLBanLayout.setVerticalGroup(
            jpnQLBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQLBanLayout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jLabel4)
                .addContainerGap(1458, Short.MAX_VALUE))
        );

        jpnTong.add(jpnQLBan, "card4");

        jpnBanHang.setBackground(new java.awt.Color(0, 51, 51));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BÁN HÀNG");

        javax.swing.GroupLayout jpnBanHangLayout = new javax.swing.GroupLayout(jpnBanHang);
        jpnBanHang.setLayout(jpnBanHangLayout);
        jpnBanHangLayout.setHorizontalGroup(
            jpnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBanHangLayout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addComponent(jLabel1)
                .addContainerGap(2726, Short.MAX_VALUE))
        );
        jpnBanHangLayout.setVerticalGroup(
            jpnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBanHangLayout.createSequentialGroup()
                .addGap(387, 387, 387)
                .addComponent(jLabel1)
                .addContainerGap(1233, Short.MAX_VALUE))
        );

        jpnTong.add(jpnBanHang, "card2");

        jpnSanPham.setBackground(new java.awt.Color(255, 255, 255));
        jpnSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Sản phẩm");
        jpnSanPham.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Chọn tầng ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tầng 1", "Tầng 2", "Tầng 3", "Tầng 4", "Tầng 5" }));

        jLabel7.setText("Chọn bàn");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bàn 1", "Bàn 2", "Bàn 3", "Bàn 4", "Bàn 5", "Bàn 6", " " }));

        jButton1.setBackground(new java.awt.Color(45, 132, 252));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Thêm bàn");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Đơn giá", "Số lượng", "Xóa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jLabel8.setText("Ghi chú");

        jLabel9.setText("Phí dịch vụ phát sinh(nếu có)");

        jLabel10.setText("Tổng thanh toán(tạm tính):");

        jButton2.setBackground(new java.awt.Color(45, 132, 252));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Thanh toán");

        jButton3.setBackground(new java.awt.Color(45, 132, 252));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Thêm hóa đơn");

        jLabel20.setText("Danh sách sản phẩm");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 153, 255));
        jLabel21.setText("Xóa tất cả");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addGap(26, 26, 26)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(60, 60, 60)
                                            .addComponent(jButton1))))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addGap(15, 15, 15)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(31, 31, 31))
        );

        jpnSanPham.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 84, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tim3.png"))); // NOI18N
        jpnSanPham.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 90, -1, -1));
        jpnSanPham.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 84, 846, 35));

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnThem.setBackground(new java.awt.Color(45, 132, 252));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel2.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblHinhAnh1.setText("Hình ảnh 2");
        lblHinhAnh1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblHinhAnh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHinhAnh1MouseEntered(evt);
            }
        });

        jLabel11.setText("Tên sản phẩm 2");

        jLabel12.setText("Giá");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblHinhAnh1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel11)))
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblHinhAnh1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 200, 280));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setText("Hình ảnh 2");
        jLabel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel24MouseEntered(evt);
            }
        });
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 19, 163, 207));

        jLabel33.setText("Tên sản phẩm 2");
        jPanel4.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 232, -1, -1));

        jLabel41.setText("Giá");
        jPanel4.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 254, 43, -1));

        btnthem2.setText("Thêm");
        jPanel4.add(btnthem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 200, 280));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel42.setText("Hình ảnh 2");
        jLabel42.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel43.setText("Tên sản phẩm 2");

        jLabel44.setText("Giá");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel43)))
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 200, -1));

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel45.setText("Hình ảnh 2");
        jLabel45.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel46.setText("Tên sản phẩm 2");

        jLabel47.setText("Giá");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel46)))
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 200, 280));

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel48.setText("Hình ảnh 2");
        jLabel48.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel49.setText("Tên sản phẩm 2");

        jLabel50.setText("Giá");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel49)))
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 200, 280));

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel51.setText("Hình ảnh 2");
        jLabel51.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel52.setText("Tên sản phẩm 2");

        jLabel53.setText("Giá");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel52)))
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel53)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 340, 200, -1));

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel54.setText("Hình ảnh 2");
        jLabel54.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel55.setText("Tên sản phẩm 2");

        jLabel56.setText("Giá");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel55)))
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel56)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 200, 280));

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel57.setText("Hình ảnh 2");
        jLabel57.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel58.setText("Tên sản phẩm 2");

        jLabel59.setText("Giá");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel58)))
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel59)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 650, 200, -1));

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblHinhAnh4.setText("Hình ảnh 2");
        lblHinhAnh4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblHinhAnh4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHinhAnh4MouseEntered(evt);
            }
        });

        jLabel61.setText("Tên sản phẩm 2");

        jLabel62.setText("Giá");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblHinhAnh4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel61)))
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblHinhAnh4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel62)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 200, 280));

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel63.setText("Hình ảnh 2");
        jLabel63.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel64.setText("Tên sản phẩm 2");

        jLabel65.setText("Giá");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel64)))
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel65)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 660, 200, 280));

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel66.setText("Hình ảnh 2");
        jLabel66.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel67.setText("Tên sản phẩm 2");

        jLabel68.setText("Giá");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel67)))
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel68)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 660, 200, 280));

        jScrollPane4.setViewportView(jPanel2);

        jpnSanPham.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 137, 840, 660));

        jpnTong.add(jpnSanPham, "card3");

        jpnHoaDon.setBackground(new java.awt.Color(0, 204, 204));

        jLabel5.setText("HÓA ĐƠN");

        javax.swing.GroupLayout jpnHoaDonLayout = new javax.swing.GroupLayout(jpnHoaDon);
        jpnHoaDon.setLayout(jpnHoaDonLayout);
        jpnHoaDonLayout.setHorizontalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel5)
                .addContainerGap(2865, Short.MAX_VALUE))
        );
        jpnHoaDonLayout.setVerticalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                .addGap(522, 522, 522)
                .addComponent(jLabel5)
                .addContainerGap(1098, Short.MAX_VALUE))
        );

        jpnTong.add(jpnHoaDon, "card5");

        jpnKhieuNaiHoTro.setBackground(new java.awt.Color(51, 255, 51));

        javax.swing.GroupLayout jpnKhieuNaiHoTroLayout = new javax.swing.GroupLayout(jpnKhieuNaiHoTro);
        jpnKhieuNaiHoTro.setLayout(jpnKhieuNaiHoTroLayout);
        jpnKhieuNaiHoTroLayout.setHorizontalGroup(
            jpnKhieuNaiHoTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3040, Short.MAX_VALUE)
        );
        jpnKhieuNaiHoTroLayout.setVerticalGroup(
            jpnKhieuNaiHoTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1636, Short.MAX_VALUE)
        );

        jpnTong.add(jpnKhieuNaiHoTro, "card7");

        jpnHoaDon1.setBackground(new java.awt.Color(255, 255, 255));
        jpnHoaDon1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("HÓA ĐƠN");
        jpnHoaDon1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        lblTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tim3.png"))); // NOI18N
        lblTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTimKiemMouseClicked(evt);
            }
        });
        jpnHoaDon1.add(lblTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 70, -1, 30));
        jpnHoaDon1.add(txtNhanVienNhapMaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 890, 30));

        tblNhanVienHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Người tạo", "Thời gian", "Tổng thanh toán", "Trạng thái thanh toán", "Ghi chú", "Chi tiết"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblNhanVienHoaDon);

        jpnHoaDon1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 890, 530));

        jpnTrangThai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpnTrangThai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setBackground(new java.awt.Color(204, 204, 204));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Trạng Thái");
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpnTrangThai.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 30));

        jLabel15.setText("Trạng thái");
        jpnTrangThai.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 25));

        cboNhanVienHDTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán" }));
        jpnTrangThai.add(cboNhanVienHDTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 170, 25));

        jpnHoaDon1.add(jpnTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 270, 120));

        jpnThoiGian1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpnThoiGian1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setBackground(new java.awt.Color(204, 204, 204));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Thời Gian");
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpnThoiGian1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 30));

        jLabel17.setText("Đến");
        jpnThoiGian1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 25));

        jLabel18.setText("Từ");
        jpnThoiGian1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 25));

        jdcTu.setDateFormatString("yyyy-MM-dd");
        jdcTu.setFocusCycleRoot(true);
        jdcTu.setIcon(null);
        jpnThoiGian1.add(jdcTu, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 180, 25));

        jdcDen.setDateFormatString("yyyy-MM-dd");
        jdcDen.setIcon(null);
        jpnThoiGian1.add(jdcDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 180, 25));

        jpnHoaDon1.add(jpnThoiGian1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 270, 140));

        lblNVTrangDau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/2.png"))); // NOI18N
        lblNVTrangDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNVTrangDauMouseClicked(evt);
            }
        });
        jpnHoaDon1.add(lblNVTrangDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 740, -1, 25));

        lblNVlui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/4.png"))); // NOI18N
        lblNVlui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNVluiMouseClicked(evt);
            }
        });
        jpnHoaDon1.add(lblNVlui, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 740, -1, 25));

        lblNhanVienTrangThaiTrang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNhanVienTrangThaiTrang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNhanVienTrangThaiTrang.setText("Bản ghi 1/1");
        jpnHoaDon1.add(lblNhanVienTrangThaiTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 740, 120, 25));

        lblNVTien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/3.png"))); // NOI18N
        lblNVTien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNVTienMouseClicked(evt);
            }
        });
        jpnHoaDon1.add(lblNVTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 740, -1, 25));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/1.png"))); // NOI18N
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        jpnHoaDon1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 740, -1, 25));

        jpnTong.add(jpnHoaDon1, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseClicked
        new DangXuat().setVisible(true);
    }//GEN-LAST:event_btnDangXuatMouseClicked

    private void btnKhieuNaiHoTroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhieuNaiHoTroMouseClicked
        jpnKhieuNaiHoTro.setVisible(true);
        jpnSanPham.setVisible(false);
        jpnBanHang.setVisible(false);
        jpnQLBan.setVisible(false);
        jpnHoaDon1.setVisible(false);


    }//GEN-LAST:event_btnKhieuNaiHoTroMouseClicked

    private void lblDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMatKhauMouseClicked

        lblDoiMatKhau.setOpaque(true);
        lblDoiMatKhau.setBackground(Color.gray);

        lblQuanLyBan.setBackground(Color.red);
        lblQuanLyBan.setOpaque(false);
        lblSanPham.setBackground(Color.red);
        lblSanPham.setOpaque(false);
        lblHoaDon.setBackground(Color.red);
        lblHoaDon.setOpaque(false);

        new DoiMatKhau(maTaiKhoan).setVisible(true);
    }//GEN-LAST:event_lblDoiMatKhauMouseClicked

    private void lblThietLapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThietLapMouseClicked

    }//GEN-LAST:event_lblThietLapMouseClicked

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed

    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void lblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseClicked
        lblSanPham.setOpaque(true);
        lblSanPham.setBackground(Color.gray);

        lblQuanLyBan.setBackground(Color.red);
        lblQuanLyBan.setOpaque(false);
        lblHoaDon.setBackground(Color.red);
        lblHoaDon.setOpaque(false);
        lblDoiMatKhau.setBackground(Color.red);
        lblDoiMatKhau.setOpaque(false);
        jpnSanPham.setVisible(true);
        jpnBanHang1.setVisible(false);
        jpnHoaDon1.setVisible(false);

    }//GEN-LAST:event_lblSanPhamMouseClicked

    private void lblQuanLyBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLyBanMouseClicked
        lblQuanLyBan.setOpaque(true);
        lblQuanLyBan.setBackground(Color.gray);

        lblSanPham.setBackground(Color.red);
        lblSanPham.setOpaque(false);

        lblHoaDon.setBackground(Color.red);
        lblHoaDon.setOpaque(false);

        lblDoiMatKhau.setBackground(Color.red);
        lblDoiMatKhau.setOpaque(false);

        jpnBanHang1.setVisible(true);
        jpnSanPham.setVisible(false);
        jpnBanHang.setVisible(false);
    }//GEN-LAST:event_lblQuanLyBanMouseClicked

    private void lblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseClicked
        lblHoaDon.setBackground(Color.gray);
        lblHoaDon.setOpaque(true);
        lblQuanLyBan.setBackground(Color.red);
        lblQuanLyBan.setOpaque(false);
        lblSanPham.setBackground(Color.red);
        lblSanPham.setOpaque(false);

        lblDoiMatKhau.setBackground(Color.red);
        lblDoiMatKhau.setOpaque(false);

        jpnHoaDon1.setVisible(true);
        jpnSanPham.setVisible(false);
        jpnBanHang1.setVisible(false);
        jpnQLBan.setVisible(false);

    }//GEN-LAST:event_lblHoaDonMouseClicked

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        btnThem.setVisible(false);
    }//GEN-LAST:event_jPanel2MouseEntered

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

    }//GEN-LAST:event_btnThemActionPerformed

    private void lblHinhAnh4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnh4MouseEntered

    }//GEN-LAST:event_lblHinhAnh4MouseEntered

    private void lblHinhAnh1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnh1MouseEntered
        btnThem.setVisible(true);
    }//GEN-LAST:event_lblHinhAnh1MouseEntered

    private void lblTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTimKiemMouseClicked
        // TODO add your handling code her
        try {
            if (Uhelper.checkNullText(txtNhanVienNhapMaHD, "bạn chưa nhập mã hóa đơn")) {
                return;
            } else {
                try {
                    int maHD = Integer.parseInt(txtNhanVienNhapMaHD.getText());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "mã hóa đơn phải là số, hãy đảm bảo mã hóa đơn không có khoảng trắng");
                    txtNhanVienNhapMaHD.requestFocus();;
                    return;
                }
            }
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                String ngay1 = df.format(jdcTu.getDate());

                java.util.Date ngayTu = (java.util.Date) df.parse(ngay1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "chưa nhập ngày bắt đầu");
                jdcTu.requestFocus();
                return;
            }
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                String ngay2 = df.format(jdcDen.getDate());

                java.util.Date ngayDen = (java.util.Date) df.parse(ngay2);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "chưa nhập ngày kết thúc");
                jdcDen.requestFocus();
                return;
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            String ngay1 = df.format(jdcTu.getDate());
            String ngay2 = df.format(jdcDen.getDate());

            java.util.Date ngayTu = (java.util.Date) df.parse(ngay1);
            java.util.Date ngayDen = (java.util.Date) df.parse(ngay2);
            int maHoaDon = Integer.parseInt(txtNhanVienNhapMaHD.getText());
            int trangThai = (cboNhanVienHDTrangThai.getSelectedItem() + "").equalsIgnoreCase("Đã thanh toán") ? 1 : 0;
            List<NhanVienHoaDonViewModel> lst = NVHoaDonSv.getList(ListDSSP, mapTenNV, mapTenBan, listCTHD, maGiamGia);
            List<NhanVienHoaDonViewModel> lstTim = NVHoaDonSv.timHD(ngayTu, ngayDen, maHoaDon, trangThai, lst);
            if (lstTim.size() > 0) {
                fillTableNVHD(lstTim);
                JOptionPane.showMessageDialog(null, "danh sách đã hiển thị");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "không tìm thấy danh sách phù hợp");
                return;
            }

        } catch (Exception e) {
        }

    }//GEN-LAST:event_lblTimKiemMouseClicked

    private void lblBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBanHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblBanHangMouseClicked

    private void btnTang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTang1ActionPerformed
        // TODO add your handling code here:
        jpnTang1.setVisible(true);
    }//GEN-LAST:event_btnTang1ActionPerformed

    private void btnTang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTang2ActionPerformed
        // TODO add your handling code here:
        jpnTang2.setVisible(true);
        jpnTang1.setVisible(false);
    }//GEN-LAST:event_btnTang2ActionPerformed

    private void btnTang3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTang3ActionPerformed
        // TODO add your handling code here:
        jpnTang3.setVisible(true);
        jpnTang2.setVisible(false);
        jpnTang1.setVisible(false);
    }//GEN-LAST:event_btnTang3ActionPerformed

    private void btnTang4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTang4ActionPerformed
        jpnTang4.setVisible(true);
        jpnTang3.setVisible(false);
        jpnTang2.setVisible(false);
        jpnTang1.setVisible(false);
    }//GEN-LAST:event_btnTang4ActionPerformed

    private void btnTang5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTang5ActionPerformed
        // TODO add your handling code here:
        jpnTang5.setVisible(true);
        jpnTang4.setVisible(false);
        jpnTang3.setVisible(false);
        jpnTang2.setVisible(false);
        jpnTang1.setVisible(false);
    }//GEN-LAST:event_btnTang5ActionPerformed

    private void jLabel24MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseEntered
        // TODO add your handling code here:
        btnthem2.setVisible(true);
    }//GEN-LAST:event_jLabel24MouseEntered

    private void txtVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVoucherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVoucherActionPerformed

    private void lblNVTrangDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNVTrangDauMouseClicked
        // TODO add your handling code here:
        truyenTrang(1);
        fillTableNVHD(lstTruyenTrang);
        demTrang = 1;
    }//GEN-LAST:event_lblNVTrangDauMouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        // TODO add your handling code here:
        truyenTrang(soTrang);
        fillTableNVHD(lstTruyenTrang);
        demTrang = soTrang;
    }//GEN-LAST:event_jLabel27MouseClicked

    private void lblNVluiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNVluiMouseClicked
        // TODO add your handling code here:
        if (demTrang == 1) {
            JOptionPane.showMessageDialog(this, "không thể lùi");
            demTrang = 1;
            return;
        } else {
            truyenTrang(demTrang);
            fillTableNVHD(lstTruyenTrang);
            demTrang--;
        }

    }//GEN-LAST:event_lblNVluiMouseClicked

    private void lblNVTienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNVTienMouseClicked
        // TODO add your handling code here:
        if (demTrang == soTrang) {
            JOptionPane.showMessageDialog(this, "không thể tiến");
            demTrang = soTrang;
            return;
        } else {
            truyenTrang(demTrang);
            fillTableNVHD(lstTruyenTrang);
            demTrang++;
        }
    }//GEN-LAST:event_lblNVTienMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String maTaiKhoan = ""; // Lấy mã tài khoản từ giao diện đăng nhập
                TraSua_NV traSua_NV = new TraSua_NV(maTaiKhoan);
                traSua_NV.setMaTaiKhoan(maTaiKhoan);
                traSua_NV.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApDungBan;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnKhieuNaiHoTro;
    private javax.swing.JButton btnTang1;
    private javax.swing.JButton btnTang2;
    private javax.swing.JButton btnTang3;
    private javax.swing.JButton btnTang4;
    private javax.swing.JButton btnTang5;
    private javax.swing.JButton btnThanhToanBan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemHoaDonBan;
    private javax.swing.JButton btnthem2;
    private javax.swing.JComboBox<String> cboNhanVienHDTrangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
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
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private com.toedter.calendar.JDateChooser jdcDen;
    private com.toedter.calendar.JDateChooser jdcTu;
    private javax.swing.JPanel jpnBan;
    private javax.swing.JPanel jpnBanHang;
    private javax.swing.JPanel jpnBanHang1;
    private javax.swing.JPanel jpnHoaDon;
    private javax.swing.JPanel jpnHoaDon1;
    private javax.swing.JPanel jpnKhieuNaiHoTro;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnQLBan;
    private javax.swing.JPanel jpnQuanLyBan;
    private javax.swing.JPanel jpnSanPham;
    private javax.swing.JPanel jpnTang1;
    private javax.swing.JPanel jpnTang2;
    private javax.swing.JPanel jpnTang3;
    private javax.swing.JPanel jpnTang4;
    private javax.swing.JPanel jpnTang5;
    private javax.swing.JPanel jpnThoiGian1;
    private javax.swing.JPanel jpnTong;
    private javax.swing.JPanel jpnTrangThai;
    private javax.swing.JLabel lblBanHang;
    private javax.swing.JLabel lblDoiMatKhau;
    private javax.swing.JLabel lblGiaSauKhiGiam;
    private javax.swing.JLabel lblHinhAnh1;
    private javax.swing.JLabel lblHinhAnh4;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblNVTien;
    private javax.swing.JLabel lblNVTrangDau;
    private javax.swing.JLabel lblNVlui;
    private javax.swing.JLabel lblNhanVienTrangThaiTrang;
    private javax.swing.JLabel lblQuanLyBan;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblThietLap;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTraSua;
    private javax.swing.JTable tblNhanVienBan;
    private javax.swing.JTable tblNhanVienHoaDon;
    private javax.swing.JTextField txtNhanVienNhapMaHD;
    private javax.swing.JTextField txtVoucher;
    // End of variables declaration//GEN-END:variables

}
