/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cafe.management.system;

import javax.swing.table.DefaultTableModel;
import dao.UserDao;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import model.User;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author kingrishabdugar
 */
public class VerifyUsers extends javax.swing.JFrame {

    public String userEmail;
    JDialog dialogLoading = new JDialog();

    /**
     * Creates new form VerifyUsers
     */
    public VerifyUsers() {
        initComponents();
        Seticon();
        setTitle(" Green-Leaf-Gourmet ");
        setLocationRelativeTo(null); //makes aligned at center of screen
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialogLoading.dispose();
                int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
                if (result == JOptionPane.OK_OPTION) {
                    // NOW we change it to dispose on close..
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    setVisible(false);
                    dispose();
                }
            }
        });
        //  new Home().setVisible(true);
        // setShape(new RoundRectangle2D.Double(0,0, 1024, 576, 35, 35));
        JTableHeader boldheader1 = jTable1.getTableHeader();
        boldheader1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        //  ((DefaultTableCellHeaderRenderer) boldheader1.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

    }

    public VerifyUsers(String email,JFrame oldclass) {
        initComponents();
        Seticon();
        setTitle(" Green-Leaf-Gourmet ");
        setLocationRelativeTo(null); //makes aligned at center of screen
        //setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialogLoading.dispose();
                int result = JOptionPane.showConfirmDialog(null, "<html><b style=\"color:Green\">Are you Sure❓</b></html>");
                if (result == JOptionPane.OK_OPTION) {
                    // NOW we change it to dispose on close..
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    oldclass.setVisible(true);
                    dialogLoading.dispose();
                    dispose();
                    setVisible(false);
                }
            }
            public void windowIconified(WindowEvent e) {
                dialogLoading.dispose();
            }
        });

        // setShape(new RoundRectangle2D.Double(0,0, 1024, 576, 35, 35));
        JTableHeader boldheader1 = jTable1.getTableHeader();
        boldheader1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        //  ((DefaultTableCellHeaderRenderer) boldheader1.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        userEmail = email;
    }

    public void getAllRecords(String email) {
        CafeManagementSystem.createDialog(dialogLoading, "/images/Loading GIFs/Orange.gif");

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0); //This line properly refreshed after update/delete
                ArrayList<User> list = UserDao.getAllRecords(email);
                Iterator<User> itr = list.iterator();
                while (itr.hasNext()) {
                    User userObj = itr.next();
                    if (!userObj.getEmail().equals("admin@gmail.com")) {
                        dtm.addRow(new Object[]{userObj.getId(), userObj.getName(), userObj.getEmail(), userObj.getMobileNumber(), userObj.getAddress(), userObj.getSecurityQuestion(), userObj.getStatus()});
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        dialogLoading.dispose();
                    }
                });
            }
        };
        worker.execute();
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
            jPanel1 = new common.PanelScale("/images/FS_Verify.png");
            jLabel2 = new javax.swing.JLabel();
            txtemail = new javax.swing.JTextField();
            jScrollPane1 = new javax.swing.JScrollPane();
            jTable1 = new javax.swing.JTable();
            jLabel1 = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setMinimumSize(new java.awt.Dimension(1024, 576));
            addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentShown(java.awt.event.ComponentEvent evt) {
                    formComponentShown(evt);
                }
            });

            pack();
        }
        catch (IOException ex)
        {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        jLabel2.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.gif"))); // NOI18N
        jLabel2.setText("Search");

        txtemail.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 17)); // NOI18N
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtemailKeyReleased(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Mobile Number", "Address", "Security Question", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTable1MouseDragged(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Instructions : Click/Tap on Row to Change User Status & Drag the Row to Delete User !");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(423, 423, 423)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19)
                .addComponent(txtemail, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addContainerGap(420, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jScrollPane1)
                .addGap(74, 74, 74))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, txtemail});

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyReleased
        // TODO add your handling code here:
        String email = txtemail.getText(); //whenever search is done all rows are removed from the table and only searched row is displayed
        getAllRecords(email);
    }//GEN-LAST:event_txtemailKeyReleased

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        getAllRecords("");
    }//GEN-LAST:event_formComponentShown
//This event is needed when user clicks on a row and system should ask user's choice to modify the row or delete it
//Here we don't delete row only change status
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        JDialog dialogLoading = new JDialog();
        CafeManagementSystem.createDialog(dialogLoading, "/images/Loading GIFs/Burger.gif");
        dialogLoading.setAlwaysOnTop(false);

        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String email = model.getValueAt(index, 2).toString();  //indexes here are actually the column numbers viz id , name etc
        String status = model.getValueAt(index, 6).toString(); //indexes here are actually the column numbers viz id , name etc
        if (status.equals("true")) {
            status = "false";
        } else {
            status = "true";
        }

        String userEmail = email;
        String userStatus = status;
        int a = JOptionPane.showConfirmDialog(null, "Do you want to change status of " + email + "", "select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            SwingWorker<Void, Void> worker;
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    UserDao.changeStatus(userEmail, userStatus);
                    //((DefaultTableModel) jTable1.getModel()).fireTableStructureChanged();

                    return null;
                }

                @Override
                protected void done() {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {

                            setVisible(false);
                            setVisible(true);
                            dialogLoading.setVisible(false);
                        }
                    });
                }
            };
            worker.execute();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseDragged
        // TODO add your handling code here:
        //JDialog dialogLoading = new JDialog();
        CafeManagementSystem.createDialog(dialogLoading, "/images/Loading GIFs/Burger.gif");
        dialogLoading.setAlwaysOnTop(false);

        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String id = model.getValueAt(index, 0).toString();
        String name = model.getValueAt(index, 1).toString();
        int a = JOptionPane.showConfirmDialog(null, "Do you want to Remove " + name + " User ?", "Select", JOptionPane.YES_NO_CANCEL_OPTION);
        if (a == 0) {
            SwingWorker<Void, Void> worker;
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    UserDao.delete(id);
                    return null;
                }

                @Override
                protected void done() {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {

                            setVisible(false);
                            setVisible(true);
                            dialogLoading.setVisible(false);
                        }
                    });
                }
            };
            worker.execute();
        }
    }//GEN-LAST:event_jTable1MouseDragged

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
            java.util.logging.Logger.getLogger(VerifyUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerifyUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerifyUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerifyUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerifyUsers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtemail;
    // End of variables declaration//GEN-END:variables

    private void Seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("salad.png")));
    }
}
