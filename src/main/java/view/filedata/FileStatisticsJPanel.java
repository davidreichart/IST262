package view.filedata;

import view.Renderable;

import javax.swing.*;
import java.awt.*;

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

    @Override
    public void setAttributes() {
        setPreferredSize(new java.awt.Dimension(1000, 800));
        setLayout(new BorderLayout());
    }

    @Override
    public void addComponents() {
        add(controlsPanel, BorderLayout.WEST);
        add(dataPanel, BorderLayout.CENTER);
    }

    @Override
    public void buildComponents() {
        controlsPanel = createControlsPanel();
        statisticsTable = new JTable();
        dataPanel = new JPanel();
        dataPanel.setLayout(new BorderLayout());
        dataPanel.add(statisticsTable);
    }

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
