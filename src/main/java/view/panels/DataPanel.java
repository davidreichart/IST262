package view.panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DataPanel extends JPanel {

    public DataPanel() {
        this.setBackground(new Color(0x1e1f22));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setLayout(new GridBagLayout());
    }
}
