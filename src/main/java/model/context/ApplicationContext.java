package model.context;

import model.data.FileTag;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * The ApplicationContext class centralizes all persistent data.
 * This class should be referred to at boot/termination of the program.
 */
public final class ApplicationContext {

    private TreeSet<FileTag> definedTags;
    private ArrayList<UserDirectory> knownDirectories;

    /**
     * Instantiates a new instance of the ApplicationContext to store persistent data.
     * This class centralizes all persistent data (it is essentially the meta-state of the program).
     * @param definedTags A set of all FileTags created by the user.
     * @param knownDirectories A list of Strings representing file directories to be scanned by the program for image files.
     */
    public ApplicationContext(TreeSet<FileTag> definedTags, ArrayList<UserDirectory> knownDirectories) {
        this.definedTags = definedTags;
        this.knownDirectories = knownDirectories;
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
        if (!this.knownDirectories.contains(newDirectory)) {
            this.knownDirectories.add(new UserDirectory(new File(directoryPath)));
        } else {
            throw new IllegalArgumentException("The directory path you attempted to add already is already stored by the program.");
        }
    }

    /**
     * Scans the list of stored directories and, if a matching one is found, removes it from the list.
     * After being removed, the program will no longer track the directory.
     * @param directoryPath The directory to remove from tracking.
     * @throws IllegalArgumentException If the directory provided is not found within the stored list of tracked directories.
     */
    public void removeExistingDirectory(String directoryPath) throws IllegalArgumentException {
        boolean directoryRemoved = false;
        // search for the directory and remove it if found
        for (int i = 0; i < this.knownDirectories.size(); i++) {
            UserDirectory currentDirectory = this.knownDirectories.get(i);
            if (currentDirectory.getDirectoryPath().getName().equals(directoryPath)) {
                this.knownDirectories.remove(i);
                directoryRemoved = true;
                break;
            }
        }

        // the directory was not found, throw an exception
        if (!directoryRemoved) {
            throw new IllegalArgumentException("There was no stored directory found with the input path.");
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
     * Known directories refer to locations on the user's computer that should be observed so that the program may
     * mirror the user's images to them through this program.
     * This list controls what directories are relevant as instructed by the user.
     * @return A list of Strings containing all relevant file recordists on the user's computer to be watched for images.
     */
    public ArrayList<UserDirectory> getKnownDirectories() {
        return knownDirectories;
    }

    /**
     * Known directories refer to locations on the user's computer that should be observed so that the program may
     * mirror the user's images to them through this program.
     * This list controls what directories are relevant as instructed by the user.
     * This method erases the existing list and replaces it with the supplied list.
     * @param knownDirectories The list of Strings of directory paths to replace the existing list.
     */
    public void setKnownDirectories(ArrayList<UserDirectory> knownDirectories) {
        this.knownDirectories = knownDirectories;
    }
}
