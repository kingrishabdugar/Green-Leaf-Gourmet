/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
// JFrame -> Component -> component shown event instead of key released for combo box in category and Table list
package cafe.management.system;

import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import java.util.ArrayList;
import model.Category;
import model.Product;
import dao.CategoryDao;
import java.util.Iterator;
import dao.ProductDao;
import dao.UserDao;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import model.User;

/**
 *
 * @author kingrishabdugar
 */
public class AddNewProduct extends javax.swing.JFrame {

    JDialog dialogLoading = new JDialog();

    /**
     * Creates new form AddNewProduct
     */
    public AddNewProduct() {
        initComponents();
        Seticon();
        //setAlwaysOnTop(true);
        btnsave.setEnabled(false);
        setLocationRelativeTo(null); //makes aligned at center of screen
        setResizable(false);
        setResizable(false);
        //setShape(new RoundRectangle2D.Double(0,0, 625, 350, 35, 35));
        setSize(625, 390);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialogLoading.dispose();
                int result = JOptionPane.showConfirmDialog(null, "<html><b style=\"color:Green\">Are you Sure❓</b></html>");
                if (result == JOptionPane.OK_OPTION) {
                    // NOW we change it to dispose on close..
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    dialogLoading.dispose();
                    dispose();
                }
            }

            public void windowIconified(WindowEvent e) {
                dialogLoading.dispose();
            }
        });
    }

    public boolean onlyDigits(String str) {
        // Regex to check string
        // contains only digits
        String regex = "[0-9]+";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null) {
            return false;
        }

        // Find match between given string
        // and regular expression
        // using Pattern.matcher()
        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }

    public void clear() {
        txtname.setText("");
        txtprice.setText("");
        btnsave.setEnabled(false);
        dialogLoading.dispose();
        setVisible(false);
        setVisible(true);
    }

    public void validateFields() {
        String name = txtname.getText();
        String price = txtprice.getText();
        if (!name.equals("") && !price.equals("")) {
            btnsave.setEnabled(true);
        } else {
            btnsave.setEnabled(false);
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

        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtprice = new javax.swing.JTextField();
        btnclear = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();
        txtcategory = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addnewproduct.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel1.setText("Name :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 80, -1, 34));

        jLabel2.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel2.setText("Category :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 129, -1, 34));

        jLabel3.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel3.setText("Price :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 178, -1, 34));

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
        getContentPane().add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 80, 260, -1));

        txtprice.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        txtprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpriceKeyReleased(evt);
            }
        });
        getContentPane().add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 178, 260, -1));

        btnclear.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        btnclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear.gif"))); // NOI18N
        btnclear.setText("Clear");
        btnclear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });
        getContentPane().add(btnclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 227, 120, -1));

        btnsave.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        btnsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.gif"))); // NOI18N
        btnsave.setText("Save");
        btnsave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnsave, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 227, 110, -1));

        txtcategory.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        getContentPane().add(txtcategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 129, 260, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addnewproduct.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnameActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:

        CafeManagementSystem.createDialog(dialogLoading, "/images/Loading GIFs/Burger.gif");//Name of gif file
        //setVisible(false);
        dialogLoading.setVisible(false);
        dialogLoading.setAlwaysOnTop(false); // loading gif stays below the dialog

        if (!onlyDigits(txtprice.getText())) {
            JOptionPane.showMessageDialog(null, "<html><b style=\"colorred\">Price should be in Digits only. Please ignore adding currency !</b></html>", "Message", JOptionPane.ERROR_MESSAGE);
            clear();
        } else {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {

                    // this is the long process
                    Product product = new Product();
                    product.setName(CafeManagementSystem.apostrophe(txtname.getText()));
                    product.setCategory((String) txtcategory.getSelectedItem());
                    product.setPrice(txtprice.getText());
                    ProductDao.save(product);
                    clear();
                    // this will execute the query taken from the object user

                    return null;
                }

                @Override
                protected void done() {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dialogLoading.dispose();
                            clear();
                        }
                    });
                }
            };

            SwingUtilities.invokeLater(() -> {
                dialogLoading.setVisible(true);
            });

            worker.execute();
        }
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnclearActionPerformed

    private void txtnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_txtnameKeyReleased

    private void txtpriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpriceKeyReleased
        // TODO add your handling code here:
        validateFields();
    }//GEN-LAST:event_txtpriceKeyReleased
//THIS IS FOR THE CATEGORY DROPDOWN SPECIFICALLY SO THAT IT SHOWS LIST OF MANAGE CATEGORY
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:

        CafeManagementSystem.createDialog(dialogLoading, "/images/Loading GIFs/Burger.gif");
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                ArrayList<Category> list = CategoryDao.getAllRecorded();
                Iterator<Category> itr = list.iterator();
                while (itr.hasNext()) {
                    Category categoryobj = itr.next();
                    txtcategory.addItem(categoryobj.getName());
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
            java.util.logging.Logger.getLogger(AddNewProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddNewProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnsave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox<String> txtcategory;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtprice;
    // End of variables declaration//GEN-END:variables

    private void Seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("salad.png")));
    }
}