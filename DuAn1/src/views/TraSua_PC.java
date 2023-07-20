/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import interfaceservices.IPhaCheLichSuServices;
import interfaceservices.ISanPhamService;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.PhaCheHoaDonServices;
import services.PhaCheLichSuServices;
import services.SanPhamService;
import utilities.PhaCheSanPhamJPanel;
import utilities.XImages;
import viewmodel.PhaCheLichSuDanhSachSanPhamViewmodel;
import viewmodel.PhaCheLichSuViewModel;
import viewmodel.PhaCheSanPhamViewModel;

public class TraSua_PC extends javax.swing.JFrame {

    DefaultTableModel modelLichSuHoaDon = new DefaultTableModel();
    DefaultTableModel modelLichSuDanhSachSp = new DefaultTableModel();
    DefaultTableModel modelHoaDon_HoaDon = new DefaultTableModel();
    DefaultTableModel modelHoaDon_DSSP = new DefaultTableModel();
    DefaultTableModel modelTongHopDonHANG = new DefaultTableModel();
    IPhaCheLichSuServices LichSuServices = new PhaCheLichSuServices();
    IPhaCheLichSuServices HoaDonServices = new PhaCheHoaDonServices();
    Map<String, Object> mapBan = LichSuServices.getBan();
    Map<String, Object> mapHoaDon = LichSuServices.getHoaDon();
    List<PhaCheLichSuDanhSachSanPhamViewmodel> lstSP = LichSuServices.getDSSP();
    List<PhaCheLichSuViewModel> lst = new ArrayList<>();
    List<PhaCheLichSuViewModel> lstCNhoadon = new ArrayList<>();
    ISanPhamService SanPhamPCService = new SanPhamService();

