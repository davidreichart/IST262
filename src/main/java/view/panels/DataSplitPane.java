package view.panels;

import javax.swing.*;

public class DataSplitPane extends JSplitPane {

    public DataSplitPane() {
        super(JSplitPane.VERTICAL_SPLIT);
        this.setTopComponent(new ControlFileSplitPane());
        this.setBottomComponent(new DataPanel());
        this.setResizeWeight(0.5);
        this.setDividerSize(5);
        this.setDividerLocation(500);
    }
}
