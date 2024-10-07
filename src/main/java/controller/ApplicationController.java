package controller;

import controller.filebrowser.FileBrowserJPanelController;
import model.ApplicationContext;
import view.ApplicationJFrame;

public class ApplicationController {

    ApplicationJMenuBarController applicationJMenuBarController;
    FileBrowserJPanelController fileBrowserJPanelController;

    public ApplicationController(ApplicationJFrame applicationJFrame, ApplicationContext applicationContext) {
        this.applicationJMenuBarController = new ApplicationJMenuBarController(applicationJFrame, applicationContext);
        this.fileBrowserJPanelController = new FileBrowserJPanelController(applicationJFrame, applicationContext);
        configureDirectoryListListeners(applicationJFrame, applicationContext);

    }

    public void configureDirectoryListListeners(ApplicationJFrame frame, ApplicationContext context) {
        context.getSystemDirectoryList().addListener(frame.getFileBrowserJPanel());
    }
}
