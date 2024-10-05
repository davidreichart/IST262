package view.panels;

import javax.swing.*;

public class ControlFileSplitPane extends JSplitPane {

    public ControlFileSplitPane() {
        super(JSplitPane.HORIZONTAL_SPLIT);
        this.setLeftComponent(new ControlsPanel());
        this.setRightComponent(new FilePanel());
        this.setResizeWeight(0);
        this.setDividerSize(5);
        this.setDividerLocation(300);
    }
}
