package model;

import java.awt.*;
import java.io.*;

/**
 * The ApplicationStateHandler class is a helper class for the sole ApplicationState object instance of the program.
 * This handler should be called upon when any direct accessing/mutation of the application's state is needed.
 * It additionally handles loading/saving of the app's state at launch and termination of the program.
 */
//todo: current implementation requires the syntax "ApplicationStateHandle.applicationState" to access any of these methods. that seems a bit long? getting around it requires code duplication
public final class ApplicationStateHandler {
    public static ApplicationState applicationState;
    private String stateLocationOnDisk;

    private ApplicationStateHandler() {}

    public static void saveApplicationStateToDisk() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(applicationState);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public static void loadApplicationStateFromDisk() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        applicationState = (ApplicationState) objectInputStream.readObject();
        fileInputStream.close();
        objectInputStream.close();
    }

    /**
     * Saves a new Application Style Template to the program's list of known style templates. This allows the user to
     * swap to this newly created template at any time without needing to enter all elements over again.
     * @param backgroundColor The background color covers the most physical space of the GUI.
     * @param accentColorOne Color of borders in GUI elements.
     * @param textColor The color of all text in the GUI.
     */
    public static void addNewStyleTemplate(Color backgroundColor, Color accentColorOne, Color textColor) {
        applicationState.getDefinedGUIStyles()
                .add(new GUIStyleTemplate(
                        backgroundColor, accentColorOne, textColor
                ));
    }
}
