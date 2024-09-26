package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FilePanel extends JPanel {

    public FilePanel() {
        this.setBackground(new Color(0x3f4148));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setLayout(new GridBagLayout());
    }
}
