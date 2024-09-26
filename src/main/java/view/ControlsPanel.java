package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlsPanel extends JPanel {

    public ControlsPanel() {
        this.setBackground(new Color(0x3f4148));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setLayout(new GridLayout(10, 1));

        JLabel labelOne = new JLabel("This is a test label, number one");
        JButton buttonOne = new JButton("ButtonOne");

        this.add(labelOne);
        this.add(styledButton());
    }

    public void paintComponent(Graphics graphics) {
        //super.paintComponent(graphics);
        //graphics.setColor(new Color(0x2b2d30));
        //graphics.fillRoundRect(5, 5, this.getWidth() - 10, this.getHeight() - 10, 20, 20);

    }

    public JButton styledButton() {
        JButton button = new JButton("BUTTON");
        button.setBackground(Color.black);
        button.setForeground(Color.WHITE);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.blue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.black);
            }
        });
        button.setSize(new Dimension(this.getWidth(), this.getHeight()));
        return button;
    }
}
