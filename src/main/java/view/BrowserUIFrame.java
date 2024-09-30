package view;

import javax.swing.*;
import java.awt.*;

public class BrowserUIFrame extends JFrame {

    private JSplitPane horizontalSplitPane;
    private JSplitPane verticalSplitPane;

    public BrowserUIFrame() {
        this.setSize(new Dimension(1000, 800));
        this.setTitle("Title");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.horizontalSplitPane = createControlsAndMainPanel();
        this.verticalSplitPane = createDataPanel();
        this.add(verticalSplitPane);
        this.setVisible(true);
    }

    /**
     * Builds the JSplitPane that holds two JPanels (ControlsPanel and FilePanel) which are defined in external classes.
     * @return a JSplitPane holding a ControlsPanel to the left and FilePanel to the right.
     */
    public JSplitPane createControlsAndMainPanel() {
        JSplitPane controlsAndMainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        // [controls][files]
        // [      data     ]
        controlsAndMainPanel.setLeftComponent(new ControlsPanel());
        controlsAndMainPanel.setRightComponent(new FilePanel());

        // behavior
        controlsAndMainPanel.setResizeWeight(0.5); // keeps relative split location on frame resize

        // appearance
        controlsAndMainPanel.setDividerSize(5);
        controlsAndMainPanel.setDividerLocation(this.getWidth() - (this.getWidth() / 100) * 80);

        return controlsAndMainPanel;
    }

    /**
     * Builds the JSplitPane that holds the JSplitPane consisting of a ControlsPanel and FilePanel on the top,
     * and a DataPanel on the bottom.
     * @return a JSplitPane with a JSplitPane above a DataPanel.
     */
    public JSplitPane createDataPanel() {
        JSplitPane dataPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        // [controls][files]
        // [      data     ]
        dataPanel.setTopComponent(this.horizontalSplitPane);
        dataPanel.setBottomComponent(new DataPanel());

        // behavior
        dataPanel.setResizeWeight(0.5); // keeps relative split location on frame resize

        // appearance
        dataPanel.setDividerSize(5);
        dataPanel.setDividerLocation(this.getHeight() - (this.getHeight() / 100)  * 30);

        return dataPanel;
    }

    public JSplitPane getHorizontalSplitPane() {
        return horizontalSplitPane;
    }

    public JSplitPane getVerticalSplitPane() {
        return verticalSplitPane;
    }
}