    private String maTaiKhoan;

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;

    }

    public TraSua_PC(String maTaiKhoan) {

        initComponents();
        jpnHienThiSP.setSize(1050, 2570);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        init();
        modelLichSuHoaDon = (DefaultTableModel) tbllichsudonhang.getModel();
        modelHoaDon_HoaDon = (DefaultTableModel) tblHoaDon_HoaDon.getModel();
        modelTongHopDonHANG = (DefaultTableModel) tblPhaCheTongDonHang.getModel();
        modelHoaDon_DSSP = (DefaultTableModel) tblHoaDon_DSSP.getModel();
        modelLichSuDanhSachSp = (DefaultTableModel) tbllichsudanhsachsphoadon.getModel();
        fillTableLichSuHoaDon();
        showGhiChu(0);
        fillTableDSSP(lst.get(0).getMaHoaDon());
        LoadSanPham();
        ///fill hóa đơn trong chức năng hóa đơn
        fillTableHoaDon_HoaDon();
        fillTableDSSPHoaDon(lstCNhoadon.get(0).getMaHoaDon());
        txtGhiChuHoaDon.setText(lstCNhoadon.get(0).getGhiChu());
        tongHopHoaDon();
        loadHd();
    }

    public void loadHd() {
        new Thread() {
            @Override
            public void run() {
                
                while (true) {
                    fillTableHoaDon_HoaDon();
                    fillTableDSSPHoaDon(lstCNhoadon.get(0).getMaHoaDon());
                    tongHopHoaDon();
                      try {
                        sleep(60000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        }.start();
    }

    public void capNhatTrangThaiHD() {
        int thongBao = HoaDonServices.capNhatTrangThai(Integer.parseInt(lblHoaDon_dssp.getText()),
                1);
        if (thongBao == 1) {
            fillTableHoaDon_HoaDon();
            fillTableDSSPHoaDon(lstCNhoadon.get(0).getMaHoaDon());
            tongHopHoaDon();
            JOptionPane.showMessageDialog(this, "hoàn thành đơn");
            return;
        }

    }

    public void init() {
        setIconImage(XImages.getIconApp());
    }

    public void LoadSanPham() {

        List<PhaCheSanPhamViewModel> lstPCSPViewModel = SanPhamPCService.getList();
        List<PhaCheSanPhamJPanel> LstPCSPJPanel = new ArrayList<>();

        for (PhaCheSanPhamViewModel PCSPView : lstPCSPViewModel) {
            LstPCSPJPanel.add(new PhaCheSanPhamJPanel(PCSPView));
        }

        GridLayout layout = new GridLayout((int) Math.ceil(lst.size() / 5), 5);
        layout.setHgap(10);
        layout.setVgap(10);
        jpnHienThiSP.setLayout(layout);
        for (PhaCheSanPhamJPanel PCSPJPanel : LstPCSPJPanel) {
            jpnHienThiSP.add(PCSPJPanel);
        }
    }

    public void fillTableLichSuHoaDon() {
        lst = LichSuServices.getList(mapBan, mapHoaDon, lstSP);
        modelLichSuHoaDon.setRowCount(0);
        lbllichsumahoadon.setText("Hóa đơn " + lst.get(0).getMaHoaDon());
        for (PhaCheLichSuViewModel a : lst) {
            modelLichSuHoaDon.addRow(new Object[]{a.getMaHoaDon(), a.getTenBan(),
                a.getTang(), a.getThoiGian(), a.getGhiChu()});
        }

    }

    //fill bảng hóa đơn trong chức năng hóa đơn
    public void fillTableHoaDon_HoaDon() {
        Map<String, Object> mapBan1 = LichSuServices.getBan();
        Map<String, Object> mapHoaDon1 = LichSuServices.getHoaDon();
        List<PhaCheLichSuDanhSachSanPhamViewmodel> lstSP1 = LichSuServices.getDSSP();
        lstCNhoadon = HoaDonServices.getList(mapBan1, mapHoaDon1, lstSP1);
        modelHoaDon_HoaDon.setRowCount(0);
        lblHoaDon_HoaDon.setText("Hóa đơn " + lstCNhoadon.get(0).getMaHoaDon());
        int stt = 1;
        for (PhaCheLichSuViewModel a : lstCNhoadon) {
            modelHoaDon_HoaDon.addRow(new Object[]{stt, a.getMaHoaDon(), a.getTenBan(),
                a.getTang(), a.getThoiGian(), a.getGhiChu()});
            stt++;
        }

    }

    //tổng hợp hóa đơn
    public void tongHopHoaDon() {
        Map<String, Object> mapBan1 = LichSuServices.getBan();
        Map<String, Object> mapHoaDon1 = LichSuServices.getHoaDon();
        List<PhaCheLichSuDanhSachSanPhamViewmodel> lstSP1 = LichSuServices.getDSSP();
        lstCNhoadon = HoaDonServices.getList(mapBan1, mapHoaDon1, lstSP1);

        List<PhaCheLichSuDanhSachSanPhamViewmodel> lstTongHop = new ArrayList<>();

        for (PhaCheLichSuViewModel a : lstCNhoadon) {
            for (PhaCheLichSuDanhSachSanPhamViewmodel b : a.getDanhSachSP()) {
                lstTongHop.add(b);
            }
        }
        modelTongHopDonHANG.setRowCount(0);
        for (PhaCheLichSuDanhSachSanPhamViewmodel a : lstTongHop) {
            modelTongHopDonHANG.addRow(new Object[]{a.getMaSanPham(), a.getTenSanPham(),
                a.getSize(), a.getSoLuong()});
        }

    }

    public void fillTableDSSP(int maHoaDon) {
        try {

            modelLichSuDanhSachSp.setRowCount(0);
            List<PhaCheLichSuDanhSachSanPhamViewmodel> lstFill = new ArrayList<>();

            for (PhaCheLichSuViewModel a : lst) {
                if (a.getMaHoaDon() == maHoaDon) {
                    lstFill = a.getDanhSachSP();
                }
            }
            if (lstFill.size() >= 0) {

                for (PhaCheLichSuDanhSachSanPhamViewmodel a : lstFill) {
                    int stt = 1;
                    modelLichSuDanhSachSp.addRow(new Object[]{
                        stt, a.getMaSanPham(), a.getTenSanPham(), a.getSize(), a.getSoLuong()
                    });
                    stt++;
                }
            } else {
                modelLichSuDanhSachSp.addRow(new Object[]{
                    "null", "null", "null", "null", "null"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "danh sách sản phẩm hóa đơn trống");
            return;
        }

    }

    //hiển thị bảng dssp chức năng hóa đơn
    public void fillTableDSSPHoaDon(int maHoaDon) {
        try {
            Map<String, Object> mapBan1 = LichSuServices.getBan();
            Map<String, Object> mapHoaDon1 = LichSuServices.getHoaDon();
            List<PhaCheLichSuDanhSachSanPhamViewmodel> lstSP1 = LichSuServices.getDSSP();

            lstCNhoadon = HoaDonServices.getList(mapBan1, mapHoaDon1, lstSP1);
            modelHoaDon_DSSP.setRowCount(0);
            List<PhaCheLichSuDanhSachSanPhamViewmodel> lstFill = new ArrayList<>();

            for (PhaCheLichSuViewModel a : lstCNhoadon) {
                if (a.getMaHoaDon() == maHoaDon) {
                    lstFill = a.getDanhSachSP();
                }
            }
            if (lstFill.size() >= 0) {

                for (PhaCheLichSuDanhSachSanPhamViewmodel a : lstFill) {
                    int stt = 1;
                    modelHoaDon_DSSP.addRow(new Object[]{
                        stt, a.getMaSanPham(), a.getTenSanPham(), a.getSize(), a.getSoLuong()
                    });
                    stt++;
                }
            } else {
                modelHoaDon_DSSP.addRow(new Object[]{
                    "null", "null", "null", "null", "null"
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "danh sách sản phẩm hóa đơn trống");
            return;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnMenu = new javax.swing.JPanel();
        lblTraSua = new javax.swing.JLabel();
        lblthietlap = new javax.swing.JLabel();
        lblHoaDon = new javax.swing.JLabel();
        lblLichSuDonHang = new javax.swing.JLabel();
        lblDoiMatKhau = new javax.swing.JLabel();
        btnKhieuNaiHoTro = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        lblSanPham = new javax.swing.JLabel();
        lblbanhang = new javax.swing.JLabel();
        jpnTong = new javax.swing.JPanel();
        jpnSanPham = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jpnHienThiSP = new javax.swing.JPanel();
        jpnHoaDon = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon_HoaDon = new javax.swing.JTable();
        lblHoaDon_HoaDon = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon_DSSP = new javax.swing.JTable();
        lblHoaDon_dssp = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChuHoaDon = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPhaCheTongDonHang = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jpnLichSu = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbllichsudonhang = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtlichsuGhiChu = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbllichsudanhsachsphoadon = new javax.swing.JTable();
        lbllichsumahoadon = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jpnKhieuNaiHoTro = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

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

        lblthietlap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblthietlap.setForeground(new java.awt.Color(255, 255, 255));
        lblthietlap.setText("THIẾT LẬP");
        lblthietlap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblthietlapMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblthietlapMouseEntered(evt);
            }
        });
        jpnMenu.add(lblthietlap, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 120, -1));

        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/hoaDon.png"))); // NOI18N
        lblHoaDon.setText("  HÓA ĐƠN");
        lblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHoaDonMouseClicked(evt);
            }
        });
        jpnMenu.add(lblHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 220, -1));

        lblLichSuDonHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblLichSuDonHang.setForeground(new java.awt.Color(255, 255, 255));
        lblLichSuDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/hoaDon.png"))); // NOI18N
        lblLichSuDonHang.setText("LỊCH SỬ ĐƠN HÀNG");
        lblLichSuDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLichSuDonHangMouseClicked(evt);
            }
        });
        jpnMenu.add(lblLichSuDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 220, -1));

        lblDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/doiMatKhau.png"))); // NOI18N
        lblDoiMatKhau.setText("  ĐỔI MẬT KHẨU");
        lblDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoiMatKhauMouseClicked(evt);
            }
        });
        jpnMenu.add(lblDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 370, 220, -1));

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
        jpnMenu.add(btnDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 750, 170, -1));
        jpnMenu.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 220, 10));
        jpnMenu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 220, 10));

        lblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sanPham.png"))); // NOI18N
        lblSanPham.setText("  SẢN PHẨM");
        lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseEntered(evt);
            }
        });
        jpnMenu.add(lblSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 220, -1));

        lblbanhang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblbanhang.setForeground(new java.awt.Color(255, 255, 255));
        lblbanhang.setText("BÁN HÀNG");
        lblbanhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblbanhangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblbanhangMouseEntered(evt);
            }
        });
        jpnMenu.add(lblbanhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 120, -1));

        jpnTong.setBackground(new java.awt.Color(0, 153, 51));
        jpnTong.setLayout(new java.awt.CardLayout());

        jpnSanPham.setBackground(new java.awt.Color(255, 255, 255));
        jpnSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Sản phẩm");
        jpnSanPham.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tim3.png"))); // NOI18N
        jpnSanPham.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 50, 30, -1));
        jpnSanPham.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1210, 40));

        jpnHienThiSP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnHienThiSP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPane6.setViewportView(jpnHienThiSP);

        jpnSanPham.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1214, 660));

        jpnTong.add(jpnSanPham, "card2");

        jpnHoaDon.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Danh sách sản phẩm");

        tblHoaDon_HoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên bàn", "Tầng", "Thời gian", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon_HoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDon_HoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon_HoaDon);

        lblHoaDon_HoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHoaDon_HoaDon.setForeground(new java.awt.Color(0, 102, 255));
        lblHoaDon_HoaDon.setText("Hóa đơn");

        tblHoaDon_DSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Size", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblHoaDon_DSSP);

        lblHoaDon_dssp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHoaDon_dssp.setForeground(new java.awt.Color(0, 102, 255));
        lblHoaDon_dssp.setText("Hóa đơn HD001");

        txtGhiChuHoaDon.setColumns(20);
        txtGhiChuHoaDon.setRows(5);
        jScrollPane4.setViewportView(txtGhiChuHoaDon);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 255));
        jLabel8.setText("Ghi chú");

        jButton1.setBackground(new java.awt.Color(45, 132, 252));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Hoàn thành đơn hàng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblPhaCheTongDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Size", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblPhaCheTongDonHang);

        jButton2.setBackground(new java.awt.Color(45, 132, 252));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Tổng hợp đơn hàng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnHoaDonLayout = new javax.swing.GroupLayout(jpnHoaDon);
        jpnHoaDon.setLayout(jpnHoaDonLayout);
        jpnHoaDonLayout.setHorizontalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoaDon_HoaDon))
                .addGap(18, 18, 18)
                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(lblHoaDon_dssp)
                    .addComponent(jScrollPane4)
                    .addComponent(jLabel8)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(677, Short.MAX_VALUE))
        );
        jpnHoaDonLayout.setVerticalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoaDon_HoaDon)
                    .addComponent(lblHoaDon_dssp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jpnHoaDonLayout.createSequentialGroup()
                        .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5))
                        .addGap(18, 18, 18)
                        .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(209, Short.MAX_VALUE))
        );

        jpnTong.add(jpnHoaDon, "card3");

        jpnLichSu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Lịch sử đơn hàng");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 102, 255));
        jLabel45.setText("Lịch sử đơn hàng");

        tbllichsudonhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên bàn", "Tầng", "Thời gian", "Ghi chú"
            }
        ));
        tbllichsudonhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbllichsudonhangMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbllichsudonhang);

        txtlichsuGhiChu.setColumns(20);
        txtlichsuGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtlichsuGhiChu);

        tbllichsudanhsachsphoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Size", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tbllichsudanhsachsphoadon);

        lbllichsumahoadon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbllichsumahoadon.setForeground(new java.awt.Color(0, 102, 255));
        lbllichsumahoadon.setText("Hóa đơn 001");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 102, 255));
        jLabel47.setText("Ghi chú");

        javax.swing.GroupLayout jpnLichSuLayout = new javax.swing.GroupLayout(jpnLichSu);
        jpnLichSu.setLayout(jpnLichSuLayout);
        jpnLichSuLayout.setHorizontalGroup(
            jpnLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnLichSuLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jpnLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel45)
                        .addComponent(jScrollPane7)
                        .addComponent(jLabel2)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel47))
                .addGap(39, 39, 39)
                .addGroup(jpnLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbllichsumahoadon))
                .addContainerGap(682, Short.MAX_VALUE))
        );
        jpnLichSuLayout.setVerticalGroup(
            jpnLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnLichSuLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addGroup(jpnLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(lbllichsumahoadon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnLichSuLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8))
                .addContainerGap(208, Short.MAX_VALUE))
        );

        jpnTong.add(jpnLichSu, "card5");

        jpnKhieuNaiHoTro.setBackground(new java.awt.Color(255, 255, 0));

        jLabel4.setText("KHIẾU NẠI HỖ TRỢ");

        javax.swing.GroupLayout jpnKhieuNaiHoTroLayout = new javax.swing.GroupLayout(jpnKhieuNaiHoTro);
        jpnKhieuNaiHoTro.setLayout(jpnKhieuNaiHoTroLayout);
        jpnKhieuNaiHoTroLayout.setHorizontalGroup(
            jpnKhieuNaiHoTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnKhieuNaiHoTroLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel4)
                .addContainerGap(1767, Short.MAX_VALUE))
        );
        jpnKhieuNaiHoTroLayout.setVerticalGroup(
            jpnKhieuNaiHoTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnKhieuNaiHoTroLayout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jLabel4)
                .addContainerGap(781, Short.MAX_VALUE))
        );

        jpnTong.add(jpnKhieuNaiHoTro, "card5");

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
        jpnHoaDon.setVisible(false);
        jpnLichSu.setVisible(false);
    }//GEN-LAST:event_btnKhieuNaiHoTroMouseClicked

    private void lblDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMatKhauMouseClicked
        lblDoiMatKhau.setOpaque(true);
        lblDoiMatKhau.setBackground(Color.gray);

        lblSanPham.setOpaque(false);
        lblSanPham.setBackground(Color.red);
        lblHoaDon.setOpaque(false);
        lblHoaDon.setBackground(Color.yellow);
        lblLichSuDonHang.setOpaque(false);
        lblLichSuDonHang.setBackground(Color.yellow);

        new DoiMatKhau(maTaiKhoan).setVisible(true);
    }//GEN-LAST:event_lblDoiMatKhauMouseClicked

    private void lblthietlapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblthietlapMouseClicked
        jpnSanPham.setVisible(true);
    }//GEN-LAST:event_lblthietlapMouseClicked

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed

    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void lblLichSuDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLichSuDonHangMouseClicked
        lblLichSuDonHang.setOpaque(true);
        lblLichSuDonHang.setBackground(Color.gray);

        lblSanPham.setOpaque(false);
        lblSanPham.setBackground(Color.red);
        lblHoaDon.setOpaque(false);
        lblHoaDon.setBackground(Color.red);

        lblDoiMatKhau.setOpaque(false);
        lblDoiMatKhau.setBackground(Color.red);

        jpnLichSu.setVisible(true);
        jpnSanPham.setVisible(false);
        jpnHoaDon.setVisible(false);

    }//GEN-LAST:event_lblLichSuDonHangMouseClicked

    private void lblthietlapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblthietlapMouseEntered

    }//GEN-LAST:event_lblthietlapMouseEntered

    private void lblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseClicked
        lblHoaDon.setOpaque(true);
        lblHoaDon.setBackground(Color.gray);

        lblSanPham.setOpaque(false);
        lblSanPham.setBackground(Color.red);

        lblLichSuDonHang.setOpaque(false);
        lblLichSuDonHang.setBackground(Color.red);
        lblDoiMatKhau.setOpaque(false);
        lblDoiMatKhau.setBackground(Color.red);

        jpnHoaDon.setVisible(true);
        jpnSanPham.setVisible(false);
        jpnLichSu.setVisible(false);
    }//GEN-LAST:event_lblHoaDonMouseClicked

    private void lblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseClicked
        // TODO add your handling code here:
        lblSanPham.setOpaque(true);
        lblSanPham.setBackground(Color.gray);
        lblHoaDon.setOpaque(false);
        lblHoaDon.setBackground(Color.red);
        lblLichSuDonHang.setOpaque(false);
        lblLichSuDonHang.setBackground(Color.red);
        lblDoiMatKhau.setOpaque(false);
        lblDoiMatKhau.setBackground(Color.red);

        jpnSanPham.setVisible(true);
        jpnHoaDon.setVisible(false);
        jpnLichSu.setVisible(false);
    }//GEN-LAST:event_lblSanPhamMouseClicked

    private void lblSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSanPhamMouseEntered

    private void lblbanhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbanhangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblbanhangMouseClicked

    private void lblbanhangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbanhangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblbanhangMouseEntered

    private void tbllichsudonhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllichsudonhangMouseClicked
        // TODO add your handling code here:
        int index = tbllichsudonhang.getSelectedRow();
        showGhiChu(index);
        int maHoaDon = lst.get(index).getMaHoaDon();
        fillTableDSSP(maHoaDon);
        lbllichsumahoadon.setText("Hóa đơn " + maHoaDon);
    }//GEN-LAST:event_tbllichsudonhangMouseClicked

    private void tblHoaDon_HoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon_HoaDonMouseClicked
        // TODO add your handling code here:
        int index = tblHoaDon_HoaDon.getSelectedRow();
        int maHoaDon = lstCNhoadon.get(index).getMaHoaDon();
        fillTableDSSPHoaDon(maHoaDon);
        txtGhiChuHoaDon.setText(lstCNhoadon.get(index).getGhiChu());
        lblHoaDon_HoaDon.setText("Hóa đơn " + lstCNhoadon.get(index).getMaHoaDon());
        lblHoaDon_dssp.setText(lstCNhoadon.get(index).getMaHoaDon() + "");


    }//GEN-LAST:event_tblHoaDon_HoaDonMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tongHopHoaDon();
        JOptionPane.showMessageDialog(null, "tổng hợp đơn hàng đã được hiển thị");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        capNhatTrangThaiHD();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void showGhiChu(int index) {
        txtlichsuGhiChu.setText(lst.get(index).getGhiChu());
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String maTaiKhoan = ""; // Lấy mã tài khoản từ giao diện đăng nhập
                TraSua_PC traSua_PC = new TraSua_PC(maTaiKhoan);
                System.out.println("mTK giao dien PC" + " " + maTaiKhoan);

                traSua_PC.setMaTaiKhoan(maTaiKhoan);
                traSua_PC.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnKhieuNaiHoTro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel jpnHienThiSP;
    private javax.swing.JPanel jpnHoaDon;
    private javax.swing.JPanel jpnKhieuNaiHoTro;
    private javax.swing.JPanel jpnLichSu;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnSanPham;
    private javax.swing.JPanel jpnTong;
    private javax.swing.JLabel lblDoiMatKhau;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblHoaDon_HoaDon;
    private javax.swing.JLabel lblHoaDon_dssp;
    private javax.swing.JLabel lblLichSuDonHang;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblTraSua;
    private javax.swing.JLabel lblbanhang;
    private javax.swing.JLabel lbllichsumahoadon;
    private javax.swing.JLabel lblthietlap;
    private javax.swing.JTable tblHoaDon_DSSP;
    private javax.swing.JTable tblHoaDon_HoaDon;
    private javax.swing.JTable tblPhaCheTongDonHang;
    private javax.swing.JTable tbllichsudanhsachsphoadon;
    private javax.swing.JTable tbllichsudonhang;
    private javax.swing.JTextArea txtGhiChuHoaDon;
    private javax.swing.JTextArea txtlichsuGhiChu;
    // End of variables declaration//GEN-END:variables
}
