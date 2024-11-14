package controller;

import controller.filebrowser.FileBrowserJPanelController;
import controller.filebrowser.SortedFileBrowserJPanelController;
import controller.filedata.FileStatisticsJPanelController;
import controller.filedisplay.FileDisplayJPanelController;
import model.ApplicationContext;
import view.ApplicationJFrame;

/**
 * The ApplicationController is a meta-controller that initializes all the other controllers in the application.
 * The ApplicationController is initialized in the Main class.
 */
public class ApplicationController {

    ApplicationJMenuBarController applicationJMenuBarController;
    FileBrowserJPanelController fileBrowserJPanelController;
    FileStatisticsJPanelController fileStatisticsJPanelController;
    FileDisplayJPanelController fileDisplayJPanelController;
    SortedFileBrowserJPanelController sortedFileBrowserJPanelController;

    public ApplicationController(ApplicationJFrame applicationJFrame, ApplicationContext applicationContext) {
        this.applicationJMenuBarController = new ApplicationJMenuBarController(applicationJFrame, applicationContext);
        this.fileBrowserJPanelController = new FileBrowserJPanelController(applicationJFrame, applicationContext);
        this.fileStatisticsJPanelController = new FileStatisticsJPanelController(applicationJFrame, applicationContext);
        this.fileDisplayJPanelController = new FileDisplayJPanelController(applicationJFrame, applicationContext);
        this.sortedFileBrowserJPanelController = new SortedFileBrowserJPanelController(applicationJFrame, applicationContext);

        // these are the views that need to know when any changes are made to the directory list
        configureDirectoryListListeners(applicationJFrame, applicationContext);

    }

    /**
     * Adds the main view and model classes to the directory list listeners.
     * @param frame The main view class.
     * @param context The main model class.
     */
    public void configureDirectoryListListeners(ApplicationJFrame frame, ApplicationContext context) {
        context.getSystemDirectoryList().addListener(frame.getFileBrowserJPanel());
    }
}
