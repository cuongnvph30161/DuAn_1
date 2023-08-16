/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainmodel.ChiTietHoaDonDomainModel;
import interfaceservices.IMaGiamGiaService;
import interfaceservices.INhanVienHoaDonServices;
import services.nhanVien.QuanLyBanService;
import services.nhanVien.SanPhamService;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import services.BanService;
import services.MaGiamGiaService;
import services.NhanVienBanService;
import services.NhanVienHoaDonServices;
import services.NhanVienService;
import utilities.DPlaceHolder;
import utilities.NhanVienBanJpanel;
import utilities.NhanVienHoaDon_ChiTiet;
import utilities.Uhelper;
import utilities.XImages;
import viewmodel.NhanVienBanViewModel;
import viewmodel.NhanVienHoaDonViewModel;
import viewmodel.PhaCheLichSuDanhSachSanPhamViewmodel;
import viewmodel.TaiKhoanViewModel;
import viewmodel.TenBanViewModel;
import viewmodel.defaultViewModel.BanViewModel;
import viewmodel.defaultViewModel.ChiTietHoaDonViewModel;
import viewmodel.defaultViewModel.ChiTietSanPhamViewModel;
import viewmodel.nhanVien.sanPham.Order;
import views.DangNhap;
import views.DangXuat;
import views.DoiMatKhau;
import views.element.ChiTietHoaDon;
import views.element.SanPham;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.GroupLayout.Alignment;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;

public class TraSua_NV extends javax.swing.JFrame {
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnApDungBan;
	private javax.swing.JButton btnDangXuat;
	private javax.swing.JButton btnKhieuNaiHoTro;
	private javax.swing.JButton btnThanhToanBan;
	private javax.swing.JButton btnThemHoaDonBan;
	private javax.swing.JComboBox<String> cboNhanVienHDTrangThai;
	private javax.swing.JButton jButton3;
	private javax.swing.JComboBox<String> cboTang;
	private javax.swing.JLabel jLabel10;
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
	private javax.swing.JLabel jLabel27;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel35;
	private javax.swing.JLabel jLabel36;
	private javax.swing.JLabel jLabel38;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel pnDanhSachSanPham;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JScrollPane jScrollPane6;
	private javax.swing.JScrollPane jScrollPane7;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JTable tblDanhSachSanPham;
	private javax.swing.JTextArea txtGhiChu;
	private javax.swing.JTextField txtSearchTenSanPham;
	private com.toedter.calendar.JDateChooser jdcDen;
	private com.toedter.calendar.JDateChooser jdcTu;
	private javax.swing.JPanel pnDanhSachBan;
	private javax.swing.JPanel pnTrong;
	private javax.swing.JPanel jpnMenu;
	private javax.swing.JPanel jpnQuanLyBan;
	private javax.swing.JPanel jpnThoiGian1;
	private javax.swing.JPanel jpnTrangThai;
	private javax.swing.JLabel lblBanHang;
	private javax.swing.JLabel lblDoiMatKhau;
	private javax.swing.JLabel lblGiaSauKhiGiam;
	private javax.swing.JLabel lblHoaDon;
	private javax.swing.JLabel lblNVTien;
	private javax.swing.JLabel lblNVTrangDau;
	private javax.swing.JLabel lblNVlui;
	private javax.swing.JLabel lblNhanVienQLBan;
	private javax.swing.JLabel lblNhanVienTrangThaiTrang;
	private javax.swing.JLabel lblQuanLyBan;
	private javax.swing.JLabel lblSanPham;
	private javax.swing.JLabel lblThietLap;
	private javax.swing.JLabel lblTimKiem;
	private javax.swing.JLabel lblTongTien;
	private javax.swing.JLabel lblTraSua;
	private javax.swing.JPanel pnHoaDon;
	private javax.swing.JPanel pnQuanLyBan;
	private javax.swing.JPanel pnSanPham;
	private javax.swing.JPanel pnTong;
	private javax.swing.JTable tblNhanVienBan;
	private javax.swing.JTable tblNhanVienHoaDon;
	private javax.swing.JTextField txtNhanVienNhapMaHD;
	private JSpinner txtVoucher;
	private JPanel pnTang;
	private services.nhanVien.QuanLyBanService svQuanLyBan = new QuanLyBanService();
	private services.defaultService.BanService svBan = new services.defaultService.BanService();
	private services.nhanVien.SanPhamService svQLSanPham = new SanPhamService();
	// End of variables declaration//GEN-END:variables

