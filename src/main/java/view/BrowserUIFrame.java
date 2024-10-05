package view;

import view.panels.*;

import javax.swing.*;
import java.awt.*;

public class BrowserUIFrame extends JFrame {

    public BrowserUIFrame() {
        this.setSize(new Dimension(1000, 800));
        this.setTitle("Title");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(new DataSplitPane());
        this.setVisible(true);
    }
}
