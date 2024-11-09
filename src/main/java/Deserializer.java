import model.ApplicationContext;
import view.filebrowser.UserFileJTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public final class Deserializer {

    private static final String FILE_PATH = "src/main/resources/state.ser";

    public Deserializer() {
    }


    public static ApplicationContext loadApplicationContext() {
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        ApplicationContext context = null;

        // creating file streams
        try {
            fileIn = new FileInputStream(FILE_PATH);
            in = new ObjectInputStream(fileIn);
        } catch (IOException e) {
            System.err.println("Cannot load state.ser at " + FILE_PATH);
            return null; // cannot continue without file streams
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

        return context;
    }

    public static UserFileJTree loadUserFileJTree() {
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        UserFileJTree fileTree = null;

        // creating file streams
        try {
            fileIn = new FileInputStream(FILE_PATH);
            in = new ObjectInputStream(fileIn);
        } catch (IOException e) {
            System.err.println("Cannot load state.ser at " + FILE_PATH);
            return null; // cannot continue without file streams
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

        return fileTree;
    }
}
