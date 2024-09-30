package view;

import view.elements.FileExplorerJTree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ControlsPanel extends JPanel {

    public ControlsPanel() {
        this.setBackground(new Color(0x1e1f22));
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setLayout(new BorderLayout());
        JTree tree = new FileExplorerJTree();
        JScrollPane scrollPane = new JScrollPane(tree);
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
