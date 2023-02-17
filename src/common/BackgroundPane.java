/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author kingr
 */
    public class BackgroundPane extends JPanel {

        private BufferedImage backgroundImage;

        public BackgroundPane() throws IOException {
            backgroundImage = ImageIO.read(getClass().getResource("/images/largesignup.png"));
        }

        @Override
        public Dimension getPreferredSize() {
            if (backgroundImage == null) {
                return new Dimension(400, 400);
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
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }

    }
