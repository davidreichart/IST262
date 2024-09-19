package model.context;

import model.data.FileTag;

import java.io.File;
import java.util.*;

/**
 * The ApplicationContext class centralizes all persistent data.
 * This class should be referred to at boot/termination of the program.
 */
public class ApplicationContext {

    private static ApplicationContext INSTANCE;
    TreeSet<FileTag> definedTags;
    ArrayList<String> knownDirectories;

    /**
     * Instantiates a new instance of the ApplicationContext to store persistent data.
     * This class centralizes all persistent data (it is essentially the meta-state of the program).
     * @param definedTags A set of all FileTags created by the user.
     * @param knownDirectories A list of Strings representing file directories to be scanned by the program for image files.
     */
    public ApplicationContext(TreeSet<FileTag> definedTags, ArrayList<String> knownDirectories) {
        this.definedTags = definedTags;
        this.knownDirectories = knownDirectories;
    }

    public void addNewFileTag(FileTag fileTag) throws IllegalArgumentException {
        if (!this.definedTags.contains(fileTag)) {
            this.definedTags.add(fileTag);
        } else {
            throw new IllegalArgumentException("The file tag you attempted to add is already defined.");
        }
    }

    public void removeExistingFileTag(String tagName) throws IllegalArgumentException {
        boolean tagRemoved = false;
        for (FileTag tag : this.definedTags) {
            if (tag.getName().equals(tagName)) {
                this.definedTags.remove(tag);
                tagRemoved = true;
                break;
            }
        }
        if (!tagRemoved) {
            throw new IllegalArgumentException("There is no known file tag with the name provided. No tag has been removed.");
        }
    }

    public void addNewDirectory(String directoryPath) throws IllegalArgumentException {
        if (!this.knownDirectories.contains(directoryPath)) {
            this.knownDirectories.add(directoryPath);
        } else {
            throw new IllegalArgumentException("The directory path you attempted to add already is already stored by the program.")
        }
    }

    public void removeExistingDirectory(String directoryPath) throws IllegalArgumentException {
        boolean directoryRemoved = false;
        for (String directory :this.knownDirectories) {
            if (directory.equals(directoryPath)) {
                this.knownDirectories.remove(directory);
                directoryRemoved = true;
                break;
            }
        }
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
    public ArrayList<String> getKnownDirectories() {
        return knownDirectories;
    }

    /**
     * Known directories refer to locations on the user's computer that should be observed so that the program may
     * mirror the user's images to them through this program.
     * This list controls what directories are relevant as instructed by the user.
     * This method erases the existing list and replaces it with the supplied list.
     * @param knownDirectories The list of Strings of directory paths to replace the existing list.
     */
    public void setKnownDirectories(ArrayList<String> knownDirectories) {
        this.knownDirectories = knownDirectories;
    }
}
