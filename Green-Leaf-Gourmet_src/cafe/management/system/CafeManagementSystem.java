/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe.management.system;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Color;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.UIDefaults;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author kingrishabdugar
 */
public class CafeManagementSystem {

    public static String apostrophe(String s) {
        s = s.replace("\'", "\\\'");
        return s;
    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    public static void createDialog(JDialog d, String gif) {
        d.setUndecorated(true);
        d.setLocationRelativeTo(null);
        d.setBackground(new Color(0, 0, 0, 0));
        JLabel l = new JLabel(new ImageIcon((gif)));
        d.setSize(200, 200);
        d.add(l);
        d.setVisible(true);
        d.setAlwaysOnTop(true);
        d.setLocationRelativeTo(null);
        d.setBackground(new Color(0, 0, 0, 0));
        //d.setUndecorated(true);
        d.pack();

    }

    public static void loadWithGif(JFrame oldClass, JFrame newClass, String gifAddress) {

        JDialog dialogLoading = new JDialog();
        createDialog(dialogLoading, gifAddress);
        dialogLoading.setVisible(false);
        oldClass.setVisible(false);
        oldClass.dispose();

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                newClass.setVisible(true); // this is the long process
                return null;
            }

            @Override
            protected void done() {
                Timer timer = new Timer(2500, (ActionEvent evt1) -> {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dialogLoading.setVisible(false);
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
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.net.URL url = ClassLoader.getSystemResource("salad-2.png");

        try {
            // TODO code application logic here
            Properties p = new Properties();
            p.put("windowTitleFont", "Lucida Grande PLAIN 15");
            p.put("logoString", "");
            p.put("windowDecoration", "off");
            McWinLookAndFeel.setCurrentTheme(p);
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
            //font of popups dialogs etc
            setUIFont(new javax.swing.plaf.FontUIResource("lucida sans unicode", Font.PLAIN, 13));
            //font of JTable Headers overall
            Font font = UIManager.getFont("TableHeader.font");
            font = new Font("lucida sans unicode", Font.BOLD, 15);
            UIManager.put("TableHeader.font", font);
            
          //  UIDefaults uiDefaults = UIManager.getDefaults();
          //  uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(Color.GREEN));
          //  uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(Color.PINK));
          //  JFrame.setDefaultLookAndFeelDecorated(true);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CafeManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CafeManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CafeManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CafeManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SplashScreen().setVisible(true);
            }

        });
    }

}
