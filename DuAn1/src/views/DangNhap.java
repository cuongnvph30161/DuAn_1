/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainmodel.TaiKhoanDomail;
import interfaceservices.ITaiKhoanServicess;
import javax.swing.JOptionPane;
import services.TaiKhoanServicess;
import utilities.Uhelper;
import utilities.XImages;

/**
 *
 * @author ADMIN
 */
public class DangNhap extends javax.swing.JFrame {

    public ITaiKhoanServicess iTaiKhoanService = new TaiKhoanServicess();

    /**
     * Creates new form DangNhap
     */
    public DangNhap() {

        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        init();

    }

    public void init() {
        setIconImage(XImages.getIconApp());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnDangNhap = new javax.swing.JButton();
        lblQuenMatKhau = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ thống quản lý quán trà sữa ToTo");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Đăng Nhập");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 145, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Mật Khẩu");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Tài Khoản");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        txtTaiKhoan.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txtTaiKhoan.setForeground(new java.awt.Color(128, 128, 128));
        txtTaiKhoan.setText("tên tài khoản...");
        txtTaiKhoan.setBorder(null);
        txtTaiKhoan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTaiKhoanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTaiKhoanFocusLost(evt);
            }
        });
        txtTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTaiKhoanMouseClicked(evt);
            }
        });
        txtTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaiKhoanActionPerformed(evt);
            }
        });
        jPanel1.add(txtTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 240, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/user.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/key.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel11.setText("______________________________________________________");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 260, -1));

        btnDangNhap.setBackground(new java.awt.Color(204, 255, 255));
        btnDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 255), null, null));
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        jPanel1.add(btnDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 270, 30));

        lblQuenMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblQuenMatKhau.setText("Quên mật khẩu?");
        lblQuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuenMatKhauMouseClicked(evt);
            }
        });
        jPanel1.add(lblQuenMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, -1, -1));

        jLabel14.setText("_________________________________________________________");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 280, -1));

        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txtMatKhau.setForeground(new java.awt.Color(128, 128, 128));
        txtMatKhau.setText("mật khẩu...");
        txtMatKhau.setBorder(null);
        txtMatKhau.setEchoChar('\u0000');
        txtMatKhau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMatKhauFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMatKhauFocusLost(evt);
            }
        });
        jPanel1.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 152, 240, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 330, 340));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/anhnenchao.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTaiKhoanMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_txtTaiKhoanMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel1MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void txtTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaiKhoanActionPerformed

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        String maTaiKhoan = txtTaiKhoan.getText();
        String matKhau = txtMatKhau.getText();

        if (maTaiKhoan.trim().equals("") || matKhau.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin đăng nhập");
            return;
        } else if (maTaiKhoan.contains(" ") || matKhau.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Tài khoản và mật khẩu không được chứa dấu cách");
            return;
        }

        TaiKhoanDomail taiKhoan = iTaiKhoanService.getTaiKhoanByMaTaiKhoanAndMatKhau(maTaiKhoan, matKhau);
        if (taiKhoan == null) {
            JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng");
            return;
        }

        try {
            int trangThai = taiKhoan.getTrangThai();
            if (trangThai == 0) {
                JOptionPane.showMessageDialog(this, "Tài khoản của bạn đã bị khoá");
                return;
            }
        } catch (NullPointerException e) {
            return;
        }
        // Kiểm tra tài khoản và mật khẩu (phân biệt chữ hoa/chữ thường)
        if (taiKhoan.getMaTaiKhoan().equals(maTaiKhoan) && taiKhoan.getMatKhau().equals(matKhau)) {
            switch (taiKhoan.getRole()) {
                case QuanLy:
                    TraSua_QL traSuaQL = new TraSua_QL(maTaiKhoan);
                    traSuaQL.setMaTaiKhoan(maTaiKhoan);
                    traSuaQL.setVisible(true);
                    this.dispose();
                    break;
                case PhaChe:
                    TraSua_PC traSua_PC = new TraSua_PC(maTaiKhoan);
                    traSua_PC.setMaTaiKhoan(maTaiKhoan);
                    traSua_PC.setVisible(true);
                    this.dispose();
                    break;
                case NhanVien:
                    TraSua_NV traSua_NV = new TraSua_NV(maTaiKhoan);
                    traSua_NV.setMaTaiKhoan(maTaiKhoan);
                    traSua_NV.setVisible(true);
                    this.dispose();
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Role không hợp lệ");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng");
        }
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void lblQuenMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuenMatKhauMouseClicked
        new QuenMatKhau().setVisible(true);
        dispose();
    }//GEN-LAST:event_lblQuenMatKhauMouseClicked

    private void txtTaiKhoanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTaiKhoanFocusGained
        // TODO add your handling code here:
        if (txtTaiKhoan.getText().equalsIgnoreCase("tên tài khoản...")) {
            txtTaiKhoan.setText(null);
            txtTaiKhoan.requestFocus();
            Uhelper.removePlayhoder(txtTaiKhoan);
        }
    }//GEN-LAST:event_txtTaiKhoanFocusGained

    private void txtTaiKhoanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTaiKhoanFocusLost
        // TODO add your handling code here:
        if(txtTaiKhoan.getText().length()==0){
            Uhelper.adPlayhoder(txtTaiKhoan);
            txtTaiKhoan.setText("tên tài khoản...");
        }
    }//GEN-LAST:event_txtTaiKhoanFocusLost

    private void txtMatKhauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMatKhauFocusGained
        // TODO add your handling code here:
        if(txtMatKhau.getText().equalsIgnoreCase("mật khẩu...")){
            txtMatKhau.setText(null);
            txtMatKhau.requestFocus();
            txtMatKhau.setEchoChar('\u2022');
            Uhelper.removePlayhoder(txtMatKhau);
        }
    }//GEN-LAST:event_txtMatKhauFocusGained

    private void txtMatKhauFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMatKhauFocusLost
        // TODO add your handling code here:
         if(txtMatKhau.getText().length()==0){
            Uhelper.adPlayhoder(txtMatKhau);
            txtMatKhau.setText("mật khẩu...");
            txtMatKhau.setEchoChar('\u0000');
            
        }
    }//GEN-LAST:event_txtMatKhauFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblQuenMatKhau;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
