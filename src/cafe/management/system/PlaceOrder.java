/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cafe.management.system;

import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import common.OpenPdf;
import dao.BillDao;
import dao.CategoryDao;
import dao.ProductDao;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.util.Arrays.stream;
import java.util.Date;
import java.util.Iterator;
import static java.util.stream.StreamSupport.stream;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static jdk.nashorn.internal.runtime.Debug.id;
import model.Bill;
import model.Product;
import model.Category;

import cafe.management.system.Home;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
//import sun.swing.table.DefaultTableCellHeaderRenderer;
//import sun.swing.table.DefaultTableCellHeaderRenderer;
import javax.swing.table.DefaultTableCellRenderer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.PanelScale;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

/**
 *
 * @author kingrishabdugar
 */
public class PlaceOrder extends javax.swing.JFrame {

    JDialog dialogLoading = new JDialog();

    //private static final Font BOLDFont = new Font(Font.getFamily("SEGOE_UI"),15,Font.BOLD);
    public int billId = 1;
    public int grandTotal = 0;
    public int productPrice = 0;
    public int productTotal = 0;
    public String emailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
    public String mobileNumberPattern = "^[0-9]*$";
    public String userEmail;
    public String DateToday;
    JFrame old;

    /**
     * Creates new form PlaceOrder
     *
     */
    public PlaceOrder() {
        initComponents();
        Seticon();
        setTitle(" Green-Leaf-Gourmet ");

        Seticon();

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
    }

    public PlaceOrder(String email, JFrame oldclass) {
        initComponents();
        old = oldclass;
        JTableHeader boldheader1 = jTable1.getTableHeader();
        JTableHeader boldheader2 = jTable2.getTableHeader();
        boldheader1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        boldheader2.setFont(new Font("Segoe UI", Font.BOLD, 15));
        Seticon();
        setTitle(" Green-Leaf-Gourmet ");
        setLocationRelativeTo(null); //makes aligned at center of screen
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        userEmail = email;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
                }
            }

            public void windowIconified(WindowEvent e) {
                dialogLoading.dispose();
            }
        });
        txtproductname.setEditable(false); //automatically product name comes user cannot edit it the second name column in the JFrame
        txtprice.setEditable(false);
        txttotal.setEditable(false);
        btnaddtocart.setEnabled(false);//form loads nothing selected so disabled initially
        btnbill.setEnabled(false);
        JFormattedTextField tf = ((JSpinner.DefaultEditor) jSpinner1.getEditor()).getTextField();
        tf.setEditable(false);
        //userEmail = email;
    }

    private static final String[] twodigits = {"", " Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety"};
//string type array for two digits numbers   
    private static final String[] onedigit = {"", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine", " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen"};
//defining constructor of the class  
//user-defined method that converts a number to words (up to 1000)  

    private static String convertUptoThousand(int number) {
        String soFar;
        if (number % 100 < 20) {
            soFar = onedigit[number % 100];
            number = number / 100;
        } else {
            soFar = onedigit[number % 10];
            number = number / 10;
            soFar = twodigits[number % 10] + soFar;
            number = number / 10;
        }
        if (number == 0) {
            return soFar;
        }
        return onedigit[number] + " Hundred " + soFar;
    }
//user-defined method that converts a long number (0 to 999999999) to string    

    public static String convertNumberToWord(long number) {
//checks whether the number is zero or not  
        if (number == 0) {
//if the given number is zero it returns zero  
            return "zero";
        }
//the toString() method returns a String object that represents the specified long  
        String num = Long.toString(number);
//for creating a mask padding with "0"   
        String pattern = "000000000000";
//creates a DecimalFormat using the specified pattern and also provides the symbols for the default locale  
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
//format a number of the DecimalFormat instance  
        num = decimalFormat.format(number);
//format: XXXnnnnnnnnn  
//the subString() method returns a new string that is a substring of this string  
//the substring begins at the specified beginIndex and extends to the character at index endIndex - 1  
//the parseInt() method converts the string into integer  
        int billions = Integer.parseInt(num.substring(0, 3));
//format: nnnXXXnnnnnn  
        int millions = Integer.parseInt(num.substring(3, 6));
//format: nnnnnnXXXnnn  
        int hundredThousands = Integer.parseInt(num.substring(6, 9));
//format: nnnnnnnnnXXX  
        int thousands = Integer.parseInt(num.substring(9, 12));
        String tradBillions;
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            case 1:
                tradBillions = convertUptoThousand(billions) + " Billion ";
                break;
            default:
                tradBillions = convertUptoThousand(billions) + " Billion ";
        }
        String result = tradBillions;
        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1:
                tradMillions = convertUptoThousand(millions) + " Million ";
                break;
            default:
                tradMillions = convertUptoThousand(millions) + " Million ";
        }
        result = result + tradMillions;
        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1:
                tradHundredThousands = "One Thousand ";
                break;
            default:
                tradHundredThousands = convertUptoThousand(hundredThousands) + " Thousand ";
        }
        result = result + tradHundredThousands;
        String tradThousand;
        tradThousand = convertUptoThousand(thousands);
        result = result + tradThousand;
