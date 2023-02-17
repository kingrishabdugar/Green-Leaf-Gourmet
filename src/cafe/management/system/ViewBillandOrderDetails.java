/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cafe.management.system;

import static cafe.management.system.CafeManagementSystem.createDialog;
import common.OpenPdf;
import dao.BillDao;
import dao.UserDao;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//for spinners
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;

import java.util.Date;
import java.util.Calendar;

import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import model.Bill;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import model.User;

/**
 *
 * @author kingrishabdugar
 */
public class ViewBillandOrderDetails extends javax.swing.JFrame {

    JDialog dialogLoading = new JDialog();
    /**
     * Creates new form ViewBillandOrderDetails
     */
    public String userEmail;

    private static final String[] DAYS = {"ALL", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
        "26", "27", "28", "29", "30", "31"};
    private static final String[] MONTHS = {"ALL", "January", "February", "March", "April", "May", "June", "July", "August",
        "September", "October", "November", "December"};

    Calendar calendar = Calendar.getInstance();
    int currentDay = calendar.get(Calendar.DAY_OF_MONTH) + 1;
    int currentMonth = (calendar.get(Calendar.MONTH)) + 1;
    int currentYear = calendar.get(Calendar.YEAR);

    public ViewBillandOrderDetails() {
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
                    dialogLoading.dispose();
                    dispose();
                }
            }

            public void windowIconified(WindowEvent e) {
                dialogLoading.dispose();
            }
        });
        //   new Home().setVisible(true);
        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String todayDate = dFormat.format(date);
