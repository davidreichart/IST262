package model.data.filetypes;

import java.util.HashSet;

/**
 * The SystemDirectoryListListener interface provides a method for responding to changes in the list of SystemDirectories.
 * Any class that needs to respond to changes in the list of SystemDirectories should implement this interface.
 */
public interface SystemDirectoryListListener {

    /**
     * Method to be called when the list of SystemDirectories is updated.
     * Any logic that needs to respond to the list of SystemDirectories being updated should be placed here.
     * @param systemDirectories The updated list of SystemDirectories.
     */
    public void systemDirectoriesListUpdated(HashSet<SystemDirectory> systemDirectories);
}
