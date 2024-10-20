package view.filedata;

import view.Renderable;

import javax.swing.*;
import java.awt.*;

public class FileStatisticsJPanel extends JPanel implements Renderable {

    private JButton editDataButton;

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
        add(editDataButton, BorderLayout.NORTH);
    }

    @Override
    public void buildComponents() {
        editDataButton = new JButton("Edit Data");
        editDataButton.setEnabled(false);
    }

    public JButton getEditDataButton() {
        return editDataButton;
    }
}
