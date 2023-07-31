package views.element;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Frame;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import viewmodel.defaultViewModel.ChiTietHoaDonViewModel;
import viewmodel.defaultViewModel.ChiTietSanPhamViewModel;
import viewmodel.nhanVien.sanPham.HoaDon;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import java.awt.Color;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.microsoft.sqlserver.jdbc.dataclassification.Label;

import services.ChiTietSanPhamService;

public class ChiTietHoaDon extends JDialog {

	private JPanel contentPane;
	private JTable tblDanhSachHoaDon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
////					ChiTietHoaDon frame = new ChiTietHoaDon(1000,new Frame());
//					frame.setModal(true);
//				
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private ChiTietSanPhamService svCTSP=new ChiTietSanPhamService();
	
	
	public ChiTietHoaDon(viewmodel.nhanVien.quanLyBan.HoaDon hoaDon,Frame frm) {
		super(frm,"Thông tin hóa đơn");
		setModal(true);
		setLocationRelativeTo(null);
		setBounds(100, 100, 673, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 271, 381);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã hóa đơn:");
		lblNewLabel.setBounds(15, 18, 86, 22);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNhnVin = new JLabel("Mã nhân viên:");
		lblNhnVin.setBounds(15, 83, 90, 22);
		panel.add(lblNhnVin);
		lblNhnVin.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblThiGian = new JLabel("Thời gian:");
		lblThiGian.setBounds(15, 155, 64, 22);
		panel.add(lblThiGian);
		lblThiGian.setHorizontalAlignment(SwingConstants.LEFT);
		lblThiGian.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel txtMaHoaDon = new JLabel("");
		txtMaHoaDon.setBounds(142, 18, 119, 22);
		panel.add(txtMaHoaDon);
		txtMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel txtMaNhanVien = new JLabel("");
		txtMaNhanVien.setBounds(142, 83, 119, 22);
		panel.add(txtMaNhanVien);
		txtMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel txtThoiGian = new JLabel("");
		txtThoiGian.setBounds(142, 155, 142, 22);
		panel.add(txtThoiGian);
		txtThoiGian.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDchVPht = new JLabel("Dịch vụ phát sinh:");
		lblDchVPht.setHorizontalAlignment(SwingConstants.LEFT);
		lblDchVPht.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDchVPht.setBounds(15, 216, 123, 22);
		panel.add(lblDchVPht);
		
		JLabel txtDVPS = new JLabel("");
		txtDVPS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDVPS.setBounds(142, 216, 119, 22);
		panel.add(txtDVPS);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(null, "Ghi ch\u00FA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setBounds(10, 263, 251, 105);
		panel.add(scrollPane_1);
		
		JTextArea txtGhiChu = new JTextArea();
	
		txtGhiChu.setEditable(false);
		txtGhiChu.setBackground(new Color(240, 240, 240));
		scrollPane_1.setViewportView(txtGhiChu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(286, 10, 365, 359);
		contentPane.add(scrollPane);
		
		tblDanhSachHoaDon = new JTable();
		tblDanhSachHoaDon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã sản phẩm","Size","Số lượng","Đơn giá","Thành tiền"
			}
		));
		
		
		scrollPane.setViewportView(tblDanhSachHoaDon);
		
		JLabel lblTngHan = new JLabel("Tổng thanh toán (tạm tính):");
		lblTngHan.setBounds(268, 382, 219, 22);
		contentPane.add(lblTngHan);
		lblTngHan.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTngHan.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblTongThanhToan = new JLabel("");
		lblTongThanhToan.setBounds(497, 382, 161, 22);
		contentPane.add(lblTongThanhToan);
		lblTongThanhToan.setFont(new Font("Tahoma", Font.BOLD, 14));
		DecimalFormat fm=new DecimalFormat("###,###,###");
		txtMaHoaDon.setText(hoaDon.getMaHoaDon()+"");
		txtDVPS.setText(fm.format(hoaDon.getDichVuPhatSinh())+" VND");
		SimpleDateFormat spd=new SimpleDateFormat("HH:mm:ss");
		txtThoiGian.setText(spd.format(hoaDon.getThoiGian()));
		txtMaNhanVien.setText(hoaDon.getMaNhanVien()+"");
		txtGhiChu.setText(hoaDon.getGhiChu());
		txtGhiChu.repaint();
		DefaultTableModel model=(DefaultTableModel) tblDanhSachHoaDon.getModel();
		lblTongThanhToan.setText(fm.format(hoaDon.getTongThanhToan())+" VND");
		hoaDon.getLstCTHD().forEach(cthd->{
			ChiTietSanPhamViewModel vmCTSP=svCTSP.getById(cthd.getMaChiTietSanPham());
			model.addRow(new Object[] {vmCTSP.getMaSanPham(),vmCTSP.getSize(),cthd.getSoLuong(),fm.format(cthd.getGia())+" VND",fm.format(cthd.getGia().intValue()*cthd.getSoLuong())+" VND "});
		});
		
		
		DefaultTableCellRenderer dtcr=new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=0;i<tblDanhSachHoaDon.getColumnCount();i++) {
			tblDanhSachHoaDon.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
	
	
	}
}
