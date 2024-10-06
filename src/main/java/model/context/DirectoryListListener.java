package model.context;

import java.util.HashSet;

/**
 * Interface for classes that want to listen for changes to the directory list.
 */
public interface DirectoryListListener {

    /**
     * Called when the directory list has changed.
     * Listeners should update their view of the directory list to match the new list.
     * @param newDirectoryList The new list of directories.
     */
    void directoryListChanged(HashSet<UserDirectory> newDirectoryList);
}
