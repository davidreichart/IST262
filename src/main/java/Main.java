import controller.ApplicationController;
import controller.ApplicationJMenuBarController;
import model.context.ApplicationContext;
import model.context.UserDirectory;
import model.data.FileTag;
import view.ApplicationJFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        runUITest();
    }

    public static void runUITest() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ApplicationJFrame applicationJFrame = new ApplicationJFrame();
                ApplicationContext applicationContext = new ApplicationContext(new TreeSet<FileTag>(), new ArrayList<UserDirectory>());
                ApplicationController applicationController = new ApplicationController(applicationJFrame, applicationContext);
            }
        });
    }
}
