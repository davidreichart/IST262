package model;

import view.filebrowser.UserFileJTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.TreeSet;

public final class Deserializer {

    private static final String FILE_PATH = "src/main/resources/state.ser";

    public Deserializer() {
    }

    /**
     * Attempts to load the ApplicationContext from a serialized file.
     * If no file is found, a new default ApplicationContext is created.
     * @return the ApplicationContext loaded from the serialized file, or a new default ApplicationContext if the file is not found
     */
    public static ApplicationContext loadApplicationContext() {
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        ApplicationContext context = null;

        // creating file streams
        try {
            fileIn = new FileInputStream(FILE_PATH);
            in = new ObjectInputStream(fileIn);
        } catch (IOException e) {
            System.err.println("loadApplicationContext :: File state.ser not found at " + FILE_PATH);
            return new ApplicationContext(new TreeSet<>()); // cannot continue without file streams
        }

        // reading objects from file
        try {
            context = (ApplicationContext) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading ApplicationContext from state.ser");
        }

        // finishing up
        try {
            in.close();
            fileIn.close();
            System.out.println("Deserialized ApplicationContext is loaded from state.ser");
        } catch (IOException e) {
            System.err.println("Error closing file streams, state.ser may be corrupted");
        }

        if (context == null) {
            return new ApplicationContext(new TreeSet<>());
        }
        return context;
    }

    /**
     * Attempts to load the UserFileJTree from a serialized file.
     * If no file is found, a new default UserFileJTree is created.
     * @return the UserFileJTree loaded from the serialized file, or a new default UserFileJTree if the file is not found
     */
    public static UserFileJTree loadUserFileJTree() {
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        UserFileJTree fileTree = null;

        // creating file streams
        try {
            fileIn = new FileInputStream(FILE_PATH);
            in = new ObjectInputStream(fileIn);
        } catch (IOException e) {
            System.err.println("loadUserFileJTree :: File state.ser not found at " + FILE_PATH);
            return new UserFileJTree(); // cannot continue without file streams
        }

        // reading objects from file
        try {
            in.readObject(); // skip ApplicationContext
            fileTree = (UserFileJTree) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading UserFileJTree from state.ser");
        }

        // finishing up
        try {
            in.close();
            fileIn.close();
            System.out.println("Deserialized UserFileJTree is loaded from state.ser");
        } catch (IOException e) {
            System.err.println("Error closing file streams, state.ser may be corrupted");
        }

        if (fileTree == null) {
            return new UserFileJTree();
        }
        return fileTree;
    }
}
