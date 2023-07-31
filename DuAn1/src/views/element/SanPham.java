package views.element;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import services.ChiTietSanPhamService;
import utilities.JdbcHelper;
import viewmodel.defaultViewModel.ChiTietSanPhamViewModel;
import viewmodel.defaultViewModel.SanPhamViewModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Font;

public class SanPham extends JPanel {

	private ImageIcon anhSanPham;
	private String tenSanPham;
	private boolean trangThai;
	private int maSanPham;
	private JPanel pnSize;
	private List<ChiTietSanPhamViewModel> lstChiTietSanPhamViewModels;

	public ImageIcon getAnhSanPham() {
		return anhSanPham;
	}

	public void setAnhSanPham(ImageIcon anhSanPham) {
		this.anhSanPham = anhSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	/**
	 * Create the panel.
	 */
	private ChiTietSanPhamService svCTSP = new ChiTietSanPhamService();

	public SanPham(int maSanPham, ImageIcon anhSanPham, String tenSanPham, boolean trangThai, DefaultTableModel model) {

		if (trangThai) {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					pnSize.setVisible(true);
					System.out.println(e.getPoint());
					repaint();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					
					if(!checkPoint(e.getPoint())) {
						pnSize.setVisible(false);
						repaint();
					}

				}
				private boolean checkPoint(Point point) {
					return point.getX()<190&&point.getX()>40&&point.getY()<190&&point.getY()>34;
				}

			});
		}
		
		this.anhSanPham = anhSanPham;
		this.tenSanPham = tenSanPham;
		this.trangThai = trangThai;
		this.maSanPham = maSanPham;

		setPreferredSize(new Dimension(230, 280));
		setSize(getPreferredSize());
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);

		pnSize = new JPanel();
		pnSize.setBackground(new Color(255, 255, 255, 140));
		pnSize.setVisible(false);
		pnSize.setBounds(0, 10, 230, 229);
		add(pnSize);
		pnSize.setLayout(null);
		JPanel pnHetHang = new JPanel();

		pnHetHang.setBackground(new Color(255, 255, 255, 140));
		pnHetHang.setBounds(0, 10, 230, 229);
		add(pnHetHang);
		pnHetHang.setLayout(null);

		JButton btnHtHng = new JButton("Hết hàng");
		btnHtHng.setForeground(SystemColor.controlLtHighlight);
		btnHtHng.setBackground(SystemColor.textHighlight);
		btnHtHng.setBounds(69, 101, 91, 27);
		pnHetHang.add(btnHtHng);

		pnHetHang.setVisible(false);

		JLabel lblAnhSanPham = new JLabel(anhSanPham);
		lblAnhSanPham.setBounds(10, 10, 210, 213);
		add(lblAnhSanPham);

		JLabel lblTenSP = new JLabel(tenSanPham);
		lblTenSP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenSP.setForeground(new Color(30, 144, 255));
		lblTenSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenSP.setBounds(18, 245, 194, 31);
		add(lblTenSP);
		if (!trangThai)
			pnHetHang.setVisible(true);
		List<ChiTietSanPhamViewModel> lstCTSP = svCTSP.getAll(maSanPham);
		pnSize.removeAll();
		pnSize.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.insets=new Insets(5, 5, 5, 5);
		int row = -1;
		for (ChiTietSanPhamViewModel vmCTSP : lstCTSP) {

			gbc.gridy = ++row ;
			JButton btn = new JButton(vmCTSP.getSize());
			btn.setForeground(SystemColor.controlLtHighlight);
			btn.setBackground(SystemColor.textHighlight);
			btn.setSize( 91, 27);
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < model.getRowCount(); i++) {
						if (model.getValueAt(i, 1).equals(vmCTSP.getMaChiTietSanPham())) {
							model.setValueAt(Integer.parseInt(model.getValueAt(i, 3).toString()) + 1, i, 3);
							return;
						}
					}
					model.addRow(new Object[] { tenSanPham, vmCTSP.getMaChiTietSanPham(),  vmCTSP.getGia().intValue(), 1,
							"<html><body style=';text-align: center; color: #1E90FF'><button><u>Xóa</u></button></body></html>" });

				}
			});
			pnSize.add(btn, gbc);
		}

	}

	public int getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(int maSanPham) {
		this.maSanPham = maSanPham;
	}
}
