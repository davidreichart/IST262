import controller.ApplicationController;
import model.ApplicationContext;
import model.Deserializer;
import model.Serializer;
import model.data.FileTag;
import model.data.filetypes.SystemDirectory;
import view.ApplicationJFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TreeSet;

public class App implements Runnable {

    private ApplicationContext applicationContext;
    private ApplicationJFrame applicationJFrame;
    private ApplicationController applicationController;
    private Serializer serializer;

    public App() {
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        loadApplicationContext();
        this.applicationJFrame = new ApplicationJFrame();
        this.applicationController = new ApplicationController(this.applicationJFrame, this.applicationContext);
        this.serializer = new Serializer(this.applicationContext, this.applicationJFrame, this.applicationController);
        addExitSerializationListener();
    }

    /**
     * Attempts to load the application context from a serialized file.
     * If no file is found, a new application context is created.
     * This "new" application context will only consist of a test set of files located at "src/test/resources".
     */
    private void loadApplicationContext() {
        ApplicationContext applicationContext = Deserializer.loadApplicationContext();
        try {
            applicationContext.getSystemDirectoryList().addDirectory(new SystemDirectory("src/test/resources"));
        } catch (Exception e) {
            System.err.println("Directory already exists in the application context");
        }
        this.applicationContext = applicationContext;
    }

    /**
     * Attaches a listener to the application JFrame that will serialize the application context when the JFrame is closed.
     * The listener will also prompt the user to confirm that they want to exit the program.
     */
    private void addExitSerializationListener() {
        this.applicationJFrame.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * A dialog box confirms that the user wants to exit the program.
             * If the user confirms, the application context is serialized and the program exits.
             * @param e the event to be processed
             */
            @Override
            public void windowClosing(WindowEvent e) {
                // returns an integer indicating the user's choice
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit the program? All files/directories displayed in the file tree will be saved.",
                        "Exit Program Message Box",
                        JOptionPane.YES_NO_OPTION);

                // the user must confirm exit
                if (confirmed == JOptionPane.YES_OPTION) {
                    Serializer serializer = new Serializer(applicationContext, applicationJFrame, applicationController);
                    serializer.saveState();
                    applicationJFrame.dispose();
                    System.exit(0);
                }
            }
        });
    }
}