//        jTextField1.setText(todayDate);

        JTableHeader boldheader1 = jTable1.getTableHeader();
        boldheader1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        //((DefaultTableCellHeaderRenderer) boldheader1.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

    }

    public ViewBillandOrderDetails(String email, JFrame oldclass) {
        initComponents();
        Seticon();
        setTitle(" Green-Leaf-Gourmet ");
        setLocationRelativeTo(null); //makes aligned at center of screen
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        userEmail = email;
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
        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String todayDate = dFormat.format(date);
        //jTextField1.setText(todayDate);

        JTableHeader boldheader1 = jTable1.getTableHeader();
        boldheader1.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 17));
        //((DefaultTableCellHeaderRenderer) boldheader1.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

    }

    public void tableDetails() {

        dialogLoading.dispose();
        Map<String, String> monthMap = new HashMap<>();
        monthMap.put(MONTHS[0], "ALL");
        for (int i = 1; i < 13; i++) {
            monthMap.put(MONTHS[i], DAYS[i]); //days is just used as a temporary array for months 01-12
        }

        CafeManagementSystem.createDialog(dialogLoading, "/images/Loading GIFs/Orange.gif");
        String month = "", day = "", year = "";

        day = (String) dayComboBox.getSelectedItem() + "-";

        String monthName = (String) monthComboBox.getSelectedItem();
        month = monthMap.get(monthName) + "-";

        year = (String) yearComboBox.getSelectedItem();

        String date1 = (day.equals("ALL-") ? "" : day) + (month.equals("ALL-") ? "" : month) + (year.equals("ALL") ? "" : year);

        if (date1.equals("ALL-ALL-ALL")) {
            date1 = "";
        }

        String date = date1;
        String incDec = (String) jComboBox1.getSelectedItem();

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                if (incDec.equals("INC")) {
                    ArrayList<Bill> list = BillDao.getAllRecordsByInc(date);
                    Iterator<Bill> itr = list.iterator();
                    while (itr.hasNext()) {
                        Bill billobj = itr.next();
                        dtm.addRow(new Object[]{billobj.getId(), billobj.getName(), billobj.getMobileNumber(), billobj.getEmail(), billobj.getDate(), billobj.getTotal(), billobj.getCreatedBy()});

                    }
                } 
                else {
                    ArrayList<Bill> list = BillDao.getAllRecordsByDesc(date);
                    Iterator<Bill> itr = list.iterator();
                    while (itr.hasNext()) {
                        Bill billobj = itr.next();
                        dtm.addRow(new Object[]{billobj.getId(), billobj.getName(), billobj.getMobileNumber(), billobj.getEmail(), billobj.getDate(), billobj.getTotal(), billobj.getCreatedBy()});

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
            jPanel1 = new common.PanelScale("/images/FS_BillOrder.png");
            ;
            jLabel2 = new javax.swing.JLabel();
            jComboBox1 = new javax.swing.JComboBox<>();
            jScrollPane1 = new javax.swing.JScrollPane();
            jTable1 = new javax.swing.JTable();
            jLabel3 = new javax.swing.JLabel();
            jLabel1 = new javax.swing.JLabel();
            dayComboBox = new javax.swing.JComboBox<>(DAYS);
            monthComboBox = new javax.swing.JComboBox<>((MONTHS));
            String[] years = new String[3];
            years[2] = "ALL";
            years[1] = String.valueOf(currentYear-1);
            years[0] = String.valueOf(currentYear);
            yearComboBox = new javax.swing.JComboBox<>(years);

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

        jLabel2.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setText("Change Order By ID :");
        jLabel2.setBorder(new javax.swing.border.MatteBorder(null));

        jComboBox1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 17)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "INC", "DESC" }));
        jComboBox1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Mob No.", "Email", "Date", "Total Amount", "Created By"
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
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 17)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Instructions : Click On Row To Refund Bill/Delete Order !");

        jLabel1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setText("Filter By Date :");
        jLabel1.setBorder(new javax.swing.border.MatteBorder(null));

        dayComboBox.setSelectedIndex(currentDay - 1);
        dayComboBox.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 17)); // NOI18N
        dayComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayComboBoxActionPerformed(evt);
            }
        });

        monthComboBox.setSelectedIndex(currentMonth);
        monthComboBox.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 17)); // NOI18N
        monthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthComboBoxActionPerformed(evt);
            }
        });

        yearComboBox.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 17)); // NOI18N
        yearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(171, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20)
                        .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel2)
                        .addGap(20, 20, 20)
                        .addComponent(jComboBox1, 0, 166, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap(172, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dayComboBox, jComboBox1, jLabel1, jLabel2, monthComboBox, yearComboBox});

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        tableDetails();
    }//GEN-LAST:event_jComboBox1ActionPerformed


    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        //TODO add your handling code here:
        dialogLoading.dispose();
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String id = model.getValueAt(index, 0).toString();
        String name = model.getValueAt(index, 1).toString();
        int a = JOptionPane.showConfirmDialog(null, "Do you want to Cancel/Refund " + name + "'s Order ?", "Select", JOptionPane.YES_NO_CANCEL_OPTION);
        if (a == 0) {
            SwingWorker<Void, Void> worker;
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    BillDao.delete(id);
                    return null;
                }

                @Override
                protected void done() {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            setVisible(false);
                            jTable1.repaint();
                            setVisible(true);
                        }
                    });
                }
            };
            worker.execute();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        tableDetails();
    }//GEN-LAST:event_formComponentShown

    private void yearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearComboBoxActionPerformed
        // TODO add your handling code here
        tableDetails();
    }//GEN-LAST:event_yearComboBoxActionPerformed

    private void monthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthComboBoxActionPerformed
        // TODO add your handling code here:
        tableDetails();
    }//GEN-LAST:event_monthComboBoxActionPerformed

    private void dayComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayComboBoxActionPerformed
        // TODO add your handling code here:
        tableDetails();
    }//GEN-LAST:event_dayComboBoxActionPerformed

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
    private javax.swing.JComboBox<String> dayComboBox;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> monthComboBox;
    private javax.swing.JComboBox<String> yearComboBox;
    // End of variables declaration//GEN-END:variables

    private void Seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("salad.png")));
    }
}
