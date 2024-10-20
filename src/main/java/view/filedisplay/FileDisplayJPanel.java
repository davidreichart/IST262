package view.filedisplay;

import model.data.filetypes.ImageFile;
import view.Renderable;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class FileDisplayJPanel extends JScrollPane implements Renderable {

    public FileDisplayJPanel() {
        setAttributes();
        buildComponents();
        addComponents();

        setVisible(true);
    }

    @Override
    public void setAttributes() {
        setPreferredSize(new java.awt.Dimension(1000, 600));
        getVerticalScrollBar().setUnitIncrement(20);
    }

    @Override
    public void addComponents() {
    }

    @Override
    public void buildComponents() {
    }

    public void updateCurrentlyRenderedImages(HashSet<ImageFile> imageFiles) {
        renderImages(imageFiles);
        repaint();
    }

    public void renderImages(HashSet<ImageFile> imageFiles) {
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(imageFiles.size() / 4, 4));

        for (ImageFile imageFile : imageFiles) {
            ImagePanel imagePanel = new ImagePanel(imageFile.METADATA().absoluteFilePath());
            content.add(imagePanel);
        }

        getViewport().removeAll();
        getViewport().add(content);
    }
}
