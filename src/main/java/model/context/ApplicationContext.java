package model.context;

import model.data.FileTag;
import model.ImageFile;

import java.util.*;

/**
 * The ApplicationContext class centralizes all persistent data.
 * This class should be referred to at boot/termination of the program.
 */
public class ApplicationContext {

    TreeSet<FileTag> definedTags;
    ArrayList<String> knownDirectories;
    ArrayList<ImageFile> knownImageFiles;

    /**
     * Instantiates a new instance of the ApplicationContext to store persistent data.
     * This class centralizes all persistent data (it is essentially the meta-state of the program).
     * @param definedTags A set of all FileTags created by the user.
     * @param knownDirectories A list of Strings representing file directories to be scanned by the program for image files.
     * @param knownImageFiles A list of ImageFile objects holding individual metadata on any previously seen files by the program.
     */
    public ApplicationContext(TreeSet<FileTag> definedTags, ArrayList<String> knownDirectories, ArrayList<ImageFile> knownImageFiles) {
        this.definedTags = definedTags;
        this.knownDirectories = knownDirectories;
        this.knownImageFiles = knownImageFiles;
    }

    /**
     * Returns a string displaying all currently stored information comprising the program's context/state.
     * @return A string detailing all currently stored state variables.
     */
    @Override
    public String toString() {
        StringBuilder object = new StringBuilder();
        object.append("All known directories: \n");
        for (String directory : this.knownDirectories) {
            object.append(directory)
                    .append("\n");
        }
        object.append("All defined file tags: \n");
        for (FileTag tag : this.definedTags) {
            object.append(tag.getName())
                    .append("\n");
        }
        object.append("All known Image File objects by name & path: \n");
        for (ImageFile imageFile : knownImageFiles) {
            object.append(imageFile.getFile().getName()).append(" @ ")
                    .append(imageFile.getFile().getPath())
                    .append("\n");
        }
        return object.toString();
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

    /**
     * Known image files refer to a list of ImageFile objects catalogued by the program.
     * These objects represent metadata on previously scanned image files.
     * @return A list of all ImageFile objects currently catalogued.
     */
    public ArrayList<ImageFile> getKnownImageFiles() {
        return knownImageFiles;
    }

    /**
     * Known image files refer to a list of ImageFile objects catalogued by the program.
     * These objects represent metadata on previously scanned image files.
     * This method erases the existing list of ImageFiles and replaces it with the provided list.
     * @param knownImageFiles The new list of ImageFiles to be saved.
     */
    public void setKnownImageFiles(ArrayList<ImageFile> knownImageFiles) {
        this.knownImageFiles = knownImageFiles;
    }
}
