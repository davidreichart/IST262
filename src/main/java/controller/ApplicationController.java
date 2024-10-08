package controller;

import controller.filebrowser.FileBrowserJPanelController;
import controller.filedata.FileStatisticsJPanelController;
import model.ApplicationContext;
import view.ApplicationJFrame;

public class ApplicationController {

    ApplicationJMenuBarController applicationJMenuBarController;
    FileBrowserJPanelController fileBrowserJPanelController;
    FileStatisticsJPanelController fileStatisticsJPanelController;

    public ApplicationController(ApplicationJFrame applicationJFrame, ApplicationContext applicationContext) {
        this.applicationJMenuBarController = new ApplicationJMenuBarController(applicationJFrame, applicationContext);
        this.fileBrowserJPanelController = new FileBrowserJPanelController(applicationJFrame, applicationContext);
        this.fileStatisticsJPanelController = new FileStatisticsJPanelController(applicationJFrame, applicationContext);
        configureDirectoryListListeners(applicationJFrame, applicationContext);

    }

    public void configureDirectoryListListeners(ApplicationJFrame frame, ApplicationContext context) {
        context.getSystemDirectoryList().addListener(frame.getFileBrowserJPanel());
    }
}
