import controller.ApplicationController;
import model.ApplicationContext;
import model.data.FileTag;
import model.data.filetypes.SystemDirectory;
import view.ApplicationJFrame;

import javax.swing.*;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        runGUI();
    }


    public static void runGUI() {
        // https://stackoverflow.com/questions/6567870/what-does-swingutilities-invokelater-do
        // https://docs.oracle.com/javase/8/docs/api/javax/swing/SwingUtilities.html#invokeLater-java.lang.Runnable-
        // This is seemingly the "correct" way to run a Swing application.
        // "This method should be used when an application thread needs to update the GUI."
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // frame holds GUI JComponents
                ApplicationJFrame applicationJFrame = new ApplicationJFrame();

                // context holds data (model)
                ApplicationContext applicationContext = new ApplicationContext(new TreeSet<FileTag>());

                // controller updates the frame and context based on user input
                ApplicationController applicationController = new ApplicationController(applicationJFrame, applicationContext);

                // sample data using /test/resources
                applicationContext.getSystemDirectoryList().addDirectory(new SystemDirectory("src/test/resources"));
            }
        });
    }
}