//removing extra space if any  
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

    public void productNameByCategory(String category) {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        ArrayList<Product> list = ProductDao.getAllRecordsByCategory(category);
        Iterator<Product> itr = list.iterator();
        while (itr.hasNext()) {
            Product productObj = itr.next();
            dtm.addRow(new Object[]{productObj.getName()});

        }
    }

    public void filterProductByname(String name, String category) {

        CafeManagementSystem.createDialog(dialogLoading, "/images/Loading GIFs/GreenApple.gif");

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                ArrayList<Product> list = ProductDao.filterProductByname(name, category);
                Iterator<Product> itr = list.iterator();
                while (itr.hasNext()) {
                    Product productObj = itr.next();
                    dtm.addRow(new Object[]{productObj.getName()});

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

    public void clearProductFields() {
        txtproductname.setText("");
        txtprice.setText("");
        jSpinner1.setValue(1);
        txttotal.setText("");
        btnaddtocart.setEnabled(false);
    }

    public void validateField() {
        String customerName = txtcusname.getText();
        String customerMobileNumber = txtcusmobile.getText();
        String customerEmail = txtcusemail.getText();
        if (!customerEmail.equals("") && customerMobileNumber.matches(mobileNumberPattern) && customerMobileNumber.length() == 10 && customerEmail.matches(emailPattern) && grandTotal > 0) {
            btnbill.setEnabled(true);
        } else {
            btnbill.setEnabled(false);
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
            jPanel1 = new PanelScale("/images/FS_PlaceOrder.png");
            jLabel1 = new javax.swing.JLabel();
            txtcusemail = new javax.swing.JTextField();
            jLabel15 = new javax.swing.JLabel();
            jComboBox1 = new javax.swing.JComboBox<>();
            jLabel11 = new javax.swing.JLabel();
            txtsearch = new javax.swing.JTextField();
            jScrollPane1 = new javax.swing.JScrollPane();
            jTable1 = new javax.swing.JTable();
            txtproductname = new javax.swing.JTextField();
            jLabel12 = new javax.swing.JLabel();
            jSpinner1 = new javax.swing.JSpinner();
            txtprice = new javax.swing.JTextField();
            jLabel13 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            txttotal = new javax.swing.JTextField();
            jLabel3 = new javax.swing.JLabel();
            btnclear = new javax.swing.JButton();
            jLabel4 = new javax.swing.JLabel();
            btnaddtocart = new javax.swing.JButton();
            jLabel5 = new javax.swing.JLabel();
            jScrollPane2 = new javax.swing.JScrollPane();
            jTable2 = new javax.swing.JTable();
            jLabel6 = new javax.swing.JLabel();
            jLabel8 = new javax.swing.JLabel();
            txtcusname = new javax.swing.JTextField();
            btnbill = new javax.swing.JButton();
            jLabel21 = new javax.swing.JLabel();
            jLabel9 = new javax.swing.JLabel();
            txtgrandtotal = new javax.swing.JLabel();
            txtcusmobile = new javax.swing.JTextField();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentShown(java.awt.event.ComponentEvent evt) {
                    formComponentShown(evt);
                }
            });

            pack();
        }
        catch (IOException ex)
        {
            Logger.getLogger(PlaceOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setText("Email :");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtcusemail.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        txtcusemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcusemailKeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(242, 242, 242));
        jLabel15.setText("--");
        jLabel15.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jComboBox1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(242, 242, 242));
        jLabel11.setText("Search :");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtsearch.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txtproductname.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(242, 242, 242));
        jLabel12.setText("Quantity :");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jSpinner1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        txtprice.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(242, 242, 242));
        jLabel13.setText("Total :");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setBackground(new java.awt.Color(250, 200, 35));
        jLabel2.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel2.setText(" CUSTOMER DETAILS ");
        jLabel2.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setOpaque(true);

        txttotal.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(242, 242, 242));
        jLabel3.setText("Category :");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnclear.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        btnclear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear.gif"))); // NOI18N
        btnclear.setText("Clear");
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(242, 242, 242));
        jLabel4.setText("Price :");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnaddtocart.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        btnaddtocart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add to cart.gif"))); // NOI18N
        btnaddtocart.setText("Add to Cart");
        btnaddtocart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddtocartActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(242, 242, 242));
        jLabel5.setText("Name :");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTable2.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 13)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Price", "Quantity", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel6.setBackground(new java.awt.Color(250, 200, 35));
        jLabel6.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel6.setText(" BILL ID : ");
        jLabel6.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel6.setOpaque(true);

        jLabel8.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(242, 242, 242));
        jLabel8.setText("Name :");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtcusname.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        txtcusname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcusnameKeyReleased(evt);
            }
        });

        btnbill.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        btnbill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/generate bill.gif"))); // NOI18N
        btnbill.setText("Generate Bill & Print");
        btnbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbillActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(242, 242, 242));
        jLabel21.setText("Grand Total : INR ");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel9.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(242, 242, 242));
        jLabel9.setText("Mobile Number :");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtgrandtotal.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        txtgrandtotal.setForeground(new java.awt.Color(242, 242, 242));
        txtgrandtotal.setText("-----");
        txtgrandtotal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtcusmobile.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 15)); // NOI18N
        txtcusmobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcusmobileActionPerformed(evt);
            }
        });
        txtcusmobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcusmobileKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtcusname, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtcusmobile, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtcusemail, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(11, 11, 11)
                        .addComponent(txtgrandtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(40, 40, 40)
                        .addComponent(btnbill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtproductname)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                            .addComponent(btnclear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(153, 153, 153)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtprice, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txttotal, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnaddtocart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnclear, jLabel12, jLabel5, jSpinner1, txtproductname});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtproductname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel13)))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcusname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnclear)
                            .addComponent(btnaddtocart))
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(15, 15, 15)
                        .addComponent(txtcusmobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addGap(15, 15, 15)
                        .addComponent(txtcusemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnbill, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtgrandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jLabel2, jSpinner1, txtcusemail, txtcusmobile, txtcusname, txtprice, txtproductname, txtsearch, txttotal});

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbillActionPerformed
        // TODO add your handling code here:

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // this is the very long process
                dialogLoading.setAlwaysOnTop(false);
                String customerName = CafeManagementSystem.apostrophe(txtcusname.getText());
                String customerMobileNumber = txtcusmobile.getText();
                String customerEmail = txtcusemail.getText();
                SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date();
                String todaydate = dFormat.format(date);
                String total = String.valueOf(grandTotal);
                String createdBy = userEmail;
                Bill bill = new Bill();
                bill.setId(billId);
                bill.setName(customerName);
                bill.setMobileNumber(customerMobileNumber);
                bill.setEmail(customerEmail);
                bill.setDate(todaydate);
                bill.setTotal(total);
                bill.setCreatedBy(createdBy);
                BillDao.save(bill);

                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date date1 = new Date();
                formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
                DateToday = formatter.format(date1);

                //Creating document
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int ra = j.showSaveDialog(null);
                String path = "D:\\Generated_Bills\\";

                if (ra == JFileChooser.APPROVE_OPTION) {
                    // set the label to the path of the selected directory
                    path = j.getSelectedFile().getAbsolutePath();
                } // if the user cancelled the operation
                else {
                    path = "D:\\";
                }

                com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
                try {
                    //file name generated here
                    //location\\ + "Green-Leaf-Gourmet_Bill_" +id(file name of bill)

                    PdfWriter.getInstance(doc, new FileOutputStream(path + "\\" + "" + "Green-Leaf-Gourmet_Bill_" + billId + ".pdf"));
                    doc.open();
                    //Code to Add Header Image in Invoice after Bill
                    String header = "JRE\\src\\images\\Invoice_Header.png";
                    Image image_header = Image.getInstance(header);
                    image_header.setAlignment(Image.MIDDLE);
                    //Fit Maximum along Margins
                    float documentWidth = doc.getPageSize().getWidth() - doc.leftMargin() - doc.rightMargin();
                    float documentHeight = doc.getPageSize().getHeight() - doc.topMargin() - doc.bottomMargin();
                    image_header.scaleToFit(documentWidth, documentHeight);
                    doc.add(image_header);

                    //Code to Add LineBreak Image in Invoice after Bill
                    String line = "JRE\\src\\images\\Invoice_LineBreak.png";
                    Image image_line = Image.getInstance(line);
                    image_line.setAlignment(Image.MIDDLE);
                    image_line.scaleToFit(documentWidth, documentHeight);

                    //Code to Add Footer Image in Invoice
                    String footer = "JRE\\src\\images\\Invoice_Footer.png";
                    Image image_footer = Image.getInstance(footer);
                    image_footer.setAlignment(Image.MIDDLE);
                    image_footer.scaleToFit(documentWidth, documentHeight);

                    String qr = "JRE\\src\\images\\InvoiceQR.png";
                    Image image_qr = Image.getInstance(qr);
                    image_qr.setAlignment(Image.MIDDLE);
                    image_qr.scaleToFit(documentWidth, documentHeight);

                    doc.add(image_line); //After Header
                    String total_paid = " ( Rupees " + convertNumberToWord(grandTotal) + " Only )";
                    Paragraph paragraph3 = new Paragraph("\tBill ID: " + billId + "\nCustomer Name: " + customerName + "\nTotal Paid: INR " + grandTotal + total_paid + "\nInvoice Date: " + DateToday);
                    doc.add(paragraph3);
                    doc.add(image_line);
                    PdfPTable tb1 = new PdfPTable(4);
                    tb1.addCell("Name");
                    tb1.addCell("Price");
                    tb1.addCell("Quantity");
                    tb1.addCell("Total");
                    for (int i = 0; i < jTable2.getRowCount(); i++) {
                        String n = jTable2.getValueAt(i, 0).toString();
                        String d = "INR " + jTable2.getValueAt(i, 1).toString();
                        String r = jTable2.getValueAt(i, 2).toString();
                        String q = "INR " + jTable2.getValueAt(i, 3).toString();
                        tb1.addCell(n);
                        tb1.addCell(d);
                        tb1.addCell(r);
                        tb1.addCell(q);
                    }
                    doc.add(tb1);
                    doc.add(image_line);
                    doc.add(image_footer);
                    doc.add(image_qr);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e, "Some Error ❌ Occured ❗", JOptionPane.ERROR_MESSAGE);
                }
                OpenPdf.openById(String.valueOf(billId), path + "\\");
                doc.close();

                new PlaceOrder(userEmail, old).setVisible(true);
                setVisible(false);
                dispose();

                return null;
            }

            @Override
            protected void done() {
                Timer timer = new Timer(10, (ActionEvent evt1) -> {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dialogLoading.dispose();
                        }
                    });
                    ((Timer) evt1.getSource()).stop();
                }); // This is thrown if we throw an exception
                // from doInBackground.
                timer.setRepeats(false);
                timer.start();

            }
        };

        SwingUtilities.invokeLater(() -> {
            dialogLoading.setVisible(true);
        });

        worker.execute();
    }//GEN-LAST:event_btnbillActionPerformed

    private void txtcusmobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcusmobileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcusmobileActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String category = (String) jComboBox1.getSelectedItem();
        productNameByCategory(category);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        // TODO add your handling code here:
        String name = txtsearch.getText();
        String category = (String) jComboBox1.getSelectedItem();
        filterProductByname(name, category);
    }//GEN-LAST:event_txtsearchKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String productName = model.getValueAt(index, 0).toString();
        Product product = ProductDao.getProductByname(CafeManagementSystem.apostrophe(productName));
        txtproductname.setText(product.getName());
        txtprice.setText(product.getPrice());
        jSpinner1.setValue(1);
        txttotal.setText(product.getPrice());
        productPrice = Integer.parseInt(product.getPrice());
        productTotal = Integer.parseInt(product.getPrice());
        btnaddtocart.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        // TODO add your handling code here:
        int quantity = (Integer) jSpinner1.getValue();
        if (quantity <= 1) {
            jSpinner1.setValue(1);
            quantity = 1;
        }
        productTotal = productPrice * quantity;
        txttotal.setText(String.valueOf(productTotal));
    }//GEN-LAST:event_jSpinner1StateChanged

    private void btnaddtocartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddtocartActionPerformed
        // TODO add your handling code here:
        String name = txtproductname.getText();
        String price = txtprice.getText();
        String quantity = String.valueOf(jSpinner1.getValue());
        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
        dtm.addRow(new Object[]{name, price, quantity, productTotal});
        grandTotal = grandTotal + productTotal;
        txtgrandtotal.setText(String.valueOf(grandTotal));

        clearProductFields();
        validateField();
    }//GEN-LAST:event_btnaddtocartActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        clearProductFields();
    }//GEN-LAST:event_btnclearActionPerformed

    private void txtcusnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcusnameKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtcusnameKeyReleased

    private void txtcusmobileKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcusmobileKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtcusmobileKeyReleased

    private void txtcusemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcusemailKeyReleased
        // TODO add your handling code here:
        validateField();
    }//GEN-LAST:event_txtcusemailKeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int index = jTable2.getSelectedRow();
        int a = JOptionPane.showConfirmDialog(null, "Do you want to remove this product", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            TableModel model = jTable2.getModel();
            String total = model.getValueAt(index, 3).toString();
            grandTotal = grandTotal - Integer.parseInt(total);
            txtgrandtotal.setText(String.valueOf(grandTotal));
            ((DefaultTableModel) jTable2.getModel()).removeRow(index);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        // TODO add your handling code here:
        CafeManagementSystem.createDialog(dialogLoading, "/images/Loading GIFs/GreenApple.gif");
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    billId = Integer.parseInt(BillDao.getId());
                    jLabel15.setText(BillDao.getId());
                    ArrayList<Category> list = CategoryDao.getAllRecorded();
                    Iterator<Category> itr = list.iterator();
                    while (itr.hasNext()) {
                        Category categoryObj = itr.next();
                        jComboBox1.addItem(categoryObj.getName());
                    }
                    String category = (String) jComboBox1.getSelectedItem();
                    productNameByCategory(category);
                } catch (Exception ex) {
                    ex.printStackTrace();
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

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchActionPerformed

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
            java.util.logging.Logger.getLogger(PlaceOrder.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlaceOrder.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlaceOrder.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlaceOrder.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlaceOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaddtocart;
    private javax.swing.JButton btnbill;
    private javax.swing.JButton btnclear;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtcusemail;
    private javax.swing.JTextField txtcusmobile;
    private javax.swing.JTextField txtcusname;
    private javax.swing.JLabel txtgrandtotal;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtproductname;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables

    private void Seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("salad.png")));
    }
}
