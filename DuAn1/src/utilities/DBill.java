package utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import repositorys.ChiTietHoaDonRepository;
import repositorys.HoaDonRepository;
import repositorys.MaGiamGiaRepository;

public class DBill extends JFrame {

	private JPanel contentPane;
	private JPanel pnTitle;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblHanBn;

	/**
	 * Launch the application.
	 */
	class Printer implements Printable {
		private Component comp;
		
		public Printer(Component comp) {
	
			this.comp = comp;
		}

		@Override
		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
			// TODO Auto-generated method stub
			if (pageIndex > 0) {
				return NO_SUCH_PAGE;
			}
			Dimension dim = comp.getSize();
			double cHeight = dim.getHeight();
			double cWidth = dim.getWidth();

			// get the bounds of the printable area
			double pHeight = pageFormat.getImageableHeight();
			double pWidth = pageFormat.getImageableWidth();

			double pXStart = pageFormat.getImageableX();
			double pYStart = pageFormat.getImageableY();

			double xRatio = pWidth / cWidth;
			double yRatio = pHeight / cHeight;

			Graphics2D g2 = (Graphics2D) graphics;
			g2.translate(pXStart, pYStart);
			g2.scale(xRatio, yRatio);
			comp.paint(g2);
			return PAGE_EXISTS;

		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBill frame = new DBill();
					frame.printHoaDon(new Integer[] {1000}, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	DecimalFormat dmf = new DecimalFormat("###,###,###");

	public DBill() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pnTitle = new JPanel();
		pnTitle.setBackground(new Color(255, 255, 255));
		pnTitle.setPreferredSize(new Dimension(400, 200));
		contentPane.add(pnTitle);
		pnTitle.setLayout(null);

		lblNewLabel = new JLabel("Quán trà sữa ToTo");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(102, 28, 196, 13);
		pnTitle.add(lblNewLabel);

		lblNewLabel_1 = new JLabel(
				"<html><body style=' text-align: center;'>Địa chỉ: Số 1 P. Trịnh Văn Bô<br> Xuân Phương, Nam Từ Liêm, Hà Nội</body></html>");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(90, 51, 235, 44);
		pnTitle.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("-------------------------------------------------------");
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(90, 126, 220, 13);
		pnTitle.add(lblNewLabel_2);

		lblHanBn = new JLabel("HÓA ĐƠN BÁN LẺ");
		lblHanBn.setBackground(new Color(255, 255, 255));
		lblHanBn.setHorizontalAlignment(SwingConstants.CENTER);
		lblHanBn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHanBn.setBounds(102, 143, 196, 19);
		pnTitle.add(lblHanBn);
		SimpleDateFormat spd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		JLabel lblNewLabel_1_1 = new JLabel("Ngày " + spd.format(new Date()));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBackground(Color.WHITE);
		lblNewLabel_1_1.setBounds(117, 105, 181, 13);
		pnTitle.add(lblNewLabel_1_1);

	}

