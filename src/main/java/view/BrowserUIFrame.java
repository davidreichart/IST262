package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BrowserUIFrame extends JFrame {

    private JSplitPane horizontalSplitPane;
    private JSplitPane verticalSplitPane;

    public BrowserUIFrame() {
        this.setSize(new Dimension(1000, 800));
        this.setTitle("Title");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        this.horizontalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        horizontalSplitPane.setLeftComponent(leftPanel);
        horizontalSplitPane.setRightComponent(rightPanel);
        horizontalSplitPane.setDividerLocation(100);
        horizontalSplitPane.setDividerLocation(
                this.getWidth() - (this.getWidth() / 100) * 80
        );

        JPanel bottomPanel = new JPanel();
        this.verticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        verticalSplitPane.setTopComponent(horizontalSplitPane);
        verticalSplitPane.setBottomComponent(bottomPanel);
        verticalSplitPane.setDividerLocation(
                this.getHeight() - (this.getHeight() / 100)  * 30
        );

        this.add(verticalSplitPane);

        this.setVisible(true);
    }

    public JSplitPane getHorizontalSplitPane() {
        return horizontalSplitPane;
    }

    public JSplitPane getVerticalSplitPane() {
        return verticalSplitPane;
    }
}
