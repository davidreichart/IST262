package view.filedisplay;

import model.data.filetypes.ImageFile;
import view.Renderable;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * A scrollable JPanel that displays images.
 */
public class FileDisplayJPanel extends JScrollPane implements Renderable {

    public FileDisplayJPanel() {
        setAttributes();
        buildComponents();
        addComponents();

        setVisible(true);
    }

    /**
     * Sets the default style and behavior of this Renderable object.
     */
    @Override
    public void setAttributes() {
        setPreferredSize(new java.awt.Dimension(1000, 600));
        getVerticalScrollBar().setUnitIncrement(20);
    }

    /**
     * Attaches JComponent objects to this Renderable object.
     * {@link #buildComponents()} should be before this method.
     */
    @Override
    public void addComponents() {
    }

    /**
     * Builds the components of this Renderable object.
     */
    @Override
    public void buildComponents() {
    }

    /**
     * Changes the view to render all images in the given set.
     * @param imageFiles The images to render.
     */
    public void updateCurrentlyRenderedImages(HashSet<ImageFile> imageFiles) {
        renderImages(imageFiles);
        repaint();
    }

    /**
     * Renders the given set of images.
     * This method is intended to be called by {@link #updateCurrentlyRenderedImages(HashSet)}.
     * @param imageFiles The images to render.
     */
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
