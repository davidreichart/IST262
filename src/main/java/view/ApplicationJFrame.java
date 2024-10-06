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

        // For some reason, I have to do this to make it so the jtextarea in the menu bar isn't in focus.
        // Why? I have no idea right now.
        JLabel focusGrabber = new JLabel("");
        focusGrabber.setPreferredSize(new Dimension(0, 0));
        add(focusGrabber, BorderLayout.SOUTH);

        setVisible(true);

        // This forces focus to the focusGrabber label.
        // It MUST be called after setVisible(true).
        focusGrabber.requestFocusInWindow();
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
