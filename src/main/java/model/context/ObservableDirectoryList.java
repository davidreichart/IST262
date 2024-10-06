package model.context;

import java.io.File;
import java.net.http.WebSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The ObservableDirectoryList class is a representation of a list of directories that the program is currently tracking.
 * This can be "subscribed" to by adding the DirectoryListListener interface to a class.
 * Listeners must be added to the list of listeners to receive updates when the list of directories changes.
 * This is done by calling the addListener() method in the {@link controller.ApplicationController} class.
 */
public class ObservableDirectoryList {

    private HashSet<UserDirectory> userDirectories;
    private ArrayList<DirectoryListListener> listeners;

    /**
     * Constructs a new ObservableDirectoryList object.
     * This object is a list of directories that the program is currently tracking.
     * Listeners can be added to this list to receive updates when the list of directories changes.
     */
    public ObservableDirectoryList() {
        this.userDirectories = new HashSet<>();
        this.listeners = new ArrayList<>();
    }

    /**
     * Adds the provided listener to the list of listeners.
     * If the listener is already listening, this method does nothing.
     * @param listener The listener to add.
     */
    public void addListener(DirectoryListListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Removes the provided listener from the list of listeners.
     * If the listener is not currently listening, this method does nothing.
     * @param listener The listener to remove.
     */
    public void removeListener(DirectoryListListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Notifies all listeners that the list of directories has changed.
     * Listeners should update their views accordingly to reflect the change.
     */
    public void notifyListeners() {
        for (DirectoryListListener listener : this.listeners) {
            listener.directoryListChanged(this.userDirectories);
        }
    }

    /**
     * Adds the provided directory to the list of tracked directories by the program.
     * @param directoryPath The directory to begin tracking.
     * @throws IllegalArgumentException If the input directory is already being tracked.
     */
    public void addNewDirectory(String directoryPath) throws IllegalArgumentException {
        // must be given a valid directory
        if (!Files.isDirectory(Path.of(directoryPath))) {
            throw new IllegalArgumentException("The provided path is not a directory.");
        }

        // only add new directories
        UserDirectory newDirectory = new UserDirectory(new File(directoryPath));
        if (!this.userDirectories.contains(newDirectory)) {
            this.userDirectories.add(new UserDirectory(new File(directoryPath)));
        } else {
            throw new IllegalArgumentException("The directory path you attempted to add already is already stored by the program.");
        }

        // notify listeners of the change
        notifyListeners();
    }

    public HashSet<UserDirectory> getUserDirectories() {
        return userDirectories;
    }
}
