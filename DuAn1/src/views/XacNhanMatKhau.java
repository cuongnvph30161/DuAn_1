/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import interfaceservices.INhanVienService;
import interfaceservices.ITaiKhoanServicess;
import javax.swing.JOptionPane;
import services.NhanVienService;
import services.TaiKhoanServicess;
import utilities.XImages;

public class XacNhanMatKhau extends javax.swing.JFrame {

    public INhanVienService iNhanVienService = new NhanVienService();
    public ITaiKhoanServicess iTaiKhoanServicess = new TaiKhoanServicess();
    private static String email;

    public XacNhanMatKhau(String email) {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        lblMatMo1.setVisible(false);
        lblMatMo2.setVisible(false);
        this.email = email;

        init();
    }

    public void init() {
        setIconImage(XImages.getIconApp());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNhapMatKhauMoi = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        txtNhapLaiMatKhauMoi = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        btnHuy = new javax.swing.JButton();
        btnTiepTuc = new javax.swing.JButton();
        lblMatMo1 = new javax.swing.JLabel();
        lblMatMo2 = new javax.swing.JLabel();
        lblMatDong1 = new javax.swing.JLabel();
        lblMatDong2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ thống quản lý trà sữa ToTo");
        setMinimumSize(new java.awt.Dimension(411, 399));
        setPreferredSize(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nhập mật khẩu mới");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));
        getContentPane().add(txtNhapMatKhauMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 134, 246, 33));

        jLabel2.setText("Nhập lại mật khẩu mới");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 194, -1, -1));
        getContentPane().add(txtNhapLaiMatKhauMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 228, 246, 33));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("XÁC NHẬN MẬT KHẨU");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 33, -1, -1));

        btnHuy.setBackground(new java.awt.Color(204, 204, 204));
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        getContentPane().add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 292, -1, -1));

        btnTiepTuc.setBackground(new java.awt.Color(0, 65, 123));
        btnTiepTuc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTiepTuc.setForeground(new java.awt.Color(255, 255, 255));
        btnTiepTuc.setText("Tiếp tục");
        btnTiepTuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiepTucActionPerformed(evt);
            }
        });
        getContentPane().add(btnTiepTuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 292, -1, -1));

        lblMatMo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eye_20px.png"))); // NOI18N
        lblMatMo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMatMo1MouseClicked(evt);
            }
        });
        getContentPane().add(lblMatMo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 142, -1, 20));

        lblMatMo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eye_20px.png"))); // NOI18N
        lblMatMo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMatMo2MouseClicked(evt);
            }
        });
        getContentPane().add(lblMatMo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, -1, -1));

        lblMatDong1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/matdong.png"))); // NOI18N
        lblMatDong1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMatDong1MouseClicked(evt);
            }
        });
        getContentPane().add(lblMatDong1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, -1, -1));

        lblMatDong2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/matdong.png"))); // NOI18N
        lblMatDong2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMatDong2MouseClicked(evt);
            }
        });
        getContentPane().add(lblMatDong2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        new DangNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void lblMatDong1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatDong1MouseClicked
        if (lblMatDong1.isVisible()) {
            txtNhapMatKhauMoi.setEchoChar((char) 0);
            lblMatMo1.setVisible(true);
            lblMatDong1.setVisible(false);
        } else {
            txtNhapMatKhauMoi.setEchoChar((char) '\u2022');
        }
    }//GEN-LAST:event_lblMatDong1MouseClicked

    private void lblMatMo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatMo1MouseClicked
        if (lblMatMo1.isVisible()) {
            txtNhapMatKhauMoi.setEchoChar((char) '\u2022');
            lblMatDong1.setVisible(true);
            lblMatMo1.setVisible(false);
        } else {

        }
    }//GEN-LAST:event_lblMatMo1MouseClicked

    private void lblMatMo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatMo2MouseClicked
        if (lblMatMo2.isVisible()) {
            txtNhapLaiMatKhauMoi.setEchoChar((char) '\u2022');
            lblMatDong2.setVisible(true);
            lblMatMo2.setVisible(false);
        } else {

        }
    }//GEN-LAST:event_lblMatMo2MouseClicked

    private void lblMatDong2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatDong2MouseClicked
        if (lblMatDong2.isVisible()) {
            txtNhapLaiMatKhauMoi.setEchoChar((char) 0);
            lblMatMo2.setVisible(true);
            lblMatDong2.setVisible(false);
        } else {
            txtNhapLaiMatKhauMoi.setEchoChar((char) '\u2022');
        }
    }//GEN-LAST:event_lblMatDong2MouseClicked

    private void btnTiepTucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiepTucActionPerformed
        String matKhauMoi = txtNhapMatKhauMoi.getText();
        String nhapLaiMatKhauMoi = txtNhapLaiMatKhauMoi.getText();
        if (matKhauMoi.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới");
            return;
        }
        if (nhapLaiMatKhauMoi.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập lại mật khẩu mới");
            return;
        }
        if (!matKhauMoi.equals(nhapLaiMatKhauMoi)) {
            JOptionPane.showMessageDialog(this, "2 mật khẩu phải trùng nhau");
            return;
        }

        int maNhanVien = iNhanVienService.getMaNhanVienByEmail(email);
        System.out.println("email XacNhanMatKhau" + " " + email);
        System.out.println("maNV" + " " + maNhanVien);
        String updateSuccess = iTaiKhoanServicess.updateMatKhauByMaNhanVien(matKhauMoi, maNhanVien);
        System.out.println("tai khoan update" + " " + updateSuccess);
        JOptionPane.showMessageDialog(this, updateSuccess);
        new DangNhap().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnTiepTucActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                XacNhanMatKhau xacNhanMatKhau = new XacNhanMatKhau(email);
                xacNhanMatKhau.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnTiepTuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblMatDong1;
    private javax.swing.JLabel lblMatDong2;
    private javax.swing.JLabel lblMatMo1;
    private javax.swing.JLabel lblMatMo2;
    private javax.swing.JPasswordField txtNhapLaiMatKhauMoi;
    private javax.swing.JPasswordField txtNhapMatKhauMoi;
    // End of variables declaration//GEN-END:variables
}
