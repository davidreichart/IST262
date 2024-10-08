package view.filedata;

import view.Renderable;

import javax.swing.*;
import java.awt.*;

public class FileStatisticsJPanel extends JPanel implements Renderable {

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
    }

    @Override
    public void buildComponents() {
    }
}