	public void printHoaDon(Integer[] maHoaDons, int maVouCher) {
		int tongTatCaHoaDon = 0;
		int tongTatCaDichVu = 0;
		for (Integer maHoaDon : maHoaDons) {
			var tongHoaDon = new HoaDonRepository().getTongHoaDon(maHoaDon);
			var dichVu = new HoaDonRepository().getTongDichVu(maHoaDon);
			tongTatCaHoaDon += tongHoaDon;
			tongTatCaDichVu += dichVu;
			JPanel pnHoaDon = new JPanel();
//			pnHoaDon.setBorder(new EmptyBorder(10,0,0,0));
			pnHoaDon.setBackground(new Color(255, 255, 255));
			pnHoaDon.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			getContentPane().add(pnHoaDon);
			pnHoaDon.setLayout(new BoxLayout(pnHoaDon, BoxLayout.Y_AXIS));
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(new Color(255, 255, 255));
			panel_2.setPreferredSize(new Dimension(400, 30));
			pnHoaDon.add(panel_2);
			panel_2.setLayout(null);

			JLabel lblMaHoaDon = new JLabel("Hóa đơn " + maHoaDon);
			lblMaHoaDon.setBackground(new Color(255, 255, 255));
			lblMaHoaDon.setSize(200, 13);
			panel_2.add(lblMaHoaDon);
			lblMaHoaDon.setOpaque(true);
			lblMaHoaDon.setPreferredSize(new Dimension(200, 13));
			lblMaHoaDon.setLocation(0, 10);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportBorder(null);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			scrollPane.setOpaque(false);
			scrollPane.setBackground(new Color(255, 255, 255));
			pnHoaDon.add(scrollPane);

			JTable table = new JTable();
			table.disable();

			table.setShowGrid(false);
			((DefaultTableCellRenderer) (table.getDefaultRenderer(Object.class))).setOpaque(false);

			table.setBackground(new Color(255, 255, 255));
			table.setModel(new DefaultTableModel(new Object[][] {

			}, new String[] { "T\u00EAn s\u1EA3n ph\u1EA9m", "Size", "S\u1ED1 l\u01B0\u1EE3ng",
					"\u0110\u01A1n gi\u00E1" }));
			table.getTableHeader().setBorder(null);
			table.getTableHeader().setBackground(Color.white);
			((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
					.setHorizontalAlignment(SwingConstants.LEFT);
			table.getColumnModel().getColumn(0).setPreferredWidth(120);
			table.getColumnModel().getColumn(1).setPreferredWidth(32);
			table.getColumnModel().getColumn(2).setPreferredWidth(52);
			table.getColumnModel().getColumn(3).setPreferredWidth(80);
			table.setRowHeight(20);
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			ResultSet rs = new ChiTietHoaDonRepository().getBill(maHoaDon);

			try {

				while (rs.next()) {
					model.addRow(new Object[] { rs.getObject(1), rs.getObject(2), rs.getObject(3),
							dmf.format(rs.getLong(4)) + " VND" });
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			table.setPreferredSize(new Dimension((int) table.getX(), (int) (table.getRowCount() * 20) + 20));
			scrollPane.setOpaque(false);
			scrollPane.setPreferredSize(table.getPreferredSize());
			scrollPane.setViewportView(table);
			JPanel panel_3 = new JPanel();
			panel_3.setLayout(null);

			panel_3.setBackground(new Color(255, 255, 255));
			panel_3.setPreferredSize(new Dimension(400, 30));
			pnHoaDon.add(panel_3);
			JLabel lblNewLabel = new JLabel("Tổng hóa đơn: " + dmf.format(tongHoaDon) + " VND");
			lblNewLabel.setBounds(40, 8, 188, 13);
			panel_3.add(lblNewLabel);

			JLabel lblDchV = new JLabel("Dịch vụ: " + dmf.format(dichVu) + " VND");
			lblDchV.setBounds(229, 8, 161, 13);
			panel_3.add(lblDchV);

		}

		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setPreferredSize(new Dimension(400, 150));
			panel.setBackground(Color.WHITE);
			contentPane.add(panel);

			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			panel_1.setBackground(Color.WHITE);
			panel_1.setBounds(10, 10, 390, 73);
			panel.add(panel_1);

			JLabel lblNewLabel_3 = new JLabel("Tổng hóa đơn:");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_3.setBounds(3, 8, 93, 13);
			panel_1.add(lblNewLabel_3);

			JLabel lblNewLabel_4 = new JLabel("Tổng dịch vụ:");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setBounds(3, 29, 93, 13);
			panel_1.add(lblNewLabel_4);

			JLabel lblNewLabel_5 = new JLabel("Voucher: ");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_5.setBounds(3, 51, 93, 13);
			panel_1.add(lblNewLabel_5);

			JLabel txtTongHoaDon = new JLabel("");
			txtTongHoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
			txtTongHoaDon.setBounds(218, 8, 144, 13);
			panel_1.add(txtTongHoaDon);

			JLabel txtTongDichVu = new JLabel("");
			txtTongDichVu.setHorizontalAlignment(SwingConstants.RIGHT);
			txtTongDichVu.setBounds(218, 29, 144, 13);
			panel_1.add(txtTongDichVu);

			JLabel txtVoucher = new JLabel("");
			txtVoucher.setHorizontalAlignment(SwingConstants.RIGHT);
			txtVoucher.setBounds(218, 50, 144, 13);
			panel_1.add(txtVoucher);

			JLabel lblNewLabel_6 = new JLabel("Tổng thanh toán:");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel_6.setBackground(Color.WHITE);
			lblNewLabel_6.setBounds(21, 100, 141, 24);
			panel.add(lblNewLabel_6);

			JLabel txtTongThanhToan = new JLabel("");
			txtTongThanhToan.setHorizontalAlignment(SwingConstants.RIGHT);
			txtTongThanhToan.setFont(new Font("Tahoma", Font.BOLD, 15));
			txtTongThanhToan.setBackground(Color.WHITE);
			txtTongThanhToan.setBounds(238, 100, 141, 24);
			panel.add(txtTongThanhToan);
			txtTongDichVu.setText(dmf.format(tongTatCaDichVu) + " VND");
			txtTongHoaDon.setText(dmf.format(tongTatCaHoaDon) + " VND");
			var soTienGiam = new MaGiamGiaRepository().getReducedAmount(maVouCher, tongTatCaHoaDon);
			if (soTienGiam != 0) {
				txtVoucher.setText(dmf.format(soTienGiam) + " VND");

			} else {
				lblNewLabel_5.setVisible(false);
			}
			txtTongThanhToan.setText(dmf.format(tongTatCaDichVu + tongTatCaHoaDon + soTienGiam) + " VND");

		}
		revalidate();
		repaint();
		pack();
		JFrame yourComponent = new JFrame();
		PrinterJob pjob = PrinterJob.getPrinterJob();
		
		PageFormat preformat = pjob.defaultPage();
		preformat.setOrientation(PageFormat.PORTRAIT);
		
//		pp.setSize(2.2440944882*72,);
//		preformat.setPaper();
		//If user does not hit cancel then print.
	
		    //Set print component
		    pjob.setPrintable(new Printer(contentPane), preformat);
		    if (pjob.printDialog()) {
		        try {
					pjob.print();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		
	}
}
