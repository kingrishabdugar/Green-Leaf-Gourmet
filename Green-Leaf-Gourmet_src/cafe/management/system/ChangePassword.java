/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cafe.management.system;

import dao.UserDao;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author kingrishabdugar
 */
public class ChangePassword extends javax.swing.JFrame {

    public String userEmail;

    /**
     * Creates new form ChangePassword
     */
    public ChangePassword() {
        initComponents();
        setLocationRelativeTo(null); //makes aligned at center of screen
        //setAlwaysOnTop(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(625, 390);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
       addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
                        if( result==JOptionPane.OK_OPTION){
                            // NOW we change it to dispose on close..
                            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            setVisible(false);
                            dispose();
                        }
                    }
                });
    }

    public ChangePassword(String email) {
        initComponents();
        if(email.equals("admin@gmail.com"))
        {
            JOptionPane.showMessageDialog(null, "<html><b style=\"colorred\">Admin Details cannot be changed !</b></html>", "Message", JOptionPane.ERROR_MESSAGE);
            forgotpassword.setEnabled(false);
        }
        Seticon();
        userEmail = email;
        btnupdate4.setEnabled(false);
        setLocationRelativeTo(null); //makes aligned at center of screen
        setResizable(false);
        // setResizable(false);
        //setShape(new RoundRectangle2D.Double(0,0, 625, 350, 35, 35));
        setLocationRelativeTo(null);
        setSize(625, 390);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
       addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
                        if( result==JOptionPane.OK_OPTION){
                            // NOW we change it to dispose on close..
                            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            setVisible(false);
                            dispose();
                        }
                    }
                });
    }

    public void validateField() {
        String oldPassword = txtold.getText();
        String newPassword = txtnew.getText();
        String confirmPassword = txtconfirm.getText();
        if(userEmail.equals("admin@gmail.com")) //Admin Details Secured 
        {
            JOptionPane.showMessageDialog(null, "<html><b style=\"colorred\">Admin Details cannot be changed !</b></html>", "Message", JOptionPane.ERROR_MESSAGE);
            btnupdate4.setEnabled(false);
            forgotpassword.setEnabled(false);
        }
        else
        {
        if (!oldPassword.equals("") && !newPassword.equals("") && !confirmPassword.equals("") && newPassword.equals(confirmPassword)) {
            btnupdate4.setEnabled(true);
        } else {
            btnupdate4.setEnabled(false);
        }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtconfirm = new javax.swing.JPasswordField();
        txtold = new javax.swing.JPasswordField();
        txtoldpassword = new javax.swing.JLabel();
        txtnewpassword = new javax.swing.JLabel();
        txtnew = new javax.swing.JPasswordField();
        forgotpassword = new javax.swing.JButton();
        txtconfirmpassword = new javax.swing.JLabel();
        btnupdate4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtconfirm.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtconfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtconfirmActionPerformed(evt);
            }
        });
        txtconfirm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtconfirmKeyReleased(evt);
            }
        });
        getContentPane().add(txtconfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 178, 282, 40));

        txtold.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtoldActionPerformed(evt);
            }
        });
        txtold.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtoldKeyReleased(evt);
            }
        });
        getContentPane().add(txtold, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 76, 282, 40));

        txtoldpassword.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtoldpassword.setText("Old Password :");
        getContentPane().add(txtoldpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 75, 128, 40));

        txtnewpassword.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtnewpassword.setText("New Password :");
        getContentPane().add(txtnewpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 126, 136, 40));

        txtnew.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtnew.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnewKeyReleased(evt);
            }
        });
        getContentPane().add(txtnew, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 127, 282, 40));

        forgotpassword.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        forgotpassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/secure1.gif"))); // NOI18N
        forgotpassword.setText("Forgot Password ?");
        forgotpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forgotpasswordActionPerformed(evt);
            }
        });
        getContentPane().add(forgotpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 278, 282, -1));

        txtconfirmpassword.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtconfirmpassword.setText("Confirm Password :");
        getContentPane().add(txtconfirmpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 177, 166, 40));

        btnupdate4.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnupdate4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.gif"))); // NOI18N
        btnupdate4.setText("Update");
        btnupdate4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnupdate4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdate4ActionPerformed(evt);
            }
        });
        getContentPane().add(btnupdate4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 228, 132, 40));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear.gif"))); // NOI18N
        jButton3.setText("Clear");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 228, 114, 40));

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/secure1.gif"))); // NOI18N
        jButton8.setText("Forgot Password ?");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(5673, 153, -1, 55));

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/secure1.gif"))); // NOI18N
        jButton9.setText("Forgot Password ?");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(5683, 163, -1, 55));

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/secure1.gif"))); // NOI18N
        jButton10.setText("Forgot Password ?");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(5693, 173, -1, 55));

        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/secure1.gif"))); // NOI18N
        jButton11.setText("Forgot Password ?");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(5703, 183, -1, 55));

        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/secure1.gif"))); // NOI18N
        jButton12.setText("Forgot Password ?");
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(5713, 193, -1, 55));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ChangePassword.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtconfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtconfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtconfirmActionPerformed

    private void txtconfirmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtconfirmKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtconfirmKeyReleased

    private void txtoldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtoldKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtoldKeyReleased

    private void txtnewKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnewKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtnewKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new ChangePassword(userEmail).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtoldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtoldActionPerformed

    private void btnupdate4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdate4ActionPerformed
        // TODO add your handling code here:
        String oldPassword = txtold.getText();
        String newPassword = txtnew.getText();
        UserDao.changePassword(userEmail, oldPassword, newPassword);
        setVisible(false);
        new ChangePassword(userEmail).setVisible(true);
    }//GEN-LAST:event_btnupdate4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new ForgotPassword().setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void forgotpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forgotpasswordActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new ForgotPassword(userEmail).setVisible(true);
    }//GEN-LAST:event_forgotpasswordActionPerformed

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
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangePassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnupdate4;
    private javax.swing.JButton forgotpassword;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField txtconfirm;
    private javax.swing.JLabel txtconfirmpassword;
    private javax.swing.JPasswordField txtnew;
    private javax.swing.JLabel txtnewpassword;
    private javax.swing.JPasswordField txtold;
    private javax.swing.JLabel txtoldpassword;
    // End of variables declaration//GEN-END:variables
 private void Seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("salad.png")));
    }
}
