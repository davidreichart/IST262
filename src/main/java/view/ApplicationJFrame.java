package view;

import view.filebrowser.FileBrowserJPanel;
import view.filedata.FileStatisticsJPanel;
import view.filedisplay.FileDisplayJPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The main JFrame for the application.
 * This JFrame contains the main components of the application:
 * - A menu bar - {@link ApplicationJMenuBar} for file operations <br>
 * - A file browser - {@link FileBrowserJPanel} for browsing files <br>
 * - A file display - {@link FileDisplayJPanel} for displaying the contents of a file <br>
 * - A file statistics panel - {@link FileStatisticsJPanel} for displaying statistics about a file <br>
 */
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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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

        // configuration for the top panel (JSplitPane)
        gbc.fill = GridBagConstraints.BOTH; // fill both x and y
        gbc.weightx = 1.0; // take up available width
        gbc.weighty = 0.7; // allocate 70% height to the top panel
        gbc.gridx = 0; // column 0
        gbc.gridy = 0; // row 0
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

    /**
     * Returns the ApplicationJMenuBar object.
     * This part of the view is used for adding a new directory or closing the application.
     * @return the ApplicationJMenuBar object
     */
    public ApplicationJMenuBar getApplicationJMenuBar() {
        return this.applicationJMenuBar;
    }

    /**
     * Returns the FileBrowserJPanel object.
     * This part of the view is used for browsing files and directories.
     * A JTree is used to display the file system, a reference to which can be obtained using a chained getter.
     * @return the FileBrowserJPanel object
     */
    public FileBrowserJPanel getFileBrowserJPanel() {
        return fileBrowserJPanel;
    }

    /**
     * Returns the FileStatisticsJPanel object.
     * This part of the view is used for displaying statistics about a file.
     * A JTable is used to display the statistics, a reference to which can be obtained using a chained getter.
     * A JPanel is positioned to the left of this table that contains buttons for editing the contents of the table.
     * @return the FileStatisticsJPanel object
     */
    public FileStatisticsJPanel getFileStatisticsJPanel() {
        return fileStatisticsJPanel;
    }

    /**
     * Returns the FileDisplayJPanel object.
     * This part of the view is used for displaying the contents of a file.
     * @return the FileDisplayJPanel object
     */
    public FileDisplayJPanel getFileDisplayJPanel() {
        return fileDisplayJPanel;
    }
}
