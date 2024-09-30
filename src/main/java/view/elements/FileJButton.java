package view.elements;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FileJButton extends JButton {

    private Color backgroundColor = new Color(0x1e1f22);
    private Color hoverColor = new Color(0x3f4148);
    private float alpha = 0f;
    private boolean isHovering = false;
    private Timer timer;
    private final int buttonHeight = 10; // Desired height
    private final int buttonWidth = 120;  // Desired width

    public FileJButton() {
        this.setText("FILEJBUTTON");
        this.setForeground(Color.WHITE);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        timer = new Timer(5, e -> {
            if (isHovering) {
                alpha += 0.05f;
                if (alpha > 1f) {
                    alpha = 1f;
                    timer.stop();
                }
            } else {
                alpha -= 0.05f;
                if (alpha < 0f) {
                    alpha = 0f;
                    timer.stop();
                }
            }
            repaint();
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovering = true;
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovering = false;
                timer.start();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate the interpolated color based on alpha
        Color currentColor = new Color(
                (int) ((1 - alpha) * backgroundColor.getRed() + alpha * hoverColor.getRed()),
                (int) ((1 - alpha) * backgroundColor.getGreen() + alpha * hoverColor.getGreen()),
                (int) ((1 - alpha) * backgroundColor.getBlue() + alpha * hoverColor.getBlue())
        );

        // Draw the rounded rectangle
        g2.setColor(currentColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);

        // Draw the button text
        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - 2;
        g2.drawString(getText(), x, y);

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(buttonWidth, buttonHeight);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(buttonWidth, buttonHeight);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(buttonWidth, buttonHeight);
    }
}
