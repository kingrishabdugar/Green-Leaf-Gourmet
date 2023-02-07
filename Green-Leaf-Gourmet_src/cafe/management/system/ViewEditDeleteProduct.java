/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cafe.management.system;

import dao.CategoryDao;
import model.Product;
import dao.ProductDao;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import model.Category;
//import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 *
 * @author kingrishabdugar
 */
public class ViewEditDeleteProduct extends javax.swing.JFrame {

    public String userEmail;

    /**
     * Creates new form ViewEditDeleteProduct
     */
    public ViewEditDeleteProduct() {
        initComponents();
        Seticon();
        btnupdate.setEnabled(false);
        btndelete.setEnabled(false);
        setLocationRelativeTo(null); //makes aligned at center of screen
        setResizable(false);
        //setAlwaysOnTop(true);
        // setShape(new RoundRectangle2D.Double(0,0, 1024, 576, 35, 35));
        setSize(1024, 616);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
                if (result == JOptionPane.OK_OPTION) {
                    // NOW we change it to dispose on close..
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    setVisible(false);
                    dispose();
                }
            }
        });
        // new Home().setVisible(true);
        JTableHeader boldheader1 = jTable1.getTableHeader();
        boldheader1.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        ((DefaultTableCellHeaderRenderer) boldheader1.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

    }

    public ViewEditDeleteProduct(String email) {
        initComponents();
        Seticon();
        btnupdate.setEnabled(false);
        btndelete.setEnabled(false);
        setLocationRelativeTo(null); //makes aligned at center of screen
        setResizable(false);
        // setShape(new RoundRectangle2D.Double(0,0, 1024, 576, 35, 35));
        setSize(1024, 616);
        userEmail = email;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
                if (result == JOptionPane.OK_OPTION) {
                    // NOW we change it to dispose on close..
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    setVisible(false);
                    dispose();
                    //new Home(userEmail).setVisible(true);
                }
            }
        });
        // new Home().setVisible(true);
        JTableHeader boldheader1 = jTable1.getTableHeader();
        boldheader1.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        ((DefaultTableCellHeaderRenderer) boldheader1.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

    }

    public void validateField() {
        String name = txtname.getText();
        String price = txtprice.getText();
        String category = (String) jComboBox1.getSelectedItem();
        if (!name.equals("") && !price.equals("") && category != null) {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtprice = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtid = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel1.setText("ID :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 140, -1, 34));

        jLabel2.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel2.setText("Name :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 194, -1, 34));

        jLabel3.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel3.setText("Category :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 248, -1, 34));

        jLabel4.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel4.setText("Price :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 302, -1, 34));

        txtname.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        txtname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnameActionPerformed(evt);
            }
        });
        txtname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnameKeyReleased(evt);
            }
        });
        getContentPane().add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 194, 301, -1));

        txtprice.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        txtprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpriceKeyReleased(evt);
            }
        });
        getContentPane().add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 302, 301, -1));

        jComboBox1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 248, 301, -1));

        btnupdate.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        btnupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.gif"))); // NOI18N
        btnupdate.setText("Update");
        btnupdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 356, 147, -1));

        btndelete.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        btndelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.gif"))); // NOI18N
        btndelete.setText("Delete");
        btndelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btndelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 356, 144, -1));

        btnclear.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        btnclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear.gif"))); // NOI18N
        btnclear.setText("Clear");
        btnclear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });
        getContentPane().add(btnclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 408, 301, -1));

        jTable1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "CATEGORY", "PRICE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 140, 535, 326));

        txtid.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        txtid.setText("---");
        getContentPane().add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 140, 301, 34));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view edit delete page.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnameActionPerformed

    private void txtnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtnameKeyReleased

    private void txtpriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpriceKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtpriceKeyReleased

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        Product product = new Product();
        int id = Integer.parseInt(txtid.getText()); //String to Integer type
        product.setId(id);
        product.setName(CafeManagementSystem.apostrophe(txtname.getText()));
        product.setCategory((String) jComboBox1.getSelectedItem());
        product.setPrice(txtprice.getText());
        ProductDao.update(product);
        setVisible(false);
        new ViewEditDeleteProduct(userEmail).setVisible(true);
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        String id = txtid.getText();
        int a = JOptionPane.showConfirmDialog(null, " Do you want to Delete this product", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            ProductDao.delete(id);
            setVisible(false);
            new ViewEditDeleteProduct(userEmail).setVisible(true);
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new ViewEditDeleteProduct(userEmail).setVisible(true);
    }//GEN-LAST:event_btnclearActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        ArrayList<Product> list = ProductDao.getAllRecords();
        Iterator<Product> itr = list.iterator();
        while (itr.hasNext()) {
            Product productObj = itr.next();
            dtm.addRow(new Object[]{productObj.getId(), productObj.getName(), productObj.getCategory(), productObj.getPrice()});

        }
    }//GEN-LAST:event_formComponentShown
//clicked on a row in the table -> should display on left side in text boxes and category
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        
        
        
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String id = model.getValueAt(index, 0).toString();
        txtid.setText(id);
        String name = model.getValueAt(index, 1).toString();
        txtname.setText(name);
        String category = model.getValueAt(index, 2).toString();
        String price = model.getValueAt(index, 3).toString();
        txtprice.setText(price);

        btnupdate.setEnabled(true);
        btndelete.setEnabled(true);
        jComboBox1.removeAllItems();
        jComboBox1.addItem(category);
        ArrayList<Category> categoryList = CategoryDao.getAllRecorded();
        Iterator<Category> categoryItr = categoryList.iterator();
        while (categoryItr.hasNext()) {
            Category categoryObj = categoryItr.next();
            if (!categoryObj.getName().equals(category)) {
                jComboBox1.addItem(categoryObj.getName());
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(ViewEditDeleteProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewEditDeleteProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewEditDeleteProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewEditDeleteProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewEditDeleteProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel txtid;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtprice;
    // End of variables declaration//GEN-END:variables
    private void Seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("salad.png")));
    }
}