	int demTrang = 1;
	List<NhanVienHoaDonViewModel> lstTruyenTrang = new ArrayList<>();
	Map<Integer, List<NhanVienHoaDonViewModel>> mapPhanTrang = new HashMap<>();
	INhanVienHoaDonServices NVHoaDonSv = new NhanVienHoaDonServices();
	private String maTaiKhoan;
	int soTrang = 1;

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;

	}

	DefaultTableModel modelNVhoaDon = new DefaultTableModel();
	List<ChiTietHoaDonDomainModel> listCTHD = new ArrayList<>();
	Map<Integer, String> mapTenNV = new HashMap<>();
	Map<Integer, String> mapTenBan = new HashMap<>();
	Map<Integer, Object> maGiamGia = new HashMap<>();
	List<PhaCheLichSuDanhSachSanPhamViewmodel> ListDSSP = new ArrayList<>();
	List<NhanVienHoaDonViewModel> listNhanVienHDView = new ArrayList<>();

	private NhanVienService svNhanVien = new NhanVienService();
	private int maNhanVien;

	////////////////////////////////////////////////////////////////
	BanService banSe = new BanService();
	IMaGiamGiaService iMGGSe = new MaGiamGiaService();
	NhanVienBanService nvBanSe = new NhanVienBanService();
	DefaultTableModel tableModelBan = new DefaultTableModel();

	public TraSua_NV(String maTaiKhoan) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initComponents();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		loadAllComponent();
		modelNVhoaDon = (DefaultTableModel) tblNhanVienHoaDon.getModel();
		init();
		jdcTu.setIcon(new ImageIcon(getClass().getResource("/Img/date_1.png")));
		jdcDen.setIcon(new ImageIcon(getClass().getResource("/Img/date_1.png")));
		getContentPane().setLayout(null);
		getContentPane().add(jpnMenu);
		getContentPane().add(pnTong);
		layDuLieuNVHD();
		phanTrang();
		truyenTrang(1);
		maNhanVien = svNhanVien.getByIdAccount(maTaiKhoan);
		loadView("pnQuanLyBan");
		DPlaceHolder.addPlaceHolder(txtSearchTenSanPham, "Tìm kiếm theo tên sản phẩm");
		dmk=new DoiMatKhau(maTaiKhoan);
		htkh=new HoTroKhachHang();

	}

	public void layDuLieuNVHD() {
		listCTHD = NVHoaDonSv.getlistCTHD();
		mapTenNV = NVHoaDonSv.mapTenNV();
		mapTenBan = NVHoaDonSv.mapTenBan();
		maGiamGia = NVHoaDonSv.mapMaGiamGia();
		ListDSSP = NVHoaDonSv.getDSSP();
		listNhanVienHDView = NVHoaDonSv.getList(ListDSSP, mapTenNV, mapTenBan, listCTHD, maGiamGia);

	}

	List<NhanVienBanViewModel> listNVban = nvBanSe.getAllNhanVienBan();

	private void FillTableBan() {
		String chiTiet = " Xem ";

		tableModelBan = (DefaultTableModel) tblNhanVienBan.getModel();
		tableModelBan.setRowCount(0);
		for (NhanVienBanViewModel nv : listNVban) {
			tableModelBan.addRow(new Object[] { nv.getMaHoaDon(), nv.getThoiGian(), nv.getTongThanhToan(),
					nv.getTrangThaiOrder() == 1 ? "Đã làm" : "Đang làm ", chiTiet });

		}
	}

	public void truyenTrang(int index) {
		List<NhanVienHoaDonViewModel> lstTruyenTrang = new ArrayList<>();
		lstTruyenTrang = mapPhanTrang.get(index);
		lblNhanVienTrangThaiTrang.setText("trang " + index + "/" + soTrang);
		fillTableNVHD(lstTruyenTrang);
	}

	public void phanTrang() {
		if (listNhanVienHDView.size() > 100) {
			double a = listNhanVienHDView.size();
			double b = 100;
			soTrang = (int) Math.ceil(a / b);

		} else {
			soTrang = 1;
		}
		if (soTrang > 1) {
			for (int i = 1; i <= soTrang; i++) {
				List<NhanVienHoaDonViewModel> listTrang = new ArrayList<>();
				if (i == soTrang) {
					int doDaiTrangCuoi = listNhanVienHDView.size() - (soTrang - 1) * 100;
					for (int j = ((soTrang - 1) * 100); j < (doDaiTrangCuoi + (((soTrang - 1) * 100))); j++) {
						listTrang.add(listNhanVienHDView.get(j));
					}

				} else {
					if (i == 1) {
						for (int k = 0; k < 100; k++) {
							listTrang.add(listNhanVienHDView.get(k));
						}
					} else {
						for (int h = (i - 1) * 100; h <= ((i - 1) * 100 + 99); h++) {
							listTrang.add(listNhanVienHDView.get(h));
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
			modelNVhoaDon.addRow(new Object[] { a.getMaHoaDon(), a.getMaNguoiTao(), a.getThoiGian(),
					a.getTongThanhToan(), trangThai, a.getGhiChu(), "Chi tiết" });
		}

	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private DoiMatKhau dmk=null;
	private HoTroKhachHang htkh=null;
	private void initComponents() {
		
		jpnMenu = new javax.swing.JPanel();
		jpnMenu.setBounds(10, 0, 221, 845);
		lblTraSua = new javax.swing.JLabel();
		lblTraSua.setBounds(0, 0, 221, 70);
		lblThietLap = new javax.swing.JLabel();
		lblThietLap.setBounds(10, 361, 175, 20);
		lblSanPham = new javax.swing.JLabel();
		lblSanPham.setBackground(new Color(8, 26, 81));
		lblSanPham.setOpaque(true);
		lblSanPham.setBounds(36, 210, 185, 40);
		lblQuanLyBan = new javax.swing.JLabel();
		lblQuanLyBan.setBackground(new Color(8, 26, 81));
		lblQuanLyBan.setOpaque(true);
		lblQuanLyBan.setBounds(36, 145, 185, 40);
		lblHoaDon = new javax.swing.JLabel();
		lblHoaDon.setBackground(new Color(8, 26, 81));
		lblHoaDon.setOpaque(true);
		lblHoaDon.setBounds(36, 270, 185, 40);
		lblDoiMatKhau = new javax.swing.JLabel();
		lblDoiMatKhau.setBackground(new Color(8, 26, 81));
		lblDoiMatKhau.setOpaque(true);
		lblDoiMatKhau.setBounds(37, 420, 185, 40);
		btnKhieuNaiHoTro = new javax.swing.JButton();
		btnKhieuNaiHoTro.setFocusable(false);
		btnKhieuNaiHoTro.setFocusTraversalKeysEnabled(false);
		btnKhieuNaiHoTro.setFocusPainted(false);
		btnKhieuNaiHoTro.setRolloverEnabled(false);
		btnKhieuNaiHoTro.setRequestFocusEnabled(false);
		btnKhieuNaiHoTro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				htkh.setVisible(true);
			}
		});
		btnKhieuNaiHoTro.setMargin(new Insets(2, 0, 2, 0));
		btnKhieuNaiHoTro.setBounds(25, 680, 170, 40);
		btnDangXuat = new javax.swing.JButton();
		btnDangXuat.setBounds(25, 740, 170, 40);
		jSeparator2 = new javax.swing.JSeparator();
		jSeparator2.setBounds(0, 70, 220, 10);
		jSeparator1 = new javax.swing.JSeparator();
		jSeparator1.setBounds(0, 630, 220, 10);
		lblBanHang = new javax.swing.JLabel();
		lblBanHang.setBounds(0, 101, 185, 20);
		pnTong = new javax.swing.JPanel();
		pnTong.setBounds(237, 0, 1303, 845);
		pnQuanLyBan = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jLabel2.setBounds(1303, 25, 131, 35);
		jpnQuanLyBan = new javax.swing.JPanel();
		jpnQuanLyBan.setBounds(10, 0, 1243, 795);
		lblNhanVienQLBan = new javax.swing.JLabel();
		lblNhanVienQLBan.setBounds(69, 19, 102, 25);
		jScrollPane6 = new javax.swing.JScrollPane();
		jScrollPane6.setLocation(69, 152);
		jScrollPane6.setSize(new Dimension(480, 633));
		jScrollPane6.setPreferredSize(new Dimension(480, 900));
		pnDanhSachBan = new javax.swing.JPanel();
		pnDanhSachBan.setPreferredSize(new Dimension(450, 800));
		pnDanhSachBan.setSize(new Dimension(400, 1000));
		jScrollPane7 = new javax.swing.JScrollPane();
		jScrollPane7.setBounds(570, 159, 673, 275);
		tblNhanVienBan = new javax.swing.JTable();
		tblNhanVienBan.setUpdateSelectionOnSort(false);
		tblNhanVienBan.setShowVerticalLines(false);
		tblNhanVienBan.setShowHorizontalLines(false);
		tblNhanVienBan.setShowGrid(false);

		tblNhanVienBan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblNhanVienBan.getSelectedColumn() == 4) {
					ChiTietHoaDon cthd = new ChiTietHoaDon(lstHoaDon.get(tblNhanVienBan.getSelectedRow()),
							(Frame) (rootPane.getParent()));
					cthd.setLocationRelativeTo(rootPane);
					cthd.setVisible(true);
				}
			}
		});
		txtVoucher = new JSpinner() {
//					@Override
//					public void paste() {
//					}
		};

		txtVoucher.setModel(new SpinnerListModel(iMGGSe.getLstID()));
		txtVoucher.setBounds(570, 464, 500, 38);
		jLabel35 = new javax.swing.JLabel();
		jLabel35.setBounds(570, 444, 54, 20);
		btnApDungBan = new javax.swing.JButton();
		btnApDungBan.setMargin(new Insets(2, 2, 2, 2));
		btnApDungBan.setBounds(1136, 464, 107, 38);
		jLabel36 = new javax.swing.JLabel();
		jLabel36.setBounds(572, 574, 169, 25);
		lblTongTien = new javax.swing.JLabel();
		lblTongTien.setBounds(790, 574, 272, 25);
		jLabel38 = new javax.swing.JLabel();
		jLabel38.setBounds(570, 628, 251, 25);
		lblGiaSauKhiGiam = new javax.swing.JLabel();
		lblGiaSauKhiGiam.setBounds(788, 628, 371, 25);
		btnThanhToanBan = new javax.swing.JButton();
		btnThanhToanBan.setBounds(572, 722, 179, 44);
		btnThemHoaDonBan = new javax.swing.JButton();
		btnThemHoaDonBan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadView("pnSanPham");
			}
		});
		btnThemHoaDonBan.setBounds(885, 722, 174, 44);
		pnTang = new javax.swing.JPanel();
		pnTang.setBounds(69, 86, 800, 41);
		pnSanPham = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jLabel3.setBounds(40, 20, 85, 25);
		jPanel1 = new javax.swing.JPanel();
		jPanel1.setBounds(34, 84, 387, 704);
		jLabel6 = new javax.swing.JLabel();
		jLabel6.setBounds(12, 37, 66, 13);
		cboTang = new javax.swing.JComboBox<>();
		cboTang.addItem("Mang về");
		cboTang.addItem("1");
		cboTang.addItem("2");
		cboTang.addItem("3");
		cboTang.addItem("4");
		setDefaultCloseOperation(_tangDuocChon);
		cboTang.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int tang = cboTang.getSelectedIndex();
				_tangDuocChon = tang;
				loadDanhSachBan(_tangDuocChon);
			}
		});

		cboTang.setBounds(116, 34, 259, 19);
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setBackground(new Color(255, 255, 255));
		jScrollPane1.setRequestFocusEnabled(false);
		jScrollPane1.setBounds(12, 306, 363, 145);
		tblDanhSachSanPham = new javax.swing.JTable();

		tblDanhSachSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblDanhSachSanPham.getSelectedRow();
				if (tblDanhSachSanPham.getSelectedColumn() == 4) {
					((DefaultTableModel) tblDanhSachSanPham.getModel()).removeRow(row);

				}
			}
		});

		jScrollPane2 = new javax.swing.JScrollPane();
		jScrollPane2.setBackground(Color.WHITE);
		jScrollPane2
				.setBorder(new TitledBorder(null, "Ghi ch\u00FA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jScrollPane2.setBounds(12, 461, 363, 71);
		txtGhiChu = new javax.swing.JTextArea();
		jLabel10 = new javax.swing.JLabel();
		jLabel10.setBounds(12, 615, 156, 13);
		jButton3 = new javax.swing.JButton();
		jButton3.setMargin(new Insets(2, 2, 2, 2));
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblBanDuocChon.getRowCount() < 1 || tblDanhSachSanPham.getRowCount() < 1) {

					JOptionPane.showMessageDialog(rootPane.getParent(), "Vui lòng chọn thêm bàn hoặc hóa đơn", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Order od = new Order();
					od.setMaNhanVien(maNhanVien);
					int dichVuPhatSinh = 0;
					try {
						dichVuPhatSinh = Integer.parseInt(txtDichVuPhatSinh.getText());
					} catch (Exception e2) {
						// TODO: handle exception
					}
					;
					od.setDichVuPhatSinh(dichVuPhatSinh);
					od.setGhiChu(txtGhiChu.getText());
					od.setLstMaBan(lstBanDuocChon.stream().toList());
					List<ChiTietHoaDonViewModel> lstCTHD = new ArrayList<ChiTietHoaDonViewModel>();
					for (int i = 0; i < tblDanhSachSanPham.getRowCount(); i++) {
						ChiTietHoaDonViewModel cthd = new ChiTietHoaDonViewModel();
						cthd.setMaChiTietSanPham((int) tblDanhSachSanPham.getValueAt(i, 1));
						cthd.setSoLuong((int) tblDanhSachSanPham.getValueAt(i, 3));
						cthd.setGia(
								BigDecimal.valueOf(Double.parseDouble(tblDanhSachSanPham.getValueAt(i, 2).toString())));
						lstCTHD.add(cthd);
					}

					od.setLstChiTietHoaDonViewModels(lstCTHD);
					System.out.println(od);
					svQLSanPham.themHoaDon(od);
//				od.set
//				od.setDichVuPhatSinh(Integer);
//				svQLSanPham.themHoaDon(new o)
					loadView("pnQuanLyBan");
				}
			}
		});
		jButton3.setBounds(258, 648, 113, 25);
		jLabel20 = new javax.swing.JLabel();
		jLabel20.setBounds(12, 283, 125, 13);
		jLabel21 = new javax.swing.JLabel();
		jLabel21.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((DefaultTableModel) tblDanhSachSanPham.getModel()).setRowCount(0);
			}
		});
		jLabel21.setBounds(319, 280, 56, 16);
		jScrollPane4 = new javax.swing.JScrollPane();
		jScrollPane4.setBounds(442, 137, 840, 660);
		pnDanhSachSanPham = new javax.swing.JPanel();
		pnTrong = new javax.swing.JPanel();
		pnHoaDon = new javax.swing.JPanel();
		jLabel13 = new javax.swing.JLabel();
		jLabel13.setBounds(40, 20, 88, 25);
		jScrollPane5 = new javax.swing.JScrollPane();
		jScrollPane5.setBounds(310, 150, 890, 530);
		tblNhanVienHoaDon = new javax.swing.JTable();
		jpnTrangThai = new javax.swing.JPanel();
		jpnTrangThai.setBounds(20, 350, 270, 120);
		jLabel14 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		cboNhanVienHDTrangThai = new javax.swing.JComboBox<>();
		jpnThoiGian1 = new javax.swing.JPanel();
		jpnThoiGian1.setBounds(20, 150, 270, 140);
		jLabel16 = new javax.swing.JLabel();
		jLabel17 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		jdcTu = new com.toedter.calendar.JDateChooser();
		jdcDen = new com.toedter.calendar.JDateChooser();
		lblNVTrangDau = new javax.swing.JLabel();
		lblNVTrangDau.setBounds(340, 740, 34, 25);
		lblNVlui = new javax.swing.JLabel();
		lblNVlui.setBounds(390, 740, 19, 25);
		lblNhanVienTrangThaiTrang = new javax.swing.JLabel();
		lblNhanVienTrangThaiTrang.setBounds(440, 740, 120, 25);
		lblNVTien = new javax.swing.JLabel();
		lblNVTien.setBounds(580, 740, 18, 25);
		jLabel27 = new javax.swing.JLabel();
		jLabel27.setBounds(620, 740, 33, 25);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Hệ thống quản lý quán trà sữa ToTo");
		setBounds(new java.awt.Rectangle(0, 0, 1920, 1080));
		setMinimumSize(new java.awt.Dimension(1920, 1080));

		jpnMenu.setBackground(new Color(8, 26, 81));
		jpnMenu.setLayout(null);

		lblTraSua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		lblTraSua.setForeground(new java.awt.Color(255, 255, 255));
		lblTraSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logoXoaNen2.png"))); // NOI18N
		lblTraSua.setText("TRÀ SỮA TOTO");
		jpnMenu.add(lblTraSua);

		lblThietLap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		lblThietLap.setForeground(new Color(81, 203, 255));
		lblThietLap.setText("THIẾT LẬP");
		lblThietLap.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblThietLapMouseClicked(evt);
			}
		});
		jpnMenu.add(lblThietLap);

		lblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
		lblSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sanPham.png"))); // NOI18N
		lblSanPham.setText("  SẢN PHẨM");
		lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblSanPhamMouseClicked(evt);
			}
		});
		jpnMenu.add(lblSanPham);

		lblQuanLyBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		lblQuanLyBan.setForeground(new java.awt.Color(255, 255, 255));
		lblQuanLyBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ban2.png"))); // NOI18N
		lblQuanLyBan.setText("  BÀN");
		lblQuanLyBan.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblQuanLyBanMouseClicked(evt);
			}
		});
		jpnMenu.add(lblQuanLyBan);

		lblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		lblHoaDon.setForeground(new java.awt.Color(255, 255, 255));
		lblHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/hoaDon.png"))); // NOI18N
		lblHoaDon.setText("  HÓA ĐƠN");
		lblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblHoaDonMouseClicked(evt);
			}
		});
		jpnMenu.add(lblHoaDon);

		lblDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		lblDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
		lblDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/doiMatKhau.png"))); // NOI18N
		lblDoiMatKhau.setText("  ĐỔI MẬT KHẨU");
		lblDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblDoiMatKhauMouseClicked(evt);
			}
		});
		jpnMenu.add(lblDoiMatKhau);

		btnKhieuNaiHoTro.setBackground(new java.awt.Color(2, 82, 155));
		btnKhieuNaiHoTro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		btnKhieuNaiHoTro.setForeground(new java.awt.Color(255, 255, 255));
		btnKhieuNaiHoTro.setText("KHIẾU NẠI HỖ TRỢ ?");
		
		jpnMenu.add(btnKhieuNaiHoTro);

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
		jpnMenu.add(btnDangXuat);
		jpnMenu.add(jSeparator2);
		jpnMenu.add(jSeparator1);

		lblBanHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		lblBanHang.setForeground(new Color(81, 203, 255));
		lblBanHang.setText("  BÁN HÀNG");
		lblBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblBanHangMouseClicked(evt);
			}
		});
		jpnMenu.add(lblBanHang);

		pnTong.setLayout(new java.awt.CardLayout());

		pnQuanLyBan.setBackground(new Color(240, 240, 240));

		jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
		jLabel2.setText("Bán Hàng");

		jpnQuanLyBan.setBackground(new Color(240, 240, 240));
		jpnQuanLyBan.setLayout(null);

		lblNhanVienQLBan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		lblNhanVienQLBan.setText("Quản lý bàn");
		jpnQuanLyBan.add(lblNhanVienQLBan);

		jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 1",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12))); // NOI18N
		jScrollPane6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jScrollPane6.setViewportView(pnDanhSachBan);
		pnDanhSachBan.setLayout(null);

		jpnQuanLyBan.add(jScrollPane6);

		tblNhanVienBan.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		tblNhanVienBan.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Mã hóa đơn", "Thời gian", "Tổng hóa đơn", "Trạng thái ", "Chi tiết" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		jScrollPane7.setViewportView(tblNhanVienBan);

		jpnQuanLyBan.add(jScrollPane7);
		jpnQuanLyBan.add(txtVoucher);

		jLabel35.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
		jLabel35.setText("Voucher");
		jpnQuanLyBan.add(jLabel35);

		btnApDungBan.setBackground(new java.awt.Color(45, 132, 252));
		btnApDungBan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnApDungBan.setForeground(new java.awt.Color(255, 255, 255));
		btnApDungBan.setText("Áp dụng");
		btnApDungBan.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnApDungBanActionPerformed(evt);
			}
		});
		jpnQuanLyBan.add(btnApDungBan);

		jLabel36.setFont(new Font("Segoe UI", Font.BOLD, 14)); // NOI18N
		jLabel36.setText("Tổng sản phẩm:");
		jpnQuanLyBan.add(jLabel36);

		lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 14)); // NOI18N
		lblTongTien.setText("0 VNĐ");
		jpnQuanLyBan.add(lblTongTien);

		jLabel38.setFont(new Font("Segoe UI", Font.BOLD, 14)); // NOI18N
		jLabel38.setText("Tổng thanh toán (đã giảm):");
		jpnQuanLyBan.add(jLabel38);

		lblGiaSauKhiGiam.setFont(new Font("Segoe UI", Font.BOLD, 14)); // NOI18N
		lblGiaSauKhiGiam.setText("0 VNĐ");
		jpnQuanLyBan.add(lblGiaSauKhiGiam);

		btnThanhToanBan.setBackground(new java.awt.Color(45, 132, 252));
		btnThanhToanBan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnThanhToanBan.setForeground(new java.awt.Color(255, 255, 255));
		btnThanhToanBan.setText("Thanh toán");
		btnThanhToanBan.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThanhToanBanActionPerformed(evt);
			}
		});
		jpnQuanLyBan.add(btnThanhToanBan);

		btnThemHoaDonBan.setBackground(new java.awt.Color(45, 132, 252));
		btnThemHoaDonBan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btnThemHoaDonBan.setForeground(new java.awt.Color(255, 255, 255));
		btnThemHoaDonBan.setText("Thêm hóa đơn");
		jpnQuanLyBan.add(btnThemHoaDonBan);

		jpnQuanLyBan.add(pnTang);
		pnTang.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		pnTong.add(pnQuanLyBan, "pnQuanLyBan");
		pnQuanLyBan.setLayout(null);
		pnQuanLyBan.add(jpnQuanLyBan);

		JLabel lblTngDchV = new JLabel();
		lblTngDchV.setText("Tổng dịch vụ phát sinh:");
		lblTngDchV.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTngDchV.setBounds(572, 523, 210, 25);
		jpnQuanLyBan.add(lblTngDchV);

		lblTongDichVuPhatSinh = new JLabel();
		lblTongDichVuPhatSinh.setText("0 VNĐ");
		lblTongDichVuPhatSinh.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTongDichVuPhatSinh.setBounds(790, 523, 371, 25);
		jpnQuanLyBan.add(lblTongDichVuPhatSinh);

		lblApDungVoucher = new JLabel();
		lblApDungVoucher.setText("0 VNĐ");
		lblApDungVoucher.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblApDungVoucher.setBounds(1108, 574, 371, 25);
		jpnQuanLyBan.add(lblApDungVoucher);

		lblpDngVoucher = new JLabel();
		lblpDngVoucher.setText("Áp dụng Voucher:");
		lblpDngVoucher.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblpDngVoucher.setBounds(970, 574, 251, 25);
		jpnQuanLyBan.add(lblpDngVoucher);
		pnQuanLyBan.add(jLabel2);

		pnSanPham.setBackground(new java.awt.Color(255, 255, 255));
		pnSanPham.setLayout(null);

		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel3.setText("Sản phẩm");
		pnSanPham.add(jLabel3);

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jLabel6.setText("Chọn tầng ");

		cboTang.setModel(
				new DefaultComboBoxModel(new String[] { "Mang về", "Tầng 1", "Tầng 2", "Tầng 3", "Tầng 4", "Tầng 5" }));

		tblDanhSachSanPham.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "S\u1EA3n ph\u1EA9m",
				"M\u00E3 s\u1EA3n ph\u1EA9m", "\u0110\u01A1n gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng", "X\u00F3a" }) {
			Class[] columnTypes = new Class[] { Object.class, Integer.class, Integer.class, Integer.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblDanhSachSanPham.getColumnModel().getColumn(0).setResizable(false);
		tblDanhSachSanPham.getColumnModel().getColumn(0).setPreferredWidth(150);
		tblDanhSachSanPham.getColumnModel().getColumn(0).setMinWidth(150);
		tblDanhSachSanPham.setVerifyInputWhenFocusTarget(false);
		tblDanhSachSanPham.setSelectionForeground(new Color(0, 128, 255));
		tblDanhSachSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDanhSachSanPham.setSelectionBackground(Color.WHITE);
		tblDanhSachSanPham.setGridColor(Color.WHITE);
		tblDanhSachSanPham.setFocusable(false);
		tblDanhSachSanPham.setFocusTraversalKeysEnabled(false);
		tblDanhSachSanPham.setUpdateSelectionOnSort(false);
		tblDanhSachSanPham.setShowHorizontalLines(false);
		tblDanhSachSanPham.setShowVerticalLines(false);
//		tblDanhSachSanPham.setCellEditor(null)
		tblDanhSachSanPham.setShowGrid(false);
		((DefaultTableCellRenderer) tblDanhSachSanPham.getTableHeader().getDefaultRenderer())
				.setVerticalAlignment(Label.CENTER);
		((DefaultTableCellRenderer) tblDanhSachSanPham.getTableHeader().getDefaultRenderer())
				.setBackground(Color.white);

//		tblDanhSachSanPham.getTableHeader().getColumnModel().ge
		tblDanhSachSanPham.setRowHeight(20);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		dtcr.setBackground(Color.white);
		tblDanhSachSanPham.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		tblDanhSachSanPham.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		tblDanhSachSanPham.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		tblDanhSachSanPham.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		tblDanhSachSanPham.getColumnModel().getColumn(4).setCellRenderer(dtcr);
		jScrollPane1.setViewportView(tblDanhSachSanPham);

		txtGhiChu.setColumns(10);
		txtGhiChu.setRows(2);
		jScrollPane2.setViewportView(txtGhiChu);

		jLabel10.setText("Tổng thanh toán(tạm tính):");

		jButton3.setBackground(new java.awt.Color(45, 132, 252));
		jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		jButton3.setForeground(new java.awt.Color(255, 255, 255));
		jButton3.setText("Thêm hóa đơn");

		jLabel20.setText("Danh sách sản phẩm");

		jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		jLabel21.setForeground(new java.awt.Color(0, 153, 255));
		jLabel21.setText("Xóa tất cả");

		pnSanPham.add(jPanel1);
		jPanel1.setLayout(null);
		jPanel1.add(jButton3);
		jPanel1.add(jScrollPane1);
		jPanel1.add(jLabel6);
		jPanel1.add(cboTang);
		jPanel1.add(jScrollPane2);
		jPanel1.add(jLabel10);
		jPanel1.add(jLabel20);
		jPanel1.add(jLabel21);

		txtDichVuPhatSinh = new JTextField() {
			@Override
			public void paste() {

			};
		};
		txtDichVuPhatSinh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
				else
					loadTTTTTT();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				loadTTTTTT();
				super.keyReleased(e);
			}
		});
		txtDichVuPhatSinh.setBorder(new TitledBorder(null, "D\u1ECBch v\u1EE5 ph\u00E1t sinh (n\u1EBFu c\u00F3)",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtDichVuPhatSinh.setBounds(12, 559, 363, 46);
		jPanel1.add(txtDichVuPhatSinh);
		txtDichVuPhatSinh.setColumns(10);

		cboBan = new JComboBox<BanViewModel>();
		cboBan.setBounds(116, 77, 177, 21);
		jPanel1.add(cboBan);

		JLabel jLabel6_1 = new JLabel();
		jLabel6_1.setText("Chọn bàn");
		jLabel6_1.setBounds(12, 81, 66, 13);
		jPanel1.add(jLabel6_1);

		JButton btnThm = new JButton();
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstBanDuocChon.add(((BanViewModel) cboBan.getSelectedItem()).getMaBan());
				loadDanhSachBanDuocChon();
			}
		});
		btnThm.setText("Thêm");
		btnThm.setForeground(Color.WHITE);
		btnThm.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnThm.setBackground(new Color(45, 132, 252));
		btnThm.setBounds(309, 76, 66, 21);
		jPanel1.add(btnThm);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(12, 121, 363, 152);
		jPanel1.add(scrollPane);

		tblBanDuocChon = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			};

		};
		tblBanDuocChon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblBanDuocChon.getSelectedRow();
				if (tblBanDuocChon.getSelectedColumn() == 3) {
					lstBanDuocChon.remove(
							((BanViewModel) tblBanDuocChon.getValueAt(tblBanDuocChon.getSelectedRow(), 1)).getMaBan());
					((DefaultTableModel) tblBanDuocChon.getModel()).removeRow(row);
					System.out.println(tblBanDuocChon.getSelectedRow());
				}
			}
		});

		tblBanDuocChon.setVerifyInputWhenFocusTarget(false);
		tblBanDuocChon.setSelectionForeground(new Color(0, 128, 255));
		tblBanDuocChon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBanDuocChon.setSelectionBackground(Color.WHITE);
		tblBanDuocChon.setGridColor(Color.WHITE);
		tblBanDuocChon.setFocusable(false);
		tblBanDuocChon.setFocusTraversalKeysEnabled(false);
		tblBanDuocChon.setUpdateSelectionOnSort(false);
		tblBanDuocChon.setShowHorizontalLines(false);
		tblBanDuocChon.setShowVerticalLines(false);
