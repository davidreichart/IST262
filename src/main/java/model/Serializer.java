package model;

import controller.ApplicationController;
import view.ApplicationJFrame;
import view.filebrowser.UserFileJTree;

import java.io.*;

public final class Serializer {

    private final ApplicationContext CONTEXT;
    private final ApplicationJFrame FRAME;
    private final ApplicationController CONTROLLER;
    private final String FILE_PATH = "src/main/resources/state.ser";

    public Serializer(ApplicationContext context, ApplicationJFrame frame, ApplicationController controller) {
        this.CONTEXT = context;
        this.FRAME = frame;
        this.CONTROLLER = controller;
    }

    /**
     * Saves the current state of the application to a serialized file.
     * The file is saved at the path specified by {@link #FILE_PATH}.
     * The following classes are directly serialized:
     * <ul>
     *     <li>{@link ApplicationContext}</li>
     *     <li>{@link UserFileJTree}</li>
     * </ul>
     */
    public void saveState() {
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;

        // creating file streams
        try {
            fileOut = new FileOutputStream(FILE_PATH);
            out = new ObjectOutputStream(fileOut);
        } catch (IOException e) {
            System.err.println("Cannot save state.ser at " + FILE_PATH);
            return; // cannot continue without file streams
        }

        // writing objects to file
        try {
            out.writeObject(CONTEXT);
            out.writeObject(FRAME.getFileBrowserJPanel().getFileTree());
        } catch (IOException e) {
            System.err.println("Error writing objects to state.ser");
            e.printStackTrace();
        }

        // finishing up
        try {
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in state.ser");
        } catch (IOException e) {
            System.err.println("Error closing file streams, state.ser may be corrupted");
            e.printStackTrace();
        }
    }
}
