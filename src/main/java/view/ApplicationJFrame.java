package view;

import view.filebrowser.FileBrowserJPanel;

import javax.swing.*;
import java.awt.*;

public class ApplicationJFrame extends JFrame implements Renderable {

    private ApplicationJMenuBar applicationJMenuBar;
    private FileBrowserJPanel fileBrowserJPanel;

    public ApplicationJFrame() {
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
        setTitle("IST 261");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Attaches JComponent objects to this Renderable object.
     * {@link #buildComponents()} should be before this method.
     */
    @Override
    public void addComponents() {
        setJMenuBar(applicationJMenuBar);
        JSplitPane topPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        topPanel.setDividerLocation(300);
        topPanel.setDividerSize(2);
        this.fileBrowserJPanel = new FileBrowserJPanel();
        topPanel.setLeftComponent(this.fileBrowserJPanel);
        topPanel.setRightComponent(new JPanel());
        add(topPanel, BorderLayout.CENTER);
    }

    /**
     * Builds the JComponent objects attached to this Renderable object.
     * {@link #addComponents()} should be called after this method.
     */
    @Override
    public void buildComponents() {
        this.applicationJMenuBar = new ApplicationJMenuBar();
    }

    public ApplicationJMenuBar getApplicationJMenuBar() {
        return this.applicationJMenuBar;
    }

    public FileBrowserJPanel getFileBrowserJPanel() {
        return fileBrowserJPanel;
    }
}
