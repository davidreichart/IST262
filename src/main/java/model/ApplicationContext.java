package model;

import model.data.FileTag;
import model.data.filetypes.SystemDirectory;
import model.data.filetypes.SystemDirectoryList;
import model.data.filetypes.SystemFile;

import java.io.Serializable;
import java.util.*;

/**
 * The ApplicationContext class centralizes all persistent data.
 * This class should be referred to at boot/termination of the program.
 */
public final class ApplicationContext implements Serializable {

    private TreeSet<FileTag> definedTags;
    private SystemDirectoryList systemDirectoryList;
    private ArrayList<SystemFile> systemFiles;

    /**
     * Instantiates a new instance of the ApplicationContext to store persistent data.
     * This class centralizes all persistent data (it is essentially the meta-state of the program).
     * @param definedTags A set of all FileTags created by the user.
     */
    public ApplicationContext(TreeSet<FileTag> definedTags) {
        this.definedTags = definedTags;
        this.systemDirectoryList = new SystemDirectoryList();
        this.systemFiles = new ArrayList<>();
    }

    /**
     * Adds a new FileTag to the list of possible tags that a user may attach to a file for additional
     * categorization/filtering/etc.
     * @param fileTag The FileTag to add to the list of usable tags.
     * @throws IllegalArgumentException If there is already a FileTag stored equal to the provided tag.
     */
    public void addNewFileTag(FileTag fileTag) throws IllegalArgumentException {
        if (!this.definedTags.contains(fileTag)) {
            this.definedTags.add(fileTag);
        } else {
            throw new IllegalArgumentException("The file tag you attempted to add is already defined.");
        }
    }

    /**
     * Adds a new SystemFile to the list of known system files.
     * If the file is already known, no action is taken.
     * This file will be displayed in the GUI under the "unknown" directory.s
     * @param systemFile The SystemFile to add to the list of known system files.
     */
    public void addNewSystemFile(SystemFile systemFile) {
        this.systemFiles.add(systemFile);
    }

    /**
     * Attempts to remove the given SystemFile from the list of known system files.
     * If the file is not found, no action is taken.
     * @param fileToRemove The SystemFile to remove from the list of known system files.
     */
    public void removeSystemFile(SystemFile fileToRemove) {
        this.systemFiles.remove(fileToRemove);
    }

    /**
     * Scans the list of known FileTags stored by the program, and removes it from the list if found.
     * @param tagName The name of the tag to remove.
     * @throws IllegalArgumentException If there is no known file tag with the provided name.
     */
    public void removeExistingFileTag(String tagName) throws IllegalArgumentException {
        boolean tagRemoved = false;
        // search for the tag and remove it if found
        for (FileTag tag : this.definedTags) {
            if (tag.getName().equals(tagName)) {
                this.definedTags.remove(tag);
                tagRemoved = true;
                break;
            }
        }

        // the tag was not found, throw an exception
        if (!tagRemoved) {
            throw new IllegalArgumentException("There is no known file tag with the name provided. No tag has been removed.");
        }
    }

    /**
     * Defined tags refer to a set of all tags declared by the user. A set prevents duplicate creation and tag reuse.
     * @return A set of all FileTag objects know by the program.
     */
    public TreeSet<FileTag> getDefinedTags() {
        return definedTags;
    }

    /**
     * Defined tags refer to a set of all tags declared by the user. A set prevents duplicate creation and tag reuse.
     * This method erases the existing set and replaces it with the supplied set.
     * @param definedTags The set of FileTag objects to replace the existing set.
     */
    public void setDefinedTags(TreeSet<FileTag> definedTags) {
        this.definedTags = definedTags;
    }

    /**
     * Returns the list of known directories.
     * The list contains objects as instances of the SystemDirectory class.
     * @return The list of known directories.
     */
    public SystemDirectoryList getSystemDirectoryList() {
        return systemDirectoryList;
    }

    /**
     * Returns a list of all known system files currently tracked by the program.
     * @return A list of all known system files currently tracked by the program.
     */
    public ArrayList<SystemFile> getSystemFiles() {
        return systemFiles;
    }

    /**
     * Checks if the list of known directories contains the provided directory.
     * @param directory The directory to check for.
     * @return True if the directory is known, false otherwise.
     */
    public boolean containsDirectory(SystemDirectory directory) {
        return systemDirectoryList.containsDirectory(directory);
    }
}
