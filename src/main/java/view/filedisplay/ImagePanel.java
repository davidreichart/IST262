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
        loadImage(); // Start loading the image asynchronously
    }

    private void loadImage() {
        // Use SwingWorker to load the image in a background thread
        SwingWorker<BufferedImage, Void> worker = new SwingWorker<>() {
            @Override
            protected BufferedImage doInBackground() {
                return ImageInspector.loadImage(imagePath);
            }

            @Override
            protected void done() {
                try {
                    image = get(); // Get the loaded image
                    repaint(); // Repaint the panel with the new image
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute(); // Start the worker
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // Get the original dimensions of the image
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            // Calculate the scaling factor to maintain aspect ratio
            double scale = Math.min((double) panelWidth / imageWidth, (double) panelHeight / imageHeight);

            // Calculate the new dimensions of the image
            int newWidth = (int) (imageWidth * scale);
            int newHeight = (int) (imageHeight * scale);

            // Center the image within the panel
            int x = (panelWidth - newWidth) / 2;
            int y = (panelHeight - newHeight) / 2;

            // Draw the scaled image
            g.drawImage(image, x, y, newWidth, newHeight, this);
        }
    }
}