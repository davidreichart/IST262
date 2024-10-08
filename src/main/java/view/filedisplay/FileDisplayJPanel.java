package view.filedisplay;

import model.data.filetypes.SystemDirectory;
import model.data.filetypes.SystemDirectoryListListener;
import view.Renderable;

import javax.swing.*;
import java.util.HashSet;

public class FileDisplayJPanel extends JPanel implements Renderable {

    public FileDisplayJPanel() {
        setAttributes();
        buildComponents();
        addComponents();

        setVisible(true);
    }

    @Override
    public void setAttributes() {
        setPreferredSize(new java.awt.Dimension(1000, 600));
    }

    @Override
    public void addComponents() {
    }

    @Override
    public void buildComponents() {
    }
}
