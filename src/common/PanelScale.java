/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kingr
 */
public class PanelScale extends JPanel {

    Image iconbg;
    private BufferedImage backgroundImage;
    Image newImage;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();
    int height = (int) screenSize.getHeight();

    public PanelScale(String file) throws IOException {
        iconbg = new ImageIcon(getClass().getResource(file)).getImage();
        backgroundImage = ImageIO.read(getClass().getResource(file));
        this.newImage = backgroundImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
    }

    @Override
    public Dimension getPreferredSize() {
        if (backgroundImage == null) {
            return new Dimension(1280, 720);
        }
        return new Dimension(backgroundImage.getWidth(), backgroundImage.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage == null) {
            return;
        }
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(newImage, 0, 0, getWidth(), getHeight(), this);
        g2d.dispose();

    }
}