//		tblBanDuocChon.setCellEditor(null)
		tblBanDuocChon.setShowGrid(false);
		((DefaultTableCellRenderer) tblBanDuocChon.getTableHeader().getDefaultRenderer())
				.setVerticalAlignment(Label.CENTER);
		tblBanDuocChon.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "T\u1EA7ng", "T\u00EAn B\u00E0n", "Tr\u1EA1ng th\u00E1i", "X\u00F3a" }));
//		tblBanDuocChon.getTableHeader().getColumnModel().ge
		tblBanDuocChon.setRowHeight(20);
		DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();
		dtcr1.setHorizontalAlignment(SwingConstants.CENTER);
		dtcr1.setBackground(Color.WHITE);
		tblBanDuocChon.getColumnModel().getColumn(0).setCellRenderer(dtcr1);
		tblBanDuocChon.getColumnModel().getColumn(1).setCellRenderer(dtcr1);
		tblBanDuocChon.getColumnModel().getColumn(2).setCellRenderer(dtcr1);
		tblBanDuocChon.getColumnModel().getColumn(3).setCellRenderer(dtcr1);
		scrollPane.setViewportView(tblBanDuocChon);

		JLabel lblDanhSchBn = new JLabel();
		lblDanhSchBn.setText("Danh sách bàn");
		lblDanhSchBn.setBounds(13, 104, 125, 13);
		jPanel1.add(lblDanhSchBn);

		JLabel jLabel21_1 = new JLabel();
		jLabel21_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lstBanDuocChon.removeAll(lstBanDuocChon);
				loadDanhSachBanDuocChon();
				loadDanhSachBan(_tangDuocChon);
			}
		});
		jLabel21_1.setText("Xóa tất cả");
		jLabel21_1.setForeground(new Color(0, 153, 255));
		jLabel21_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		jLabel21_1.setBounds(320, 103, 56, 16);
		jPanel1.add(jLabel21_1);

		txtTTTTT = new JLabel();
		txtTTTTT.setBounds(178, 615, 125, 13);
		jPanel1.add(txtTTTTT);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(442, 84, 842, 35);
		pnSanPham.add(panel_1);
		panel_1.setLayout(null);
		jLabel22 = new javax.swing.JLabel();
		jLabel22.setBounds(814, 6, 24, 24);
		panel_1.add(jLabel22);

		jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tim3.png")));
		txtSearchTenSanPham = new JTextField();
		txtSearchTenSanPham.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyReleased(e);
				searchDanhSachSanPham();
			}

		});
		txtSearchTenSanPham.setBounds(1, 1, 805, 33);
		txtSearchTenSanPham.setMargin(new Insets(2, 202, 2, 2));
		panel_1.add(txtSearchTenSanPham);

		txtSearchTenSanPham.setBorder(null);

		jScrollPane4.setViewportView(pnDanhSachSanPham);

		pnSanPham.add(jScrollPane4);

		pnTong.add(pnSanPham, "pnSanPham");

		pnTrong.setBackground(new Color(240, 240, 240));

		pnTong.add(pnTrong, "pnTrong");
		pnTrong.setLayout(null);

		pnHoaDon.setBackground(new java.awt.Color(255, 255, 255));
		pnHoaDon.setLayout(null);

		jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel13.setText("HÓA ĐƠN");
		pnHoaDon.add(jLabel13);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(310, 70, 884, 30);
		pnHoaDon.add(panel_2);
		panel_2.setLayout(null);
		lblTimKiem = new javax.swing.JLabel();
		lblTimKiem.setBounds(860, 0, 24, 30);
		panel_2.add(lblTimKiem);

		lblTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tim3.png"))); // NOI18N
		txtNhanVienNhapMaHD = new javax.swing.JTextField();
		txtNhanVienNhapMaHD.setBounds(5, 2, 845, 27);
		panel_2.add(txtNhanVienNhapMaHD);
		txtNhanVienNhapMaHD.setBorder(null);
		DPlaceHolder.addPlaceHolder(txtNhanVienNhapMaHD, "Tìm kiếm theo Mã nhân viên");
		lblTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblTimKiemMouseClicked(evt);
			}
		});

		tblNhanVienHoaDon.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null } },
				new String[] { "Mã hóa đơn", "Người tạo", "Thời gian", "Tổng thanh toán", "Trạng thái thanh toán",
						"Ghi chú", "Chi tiết" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tblNhanVienHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblNhanVienHoaDonMouseClicked(evt);
			}
		});
		jScrollPane5.setViewportView(tblNhanVienHoaDon);

		pnHoaDon.add(jScrollPane5);

		jpnTrangThai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
		jpnTrangThai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel14.setBackground(new java.awt.Color(204, 204, 204));
		jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel14.setText("Trạng Thái");
		jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
		jpnTrangThai.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 30));

		jLabel15.setText("Trạng thái");
		jpnTrangThai.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 25));

		cboNhanVienHDTrangThai
				.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán" }));
		jpnTrangThai.add(cboNhanVienHDTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 170, 25));

		pnHoaDon.add(jpnTrangThai);

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

		pnHoaDon.add(jpnThoiGian1);

		lblNVTrangDau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/2.png"))); // NOI18N
		lblNVTrangDau.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblNVTrangDauMouseClicked(evt);
			}
		});
		pnHoaDon.add(lblNVTrangDau);

		lblNVlui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/4.png"))); // NOI18N
		lblNVlui.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblNVluiMouseClicked(evt);
			}
		});
		pnHoaDon.add(lblNVlui);

		lblNhanVienTrangThaiTrang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		lblNhanVienTrangThaiTrang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblNhanVienTrangThaiTrang.setText("Bản ghi 1/1");
		pnHoaDon.add(lblNhanVienTrangThaiTrang);

		lblNVTien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/3.png"))); // NOI18N
		lblNVTien.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				lblNVTienMouseClicked(evt);
			}
		});
		pnHoaDon.add(lblNVTien);

		jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/1.png"))); // NOI18N
		jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel27MouseClicked(evt);
			}
		});
		pnHoaDon.add(jLabel27);

		pnTong.add(pnHoaDon, "pnHoaDon");
		pack();
		DefaultTableCellRenderer dfr = new DefaultTableCellRenderer();
		dfr.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tblNhanVienBan.getColumnCount(); i++) {

			tblNhanVienBan.getColumnModel().getColumn(i).setCellRenderer(dfr);
		}
		tblDanhSachSanPham.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				loadTTTTTT();
			}
		});
		modelSPDC = (DefaultTableModel) tblDanhSachSanPham.getModel();
		lblpDngVoucher.setVisible(false);
		lblApDungVoucher.setVisible(false);

	}// </editor-fold>//GEN-END:initComponents

	protected void loadBanDuocChon() {
		DefaultTableModel model = (DefaultTableModel) tblBanDuocChon.getModel();
		model.setRowCount(0);
		lstBanDuocChon.forEach(ban -> {
			svBan.getAll();
		});

	}

	private void btnDangXuatMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnDangXuatMouseClicked
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
	}// GEN-LAST:event_btnDangXuatMouseClicked


	private void lblDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDoiMatKhauMouseClicked
		loadView("pnDoiMatKhau");

	}// GEN-LAST:event_lblDoiMatKhauMouseClicked

	private void lblThietLapMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblThietLapMouseClicked

	}// GEN-LAST:event_lblThietLapMouseClicked

	private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDangXuatActionPerformed

	}// GEN-LAST:event_btnDangXuatActionPerformed

	private void lblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblSanPhamMouseClicked
		loadView("pnSanPham");

	}// GEN-LAST:event_lblSanPhamMouseClicked

	private void lblQuanLyBanMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblQuanLyBanMouseClicked
		lstBanDuocChon.removeAll(lstBanDuocChon);
		loadView("pnQuanLyBan");
	}// GEN-LAST:event_lblQuanLyBanMouseClicked

	private void lblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblHoaDonMouseClicked
		loadView("pnHoaDon");
		layDuLieuNVHD();
		phanTrang();
		truyenTrang(1);

	}// GEN-LAST:event_lblHoaDonMouseClicked

	private void lblTimKiemMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblTimKiemMouseClicked
		// TODO add your handling code her
		try {
			if (Uhelper.checkNullText(txtNhanVienNhapMaHD, "bạn chưa nhập mã nhân viên")) {
				return;
			} else {
				try {
					int maHD = Integer.parseInt(txtNhanVienNhapMaHD.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this,
							"mã nhân viên phải là số hoặc hãy đảm bảo mã nhân viên không có khoảng trắng");
					txtNhanVienNhapMaHD.requestFocus();
					;
					return;
				}
				if (Integer.parseInt(txtNhanVienNhapMaHD.getText()) < 0) {
					JOptionPane.showMessageDialog(null, "mã nhân viên không được âm");
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
	}// GEN-LAST:event_lblTimKiemMouseClicked

	private void lblBanHangMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblBanHangMouseClicked
		// TODO add your handling code here:
	}// GEN-LAST:event_lblBanHangMouseClicked

	private void txtVoucherActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtVoucherActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtVoucherActionPerformed

	private void lblNVTrangDauMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		truyenTrang(1);
		demTrang = 1;
	}

	private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel27MouseClicked
		// TODO add your handling code here:
		truyenTrang(soTrang);
		demTrang = soTrang;
	}// GEN-LAST:event_jLabel27MouseClicked

	private void lblNVluiMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		if (demTrang > 1) {
			demTrang--;
			truyenTrang(demTrang);
		} else {
			JOptionPane.showMessageDialog(null, "Không thể lùi !", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

	}// GEN-LAST:event_lblNVluiMouseClicked

	private void lblNVTienMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		if (demTrang < soTrang) {
			demTrang++;
			truyenTrang(demTrang);
		} else {
			JOptionPane.showMessageDialog(null, "Không thể tiến !", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	int viTri;

	private void btnApDungBanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnApDungBanActionPerformed
		DecimalFormat fmt = new DecimalFormat("###,###,###");
		lblpDngVoucher.setVisible(false);
		lblApDungVoucher.setVisible(false);
		if (tblNhanVienBan.getRowCount() <= 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn cần thanh toán !", "Lỗi", JOptionPane.ERROR_MESSAGE);
			lblGiaSauKhiGiam.setText(fmt.format(_tongDichVuPhatSinh + _tongTatCaHoaDon) + " VND");
			return;

		}
		String voucher = txtVoucher.getValue().toString();
		if (voucher.length() < 2) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập voucher", "Lỗi", JOptionPane.ERROR_MESSAGE);
			lblGiaSauKhiGiam.setText(fmt.format(_tongDichVuPhatSinh + _tongTatCaHoaDon) + " VND");

			return;
		}
		try {
			double voucher2 = Double.parseDouble(voucher);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Sai kiểu dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
			lblGiaSauKhiGiam.setText(fmt.format(_tongDichVuPhatSinh + _tongTatCaHoaDon) + " VND");

			return;

		}

		if (iMGGSe.checkMaGiamGia(Integer.parseInt(txtVoucher.getValue().toString().trim().equals("") ? "0"
				: txtVoucher.getValue().toString())) == false) {
			JOptionPane.showMessageDialog(this, "Mã voucher sai", "Lỗi", JOptionPane.ERROR_MESSAGE);
			lblGiaSauKhiGiam.setText(fmt.format(_tongDichVuPhatSinh + _tongTatCaHoaDon) + " VND");

			return;
		}
		_voucher = Integer
				.parseInt(txtVoucher.getValue().toString().trim().equals("") ? "0" : txtVoucher.getValue().toString());
		var soTienCanThanhToan = iMGGSe.applyVoucher(
				Integer.parseInt(
						txtVoucher.getValue().toString().trim().equals("") ? "0" : txtVoucher.getValue().toString()),
				_tongTatCaHoaDon);
		if (soTienCanThanhToan != _tongTatCaHoaDon) {
			jLabel38.setText("Tổng thanh toán (đã giảm):");

			lblGiaSauKhiGiam.setText("<html><strike>" + fmt.format(_tongDichVuPhatSinh + _tongTatCaHoaDon) + " VND"
					+ "</strike> " + fmt.format(_tongDichVuPhatSinh + soTienCanThanhToan) + " VND </html>");
			lblApDungVoucher.setText(fmt.format(_tongTatCaHoaDon - soTienCanThanhToan) + " VND");
			lblpDngVoucher.setVisible(true);
			lblApDungVoucher.setVisible(true);
		} else {
			lblpDngVoucher.setVisible(false);
			lblApDungVoucher.setVisible(false);
			jLabel38.setText("Tổng thanh toán:");
			lblGiaSauKhiGiam.setText(fmt.format(_tongDichVuPhatSinh + soTienCanThanhToan) + " VND");
		}
	}// GEN-LAST:event_btnApDungBanActionPerformed

	private void btnThanhToanBanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThanhToanBanActionPerformed
		for (Integer maBan : lstBanDuocChon) {
			svBan.actives(maBan, false);
		}

		lstBanDuocChon.removeAll(lstBanDuocChon);
		loadDanhSachBan(_tangDuocChon);
		if (tblNhanVienBan.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Không có hóa đơn nào cần thanh toán", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Integer[] danhSachHoaDon = new Integer[tblNhanVienBan.getRowCount()];
		for (int i = 0; i < tblNhanVienBan.getRowCount(); i++) {
			danhSachHoaDon[i] = Integer.parseInt(tblNhanVienBan.getValueAt(i, 0).toString());
		}
		JOptionPane.showMessageDialog(null,
				svQuanLyBan.thanhToanHoaDon(danhSachHoaDon, _voucher) ? "Thanh toán thành công !"
						: "Thanh toán thất bại !");
		loadHoaDon(lstBanDuocChon);

	}// GEN-LAST:event_btnThanhToanBanActionPerformed

	private void tblNhanVienHoaDonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblNhanVienHoaDonMouseClicked
		// TODO add your handling code here:
		int index = tblNhanVienHoaDon.getSelectedRow();
		int maHD = (int) tblNhanVienHoaDon.getValueAt(index, 0);
		NhanVienHoaDonViewModel hoaDon = new NhanVienHoaDonViewModel();
		for (NhanVienHoaDonViewModel a : listNhanVienHDView) {
			if (a.getMaHoaDon() == maHD) {
				hoaDon = a;
			}
		}

		NhanVienHoaDon_ChiTiet nv = new NhanVienHoaDon_ChiTiet(hoaDon);
		nv.setVisible(true);
	}// GEN-LAST:event_tblNhanVienHoaDonMouseClicked

	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				String maTaiKhoan = "TKNV1001"; // Lấy mã tài khoản từ giao diện đăng nhập
				TraSua_NV traSua_NV = new TraSua_NV(maTaiKhoan);
				traSua_NV.setMaTaiKhoan(maTaiKhoan);
				traSua_NV.setVisible(true);
			}
		});
	}

	private int _tangDuocChon = 0;

	private void loadView(String panel) {
		CardLayout cardLayout = (CardLayout) pnTong.getLayout();
		switch (panel) {
		case "pnQuanLyBan": {
			txtVoucher.getModel().setValue("");
			cardLayout.show(pnTong, "pnQuanLyBan");
			lblQuanLyBan.setBackground(new Color(27, 43, 101));
			lblHoaDon.setBackground(new Color(8, 26, 81));
			lblDoiMatKhau.setBackground(new Color(8, 26, 81));
			lblSanPham.setBackground(new Color(8, 26, 81));
			loadDanhSachBan(_tangDuocChon);
			loadHoaDon(lstBanDuocChon);
			txtVoucher.setModel(new SpinnerListModel(iMGGSe.getLstID()));
			break;
		}
		case "pnDoiMatKhau": {
		dmk.setVisible(true);
			break;
		}

		case "pnSanPham": {
			cardLayout.show(pnTong, "pnSanPham");
			lblQuanLyBan.setBackground(new Color(8, 26, 81));
			lblHoaDon.setBackground(new Color(8, 26, 81));
			lblDoiMatKhau.setBackground(new Color(8, 26, 81));
			lblSanPham.setBackground(new Color(27, 43, 101));
			if(!flagLoadDSSP) {
				loadDanhSachSanPham();
			}
			flagLoadDSSP=true;
			loadDanhSachBan(_tangDuocChon);
			loadDanhSachBanDuocChon();
			cboTang.setSelectedIndex(_tangDuocChon);
			((DefaultTableModel) tblDanhSachSanPham.getModel()).setRowCount(0);

			break;
		}
		case "pnHoaDon": {
			cardLayout.show(pnTong, "pnHoaDon");
			lblQuanLyBan.setBackground(new Color(8, 26, 81));
			lblHoaDon.setBackground(new Color(27, 43, 101));
			lblDoiMatKhau.setBackground(new Color(8, 26, 81));
			lblSanPham.setBackground(new Color(8, 26, 81));
			phanTrang();
			truyenTrang(1);

			break;

		}
		case "pnKhieuNaiHoTro": {
			lblQuanLyBan.setBackground(new Color(8, 26, 81));
			lblHoaDon.setBackground(new Color(8, 26, 81));
			lblDoiMatKhau.setBackground(new Color(8, 26, 81));
			lblSanPham.setBackground(new Color(8, 26, 81));
			break;
		}

		default:
		}

	}
	private boolean flagLoadDSSP=false;

	private void loadDanhSachBanDuocChon() {
		DefaultTableModel model = (DefaultTableModel) tblBanDuocChon.getModel();
		model.setRowCount(0);
		lstBanDuocChon.forEach(maBan -> {
			BanViewModel vmBan = svBan.getById(maBan);
			model.addRow(new Object[] { vmBan.getTang() == 0 ? "Mang về" : "Tầng " + vmBan.getTang(), vmBan,
					vmBan.getTrangThai() == 1 ? "Đã có người" : "Trống",
					"<html><body style=';text-align: center; color:  #1E90FF'><u>Xóa bàn</u></body></html>" });
		});
	}

	private void loadAllComponent() {
		loadDanhSachTang(svBan.getAll());
	}

	private void loadDanhSachTang(List<BanViewModel> lstBan) {

		class WrapperBan {

			private BanViewModel ban;

			public WrapperBan(BanViewModel ban) {
				this.ban = ban;
			}

			public BanViewModel unwrap() {
				return this.ban;
			}

			@Override
			public int hashCode() {
				return Objects.hash(ban.getTang());
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj) {
					return true;
				}
				if (obj == null) {
					return false;
				}
				if (getClass() != obj.getClass()) {
					return false;
				}
				WrapperBan other = (WrapperBan) obj;
				return ban.getTang() == other.ban.getTang();
			}

		}

		ButtonGroup btnGr = new ButtonGroup();
		List<JButton> lstBtn = new ArrayList<JButton>();
		pnTang.removeAll();

		lstBan.stream().map(WrapperBan::new).distinct().map(WrapperBan::unwrap)
				.sorted((tang1, tang2) -> tang1.getTang() > tang2.getTang() ? 1 : -1).forEach(tang -> {
					JButton btn = new JButton("");
					btn.setFocusTraversalKeysEnabled(false);
					btn.setFocusPainted(false);
					btn.setFocusable(false);
					btn.setForeground(new Color(255, 255, 255));
					btn.setFont(new Font("Tahoma", Font.PLAIN, 16));
					btn.setBackground(new Color(0, 128, 255));
					btn.setText(tang.getTang() == 0 ? "Mang về" : "Tầng " + tang.getTang());
					btn.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							loadDanhSachBan(tang.getTang());
							jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, btn.getText(),
									javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									new java.awt.Font("Segoe UI", 3, 12))); // NOI18N
							lstBtn.forEach(btn -> btn.setBackground(new Color(0, 128, 255)));
							btn.setBackground(Color.gray);
							_tangDuocChon = tang.getTang();

						}
					});
					lstBtn.add(btn);
					btnGr.add(btn);
					pnTang.add(btn);

				});
		loadDanhSachBan(0);

	}

	private Set<Integer> lstBanDuocChon = new HashSet<Integer>();

	private void loadDanhSachBan(int tang) {
		Component[] lstCPN = pnTang.getComponents();
		for (int i = 0; i < lstCPN.length; i++) {
			lstCPN[i].setBackground(i == _tangDuocChon ? Color.gray : new Color(0, 128, 255));
		}
		List<BanViewModel> lstBan = svBan.getAll().stream().filter(ban -> ban.getTang() == tang)
				.collect(Collectors.toList());

		cboBan.removeAllItems();
		((DefaultComboBoxModel<BanViewModel>) cboBan.getModel()).addAll(lstBan);
		cboBan.setSelectedIndex(0);
		pnDanhSachBan.setSize(410 / 3, (lstBan.size() / 3 + 1) * 150);
		pnDanhSachBan.setPreferredSize(new Dimension((int) 410 / 3, (int) ((lstBan.size() / 3 + 1) * 150)));
		GridBagConstraints gbc = new GridBagConstraints();
		pnDanhSachBan.setLayout(new GridBagLayout());
		int row = 0;
		int col = -1;
		pnDanhSachBan.removeAll();
		gbc.insets = new Insets(1, 1, 1, 1);
		for (BanViewModel ban : lstBan) {
			if (col == 2) {
				row++;
				col = 0;
			} else {
				col++;
			}

			gbc.gridx = col;
			gbc.gridy = row;
//			System.out.println(rơ);
			JButton btn = new JButton("");
			btn.setFocusTraversalKeysEnabled(false);
			btn.setFocusPainted(false);
			btn.setFocusable(false);
			btn.setForeground(new Color(255, 255, 255));
			btn.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btn.setBackground(ban.getTrangThai() == 0 ? new Color(0, 128, 255) : new Color(0, 128, 0));

			if (lstBanDuocChon.contains(ban.getMaBan())) {
				btn.setBackground(Color.gray);
			}
			btn.setText(
					"<html><body style='text-align:center'>" + ban.getTenBan() + "<br>" + "<p style='font-size: 8px'>"
							+ (ban.getTrangThai() == 0 ? "\r\n(Trống)" : "\r\n(Đang sử dụng)") + "</p>" + "</html>");
			btn.setPreferredSize(new Dimension(140, 140));
			btn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					System.out.println(btn.isSelected());
					if (btn.isSelected()) {
						btn.setSelected(false);
						lstBanDuocChon.remove(ban.getMaBan());
						btn.setBackground(ban.getTrangThai() == 0 ? new Color(0, 128, 255) : new Color(0, 128, 0));
						loadHoaDon(lstBanDuocChon);

					} else {
						btn.setSelected(true);
						lstBanDuocChon.add(ban.getMaBan());
						btn.setBackground(Color.gray);
						loadHoaDon(lstBanDuocChon);
					}
					System.out.println(lstBanDuocChon.size());
				}

			});
			pnDanhSachBan.add(btn, gbc);
		}
	}

	private int _voucher;
	private int _tongDichVuPhatSinh = 0;
	private int _tongTatCaHoaDon = 0;
	private JLabel lblTongDichVuPhatSinh;
