/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import utilities.XImages;

public class DangXuat extends javax.swing.JFrame {

    public DangXuat() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        init();
    }

    public void init() {
        setIconImage(XImages.getIconApp());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lblThongBao = new javax.swing.JLabel();
        btnYes = new javax.swing.JButton();
        btnNo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ thống quản lý quán trà sữa ToTo");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/hoiCham2.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));

        lblThongBao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThongBao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblThongBao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 300, 30));

        btnYes.setBackground(new java.awt.Color(0, 65, 123));
        btnYes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnYes.setForeground(new java.awt.Color(255, 255, 255));
        btnYes.setText("Yes");
        btnYes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnYesMouseClicked(evt);
            }
        });
        btnYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYesActionPerformed(evt);
            }
        });
        getContentPane().add(btnYes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 150, 40));

        btnNo.setBackground(new java.awt.Color(255, 51, 0));
        btnNo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNo.setForeground(new java.awt.Color(255, 255, 255));
        btnNo.setText("No");
        btnNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNoMouseClicked(evt);
            }
        });
        btnNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 150, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNoMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnNoMouseClicked
 public void thongBao(String thongBao){
        lblThongBao.setText(thongBao);
    }
    
    public void yes(ActionListener actionListener) {
        btnYes.addActionListener(actionListener);
    }
    
    public void no(ActionListener actionListene){
        btnNo.addActionListener(actionListene);
    }
    private void btnYesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnYesMouseClicked

// TODO add your handling code here:
        WindowEvent closingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);

    }//GEN-LAST:event_btnYesMouseClicked

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNoActionPerformed

    private void btnYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnYesActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DangXuat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnYes;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblThongBao;
    // End of variables declaration//GEN-END:variables
}
