/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cafe.management.system;
import dao.ConnectionProvider;
import java.util.Iterator;
import dao.ProductDao;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author kingr
 */
public class LocalSQLConnection extends javax.swing.JFrame {

    /**
     * Creates new form LocalSQLConnection
     */
    public LocalSQLConnection() {
        initComponents();
       // initComponents();
       Seticon();
        connectbtn.setEnabled(false);
        setLocationRelativeTo(null); //makes aligned at center of screen
        setResizable(false);
       // setResizable(false);
        //setShape(new RoundRectangle2D.Double(0,0, 625, 350, 35, 35));
        setSize(550,420);
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
    public void validateFields() {
        String Username = username.getText();
        String Password = password.getText();
        String Server = server.getText();
        if(!Username.equals("") && !Server.equals("") && !password.equals(""))
            connectbtn.setEnabled(true);
        else
            connectbtn.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        connectbtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        server = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        username.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        username.setText("root");
        username.setMinimumSize(new java.awt.Dimension(96, 25));
        username.setPreferredSize(new java.awt.Dimension(96, 25));
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameKeyReleased(evt);
            }
        });
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 250, 31));

        connectbtn.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        connectbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/database.gif"))); // NOI18N
        connectbtn.setText("Connect");
        connectbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        connectbtn.setMaximumSize(new java.awt.Dimension(134, 25));
        connectbtn.setMinimumSize(new java.awt.Dimension(134, 25));
        connectbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectbtnActionPerformed(evt);
            }
        });
        getContentPane().add(connectbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(242, 242, 242));
        jLabel11.setText(" localhost: ");
        jLabel11.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel11.setMaximumSize(new java.awt.Dimension(88, 25));
        jLabel11.setMinimumSize(new java.awt.Dimension(88, 25));
        jLabel11.setPreferredSize(new java.awt.Dimension(88, 25));
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 155, -1, 31));

        password.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        password.setMaximumSize(new java.awt.Dimension(2147483647, 34));
        password.setMinimumSize(new java.awt.Dimension(96, 25));
        password.setPreferredSize(new java.awt.Dimension(96, 25));
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordKeyReleased(evt);
            }
        });
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 112, 250, 31));

        server.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        server.setText("3306");
        server.setMinimumSize(new java.awt.Dimension(96, 25));
        server.setPreferredSize(new java.awt.Dimension(96, 25));
        server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverActionPerformed(evt);
            }
        });
        server.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                serverKeyReleased(evt);
            }
        });
        getContentPane().add(server, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 154, 167, 31));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Connect to MySQL database.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void usernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_usernameKeyReleased

    private void connectbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectbtnActionPerformed
// TODO add your handling code here:
        ConnectionProvider.setusername(username.getText());
        ConnectionProvider.setpassword(password.getText());
        ConnectionProvider.setserver(server.getText());
        //new Login().setVisible(true);
        ImageIcon icon = new ImageIcon("src\\images\\database.gif");
        JOptionPane.showMessageDialog(null, "Details Saved! Proceed to Signup/Login !!", "Connecting to the Server...", JOptionPane.INFORMATION_MESSAGE, icon);
                
        setVisible(false);
    }//GEN-LAST:event_connectbtnActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void serverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serverActionPerformed

    private void passwordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_passwordKeyReleased

    private void serverKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serverKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_serverKeyReleased

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
            java.util.logging.Logger.getLogger(LocalSQLConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LocalSQLConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LocalSQLConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LocalSQLConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LocalSQLConnection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField server;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables

    private void Seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("salad.png")));
    }
}
