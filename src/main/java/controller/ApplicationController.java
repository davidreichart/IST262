package controller;

import controller.filebrowser.FileBrowserJPanelController;
import controller.filedata.FileStatisticsJPanelController;
import controller.filedisplay.FileDisplayJPanelController;
import model.ApplicationContext;
import view.ApplicationJFrame;

public class ApplicationController {

    ApplicationJMenuBarController applicationJMenuBarController;
    FileBrowserJPanelController fileBrowserJPanelController;
    FileStatisticsJPanelController fileStatisticsJPanelController;
    FileDisplayJPanelController fileDisplayJPanelController;

    public ApplicationController(ApplicationJFrame applicationJFrame, ApplicationContext applicationContext) {
        this.applicationJMenuBarController = new ApplicationJMenuBarController(applicationJFrame, applicationContext);
        this.fileBrowserJPanelController = new FileBrowserJPanelController(applicationJFrame, applicationContext);
        this.fileStatisticsJPanelController = new FileStatisticsJPanelController(applicationJFrame, applicationContext);
        this.fileDisplayJPanelController = new FileDisplayJPanelController(applicationJFrame, applicationContext);

        // these are the views that need to know when any changes are made to the directory list
        configureDirectoryListListeners(applicationJFrame, applicationContext);

    }

    public void configureDirectoryListListeners(ApplicationJFrame frame, ApplicationContext context) {
        context.getSystemDirectoryList().addListener(frame.getFileBrowserJPanel());
    }
}
