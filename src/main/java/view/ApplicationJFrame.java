package view;

import view.filebrowser.FileBrowserJPanel;
import view.filebrowser.SortedFileBrowserJPanel;
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
 * - A sorted file browser panel - {@link SortedFileBrowserJPanel} for browsing files in a sorted manner <br>
 * - A split pane - {@link JSplitPane} for the file browser and file display panels <br>
 */
public class ApplicationJFrame extends JFrame implements Renderable {

    private ApplicationJMenuBar applicationJMenuBar;
    private FileBrowserJPanel fileBrowserJPanel;
    private SortedFileBrowserJPanel sortedFileBrowserJPanel;
    private FileDisplayJPanel fileDisplayJPanel;
    private FileStatisticsJPanel fileStatisticsJPanel;
    private JSplitPane topFileAndBrowserPane;

    public ApplicationJFrame() {
        setAttributes();
        buildComponents();
        addComponents();

        // take focus away from menu bar text area on launch
        JLabel focusGrabber = new JLabel("");
        focusGrabber.setPreferredSize(new Dimension(0, 0));
        add(focusGrabber, BorderLayout.SOUTH);

        setVisible(true);

        // This forces focus to the focusGrabber label.
        // It MUST be called after setVisible(true).
        focusGrabber.requestFocusInWindow();
        fileBrowserJPanel.getFileTree().reloadTree();
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
        renderMainPanel(fileBrowserJPanel, fileDisplayJPanel); // initial state uses file tree
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
        this.sortedFileBrowserJPanel = new SortedFileBrowserJPanel();
        sortedFileBrowserJPanel.setVisible(false); // initial state should be hidden
    }

    /**
     * Re-renders the main panel of the application.
     * @param left The JPanel to sit to the left of the file display panel.
     * @param right The file display panel to display file contents.
     */
    public void renderMainPanel(JPanel left, FileDisplayJPanel right) {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // configuration for the top panel (JSplitPane)
        gbc.fill = GridBagConstraints.BOTH; // fill both x and y
        gbc.weightx = 1.0; // take up available width
        gbc.weighty = 0.7; // allocate 70% height to the top panel
        gbc.gridx = 0; // column 0
        gbc.gridy = 0; // row 0
        this.topFileAndBrowserPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
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

    public JSplitPane getTopFileAndBrowserPane() {
        return topFileAndBrowserPane;
    }

    public void setTopFileAndBrowserPane(JSplitPane topFileAndBrowserPane) {
        this.topFileAndBrowserPane = topFileAndBrowserPane;
    }

    public SortedFileBrowserJPanel getSortedFileBrowserJPanel() {
        return sortedFileBrowserJPanel;
    }

    public void setSortedFileBrowserJPanel(SortedFileBrowserJPanel sortedFileBrowserJPanel) {
        this.sortedFileBrowserJPanel = sortedFileBrowserJPanel;
    }
}
