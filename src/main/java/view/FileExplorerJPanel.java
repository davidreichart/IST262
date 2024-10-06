package view;

import javax.swing.*;
import java.awt.*;

public class FileExplorerJPanel extends JPanel implements Renderable {

    public FileExplorerJPanel() {
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
        setBackground(Color.BLUE);
        setPreferredSize(new java.awt.Dimension(1000, 800));
        setLayout(new GridBagLayout());
    }

    /**
     * Attaches JComponent objects to this Renderable object.
     * {@link #buildComponents()} should be before this method.
     */
    @Override
    public void addComponents() {

    }

    /**
     * Builds the JComponent objects attached to this Renderable object.
     * {@link #addComponents()} should be called after this method.
     */
    @Override
    public void buildComponents() {

    }
}
