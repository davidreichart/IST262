package controller.filebrowser;

import model.context.ApplicationContext;
import view.ApplicationJFrame;

import javax.swing.event.*;
import javax.swing.tree.DefaultTreeModel;

public class FileBrowserJPanelController {

    private ApplicationJFrame frame;
    private ApplicationContext context;
    int lastCheckedDirectoryCount;


    public FileBrowserJPanelController(ApplicationJFrame frame, ApplicationContext context) {
        this.frame = frame;
        this.context = context;
        this.lastCheckedDirectoryCount = 0;
    }
}
