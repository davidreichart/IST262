package controller;

import controller.filebrowser.FileBrowserJPanelController;
import model.context.ApplicationContext;
import view.ApplicationJFrame;
import view.filebrowser.FileBrowserJPanel;

public class ApplicationController {

    ApplicationJMenuBarController applicationJMenuBarController;
    FileBrowserJPanelController fileBrowserJPanelController;

    public ApplicationController(ApplicationJFrame applicationJFrame, ApplicationContext applicationContext) {
        this.applicationJMenuBarController = new ApplicationJMenuBarController(applicationJFrame, applicationContext);
        this.fileBrowserJPanelController = new FileBrowserJPanelController(applicationJFrame, applicationContext);
        configureDirectoryListListeners(applicationJFrame, applicationContext);
    }

    public void configureDirectoryListListeners(ApplicationJFrame frame, ApplicationContext context) {
        context.getDirectoryList().addListener(frame.getFileBrowserJPanel());
    }
}
