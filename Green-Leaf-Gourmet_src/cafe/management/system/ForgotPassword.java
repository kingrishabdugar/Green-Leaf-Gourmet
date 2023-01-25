/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cafe.management.system;

import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import javax.swing.JOptionPane;
import model.User;
import dao.UserDao;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.util.Properties;
import common.PanelScale;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author kingrishabdugar
 */
public class ForgotPassword extends javax.swing.JFrame {

    private String dbAnswer = null;
    private String email = null;
    private String emailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
    public String userEmail="";
    /**
     * Creates new form ForgotPassword
     */
    public ForgotPassword() {
        initComponents();
        Seticon();
        setTitle(" Green-Leaf-Gourmet ");
        setLocationRelativeTo(null); //makes aligned at center of screen
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(1100,680); 
        setLocationRelativeTo(null);
        btnupdate.setEnabled(false);
        btnsearch.setEnabled(false);
        txtsq.setEditable(false);//security question cannot be changed !

    }
    
     public ForgotPassword(String email) {
        initComponents();
        Seticon();
        userEmail = email;
        txtemail.setText(userEmail);
        validateEmail();
        if (email.matches(emailPattern)&& !email.equals("admin@gmail.com")) {
            btnsearch.setEnabled(true);
        }
        else if(email.equals("admin@gmail.com")) //Admin Details Secured 
        {
            JOptionPane.showMessageDialog(null, "<html><b style=\"colorred\">Admin Details cannot be changed !</b></html>", "Message", JOptionPane.ERROR_MESSAGE);
            btnsearch.setEnabled(false);
        }
        setTitle(" Green-Leaf-Gourmet ");
        setLocationRelativeTo(null); //makes aligned at center of screen
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(1100,680); 
        setLocationRelativeTo(null);
        btnupdate.setEnabled(false);
        txtsq.setEditable(false);//security question cannot be changed !
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
                         //   new Home(email).setVisible(true);
                        }
                    }
                });
    }

    public void clear() {
        btnupdate.setEnabled(false);
        btnsearch.setEnabled(false);
        txtemail.setEditable(true);
        txtemail.setText("");
        txtsq.setText("");
        txtans.setText("");
        txtpassword.setText("");

    }

    //Admin Details cannot be changed 
    public void validateEmail() {
        email = txtemail.getText();
        if (email.matches(emailPattern)&& !email.equals("admin@gmail.com")) {
            btnsearch.setEnabled(true);
        }
        else if(email.equals("admin@gmail.com")) //Admin Details Secured 
        {
            JOptionPane.showMessageDialog(null, "<html><b style=\"colorred\">Admin Details cannot be changed !</b></html>", "Message", JOptionPane.ERROR_MESSAGE);
            btnsearch.setEnabled(false);
        }
        else {
            btnsearch.setEnabled(false);
        }
    }

    //as soon as user finishes typing data releases key we validate the data 
    public void validateFields() {
        String password = txtpassword.getText();
        String answer = txtans.getText();
        String securityQuestion = txtsq.getText();
        if (!password.equals("") && !answer.equals("") && !securityQuestion.equals("")) {
            btnupdate.setEnabled(true);
        } else {
            btnupdate.setEnabled(false);
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

        try
        {
            jPanel1 = new common.PanelScale("/images/FS_ForgotPassword.png");
            txtsq = new javax.swing.JTextField();
            txtans = new javax.swing.JTextField();
            txtpassword = new javax.swing.JPasswordField();
            btnupdate = new javax.swing.JButton();
            btnclear = new javax.swing.JButton();
            btnexit = new javax.swing.JButton();
            jLabel1 = new javax.swing.JLabel();
            jButton4 = new javax.swing.JButton();
            jLabel2 = new javax.swing.JLabel();
            jButton5 = new javax.swing.JButton();
            jLabel3 = new javax.swing.JLabel();
            btnsearch = new javax.swing.JButton();
            jLabel4 = new javax.swing.JLabel();
            txtemail = new javax.swing.JTextField();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            pack();
        }
        catch (IOException ex)
        {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtsq.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        txtsq.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtsq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsqKeyReleased(evt);
            }
        });

        txtans.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        txtans.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtansActionPerformed(evt);
            }
        });
        txtans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtansKeyReleased(evt);
            }
        });

        txtpassword.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        txtpassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpasswordKeyReleased(evt);
            }
        });

        btnupdate.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.gif"))); // NOI18N
        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btnclear.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear.gif"))); // NOI18N
        btnclear.setText("Clear");
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });

        btnexit.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smallexit.gif"))); // NOI18N
        btnexit.setText("Exit");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(172, 72, 12));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setText(" Email : ");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(112, 40, 6), 2, true));
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setOpaque(true);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login30.gif"))); // NOI18N
        jButton4.setText("Login");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(172, 72, 12));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setText(" Security Question : ");
        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(112, 40, 6), 2, true));
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setOpaque(true);

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/signup.gif"))); // NOI18N
        jButton5.setText("Sign Up");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(172, 72, 12));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(242, 242, 242));
        jLabel3.setText(" Your Answer : ");
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(112, 40, 6), 2, true));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel3.setOpaque(true);

        btnsearch.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.gif"))); // NOI18N
        btnsearch.setText("Search");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(172, 72, 12));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(242, 242, 242));
        jLabel4.setText(" Enter New Password : ");
        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(112, 40, 6), 2, true));
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.setOpaque(true);

        txtemail.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        txtemail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtemailKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(250, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtemail)
                    .addComponent(txtsq)
                    .addComponent(txtans)
                    .addComponent(txtpassword)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        .addGap(136, 136, 136)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnupdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28)
                        .addComponent(btnclear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29)
                        .addComponent(btnexit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15)
                .addComponent(btnsearch, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearch))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(txtsq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(txtans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnupdate)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnclear)
                        .addComponent(btnexit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(200, 200, 200))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtansActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtansActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnclearActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        // TODO add your handling code here:
        if(userEmail != "")
        {
            setVisible(false);
        }
        else
        {
        int a = JOptionPane.showConfirmDialog(null, "Do you really wish to close the Application ?", "Select", JOptionPane.YES_NO_CANCEL_OPTION);
        if (a == 0) {
            System.exit(0);
        }
        //setVisible(false);
        //new Thankyou().setVisible(true);
        }
    }//GEN-LAST:event_btnexitActionPerformed

    private void txtemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyReleased
        // TODO add your handling code here:
        validateEmail();
    }//GEN-LAST:event_txtemailKeyReleased

    private void txtsqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsqKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_txtsqKeyReleased

    private void txtansKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtansKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_txtansKeyReleased

    private void txtpasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasswordKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_txtpasswordKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
        email = txtemail.getText();
        validateEmail();
        User user = null;
        user = UserDao.getSecurityQuestion(email);
        if (user == null) {
            JOptionPane.showMessageDialog(null, "<html><b style=\"colorred\">Incorrect Email</b></html>", "Message", JOptionPane.ERROR_MESSAGE);

        } else {
            btnsearch.setEnabled(false);
            txtemail.setEditable(false);
            dbAnswer = user.getAnswer();
            txtsq.setText(user.getSecurityQuestion());
            validateFields();

        }
    }//GEN-LAST:event_btnsearchActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        String answer = CafeManagementSystem.apostrophe(txtans.getText());
        String newPassword = txtpassword.getText();
        if (answer.equals(dbAnswer)) {
            UserDao.update(email, newPassword);
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "<html><b style=\"colorred\">Incorrect Answer</b></html>", "Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Sign_Up().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
        txtemail.setText(userEmail);
        validateEmail();
    }//GEN-LAST:event_txtemailActionPerformed

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
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ForgotPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtans;
    private javax.swing.JTextField txtemail;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtsq;
    // End of variables declaration//GEN-END:variables

        private void Seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("salad.png")));
    }
}
