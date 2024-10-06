package model.context;

import java.io.File;
import java.net.http.WebSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;

public class ObservableDirectoryList {

    private HashSet<UserDirectory> userDirectories;
    private ArrayList<DirectoryListListener> listeners;

    public ObservableDirectoryList() {
        this.userDirectories = new HashSet<>();
        this.listeners = new ArrayList<>();
    }

    public void addListener(DirectoryListListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(DirectoryListListener listener) {
        this.listeners.remove(listener);
    }

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
