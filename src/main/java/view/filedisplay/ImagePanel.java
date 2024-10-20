package view.filedisplay;

import model.util.ImageInspector;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A JPanel that displays an image.
 */
public class ImagePanel extends JPanel {

    BufferedImage image;

    public ImagePanel(String imagePath) {
        this.image = ImageInspector.loadImage(imagePath);
        setPreferredSize(new Dimension(200, 200));
        setVisible(true);
    }

    /**
     * Paints the image on the panel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Draw the image
            g.drawImage(image, 0, 0, this);
        }
    }
}
