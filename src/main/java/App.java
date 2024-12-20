import controller.ApplicationController;
import model.ApplicationContext;
import model.Deserializer;
import model.Serializer;
import model.data.filetypes.ImageFile;
import model.data.filetypes.SystemDirectory;
import model.util.FileInspector;
import view.ApplicationJFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Objects;

public class App implements Runnable {

    private ApplicationContext applicationContext;
    private ApplicationJFrame applicationJFrame;
    private ApplicationController applicationController;
    private Serializer serializer;

    public App() {
        this.applicationJFrame = new ApplicationJFrame();
        loadApplicationContext();
        this.applicationController = new ApplicationController(this.applicationJFrame, this.applicationContext);
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        this.serializer = new Serializer(this.applicationContext, this.applicationJFrame, this.applicationController);
        addExitSerializationListener();

        // test data
        // this must be added AFTER the context and frames have been loaded in otherwise the tree will not show the test data
        try {
            // add directory to tracked context
            applicationContext.getSystemDirectoryList().addDirectory(new SystemDirectory("src/test/resources"));
            // add all image files in the test resources directory to the context
            File[] resources = new File("src/test/resources").listFiles();
            for (File resource : Objects.requireNonNull(resources)) {
                if (resource.isFile() && FileInspector.isImageFile(resource)) {
                    ImageFile imageFile = new ImageFile(resource.getAbsolutePath());
                    applicationContext.addNewSystemFile(imageFile);
                }
            }
        } catch (Exception e) {
            System.err.println("Test data src/test/resources already exists in the application context.");
        }
    }

    /**
     * Attempts to load the application context from a serialized file.
     * If no file is found, a new application context is created.
     * This "new" application context will only consist of a test set of files located at "src/test/resources".
     */
    private void loadApplicationContext() {
        ApplicationContext applicationContext = Deserializer.loadApplicationContext();
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

    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    public ApplicationController getApplicationController() {
        return this.applicationController;
    }

    public ApplicationJFrame getApplicationJFrame() {
        return this.applicationJFrame;
    }
}
