package view.filedata;

import view.Renderable;

import javax.swing.*;
import java.awt.*;

/**
 * The FileStatisticsJPanel class is a JPanel containing a JTable for displaying file statistics.
 * It also contains buttons for editing the file path, file size, image width, and image height.
 */
public class FileStatisticsJPanel extends JPanel implements Renderable {

    private JButton editFilePathButton;
    private JButton editFileSizeButton;
    private JButton editImageWidthButton;
    private JButton editImageHeightButton;
    private JPanel controlsPanel;
    private JPanel dataPanel;
    private JTable statisticsTable;

    public FileStatisticsJPanel() {
        setAttributes();
        buildComponents();
        addComponents();

        setVisible(true);
    }

    /**
     * Sets the attributes of the FileStatisticsJPanel.
     */
    @Override
    public void setAttributes() {
        setPreferredSize(new java.awt.Dimension(1000, 800));
        setLayout(new BorderLayout());
    }

    /**
     * Attaches JComponent objects to this view object.
     * {@link #buildComponents()} should be before this method.
     */
    @Override
    public void addComponents() {
        add(controlsPanel, BorderLayout.WEST);
        add(dataPanel, BorderLayout.CENTER);
    }

    /**
     * Builds the JComponent objects attached to this view object.
     * {@link #addComponents()} should be called after this method.
     */
    @Override
    public void buildComponents() {
        controlsPanel = createControlsPanel();
        statisticsTable = new JTable();
        // the data panel holds the jtable containing the file statistics
        dataPanel = new JPanel();
        dataPanel.setLayout(new BorderLayout());
        dataPanel.add(statisticsTable);
    }

    /**
     * Creates the controls panel containing buttons for editing file statistics.
     * @return the controls panel
     */
    private JPanel createControlsPanel() {
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(4, 1));

        editFilePathButton = new JButton("Edit File Path");
        editFilePathButton.setEnabled(false);

        editFileSizeButton = new JButton("Edit File Size");
        editFileSizeButton.setEnabled(false);

        editImageWidthButton = new JButton("Edit Image Width");
        editImageWidthButton.setEnabled(false);

        editImageHeightButton = new JButton("Edit Image Height");
        editImageHeightButton.setEnabled(false);

        controlsPanel.add(editFilePathButton);
        controlsPanel.add(editFileSizeButton);
        controlsPanel.add(editImageWidthButton);
        controlsPanel.add(editImageHeightButton);

        return controlsPanel;
    }

    public JButton getEditFilePathButton() {
        return editFilePathButton;
    }

    public JButton getEditFileSizeButton() {
        return editFileSizeButton;
    }

    public JPanel getControlsPanel() {
        return controlsPanel;
    }

    public JButton getEditImageWidthButton() {
        return editImageWidthButton;
    }

    public JButton getEditImageHeightButton() {
        return editImageHeightButton;
    }

    public JPanel getDataPanel() {
        return dataPanel;
    }
}
