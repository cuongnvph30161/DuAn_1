/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import interfaceservices.IPhaCheLichSuServices;
import interfaceservices.ISanPhamService;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.PhaCheHoaDonServices;
import services.PhaCheLichSuServices;
import services.SanPhamService;
import utilities.DBConnect;
import utilities.PhaCheSanPhamJPanel;
import utilities.Uhelper;
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
    Map<String, Object> mapBan = new HashMap<>();
    Map<String, Object> mapHoaDon = new HashMap<>();
    List<PhaCheLichSuDanhSachSanPhamViewmodel> lstSP = new ArrayList<>();
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
        modelHoaDon_HoaDon.setRowCount(0);
        modelTongHopDonHANG.setRowCount(0);
        modelHoaDon_DSSP.setRowCount(0);

        try {
            fillTableLichSuHoaDon();
           txtlichsuGhiChu.setText(tbllichsudonhang.getValueAt(0, 4) + "");
//            fillTableDSSP(lst.get(0).getMaHoaDon());
            LoadSanPham();
            ///fill hóa đơn trong chức năng hóa đơn
            fillTableHoaDon_HoaDon();
            try {
                fillTableDSSPHoaDon(lstCNhoadon.get(0).getMaHoaDon());
            } catch (Exception e) {
            }

            tongHopHoaDon();
            loadHd();
            loadLB();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadLB() {
        if (lstCNhoadon.size() > 0) {

            lblHoaDon_HoaDon.setText("Hóa đơn " + lstCNhoadon.get(0).getMaHoaDon());
            lblHoaDon_dssp.setText(lstCNhoadon.get(0).getMaHoaDon() + "");
            txtGhiChuHoaDon.setText(lstCNhoadon.get(0).getGhiChu());
        }
    }

    public void loadHd() {
        new Thread() {
            @Override
            public void run() {

                while (true) {

                    fillTableHoaDon_HoaDon();
//                    try {
//                        fillTableDSSPHoaDon(lstCNhoadon.get(0).getMaHoaDon());
//                    } catch (Exception e) {
//                    }
                    tongHopHoaDon();

                    try {
                        sleep(5000);
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
            JOptionPane.showMessageDialog(this, "hoàn thành đơn");

            fillTableHoaDon_HoaDon();

            tongHopHoaDon();

            try {
                fillTableDSSPHoaDon(lstCNhoadon.get(0).getMaHoaDon());
            } catch (Exception e) {
            }

            return;
        }

    }

    public void init() {
        setIconImage(XImages.getIconApp());
    }

    public void LoadSanPham() {
        jpnHienThiSP.removeAll();
        GridBagLayout layout = new GridBagLayout();

        jpnHienThiSP.setLayout(layout);

        GridBagConstraints gcb = new GridBagConstraints();
        List<PhaCheSanPhamViewModel> lstPCSPViewModel = SanPhamPCService.getList();
        List<PhaCheSanPhamJPanel> LstPCSPJPanel = new ArrayList<>();

        for (PhaCheSanPhamViewModel PCSPView : lstPCSPViewModel) {
            LstPCSPJPanel.add(new PhaCheSanPhamJPanel(PCSPView));
        }

        int x = 0;
        int y = (int) Math.ceil(LstPCSPJPanel.size() / 5);

        for (PhaCheSanPhamJPanel PCSPJPanel : LstPCSPJPanel) {
            gcb.anchor = GridBagConstraints.FIRST_LINE_START;
            gcb.gridx = x;
            gcb.gridy = y;
            gcb.weightx = 0;
            gcb.weighty = 1;
            //gcb.fill=GridBagConstraints.NONE;
            gcb.gridheight = 1;
            gcb.gridwidth = 1;
            gcb.insets = new Insets(10, 10, 10, 10);

            jpnHienThiSP.add(PCSPJPanel, gcb);
            jpnHienThiSP.updateUI();
            x++;
            if (x == 5) {
                x = 0;
                y++;
            }
        }
    }

    public void fillTableLichSuHoaDon() {
        Map<String, Object> mapBan = LichSuServices.getBan();
        Map<String, Object> mapHoaDon = LichSuServices.getHoaDon();
        List<PhaCheLichSuDanhSachSanPhamViewmodel> lstSP = LichSuServices.getDSSP();
        lst = LichSuServices.getList(mapBan, mapHoaDon, lstSP);
        modelLichSuHoaDon.setRowCount(0);
        lbllichsumahoadon.setText("Hóa đơn " + lst.get(0).getMaHoaDon());

        long millis = System.currentTimeMillis();
        java.sql.Date soiNgay = new java.sql.Date(millis);
        List<PhaCheLichSuViewModel> lstLoc = new ArrayList<>();

        lstLoc.add(new PhaCheLichSuViewModel(
                lst.get(0).getMaHoaDon(),
                "", lst.get(0).getTang(),
                lst.get(0).getThoiGian(),
                lst.get(0).getGhiChu(),
                lst.get(0).getDanhSachSP()));
        for (PhaCheLichSuViewModel a : lst) {
            int check = 1;
            for (PhaCheLichSuViewModel b : lstLoc) {
                if (a.getMaHoaDon() == b.getMaHoaDon()) {
                    b.setTenBan(a.getTenBan() + ", " + b.getTenBan());
                    check = -1;
                }
            }
            if (check == 1) {
                lstLoc.add(a);
            }
        }
        lst = lstLoc;
        for (PhaCheLichSuViewModel a : lst) {

            java.sql.Date soi = new java.sql.Date(a.getThoiGian().getTime());
            if (soi.toString().equalsIgnoreCase(soiNgay.toString())) {
                modelLichSuHoaDon.addRow(new Object[]{a.getMaHoaDon(), a.getTenBan(),
                    a.getTang(), a.getThoiGian(), a.getGhiChu()});

            }

        }

    }

    //fill bảng hóa đơn trong chức năng hóa đơn
    public void fillTableHoaDon_HoaDon() {
        try {
            Map<String, Object> mapBan1 = LichSuServices.getBan();
            Map<String, Object> mapHoaDon1 = LichSuServices.getHoaDon();
            List<PhaCheLichSuDanhSachSanPhamViewmodel> lstSP1 = LichSuServices.getDSSP();
            lstCNhoadon = HoaDonServices.getList(mapBan1, mapHoaDon1, lstSP1);
            modelHoaDon_HoaDon.setRowCount(0);
            lblHoaDon_HoaDon.setText("Hóa đơn " + lstCNhoadon.get(0).getMaHoaDon());
            int stt = 1;

            List<PhaCheLichSuViewModel> lstLoc = new ArrayList<>();
            lstLoc.add(new PhaCheLichSuViewModel(
                    lstCNhoadon.get(0).getMaHoaDon(),
                    "", lstCNhoadon.get(0).getTang(),
                    lstCNhoadon.get(0).getThoiGian(),
                    lstCNhoadon.get(0).getGhiChu(),
                    lstCNhoadon.get(0).getDanhSachSP()));
            for (PhaCheLichSuViewModel a : lstCNhoadon) {

                int check = 1;
                for (PhaCheLichSuViewModel b : lstLoc) {
                    if (a.getMaHoaDon() == b.getMaHoaDon()) {
                        b.setTenBan(a.getTenBan() + ", " + b.getTenBan());
                        check = -1;
                    }
                }
                if (check == 1) {
                    lstLoc.add(a);
                }
            }
            lstCNhoadon = lstLoc;
            for (PhaCheLichSuViewModel a : lstCNhoadon) {
                modelHoaDon_HoaDon.addRow(new Object[]{stt, a.getMaHoaDon(), a.getTenBan(),
                    a.getTang(), a.getThoiGian(), a.getGhiChu()});
                stt++;
            }
            tblHoaDon_HoaDon.setRowSelectionInterval(0, 0);
        } catch (Exception e) {
        }
    }

    //tổng hợp hóa đơn
    public void tongHopHoaDon() {
        try {
            // Map<String, Object> mapBan1 = LichSuServices.getBan();
            //Map<String, Object> mapHoaDon1 = LichSuServices.getHoaDon();
            //List<PhaCheLichSuDanhSachSanPhamViewmodel> lstSP1 = LichSuServices.getDSSP();
            //lstCNhoadon = HoaDonServices.getList(mapBan1, mapHoaDon1, lstSP1);

            List<PhaCheLichSuDanhSachSanPhamViewmodel> lstTongHop = new ArrayList<>();

            for (PhaCheLichSuViewModel a : lstCNhoadon) {
                for (PhaCheLichSuDanhSachSanPhamViewmodel b : a.getDanhSachSP()) {
                    lstTongHop.add(b);
                }
            }
            modelTongHopDonHANG.setRowCount(0);
            List<PhaCheLichSuDanhSachSanPhamViewmodel> lstCongDon = new ArrayList<>();
            lstCongDon.add(new PhaCheLichSuDanhSachSanPhamViewmodel(lstTongHop.get(0).getMaSanPham(),
                    lstTongHop.get(0).getTenSanPham(), lstTongHop.get(0).getSize(), 0));
            for (PhaCheLichSuDanhSachSanPhamViewmodel a : lstTongHop) {
                int check = 1;
                for (PhaCheLichSuDanhSachSanPhamViewmodel b : lstCongDon) {
                    if (a.getMaSanPham() == b.getMaSanPham() && a.getSize().equalsIgnoreCase(b.getSize())) {
                        b.setSoLuong(a.getSoLuong() + b.getSoLuong());
                        check = -1;
                    }
                }
                if (check == 1) {
                    lstCongDon.add(a);
                }

            }
            for (PhaCheLichSuDanhSachSanPhamViewmodel a : lstCongDon) {
                modelTongHopDonHANG.addRow(new Object[]{
                    a.getMaSanPham(), a.getTenSanPham(), a.getSize(), a.getSoLuong()
                });

            }
        } catch (Exception e) {
        }
    }

    public void fillTableDSSP(int maHoaDon) {
        try {
            Map<String, Object> mapBan = LichSuServices.getBan();
            Map<String, Object> mapHoaDon = LichSuServices.getHoaDon();
            List<PhaCheLichSuDanhSachSanPhamViewmodel> lstSP = LichSuServices.getDSSP();
            lst = LichSuServices.getList(mapBan, mapHoaDon, lstSP);
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
        modelHoaDon_DSSP.setRowCount(0);
        try {
            Map<String, Object> mapBan1 = LichSuServices.getBan();
            Map<String, Object> mapHoaDon1 = LichSuServices.getHoaDon();
            List<PhaCheLichSuDanhSachSanPhamViewmodel> lstSP1 = LichSuServices.getDSSP();

            lstCNhoadon = HoaDonServices.getList(mapBan1, mapHoaDon1, lstSP1);
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
        btnKhieuNaiHoTro = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        lblbanhang = new javax.swing.JLabel();
        jpnNenSanPham = new javax.swing.JPanel();
        lblSanPham = new javax.swing.JLabel();
        jpnNenHoaDon = new javax.swing.JPanel();
        lblHoaDon = new javax.swing.JLabel();
        jpnNenLichSu = new javax.swing.JPanel();
        lblLichSuDonHang = new javax.swing.JLabel();
        jpnNenDoiMatKhau = new javax.swing.JPanel();
        lblDoiMatKhau = new javax.swing.JLabel();
        jpnTong = new javax.swing.JPanel();
        jpnSanPham = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtTimKiemSPPhaChe = new javax.swing.JTextField();
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
        lblHoaDon_dssp1 = new javax.swing.JLabel();
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
        lblthietlap.setForeground(new java.awt.Color(45, 132, 252));
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

        lblbanhang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblbanhang.setForeground(new java.awt.Color(45, 132, 252));
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

        jpnNenSanPham.setBackground(new java.awt.Color(0, 65, 123));

        lblSanPham.setBackground(new java.awt.Color(0, 65, 123));
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

        javax.swing.GroupLayout jpnNenSanPhamLayout = new javax.swing.GroupLayout(jpnNenSanPham);
        jpnNenSanPham.setLayout(jpnNenSanPhamLayout);
        jpnNenSanPhamLayout.setHorizontalGroup(
            jpnNenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNenSanPhamLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblSanPham)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jpnNenSanPhamLayout.setVerticalGroup(
            jpnNenSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNenSanPhamLayout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(lblSanPham))
        );

        jpnMenu.add(jpnNenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 220, 40));

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
                .addGap(28, 28, 28)
                .addComponent(lblHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jpnNenHoaDonLayout.setVerticalGroup(
            jpnNenHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNenHoaDonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHoaDon)
                .addContainerGap())
        );

        jpnMenu.add(jpnNenHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 220, 40));

        jpnNenLichSu.setBackground(new java.awt.Color(0, 65, 123));

        lblLichSuDonHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblLichSuDonHang.setForeground(new java.awt.Color(255, 255, 255));
        lblLichSuDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/hoaDon.png"))); // NOI18N
        lblLichSuDonHang.setText("LỊCH SỬ ĐƠN HÀNG");
        lblLichSuDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLichSuDonHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpnNenLichSuLayout = new javax.swing.GroupLayout(jpnNenLichSu);
        jpnNenLichSu.setLayout(jpnNenLichSuLayout);
        jpnNenLichSuLayout.setHorizontalGroup(
            jpnNenLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNenLichSuLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblLichSuDonHang)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jpnNenLichSuLayout.setVerticalGroup(
            jpnNenLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNenLichSuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLichSuDonHang)
                .addContainerGap())
        );

        jpnMenu.add(jpnNenLichSu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 220, 40));

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
                .addGap(27, 27, 27)
                .addComponent(lblDoiMatKhau)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jpnNenDoiMatKhauLayout.setVerticalGroup(
            jpnNenDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNenDoiMatKhauLayout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(lblDoiMatKhau))
        );

        jpnMenu.add(jpnNenDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 220, 40));

        jpnTong.setBackground(new java.awt.Color(0, 153, 51));
        jpnTong.setLayout(new java.awt.CardLayout());

        jpnSanPham.setBackground(new java.awt.Color(255, 255, 255));
        jpnSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Sản phẩm");
        jpnSanPham.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tim3.png"))); // NOI18N
        jpnSanPham.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 50, 30, -1));

        txtTimKiemSPPhaChe.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txtTimKiemSPPhaChe.setForeground(new java.awt.Color(128, 128, 128));
        txtTimKiemSPPhaChe.setText("nhập tên sản phẩm...");
        txtTimKiemSPPhaChe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemSPPhaCheFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemSPPhaCheFocusLost(evt);
            }
        });
        txtTimKiemSPPhaChe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemSPPhaCheActionPerformed(evt);
            }
        });
        txtTimKiemSPPhaChe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSPPhaCheKeyReleased(evt);
            }
        });
        jpnSanPham.add(txtTimKiemSPPhaChe, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1210, 40));

        jpnHienThiSP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnHienThiSP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPane6.setViewportView(jpnHienThiSP);

        jpnSanPham.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1214, 660));

        jpnTong.add(jpnSanPham, "card2");

        jpnHoaDon.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Hoá đơn");

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
        tblHoaDon_HoaDon.setGridColor(new java.awt.Color(0, 65, 123));
        tblHoaDon_HoaDon.setRowHeight(40);
        tblHoaDon_HoaDon.setSelectionBackground(new java.awt.Color(51, 204, 255));
        tblHoaDon_HoaDon.setSelectionForeground(new java.awt.Color(255, 0, 0));
        tblHoaDon_HoaDon.setShowGrid(true);
        tblHoaDon_HoaDon.setSurrendersFocusOnKeystroke(true);
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
        tblHoaDon_DSSP.setGridColor(new java.awt.Color(0, 65, 123));
        tblHoaDon_DSSP.setRowHeight(40);
        tblHoaDon_DSSP.setSelectionBackground(new java.awt.Color(51, 204, 255));
        tblHoaDon_DSSP.setSelectionForeground(new java.awt.Color(255, 0, 0));
        tblHoaDon_DSSP.setShowGrid(true);
        tblHoaDon_DSSP.setSurrendersFocusOnKeystroke(true);
        jScrollPane3.setViewportView(tblHoaDon_DSSP);

        lblHoaDon_dssp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHoaDon_dssp.setForeground(new java.awt.Color(0, 102, 255));
        lblHoaDon_dssp.setText(".....");

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
        tblPhaCheTongDonHang.setGridColor(new java.awt.Color(0, 65, 123));
        tblPhaCheTongDonHang.setRowHeight(40);
        tblPhaCheTongDonHang.setSelectionBackground(new java.awt.Color(51, 204, 255));
        tblPhaCheTongDonHang.setSelectionForeground(new java.awt.Color(255, 0, 0));
        tblPhaCheTongDonHang.setShowGrid(true);
        tblPhaCheTongDonHang.setSurrendersFocusOnKeystroke(true);
        jScrollPane5.setViewportView(tblPhaCheTongDonHang);

        lblHoaDon_dssp1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHoaDon_dssp1.setForeground(new java.awt.Color(0, 102, 255));
        lblHoaDon_dssp1.setText("Danh sách sản phẩm hoá đơn:");

        javax.swing.GroupLayout jpnHoaDonLayout = new javax.swing.GroupLayout(jpnHoaDon);
        jpnHoaDon.setLayout(jpnHoaDonLayout);
        jpnHoaDonLayout.setHorizontalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnHoaDonLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(584, 584, 584))
                    .addGroup(jpnHoaDonLayout.createSequentialGroup()
                        .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHoaDon_HoaDon))
                        .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8))
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblHoaDon_dssp1)
                                .addGap(5, 5, 5)
                                .addComponent(lblHoaDon_dssp, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(470, 470, 470)))))
                .addContainerGap(592, Short.MAX_VALUE))
        );
        jpnHoaDonLayout.setVerticalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHoaDonLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoaDon_HoaDon)
                    .addComponent(lblHoaDon_dssp)
                    .addComponent(lblHoaDon_dssp1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                    .addGroup(jpnHoaDonLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5))
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
        tbllichsudonhang.setGridColor(new java.awt.Color(0, 65, 123));
        tbllichsudonhang.setRowHeight(40);
        tbllichsudonhang.setSelectionBackground(new java.awt.Color(51, 204, 255));
        tbllichsudonhang.setSelectionForeground(new java.awt.Color(255, 0, 0));
        tbllichsudonhang.setShowGrid(true);
        tbllichsudonhang.setSurrendersFocusOnKeystroke(true);
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
        tbllichsudanhsachsphoadon.setGridColor(new java.awt.Color(0, 65, 123));
        tbllichsudanhsachsphoadon.setRowHeight(40);
        tbllichsudanhsachsphoadon.setSelectionBackground(new java.awt.Color(51, 204, 255));
        tbllichsudanhsachsphoadon.setSelectionForeground(new java.awt.Color(255, 0, 0));
        tbllichsudanhsachsphoadon.setShowGrid(true);
        tbllichsudanhsachsphoadon.setSurrendersFocusOnKeystroke(true);
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
                .addContainerGap(594, Short.MAX_VALUE))
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
                .addContainerGap(1679, Short.MAX_VALUE))
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
                .addComponent(jpnTong, javax.swing.GroupLayout.DEFAULT_SIZE, 1879, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseClicked

    }//GEN-LAST:event_btnDangXuatMouseClicked

    private void btnKhieuNaiHoTroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhieuNaiHoTroMouseClicked

        new HoTroKhachHang().setVisible(true);
    }//GEN-LAST:event_btnKhieuNaiHoTroMouseClicked

    private void lblDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMatKhauMouseClicked
        jpnNenDoiMatKhau.setBackground(new Color(0, 88, 166));
        jpnNenSanPham.setBackground(new Color(0, 65, 123));
        jpnNenHoaDon.setBackground(new Color(0, 65, 123));
        jpnNenLichSu.setBackground(new Color(0, 65, 123));

        new DoiMatKhau(maTaiKhoan).setVisible(true);
    }//GEN-LAST:event_lblDoiMatKhauMouseClicked

    private void lblthietlapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblthietlapMouseClicked

    }//GEN-LAST:event_lblthietlapMouseClicked

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

    private void lblLichSuDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLichSuDonHangMouseClicked
        fillTableLichSuHoaDon();
        int ma = (int) tbllichsudonhang.getValueAt(0, 0);
        fixLichSu(ma);

        jpnNenLichSu.setBackground(new Color(0, 88, 166));
        jpnNenSanPham.setBackground(new Color(0, 65, 123));
        jpnNenHoaDon.setBackground(new Color(0, 65, 123));
        jpnNenDoiMatKhau.setBackground(new Color(0, 65, 123));

        jpnLichSu.setVisible(true);
        jpnSanPham.setVisible(false);
        jpnHoaDon.setVisible(false);

    }//GEN-LAST:event_lblLichSuDonHangMouseClicked

    private void lblthietlapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblthietlapMouseEntered

    }//GEN-LAST:event_lblthietlapMouseEntered

    private void lblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoaDonMouseClicked
        jpnNenHoaDon.setBackground(new Color(0, 88, 166));
        jpnNenSanPham.setBackground(new Color(0, 65, 123));
        jpnNenLichSu.setBackground(new Color(0, 65, 123));
        jpnNenDoiMatKhau.setBackground(new Color(0, 65, 123));

        jpnHoaDon.setVisible(true);
        jpnSanPham.setVisible(false);
        jpnLichSu.setVisible(false);
    }//GEN-LAST:event_lblHoaDonMouseClicked

    private void lblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseClicked
        // TODO add your handling code here:

        jpnNenSanPham.setBackground(new Color(0, 88, 166));
        jpnNenHoaDon.setBackground(new Color(0, 65, 123));
        jpnNenLichSu.setBackground(new Color(0, 65, 123));
        jpnNenDoiMatKhau.setBackground(new Color(0, 65, 123));

        jpnSanPham.setVisible(true);
        jpnHoaDon.setVisible(false);
        jpnLichSu.setVisible(false);
    }//GEN-LAST:event_lblSanPhamMouseClicked
    public void fixLichSu(int maHoaDon) {
        modelLichSuDanhSachSp.setRowCount(0);
        try {
            java.sql.Connection con = DBConnect.getConnect();
            String lenh = "SELECT SanPham.MaSanPham,SanPham.TenSanPham,ChiTietSanPham.Size,ChiTietHoaDon.soLuong FROM ChiTietHoaDon LEFT JOIN ChiTietSanPham ON ChiTietHoaDon.MaChiTietSanPham=ChiTietSanPham.MaChiTietSanPham LEFT JOIN SanPham ON ChiTietSanPham.MaSanPham=SanPham.MaSanPham WHERE MaHoaDon= " + maHoaDon;
            java.sql.Statement st = con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(lenh);
            List<PhaCheLichSuDanhSachSanPhamViewmodel> lst = new ArrayList<>();
            while (rs.next()) {
                lst.add(new PhaCheLichSuDanhSachSanPhamViewmodel(rs.getInt(1),
                        rs.getString(2), rs.getString(3),
                        rs.getInt(4)));
            }
            int stt = 1;
            for (PhaCheLichSuDanhSachSanPhamViewmodel a : lst) {
                modelLichSuDanhSachSp.addRow(new Object[]{
                    stt, a.getMaSanPham(), a.getTenSanPham(), a.getSize(), a.getSoLuong()
                });
                stt++;
            }

        } catch (Exception e) {
        }
    }
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
        int maHoaDon = (int) tbllichsudonhang.getValueAt(index, 0);
        fixLichSu(maHoaDon);
        txtlichsuGhiChu.setText(tbllichsudonhang.getValueAt(index, 4) + "");
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        modelHoaDon_DSSP.setRowCount(0);
        if (lblHoaDon_dssp.getText().equalsIgnoreCase(".....")) {
            JOptionPane.showMessageDialog(null, "Không có hóa đơn nào");
            return;
        }
        try {
            capNhatTrangThaiHD();
        } catch (Exception e) {
            e.printStackTrace();
        }
        lblHoaDon_dssp.setText("");
        txtGhiChuHoaDon.setText("");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTimKiemSPPhaCheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSPPhaCheKeyReleased
        // TODO add your handling code here:
        jpnHienThiSP.removeAll();

        List<PhaCheSanPhamViewModel> lstPCSPViewModel = SanPhamPCService.getSanPhamTheoTen(txtTimKiemSPPhaChe.getText());

        List<PhaCheSanPhamJPanel> LstPCSPJPanel = new ArrayList<>();

        for (PhaCheSanPhamViewModel PCSPView : lstPCSPViewModel) {

            LstPCSPJPanel.add(new PhaCheSanPhamJPanel(PCSPView));

        }

        GridBagLayout layout = new GridBagLayout();

        jpnHienThiSP.setLayout(layout);

        GridBagConstraints gcb = new GridBagConstraints();

        int x = 0;
        int y = (int) Math.ceil(LstPCSPJPanel.size() / 5);

        for (PhaCheSanPhamJPanel PCSPJPanel : LstPCSPJPanel) {
            gcb.anchor = GridBagConstraints.FIRST_LINE_START;
            gcb.gridx = x;
            gcb.gridy = y;
            gcb.weightx = 0;
            gcb.weighty = 1;
            //gcb.fill=GridBagConstraints.NONE;
            gcb.gridheight = 1;
            gcb.gridwidth = 1;
            gcb.insets = new Insets(10, 10, 10, 10);

            jpnHienThiSP.add(PCSPJPanel, gcb);
            jpnHienThiSP.updateUI();
            x++;
            if (x == 5) {
                x = 0;
                y++;
            }
        }

        if (LstPCSPJPanel.isEmpty()) {
            jpnHienThiSP.removeAll();
            jpnHienThiSP.updateUI();
        }
        if (txtTimKiemSPPhaChe.equals("")) {
            LoadSanPham();
        }

    }//GEN-LAST:event_txtTimKiemSPPhaCheKeyReleased

    private void txtTimKiemSPPhaCheFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemSPPhaCheFocusGained
        // TODO add your handling code here:
        //txtTimKiemSPPhaChe
        if (txtTimKiemSPPhaChe.getText().equalsIgnoreCase("nhập tên sản phẩm...")) {
            txtTimKiemSPPhaChe.setText(null);
            txtTimKiemSPPhaChe.requestFocus();
            Uhelper.removePlayhoder(txtTimKiemSPPhaChe);

        }
    }//GEN-LAST:event_txtTimKiemSPPhaCheFocusGained

    private void txtTimKiemSPPhaCheFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemSPPhaCheFocusLost
        // TODO add your handling code here:
        if (txtTimKiemSPPhaChe.getText().length() == 0) {
            Uhelper.adPlayhoder(txtTimKiemSPPhaChe);
            txtTimKiemSPPhaChe.setText("nhập tên sản phẩm...");
        }
    }//GEN-LAST:event_txtTimKiemSPPhaCheFocusLost

    private void txtTimKiemSPPhaCheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemSPPhaCheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemSPPhaCheActionPerformed

    public void showGhiChu(int index) {
        txtlichsuGhiChu.setText(lst.get(index).getGhiChu());
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String maTaiKhoan = ""; // Lấy mã tài khoản từ giao diện đăng nhập
                TraSua_PC traSua_PC = new TraSua_PC(maTaiKhoan);
                traSua_PC.setMaTaiKhoan(maTaiKhoan);
                traSua_PC.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnKhieuNaiHoTro;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPanel jpnHienThiSP;
    private javax.swing.JPanel jpnHoaDon;
    private javax.swing.JPanel jpnKhieuNaiHoTro;
    private javax.swing.JPanel jpnLichSu;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnNenDoiMatKhau;
    private javax.swing.JPanel jpnNenHoaDon;
    private javax.swing.JPanel jpnNenLichSu;
    private javax.swing.JPanel jpnNenSanPham;
    private javax.swing.JPanel jpnSanPham;
    private javax.swing.JPanel jpnTong;
    private javax.swing.JLabel lblDoiMatKhau;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblHoaDon_HoaDon;
    private javax.swing.JLabel lblHoaDon_dssp;
    private javax.swing.JLabel lblHoaDon_dssp1;
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
    private javax.swing.JTextField txtTimKiemSPPhaChe;
    private javax.swing.JTextArea txtlichsuGhiChu;
    // End of variables declaration//GEN-END:variables
}
