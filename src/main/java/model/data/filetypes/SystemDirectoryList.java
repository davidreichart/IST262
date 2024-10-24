package model.data.filetypes;

import java.util.HashSet;

/**
 * The SystemDirectoryList class is a representation of a list of SystemDirectories.
 * This class can be listened to by implementing the {@link SystemDirectoryListListener} interface.
 * Any listening classes will be notified when the list of SystemDirectories changes.
 */
public class SystemDirectoryList {

    private HashSet<SystemDirectory> systemDirectories;
    private HashSet<SystemDirectoryListListener> listeners;

    public SystemDirectoryList() {
        this.systemDirectories = new HashSet<>();
        this.listeners = new HashSet<>();
    }

    /**
     * Adds a SystemDirectory to the list of SystemDirectories.
     * @param systemDirectory The SystemDirectory to add.
     * @throws IllegalArgumentException If the SystemDirectory is already in the list.
     */
    public void addDirectory(SystemDirectory systemDirectory) throws IllegalArgumentException {
        for (SystemDirectory directory : this.systemDirectories) {
            if (directory.directoryPath().equals(systemDirectory.directoryPath())) {
                throw new IllegalArgumentException("The directory you attempted to add is already in the list.");
            }
        }
        systemDirectories.add(systemDirectory);

        notifyListeners();
    }

    /**
     * Adds a new listener to be updated when the list of SystemDirectories changes.
     * If the listener is already listening, this method does nothing.
     * @param listener The listener to add.
     */
    public void addListener(SystemDirectoryListListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Removes a listener that was previously being updated when the list of SystemDirectories changes.
     * If the listener is not currently listening, this method does nothing.
     * @param listener The listener to remove.
     */
    public void removeListener(SystemDirectoryListListener listener) {
        this.listeners.remove(listener);

        notifyListeners();
    }

    /**
     * Notifies all listeners that the list of SystemDirectories has changed.
     * This method calls all implementations of {@link SystemDirectoryListListener#systemDirectoriesListUpdated(HashSet)}.
     * Listeners should have implemented logic to respond to the list of SystemDirectories being updated.
     */
    public void notifyListeners() {
        for (SystemDirectoryListListener listener : this.listeners) {
            listener.systemDirectoriesListUpdated(this.systemDirectories);
        }
    }

    /**
     * Informs if this list of SystemDirectories contains a specific SystemDirectory.
     * @param directory The SystemDirectory to check for.
     * @return true if the SystemDirectory is in the list, false otherwise.
     */
    public boolean containsDirectory(SystemDirectory directory) {
        return systemDirectories.contains(directory);
    }
}