//	private ListlstHoaDonDetail
	List<viewmodel.nhanVien.quanLyBan.HoaDon> lstHoaDon = null;

	private void loadHoaDon(Set<Integer> lstBanDuocChon) {
		txtVoucher.getModel().setValue("");
		DefaultTableModel model = new DefaultTableModel();
		model = (DefaultTableModel) tblNhanVienBan.getModel();
		model.setRowCount(0);
		_tongTatCaHoaDon = 0;
		_tongDichVuPhatSinh = 0;
		lblGiaSauKhiGiam.setText("0 VND");
		lblTongDichVuPhatSinh.setText("0 VND");
		lblTongTien.setText("0 VND");
		if (lstBanDuocChon.size() < 1) {
			return;
		}
		Integer lstMaBan[] = lstBanDuocChon.toArray(new Integer[0]);
		lstHoaDon = svQuanLyBan.getLstHoaDonTheoBan(lstMaBan);
		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

		for (viewmodel.nhanVien.quanLyBan.HoaDon hoaDon : lstHoaDon) {
			int tongTien = 0;
			for (var cthd : hoaDon.getLstCTHD()) {
				System.out.println(cthd.getGia().intValue() * cthd.getSoLuong());
				tongTien += cthd.getGia().intValue() * cthd.getSoLuong();
			}
			DecimalFormat dfm=new DecimalFormat("###,###,###");
			_tongTatCaHoaDon += tongTien;
			hoaDon.setTongThanhToan(tongTien + hoaDon.getDichVuPhatSinh());
			model.addRow(new Object[] { hoaDon.getMaHoaDon(), fm.format(hoaDon.getThoiGian()), dfm.format(tongTien)+"",
					hoaDon.getTrangThaiOder() == 0 ? "Đã tiếp nhận" : "Đã làm",
					"<html><body style=';text-align: center; color:  #1E90FF'><u>Xem chi tiết</u></body></html>" });
			_tongDichVuPhatSinh += hoaDon.getDichVuPhatSinh();
		}
		DecimalFormat fmt = new DecimalFormat("###,###,###");
		lblTongTien.setText(fmt.format(_tongTatCaHoaDon) + " VND");
		lblTongDichVuPhatSinh.setText(fmt.format(_tongDichVuPhatSinh) + " VND");

		lblGiaSauKhiGiam.setText(fmt.format(_tongDichVuPhatSinh + _tongTatCaHoaDon) + " VND");

		lblpDngVoucher.setVisible(false);
		lblApDungVoucher.setVisible(false);
		jLabel38.setText("Tổng thanh toán:");
		lblGiaSauKhiGiam.setText(fmt.format(_tongDichVuPhatSinh + _tongTatCaHoaDon) + " VND");

	}

	List<SanPham> lstELMSP = new ArrayList<SanPham>();
	private JTextField txtDichVuPhatSinh;
	private JComboBox<BanViewModel> cboBan;
	private JTable tblBanDuocChon;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private DefaultTableModel modelSPDC;
	private JLabel txtTTTTT;

	private void loadTTTTTT() {
		int tongTien = 0;
		for (int i = 0; i < tblDanhSachSanPham.getRowCount(); i++) {
			tongTien += (int) tblDanhSachSanPham.getValueAt(i, 2) * (int) tblDanhSachSanPham.getValueAt(i, 3);
		}
		try {
			tongTien += Integer.parseInt(txtDichVuPhatSinh.getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
		DecimalFormat df = new DecimalFormat("###,###,###");
		txtTTTTT.setText(df.format(tongTien) + " VND");
	}

	List<views.element.SanPham> lstSP;
	private JPanel panel_2;
	private JLabel lblpDngVoucher;
	private JLabel lblApDungVoucher;

	private void loadDanhSachSanPham() {
		lstSP = svQLSanPham.getAllSanPham(modelSPDC);
		pnDanhSachSanPham.removeAll();
		pnDanhSachSanPham.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		int row = 0;
		int col = 0;

		pnDanhSachSanPham.setSize(pnDanhSachSanPham.getWidth(), 280 * (lstSP.size() / 3));
		System.out.println(pnDanhSachSanPham.getSize());
		gbc.insets = new Insets(10, 10, 10, 10);

		for (SanPham sp : lstSP) {
			gbc.gridx = col;
			gbc.gridy = row;

			pnDanhSachSanPham.add(sp, gbc);
			if (col == 2) {
				row++;
				col = 0;
			} else {
				col++;
			}

		}
		pnDanhSachSanPham.revalidate();
		pnDanhSachSanPham.repaint();
	}

	private void searchDanhSachSanPham() {
		String searchKey = txtSearchTenSanPham.getText().toLowerCase();
		if (!searchKey.equalsIgnoreCase("Tìm kiếm theo tên sản phẩm".toLowerCase())) {
			var searchKeyKD = Normalizer.normalize(searchKey, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
			;
			List<SanPham> lstSP = this.lstSP.stream()
					.filter(sanPham -> Normalizer.normalize(sanPham.getTenSanPham().toLowerCase(), Normalizer.Form.NFD)
							.replaceAll("\\p{M}", "").matches(searchKeyKD.replaceAll("", ".*")))
					.collect(Collectors.toList());
			pnDanhSachSanPham.removeAll();
			pnDanhSachSanPham.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();

			int row = 0;
			int col = 0;

			pnDanhSachSanPham.setSize(pnDanhSachSanPham.getWidth(), 280 * (lstSP.size() / 3));
			System.out.println(pnDanhSachSanPham.getSize());
			gbc.insets = new Insets(10, 10, 10, 10);

			for (SanPham sp : lstSP) {
				gbc.gridx = col;
				gbc.gridy = row;

				pnDanhSachSanPham.add(sp, gbc);
				if (col == 2) {
					row++;
					col = 0;
				} else {
					col++;
				}

			}
			pnDanhSachSanPham.revalidate();
			pnDanhSachSanPham.repaint();
		} else {
			pnDanhSachSanPham.removeAll();
			pnDanhSachSanPham.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();

			int row = 0;
			int col = 0;

			pnDanhSachSanPham.setSize(pnDanhSachSanPham.getWidth(), 280 * (lstSP.size() / 3));
			System.out.println(pnDanhSachSanPham.getSize());
			gbc.insets = new Insets(10, 10, 10, 10);

			for (SanPham sp : lstSP) {
				gbc.gridx = col;
				gbc.gridy = row;

				pnDanhSachSanPham.add(sp, gbc);
				if (col == 2) {
					row++;
					col = 0;
				} else {
					col++;
				}

			}
			pnDanhSachSanPham.revalidate();
			pnDanhSachSanPham.repaint();
		}

	}
}
