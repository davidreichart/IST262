package controller;

import model.context.ApplicationContext;
import view.ApplicationJFrame;

public class ApplicationController {

    ApplicationJMenuBarController applicationJMenuBarController;

    public ApplicationController(ApplicationJFrame applicationJFrame, ApplicationContext applicationContext) {
        this.applicationJMenuBarController = new ApplicationJMenuBarController(applicationJFrame, applicationContext);
        configureDirectoryListListeners(applicationJFrame, applicationContext);
    }

    public void configureDirectoryListListeners(ApplicationJFrame frame, ApplicationContext context) {
        context.getDirectoryList().addListener(frame.getFileBrowserJPanel());
    }
}
