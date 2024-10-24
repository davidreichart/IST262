package view.filedisplay;

import model.util.ImageInspector;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private String imagePath;
    private BufferedImage image;

    public ImagePanel(String imagePath) {
        this.imagePath = imagePath;
        setPreferredSize(new Dimension(200, 200));
        setVisible(true);
        loadImage(); // async image load/render
    }

    private void loadImage() {
        // use SwingWorker to load the image in a background thread
        SwingWorker<BufferedImage, Void> worker = new SwingWorker<>() {
            // the swing worker will process image resizing in the background on another thread
            // this dramatically improves rendering performance but at a memory cost
            @Override
            protected BufferedImage doInBackground() {
                return ImageInspector.loadImage(imagePath);
            }

            // this method is automatically called after the swing worker has rescaled the image
            @Override
            protected void done() {
                try {
                    image = get();
                    repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // get the original dimensions of the image
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            // calculate the scaling factor to maintain aspect ratio
            double scale = Math.min((double) panelWidth / imageWidth, (double) panelHeight / imageHeight);

            // calculate the new dimensions of the image
            int newWidth = (int) (imageWidth * scale);
            int newHeight = (int) (imageHeight * scale);

            // center the image within the panel
            int x = (panelWidth - newWidth) / 2;
            int y = (panelHeight - newHeight) / 2;

            // draw the scaled image
            g.drawImage(image, x, y, newWidth, newHeight, this);
        }
    }
}