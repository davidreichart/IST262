package view;

import view.filebrowser.FileBrowserJPanel;
import view.filedata.FileStatisticsJPanel;
import view.filedisplay.FileDisplayJPanel;

import javax.swing.*;
import java.awt.*;

public class ApplicationJFrame extends JFrame implements Renderable {

    private ApplicationJMenuBar applicationJMenuBar;
    private FileBrowserJPanel fileBrowserJPanel;
    private FileDisplayJPanel fileDisplayJPanel;
    private FileStatisticsJPanel fileStatisticsJPanel;

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

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configuration for the top panel (JSplitPane)
        gbc.fill = GridBagConstraints.BOTH; // Fill both x and y
        gbc.weightx = 1.0; // Take up available width
        gbc.weighty = 0.7; // Allocate 70% height to the top panel
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        JSplitPane topFileAndBrowserPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        topFileAndBrowserPane.setDividerLocation(300);
        topFileAndBrowserPane.setDividerSize(2);
        topFileAndBrowserPane.add(this.fileBrowserJPanel, JSplitPane.LEFT);
        topFileAndBrowserPane.add(this.fileDisplayJPanel, JSplitPane.RIGHT);
        mainPanel.add(topFileAndBrowserPane, gbc);

        // Configuration for the fileStatisticsJPanel
        gbc.weighty = 0.3; // Allocate 30% height to the statistics panel
        gbc.gridy = 1; // Move to the next row
        mainPanel.add(this.fileStatisticsJPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Builds the JComponent objects attached to this Renderable object.
     * {@link #addComponents()} should be called after this method.
     */
    @Override
    public void buildComponents() {
        this.fileBrowserJPanel = new FileBrowserJPanel();
        this.fileDisplayJPanel = new FileDisplayJPanel();
        this.fileStatisticsJPanel = new FileStatisticsJPanel();
        this.applicationJMenuBar = new ApplicationJMenuBar();
    }

    public ApplicationJMenuBar getApplicationJMenuBar() {
        return this.applicationJMenuBar;
    }

    public FileBrowserJPanel getFileBrowserJPanel() {
        return fileBrowserJPanel;
    }

    public FileStatisticsJPanel getFileStatisticsJPanel() {
        return fileStatisticsJPanel;
    }

    public FileDisplayJPanel getFileDisplayJPanel() {
        return fileDisplayJPanel;
    }
}
