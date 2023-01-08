/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cafe.management.system;

import common.OpenPdf;
import dao.BillDao;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import model.Bill;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author kingrishabdugar
 */
public class ViewBillandOrderDetails extends javax.swing.JFrame {

    /**
     * Creates new form ViewBillandOrderDetails
     */
        public String userEmail;

    public ViewBillandOrderDetails() {
        initComponents();
        Seticon();
        setTitle(" Green-Leaf-Gourmet ");
        setLocationRelativeTo(null); //makes aligned at center of screen
        //setShape(new RoundRectangle2D.Double(0,0, 1024, 576, 35, 35));
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(1100,680); 
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
             //   new Home().setVisible(true);
        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String todayDate = dFormat.format(date);
        jTextField1.setText(todayDate);
        
        
        JTableHeader boldheader1 = jTable1.getTableHeader();
        boldheader1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        //((DefaultTableCellHeaderRenderer) boldheader1.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
       
    }
    
    public ViewBillandOrderDetails(String email) {
        initComponents();
        Seticon();
        setTitle(" Green-Leaf-Gourmet ");
        setLocationRelativeTo(null); //makes aligned at center of screen
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(1100,680); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        userEmail = email;
       addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
                        if( result==JOptionPane.OK_OPTION){
                            // NOW we change it to dispose on close..
                            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            setVisible(false);
                            dispose();
                            new Home(userEmail).setVisible(true);
                        }
                    }
                });
        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String todayDate = dFormat.format(date);
        jTextField1.setText(todayDate);
        
        
        JTableHeader boldheader1 = jTable1.getTableHeader();
        boldheader1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        //((DefaultTableCellHeaderRenderer) boldheader1.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
       
    }

    public void tableDetails() {
        String date = jTextField1.getText();
        String incDec = (String) jComboBox1.getSelectedItem();
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        if (incDec.equals("INC")) {
            ArrayList<Bill> list = BillDao.getAllRecordsByInc(date);
            Iterator<Bill> itr = list.iterator();
            while (itr.hasNext()) {
                Bill billobj = itr.next();
                dtm.addRow(new Object[]{billobj.getId(), billobj.getName(), billobj.getMobileNumber(), billobj.getEmail(), billobj.getDate(), billobj.getTotal(), billobj.getCreatedBy()});

            }
        } else {
            ArrayList<Bill> list = BillDao.getAllRecordsByDesc(date);
            Iterator<Bill> itr = list.iterator();
            while (itr.hasNext()) {
                Bill billobj = itr.next();
                dtm.addRow(new Object[]{billobj.getId(), billobj.getName(), billobj.getMobileNumber(), billobj.getEmail(), billobj.getDate(), billobj.getTotal(), billobj.getCreatedBy()});

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

        try
        {
            jPanel1 = new common.PanelScale("/images/FS_BillOrder.png");
            ;
            jLabel2 = new javax.swing.JLabel();
            jComboBox1 = new javax.swing.JComboBox<>();
            jScrollPane1 = new javax.swing.JScrollPane();
            jTable1 = new javax.swing.JTable();
            jLabel3 = new javax.swing.JLabel();
            jLabel1 = new javax.swing.JLabel();
            jTextField1 = new javax.swing.JTextField();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentShown(java.awt.event.ComponentEvent evt) {
                    formComponentShown(evt);
                }
            });

            pack();
        }
        catch (IOException ex)
        {
            Logger.getLogger(ViewBillandOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setText("Change Order By ID :");
        jLabel2.setBorder(new javax.swing.border.MatteBorder(null));

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "INC", "DESC" }));
        jComboBox1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Mob No.", "Email", "Date", "Total Amount", "Created By"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Instructions : Click On Row To Refund Bill/Delete Order !");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setText("Filter By Date :");
        jLabel1.setBorder(new javax.swing.border.MatteBorder(null));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(138, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(19, 19, 19)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                        .addGap(156, 156, 156)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(19, 19, 19)
                        .addComponent(jComboBox1, 0, 259, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE))
                .addContainerGap(138, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jLabel1, jLabel2, jTextField1});

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        tableDetails();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        tableDetails();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         //TODO add your handling code here:
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String id = model.getValueAt(index, 0).toString();
        String name = model.getValueAt(index, 1).toString();
        int a = JOptionPane.showConfirmDialog(null, "Do you want to Cancel/Refund " + name + "'s Order ?", "Select", JOptionPane.YES_NO_CANCEL_OPTION);
        if (a == 0) {
            BillDao.delete(id);
            setVisible(false);
            new ViewBillandOrderDetails(userEmail).setVisible(true);
        }
         
        //OpenPdf.openById(id);
    }//GEN-LAST:event_jTable1MouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        tableDetails();
    }//GEN-LAST:event_formComponentShown

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
            java.util.logging.Logger.getLogger(ViewBillandOrderDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewBillandOrderDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewBillandOrderDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewBillandOrderDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewBillandOrderDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
  
    private void Seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("salad.png")));
    }
}
