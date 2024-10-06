import controller.ApplicationJMenuBarController;
import model.context.ApplicationContext;
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
                ApplicationContext applicationContext = new ApplicationContext(new TreeSet<FileTag>(), new ArrayList<String>());
                ApplicationJMenuBarController applicationJMenuBarController = new ApplicationJMenuBarController(applicationJFrame, applicationContext);
            }
        });
    }
}
