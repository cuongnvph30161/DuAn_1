/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainmodel.TaiKhoanDomail;
import interfaceservices.ITaiKhoanServicess;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import utilities.XImages;
import viewmodel.TaiKhoanViewModel;
import interfaceservices.ITaiKhoanServicess;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import services.TaiKhoanServicess;

public class DoiMatKhau extends javax.swing.JFrame {

    private String maTaiKhoan;

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }
    public ITaiKhoanServicess iTaiKhoanServicess = new TaiKhoanServicess();

    public DoiMatKhau(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        lblMatMo1.setVisible(false);
        lblMatMo2.setVisible(false);
        lblMatMo3.setVisible(false);
        init();
    }

    public void init() {
        setIconImage(XImages.getIconApp());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMatKhauHienTai = new javax.swing.JPasswordField();
        btnDoiMatKhau = new javax.swing.JButton();
        txtMatKhauMoi = new javax.swing.JPasswordField();
        txtNhapLaiMatKhauMoi = new javax.swing.JPasswordField();
        lblMatDong1 = new javax.swing.JLabel();
        lblMatDong2 = new javax.swing.JLabel();
        lblMatDong3 = new javax.swing.JLabel();
        lblBackLai = new javax.swing.JLabel();
        lblMatMo1 = new javax.swing.JLabel();
        lblMatMo2 = new javax.swing.JLabel();
        lblMatMo3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ thống quản lý quán trà sữa ToTo");
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setPreferredSize(new java.awt.Dimension(430, 430));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("ĐỔI MẬT KHẨU");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 18, -1, -1));

        jLabel2.setText("Mật khẩu hiện tại");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 74, 110, -1));

        jLabel3.setText("Mật khẩu mới");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 148, 110, -1));

        jLabel4.setText("Nhập lại mật khẩu mới");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 216, -1, -1));
        getContentPane().add(txtMatKhauHienTai, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 108, 253, 34));

        btnDoiMatKhau.setBackground(new java.awt.Color(22, 139, 89));
        btnDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiMatKhau.setText("Đổi mật khẩu");
        btnDoiMatKhau.setFocusable(false);
        btnDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDoiMatKhauMouseClicked(evt);
            }
        });
        btnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMatKhauActionPerformed(evt);
            }
        });
        getContentPane().add(btnDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 301, 253, 40));
        getContentPane().add(txtMatKhauMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 176, 253, 34));
        getContentPane().add(txtNhapLaiMatKhauMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 244, 253, 34));

        lblMatDong1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/matdong.png"))); // NOI18N
        lblMatDong1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMatDong1MouseClicked(evt);
            }
        });
        getContentPane().add(lblMatDong1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, 20));

        lblMatDong2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/matdong.png"))); // NOI18N
        lblMatDong2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMatDong2MouseClicked(evt);
            }
        });
        getContentPane().add(lblMatDong2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, -1, 20));

        lblMatDong3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/matdong.png"))); // NOI18N
        lblMatDong3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMatDong3MouseClicked(evt);
            }
        });
        getContentPane().add(lblMatDong3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, -1, 20));

        lblBackLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/double_left_30px.png"))); // NOI18N
        lblBackLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackLaiMouseClicked(evt);
            }
        });
        getContentPane().add(lblBackLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        lblMatMo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eye_20px.png"))); // NOI18N
        lblMatMo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMatMo1MouseClicked(evt);
            }
        });
        getContentPane().add(lblMatMo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 30, 40));

        lblMatMo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eye_20px.png"))); // NOI18N
        lblMatMo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMatMo2MouseClicked(evt);
            }
        });
        getContentPane().add(lblMatMo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 30, 40));

        lblMatMo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eye_20px.png"))); // NOI18N
        lblMatMo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMatMo3MouseClicked(evt);
            }
        });
        getContentPane().add(lblMatMo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 30, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiMatKhauMouseClicked
        WindowEvent closingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
    }//GEN-LAST:event_btnDoiMatKhauMouseClicked

    private void lblBackLaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackLaiMouseClicked
        this.dispose();
    }//GEN-LAST:event_lblBackLaiMouseClicked

    private void lblMatDong1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatDong1MouseClicked
        if (lblMatDong1.isVisible()) {
            txtMatKhauHienTai.setEchoChar((char) 0);
            lblMatMo1.setVisible(true);
            lblMatDong1.setVisible(false);
        } else {
            txtMatKhauHienTai.setEchoChar((char) '\u2022');
        }
    }//GEN-LAST:event_lblMatDong1MouseClicked

    private void lblMatMo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatMo1MouseClicked
        if (lblMatMo1.isVisible()) {
            txtMatKhauHienTai.setEchoChar((char) '\u2022');
            lblMatDong1.setVisible(true);
            lblMatMo1.setVisible(false);
        } else {

        }
    }//GEN-LAST:event_lblMatMo1MouseClicked

    private void lblMatMo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatMo2MouseClicked
        if (lblMatMo2.isVisible()) {
            txtMatKhauMoi.setEchoChar((char) '\u2022');
            lblMatDong2.setVisible(true);
            lblMatMo2.setVisible(false);
        } else {
//
        }
    }//GEN-LAST:event_lblMatMo2MouseClicked

    private void lblMatMo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatMo3MouseClicked
        if (lblMatMo3.isVisible()) {
            txtNhapLaiMatKhauMoi.setEchoChar((char) '\u2022');
            lblMatDong3.setVisible(true);
            lblMatMo3.setVisible(false);
        } else {

        }
    }//GEN-LAST:event_lblMatMo3MouseClicked

    private void lblMatDong2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatDong2MouseClicked
        if (lblMatDong2.isVisible()) {
            txtMatKhauMoi.setEchoChar((char) 0);
            lblMatMo2.setVisible(true);
            lblMatDong2.setVisible(false);
        } else {
            txtMatKhauMoi.setEchoChar((char) '\u2022');
        }
    }//GEN-LAST:event_lblMatDong2MouseClicked

    private void lblMatDong3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMatDong3MouseClicked
        if (lblMatDong3.isVisible()) {
            txtNhapLaiMatKhauMoi.setEchoChar((char) 0);
            lblMatMo3.setVisible(true);
            lblMatDong3.setVisible(false);
        } else {
            txtNhapLaiMatKhauMoi.setEchoChar((char) '\u2022');
        }
    }//GEN-LAST:event_lblMatDong3MouseClicked
    public TaiKhoanDomail getData() {
        TaiKhoanDomail taiKhoanDomail = new TaiKhoanDomail();
        String matKhauHienTai = txtMatKhauHienTai.getText();
        String matKhauMoi = txtMatKhauMoi.getText();
        String nhapLaiMatKhauMoi = txtNhapLaiMatKhauMoi.getText();
        String checkMatKhau = iTaiKhoanServicess.checkMatKhau(maTaiKhoan);
// Kiểm tra mật khẩu hiện tại
        if (matKhauHienTai.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu hiện tại không được chứa dấu cách");
            return null;
        }

// Kiểm tra mật khẩu mới
        if (matKhauMoi.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới không được chứa dấu cách");
            return null;
        }

        // Kiểm tra nhập lại mật khẩu 
        if (nhapLaiMatKhauMoi.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Nhập lại mật khẩu không được chứa dấu cách");
            return null;
        }
        // Kiểm tra trường rỗng
        if (matKhauHienTai.trim().isEmpty() || matKhauMoi.trim().isEmpty() || nhapLaiMatKhauMoi.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống");
            return null;
        }

        // Kiểm tra mật khẩu hiện tại
        if (!matKhauHienTai.equals(checkMatKhau)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng mật khẩu hiện tại");
            return null;
        }

        // Kiểm tra mật khẩu mới và nhập lại mật khẩu mới
        if (!matKhauMoi.equals(nhapLaiMatKhauMoi)) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới khớp nhau");
            return null;
        }
        taiKhoanDomail.setMatKhau(matKhauMoi);
        return taiKhoanDomail;
    }
    private void btnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMatKhauActionPerformed
        TaiKhoanDomail taiKhoanDomail = getData();
        if (taiKhoanDomail == null) {
            return;
        }
        JOptionPane.showMessageDialog(this, iTaiKhoanServicess.doiMatKhau(taiKhoanDomail.getMatKhau(), maTaiKhoan));
        Frame[] frames = Frame.getFrames();
        for (Frame frame : frames) {
            frame.dispose();
        }

        // Mở lại frame đăng nhập
        DangNhap dangNhapFrame = new DangNhap();
        dangNhapFrame.setVisible(true);

    }//GEN-LAST:event_btnDoiMatKhauActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                String maTaiKhoan = ""; // Lấy mã tài khoản từ giao diện đăng nhập
                DoiMatKhau doiMatKhau = new DoiMatKhau(maTaiKhoan);
                doiMatKhau.setMaTaiKhoan(maTaiKhoan);
                doiMatKhau.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiMatKhau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblBackLai;
    private javax.swing.JLabel lblMatDong1;
    private javax.swing.JLabel lblMatDong2;
    private javax.swing.JLabel lblMatDong3;
    private javax.swing.JLabel lblMatMo1;
    private javax.swing.JLabel lblMatMo2;
    private javax.swing.JLabel lblMatMo3;
    private javax.swing.JPasswordField txtMatKhauHienTai;
    private javax.swing.JPasswordField txtMatKhauMoi;
    private javax.swing.JPasswordField txtNhapLaiMatKhauMoi;
    // End of variables declaration//GEN-END:variables
}
